package maharjanworks.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import maharjanworks.dto.EmployeeDTO;
import maharjanworks.model.Employee;
import maharjanworks.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public void save(EmployeeDTO dto) {
		Employee employee = new Employee();
		BeanUtils.copyProperties(dto, employee);
		this.employeeRepository.save(employee);
	}

	@Override
	public EmployeeDTO findByEmail(String email) {
		Optional<Employee> optional =this.employeeRepository.findByEmail(email);
		EmployeeDTO dto =null;
		if(optional.isPresent()) {
			Employee employee = optional.get();
			dto = new EmployeeDTO();
			BeanUtils.copyProperties(employee,dto);
			return dto;
		}
		return dto;
	}

}
