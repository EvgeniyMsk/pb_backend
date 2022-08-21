package ou.phonebook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    private Set<Person> chefs;
    @OneToMany(cascade = CascadeType.REFRESH, orphanRemoval = true)
    @JsonIgnore
    private Set<Person> employees;
    @OneToOne(cascade = CascadeType.REFRESH)
    @JsonIgnore
    private Department headDepartment;
    @OneToMany(cascade = CascadeType.REFRESH, orphanRemoval = true)
    private Set<Department> departments;
    public Department() {
        chefs = new HashSet<>();
        employees = new HashSet<>();
        headDepartment = null;
        departments = new HashSet<>();
    }

    public void addDepartment(Department department) {
        departments.add(department);
        department.setHeadDepartment(this);
    }

    public void deleteDepartment(Department department) {
        departments.remove(department);
        department.setHeadDepartment(null);
    }

    public void addChef(Person person) {
        chefs.add(person);
        person.setDepartment(this);
    }

    public void deleteChef(Person person) {
        chefs.remove(person);
        person.setDepartment(null);
    }

    public void addEmployee(Person person) {
        employees.add(person);
        person.setDepartment(this);
    }

    public void deleteEmployee(Person person) {
        employees.remove(person);
        person.setDepartment(null);
    }
}
