package com.studiozero.projeto.domain.services.impl;

import com.studiozero.projeto.domain.repositories.ProductRepository;
import com.studiozero.projeto.domain.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
}
