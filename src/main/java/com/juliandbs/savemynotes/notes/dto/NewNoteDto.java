package com.juliandbs.savemynotes.notes.dto;

import com.juliandbs.savemynotes.notes.annotations.ValidTitle;
import com.juliandbs.savemynotes.notes.annotations.ValidStringArrayContent;

import java.lang.NullPointerException;

public class NewNoteDto {

	@ValidTitle
	private String title;

	@ValidStringArrayContent
	private String[] content;

	public NewNoteDto() {}

	public String getTitle() {return title;}

	public void setTitle(String title) {this.title = title;}

	public String[] getContent() {return content;}

	public void setContent(String[] content) {this.content = content;}

	@Override
	public int hashCode() {
		return title.hashCode() + content.hashCode();
	}
}
