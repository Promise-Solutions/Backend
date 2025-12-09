package com.studiozero.projeto.web.controllers;

import com.studiozero.projeto.application.usecases.product.CreateProductUseCase;
import com.studiozero.projeto.application.usecases.product.DeleteProductUseCase;
import com.studiozero.projeto.application.usecases.product.GetProductUseCase;
import com.studiozero.projeto.application.usecases.product.ListProductsUseCase;
import com.studiozero.projeto.application.usecases.product.UpdateProductUseCase;
import com.studiozero.projeto.domain.entities.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductEntityControllerTest {
    private MockMvc mockMvc;

    @BeforeEach
    void setup() {
        CreateProductUseCase create = new CreateProductUseCase(null) {
            @Override
            public Product execute(Product product) { return product; }
        };

        GetProductUseCase get = new GetProductUseCase(null) {
            @Override
            public Product execute(Integer id) { throw new IllegalArgumentException("not found"); }
        };

        UpdateProductUseCase update = new UpdateProductUseCase(null) {
            @Override
            public Product execute(Product product) { return product; }
        };

        DeleteProductUseCase delete = new DeleteProductUseCase(null) {
            @Override
            public void execute(Integer id) { }
        };

        ListProductsUseCase list = new ListProductsUseCase(null) {
            @Override
            public java.util.List<Product> execute() { return List.of(); }
        };

        ProductController controller = new ProductController(create, get, update, delete, list);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void testListAllProducts() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isNoContent());
    }
}
