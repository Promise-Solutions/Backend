package com.studiozero.projeto.infrastructure.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class EmployeeUserDetailsEntity implements UserDetails {
    private final EmployeeEntity employeeEntity;

    public EmployeeUserDetailsEntity(EmployeeEntity employeeEntity) {
        this.employeeEntity = employeeEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Retorne as authorities do funcionário, se houver. Aqui retorna vazio.
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return employeeEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return employeeEntity.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return Boolean.TRUE.equals(employeeEntity.getActive());
    }

    public EmployeeEntity getEmployee() {
        return employeeEntity;
    }
}