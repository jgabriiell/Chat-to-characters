package com.jg.talktocharacters.adapters.in.exception;

import com.jg.talktocharacters.domain.exceptions.CharacterNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CharacterNotFoundException.class)
    public ResponseEntity<ApiError> handleDomainException(CharacterNotFoundException domainError) {
        return ResponseEntity.unprocessableEntity().body(new ApiError(domainError.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleDomainException(Exception unexpectedError) {
        String message = "Oh, no! Something went wrong :(";
        logger.error(message, unexpectedError);
        return ResponseEntity.internalServerError().body(new ApiError(message));
    }

    public record ApiError(String message) {}
}
