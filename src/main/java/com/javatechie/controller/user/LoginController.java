package com.javatechie.controller.user;

import com.javatechie.dto.UserDto;
import com.javatechie.service.impl.JwtService;
import com.javatechie.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class LoginController {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;

    // đăng nhập
    @PostMapping("/login")
    public ResponseEntity<?> authenticateAndGetToken(@RequestBody UserDto userDto){
        try {
            Authentication authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
            if (authentication.isAuthenticated()) {
                return ResponseEntity.ok(jwtService.generateToken(userDto.getEmail()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("invalid user request !");
    }

    @PostMapping("/signup") // role == 0(employee), 1(admin), 2(admin + employee)
    public ResponseEntity<?> signIn(@RequestBody UserDto user, @RequestParam("role") Integer role) {
        String responseSignUp = userService.addUser(user, role);
        if(responseSignUp.equals("success")) {
            return ResponseEntity.ok("Sign up account success");
        }
        return ResponseEntity.badRequest().body(responseSignUp);
    }
}
