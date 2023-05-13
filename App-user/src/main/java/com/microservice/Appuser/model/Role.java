package com.microservice.Appuser.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "role")
public class Role {
    @Id
    private Long roleId;
    private String roleName;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "role")
    private Set<UserRole> userRoles = new HashSet<>();
}
