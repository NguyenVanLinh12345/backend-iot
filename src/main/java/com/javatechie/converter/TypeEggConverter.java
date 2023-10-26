package com.javatechie.converter;

import com.javatechie.dto.TypeEggDto;
import com.javatechie.entity.TypeEgg;
import org.modelmapper.ModelMapper;

public class TypeEggConverter {

    public static TypeEggDto toDto(TypeEgg typeEgg) {
        try {
            ModelMapper mapper = new ModelMapper();
            TypeEggDto typeEggDto = mapper.map(typeEgg, TypeEggDto.class);
            return typeEggDto;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
