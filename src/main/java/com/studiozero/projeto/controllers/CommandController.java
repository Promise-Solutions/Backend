package com.studiozero.projeto.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/commands")
@Tag(name = "Commands", description = "Endpoints for Command Management")
public class CommandController {

//    @Autowired
//    private CommandService commandService;
}
