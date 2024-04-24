package com.hoangkimgia.cellphones.service;

import com.hoangkimgia.cellphones.entity.Role;

import java.util.List;

public interface RoleServiceImpl {
    Role createRole(Role theRole);
    List<Role> getRoles();
    void deleteRoles(Long rolesId);
    Role findByName(String name);
}
