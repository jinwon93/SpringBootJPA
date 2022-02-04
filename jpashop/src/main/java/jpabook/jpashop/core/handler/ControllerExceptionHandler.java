package jpabook.jpashop.core.handler;


import jpabook.jpashop.core.handler.ex.CustomApiException;
import jpabook.jpashop.core.handler.ex.CustomVaildationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ControllerExceptionHandler {


    public String validationException(CustomVaildationException e){
        if (e.getErrorMap() == null){
            return null;
        }
        return null;
    }


    @ExceptionHandler(CustomVaildationException.class)
    public ResponseEntity<?> validationApiException(CustomVaildationException e){
        return new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomApiException.class)
    public ResponseEntity<?> apiException(CustomVaildationException e){
        return  new ResponseEntity<>(e.getMessage() , HttpStatus.BAD_REQUEST);
    }
}
