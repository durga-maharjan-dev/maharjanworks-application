package maharjanworks.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import maharjanworks.dto.EmployeeDTO;
import maharjanworks.exception.EmployeeNotFoundException;
import maharjanworks.exception.InvalidEmployeeIdException;
import maharjanworks.service.EmployeeService;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerGetByIdTest {
	
	@Mock
	private EmployeeService employeeService;
	
	@InjectMocks
	private EmployeeController employeeController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(employeeService);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetById_employeeFound() {
		EmployeeDTO dto = new EmployeeDTO(100,"test","test","test@gmail.com","test");
	
		
		ResponseEntity<?> expected = new ResponseEntity<>(dto,HttpStatus.FOUND);
		
		when(employeeService.findById(dto.getEmployeeId())).thenReturn(expected);
		
		ResponseEntity<?> actual = this.employeeController.getById(dto.getEmployeeId());

		verify(employeeService,times(1)).findById(dto.getEmployeeId());
	}
	
	@Test(expected = EmployeeNotFoundException.class)
	public void testGetById_employeeNotFound() {
		EmployeeDTO dto = new EmployeeDTO(100,"test","test","test@gmail.com","test");
		
		when(employeeService.findById(dto.getEmployeeId())).thenThrow(EmployeeNotFoundException.class);
		
		this.employeeController.getById(dto.getEmployeeId());
	}
	
	@Test(expected = InvalidEmployeeIdException.class)
	public void testGetById_invalidEmployeeId() {
		int employeeId = 0;
		
		when(employeeService.findById(employeeId)).thenThrow(InvalidEmployeeIdException.class);
		
		employeeService.findById(employeeId);
		
		verify(employeeService,times(1)).findById(employeeId);
	}

}
