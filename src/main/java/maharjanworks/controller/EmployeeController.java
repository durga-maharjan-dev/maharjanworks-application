package maharjanworks.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import maharjanworks.dto.EmployeeDTO;
import maharjanworks.exception.EmailAlreadyExistsException;
import maharjanworks.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping()
	public ResponseEntity<?> save(@RequestBody EmployeeDTO dto	){
		EmployeeDTO dbDTO = this.employeeService.findByEmail(dto.getEmail());
		if (dbDTO == null) {
			this.employeeService.save(dto);
			return new ResponseEntity<>("Record Saved.", HttpStatus.CREATED);
		}else {
			throw new EmailAlreadyExistsException("Email already exists!");
		}
	}
	
	
	
	


}
