package com.targa.labs.myboutique.service;

import com.targa.labs.myboutique.domain.*;
import com.targa.labs.myboutique.domain.enumeration.OrderStatus;
import com.targa.labs.myboutique.repository.OrderRepository;
import com.targa.labs.myboutique.web.dto.OrderDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    private static final String STREET = "123 Test St";
    private static final String CITY = "Test City";
    private static final String STATE = "Test State";
    private static final String ZIP = "12345";
    private static final BigDecimal TEST_PRICE = BigDecimal.valueOf(99.99);
    private static final String COUNTRY = "USA";

    @Mock
    private OrderRepository orderRepository;

    private OrderService orderService;

    @Before
    public void setUp() {
        orderService = new OrderService(orderRepository);
    }

    private Address createTestAddress() {
        return new Address(STREET, CITY, STATE, ZIP, COUNTRY);
    }

    private Customer createTestCustomer() {
        Customer customer = new Customer("John", "Doe", "john@test.com", "1234567890", new HashSet<>(), true);
        customer.setId(1L);
        return customer;
    }

    private Cart createTestCart() {
        Cart cart = new Cart(createTestCustomer());
        cart.setId(1L);
        return cart;
    }

    private Order createTestOrder() {
        Order order = new Order(
            TEST_PRICE,
            OrderStatus.CREATION,
            ZonedDateTime.now(),
            new Payment(),
            createTestAddress(),
            new HashSet<>(),
            createTestCart()
        );
        order.setId(1L);
        return order;
    }

    @Test
    public void testFindAll() {
        Order testOrder = createTestOrder();
        when(orderRepository.findAll()).thenReturn(Arrays.asList(testOrder));

        List<OrderDto> result = orderService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(orderRepository).findAll();
    }

    @Test
    public void testFindById() {
        Order testOrder = createTestOrder();
        when(orderRepository.findById(1L)).thenReturn(Optional.of(testOrder));

        OrderDto result = orderService.findById(1L);

        assertNotNull(result);
        assertEquals(testOrder.getTotalPrice(), result.getTotalPrice());
        assertEquals(testOrder.getStatus().name(), result.getStatus());
        verify(orderRepository).findById(1L);
    }

    @Test
    public void testFindAllByUser() {
        Order testOrder = createTestOrder();
        when(orderRepository.findByCartCustomer_Id(1L))
            .thenReturn(Arrays.asList(testOrder));

        List<OrderDto> result = orderService.findAllByUser(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(orderRepository).findByCartCustomer_Id(1L);
    }

    @Test
    public void testCreateFromCart() {
        Order testOrder = createTestOrder();
        when(orderRepository.save(any(Order.class))).thenReturn(testOrder);

        Order result = orderService.create(createTestCart(), createTestAddress());

        assertNotNull(result);
        assertEquals(OrderStatus.CREATION, result.getStatus());
        assertEquals(createTestAddress(), result.getShipmentAddress());
        verify(orderRepository).save(any(Order.class));
    }

    @Test
    public void testDelete() {
        doNothing().when(orderRepository).deleteById(1L);
        
        orderService.delete(1L);
        
        verify(orderRepository).deleteById(1L);
    }
}