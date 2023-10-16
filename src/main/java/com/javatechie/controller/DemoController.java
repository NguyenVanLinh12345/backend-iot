package com.javatechie.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class DemoController {


	@GetMapping("/welcome-admin")
	@PreAuthorize("hasAuthority('ADMIN')")
	public String welcome() {
		return "Hello, admin";
	}
	
	@GetMapping("/welcome-employee")
	@PreAuthorize("hasAuthority('Employee')")
	public String welcomeEmployee() {
		return "Hello employee";
	}

	@GetMapping("/hello-world")
	public String myNew() {
		return "hello, no authentication";
	}


}
