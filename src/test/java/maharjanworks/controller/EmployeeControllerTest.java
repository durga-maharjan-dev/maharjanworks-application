package maharjanworks.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
import maharjanworks.exception.EmployeeListIsEmptyException;
import maharjanworks.exception.EmployeeNotFoundException;
import maharjanworks.exception.InvalidEmployeeIdException;
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
	
	@Test
	public void testGetById_employeeFound() {
		EmployeeDTO dto = new EmployeeDTO(100,"test","test","test@gmail.com","test");
	
		ResponseEntity<EmployeeDTO> expected = new ResponseEntity<>(dto,HttpStatus.FOUND);
		
		when(this.employeeService.findById(dto.getEmployeeId())).thenReturn(expected);
		
		ResponseEntity<?> actual = this.employeeController.getById(dto.getEmployeeId());
		
		assertNotNull(actual);
		assertEquals(expected,actual);

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
	
	
	@Test
	public void testGetEmployeeList_found() {
		List<EmployeeDTO> dtoList = new ArrayList<>();
		dtoList.add(new EmployeeDTO(100,"joe","biden","joe.biden@gmail.com","j1234"));
		dtoList.add(new EmployeeDTO(101,"kamala","harris","kamala.harris@gmail.com","k1234"));
		
		ResponseEntity<List<EmployeeDTO>> expected = new ResponseEntity<>(dtoList,HttpStatus.OK);
		
		when(this.employeeService.findAll()).thenReturn(expected);
		
		ResponseEntity<List<EmployeeDTO>> actual = this.employeeController.getEmployeeList();
	
		assertNotNull(actual);
		assertEquals(expected, actual);
		
		verify(this.employeeService,times(1)).findAll();
	}
	



}
