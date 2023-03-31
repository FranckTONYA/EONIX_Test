package com.gestion.controllers;

import com.gestion.models.Personne;
import com.gestion.services.PersonneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


    public PersonneController() {
        this.logger = LoggerFactory.getLogger(this.getClass());
    }

    /**
     * Save or update Person
     * @param personne
     * @return
     */
    @PostMapping(value = "save")
    public ResponseEntity<?> savePerson(@RequestBody Personne personne){
        logger.debug("Call : Save or update Person");

        return ResponseEntity.ok(personneService.savePerson(personne));
    }


    /**
     * Find person by ID
     * @param id
     * @return
     */
    @GetMapping(value = "find-by-id/{id}")
    public ResponseEntity<?> findPersonById(@PathVariable Long id){
        logger.debug("Call : Find Personne by id");
        Optional<Personne> result = personneService.getPersonneRepository().findById(id);
        return result.map(ResponseEntity::ok).orElse(null);
    }

    /**
     * Find all persons
     */
    @GetMapping(value = "find-all")
    public ResponseEntity<?> findAllPersons(){
        logger.debug("Call : Find all persons");
        return ResponseEntity.ok(personneService
                .getPersonneRepository().findAll());
    }


    /**
     * Delete person
     * @param id
     * @return True if ID deleted
     */
    @DeleteMapping("delete-by-id/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Long id){
        logger.debug("Call : Delete Person");
        personneService.getPersonneRepository().deleteById(id);
        return ResponseEntity.ok(true);
    }

    /**
     * Search person by firstname or name
     * @param keyword
     */
    @GetMapping("search/{keyword}")
    public ResponseEntity<?> SearchByKeyword(@PathVariable String keyword){
        logger.debug("Call : Search Person by keyword");
        return ResponseEntity.ok( personneService.search(keyword));
    }
}
