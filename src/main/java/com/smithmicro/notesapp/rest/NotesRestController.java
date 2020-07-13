package com.smithmicro.notesapp.rest;


import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smithmicro.notesapp.entity.MyUserPrincipal;
import com.smithmicro.notesapp.entity.Notes;
import com.smithmicro.notesapp.entity.User;
import com.smithmicro.notesapp.service.LoginService;
import com.smithmicro.notesapp.service.NotesService;
//import org.springframework.security.core.userdetails.User;

@RestController
@RequestMapping("/api")
public class NotesRestController {
	
   @Autowired
   private NotesService notesService;
   
   
   
   @Autowired
   private LoginService loginService;
   
   private Logger logger = Logger.getLogger(getClass().getName());
   
   @GetMapping("/notes/{user}")
   public List<Notes> getNotes(@PathVariable String user){
	   logger.info("Entering in get User");
	   MyUserPrincipal currentuser = (MyUserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	  // MyUserPrincipal myUserPrincipal=new MyUserPrincipal(currentuser);
	   logger.info("username"+currentuser.getUsername());
	   List<Notes> notesPerUser=notesService.getNotes(user);
	    
	   if(notesPerUser == null || notesPerUser.isEmpty()) {
			throw new NotesUserNotFoundException(" There are no notes.Create one ");
		}
	  
	   return notesPerUser;
	  
   }
   
   @PostMapping("/notes")
   public Notes addNote(@RequestBody Notes theNote) {
	   theNote.setNoteId(0);//force for save operation of new item just in case any id is passed in JSON SEND
	  
	   notesService.saveNote(theNote);
	   
	   return theNote;
	   
   }
   
  @PostMapping("/login")
   public String addUser(@RequestBody User theUser) {
	   //force for save operation of new item just in case any id is passed in JSON SEND
	  
	   loginService.saveUser(theUser);
	   
	   if(theUser.getIsUserLogged()==1) {
		   return theUser.getUserName().toString()+".. Congrats you have successfully logged in.And you are authorized to use your noteapp";
	   }else {
		   return theUser.getUserName().toString()+".. You have successfully logged out";
	   }
	   
   }
   
   @PutMapping("/notes")
   public Notes updateNote(@RequestBody Notes theNote) {
	  
	   notesService.saveNote(theNote);
	   
	   return theNote;
	   
   }
   
   @DeleteMapping("/notes/{noteid}")
   public String deleteNote(@PathVariable int noteid) {
	   
	   Notes tempNote=notesService.getNote(noteid);
	   
	   if(tempNote==null) {
		   throw new NotesUserNotFoundException(" Note not found for deletion "+noteid);
	   }
	   
	   notesService.deleteNote(noteid);
	   
	   return "Deleted note id "+noteid;
	   
   }
}
