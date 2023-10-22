package tn.esprit.feedservice.feign;

import org.springframework.cloud.openfeign.*;
import org.springframework.web.bind.annotation.*;
import tn.esprit.feedservice.model.*;

import java.util.*;

/**
 * @author Jozef
 */
@FeignClient(name = "nodejs-service", path = "/")
public interface UserRestFeignClientService {

    @GetMapping("/users")
    public List<User> findAll();

    @GetMapping("/users/byId/{idUser}")
    public User findById(@PathVariable String idUser);
}
