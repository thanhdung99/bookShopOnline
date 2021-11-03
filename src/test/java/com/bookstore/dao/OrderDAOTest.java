package com.bookstore.dao;

import com.bookstore.entity.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

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
    @Test
    public void testListAllOrder(){
        Collection<BookOrder> bookOrders = orderDAO.listAll();
        BookOrder bookOrder = bookOrders.stream().filter( x-> x.getOrderId() == 795).findFirst().orElse(null);
        System.out.println(bookOrder.getOrderId());
        bookOrder.getOrderDetailsByOrderId();
//        assertTrue(bookOrders.size() > 0);
        assertTrue(bookOrder.getOrderDetailsByOrderId().size()>0);
//        assertNotNull(bookOrder);
    }
    @Test
    public void testFindOrder(){
        BookOrder order = orderDAO.get(725);
        System.out.println(order.getCustomerByCustomerId().getFullName());
        assertNotNull(order);
    }
    @Test
    public void testUpdateOrder(){
        BookOrder order = orderDAO.get(725);
        String status = "Shipped";
        order.setStatus(status);
        BookOrder updatedOrder = orderDAO.update(order);
        assertEquals(updatedOrder.getStatus(), status);
    }
    @Test
    public void testDeleteOrder(){
        orderDAO.delete(715);
        BookOrder order = orderDAO.get(715);
        assertNull(order);
    }

    @AfterEach
    void tearDown() {
        orderDAO.close();
    }
}