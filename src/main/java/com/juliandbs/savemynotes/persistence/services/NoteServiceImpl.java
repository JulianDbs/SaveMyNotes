package com.juliandbs.savemynotes.persistence.services;

import com.juliandbs.savemynotes.notes.dto.NewNoteDto;
import com.juliandbs.savemynotes.persistence.services.NoteService;
import com.juliandbs.savemynotes.security.userdetails.UserDetailsImpl;
import com.juliandbs.savemynotes.persistence.repositories.NoteRepository;
import com.juliandbs.savemynotes.persistence.exceptions.NoteNotFoundException;
import com.juliandbs.savemynotes.persistence.exceptions.NoteAlreadyExistsException;
import com.juliandbs.savemynotes.persistence.exceptions.UnauthorizedUserException;
import com.juliandbs.savemynotes.persistence.exceptions.InvalidUserDetailsException;
import com.juliandbs.savemynotes.main.utils.ValidationErrorFilter;
import com.juliandbs.savemynotes.main.utils.CustomResponse;
import com.juliandbs.savemynotes.persistence.models.Note;
import com.juliandbs.savemynotes.main.utils.CodeGenerator;
import com.juliandbs.savemynotes.notes.dto.NoteDto;
import com.juliandbs.savemynotes.notes.dto.NoteResumeDto;
import com.juliandbs.savemynotes.notes.dto.EditNoteDto;
import com.juliandbs.savemynotes.main.utils.ArrayTools;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.security.core.Authentication;
import org.springframework.dao.DataIntegrityViolationException;

import java.lang.NullPointerException;
import java.util.List;
import java.util.LinkedList;
import java.util.Collections;
import java.util.stream.Collectors;

@Service("noteService")
public class NoteServiceImpl implements NoteService {

	public static final String redirectToDesktop = "redirect:/desktop";
	public static final String redirectToError401 = "redirect:/error-401";
	public static final String redirectToError403 = "redirect:/error-403";
	public static final String redirectToError404 = "redirect:/error-404";
	public static final String redirectToError500 = "redirect:/error-500";
	public static final String redirectToEditNote = "redirect:/edit-note";
	public static final String redirectToEditNoteFailure = "redirect:/edit-note-failure";
	public static final String redirectToNoteNotFound = "redirect:/note-not-found";
	public static final String redirectToNoteWarning = "redirect:/note-warning";
	public static final String redirectToDeleteNoteSuccess = "redirect:/delete-note-success";
	public static final String redirectToDeleteNoteFailure = "redirect:/delete-note-failure";

	public static final String toDesktop = "desktop/desktop";
	public static final String toNote = "note/note";
	public static final String toNewNote = "note/new-note";
	public static final String toEditNote = "note/edit-note";
	public static final String toEditNoteSuccess = "note/edit-note-success";
	public static final String toEditNoteFailure = "note/edit-note-failure";
	public static final String toDeleteNote = "note/delete-note";

	@Autowired
	private ValidationErrorFilter validationErrorFilter;

	@Autowired
	private NoteRepository noteRepository;

	//Create
	@Override
	public CustomResponse createNewNote(Model model, Errors errors, NewNoteDto newNoteDto, Authentication auth) {
		String toUrl = redirectToDesktop;
		if (errors.hasErrors()) {
			toUrl = toNewNote;
			model.addAttribute("note", newNoteDto);
			model.addAttribute("titleErrors", validationErrorFilter.getTitleNoteErrors(errors));
			model.addAttribute("contentErrors", validationErrorFilter.getContentNoteErrors(errors));
		} else {
			UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
			if (user.isValid()) {
				String code = CodeGenerator.generateUniqueCode(user.getUsername());
				Note newNote = new Note(user.getUsername(), code, newNoteDto.getTitle(), newNoteDto.getContent());
				try {
					noteRepository.addNewNote(newNote);
				} catch (NullPointerException a) {
					toUrl = redirectToError500;
				} catch (NoteAlreadyExistsException b) {
					toUrl = redirectToNoteWarning;
				}
			} else {
				toUrl = redirectToError401;
			}
		}
		return new CustomResponse(toUrl, model);
	}

