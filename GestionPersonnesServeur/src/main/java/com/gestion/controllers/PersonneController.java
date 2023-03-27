package com.gestion.controllers;

import com.gestion.models.Personne;
import com.gestion.services.PersonneService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "personne/")
public class PersonneController {
    protected Logger logger;

    @Autowired
    private PersonneService personneService;


    /**
     * Save Transaction
     * @param personne
     * @return
     */
    @PostMapping(value = "save")
    public ResponseEntity<?> savePerson(@RequestBody Personne personne){
        logger.debug("Call : Save Person");

        return ResponseEntity.ok(personneService.savePerson(personne));
    }


    /**
     * Update Personne
     * @param personne
     * @return
     */
    @PostMapping(value = "update")
    public ResponseEntity<?> updatePerson(@RequestBody Personne personne){
        logger.debug("Call : Update person");

        return ResponseEntity.ok(personneService
                .updatePerson(personne));
    }

    @GetMapping(value = "find-by-id/{id}")
    public ResponseEntity<?> findPersonById(@PathVariable Long id){
        logger.debug("Call : Find Personne by id");
        Optional<Personne> result = personneService.getPersonneRepository().findById(id);
        return result.map(ResponseEntity::ok).orElse(null);
    }


    /**
     * @param id A Category ID
     * @return True if Category deleted
     */
    @DeleteMapping("delete-by-id/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id){
        logger.debug("Call : Delete Person");
        personneService.getPersonneRepository().deleteById(id);
        return ResponseEntity.ok(true);
    }
}
