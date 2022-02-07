package com.template.spring.core.exceptions;

import com.template.spring.core.exceptions.custom.BaseException;
import com.template.spring.core.exceptions.custom.UnauthorizedException;
import com.template.spring.core.exceptions.custom.ValidationException;
import com.template.spring.core.exceptions.custom.ValidatorException;
import com.template.spring.core.helpers.Messages;
import com.template.spring.core.validator.GenericValidationResult;
import com.template.spring.core.validator.utils.ValidationField;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.CannotAcquireLockException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleApiRequestException(Exception ex) {

        logger.error(ex.getMessage(), ex);

        return new ResponseEntity<>(
                new ApiException(HttpStatus.INTERNAL_SERVER_ERROR, Messages.get("error.standard"), Messages.get("title.error.standard"), ex),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(value = {BaseException.class})
    protected ResponseEntity<Object> handleApiRequestException(BaseException ex) {

        logger.error(ex.getMessage(), ex);

        return new ResponseEntity<>(
                new ApiException(HttpStatus.NOT_IMPLEMENTED, ex.getUserMessage(), ex.getMessageTitle(), ex),
                HttpStatus.NOT_IMPLEMENTED
        );
    }

    @ExceptionHandler(value = {ValidationException.class})
    protected ResponseEntity<Object> handleApiRequestException(ValidationException ex) {

        logger.error(ex.getMessage(), ex);

        return new ResponseEntity<>(
                new ApiException(HttpStatus.UNPROCESSABLE_ENTITY, ex.getUserMessage(), ex.getMessageTitle(), ex, ex.getFieldErrors()),
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }

    @ExceptionHandler(value = {ValidatorException.class})
    protected ResponseEntity<Object> handleApiRequestException(ValidatorException ex) {

        logger.error(ex.getMessage(), ex);

        return new ResponseEntity<>(
                new ApiException(HttpStatus.UNPROCESSABLE_ENTITY, null, ex.getMessageTitle(), ex.getErros().stream().map(GenericValidationResult::getMessage).collect(Collectors.toList()), ex),
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }

    @ExceptionHandler(value = {UnauthorizedException.class})
    protected ResponseEntity<Object> handleApiRequestException(UnauthorizedException ex) {

        logger.error(ex.getMessage(), ex);

        return new ResponseEntity<>(
                new ApiException(HttpStatus.UNAUTHORIZED, ex.getUserMessage(), ex.getMessageTitle(), ex),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    protected ResponseEntity<Object> handleApiRequestException(BadCredentialsException ex) {

        logger.error(ex.getMessage(), ex);

        return new ResponseEntity<>(
                new ApiException(HttpStatus.FORBIDDEN, "User and/or passwords invalids.", "Authentication failure!", ex),
                HttpStatus.FORBIDDEN
        );
    }

    @ExceptionHandler(value = {CannotAcquireLockException.class})
    protected ResponseEntity<Object> handleApiRequestException(CannotAcquireLockException ex) {

        logger.error(ex.getMessage(), ex);

        return new ResponseEntity<>(
                new ApiException(HttpStatus.CONFLICT, "There is currently someone performing the same operation, wait a few moments and try again", ex),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(value = {DataIntegrityViolationException.class})
    protected ResponseEntity<Object> handleApiRequestException(DataIntegrityViolationException ex) {
        Map<String, String> sqlConstraintErrors = Map.of(
                "username_alphanumeric_only", "Only alphanumeric characters can be used for username",
                "un_user_username", "Username must be unique",
                "un_user_email", "E-mail must be unique",
                "valid_email", "User must have a valid E-mail address"
        );

        logger.error(ex.getMessage(), ex);

        String errorMessage = "There is a violation while saving the entity";
        if (ex.getRootCause() instanceof ConstraintViolationException) {
            errorMessage = sqlConstraintErrors.getOrDefault(((ConstraintViolationException) ex.getCause()).getConstraintName(), errorMessage);
        }

        return new ResponseEntity<>(
                new ApiException(HttpStatus.UNPROCESSABLE_ENTITY, errorMessage, "Action failed!", ex),
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    protected ResponseEntity<Object> handleApiRequestException(EntityNotFoundException ex) {

        logger.error(ex.getMessage(), ex);

        return new ResponseEntity<>(
                new ApiException(HttpStatus.NOT_FOUND, ex.getMessage(), "Entity not found!", ex),
                HttpStatus.NOT_FOUND
        );
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        logger.error(ex.getMessage(), ex);

        return new ResponseEntity<>(
                new ApiException(HttpStatus.BAD_REQUEST, "The request could not be understood by the server due to malformed syntax. The client should not repeat the request without modifications.", "Bad Request", ex),
                HttpStatus.BAD_REQUEST
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<ValidationField> validationFields = new ArrayList<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        fieldErrors.forEach(fieldError -> validationFields.add(new ValidationField(fieldError.getDefaultMessage(), fieldError.getField(), fieldError.getRejectedValue())));


        return new ResponseEntity<>(new ApiException(HttpStatus.BAD_REQUEST, "Validate the fields and try again.", "Fields invalid!", ex, validationFields),
                HttpStatus.BAD_REQUEST);
    }


}
