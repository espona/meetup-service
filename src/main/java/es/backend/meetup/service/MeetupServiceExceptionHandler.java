package es.backend.meetup.service;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import es.backend.meetup.dto.ErrorDTO;

/**
* MeetupServiceExceptionHandler defines the handling
* of errors during requests. An {@link es.backend.meetup.dto.ErrorDTO} is always returned
* 
* @author Lucia de Espona
*
**/
@ControllerAdvice
public class MeetupServiceExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	protected ResponseEntity<Object> handleMissingServletRequestParameter(
	  MissingServletRequestParameterException ex, HttpHeaders headers, 
	  HttpStatus status, WebRequest request) {
	    String detail = ex.getParameterName() + " parameter is missing";
	     
	    ErrorDTO apiError = 
	      new ErrorDTO(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage() + ": " + detail);
	    return new ResponseEntity<Object>(
	      apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	@ExceptionHandler({ MethodArgumentTypeMismatchException.class })
	public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
	  MethodArgumentTypeMismatchException ex, WebRequest request) {
	    String detail = 
	      ex.getName() + " should be of type " + ex.getRequiredType().getName();
	 
	    ErrorDTO apiError = 
	  	      new ErrorDTO(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage() + ": " + detail);

	    return new ResponseEntity<Object>(
	      apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
	  MethodArgumentNotValidException ex, 
	  HttpHeaders headers, 
	  HttpStatus status, 
	  WebRequest request) {
	    String errors = "";
	    for (FieldError error : ex.getBindingResult().getFieldErrors()) {
	        errors += " " + error.getField() + ": " + error.getDefaultMessage();
	    }
	    for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
	        errors += " " + error.getObjectName() + ": " + error.getDefaultMessage();
	    }
	     
	    ErrorDTO apiError = 
		  	      new ErrorDTO(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage() + ": " + errors);

	    return handleExceptionInternal(
	      ex, apiError, headers, apiError.getStatus(), request);
	}
	
	@Override
	protected ResponseEntity<Object> handleNoHandlerFoundException(
	  NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
	    String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();
	 
	    ErrorDTO apiError = 
		  	      new ErrorDTO(HttpStatus.NOT_FOUND, ex.getLocalizedMessage() + ": " + error);

	    return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
	  HttpRequestMethodNotSupportedException ex, 
	  HttpHeaders headers, 
	  HttpStatus status, 
	  WebRequest request) {
	    StringBuilder builder = new StringBuilder();
	    builder.append(ex.getMethod());
	    builder.append(
	      " method is not supported for this request. Supported methods are ");
	    ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
	
	    ErrorDTO apiError = 
		  	      new ErrorDTO(HttpStatus.METHOD_NOT_ALLOWED, ex.getLocalizedMessage() + ": " + builder.toString());

	    return new ResponseEntity<Object>(
	      apiError, new HttpHeaders(), apiError.getStatus());
	}
	
	//TODO: Override further exception handling methods
}
