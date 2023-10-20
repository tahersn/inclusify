package tn.esprit.gatewayservice;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.*;
import java.security.*;

/**
 * @author Jozef
 */
@Controller
public class WebController {

    @GetMapping()
    public String index(Principal principal){
        return principal.getName();
    }
}
