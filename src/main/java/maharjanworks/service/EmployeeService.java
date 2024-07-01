package maharjanworks.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import maharjanworks.dto.EmployeeDTO;

public interface EmployeeService {

	void save(EmployeeDTO dto);

	EmployeeDTO findByEmail(String email);

	ResponseEntity<EmployeeDTO> findById(int employeeId);

	ResponseEntity<List<EmployeeDTO>> findAll();

}
