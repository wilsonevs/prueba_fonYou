package com.wilsonevs.configuracion;

import com.wilsonevs.excepciones.ApiError;
import com.wilsonevs.excepciones.ConflictException;
import com.wilsonevs.excepciones.DatosInvalidosExcepcion;
import com.wilsonevs.excepciones.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@ControllerAdvice(annotations = RestController.class)
public class InterceptorExcepciones {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiError> processSupportedExceptions(Exception ex) {
        return new ResponseEntity<>(ApiError.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .error(ex.getClass().getSimpleName())
                .message(ex.getMessage())
                .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ApiError> conflictoExcepcion(ConflictException ex) {
        return createResponseEntityFromException(ex, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(DatosInvalidosExcepcion.class)
    public ResponseEntity<ApiError> datosInvalidosExcepcion(DatosInvalidosExcepcion ex) {
        return createResponseEntityFromException(ex, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiError> notFoundException(NotFoundException ex) {
        return createResponseEntityFromException(ex, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<ApiError> createResponseEntityFromException(RuntimeException ex, HttpStatus status) {
        Objects.requireNonNull(ex);
        return new ResponseEntity<>(ApiError.builder()
                .status(status.value())
                .error(ex.getClass().getSimpleName())
                .message(ex.getMessage())
                .build(),
                status);
    }

}
