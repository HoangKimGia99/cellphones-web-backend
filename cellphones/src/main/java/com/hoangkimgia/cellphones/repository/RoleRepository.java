package com.hoangkimgia.cellphones.repository;

import com.hoangkimgia.cellphones.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    boolean existsByName(String role);
    Optional<Role> findByName(String role);
}
