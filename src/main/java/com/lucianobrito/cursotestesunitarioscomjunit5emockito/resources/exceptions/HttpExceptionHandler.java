package com.lucianobrito.cursotestesunitarioscomjunit5emockito.resources.exceptions;

import com.lucianobrito.cursotestesunitarioscomjunit5emockito.services.exceptions.ResourceAlreadyException;
import com.lucianobrito.cursotestesunitarioscomjunit5emockito.services.exceptions.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@AllArgsConstructor
public class HttpExceptionHandler extends ResponseEntityExceptionHandler {

    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        List<StandardError.Field> fields = new ArrayList<>();

        for (ObjectError error : e.getBindingResult().getAllErrors()) {
            String field = ((FieldError) error).getField();
            String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());

            fields.add(new StandardError.Field(field, message));
        }

        String titleError = messageSource.getMessage("FieldErrors", null, LocaleContextHolder.getLocale());
        StandardError err = new StandardError(OffsetDateTime.now(),
                status.value(), ((ServletWebRequest) request).getRequest().getRequestURI(), titleError, null, fields, e.getCause());
        return handleExceptionInternal(e, err, headers, status, request);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, WebRequest request) {

        String titleError = messageSource.getMessage("ResourceNotFound", null, LocaleContextHolder.getLocale());
        HttpStatus statusCode = HttpStatus.NOT_FOUND;
        StandardError err = this.getGenericError(e, titleError, statusCode, request);
        return ResponseEntity.status(statusCode).body(err);
    }

    @ExceptionHandler(ResourceAlreadyException.class)
    public ResponseEntity<StandardError> resourceAlreadyError(ResourceAlreadyException e, WebRequest request) {

        String titleError = messageSource.getMessage("ResourceAlreadyError", null, LocaleContextHolder.getLocale());
        HttpStatus statusCode = HttpStatus.BAD_REQUEST;
        StandardError err = this.getGenericError(e, titleError, statusCode, request);
        return ResponseEntity.status(statusCode).body(err);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> internalServerError(Exception e, WebRequest request) {

        String titleError = messageSource.getMessage("InternalServerError", null, LocaleContextHolder.getLocale());
        HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        StandardError err = this.getGenericError(e, titleError, statusCode, request);
        return ResponseEntity.status(statusCode).body(err);
    }

    private StandardError getGenericError(Exception e, String titleError, HttpStatus statusCode, WebRequest request) {
        String URI = ((ServletWebRequest) request).getRequest().getRequestURI();
        return new StandardError(OffsetDateTime.now(), statusCode.value(), URI, titleError, e.getMessage(), null, e.getCause());
    }

}