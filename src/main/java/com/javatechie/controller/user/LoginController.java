package com.javatechie.controller.user;

import com.javatechie.dto.AuthRequest;
import com.javatechie.entity.UserInfo;
import com.javatechie.service.impl.JwtService;
import com.javatechie.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

    @PostMapping("/signup")
    public ResponseEntity<?> signIn(@RequestBody UserInfo user) {
        String responseSignUp = userService.addUser(user);
        if(responseSignUp.equals("success")) {
            return ResponseEntity.ok("Sign up account success");
        }
        return ResponseEntity.badRequest().body(responseSignUp);
    }
}
