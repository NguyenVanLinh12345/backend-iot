package com.javatechie.controller;

import com.javatechie.config.UserInfoUserDetails;
import com.javatechie.dto.UserDto;
import com.javatechie.service.IUserService;
import com.javatechie.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/admin/api/users") // lay ra toan bo user theo role trong database
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<UserDto> findAllUser(@RequestParam("role") Optional<String> role) {
        List<UserDto> listUsers = userService.findAllUser(role.orElse(""));
        return listUsers;
    }

    @GetMapping("/admin/api/user")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> findOneUser(@RequestParam("id") Integer id) {
        UserDto user = userService.findOneUser(id);
        if(user == null) {
            return ResponseEntity.badRequest().body("Can not found user!!");
        }
        return ResponseEntity.ok(user);
    }

    @PutMapping("/admin/api/user")
    @PreAuthorize("hasAuthority('ADMIN')") // admin thay đổi thông tin của các employee khác
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto) {
        UserDto userResponse = userService.updateUser(userDto, 1); // 1 = ADMIN
        if(userResponse.getMessage().contains("success")) {
            return ResponseEntity.ok(userResponse);
        }
        return ResponseEntity.badRequest().body(userResponse.getMessage());
    }

    @PutMapping("/admin/api/change/info") // tự thay đổi thông tin của chính mình
    public ResponseEntity<?> updateEmployee(@RequestBody UserDto userDto) {
        UserDto userResponse = userService.updateUser(userDto, 2); // 2 = EMPLOYEE
        if(userResponse.getMessage().contains("success")) {
            return ResponseEntity.ok(userResponse);
        }
        return ResponseEntity.badRequest().body(userResponse.getMessage());
    }

    @DeleteMapping("/admin/api/user")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteUser(@RequestParam("id") Integer id) {
        String message = userService.deleteUser(id);
        if(message.equals("Success")) {
            return ResponseEntity.ok(message);
        }
        return ResponseEntity.badRequest().body(message);
    }

    @GetMapping("api/user/infoDetail")
    public ResponseEntity<?> getInfo() {
        UserDto userDto = userService.getInfoUser();
        if(userDto.getMessage() != null) {
            return ResponseEntity.badRequest().body(userDto.getMessage());
        }
        return ResponseEntity.ok(userDto);
    }
}
