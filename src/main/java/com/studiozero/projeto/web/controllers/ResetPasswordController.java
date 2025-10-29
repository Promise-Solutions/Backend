package com.studiozero.projeto.web.controllers;


import com.studiozero.projeto.application.usecases.employee.UpdateEmployeeUseCase;
import com.studiozero.projeto.domain.entities.Employee;
import com.studiozero.projeto.infrastructure.repositories.Implements.EmployeeRepositoryImpl;
import com.studiozero.projeto.infrastructure.services.ResetPassword.ResetPasswordService;
import com.studiozero.projeto.infrastructure.services.ResetPassword.TokenResetPasswordService;
import com.studiozero.projeto.web.dtos.request.ForgotPasswordRequestDTO;
import com.studiozero.projeto.web.dtos.request.ResetPasswordRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class ResetPasswordController {

    private ResetPasswordService service;

    public ResetPasswordController(ResetPasswordService resetPasswordService) {
        this.service = resetPasswordService;
    }

    @PostMapping("/forgot-password")
    public ResponseEntity forgotPassword(@RequestBody @Valid ForgotPasswordRequestDTO request) {
        service.requestReset(request.getEmail());
        return ResponseEntity.ok().body("Se o e-mail existir, enviaremos instruções para redefinição.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity resetPassword( @RequestBody @Valid ResetPasswordRequest request) {
        service.resetPassword(request.getToken(), request.getNewPassword());
        return ResponseEntity.ok().body("Senha alterada com sucesso.");
    }

}
