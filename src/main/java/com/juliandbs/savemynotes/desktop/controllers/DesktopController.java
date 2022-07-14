package com.juliandbs.savemynotes.desktop.controllers;

import com.juliandbs.savemynotes.persistence.services.NoteServiceImpl;
import com.juliandbs.savemynotes.persistence.models.Note;
import com.juliandbs.savemynotes.main.utils.CustomResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

@Controller
public class DesktopController {

	@Autowired
	private NoteServiceImpl noteService;

	@GetMapping("/desktop")
	public String getDesktopView(Model model, Authentication auth) {
		CustomResponse response = noteService.getUserNotes(model, auth);
		model = response.getModel();
		return response.getUrl();
	}

}
