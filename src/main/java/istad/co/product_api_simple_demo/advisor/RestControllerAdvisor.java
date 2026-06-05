package istad.co.product_api_simple_demo.advisor;

import istad.co.product_api_simple_demo.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class RestControllerAdvisor {

    //ExceptionHandler(NoSuchElementException.class)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse<?>> handleNoSuchMethodException(NoSuchElementException exception){

        return new ResponseEntity<>(
                ErrorResponse.builder()
                        .message(exception.getMessage())
                        .status(HttpStatus.NOT_FOUND.value())
                        .timeStamp(LocalDateTime.now())
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }



   @ExceptionHandler(MethodArgumentNotValidException.class)
   public ResponseEntity<ErrorResponse<?>> handleMethodNotValidException(MethodArgumentNotValidException exception) {

       Map<String, String> errors = new HashMap<>();
       exception.getBindingResult().getFieldErrors().forEach(
               error -> errors.put(error.getField(), error.getDefaultMessage())
       );

       return new ResponseEntity<>(
               ErrorResponse.builder()
                       .message("Provided data is invalid")
                       .status(HttpStatus.BAD_REQUEST.value())
                       .errors(errors)
                       .timeStamp(LocalDateTime.now())
                       .build(), HttpStatus.BAD_REQUEST
       );
   }

}
