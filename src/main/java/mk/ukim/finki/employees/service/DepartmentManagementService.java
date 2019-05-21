package mk.ukim.finki.employees.service;

import mk.ukim.finki.employees.model.Department;

import java.util.List;
import java.util.Optional;

public interface DepartmentManagementService {

    Optional<List<Department>> getAllDepartments();

    Optional<Department> getDepartmentById(Long id);

    Optional<Department> getDepartmentWithName(String name);

    Optional<List<Department>> getAllDepartmentsWithManager(Long manager);
}