	//Read
	@Override
	public CustomResponse getUserNotes(Model model, Authentication auth) throws NullPointerException {
		if (model == null || auth == null)
			throw new NullPointerException();
		String toUrl = toDesktop;
		UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
		if (user.isValid()) {
			List<Note> notes = noteRepository.findNotesByEmail(user.getUsername());
			Collections.sort(notes);
			List<NoteResumeDto> dtoList = this.noteModelToNoteResumeDtoList(notes);
			model.addAttribute("noteList", dtoList);
		} else {
			toUrl = redirectToError500;
		}
		return new CustomResponse(toUrl, model);
	}

	private static List<NoteResumeDto> noteModelToNoteResumeDtoList(List<Note> notes) {
		return notes.stream()
				.map(Note::toNoteResumeDto)
				.collect(Collectors.toCollection(LinkedList::new));
	}

	@Override
	public CustomResponse getUserNoteById(final String id, Model model, Authentication auth, String to) throws NullPointerException {
		if (id == null || model == null || auth == null || to == null)
			throw new NullPointerException();
		String toUrl = to;
		try {
			Note note = noteRepository.getNoteById(Long.valueOf(id), (UserDetailsImpl) auth.getPrincipal());
			switch (to) {
				case "note/note" : model.addAttribute("note", note.toNoteDto()); break;
				case "note/edit-note" : model.addAttribute("note", note.toEditNoteDto()); break;
				default : model.addAttribute("note", note.toNoteDto()); break;
			}
		} catch (NullPointerException e) {
			toUrl = redirectToError500;	//what to do here ?
		} catch (NoteNotFoundException e) {
			toUrl = redirectToNoteNotFound;
		} catch (UnauthorizedUserException e) {
			toUrl = redirectToError403;	//redirect to a custom error view ??
		} catch (InvalidUserDetailsException e) {
			toUrl = redirectToError500;	//what to do here ?
		}
		return new CustomResponse(toUrl, model);
	}

	//Update
	@Override
	public CustomResponse updateUserNote(EditNoteDto editNoteDto, Errors errors, Model model, Authentication auth) throws NullPointerException {
		if (editNoteDto == null || model == null || auth == null)
			throw new NullPointerException();
		String toUrl = toEditNoteSuccess;
		if (errors.hasErrors()) {
			toUrl = toEditNote;
			model.addAttribute("note", editNoteDto);
			model.addAttribute("titleErrors", validationErrorFilter.getTitleNoteErrors(errors));
			model.addAttribute("contentErrors", validationErrorFilter.getContentNoteErrors(errors));
		} else {
			try {
				UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
				Long editNoteId = editNoteDto.getId();
				Note note = noteRepository.getNoteById(editNoteId, user);
				String newTitle = editNoteDto.getTitle();
				if (!note.getTitle().equals(newTitle)) {
					noteRepository.updateNoteTitleById(editNoteId, newTitle, user);
				}
				String[] newContent = ArrayTools.stringToStringArray(editNoteDto.getContent());
				if (!note.getContent().equals(newContent)) {
					noteRepository.updateNoteContentById(editNoteId, newContent, user);
				}
				noteRepository.updateNoteLastAccessById(editNoteId);
			} catch (DataIntegrityViolationException e) {
				toUrl = redirectToError500;
			} catch (NullPointerException e) {
				toUrl = redirectToError500;	//what to do here ?
			} catch (NoteNotFoundException e) {
				toUrl = redirectToNoteNotFound;
			} catch (UnauthorizedUserException e) {
				toUrl = redirectToError403;	//redirect to a custom error view ??
			} catch (InvalidUserDetailsException e) {
				e.printStackTrace();
				toUrl = redirectToError500;	//what to do here ?
			}
		}
		return new CustomResponse(toUrl, model);
	}

	//Delete
	public CustomResponse deleteUserNote(String id, Model model, Authentication auth) throws NullPointerException {
		if (id == null || model == null || auth == null)
			throw new NullPointerException();
		String toUrl = redirectToDeleteNoteSuccess;
		try {
			UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
			noteRepository.deleteNoteById(Long.valueOf(id), user);
		} catch (DataIntegrityViolationException e) {
			toUrl = redirectToError500;
		} catch (NullPointerException e) {
			toUrl = redirectToError500;
		} catch (NoteNotFoundException e) {
			toUrl = redirectToNoteNotFound;
		} catch (UnauthorizedUserException e) {
			toUrl = redirectToError403;
		} catch (InvalidUserDetailsException e) {
			toUrl = redirectToError500;
		}
		return new CustomResponse(toUrl, model);
	}
}
