package ou.phonebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.phonebook.entity.Person;
import ou.phonebook.entity.dto.PersonDTO;
import ou.phonebook.repository.PersonRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(Long id) {
        return personRepository.findById(id).orElseThrow(NullPointerException::new);
    }

    public Person createPerson(PersonDTO personDTO) {
        Person person = new Person();
        person.setName(personDTO.getName());
        person.setJobTitle(personDTO.getJobTitle());
        person.setDepartment(null);
        return personRepository.save(person);
    }

    public Person updatePerson(Long personId, PersonDTO personDTO) {
        Person person = personRepository.findById(personId).orElseThrow(NullPointerException::new);
        person.setName(personDTO.getName());
        person.setJobTitle(personDTO.getJobTitle());
        return personRepository.save(person);
    }

    public void deletePerson(Long personId) {
        personRepository.deleteById(personId);
    }
}
