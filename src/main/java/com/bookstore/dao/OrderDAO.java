package com.bookstore.dao;

import com.bookstore.entity.BookOrder;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class OrderDAO extends JpaDAO<BookOrder> implements GenericDAO<BookOrder>{
    @Override
    public BookOrder create(BookOrder order) {
        Date date = new Date();
        order.setOrderDate(new Timestamp(date.getTime()));
        order.setRecipientMethod("Cash on Delivery ");
        order.setStatus("Processing");
        return super.create(order);
    }
    @Override
    public BookOrder get(Object orderId) {
        return null;
    }

    @Override
    public void delete(Object orderId) {

    }

    @Override
    public List<BookOrder> listAll() {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }
}