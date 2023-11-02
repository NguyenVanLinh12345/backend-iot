package com.javatechie.controller.employee;

import com.javatechie.dto.TypeEggDto;
import com.javatechie.service.ITypeEggService;
import com.javatechie.service.impl.TypeEggService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin("*")
public class TypeEggCcontroller {

    @Autowired
    private ITypeEggService typeEggService;

    @PostMapping("/typeEgg/import")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> saveTypeEgg(@RequestBody TypeEggDto typeEggDto) {
        TypeEggDto typeEgg = typeEggService.addTypeEgg(typeEggDto);
        if(typeEgg == null) {
            return ResponseEntity.badRequest().body("The name is the same");
        }
        else if(typeEgg.getId() != null) {
            return ResponseEntity.ok(typeEgg);
        }
        return ResponseEntity.badRequest().body("Save typeEgg error");
    }

    @PutMapping("/typeEgg/update")
    @PreAuthorize("hasAuthority('ADMIN EMPLOYEE')")
    public ResponseEntity<?> updateTypeEgg(@RequestBody TypeEggDto typeEggDto) {
        TypeEggDto typeEgg = typeEggService.updateTypeEgg(typeEggDto);
        if(typeEgg == null) {
            return ResponseEntity.badRequest().body("The name is the same");
        }
        else if(typeEgg.getId() != null) {
            return ResponseEntity.ok(typeEgg);
        }
        return ResponseEntity.badRequest().body("Update error");
    }

    @GetMapping("/typeEgges")
    @PreAuthorize("hasAuthority('ADMIN EMPLOYEE')")
    public List<TypeEggDto> findAll() {
        return typeEggService.findAll();
    }
    @GetMapping("typeEgg")
    @PreAuthorize("hasAuthority('ADMIN EMPLOYEE')")
    public ResponseEntity<?> findOne(@RequestParam("id") Integer id) {
        TypeEggDto typeEggDto = typeEggService.findOne(id);
        if(typeEggDto == null) {
            return ResponseEntity.badRequest().body("Can not found typeEgg with id = " + id);
        }
        return ResponseEntity.ok(typeEggDto);
    }

    @DeleteMapping("/typeEgg")
    @PreAuthorize("hasAuthority('ADMIN EMPLOYEE')")
    public ResponseEntity<?> deleteTypeEgg(@RequestParam("id") Integer id) {
        String message = typeEggService.deleteTypeEgg(id);
        if(message.equals("success")) {
            return ResponseEntity.ok(message);
        }
        return ResponseEntity.badRequest().body(message);
    }
}
