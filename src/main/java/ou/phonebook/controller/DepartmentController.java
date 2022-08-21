package ou.phonebook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ou.phonebook.Requests.DepartmentIdRequest;
import ou.phonebook.Requests.PersonIdRequest;
import ou.phonebook.entity.Department;
import ou.phonebook.entity.Person;
import ou.phonebook.entity.dto.DepartmentDTO;
import ou.phonebook.service.DepartmentService;

import java.util.List;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/departments")
public class DepartmentController {
    private final DepartmentService departmentService;

    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping()
    private ResponseEntity<List<Department>> findAllDepartments() {
        return new ResponseEntity<>(departmentService.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    private ResponseEntity<Department> createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        try {
            return ResponseEntity.ok(departmentService.createDepartment(departmentDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Department> findDepartmentById(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(departmentService.getDepartmentById(id));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Department> updateDepartmentById(@PathVariable("id") Long id,
                                                           @RequestBody DepartmentDTO departmentDTO) {
        try {
            return ResponseEntity.ok(departmentService.updateDepartment(id, departmentDTO));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Department> deleteDepartment(@PathVariable("id") Long id) {
        try {
            departmentService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/chefs")
    public ResponseEntity<Set<Person>> getChefs(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(departmentService.getDepartmentById(id).getChefs());
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/chefs")
    public ResponseEntity<Set<Person>> createChef(@PathVariable("id") Long departmentId,
                                                  @RequestBody PersonIdRequest personId) {
        try {
            return ResponseEntity.ok(departmentService.addChef(departmentId, personId));
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}/chefs")
    public ResponseEntity<Set<Person>> deleteChef(@PathVariable("id") Long departmentId,
                                                  @RequestBody PersonIdRequest personId) {
        try {
            return ResponseEntity.ok(departmentService.deleteChef(departmentId, personId));
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity<Set<Person>> getEmployees(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(departmentService.getDepartmentById(id).getEmployees());
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/employees")
    public ResponseEntity<Set<Person>> createEmployee(@PathVariable("id") Long departmentId,
                                                  @RequestBody PersonIdRequest personId) {
        try {
            return ResponseEntity.ok(departmentService.addEmployee(departmentId, personId));
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}/employees")
    public ResponseEntity<Set<Person>> deleteEmployee(@PathVariable("id") Long departmentId,
                                                  @RequestBody PersonIdRequest personId) {
        try {
            return ResponseEntity.ok(departmentService.deleteEmployee(departmentId, personId));
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}/departments")
    public ResponseEntity<Set<Department>> getSubDepartments(@PathVariable("id") Long id) {
        try {
            return ResponseEntity.ok(departmentService.getDepartmentById(id).getDepartments());
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/{id}/departments")
    public ResponseEntity<Set<Department>> addSubDepartment(@PathVariable("id") Long departmentId,
                                                            @RequestBody DepartmentIdRequest subDepartmentId) {
        try {
            return ResponseEntity.ok(departmentService.addDepartment(departmentId, subDepartmentId));
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}/departments")
    public ResponseEntity<Set<Department>> deleteSubDepartment(@PathVariable("id") Long departmentId,
                                                        @RequestBody DepartmentIdRequest subDepartmentId) {
        try {
            return ResponseEntity.ok(departmentService.deleteDepartment(departmentId, subDepartmentId));
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
