package com.bookstore.dao;

import com.bookstore.entity.Customer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerDAOTest {
    private static CustomerDAO customerDAO;
    @BeforeEach
    public void setUp() {
        customerDAO = new CustomerDAO();
    }
    @Test
    @Disabled
    public void testCreateCustomer(){
        Customer customer = new Customer();
        customer.setEmail("phichh16@gmail.com");
        customer.setFullName("dunghuynh");
        customer.setCity("HCM");
        customer.setCountry("VN");
        customer.setAddress("1 Vo Van Ngan");
        customer.setPassword("thanhdung99");
        customer.setPhone("1234567890");
        customer.setZipcode("90000");
        Customer savedCustomer = customerDAO.create(customer);
        assertTrue(savedCustomer.getCustomerId() > 0);
    }

    @Test
    public void testFindCustomer(){
        int customerId = 2;
        Customer customer = customerDAO.get(customerId);
        assertNotNull(customer);
    }

    @Test
    public void testUpdateCustomer(){
        int customerId = 2;
        Customer customer = customerDAO.get(customerId);
        customer.setZipcode("30000");
        Customer updatedCustomer = customerDAO.update(customer);
        assertTrue(updatedCustomer.getZipcode().equals(customer.getZipcode()));
    }
    @Test
    @Disabled
    public void testDeleteCustomer(){
        int customerId = 1;
        customerDAO.delete(customerId);
        Customer customer = customerDAO.get(customerId);
        assertNull(customer);
    }


    @AfterEach
    public void tearDown() {
        customerDAO.close();
    }
}