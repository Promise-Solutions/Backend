package com.studiozero.projeto.infrastructure.configs.usecases;

import com.studiozero.projeto.infrastructure.repositories.Implements.ProductRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.studiozero.projeto.domain.repositories.ProductRepository;
import com.studiozero.projeto.application.usecases.product.GetProductUseCase;
import com.studiozero.projeto.application.usecases.product.CreateProductUseCase;
import com.studiozero.projeto.application.usecases.product.ListProductsUseCase;
import com.studiozero.projeto.application.usecases.product.UpdateProductUseCase;
import com.studiozero.projeto.application.usecases.product.DeleteProductUseCase;

@Configuration
public class ProductUseCaseConfig {
    @Bean
    public GetProductUseCase getProductUseCase(ProductRepositoryImpl productRepository) {
        return new GetProductUseCase(productRepository);
    }

    @Bean
    public CreateProductUseCase createProductUseCase(ProductRepositoryImpl productRepository) {
        return new CreateProductUseCase(productRepository);
    }

    @Bean
    public ListProductsUseCase listProductsUseCase(ProductRepositoryImpl productRepository) {
        return new ListProductsUseCase(productRepository);
    }

    @Bean
    public UpdateProductUseCase updateProductUseCase(ProductRepositoryImpl productRepository) {
        return new UpdateProductUseCase(productRepository);
    }

    @Bean
    public DeleteProductUseCase deleteProductUseCase(ProductRepositoryImpl productRepository) {
        return new DeleteProductUseCase(productRepository);
    }
}

