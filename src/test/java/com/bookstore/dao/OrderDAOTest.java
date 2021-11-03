package com.bookstore.dao;

import com.bookstore.entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OrderDAOTest {
    private static OrderDAO orderDAO;
    private static BookDAO bookDAO;
    @BeforeEach
    public void setUp() {
        bookDAO = new BookDAO();
        orderDAO = new OrderDAO();
    }

    @Test
    void testCreateOrder() {
        BookOrder order = new BookOrder();
        Customer customer = new Customer();
        customer.setCustomerId(9);
        order.setCustomerByCustomerId(customer);
        order.setRecipientName("Huynh Thanh Dung");
        order.setRecipientPhone("1234567890");
        order.setStatus("Processing");
        order.setTotal(1.4f);
        order.setShippingAddress("123 Tran Phu");

        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail;
        orderDetail = new OrderDetail();
        orderDetail.setQuantity(1);
        orderDetail.setSubtotal(1.4f);
        Book book = bookDAO.get(25);
        orderDetail.setBookByBookId(book);
        orderDetail.setBookOrderByOrderId(order);

        OrderDetailPK orderDetailPK = new OrderDetailPK();
        orderDetailPK.setBook(book);
        orderDetailPK.setBookOrder(order);

        orderDetail.setId(orderDetailPK);

        orderDetails.add(orderDetail);

        order.setOrderDetailsByOrderId(orderDetails);
        BookOrder savedOrder = orderDAO.create(order);
        System.out.println(savedOrder.getOrderId());
        assertNotNull(savedOrder);
    }

    @AfterEach
    void tearDown() {
        orderDAO.close();
    }
}