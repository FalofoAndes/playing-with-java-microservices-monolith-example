package com.targa.labs.myboutique.service;

import com.targa.labs.myboutique.domain.Category;
import com.targa.labs.myboutique.domain.Product;
import com.targa.labs.myboutique.domain.Review;
import com.targa.labs.myboutique.domain.enumeration.ProductStatus;
import com.targa.labs.myboutique.repository.CategoryRepository;
import com.targa.labs.myboutique.repository.ProductRepository;
import com.targa.labs.myboutique.web.dto.ProductDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    private static final String TEST_PRODUCT_NAME = "Test Product";
    private static final String TEST_DESCRIPTION = "Description";
    private static final BigDecimal TEST_PRICE = BigDecimal.valueOf(99.99);

    @Mock
    private ProductRepository productRepository;

    @Mock
    private CategoryRepository categoryRepository;

    private ProductService productService;

    @Before
    public void setUp() {
        productService = new ProductService(productRepository, categoryRepository);
    }

    private Product createTestProduct() {
        return new Product(
            TEST_PRODUCT_NAME,
            TEST_DESCRIPTION,
            TEST_PRICE,
            ProductStatus.AVAILABLE,
            0,
            new HashSet<>(),
            new Category()
        );
    }

    @Test
    public void testFindAll() {
        Product product1 = new Product("Product 1", "Description 1", TEST_PRICE, ProductStatus.AVAILABLE, 0, new HashSet<>(), new Category());
        Product product2 = new Product("Product 2", "Description 2", BigDecimal.valueOf(149.99), ProductStatus.AVAILABLE, 0, new HashSet<>(),  new Category());
        
        when(productRepository.findAll()).thenReturn(Arrays.asList(product1, product2));

        List<ProductDto> result = productService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(productRepository).findAll();
    }

    @Test
    public void testFindById() {
        Product product = createTestProduct();
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        ProductDto result = productService.findById(1L);

        assertNotNull(result);
        assertEquals(TEST_PRODUCT_NAME, result.getName());
        verify(productRepository).findById(1L);
    }

    @Test
    public void testCreate() {
        Category category = new Category("Test Category", "Test Description", new HashSet<>());
        Product product = createTestProduct();
        product.setCategory(category);
        
        when(categoryRepository.findById(any())).thenReturn(Optional.of(category));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductDto productDto = new ProductDto(null, TEST_PRODUCT_NAME, TEST_DESCRIPTION, TEST_PRICE, "AVAILABLE", 0, new HashSet<>(), 1L);
        ProductDto result = productService.create(productDto);

        assertNotNull(result);
        assertEquals(productDto.getName(), result.getName());
        verify(productRepository).save(any(Product.class));
        verify(categoryRepository).findById(any());
    }

    @Test
    public void testAddReview() {
        Product product = createTestProduct();
        Review review = new Review("Great", "Excellent product", 5L);
        
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));
        when(productRepository.saveAndFlush(any(Product.class))).thenReturn(product);

        ProductDto result = productService.addReview(1L, review);

        assertNotNull(result);
        verify(productRepository).findById(1L);
        verify(productRepository).saveAndFlush(any(Product.class));
    }

    @Test(expected = IllegalStateException.class)
    public void testAddReviewToNonExistentProduct() {
        Review review = new Review("Great", "Excellent product", 5L);
        when(productRepository.findById(1L)).thenReturn(Optional.empty());

        productService.addReview(1L, review);
    }

    @Test
    public void testDelete() {
        productService.delete(1L);
        verify(productRepository).deleteById(1L);
    }
}