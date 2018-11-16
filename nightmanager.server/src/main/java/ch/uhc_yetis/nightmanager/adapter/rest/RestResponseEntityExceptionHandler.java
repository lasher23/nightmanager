package ch.uhc_yetis.nightmanager.adapter.rest;

import ch.uhc_yetis.nightmanager.application.generation.GenerationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<HttpErrorObject> handleConflict(RuntimeException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(HttpErrorObject.withTimestamp(LocalDateTime.now()).withPath(request.getContextPath()).withMessage(ex.getMessage()).withError("unexpected error").build());
    }

    @ExceptionHandler(value = {GenerationException.class})
    protected ResponseEntity<HttpErrorObject> handleGenerationException(RuntimeException ex, WebRequest request) {
        GenerationException generationException = (GenerationException) ex;
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(HttpErrorObject.withTimestamp(LocalDateTime.now()).withPath(request.getContextPath()).withMessage(generationException.getMessage()).withError("Generierungs Fehler").build());
    }
}
