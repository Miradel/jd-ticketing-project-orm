package com.cybertek.repository;

import com.cybertek.entity.Role;
import org.hibernate.secure.spi.JaccPermissionDeclarations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByDescription(String description);
}
