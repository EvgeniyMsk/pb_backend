package ou.phonebook.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ou.phonebook.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    @Query("select d from Department d left join fetch d.chefs left join fetch d.employees left join fetch d.departments where d.id=:id")
    Department findDepartmentByIdWithPeople(@Param("id") Long id);
}
