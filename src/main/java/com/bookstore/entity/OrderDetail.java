package com.bookstore.entity;

import javax.persistence.*;

@Entity
@Table(name = "order_detail", schema = "bookstoredb", catalog = "")
@IdClass(OrderDetailPK.class)
public class OrderDetail {
    private int orderId;
    private int bookId;
    private int quantity;
    private double subtotal;
    private Book bookByBookId;
    private BookOrder bookOrderByOrderId;

    @Id
    @Column(name = "order_id", nullable = false, updatable = false, insertable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Id
    @Column(name = "book_id", nullable = false)
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "quantity", nullable = false)
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "subtotal", nullable = false, precision = 0)
    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderDetail that = (OrderDetail) o;

        if (orderId != that.orderId) return false;
        if (bookId != that.bookId) return false;
        if (quantity != that.quantity) return false;
        if (Double.compare(that.subtotal, subtotal) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = orderId;
        result = 31 * result + bookId;
        result = 31 * result + quantity;
        temp = Double.doubleToLongBits(subtotal);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "book_id", nullable = false)
    public Book getBookByBookId() {
        return bookByBookId;
    }

    public void setBookByBookId(Book bookByBookId) {
        this.bookByBookId = bookByBookId;
    }

    @ManyToOne
    @JoinColumn(name = "order_id", referencedColumnName = "order_id", nullable = false)
    public BookOrder getBookOrderByOrderId() {
        return bookOrderByOrderId;
    }

    public void setBookOrderByOrderId(BookOrder bookOrderByOrderId) {
        this.bookOrderByOrderId = bookOrderByOrderId;
    }
}