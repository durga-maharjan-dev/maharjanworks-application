package maharjanworks.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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
import maharjanworks.exception.EmailAlreadyExistsException;
import maharjanworks.service.EmployeeService;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeControllerTest {
	
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
	public void testSave_newEmail() {
		EmployeeDTO dto = new EmployeeDTO("firstName","lastName","test@gmail.com","test");
		
		when(this.employeeService.findByEmail(dto.getEmail())).thenReturn(null);
		
		ResponseEntity<?> responseEntity = this.employeeController.save(dto);
		
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals("Record Saved.", responseEntity.getBody());
		
		verify(employeeService,times(1)).save(dto);
	}
	
	@Test
	public void testSave_emailAlreadyExists() {
		
		EmployeeDTO dto = new EmployeeDTO("firstName","lastName","test@gmail.com","test");
		
		when(this.employeeService.findByEmail(dto.getEmail())).thenReturn(dto);
		
	    EmailAlreadyExistsException exception = assertThrows(EmailAlreadyExistsException.class, ()->employeeController.save(dto));
		
	    assertEquals("Email already exists!", exception.getMessage());
	    
		verify(employeeService,never()).save(dto);
	}

}
