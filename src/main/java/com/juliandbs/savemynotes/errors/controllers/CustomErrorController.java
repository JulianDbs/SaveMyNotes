package com.juliandbs.savemynotes.errors.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.RequestDispatcher;

@Controller
public class CustomErrorController implements ErrorController {

	@GetMapping
	public String handleError(HttpServletRequest request) {
		String error = "errors/error-500";
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		if (status != null) {
			Integer statusCode = Integer.valueOf(status.toString());
			switch(statusCode) {
				case 401: error = "errors/error-401"; break;
				case 403: error = "errors/error-403"; break;
				case 404: error = "errors/error-404"; break;
				case 500: error = "errors/error-500"; break;
			}
		}
		return error;
	}
}
