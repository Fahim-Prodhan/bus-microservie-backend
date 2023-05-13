package com.microservice.Appuser.repository;

import com.microservice.Appuser.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
