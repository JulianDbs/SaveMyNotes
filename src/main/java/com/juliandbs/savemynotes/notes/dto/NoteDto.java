package com.juliandbs.savemynotes.notes.dto;

import java.lang.NullPointerException;

public class NoteDto {

	private Long id;

	private String title;

	private String content;

	private Long lineCount;

	public NoteDto() {}

	public NoteDto(final Long id, final String title, final String content, final Long lineCount) throws NullPointerException {
		if (id == null || title == null || content == null || lineCount == null)
			throw new NullPointerException();
		this.id = id;
		this.title = title;
		this.content = content;
		this.lineCount = lineCount;
	}

	public Long getId() {return id;}

	public String getTitle() {return title;}

	public String getContent() {return content;}

	public Long getLineCount() {return lineCount;}
}
