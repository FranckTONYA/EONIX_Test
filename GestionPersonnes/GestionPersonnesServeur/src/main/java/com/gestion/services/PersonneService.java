package com.gestion.services;

import com.gestion.models.Personne;
import com.gestion.repositories.PersonneRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service()
public class PersonneService {

    @Autowired
    private PersonneRepository personneRepository;

    public PersonneRepository getPersonneRepository() {
        return personneRepository;
    }

    /**
     * Save person in DB
     * @param personne
     * @return
     */
    public Personne savePerson(Personne personne){
        if (personne.getNom().isEmpty() || personne.getPrenom().isEmpty()){
            throw new RuntimeException("Name or firstname empty");
        }
        if(personne.getId() != null){
            return updatePerson(personne);
        }
        return personneRepository.save(personne);
    }

    /**
     * Update person in DB
     * @param personne
     * @return
     */
    public Personne updatePerson(Personne personne){
        if (personne.getNom().isEmpty() || personne.getPrenom().isEmpty()){
            throw new RuntimeException("Name or firstname empty");
        }
        Personne personFound = personneRepository
                .findById(personne.getId()).get();

        personFound.setNom(personne.getNom());
        personFound.setPrenom(personne.getPrenom());

        return personneRepository.save(personFound);
    }


    @PersistenceContext
    private EntityManager entityManager;
    /**
     * Search firstname or name by keyword in DB
     * @param keyword
     * @return
     */
    public List<Personne> search(String keyword) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Personne> criteriaQuery = criteriaBuilder.createQuery(Personne.class);

        Root<Personne> root = criteriaQuery.from(Personne.class);
        String criteriaStr = "%" + keyword.toUpperCase() + "%";

        Predicate predicate = criteriaBuilder.or(criteriaBuilder
                        .like(criteriaBuilder.upper(root.get("prenom")), criteriaStr),
                criteriaBuilder.like(criteriaBuilder.upper(root.get("nom")), criteriaStr));
        criteriaQuery.where(predicate);

        TypedQuery<Personne> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }
}
