package com.javatechie.service;

import com.javatechie.dto.UserDto;
import com.javatechie.entity.User;

import java.util.List;

public interface IUserService {
    // thêm mới User(admin hoặc employee)
    String addUser(UserDto user, Integer role);
    // lấy ra toàn bộ user có trong database với role là EMPLOYEE
    List<UserDto> findAllUser();
    // lấy ra 1 user trong database(role = EMPLOYEE)
    UserDto findOneUser(Integer id);
    UserDto updateUser(UserDto userDto);

    String deleteUser(Integer id);
}
