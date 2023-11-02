package com.javatechie.service;

import com.javatechie.dto.TypeEggDto;

import java.util.List;

public interface ITypeEggService {

    List<TypeEggDto> findAll();
    TypeEggDto findOne(Integer id);
    TypeEggDto updateTypeEgg(TypeEggDto typeEggDto);
    String deleteTypeEgg(Integer id);
    TypeEggDto addTypeEgg(TypeEggDto typeEggDto);
}
