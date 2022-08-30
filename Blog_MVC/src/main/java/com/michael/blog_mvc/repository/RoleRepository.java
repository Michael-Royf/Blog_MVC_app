package com.michael.blog_mvc.repository;

import com.michael.blog_mvc.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;





public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}