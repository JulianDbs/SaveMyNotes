package com.juliandbs.savemynotes.notes.controllers;

import com.juliandbs.savemynotes.persistence.services.NoteServiceImpl;
import com.juliandbs.savemynotes.security.userdetails.UserDetailsImpl;
import com.juliandbs.savemynotes.notes.dto.NewNoteDto;
import com.juliandbs.savemynotes.notes.dto.EditNoteDto;
import com.juliandbs.savemynotes.main.utils.CodeGenerator;
import com.juliandbs.savemynotes.main.utils.CustomResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class NoteController {

	@Autowired
	private NoteServiceImpl noteService;

	@GetMapping("note")
	public String getNoteView(@RequestParam String id, Model model, Authentication auth) {
		CustomResponse response = noteService.getUserNoteById(id, model, auth, "note/note");
		model = response.getModel();
		return response.getUrl();
	}

	@GetMapping("new-note")
	public String getNewNoteView(Model model) {
		model.addAttribute("note", new NewNoteDto());
		return "note/new-note";
	}

	@PostMapping("new-note")
	public String addNewNote(@Valid @ModelAttribute("note") NewNoteDto newNoteDto, Errors errors, Model model, Authentication auth) {
		CustomResponse response = noteService.createNewNote(model, errors, newNoteDto, auth);
		model = response.getModel();
		return response.getUrl();
	}

	@GetMapping("edit-note")
	public String getNoteViewForEdit(@RequestParam String id, Model model, Authentication auth) {
		CustomResponse response = noteService.getUserNoteById(id, model, auth, "note/edit-note");
		model = response.getModel();
		return response.getUrl();
	}

	@PostMapping("edit-note")
	public String updateNote(@Valid @ModelAttribute("note") EditNoteDto editNoteDto, Errors errors, Model model, Authentication auth) {
		CustomResponse response = noteService.updateUserNote(editNoteDto, errors, model, auth);
		model = response.getModel();
		return response.getUrl();
	}

	@GetMapping("delete-note")
	public String getDeleteNoteView(@RequestParam String id, Model model, Authentication auth) {
		CustomResponse response = noteService.getUserNoteById(id, model, auth, "note/delete-note");
		model = response.getModel();
		return response.getUrl();
	}

	@PostMapping("delete-note")
	public String deleteNote(@RequestParam String id, Model model, Authentication auth) {
		CustomResponse response = noteService.deleteUserNote(id, model, auth);
		model = response.getModel();
		return response.getUrl();
	}
}
