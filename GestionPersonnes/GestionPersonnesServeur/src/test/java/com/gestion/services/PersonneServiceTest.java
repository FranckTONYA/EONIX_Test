package com.gestion.services;

import com.gestion.models.Personne;
import com.gestion.repositories.PersonneRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PersonneServiceTest {
    @InjectMocks
    PersonneService personneService;

    @Mock
    PersonneRepository personneRepository;

    Personne personne;
    List<Personne> personneList;

    @BeforeEach
    void init() {

        MockitoAnnotations.openMocks(this);

        personne = build_person();
        personneList = build_person_list();

        when(personneRepository.save(Mockito.any(Personne.class))).then(AdditionalAnswers.returnsFirstArg());
        when(personneRepository.findAll()).thenReturn(personneList);
        when(personneRepository.findById(Mockito.anyLong())).thenReturn(Optional.ofNullable(personne));

        personneService.setPersonneRepository(personneRepository);
    }

        /**
         * Test saving a Person
         */
        @Test
        final void testSavePerson() {

            Personne personneTest = personneService.savePerson(personne);

            Assertions.assertNotNull(personneTest);
            Assertions.assertNotNull(personneTest.getId());
            Assertions.assertEquals(personneTest.getNom(), personne.getNom());
            Assertions.assertEquals(personneTest.getPrenom(), personne.getPrenom());
        }

        /**
         * Test editing a already existing Person
         */
        @Test
        final void testEditPerson () {
            Personne personneTest = personneService.savePerson(personne);

            Assertions.assertNotNull(personneTest);
            Assertions.assertNotNull(personneTest.getId());
            Assertions.assertEquals(personneTest.getNom(), personne.getNom());
            Assertions.assertEquals(personneTest.getPrenom(), personne.getPrenom());
        }

    /**
     * Test find all Person
     */
    @Test
    final void testFindAllPerson () {
        List<Personne> personneTestList = (List<Personne>) personneService.getPersonneRepository().findAll();

        Assertions.assertNotNull(personneTestList);
        Assertions.assertNotNull(personneTestList.get(0).getId());
        Assertions.assertEquals(personneList.size(), personneTestList.size());
    }

    /**
     * Build Person for test
     * @return personne
     */
    private Personne build_person() {
        Personne personne = new Personne();
        personne.setId(Long.parseLong("1"));
        personne.setPrenom("Frank");
        personne.setNom("Tonya");

        return personne;
    }

    /**
     * Build Person list for test
     * @return list of Person
     */
    private List<Personne> build_person_list() {
        List<Personne> personneList = new ArrayList<>();
        Personne personne = new Personne();
        personne.setId(Long.parseLong("1"));
        personne.setPrenom("Frank");
        personne.setNom("Tonya");

        Personne personne2 = new Personne();
        personne2.setId(Long.parseLong("2"));
        personne2.setPrenom("Aubin");
        personne2.setNom("Buy");

        Personne personne3 = new Personne();
        personne3.setId(Long.parseLong("1"));
        personne3.setPrenom("Vincent");
        personne3.setNom("DuBois");

        personneList.add(personne);
        personneList.add(personne2);
        personneList.add(personne3);

        return personneList;
    }
}
