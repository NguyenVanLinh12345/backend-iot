package com.javatechie.repository;

import com.javatechie.entity.TypeEgg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeEggRepository extends JpaRepository<TypeEgg, Integer> {
}
