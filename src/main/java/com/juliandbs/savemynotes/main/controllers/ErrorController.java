package com.juliandbs.savemynotes.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;


@Controller
public class ErrorController {

	@GetMapping("/errors")
	public String processError(HttpServletRequest request) {
		String result = "errors/error-500";
		Integer httpErrorCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
		if (httpErrorCode != null)
			switch(httpErrorCode) {
				case 400: result = "errors/error-400"; break;
				case 401: result = "errors/error-501"; break;
				case 500: result = "errors/error-500"; break;
			}
		return result;
	}

}

