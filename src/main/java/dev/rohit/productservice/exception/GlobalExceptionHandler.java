package dev.rohit.productservice.exception;

import dev.rohit.productservice.dtos.ExceptionDTO;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionDTO> handleException(NotFoundException notFoundException) {
        return new ResponseEntity<>(new ExceptionDTO(HttpStatus.NOT_FOUND,notFoundException.getMessage()),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleTypeMismatch(HttpMessageNotReadableException ex) {
        return new ResponseEntity<>("Invalid data type provided", HttpStatus.BAD_REQUEST);
    }
}
