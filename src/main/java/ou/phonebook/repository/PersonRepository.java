package ou.phonebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ou.phonebook.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
