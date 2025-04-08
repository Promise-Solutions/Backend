package com.studiozero.projeto.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/command-products")
@Tag(name = "Command Products", description = "Endpoints for Command Product Management")
public class CommandProductController {

    @Autowired
    private CommandProductService commandProductService;
}
