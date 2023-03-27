package com.gestion.services;

import com.gestion.models.Personne;
import com.gestion.repositories.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service()
public class PersonneService {

    @Autowired
    private PersonneRepository personneRepository;

    public PersonneRepository getPersonneRepository() {
        return personneRepository;
    }

    public Personne savePerson(Personne personne){
        if (personne.getNom().isEmpty() || personne.getPrenom().isEmpty()){

        }
        return personneRepository.save(personne);
    }

    public Personne updatePerson(Personne personne){
        if (personne.getNom().isEmpty() || personne.getPrenom().isEmpty()){

        }
        Personne personFound = personneRepository
                .findById(personne.getId()).get();

        personFound.setNom(personne.getNom());
        personFound.setPrenom(personne.getPrenom());

        return personneRepository.save(personFound);
    }

}
