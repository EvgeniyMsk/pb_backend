package ou.phonebook;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import ou.phonebook.entity.Department;
import ou.phonebook.entity.Person;
import ou.phonebook.repository.DepartmentRepository;
import ou.phonebook.repository.PersonRepository;
import ou.phonebook.service.DepartmentService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
public class PhonebookTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PersonRepository personRepository;

    private ObjectMapper objectMapper;
    private Person person;
    private Department department;
    private Department underDepartment;

    @BeforeEach
    public void init() {
        person = personRepository.getById(1L);
//        person.setName("Логвинов Евгений Вячеславович");
//        person.setJobTitle("Начальник отделения");
        objectMapper = new ObjectMapper();
    }

    @Test
    public void create() throws JsonProcessingException {
    }

}
