package com.juliandbs.savemynotes.persistence.repositories;

import com.juliandbs.savemynotes.persistence.models.Note;
import com.juliandbs.savemynotes.persistence.exceptions.NoteAlreadyExistsException;
import com.juliandbs.savemynotes.persistence.exceptions.NoteNotFoundException;
import com.juliandbs.savemynotes.persistence.exceptions.InvalidUserDetailsException;
import com.juliandbs.savemynotes.persistence.exceptions.UnauthorizedUserException;
import com.juliandbs.savemynotes.security.userdetails.UserDetailsImpl;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;
import java.lang.NullPointerException;

@NoRepositoryBean
public interface CustomizedNoteRepository {

	//Create
	public void addNewNote(Note note) throws NullPointerException, NoteAlreadyExistsException;

	//Read
	@Query(nativeQuery = true, value = "SELECT * FROM notes WHERE notes.id = :id")
	public Optional<Note> findNoteById(@Param("id") Long id);

	@Query(nativeQuery = true, value = "SELECT * FROM notes WHERE notes.email = :email AND notes.code = :code")
	public Optional<Note> findNoteByEmailAndCode(@Param("email") String email, @Param("code") String code);

	@Query(nativeQuery = true, value = "SELECT * FROM notes WHERE notes.email = :email")
	public List<Note> findNotesByEmail(@Param("email") String email);

	//Update
	public void updateNoteTitleById(Long id, String newTitle, UserDetailsImpl user) throws NullPointerException, NoteNotFoundException, InvalidUserDetailsException, UnauthorizedUserException;

	public void updateNoteContentById(Long id, String[] newContent, UserDetailsImpl user) throws NullPointerException, NoteNotFoundException, InvalidUserDetailsException, UnauthorizedUserException;

	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE notes SET last_access = current_timestamp WHERE notes.id = :id")
	public void updateNoteLastAccessById(@Param("id") Long id);

	//Delete
	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query(nativeQuery = true, value = "DELETE FROM notes WHERE notes.email = :email AND notes.id = :id")
	public void deleteNoteByEmailAndId(@Param("email") String email, @Param("id") Long id);

	@Modifying(clearAutomatically = true, flushAutomatically = true)
	@Transactional
	@Query(nativeQuery = true, value = "DELETE FROM notes WHERE notes.email = :email")
	public void deleteAllUserNotesByEmail(@Param("email") String email);
}
