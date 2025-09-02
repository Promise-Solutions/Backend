package com.studiozero.projeto.catalog.infrastruture.usecase;

import com.studiozero.projeto.catalog.infrastruture.repository.ProductRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.studiozero.projeto.catalog.application.product.GetProductUseCase;
import com.studiozero.projeto.catalog.application.product.CreateProductUseCase;
import com.studiozero.projeto.catalog.application.product.ListProductsUseCase;
import com.studiozero.projeto.catalog.application.product.UpdateProductUseCase;
import com.studiozero.projeto.catalog.application.product.DeleteProductUseCase;

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

