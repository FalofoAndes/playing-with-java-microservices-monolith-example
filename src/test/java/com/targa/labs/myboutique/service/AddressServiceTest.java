package com.targa.labs.myboutique.service;

import com.targa.labs.myboutique.domain.Address;
import com.targa.labs.myboutique.web.dto.AddressDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

class AddressServiceTest {

    @Test
    void testMapToDto() {
        // Arrange
        Address address = new Address(
                "123 Main St",
                "Apt 4B",
                "Springfield",
                "12345",
                "USA"
        );

        // Act
        AddressDto addressDto = AddressService.mapToDto(address);

        // Assert
        assertNotNull(addressDto);
        assertEquals("123 Main St", addressDto.getAddress1());
        assertEquals("Apt 4B", addressDto.getAddress2());
        assertEquals("Springfield", addressDto.getCity());
        assertEquals("12345", addressDto.getPostcode());
        assertEquals("USA", addressDto.getCountry());
    }

    @Test
    void testCreateFromDto() {
        // Arrange
        AddressDto addressDto = new AddressDto(
                "123 Main St",
                "Apt 4B",
                "Springfield",
                "12345",
                "USA"
        );

        // Act
        Address address = AddressService.createFromDto(addressDto);

        // Assert
        Assertions.assertNotNull(address);
        assertEquals("123 Main St", address.getAddress1());
        assertEquals("Apt 4B", address.getAddress2());
        assertEquals("Springfield", address.getCity());
        assertEquals("12345", address.getPostcode());
        assertEquals("USA", address.getCountry());
    }
}