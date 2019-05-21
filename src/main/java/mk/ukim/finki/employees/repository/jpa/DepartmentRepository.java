package mk.ukim.finki.employees.repository.jpa;

import mk.ukim.finki.employees.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Department findByName(String name);

    List<Department> findAllByManager(Long manager);
}
