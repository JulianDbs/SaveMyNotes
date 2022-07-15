package com.juliandbs.savemynotes.desktop.services;

import com.juliandbs.savemynotes.main.utils.CustomResponse;

import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;

import java.lang.NullPointerException;

public interface DesktopService {

	public CustomResponse getHomeView(Model model, Authentication auth) throws NullPointerException;
}
