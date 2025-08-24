package com.studiozero.projeto.web.dtos.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.studiozero.projeto.domain.entities.Employee;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@JsonNaming(PropertyNamingStrategies.LowerCamelCaseStrategy.class)
public class EmployeeRequestDTO implements UserDetails {

    @NotBlank(message = "Name value is mandatory")
    private String name;

    @NotBlank(message = "Email value is mandatory")
    @Email
    private String email;

    @NotBlank(message = "Contact value is mandatory")
    @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}", message = "Contact must be in the format (XX) XXXXX-XXXX")
    private String contact;

    @NotNull(message = "Cpf value is mandatory")
    @CPF
    @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF must be in the format 000.000.000-00")
    private String cpf;

    @NotBlank(message = "Password value is mandatory")
    private String password;

    @NotNull(message = "Active value is mandatory")
    private Boolean active = true;

    public EmployeeRequestDTO() {
    }

    public EmployeeRequestDTO(Employee employee) {
        this.name = employee.getName();
        this.email = employee.getEmail();
        this.contact = employee.getContact();
        this.cpf = employee.getCpf();
        this.password = employee.getPassword();
        this.active = employee.getActive();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return email;
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
        return true;
    }

    public @NotBlank(message = "Name value is mandatory") String getName() {
        return name;
    }

    public void setName(@NotBlank(message = "Name value is mandatory") String name) {
        this.name = name;
    }

    public @NotBlank(message = "Email value is mandatory") @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email value is mandatory") @Email String email) {
        this.email = email;
    }

    public @NotBlank(message = "Contact value is mandatory") @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}", message = "Contact must be in the format (XX) XXXXX-XXXX") String getContact() {
        return contact;
    }

    public void setContact(@NotBlank(message = "Contact value is mandatory") @Pattern(regexp = "\\(\\d{2}\\) \\d{5}-\\d{4}", message = "Contact must be in the format (XX) XXXXX-XXXX") String contact) {
        this.contact = contact;
    }

    public @NotNull(message = "Cpf value is mandatory") @CPF @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF must be in the format 000.000.000-00") String getCpf() {
        return cpf;
    }

    public void setCpf(@NotNull(message = "Cpf value is mandatory") @CPF @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}", message = "CPF must be in the format 000.000.000-00") String cpf) {
        this.cpf = cpf;
    }

    @Override
    public @NotBlank(message = "Password value is mandatory") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank(message = "Password value is mandatory") String password) {
        this.password = password;
    }

    public @NotNull(message = "Active value is mandatory") Boolean getActive() {
        return active;
    }

    public void setActive(@NotNull(message = "Active value is mandatory") Boolean active) {
        this.active = active;
    }
}
