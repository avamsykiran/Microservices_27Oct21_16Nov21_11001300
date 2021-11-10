package in.budgettracker.tms.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import in.budgettracker.tms.exception.TxnManagementException;

@RestControllerAdvice
public class GlobalExceptionHandlingAdvice {

	@ExceptionHandler(TxnManagementException.class)
	public ResponseEntity<String> handleUserManagementException(TxnManagementException exp){
		return new ResponseEntity<>(exp.getMessage(),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleUnhandledCheckedException(Exception exp){
		exp.printStackTrace();
		return new ResponseEntity<>(exp.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
