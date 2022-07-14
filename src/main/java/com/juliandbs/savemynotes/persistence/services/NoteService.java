package com.juliandbs.savemynotes.persistence.services;

import com.juliandbs.savemynotes.notes.dto.NewNoteDto;
import com.juliandbs.savemynotes.notes.dto.EditNoteDto;
import com.juliandbs.savemynotes.main.utils.CustomResponse;

import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.security.core.Authentication;

public interface NoteService {

	//Create
	public CustomResponse createNewNote(Model model, Errors errors, NewNoteDto newNote, Authentication auth);

	//Read
	public CustomResponse getUserNotes(Model model, Authentication auth) throws NullPointerException;

	public CustomResponse getUserNoteById(String id, Model model, Authentication auth, String to) throws NullPointerException;

	//Update
	public CustomResponse updateUserNote(EditNoteDto editNoteDto, Errors errors, Model model, Authentication auth) throws NullPointerException;

	//Delete
	public CustomResponse deleteUserNote(String id, Model model, Authentication auth) throws NullPointerException;

	//public CustomResponse deleteAllUserNotes(Errors errors, Model model, Authentication auth) throws NullPointerException;

}
