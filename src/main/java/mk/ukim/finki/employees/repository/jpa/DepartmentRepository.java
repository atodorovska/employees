package mk.ukim.finki.employees.repository.jpa;

import mk.ukim.finki.employees.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

    Optional<Department> findByName(String name);

    Optional<List<Department>> findAllByManager(Long manager);
}
