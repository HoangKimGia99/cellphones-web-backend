package com.hoangkimgia.cellphones.service;

import com.hoangkimgia.cellphones.entity.Role;
import com.hoangkimgia.cellphones.exception.AppException;
import com.hoangkimgia.cellphones.exception.ErrorCode;
import com.hoangkimgia.cellphones.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class RoleService implements RoleServiceImpl{
    RoleRepository roleRepository;
    @Override
    public Role createRole(Role theRole) {
        /*Kiểm tra role có tồn tại không nếu có thì báo lỗi đã tồn tại không thì lưu lại*/
        String roleName = "ROLE_"+theRole.getName().toUpperCase();
        Role role = new Role(roleName);
        if(roleRepository.existsByName(role.getName()))
            throw new AppException(ErrorCode.NAME_EXISTED);
        return roleRepository.save(role);
    }

    @Override
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void deleteRoles(Long rolesId) {
        Role role= roleRepository.findById(rolesId)
                .orElseThrow(()->new AppException(ErrorCode.USER_NOT_EXISTED));
        roleRepository.deleteById(rolesId);
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name).get();
    }

}
