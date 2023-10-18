package tn.esprit.jobservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import tn.esprit.jobservice.model.User;

import java.util.List;

@FeignClient(name = "nodejs-service")
public interface UserRestService {

    @GetMapping("/users")
    public List<User> findAll();

    @GetMapping("/users/{idUser}")
    public User findById(String idUser);


}
