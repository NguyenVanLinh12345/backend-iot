package com.javatechie.repository;

import com.javatechie.entity.TypeEgg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TypeEggRepository extends JpaRepository<TypeEgg, Integer> {

    Optional<TypeEgg> findByName(String name);
    Boolean existsByName(String name);
}
