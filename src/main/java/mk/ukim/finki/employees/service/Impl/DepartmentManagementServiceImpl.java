package mk.ukim.finki.employees.service.Impl;

import mk.ukim.finki.employees.model.Department;
import mk.ukim.finki.employees.repository.jpa.DepartmentRepository;
import mk.ukim.finki.employees.service.DepartmentManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentManagementServiceImpl implements DepartmentManagementService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public Optional<List<Department>> getAllDepartments() {
        return Optional.of(this.departmentRepository.findAll());
    }

    @Override
    public Optional<Department> getDepartmentById(Long id) {
        return this.departmentRepository.findById(id);
    }

    @Override
    public Optional<Department> getDepartmentWithName(String name) {
        return this.departmentRepository.findByName(name);
    }

    @Override
    public Optional<List<Department>> getAllDepartmentsWithManager(Long manager) {
        return this.departmentRepository.findAllByManager(manager);
    }
}
