package maharjanworks.service;

import org.springframework.http.ResponseEntity;

import maharjanworks.dto.EmployeeDTO;

public interface EmployeeService {

	void save(EmployeeDTO dto);

	EmployeeDTO findByEmail(String email);

	ResponseEntity<?> findById(int employeeId);

}
