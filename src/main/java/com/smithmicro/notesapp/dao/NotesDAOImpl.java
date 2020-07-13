package com.smithmicro.notesapp.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.smithmicro.notesapp.entity.Notes;

@Repository
public class NotesDAOImpl implements NotesDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	
			
	@Override
	public List<Notes> getNotes(String user) {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query  ... sort by last name
		Query<Notes> theQuery = 
				currentSession.createQuery("from Notes where userName=:id",
											Notes.class);
		theQuery.setParameter("id", user);
		
		
		
		// execute query and get result list
		List<Notes> notesList = theQuery.getResultList();
				
		// return the results		
		return notesList;
	}

	@Override
	public void saveNote(Notes theNote) {
		
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();			
		
		if(theNote.getNoteId()!=0) {
			//appending the newnote in DataBase
			Notes note=currentSession.get(Notes.class,theNote.getNoteId());
			String noteContent=note.getNoteContent();
			StringBuffer sb=new StringBuffer(noteContent);
			String newNoteContent=theNote.getNoteContent();
			sb.append(" ");
			sb.append(newNoteContent);
			theNote.setNoteContent(sb.toString());
			currentSession.merge(theNote);
		}else {
		currentSession.saveOrUpdate(theNote);
		}
	}

	

	@Override
	public void deleteNote(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query<?> theQuery = 
				currentSession.createQuery("delete from Notes where noteId=:id");
		theQuery.setParameter("id", theId);
		
		
		theQuery.executeUpdate();		
	}
	
	@Override
	public Notes getNote(int theNoteId) {
		Session currentSession = sessionFactory.getCurrentSession();
		
		Notes note=currentSession.get(Notes.class,theNoteId);
		
		return note;
		
	}
	

}











