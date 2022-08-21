package ou.phonebook.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne()
    @JoinColumn(name = "department_id")
    private Department department;
    private String name;
    private String jobTitle;

    public Person(Department department, String name, String jobTitle) {
        this.department = department;
        this.name = name;
        this.jobTitle = jobTitle;
    }
}
