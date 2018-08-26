package ch.uhc_yetis.nightmanager.adapter.rest;

import ch.uhc_yetis.nightmanager.application.CustomException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {CustomException.class})
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        System.out.print("we did it");
        CustomException customException = (CustomException) ex;
        customException.setPath(request.getContextPath());
        customException.setTimestamp(System.currentTimeMillis() + "");
        return ResponseEntity.status(customException.getStatus().getHttpCode()).body(customException);
    }
}
