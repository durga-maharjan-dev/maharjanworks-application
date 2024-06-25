package maharjanworks.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import maharjanworks.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

}
