package com.studiozero.projeto.application.usecases;

import com.studiozero.projeto.application.usecases.product.*;
import com.studiozero.projeto.domain.entities.Product;
import com.studiozero.projeto.domain.repositories.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductUseCasesTest {

    @Mock
    private ProductRepository productRepository;

    @Nested
    class CreateProductUseCaseTests {
        private CreateProductUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new CreateProductUseCase(productRepository);
        }

        @Test
        void shouldCreateProductSuccessfully() {
            Product product = new Product();
            product.setId(1);
            product.setName("Test Product");
            product.setClientValue(100.0);
            product.setInternalValue(50.0);

            Product result = useCase.execute(product);

            assertNotNull(result);
            assertEquals(1, result.getId());
            assertEquals("Test Product", result.getName());
            assertEquals(100.0, result.getClientValue());
            assertEquals(50.0, result.getInternalValue());
            verify(productRepository, times(1)).save(product);
        }

        @Test
        void shouldThrowExceptionWhenProductIsNull() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(null));

            assertEquals("Produto inválido", exception.getMessage());
            verify(productRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenClientValueIsNull() {
            Product product = new Product();
            product.setName("Test Product");
            product.setClientValue(null);
            product.setInternalValue(50.0);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(product));

            assertEquals("Client value should be greater than zero", exception.getMessage());
            verify(productRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenClientValueIsZero() {
            Product product = new Product();
            product.setName("Test Product");
            product.setClientValue(0.0);
            product.setInternalValue(50.0);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(product));

            assertEquals("Client value should be greater than zero", exception.getMessage());
            verify(productRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenClientValueIsNegative() {
            Product product = new Product();
            product.setName("Test Product");
            product.setClientValue(-10.0);
            product.setInternalValue(50.0);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(product));

            assertEquals("Client value should be greater than zero", exception.getMessage());
            verify(productRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenInternalValueIsNull() {
            Product product = new Product();
            product.setName("Test Product");
            product.setClientValue(100.0);
            product.setInternalValue(null);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(product));

            assertEquals("Internal value should be greater than zero", exception.getMessage());
            verify(productRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenInternalValueIsZero() {
            Product product = new Product();
            product.setName("Test Product");
            product.setClientValue(100.0);
            product.setInternalValue(0.0);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(product));

            assertEquals("Internal value should be greater than zero", exception.getMessage());
            verify(productRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenInternalValueIsNegative() {
            Product product = new Product();
            product.setName("Test Product");
            product.setClientValue(100.0);
            product.setInternalValue(-5.0);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(product));

            assertEquals("Internal value should be greater than zero", exception.getMessage());
            verify(productRepository, never()).save(any());
        }

        @Test
        void shouldValidateClientValueBeforeInternalValue() {
            Product product = new Product();
            product.setName("Test Product");
            product.setClientValue(0.0);
            product.setInternalValue(0.0);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(product));

            assertEquals("Client value should be greater than zero", exception.getMessage());
        }

        @Test
        void shouldReturnSameProductInstanceAfterSave() {
            Product product = new Product();
            product.setName("Test Product");
            product.setClientValue(100.0);
            product.setInternalValue(50.0);

            Product result = useCase.execute(product);

            assertSame(product, result);
        }
    }

    @Nested
    class DeleteProductUseCaseTests {
        private DeleteProductUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new DeleteProductUseCase(productRepository);
        }

        @Test
        void shouldDeleteProductSuccessfully() {
            Integer productId = 1;
            Product product = new Product();
            product.setId(productId);
            product.setName("Test Product");

            when(productRepository.findById(productId)).thenReturn(product);

            useCase.execute(productId);

            verify(productRepository, times(1)).findById(productId);
            verify(productRepository, times(1)).deleteById(productId);
        }

        @Test
        void shouldThrowExceptionWhenProductNotFound() {
            Integer productId = 999;

            when(productRepository.findById(productId)).thenReturn(null);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(productId));

            assertEquals("Produto não encontrado", exception.getMessage());
            verify(productRepository, times(1)).findById(productId);
            verify(productRepository, never()).deleteById(any());
        }

        @Test
        void shouldCheckProductExistenceBeforeDeleting() {
            Integer productId = 1;

            when(productRepository.findById(productId)).thenReturn(null);

            assertThrows(IllegalArgumentException.class, () -> useCase.execute(productId));

            verify(productRepository, never()).deleteById(any());
        }
    }

    @Nested
    class GetProductUseCaseTests {
        private GetProductUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new GetProductUseCase(productRepository);
        }

        @Test
        void shouldReturnProductWhenFound() {
            Integer productId = 1;
            Product expectedProduct = new Product();
            expectedProduct.setId(productId);
            expectedProduct.setName("Test Product");
            expectedProduct.setClientValue(100.0);
            expectedProduct.setInternalValue(50.0);

            when(productRepository.findById(productId)).thenReturn(expectedProduct);

            Product result = useCase.execute(productId);

            assertNotNull(result);
            assertEquals(productId, result.getId());
            assertEquals("Test Product", result.getName());
            assertEquals(100.0, result.getClientValue());
            assertEquals(50.0, result.getInternalValue());
            verify(productRepository, times(1)).findById(productId);
        }

        @Test
        void shouldThrowExceptionWhenProductNotFound() {
            Integer productId = 999;

            when(productRepository.findById(productId)).thenReturn(null);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(productId));

            assertEquals("Produto não encontrado", exception.getMessage());
            verify(productRepository, times(1)).findById(productId);
        }

        @Test
        void shouldReturnSameProductInstanceFromRepository() {
            Integer productId = 1;
            Product expectedProduct = new Product();
            expectedProduct.setId(productId);

            when(productRepository.findById(productId)).thenReturn(expectedProduct);

            Product result = useCase.execute(productId);

            assertSame(expectedProduct, result);
        }
    }

    @Nested
    class ListProductsUseCaseTests {
        private ListProductsUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new ListProductsUseCase(productRepository);
        }

        @Test
        void shouldReturnListOfProducts() {
            Product product1 = new Product();
            product1.setId(1);
            product1.setName("Product 1");
            product1.setClientValue(100.0);

            Product product2 = new Product();
            product2.setId(2);
            product2.setName("Product 2");
            product2.setClientValue(200.0);

            List<Product> expectedProducts = Arrays.asList(product1, product2);

            when(productRepository.findAll()).thenReturn(expectedProducts);

            List<Product> result = useCase.execute();

            assertNotNull(result);
            assertEquals(2, result.size());
            assertEquals("Product 1", result.get(0).getName());
            assertEquals("Product 2", result.get(1).getName());
            assertEquals(100.0, result.get(0).getClientValue());
            assertEquals(200.0, result.get(1).getClientValue());
            verify(productRepository, times(1)).findAll();
        }

        @Test
        void shouldReturnEmptyListWhenNoProductsExist() {
            when(productRepository.findAll()).thenReturn(Collections.emptyList());

            List<Product> result = useCase.execute();

            assertNotNull(result);
            assertTrue(result.isEmpty());
            assertEquals(0, result.size());
            verify(productRepository, times(1)).findAll();
        }

        @Test
        void shouldReturnSameListInstanceFromRepository() {
            List<Product> expectedList = Arrays.asList(new Product(), new Product());

            when(productRepository.findAll()).thenReturn(expectedList);

            List<Product> result = useCase.execute();

            assertSame(expectedList, result);
            verify(productRepository, times(1)).findAll();
        }

        @Test
        void shouldCallRepositoryFindAllExactlyOnce() {
            when(productRepository.findAll()).thenReturn(Collections.emptyList());

            useCase.execute();

            verify(productRepository, times(1)).findAll();
            verifyNoMoreInteractions(productRepository);
        }
    }

    @Nested
    class UpdateProductUseCaseTests {
        private UpdateProductUseCase useCase;

        @BeforeEach
        void setUp() {
            useCase = new UpdateProductUseCase(productRepository);
        }

        @Test
        void shouldUpdateProductSuccessfully() {
            Integer productId = 1;

            Product existing = new Product();
            existing.setId(productId);
            existing.setName("Old Product");
            existing.setClientValue(100.0);
            existing.setInternalValue(50.0);

            Product updated = new Product();
            updated.setId(productId);
            updated.setName("New Product");
            updated.setClientValue(150.0);
            updated.setInternalValue(75.0);

            when(productRepository.findById(productId)).thenReturn(existing);

            Product result = useCase.execute(updated);

            assertNotNull(result);
            assertEquals(productId, result.getId());
            assertEquals("New Product", result.getName());
            assertEquals(150.0, result.getClientValue());
            assertEquals(75.0, result.getInternalValue());
            verify(productRepository, times(1)).findById(productId);
            verify(productRepository, times(1)).save(updated);
        }

        @Test
        void shouldThrowExceptionWhenProductIsNull() {
            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(null));

            assertEquals("Produto inválido", exception.getMessage());
            verify(productRepository, never()).findById(any());
            verify(productRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenIdIsNull() {
            Product updated = new Product();
            updated.setName("Test Product");
            updated.setClientValue(100.0);
            updated.setInternalValue(50.0);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(updated));

            assertEquals("Produto inválido", exception.getMessage());
            verify(productRepository, never()).findById(any());
            verify(productRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenProductNotFound() {
            Integer productId = 999;
            Product updated = new Product();
            updated.setId(productId);
            updated.setName("Test Product");
            updated.setClientValue(100.0);
            updated.setInternalValue(50.0);

            when(productRepository.findById(productId)).thenReturn(null);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(updated));

            assertEquals("Produto não encontrado", exception.getMessage());
            verify(productRepository, times(1)).findById(productId);
            verify(productRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenClientValueIsNull() {
            Integer productId = 1;
            Product existing = new Product();
            existing.setId(productId);

            Product updated = new Product();
            updated.setId(productId);
            updated.setClientValue(null);
            updated.setInternalValue(50.0);

            when(productRepository.findById(productId)).thenReturn(existing);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(updated));

            assertEquals("Client value should not be negative", exception.getMessage());
            verify(productRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenClientValueIsNegative() {
            Integer productId = 1;
            Product existing = new Product();
            existing.setId(productId);

            Product updated = new Product();
            updated.setId(productId);
            updated.setClientValue(-10.0);
            updated.setInternalValue(50.0);

            when(productRepository.findById(productId)).thenReturn(existing);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(updated));

            assertEquals("Client value should not be negative", exception.getMessage());
            verify(productRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenInternalValueIsNull() {
            Integer productId = 1;
            Product existing = new Product();
            existing.setId(productId);

            Product updated = new Product();
            updated.setId(productId);
            updated.setClientValue(100.0);
            updated.setInternalValue(null);

            when(productRepository.findById(productId)).thenReturn(existing);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(updated));

            assertEquals("Internal value should not be negative", exception.getMessage());
            verify(productRepository, never()).save(any());
        }

        @Test
        void shouldThrowExceptionWhenInternalValueIsNegative() {
            Integer productId = 1;
            Product existing = new Product();
            existing.setId(productId);

            Product updated = new Product();
            updated.setId(productId);
            updated.setClientValue(100.0);
            updated.setInternalValue(-5.0);

            when(productRepository.findById(productId)).thenReturn(existing);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(updated));

            assertEquals("Internal value should not be negative", exception.getMessage());
            verify(productRepository, never()).save(any());
        }

        @Test
        void shouldReturnUpdatedProductInstance() {
            Integer productId = 1;
            Product existing = new Product();
            existing.setId(productId);

            Product updated = new Product();
            updated.setId(productId);
            updated.setName("Updated Product");
            updated.setClientValue(100.0);
            updated.setInternalValue(50.0);

            when(productRepository.findById(productId)).thenReturn(existing);

            Product result = useCase.execute(updated);

            assertSame(updated, result);
        }

        @Test
        void shouldValidateProductBeforeCheckingExistence() {
            assertThrows(IllegalArgumentException.class, () -> useCase.execute(null));

            verify(productRepository, never()).findById(any());
        }

        @Test
        void shouldCheckExistenceBeforeValidatingValues() {
            Integer productId = 1;
            Product updated = new Product();
            updated.setId(productId);
            updated.setClientValue(0.0);
            updated.setInternalValue(0.0);

            when(productRepository.findById(productId)).thenReturn(null);

            IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                    () -> useCase.execute(updated));

            assertEquals("Produto não encontrado", exception.getMessage());
        }
    }
}
