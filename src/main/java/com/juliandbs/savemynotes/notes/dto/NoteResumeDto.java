package com.juliandbs.savemynotes.notes.dto;

import java.lang.NullPointerException;

public class NoteResumeDto {

	private String title;

	private String[] content;

	private Long id;

	public NoteResumeDto() {}

	public NoteResumeDto(final String title, final String[] content, final Long id) throws NullPointerException {
		if (title == null || content == null || id == null)
			throw new NullPointerException();
		this.title = title;
		this.content = buildContent(content);
		this.id = id;
	}

	private String[] buildContent(String[] content) {
		String[] lines = new String[10];
		lines[0] = (content.length >= 1)? content[0] : "";
		lines[1] = (content.length >= 2)? content[1] : "";
		lines[2] = (content.length >= 3)? content[2] : "";
		lines[3] = (content.length >= 4)? content[3] : "";
		lines[4] = (content.length >= 5)? content[4] : "";
		lines[5] = (content.length >= 6)? content[5] : "";
		lines[6] = (content.length >= 7)? content[6] : "";
		lines[7] = (content.length >= 8)? content[7] : "";
		lines[8] = (content.length >= 9)? content[8] : "";
		lines[9] = (content.length >= 10)? content[9] : "";
		return lines;
	}

	public String getTitle() {return title;}

	public String[] getContent() {return content;}

	public Long getId() {return id;}
}
