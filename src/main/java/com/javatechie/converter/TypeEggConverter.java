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

    public static TypeEgg toEntity(TypeEggDto typeEgg) {
        try {
            ModelMapper mapper = new ModelMapper();
            TypeEgg entity = mapper.map(typeEgg, TypeEgg.class);
            return entity;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static TypeEgg toEntity(TypeEgg typeEgg, TypeEggDto typeEggDto) {
        try {
           if(typeEggDto.getHumidity() != null) {
               typeEgg.setHumidity(typeEgg.getHumidity());
           }
           if(typeEggDto.getTemperature() != null) {
               typeEgg.setTemperature(typeEggDto.getTemperature());
           }
           if(typeEggDto.getNumberHatch() != null) {
               typeEgg.setNumberHatch(typeEggDto.getNumberHatch());
           }
           if(typeEggDto.getNumberTurn() != null) {
               typeEgg.setNumberTurn(typeEggDto.getNumberTurn());
           }
           if(typeEggDto.getDescription() != null) {
               typeEgg.setDescription(typeEggDto.getDescription());
           }
           if(typeEggDto.getName() != null) {
               typeEgg.setName(typeEggDto.getName());
           }
           return typeEgg;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
