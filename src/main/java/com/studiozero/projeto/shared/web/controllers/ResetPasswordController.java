package com.studiozero.projeto.shared.web.controllers;


import com.studiozero.projeto.shared.infrastructure.services.ResetPassword.ResetPasswordService;
import com.studiozero.projeto.shared.web.dtos.request.ForgotPasswordRequestDTO;
import com.studiozero.projeto.shared.web.dtos.request.ResetPasswordRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
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
