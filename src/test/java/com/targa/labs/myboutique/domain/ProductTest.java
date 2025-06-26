package com.targa.labs.myboutique.domain;

import com.targa.labs.myboutique.domain.enumeration.ProductStatus;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.HashSet;

import static org.junit.Assert.*;

public class ProductTest {

    private static final String TEST_PRODUCT_NAME = "Test Product";
    private static final String TEST_DESCRIPTION = "Test Description";
    private static final BigDecimal TEST_PRICE = BigDecimal.valueOf(99.99);

    private Product createTestProduct() {
        return new Product(
            TEST_PRODUCT_NAME,
            TEST_DESCRIPTION,
            TEST_PRICE,
            ProductStatus.AVAILABLE,
            0,
            new HashSet<>(),
            null
        );
    }

    @Test
    public void testProductCreation() {
        Product product = createTestProduct();

        assertNotNull(product);
        assertEquals(TEST_PRODUCT_NAME, product.getName());
        assertEquals(TEST_DESCRIPTION, product.getDescription());
        assertEquals(TEST_PRICE, product.getPrice());
        assertEquals(ProductStatus.AVAILABLE, product.getStatus());
        assertEquals(Integer.valueOf(0), product.getSalesCounter());
        assertTrue(product.getReviews().isEmpty());
        assertNull(product.getCategory());
    }

    @Test
    public void testProductEquality() {
        Product product1 = createTestProduct();
        Product product2 = createTestProduct();

        assertEquals(product1, product2);
        assertEquals(product1.hashCode(), product2.hashCode());
    }

    @Test
    public void testAddReview() {
        Product product = createTestProduct();
        Review review = new Review("Great", "Excellent product", 5L);
        product.getReviews().add(review);

        assertEquals(1, product.getReviews().size());
        assertTrue(product.getReviews().contains(review));
    }
}