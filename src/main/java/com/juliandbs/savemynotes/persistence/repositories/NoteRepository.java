package com.juliandbs.savemynotes.persistence.repositories;

import com.juliandbs.savemynotes.persistence.repositories.CustomizedNoteRepository;
import com.juliandbs.savemynotes.persistence.models.Note;
import com.juliandbs.savemynotes.persistence.exceptions.NoteNotFoundException;
import com.juliandbs.savemynotes.persistence.exceptions.NoteAlreadyExistsException;
import com.juliandbs.savemynotes.persistence.exceptions.UnauthorizedUserException;
import com.juliandbs.savemynotes.persistence.exceptions.InvalidUserDetailsException;
import com.juliandbs.savemynotes.security.userdetails.UserDetailsImpl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.dao.DataIntegrityViolationException;

import java.lang.NullPointerException;
import java.util.Optional;
import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>, CustomizedNoteRepository {

	//Create
	@Override
	public default void addNewNote(Note note) throws NullPointerException, NoteAlreadyExistsException {
		if (note == null)
			throw new NullPointerException();
		try {
			Note result = this.save(note);
			this.flush();
		} catch (DataIntegrityViolationException e) {	//replace dis part with a dao exceptions processor ??
			throw new NoteAlreadyExistsException();
		}
	}

	//Read
	public default Note getNoteById(final Long id, final UserDetailsImpl user)
		throws NullPointerException, NoteNotFoundException, UnauthorizedUserException, InvalidUserDetailsException {
		if (id == null || user == null)
			throw new NullPointerException();
		Optional<Note> result = this.findNoteById(id);
		if (result.isEmpty())
			throw new NoteNotFoundException();
		if (!user.isValid())
			throw new InvalidUserDetailsException();
		if (!result.get().getEmail().equals(user.getUsername()))
			throw new UnauthorizedUserException();
		return result.get();
	}

	//useless method ???
	public default Note getNoteByEmailAndCode(final String email, final String code) throws NullPointerException, NoteNotFoundException {
		if (email == null || code == null)
			throw new NullPointerException();
		Optional<Note> result = this.findNoteByEmailAndCode(email, code);
		if (result.isEmpty())
			throw new NoteNotFoundException();
		return result.get();
	}

	//Update
	@Override
	public default void updateNoteTitleById(final Long id, final String newTitle, final UserDetailsImpl user)
		throws NullPointerException, NoteNotFoundException, InvalidUserDetailsException, UnauthorizedUserException {
		if (id == null || newTitle == null || user == null)
			throw new NullPointerException();
		if (!user.isValid())
			throw new InvalidUserDetailsException();
		Optional<Note> result = this.findNoteById(id);
		if (result.isEmpty())
			throw new NoteNotFoundException();
		if (!result.get().getEmail().equals(user.getUsername()))
			throw new UnauthorizedUserException();
		Note note = result.get();
		note.setTitle(newTitle);
		this.saveAndFlush(note);
	}

	@Override
	public default void updateNoteContentById(final Long id, final String[] newContent, final UserDetailsImpl user)
			throws NullPointerException, NoteNotFoundException, InvalidUserDetailsException, UnauthorizedUserException {
		if (id == null || newContent == null || user == null)
			throw new NullPointerException();
		if (!user.isValid())
			throw new InvalidUserDetailsException();
		Optional<Note> result = this.findNoteById(id);
		if (result.isEmpty())
			throw new NoteNotFoundException();
		if(!result.get().getEmail().equals(user.getUsername()))
			throw new UnauthorizedUserException();
		Note note = result.get();
		note.setContent(newContent);
		this.saveAndFlush(note);
	}

	//Delete
	public default void deleteNoteById(final Long id, final UserDetailsImpl user)
		throws NullPointerException, NoteNotFoundException, InvalidUserDetailsException, UnauthorizedUserException {
		if (id == null || user == null)
			throw new NullPointerException();
		if(!user.isValid())
			throw new InvalidUserDetailsException();
		Optional<Note> result = this.findNoteById(id);
		if(result.isEmpty())
			throw new NoteNotFoundException();
		if(!result.get().getEmail().equals(user.getUsername()))
			throw new UnauthorizedUserException();
		this.deleteNoteByEmailAndId(user.getUsername(), id);
	}
}
