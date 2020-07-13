package com.smithmicro.notesapp.dao;

import java.util.List;

import com.smithmicro.notesapp.entity.Notes;

public interface NotesDAO {

	public List<Notes> getNotes(String user);

	public void saveNote(Notes theNote);

	public void deleteNote(int theId);
	
	public Notes getNote(int theNoteId);
}
