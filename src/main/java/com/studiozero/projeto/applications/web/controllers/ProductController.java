package com.studiozero.projeto.applications.web.controllers;

import com.studiozero.projeto.domain.services.ProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
@Tag(name = "Products", description = "Endpoints for Product Management")
public class ProductController {

    @Autowired
    private ProductService productService;
}
