package com.smithmicro.notesapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smithmicro.notesapp.dao.NotesDAO;
import com.smithmicro.notesapp.entity.Notes;
import com.smithmicro.notesapp.rest.NotesUserNotFoundException;

@Service
public class NotesServiceImpl implements NotesService {

	// need to inject notes dao
	@Autowired
	private NotesDAO notesDAO;
	
	@Override
	@Transactional
	public List<Notes> getNotes(String user) {
		
	return notesDAO.getNotes(user);
	}

	@Override
	@Transactional
	public void saveNote(Notes theNote) {
        //updating the same note
		
		notesDAO.saveNote(theNote);
	}

	

	@Override
	@Transactional
	public void deleteNote(int theId) {
		
		notesDAO.deleteNote(theId);
	}
	
	@Override
	@Transactional
	public Notes getNote(int theNoteId) {
		return notesDAO.getNote(theNoteId);
	}
}





