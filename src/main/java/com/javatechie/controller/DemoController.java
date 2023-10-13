package com.javatechie.controller;

import com.javatechie.dto.AuthRequest;
import com.javatechie.entity.UserInfo;
import com.javatechie.service.JwtService;
import com.javatechie.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class DemoController {
	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;

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

	@PostMapping("/login")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest){
		try {
	        Authentication authentication = authenticationManager
	                .authenticate(new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
	        
	        if (authentication.isAuthenticated()) {
	            return jwtService.generateToken(authRequest.getEmail());
	        } 
	        else {
	            throw new UsernameNotFoundException("invalid user request !");
	        }
	    } catch (BadCredentialsException e) {
	    	throw new BadCredentialsException("invalid user request !");
	    } catch (Exception e) {
	    	throw new UsernameNotFoundException("invalid user request !");
	    } 
	}

	@PostMapping("/signin")
	public String signIn(@RequestBody UserInfo user) {

		return userService.addUser(user);
	}
}
