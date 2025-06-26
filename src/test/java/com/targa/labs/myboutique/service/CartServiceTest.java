package com.targa.labs.myboutique.service;

import com.targa.labs.myboutique.domain.Cart;
import com.targa.labs.myboutique.domain.Customer;
import com.targa.labs.myboutique.domain.enumeration.CartStatus;
import com.targa.labs.myboutique.repository.CartRepository;
import com.targa.labs.myboutique.repository.CustomerRepository;
import com.targa.labs.myboutique.web.dto.CartDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private OrderService orderService;

    private CartService cartService;
    private Customer testCustomer;
    private Cart testCart;

    @Before
    public void setUp() {
        cartService = new CartService(cartRepository, customerRepository, orderService);
        
        testCustomer = new Customer("John", "Doe", "john@test.com", "1234567890", new HashSet<>(), true);
        testCustomer.setId(1L);
        testCart = new Cart(testCustomer, CartStatus.NEW);
        testCart.setId(1L);
    }

    @Test
    public void testFindAll() {
        when(cartRepository.findAll()).thenReturn(Arrays.asList(testCart));

        List<CartDto> result = cartService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testCart.getId(), result.get(0).getId());
        verify(cartRepository).findAll();
    }

    @Test
    public void testFindAllActiveCarts() {
        when(cartRepository.findByStatus(CartStatus.NEW)).thenReturn(Arrays.asList(testCart));

        List<CartDto> result = cartService.findAllActiveCarts();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(CartStatus.NEW.name(), result.get(0).getStatus());
        verify(cartRepository).findByStatus(CartStatus.NEW);
    }

    @Test
    public void testCreate() {
        when(customerRepository.findById(1L)).thenReturn(Optional.of(testCustomer));
        when(cartRepository.save(any(Cart.class))).thenReturn(testCart);

        Cart result = cartService.create(1L);

        assertNotNull(result);
        assertEquals(testCustomer.getId(), result.getCustomer().getId());
        assertEquals(CartStatus.NEW, result.getStatus());
        verify(customerRepository).findById(1L);
        verify(cartRepository).save(any(Cart.class));
    }

    @Test
    public void testGetActiveCart() {
        when(cartRepository.findByStatusAndCustomerId(CartStatus.NEW, 1L))
            .thenReturn(Arrays.asList(testCart));

        CartDto result = cartService.getActiveCart(1L);

        assertNotNull(result);
        assertEquals(testCart.getId(), result.getId());
        assertEquals(CartStatus.NEW.name(), result.getStatus());
        verify(cartRepository).findByStatusAndCustomerId(CartStatus.NEW, 1L);
    }
    
    @Test(expected = IllegalStateException.class)
    public void testCreateWithNonExistentCustomer() {
        when(customerRepository.findById(1L)).thenReturn(Optional.empty());
        
        cartService.create(1L);
    }

    @Test
    public void testFindById() {
        when(cartRepository.findById(1L)).thenReturn(Optional.of(testCart));

        CartDto result = cartService.findById(1L);

        assertNotNull(result);
        assertEquals(testCart.getId(), result.getId());
        assertEquals(testCart.getStatus().name(), result.getStatus());
        verify(cartRepository).findById(1L);
    }
}