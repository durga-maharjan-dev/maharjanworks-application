package maharjanworks.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import maharjanworks.model.Employee;

@RunWith(MockitoJUnitRunner.class)
public class EmployeeRepositoryTest {
	
	@Mock
	private EmployeeRepository employeeRepository;
	
	
	@Before
	public void before(){
		MockitoAnnotations.initMocks(employeeRepository);
	}


	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindByEmail_Found() {
		Employee employee = new Employee("firstName","lastName", "test@gmail.com","test");
		
		when(this.employeeRepository.findByEmail(employee.getEmail())).thenReturn(Optional.of(employee));
		
		Optional<Employee> optional= this.employeeRepository.findByEmail(employee.getEmail());
		
		assertTrue(optional.isPresent());
		Employee dbEmployee = optional.get();
		assertEquals(employee.getFirstName(),dbEmployee.getFirstName());
		
		verify(this.employeeRepository,times(1)).findByEmail(employee.getEmail());
	}
	
	@Test 
	public void testFindByEmail_notFound() {
		String email = "test@gmail.com";
		
		when(this.employeeRepository.findByEmail(email)).thenReturn(Optional.empty());
		
		Optional<Employee> optional = this.employeeRepository.findByEmail(email);
		
		assertFalse(optional.isPresent());	
		
		verify(employeeRepository,times(1)).findByEmail(email);
	}

}
