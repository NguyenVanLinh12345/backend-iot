package com.javatechie.service.impl;

import com.javatechie.entity.UserInfo;
import com.javatechie.repository.UserInfoRepository;
import com.javatechie.service.IUserService;
import com.javatechie.util.CheckPassWord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addUser(UserInfo userInfo) {
        try {
            Boolean checkEmail = checkEmailExist(userInfo.getEmail());
            if(checkEmail) { // email đã tồn tại trong cơ sở dữ liệu
                return "Email already exists ";
            }
            // kiểm tra password có hợp lệ không
            Boolean checkPassword = CheckPassWord.isStrongPassword(userInfo.getPassword());
            if(!checkPassword) { // password không hợp lệ
                return "Password is not valid";
            }
            userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
            userInfoRepository.save(userInfo);
            return "success";
        }
        catch (Exception e) {
            System.out.println("Đăng ký tài khoản lỗi rồi!! (Line 20)");
            e.printStackTrace();
            return "Fail";
        }
    }

    // kiểm tra email có tồn tại không
    private Boolean checkEmailExist(String email) {
        return userInfoRepository.existsByEmail(email);
    }
}
