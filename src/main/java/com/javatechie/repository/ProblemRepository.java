package com.javatechie.repository;

import com.javatechie.entity.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProblemRepository extends JpaRepository<Problem, Integer> {
    List<Problem> findAllByMachineId(Integer machineId);
}
