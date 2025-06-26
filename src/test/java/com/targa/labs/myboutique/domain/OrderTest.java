package com.targa.labs.myboutique.domain;

import com.targa.labs.myboutique.domain.enumeration.OrderStatus;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashSet;

import static org.junit.Assert.*;

public class OrderTest {

    private static final String STREET = "123 Test St";
    private static final String CITY = "Test City";
    private static final String STATE = "Test State";
    private static final String ZIP = "12345";
    private static final String COUNTRY = "USA";
    private static final BigDecimal TEST_PRICE = BigDecimal.valueOf(99.99);

    private Address createTestAddress() {
        return new Address(STREET, CITY, STATE, ZIP, COUNTRY);
    }

    private Order createTestOrder(Cart cart) {
        return new Order(
            TEST_PRICE,
            OrderStatus.CREATION,
            ZonedDateTime.now(),
            null,
            createTestAddress(),
            new HashSet<>(),
            cart
        );
    }

    @Test
    public void testOrderCreation() {
        Cart cart = new Cart();
        Order order = createTestOrder(cart);

        assertNotNull(order);
        assertEquals(TEST_PRICE, order.getTotalPrice());
        assertEquals(OrderStatus.CREATION, order.getStatus());
        assertNotNull(order.getShipped());
        assertNull(order.getPayment());
        assertEquals(createTestAddress(), order.getShipmentAddress());
        assertTrue(order.getOrderItems().isEmpty());
        assertEquals(cart, order.getCart());
    }

    @Test
    public void testOrderEquality() {
        Cart cart = new Cart();
        ZonedDateTime shippedDate = ZonedDateTime.now();
        
        Order order1 = new Order(
            TEST_PRICE,
            OrderStatus.CREATION,
            shippedDate,
            null,
            createTestAddress(),
            new HashSet<>(),
            cart
        );

        Order order2 = new Order(
            TEST_PRICE,
            OrderStatus.CREATION,
            shippedDate,
            null,
            createTestAddress(),
            new HashSet<>(),
            cart
        );

        assertEquals(order1, order2);
        assertEquals(order1.hashCode(), order2.hashCode());
    }

    @Test
    public void testAddOrderItem() {
        Order order = createTestOrder(new Cart());

        Product product = new Product(
            "Test Product",
            "Test Description",
            TEST_PRICE,
            null,
            0,
            new HashSet<>(),
            null
        );

        OrderItem orderItem = new OrderItem(2L, product, order);
        order.getOrderItems().add(orderItem);

        assertEquals(1, order.getOrderItems().size());
        assertTrue(order.getOrderItems().contains(orderItem));
    }
}