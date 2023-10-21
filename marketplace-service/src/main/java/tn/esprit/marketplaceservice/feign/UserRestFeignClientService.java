package tn.esprit.marketplaceservice.feign;

import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.openfeign.*;
import tn.esprit.marketplaceservice.model.User;
import java.util.List;

@FeignClient(name = "nodejs-service", path = "/")
public interface UserRestFeignClientService {

    @GetMapping("/users")
    public List<User> findAll();

    @GetMapping("/users/{idUser}")
    public User findById(@PathVariable String idUser);
}