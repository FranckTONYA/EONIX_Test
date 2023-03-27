package com.gestion.repositories;

import com.gestion.models.Personne;
import org.springframework.data.repository.CrudRepository;

public interface PersonneRepository extends CrudRepository<Personne, Long> {
}
