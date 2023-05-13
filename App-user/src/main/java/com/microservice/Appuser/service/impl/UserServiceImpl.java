package com.microservice.Appuser.service.impl;

import com.microservice.Appuser.model.AppUser;
import com.microservice.Appuser.model.UserRole;
import com.microservice.Appuser.repository.RoleRepository;
import com.microservice.Appuser.repository.UserRepository;
import com.microservice.Appuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public AppUser createUser(AppUser user, Set<UserRole> userRoles) throws Exception {

        AppUser local = userRepository.findByUsername(user.getUsername());
        AppUser local1 = userRepository.findByEmail(user.getEmail());


        if (local != null) {
            throw new Exception("username is already exit");
        } else if (local1 != null) {
            throw new Exception("Email is already exit");
        } else {
            for (UserRole userRole : userRoles) {
                roleRepository.save(userRole.getRole());
            }
            user.getUserRoles().addAll(userRoles);
            local = userRepository.save(user);
        }

        return local;
    }

    @Override
    public AppUser getSingleUser(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public AppUser updateUser(AppUser user) {
        return userRepository.save(user);
    }

    @Override
    public List<AppUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public long getTotalUser() {
        return userRepository.count();
    }
}
