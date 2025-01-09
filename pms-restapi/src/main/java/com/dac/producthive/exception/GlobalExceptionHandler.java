 package com.dac.producthive.exception;

 import org.springframework.http.HttpStatus;
 import org.springframework.http.ResponseEntity;
 import org.springframework.web.bind.annotation.ControllerAdvice;
 import org.springframework.web.bind.annotation.ExceptionHandler;
 import org.springframework.web.bind.annotation.ResponseStatus;

 /**
  * Author : rajgs
  * Date   : 1 Jan 2025
  * Time   : 12:20:08 pm
 */

 /*
  * Global exception Handler class to manage Custom Exceptions.
  * This approach allows you to remove the exception handling 
  * logic from your controller method.
  * 
  * The @ControllerAdvice annotation makes this class a global exception handler.
  * The @ExceptionHandler annotation specifies that the handleResourceNotFoundException method 
  * should be invoked when a ResourceNotFoundException is thrown.
  * The method returns a ResponseEntity with an appropriate status code (404 Not Found).
  */

 @ControllerAdvice
 public class GlobalExceptionHandler {
 	
 	@ExceptionHandler(ResourceNotFoundException.class)
     @ResponseStatus(HttpStatus.NOT_FOUND)
     public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
         return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
     }


 	// You can add more exception handlers for other exceptions here
 }
