package com.juliandbs.savemynotes.main.utils;

import org.springframework.ui.Model;

public class CustomResponse {

	private String url;

	private Model model;

	public CustomResponse(String url, Model model) throws NullPointerException {
		if (url == null || model == null)
			throw new NullPointerException();
		this.url = url;
		this.model = model;
	}

	public String getUrl() {return url;}

	public Model getModel() {return model;}

	@Override
	public boolean equals(Object object) throws NullPointerException {
		boolean result = false;
		if (object == null)
			throw new NullPointerException();
		if (object instanceof CustomResponse) {
			CustomResponse customResponse = (CustomResponse) object;
			result = customResponse.getUrl().equals(url) &&
				customResponse.getModel().toString().equals(model.toString());
		}
		return result;
	}

	@Override
	public int hashCode() {
		return url.hashCode() + model.hashCode();
	}

	@Override
	public String toString() {
		return url.toString() + " "  + model.toString();
	}
}
