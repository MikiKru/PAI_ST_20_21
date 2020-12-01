package com.example.rest_api.repository;

import com.example.rest_api.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    // SELECT * FROM roles WHERE role_name = ?;
    Optional<Role> findFirstByRoleName(String roleName);
}
