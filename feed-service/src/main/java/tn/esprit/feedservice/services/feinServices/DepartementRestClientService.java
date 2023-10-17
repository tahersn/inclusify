package tn.esprit.feedservice.services.feinServices;

import org.springframework.cloud.openfeign.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author Jozef
 */
@FeignClient(name = "university-service",path = "/kaddem/dep")
public interface DepartementRestClientService {
//    @GetMapping("/get/{idDep}")
//    public Departement findById(@PathVariable Integer idDep);
//
//    @GetMapping("/getByNom/{nomDep}")
//    public Departement findByNomDep(@PathVariable String nomDep);
//
//    @GetMapping("/all")
//    public List<Departement> findAll();
//
//    @GetMapping("/getEtudByNom/{nomDep}")
//    public List<Etudiant> findEudDep(@PathVariable String nomDep);
//
//    @PostMapping("/add")
//    public Departement addDepart(@RequestBody Departement dep);
}
