package vn.edu.hcmut.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import vn.edu.hcmut.exception.DataNotFoundException;
import vn.edu.hcmut.exception.InvalidTokenException;
import vn.edu.hcmut.exception.RefreshTokenExpiredException;
import vn.edu.hcmut.response.ErrorResponse;

import java.nio.file.AccessDeniedException;


@RestControllerAdvice
public class GlobalExceptionHandler {
    static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> pathNotFound(NoHandlerFoundException ex) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ErrorResponse response = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(response, notFound);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> accessDenied(AccessDeniedException ex) {
        HttpStatus forbidden = HttpStatus.FORBIDDEN;
        ErrorResponse response = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(response, forbidden);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exception(Exception ex) {
        logger.debug("", ex);
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorResponse response = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(response, httpStatus);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponse> exception(InvalidTokenException ex) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErrorResponse response = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(response, httpStatus);
    }

    @ExceptionHandler(RefreshTokenExpiredException.class)
    public ResponseEntity<ErrorResponse> exception(RefreshTokenExpiredException ex) {
        HttpStatus httpStatus = HttpStatus.FORBIDDEN;
        ErrorResponse response = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(response, httpStatus);
    }

    @ExceptionHandler({DataNotFoundException.class})
    public ResponseEntity<ErrorResponse> exception(DataNotFoundException ex) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErrorResponse response = new ErrorResponse(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(response, httpStatus);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> exception(IllegalArgumentException ex) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        ErrorResponse response = new ErrorResponse("Invalid request");
        return new ResponseEntity<ErrorResponse>(response, httpStatus);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> exception(MethodArgumentNotValidException ex) {
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        var fieldError = ex.getBindingResult().getFieldError();
        ErrorResponse response = new ErrorResponse(fieldError.getField() + " " + fieldError.getDefaultMessage());
        return new ResponseEntity<>(response, httpStatus);
    }
}
