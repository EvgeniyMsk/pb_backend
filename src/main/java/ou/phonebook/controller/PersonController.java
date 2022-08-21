package ou.phonebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ou.phonebook.entity.Person;
import ou.phonebook.entity.dto.PersonDTO;
import ou.phonebook.service.PersonService;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/people")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public ResponseEntity<List<Person>> findAll() {
        try {
            return ResponseEntity.ok(personService.findAll());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping
    public ResponseEntity<Person> createPerson(@RequestBody PersonDTO personDTO) {
        try {
            return ResponseEntity.ok(personService.createPerson(personDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Person> findById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(personService.findById(id));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable("id") Long id,
                                               @RequestBody PersonDTO personDTO) {
        try {
            return ResponseEntity.ok(personService.updatePerson(id, personDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Person> deletePerson(@PathVariable("id") Long id) {
        try {
            personService.deletePerson(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
