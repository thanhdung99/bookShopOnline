package com.bookstore.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookOrder bookOrder = (BookOrder) o;

        if (orderId != bookOrder.orderId) return false;
        if (customerId != bookOrder.customerId) return false;
        if (Double.compare(bookOrder.total, total) != 0) return false;
        if (orderDate != null ? !orderDate.equals(bookOrder.orderDate) : bookOrder.orderDate != null) return false;
        if (shippingAddress != null ? !shippingAddress.equals(bookOrder.shippingAddress) : bookOrder.shippingAddress != null)
            return false;
        if (recipientName != null ? !recipientName.equals(bookOrder.recipientName) : bookOrder.recipientName != null)
            return false;
        if (recipientPhone != null ? !recipientPhone.equals(bookOrder.recipientPhone) : bookOrder.recipientPhone != null)
            return false;
        if (recipientMethod != null ? !recipientMethod.equals(bookOrder.recipientMethod) : bookOrder.recipientMethod != null)
            return false;
        if (status != null ? !status.equals(bookOrder.status) : bookOrder.status != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = orderId;
        result = 31 * result + customerId;
        result = 31 * result + (orderDate != null ? orderDate.hashCode() : 0);
        result = 31 * result + (shippingAddress != null ? shippingAddress.hashCode() : 0);
        result = 31 * result + (recipientName != null ? recipientName.hashCode() : 0);
        result = 31 * result + (recipientPhone != null ? recipientPhone.hashCode() : 0);
        result = 31 * result + (recipientMethod != null ? recipientMethod.hashCode() : 0);
        temp = Double.doubleToLongBits(total);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id", nullable = false)
    public Customer getCustomerByCustomerId() {
        return customerByCustomerId;
    }

    public void setCustomerByCustomerId(Customer customerByCustomerId) {
        this.customerByCustomerId = customerByCustomerId;
    }

    @OneToMany(mappedBy = "bookOrderByOrderId")
    public Collection<OrderDetail> getOrderDetailsByOrderId() {
        return orderDetailsByOrderId;
    }

    public void setOrderDetailsByOrderId(Collection<OrderDetail> orderDetailsByOrderId) {
        this.orderDetailsByOrderId = orderDetailsByOrderId;
    }
}
