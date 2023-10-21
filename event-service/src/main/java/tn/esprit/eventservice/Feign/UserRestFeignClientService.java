package tn.esprit.eventservice.Feign;

import org.springframework.cloud.openfeign.*;
import org.springframework.web.bind.annotation.*;
import tn.esprit.eventservice.Model.*;

import java.util.*;

/**
 * @author oumaima1115
 */
@FeignClient(name = "nodejs-service", path = "/")
public interface UserRestFeignClientService {

    @GetMapping("/users")
    public List<User> findAll();

    @GetMapping("/users/{idUser}")
    public User findById(@PathVariable String idUser);

}
