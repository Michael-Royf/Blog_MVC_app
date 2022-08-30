package com.michael.blog_mvc.service;

import com.michael.blog_mvc.entity.User;
import com.michael.blog_mvc.payload.request.RegistrationRequest;

public interface UserService {

    void saveUser(RegistrationRequest request);

    User findByEmail(String email);
}
