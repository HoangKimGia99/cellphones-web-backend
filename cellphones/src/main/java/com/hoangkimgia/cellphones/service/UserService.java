package com.hoangkimgia.cellphones.service;

import com.hoangkimgia.cellphones.entity.Role;
import com.hoangkimgia.cellphones.entity.User;
import com.hoangkimgia.cellphones.exception.AppException;
import com.hoangkimgia.cellphones.exception.ErrorCode;
import com.hoangkimgia.cellphones.exception.UserAlreadyExistsException;
import com.hoangkimgia.cellphones.repository.RoleRepository;
import com.hoangkimgia.cellphones.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)

public class UserService implements UserServiceImpl{
    final UserRepository userRepository;
    final RoleRepository roleRepository;

    @Override
    public void createUser(User user) {
        /*Kiểm tra người dùng có tồn tại không nếu có thì báo lỗi đã tồn tại không thì lưu lại*/
        if(userRepository.existsByEmail(user.getEmail()))
            throw new AppException(ErrorCode.EMAIL_EXISTED);
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singletonList(userRole));
        userRepository.save(user);
    }
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new UserAlreadyExistsException("User not found"));
            userRepository.deleteById(userId);
    }

    @Override
    public User getUserId(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new AppException(ErrorCode.USER_NOT_EXISTED));
        userRepository.findById(userId);
        return user;
    }
}
