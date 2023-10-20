package tn.esprit.skillservice.feign;

import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.*;
import tn.esprit.skillservice.models.User;

import java.util.List;

/**
 * @author Fourat
 */
@FeignClient(name = "nodejs-service", path = "/")
public interface UserRestFeignClientService {

    @GetMapping("/users")
    public List<User> findAll();

    @GetMapping("/users/{idUser}")
    public User findById(@PathVariable String idUser);
}
