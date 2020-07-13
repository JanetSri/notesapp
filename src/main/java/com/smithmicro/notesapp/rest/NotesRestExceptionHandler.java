package com.smithmicro.notesapp.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class NotesRestExceptionHandler {
//Add an exception handler for NotesUserNotFound exception
	
   @ExceptionHandler
   public ResponseEntity<NoteUserErrorResponse> handleException(NotesUserNotFoundException exc){
	   NoteUserErrorResponse error=new NoteUserErrorResponse(HttpStatus.NOT_FOUND.value(),
			                                                 exc.getMessage(),
			                                                 System.currentTimeMillis());
	   
	   return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
   }
   
 //Add an exception handler o catch all exceptions  
   
   @ExceptionHandler
   public ResponseEntity<NoteUserErrorResponse> handleException(Exception exc){
	   NoteUserErrorResponse error=new NoteUserErrorResponse(HttpStatus.BAD_REQUEST.value(),
			                                                 exc.getMessage(),
			                                                 System.currentTimeMillis());
	   
	   return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
   }
   
	

}
