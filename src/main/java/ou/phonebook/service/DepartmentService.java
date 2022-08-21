package ou.phonebook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ou.phonebook.Requests.DepartmentIdRequest;
import ou.phonebook.Requests.PersonIdRequest;
import ou.phonebook.entity.Department;
import ou.phonebook.entity.Person;
import ou.phonebook.entity.dto.DepartmentDTO;
import ou.phonebook.repository.DepartmentRepository;
import ou.phonebook.repository.PersonRepository;

import java.util.List;
import java.util.Set;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final PersonRepository personRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository,
                             PersonRepository personRepository) {
        this.departmentRepository = departmentRepository;
        this.personRepository = personRepository;
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    public Department createDepartment(DepartmentDTO departmentDTO) {
        Department headDepartment = null;
        if (departmentDTO.getHeadDepartmentId() != null)
            headDepartment = departmentRepository.findById(departmentDTO.getHeadDepartmentId()).orElse(null);
        Department department = new Department();
        if (headDepartment != null)
            headDepartment.addDepartment(department);
        department.setHeadDepartment(headDepartment);
        department.setName(departmentDTO.getName());
        return departmentRepository.save(department);
    }

    public Department updateDepartment(Long id, DepartmentDTO departmentDTO) {
        Department temp = departmentRepository.findById(id).orElseThrow(NullPointerException::new);
        temp.setName(departmentDTO.getName());
        Department headDepartment = null;
        if (departmentDTO.getHeadDepartmentId() != null)
            headDepartment = departmentRepository.findById(departmentDTO.getHeadDepartmentId()).orElse(null);
        temp.setHeadDepartment(headDepartment);
        return departmentRepository.save(temp);
    }

    public Department getDepartmentById(Long id) {
        return departmentRepository.findDepartmentByIdWithPeople(id);
    }

    public void deleteById(Long id) {
        Department department = departmentRepository.findDepartmentByIdWithPeople(id).getHeadDepartment();
        department.deleteDepartment(departmentRepository.findDepartmentByIdWithPeople(id));
        departmentRepository.deleteById(id);
    }

    public Set<Person> addChef(Long departmentId, PersonIdRequest personIdRequest) {
        Department department = departmentRepository.findDepartmentByIdWithPeople(departmentId);
        Person person = personRepository.findById(personIdRequest.getPersonId()).orElseThrow(NullPointerException::new);
        department.addChef(person);
        departmentRepository.save(department);
        return department.getChefs();
    }

    public Set<Person> deleteChef(Long departmentId, PersonIdRequest personIdRequest) {
        Department department = departmentRepository.findDepartmentByIdWithPeople(departmentId);
        Person person = personRepository.findById(personIdRequest.getPersonId()).orElseThrow(NullPointerException::new);
        department.deleteChef(person);
        departmentRepository.save(department);
        return department.getChefs();
    }

    public Set<Person> addEmployee(Long departmentId, PersonIdRequest personIdRequest) {
        Department department = departmentRepository.findDepartmentByIdWithPeople(departmentId);
        Person person = personRepository.findById(personIdRequest.getPersonId()).orElseThrow(NullPointerException::new);
        department.addEmployee(person);
        departmentRepository.save(department);
        return department.getEmployees();
    }

    public Set<Person> deleteEmployee(Long departmentId, PersonIdRequest personIdRequest) {
        Department department = departmentRepository.findDepartmentByIdWithPeople(departmentId);
        Person person = personRepository.findById(personIdRequest.getPersonId()).orElseThrow(NullPointerException::new);
        department.deleteEmployee(person);
        departmentRepository.save(department);
        return department.getEmployees();
    }

    public Set<Department> addDepartment(Long departmentId, DepartmentIdRequest departmentIdRequest) {
        Department department = departmentRepository.findDepartmentByIdWithPeople(departmentId);
        Department subDepartment = departmentRepository.findById(departmentIdRequest.getDepartmentId()).orElseThrow(NullPointerException::new);
        department.addDepartment(subDepartment);
        departmentRepository.save(department);
        return department.getDepartments();
    }

    public Set<Department> deleteDepartment(Long departmentId, DepartmentIdRequest departmentIdRequest) {
        Department department = departmentRepository.findDepartmentByIdWithPeople(departmentId);
        Department subDepartment = departmentRepository.findById(departmentIdRequest.getDepartmentId()).orElseThrow(NullPointerException::new);
        department.deleteDepartment(subDepartment);
        departmentRepository.save(department);
        return department.getDepartments();
    }
}
