package com.javatechie.converter;

import com.javatechie.dto.UserDto;
import com.javatechie.entity.User;
import org.modelmapper.ModelMapper;

public class UserConverter {

    public static User toEntity(UserDto user) {
        try {
            ModelMapper mapper = new ModelMapper();
            User userEntity = mapper.map(user, User.class);
            return userEntity;
        }
        catch (Exception e) {
            System.out.println("Convert from userDto to userEntity error!!");
            return null;
        }
    }

    public static UserDto toDto(User user) {
        try {
            ModelMapper mapper = new ModelMapper();
            UserDto userDto = mapper.map(user, UserDto.class);
            return userDto;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static User toEntity(User user, UserDto userDto) {
        try {
            if(userDto.getName() != null) {
                user.setName(userDto.getName());
            }
            if(userDto.getDescription() != null) {
                user.setDescription(userDto.getDescription());
            }
            return user;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
