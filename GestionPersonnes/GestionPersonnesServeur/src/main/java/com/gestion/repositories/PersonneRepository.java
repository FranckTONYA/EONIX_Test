package com.gestion.repositories;

import com.gestion.models.Personne;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonneRepository extends CrudRepository<Personne, Long> {
    @Query("SELECT p FROM Personne p WHERE p.nom LIKE %:keyword"
            + " OR p.prenom LIKE %:keyword")
    public List<Personne> search(@Param("keyword") String keyword);
}
