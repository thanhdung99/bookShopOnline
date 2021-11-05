package com.bookstore.dao;

import com.bookstore.entity.Book;
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
    public BookOrder update(BookOrder bookOrder){return super.update(bookOrder);}
    @Override
    public BookOrder get(Object orderId) {
        return super.find(BookOrder.class, orderId);
    }

    @Override
    public void delete(Object orderId) {
        super.delete(BookOrder.class, orderId);
    }

    @Override
    public List<BookOrder> listAll() {
        return super.findWithNamedQuery("BookOrder.findAll");
    }

    @Override
    public long count() {
        return super.countWithNamedQuery("BookOrder.countAll");
    }
    public List<BookOrder> listByCustomer(int customerId){
        return super.findWithNamedQuery("BookOrder.findByCustomer",
                "customerId",customerId);
    }

}
