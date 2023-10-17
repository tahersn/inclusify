package tn.esprit.feedservice.services.Exceptions;

/**
 * @author Jozef
 */

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@ResponseStatus(HttpStatus.NOT_FOUND)
public class MyFileNotFoundException extends RuntimeException {
    public MyFileNotFoundException(String message) {
        super(message);
    }

    public MyFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
