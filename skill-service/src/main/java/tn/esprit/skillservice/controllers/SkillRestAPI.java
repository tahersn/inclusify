package tn.esprit.skillservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.skillservice.feign.UserRestFeignClientService;
import tn.esprit.skillservice.models.SkillListItem;
import tn.esprit.skillservice.models.User;
import tn.esprit.skillservice.services.SkillService;
import tn.esprit.skillservice.entities.Skill;

import javax.annotation.security.PermitAll;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/skill")
public class SkillRestAPI {

    @Autowired
    private SkillService skillService;

    @GetMapping(value = {"/list","/list/{userId}"}, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SkillListItem> getSkills(@PathVariable(value = "userId") Optional<String> userId) {
        if (userId.isPresent())
            return skillService.getSkillItems(userId.get());
        else return skillService.getSkills();
    }

    @GetMapping(value = "/byId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Skill> getSkill(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(skillService.getSkill(id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Skill createSkill(@RequestBody Skill skill) {
        return skillService.addSkill(skill);
    }

    @PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Skill> updateSkill(@PathVariable(value = "id") int id,
                                                 @RequestBody Skill skill){
        return new ResponseEntity<>(skillService.updateSkill(id, skill), HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> deleteSkill(@PathVariable(value = "id") int id){
        return new ResponseEntity<>(skillService.deleteSkill(id), HttpStatus.OK);
    }
}
