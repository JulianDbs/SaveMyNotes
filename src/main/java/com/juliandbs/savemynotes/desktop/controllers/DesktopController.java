package com.juliandbs.savemynotes.desktop.controllers;

import com.juliandbs.savemynotes.persistence.services.NoteServiceImpl;
import com.juliandbs.savemynotes.persistence.models.Note;
import com.juliandbs.savemynotes.main.utils.CustomResponse;
import com.juliandbs.savemynotes.desktop.services.DesktopServiceImpl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

@Controller
public class DesktopController {

	@Autowired
	private NoteServiceImpl noteService;

	@Autowired
	private DesktopServiceImpl desktopService;

	@GetMapping("/")
	public String getHomeView(Model model, Authentication auth) {
		CustomResponse response = desktopService.getHomeView(model, auth);
		model = response.getModel();
		return response.getUrl();
	}

	@GetMapping("/desktop")
	public String getDesktopView(Model model, Authentication auth) {
		CustomResponse response = noteService.getUserNotes(model, auth);
		model = response.getModel();
		return response.getUrl();
	}

}
