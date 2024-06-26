package maharjanworks.service;

import maharjanworks.dto.EmployeeDTO;

public interface EmployeeService {

	void save(EmployeeDTO dto);

	EmployeeDTO findByEmail(String email);

}
