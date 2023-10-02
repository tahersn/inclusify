package tn.esprit.skillservice.Skill;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/skill")
public class SkillRestAPI {

    @Autowired
    private SkillService skillService;

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Skill> getSkill(@PathVariable(value = "id") int id) {
        return new ResponseEntity<>(skillService.getSkill(id), HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Skill> createSkill(@RequestBody Skill skill) {
        return new ResponseEntity<>(skillService.addSkill(skill), HttpStatus.OK);
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
