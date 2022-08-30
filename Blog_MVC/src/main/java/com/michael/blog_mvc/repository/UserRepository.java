package com.michael.blog_mvc.repository;

import com.michael.blog_mvc.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User  findByEmail(String email);
}
