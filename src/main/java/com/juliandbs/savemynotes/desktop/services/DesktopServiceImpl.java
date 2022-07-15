package com.juliandbs.savemynotes.desktop.services;

import com.juliandbs.savemynotes.desktop.services.DesktopService;
import com.juliandbs.savemynotes.main.utils.CustomResponse;
import com.juliandbs.savemynotes.security.userdetails.UserDetailsImpl;

import org.springframework.ui.Model;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.lang.NullPointerException;

@Service("desktopService")
public class DesktopServiceImpl implements DesktopService {

	@Override
	public CustomResponse getHomeView(Model model, Authentication auth) throws NullPointerException {
		if (model == null)
			throw new NullPointerException("null model");
		String toUrl = "home/home";
		if (auth != null) {
			UserDetailsImpl user = (UserDetailsImpl) auth.getPrincipal();
			if (user.isValid())
				toUrl = "redirect:/desktop";
		}
		return new CustomResponse(toUrl, model);
	}
}

