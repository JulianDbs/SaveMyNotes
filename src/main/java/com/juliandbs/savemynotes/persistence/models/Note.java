package com.juliandbs.savemynotes.persistence.models;

import com.juliandbs.savemynotes.notes.dto.NoteDto;
import com.juliandbs.savemynotes.notes.dto.NoteResumeDto;
import com.juliandbs.savemynotes.notes.dto.EditNoteDto;
import com.juliandbs.savemynotes.main.utils.ArrayTools;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.lang.NullPointerException;
import java.lang.Comparable;

import javax.persistence.ElementCollection;

import org.hibernate.annotations.Type;

@Entity(name = "notes")
@Table(name = "notes")
public class Note implements Comparable<Note> {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "email", nullable = false, length = 40, unique = true)
	private String email;

	@Column(name = "creation_date", nullable = false)
	private LocalDate creation_date;

	@Column(name = "last_access", nullable = false)
	private LocalDateTime last_access;

	@Column(name = "code", nullable = false, unique = true)
	private String code;

	@Column(name = "title", nullable = false, length = 30)
	private String title;

	@Column(name = "content", nullable = false, columnDefinition = "text[]")
	@Type(type = "com.juliandbs.savemynotes.persistence.types.StringArrayType")
	private String[] content;

	public Note() {}

	public Note(
		String email,
		String code,
		String title,
		String[] content
	) throws NullPointerException {
		if (email == null || code == null || title == null || content == null)
			throw new NullPointerException();
		this.email = email;
		creation_date = LocalDate.now();
		last_access = LocalDateTime.now();
		this.code = code;
		this.title = title;
		this.content = content;
	}

	public Note(
		String email,
		LocalDate creation_date,
		LocalDateTime last_access,
		String code,
		String title,
		String[] content
	) throws NullPointerException {
		if (email == null || creation_date == null || last_access  == null || code == null || title == null || content == null)
			throw new NullPointerException();
		this.email = email;
		this.creation_date = creation_date;
		this.last_access = last_access;
		this.code = code;
		this.title = title;
		this.content = content;
	}

	public Note(
		Long id,
		String email,
		LocalDate creation_date,
		LocalDateTime last_access,
		String code,
		String title,
		String[] content
	) throws NullPointerException {
		if (id == null || email == null || creation_date == null || last_access == null || code == null || title == null || content == null)
			throw new NullPointerException();
		this.id = id;
		this.email = email;
		this.creation_date = creation_date;
		this.last_access = last_access;
		this.code = code;
		this.title = title;
		this.content = content;
	}

	public Long getId() {return id;}

	public void setId(Long id) {this.id = id;}

	public String getEmail() {return email;}

	public void setEmail(String email) {this.email = email;}

	public LocalDate getCreationDate() {return creation_date;}

	public void setCreationDate(LocalDate creation_date) {this.creation_date = creation_date;}

	public LocalDateTime getLastAccess() {return last_access;}

	public void setLastAccess(LocalDateTime last_access) {this.last_access = last_access;}

	public String getCode() {return code;}

	public void setCode(String code) {this.code = code;}

	public String getTitle() {return title;}

	public void setTitle(String title) {this.title = title;}

	public String[] getContent() {return content;}

	public void setContent(String[] content) {this.content = content;}

	public NoteDto toNoteDto() {
		return new NoteDto(id, title, ArrayTools.stringArrayToString(content), Long.valueOf(content.length));
	}

	public NoteResumeDto toNoteResumeDto() {
		return new NoteResumeDto(title, content, id);
	}

	public EditNoteDto toEditNoteDto() {
		return new EditNoteDto(id, title, content, Long.valueOf(content.length));
	}

	@Override
	public int compareTo(Note note) throws NullPointerException {
		if (note == null)
			throw new NullPointerException();
		return note.getTitle().compareTo(title) + note.getCreationDate().compareTo(creation_date);
	}

	@Override
	public boolean equals(Object object) throws NullPointerException {
		if (object == null)
			throw new NullPointerException();
		boolean result = false;
		if (object instanceof Note) {
			Note note = (Note) object;
			result = note.getId().equals(id) &&
				note.getEmail().equals(email) &&
				note.getCreationDate().equals(creation_date) &&
				note.getTitle().equals(title) &&
				note.getContent().equals(content);
		}
		return result;
	}

	@Override
	public int hashCode() {
		return id.hashCode() +
			email.hashCode() +
			creation_date.hashCode() +
			title.hashCode() +
			content.hashCode();
	}

	@Override
	public String toString() {
		return id.toString() + " " +
			email + " " +
			creation_date.toString() + " " +
			title + " " +
			content.toString();
	}
}
