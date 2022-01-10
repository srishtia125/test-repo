package com.travix.medusa.busyflights.busyflights.controller;

import com.travix.medusa.busyflights.busyflights.exceptions.ErrorData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@ControllerAdvice
public class GlobalExceptionHandlerAdvice {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<List<ErrorData>> handleInvalidInputs(final ConstraintViolationException exception) {
        List<ErrorData> errorDataList = null;
        Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
        if (null != constraintViolations && !constraintViolations.isEmpty()) {
            errorDataList = new ArrayList<>(constraintViolations.size());
            for (ConstraintViolation constraintViolation : constraintViolations) {
                ErrorData errorData = new ErrorData();
                errorData.setField(constraintViolation.getPropertyPath().toString());
                errorData.setErrorMessage(constraintViolation.getMessage());
                errorDataList.add(errorData);
            }
            return new ResponseEntity(errorDataList, HttpStatus.BAD_REQUEST);
        }
    return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
}
}
