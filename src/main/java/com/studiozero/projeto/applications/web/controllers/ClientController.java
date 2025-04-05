package com.studiozero.projeto.applications.web.controllers;

import com.studiozero.projeto.domain.services.ClientService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clients")
@Tag(name = "Clients", description = "Endpoints for Client Management")
public class ClientController {

    @Autowired
    private ClientService clientService;
}
