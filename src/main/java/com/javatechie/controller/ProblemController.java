package com.javatechie.controller;

import com.javatechie.dto.ProblemDto;
import com.javatechie.service.IProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api")
@CrossOrigin("*")
@RestController
public class ProblemController {
    @Autowired
    private IProblemService problemService;

    @PostMapping("/problem")
    public ResponseEntity<?> saveProblem(@RequestBody ProblemDto problemDto) {
        ProblemDto problem = problemService.saveProblem(problemDto);
        if(problem == null) {
            return ResponseEntity.badRequest().body("Save problem error!");
        }
        return ResponseEntity.ok(problem);
    }

    @GetMapping("/problems")
    public List<ProblemDto> findAllProblem(@RequestParam("machineId") Integer machineId) {
        List<ProblemDto> listProblem = problemService.findAllByMachine(machineId);
        return listProblem;
    }

    @GetMapping("/problem")
    public ResponseEntity<?> findOneProblem(@RequestParam("id") Integer id) {
        ProblemDto problemDto = problemService.findOneProblem(id);
        if(problemDto == null) {
            return ResponseEntity.badRequest().body("Can not found problem!!");
        }
        return ResponseEntity.ok(problemDto);
    }

    @DeleteMapping("/problem")
    public ResponseEntity<?> deleteProblem(@RequestParam("id") Integer id) {
        String message = problemService.deleteProblem(id);
        if(message.equals("success")) {
            return ResponseEntity.ok(message);
        }
        return ResponseEntity.badRequest().body(message);
    }
}
