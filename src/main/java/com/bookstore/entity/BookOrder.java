package com.bookstore.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "book_order", schema = "bookstoredb", catalog = "")
public class BookOrder {
    private int orderId;
    private int customerId;
    private Timestamp orderDate;
    private String shippingAddress;
    private String recipientName;
    private String recipientPhone;
    private String recipientMethod;
    private double total;
    private String status;
    private Customer customerByCustomerId;
    private Collection<OrderDetail> orderDetailsByOrderId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Basic
    @Column(name = "customer_id", nullable = false, insertable = false, updatable = false)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "order_date", nullable = false)
    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    @Basic
    @Column(name = "shipping_address", nullable = false, length = 256)
    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @Basic
    @Column(name = "recipient_name", nullable = false, length = 30)
    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    @Basic
    @Column(name = "recipient_phone", nullable = false, length = 15)
    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    @Basic
    @Column(name = "recipient_method", nullable = false, length = 20)
    public String getRecipientMethod() {
        return recipientMethod;
    }

    public void setRecipientMethod(String recipientMethod) {
        this.recipientMethod = recipientMethod;
    }

    @Basic
    @Column(name = "total", nullable = false, precision = 0)
    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Basic
    @Column(name = "status", nullable = false, length = 20)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false)
    public Customer getCustomerByCustomerId() {
        return customerByCustomerId;
    }

    public void setCustomerByCustomerId(Customer customerByCustomerId) {
        this.customerByCustomerId = customerByCustomerId;
    }

    @OneToMany(mappedBy = "bookOrderByOrderId" , cascade = CascadeType.ALL, orphanRemoval = true)
    public Collection<OrderDetail> getOrderDetailsByOrderId() {
        return orderDetailsByOrderId;
    }

    public void setOrderDetailsByOrderId(Collection<OrderDetail> orderDetailsByOrderId) {
        this.orderDetailsByOrderId = orderDetailsByOrderId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookOrder order = (BookOrder) o;
        return orderId == order.orderId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId);
    }
}
