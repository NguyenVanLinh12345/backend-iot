package com.javatechie.controller.admin;

import com.javatechie.dto.UserDto;
import com.javatechie.service.IUserService;
import com.javatechie.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/api")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserDto> findAllUser() {
        List<UserDto> listUsers = userService.findAllUser();
        return listUsers;
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> findOneUser(@RequestParam("id") Integer id) {
        UserDto user = userService.findOneUser(id);
        if(user == null) {
            return ResponseEntity.badRequest().body("Can not found user!!");
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/user")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto) {
        UserDto userResponse = userService.updateUser(userDto);
        if(userResponse.getMessage().contains("success")) {
            return ResponseEntity.ok(userResponse);
        }
        return ResponseEntity.badRequest().body(userResponse.getMessage());
    }

    @DeleteMapping("/user")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteUser(@RequestParam("id") Integer id) {
        String message = userService.deleteUser(id);
        if(message.equals("Success")) {
            return ResponseEntity.ok(message);
        }
        return ResponseEntity.badRequest().body(message);
    }
}
