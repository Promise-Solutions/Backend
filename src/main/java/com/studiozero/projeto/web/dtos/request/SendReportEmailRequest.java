package com.studiozero.projeto.web.dtos.request;

import java.util.List;

public class SendReportEmailRequest {
    private List<String> destinatarios;

    public List<String> getDestinatarios() {
        return destinatarios;
    }

    public void setDestinatarios(List<String> destinatarios) {
        this.destinatarios = destinatarios;
    }
}

