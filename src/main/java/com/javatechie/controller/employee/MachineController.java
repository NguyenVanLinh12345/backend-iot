package com.javatechie.controller.employee;

import com.javatechie.dto.MachineDto;
import com.javatechie.service.IMachineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class MachineController {

    @Autowired
    private IMachineService machineService;

    @GetMapping("/machines")
    public List<MachineDto> findAllMachine() {
        List<MachineDto> listMachine = machineService.findAll();
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
    public ResponseEntity<?> saveMachine(@RequestBody MachineDto machineDto) {
        MachineDto machineResponse = machineService.saveMachine(machineDto);
        if(machineResponse == null) {
            return ResponseEntity.badRequest().body("Save machine error!!");
        }
        return ResponseEntity.ok(machineResponse);
    }
}
