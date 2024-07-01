package maharjanworks.service;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import maharjanworks.dto.EmployeeDTO;
import maharjanworks.exception.EmployeeListIsEmptyException;
import maharjanworks.model.Employee;
import maharjanworks.repository.EmployeeRepository;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeServiceImplTest {
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	@InjectMocks
	private EmployeeServiceImpl employeeService;
	
	@Before
	public void before() {
		MockitoAnnotations.initMocks(employeeRepository);
	}

	@Test
	public void testSave() {
		EmployeeDTO dto = new EmployeeDTO("firstName","lastName","email@gmail.com","xxxx");
		Mockito.when(this.employeeRepository.save(Mockito.any(Employee.class))).thenReturn(null);
		employeeService.save(dto);
		Mockito.verify(employeeRepository,times(1)).save(Mockito.any(Employee.class));
		verifyNoMoreInteractions(employeeRepository);
	}
	
	
	@Test
	public void testFindByEmail_employeeFound() {
		
		Employee employee = new Employee("firstName","lastName","email@gmail.com","xxxx");
		
		when(this.employeeRepository.findByEmail(employee.getEmail())).thenReturn(Optional.of(employee));
		
		EmployeeDTO dto = this.employeeService.findByEmail(employee.getEmail());
		
		assertNotNull(dto);
		assertEquals(employee.getEmail(),dto.getEmail());
		assertEquals(employee.getFirstName(),dto.getFirstName());
		Mockito.verify(employeeRepository,times(1)).findByEmail(employee.getEmail());

	}
	
	@Test
	public void testFindByEmail_notFound() {
		String email = "test@gmail.com";
		
		when(this.employeeRepository.findByEmail(email)).thenReturn(Optional.empty());
		
		EmployeeDTO dto = this.employeeService.findByEmail(email);
		
		assertNull(dto);
		verify(employeeRepository,times(1)).findByEmail(email);
		verifyNoMoreInteractions(employeeRepository);
	}
	
	@After
	public void after() {
		
	}
	
	
	@Test
	public void testFindAll_employeListFound() {
		
		List<Employee> employeeList = new ArrayList<>();
		employeeList.add(new Employee(100,"joe","biden","joe.biden@gmail.com","j1234"));
		employeeList.add(new Employee(101,"kamala","harris","kamala.harris@gmail.com","k1234"));
		
		when(this.employeeRepository.findAll()).thenReturn(employeeList);

		List<EmployeeDTO> dtoList = new ArrayList<>();
		for(Employee employee: employeeList) {
			EmployeeDTO dto = new EmployeeDTO();
			BeanUtils.copyProperties(employee, dto);
			dtoList.add(dto);
		}
		
		ResponseEntity<List<EmployeeDTO>> expected = new ResponseEntity<>(dtoList,HttpStatus.OK);
		
		ResponseEntity<List<EmployeeDTO>> actual = this.employeeService.findAll();
		
		assertEquals(expected, actual);
		verify(this.employeeRepository, times(1)).findAll();
		
	}
	
	@Test (expected = EmployeeListIsEmptyException.class)
	public void testFindAll_employeeListEmpty() {
		
		List<Employee> employeeList = new ArrayList<>();
		
		when(this.employeeRepository.findAll()).thenReturn(employeeList);
		
		this.employeeService.findAll();
	}
	
	

}