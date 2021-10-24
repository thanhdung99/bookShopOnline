package com.bookstore.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@NamedQueries({
        @NamedQuery(name="Customer.findAll",
                query="SELECT c FROM Customer c ORDER BY c.registerDate DESC "),
        @NamedQuery(name="Customer.countAll",
                query="SELECT COUNT(c) FROM Customer c "),
        @NamedQuery(name="Customer.findByEmail",
                query="SELECT c FROM Customer c WHERE c.email = :email"),
        @NamedQuery(name = "Customer.checkLogin",
                query="SELECT c FROM Customer c WHERE c.email = :email AND c.password = :password")
})
public class Customer {
    private int customerId;
    private String email;
    private String fullName;
    private String address;
    private String city;
    private String country;
    private String phone;
    private String zipcode;
    private String password;
    private Date registerDate;
    private Collection<BookOrder> bookOrdersByCustomerId;
    private Collection<Review> reviewsByCustomerId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id", nullable = false)
    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 64)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "full_name", nullable = false, length = 30)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Basic
    @Column(name = "address", nullable = true, length = 128)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "city", nullable = true, length = 32)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "country", nullable = true, length = 64)
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Basic
    @Column(name = "phone", nullable = true, length = 15)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "zipcode", nullable = true, length = 24)
    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    @Basic
    @Column(name = "password", nullable = false, length = 16)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "register_date", nullable = false)
    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (customerId != customer.customerId) return false;
        if (email != null ? !email.equals(customer.email) : customer.email != null) return false;
        if (fullName != null ? !fullName.equals(customer.fullName) : customer.fullName != null) return false;
        if (address != null ? !address.equals(customer.address) : customer.address != null) return false;
        if (city != null ? !city.equals(customer.city) : customer.city != null) return false;
        if (country != null ? !country.equals(customer.country) : customer.country != null) return false;
        if (phone != null ? !phone.equals(customer.phone) : customer.phone != null) return false;
        if (zipcode != null ? !zipcode.equals(customer.zipcode) : customer.zipcode != null) return false;
        if (password != null ? !password.equals(customer.password) : customer.password != null) return false;
        if (registerDate != null ? !registerDate.equals(customer.registerDate) : customer.registerDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = customerId;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (zipcode != null ? zipcode.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (registerDate != null ? registerDate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "customerByCustomerId")
    public Collection<BookOrder> getBookOrdersByCustomerId() {
        return bookOrdersByCustomerId;
    }

    public void setBookOrdersByCustomerId(Collection<BookOrder> bookOrdersByCustomerId) {
        this.bookOrdersByCustomerId = bookOrdersByCustomerId;
    }

    @OneToMany(mappedBy = "customerByCustomerId")
    public Collection<Review> getReviewsByCustomerId() {
        return reviewsByCustomerId;
    }

    public void setReviewsByCustomerId(Collection<Review> reviewsByCustomerId) {
        this.reviewsByCustomerId = reviewsByCustomerId;
    }
}
