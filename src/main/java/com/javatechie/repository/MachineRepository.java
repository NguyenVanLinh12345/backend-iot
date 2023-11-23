package com.javatechie.repository;

import com.javatechie.entity.Machine;
import org.apache.el.parser.BooleanNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.OptionalLong;

@Repository
public interface MachineRepository extends JpaRepository<Machine, Integer> {
    List<Machine> findAllByUserId(Integer userId);
    Boolean  existsByName(String name);
    Optional<Machine> findByName(String name);
}
