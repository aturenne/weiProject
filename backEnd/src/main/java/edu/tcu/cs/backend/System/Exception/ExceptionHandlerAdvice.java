package edu.tcu.cs.backend.System.Exception;

import edu.tcu.cs.backend.System.Result;
import edu.tcu.cs.backend.System.StatusCode;
import edu.tcu.cs.backend.UserDto.crewMemberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(crewMemberNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Result handleCrewMemberNotFoundException(crewMemberNotFoundException e) {
        return new Result(false, StatusCode.NOT_FOUND, e.getMessage());
    }
}
