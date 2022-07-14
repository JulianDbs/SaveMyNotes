package com.juliandbs.savemynotes.notes.dto;

import com.juliandbs.savemynotes.main.utils.ArrayTools;
import com.juliandbs.savemynotes.notes.annotations.ValidTitle;
import com.juliandbs.savemynotes.notes.annotations.ValidStringContent;

import java.lang.NullPointerException;

public class EditNoteDto {

	private Long id;

	@ValidTitle
	private String title;

	@ValidStringContent
	private String content;

	private Long lineCount;

	public EditNoteDto() {}

	public EditNoteDto(
		final Long id,
		final String title,
		final String[] content,
		final Long lineCount
	) throws NullPointerException {
		if (id == null || title == null || content == null || lineCount == null)
			throw new NullPointerException();
		this.id = id;
		this.title = title;
		this.content = ArrayTools.stringArrayToString(content);
		this.lineCount = lineCount;
	}

	public Long getId() {return id;}

	public void setId(Long id) {this.id = id;}

	public String getTitle() {return title;}

	public void setTitle(String title) {this.title = title;}

	public String getContent() {return content;}

	public void setContent(String content) {this.content = content;}

	public Long getLineCount() {return lineCount;}

	public void setLineCount(Long lineCount) {this.lineCount = lineCount;}

	@Override
	public int hashCode() {
		return id.hashCode() + title.hashCode() + content.hashCode();
	}
}
