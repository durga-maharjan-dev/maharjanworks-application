package maharjanworks.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {
	
	
	@GetMapping("/hello")
	public String getHi() {
		return "Hi.......";
	}
	

}
