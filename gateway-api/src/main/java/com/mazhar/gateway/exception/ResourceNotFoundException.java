/**
 * 
 */
package com.mazhar.gateway.exception;

/**
 * @author mazhar
 *
 * Dec 12, 2020
 */
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
public class ResourceNotFoundException extends Exception{

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message){
    	super(message);
    }
}
