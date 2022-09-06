package africa.semicolon.diary.exceptions;

import africa.semicolon.diary.exceptions.user.UserNotFoundException;
import africa.semicolon.diary.dtos.response.ErrorResponse;
import africa.semicolon.diary.exceptions.userProfile.UserProfileNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import javax.servlet.http.HttpServletRequest;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> userNotFoundHandler(UserNotFoundException exception,
                                                             HttpServletRequest request){
        ErrorResponse error = new ErrorResponse(
                ZonedDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                request.getRequestURI(),
                exception.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> userProfileNotFoundHandler(UserProfileNotFoundException exception,
                                                                    HttpServletRequest request){
        ErrorResponse error = new ErrorResponse(
                ZonedDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                request.getRequestURI(),
                exception.getMessage());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}
