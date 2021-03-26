/**
 * 
 */
package com.mazhar.gateway.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author mazhar
 *
 * Dec 12, 2020
 */
@ResponseStatus(value = HttpStatus.EXPECTATION_FAILED)
public class ValidationFailure extends RuntimeException {

	public ValidationFailure(String message){
    	super(message);
    }
}
