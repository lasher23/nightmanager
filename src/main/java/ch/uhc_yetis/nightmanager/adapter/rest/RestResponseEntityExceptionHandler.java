package ch.uhc_yetis.nightmanager.adapter.rest;

import ch.uhc_yetis.nightmanager.application.generation.GenerationException;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<HttpErrorObject> handleConflict(RuntimeException ex, WebRequest request) {
        LoggerFactory.getLogger(this.getClass()).trace(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(HttpErrorObject.withTimestamp(LocalDateTime.now()).withPath(request.getContextPath()).withMessage(ex.getMessage()).withError("unexpected error").build());
    }

    @ExceptionHandler(value = {GenerationException.class})
    protected ResponseEntity<HttpErrorObject> handleGenerationException(RuntimeException ex, WebRequest request) {
        LoggerFactory.getLogger(this.getClass()).trace(ex.getMessage(), ex);
        GenerationException generationException = (GenerationException) ex;
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(HttpErrorObject.withTimestamp(LocalDateTime.now()).withPath(request.getContextPath()).withMessage(generationException.getMessage()).withError("Generierungs Fehler").build());
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    protected ResponseEntity<HttpErrorObject> handleAccessDeniedException(RuntimeException ex, WebRequest request) {
        LoggerFactory.getLogger(this.getClass()).trace(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(HttpErrorObject.withTimestamp(LocalDateTime.now()).withPath(request.getContextPath()).withMessage(ex.getMessage()).withError("Zugriff verweigert").build());
    }

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    protected ResponseEntity<HttpErrorObject> handleUsernameNotFoundException(RuntimeException ex, WebRequest request) {
        LoggerFactory.getLogger(this.getClass()).trace(ex.getMessage(), ex);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(HttpErrorObject.withTimestamp(LocalDateTime.now()).withPath(request.getContextPath()).withMessage(ex.getMessage()).withError("Invalide Login Daten").build());
    }
}
