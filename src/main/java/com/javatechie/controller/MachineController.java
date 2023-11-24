package com.javatechie.controller;

import com.javatechie.dto.MachineDto;
import com.javatechie.entity.Machine;
import com.javatechie.service.IMachineService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class MachineController {

    @Autowired
    private IMachineService machineService;

    @GetMapping("/machines") // lấy ra toàn bộ machine có trong database chỉ của admin
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<MachineDto> findAllMachine() {
        List<MachineDto> listMachine = machineService.findAll();
        return listMachine;
    }

    @GetMapping("/machines/{userId}")
    public List<MachineDto> findAllMachineByEmployee(@PathVariable("userId") Integer userId) {
        List<MachineDto> listMachine = machineService.findAllByUser(userId);
        return listMachine;
    }

    @GetMapping("/machine")
    public ResponseEntity<?> findOne(@RequestParam("id") Integer id) {
        MachineDto machineDto = machineService.findOne(id);
        if(machineDto == null) {
            return ResponseEntity.badRequest().body("Can not found machine!!");
        }
        return ResponseEntity.ok(machineDto);
    }

    @PutMapping("/machine")
    public ResponseEntity<?> updateMachine(@RequestBody MachineDto machineDto) {
        MachineDto machineResponse = machineService.updateMachine(machineDto);
        if(machineResponse == null) {
            return ResponseEntity.badRequest().body("Can not found machine!!");
        }
        else if(machineResponse.getMessage() != null) {
            return ResponseEntity.badRequest().body(machineResponse.getMessage());
        }
        return ResponseEntity.ok(machineResponse);
    }

    @DeleteMapping("/machine")
    public ResponseEntity<?> deleteMachine(@RequestParam("id") Integer id) {
        String message = machineService.deleteMachine(id);
        if(message.contains("success")) {
            return ResponseEntity.ok(message);
        }
        return ResponseEntity.badRequest().body(message);
    }

    @PostMapping("/machine")
    @PreAuthorize("hasAuthority('ADMIN')") // chi admin duoc them moi machine
    public ResponseEntity<?> saveMachine(@RequestBody MachineDto machineDto) {
        MachineDto machineResponse = machineService.saveMachine(machineDto);
        if(machineResponse == null) {
            return ResponseEntity.badRequest().body("Save machine error!!");
        }
        else if (machineResponse.getMessage() != null) {
            return ResponseEntity.badRequest().body(machineResponse.getMessage());
        }
        return ResponseEntity.ok(machineResponse);
    }
}
