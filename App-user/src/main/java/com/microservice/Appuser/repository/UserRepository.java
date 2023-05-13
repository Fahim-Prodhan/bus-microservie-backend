package com.microservice.Appuser.repository;

import com.microservice.Appuser.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
    AppUser findByEmail(String email);

    long count();
}
