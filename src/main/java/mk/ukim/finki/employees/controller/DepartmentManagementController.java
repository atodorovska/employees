package mk.ukim.finki.employees.controller;

import mk.ukim.finki.employees.model.Department;
import mk.ukim.finki.employees.service.DepartmentManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentManagementController {

    @Autowired
    private DepartmentManagementService departmentManagementService;

    @GetMapping("/department/id")
    public ResponseEntity<Department> getDepartmentById(@RequestParam Long id){
        return this.departmentManagementService.getDepartmentById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/department/name")
    public ResponseEntity<Department> getDepartmentWithName(@RequestParam String name){
        return this.departmentManagementService.getDepartmentWithName(name).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all")
    public ResponseEntity<List<Department>> getAllDepartments(){
        return this.departmentManagementService.getAllDepartments().map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/all/manager")
    public ResponseEntity<List<Department>> getAllDepartmentsWithManager(@RequestParam Long manager){
        return this.departmentManagementService.getAllDepartmentsWithManager(manager).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
