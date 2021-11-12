package in.budgettracker.ums.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import in.budgettracker.ums.exception.UserManagementException;

@RestControllerAdvice
public class GlobalExceptionHandlingAdvice {

	@ExceptionHandler(UserManagementException.class)
	public ResponseEntity<String> handleUserManagementException(UserManagementException exp){
		return new ResponseEntity<>(exp.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleUnhandledCheckedException(Exception exp){
		return new ResponseEntity<>(exp.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
