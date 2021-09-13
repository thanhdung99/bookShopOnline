package com.bookstore.dao;

import com.bookstore.entity.Customer;
import com.bookstore.entity.Users;


import java.sql.Date;

import java.util.Calendar;
import java.util.List;

public class CustomerDAO extends JpaDAO<Customer> implements GenericDAO<Customer>{

    @Override
    public Customer create(Customer customer) {
        String encryptedPassword = HashGenerator.generateMD5(customer.getPassword());
        customer.setPassword(encryptedPassword);
        customer.setRegisterDate(new Date(Calendar.getInstance().getTimeInMillis()));
        return super.create(customer);
    }

    @Override
    public Customer update(Customer customer) {
        return super.update(customer);
    }


    @Override
    public Customer get(Object customerId) {
        return super.find(Customer.class, customerId);
    }

    @Override
    public void delete(Object customerId) {
        super.delete(Customer.class, customerId);
    }

    @Override
    public List<Customer> listAll() {
        return super.findWithNamedQuery("Customer.findAll");
    }

    @Override
    public long count() {
        return super.countWithNamedQuery("Customer.countAll");
    }

    public Customer findByEmail(String email){
        List<Customer> listResults = super.findWithNamedQuery("Customer.findByEmail", "email", email);
        if (listResults != null && listResults.size() > 0 ){
            return listResults.get(0);
        }
        return null;
    }
}
