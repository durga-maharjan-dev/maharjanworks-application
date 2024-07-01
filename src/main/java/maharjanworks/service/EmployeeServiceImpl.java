package maharjanworks.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import maharjanworks.dto.EmployeeDTO;
import maharjanworks.exception.EmployeeListIsEmptyException;
import maharjanworks.exception.EmployeeNotFoundException;
import maharjanworks.exception.InvalidEmployeeIdException;
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

	@Override
	public ResponseEntity<EmployeeDTO> findById(int employeeId) {
		if (employeeId <= 0) {
			throw new InvalidEmployeeIdException("Employee ID must be greater than zero");
		}
		Optional<Employee> optional = this.employeeRepository.findById(employeeId);
		if (optional.isPresent()) {
			EmployeeDTO dto = new EmployeeDTO();
			BeanUtils.copyProperties(optional.get(), dto);
			return new ResponseEntity<>(dto, HttpStatus.FOUND);
		}else {
			throw new EmployeeNotFoundException("Employee Id Not Exists!");
		}
	}

	@Override
	public ResponseEntity<List<EmployeeDTO>> findAll() {
		List<Employee> employeeList = this.employeeRepository.findAll();
		
		List<EmployeeDTO>  dtoList = new ArrayList<>();
		if (!employeeList.isEmpty()) {
			for( Employee employee: employeeList) {
				EmployeeDTO dto = new EmployeeDTO();
				BeanUtils.copyProperties(employee, dto);
				dtoList.add(dto);
			}
			return new ResponseEntity<>(dtoList, HttpStatus.OK);
		}else {
			throw new EmployeeListIsEmptyException("No Employee Record Found.");
		}
		
	}

}
