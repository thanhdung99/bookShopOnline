package com.bookstore.service;

import com.bookstore.dao.CustomerDAO;
import com.bookstore.entity.Category;
import com.bookstore.entity.Customer;
import com.bookstore.entity.Users;
import com.bookstore.store.Message;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CustomerServices {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private CustomerDAO customerDAO;
    public CustomerServices(HttpServletRequest request, HttpServletResponse response) {
        customerDAO = new CustomerDAO();
        this.request = request;
        this.response = response;
    }
    public void listCustomers() throws ServletException, IOException {
        List<Customer> customersList = customerDAO.listAll();
        request.setAttribute("customersList", customersList);
        String listPage ="/admin/customers/customers_list.jsp";

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
        requestDispatcher.forward(request,response);
    }
    public void readCustomerFields(Customer customer){
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String zipcode = request.getParameter("zipcode");
        String country = request.getParameter("country");

        customer.setFullName(fullName);
        customer.setPassword(password);
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setCity(city);
        customer.setZipcode(zipcode);
        customer.setCountry(country);
    }
    public void createCustomer() throws ServletException, IOException {
        String email = request.getParameter("email");
        Customer existCustomer = customerDAO.findByEmail(email);
        Customer customer = new Customer();
        if(existCustomer != null){
            Message message = new Message("Could not create customer", "A customer with email " +email +" already exist","error");
            readCustomerFields(customer);

            request.setAttribute("message", message);
            request.setAttribute("customer", customer);

            String createUserPage ="/admin/customers/customer_form.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(createUserPage);
            requestDispatcher.forward(request,response);
        }else {
            customer.setEmail(email);
            readCustomerFields(customer);
            customerDAO.create(customer);

            Message message = new Message("Create successful", "Create customer successful", "success");
            request.setAttribute("message", message);
        }
    }

    public void editCustomer() throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("id"));
        Customer customer = customerDAO.get(customerId);
        if(customer == null){
            Message message = new Message("Customer not found", "Could not find customer with ID " + customerId,"error");
            request.setAttribute("message", message);
            List<Customer> customersList = customerDAO.listAll();
            request.setAttribute("customersList", customersList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/customers/customersList_list.jsp");
            dispatcher.forward(request, response);
        }else {
            request.setAttribute("customer", customer);
            String editCategoryPage ="/admin/customers/customer_form.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(editCategoryPage);
            requestDispatcher.forward(request,response);
        }
    }

    public void updateCustomer() throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        String email = request.getParameter("email");
        Customer existCustomer = customerDAO.findByEmail(email);
        Customer customer = new Customer();
        readCustomerFields(customer);
        if(existCustomer != null && existCustomer.getCustomerId() != customerId){
            Message message = new Message("Could not update customer", "A customer with email " +email +" already exist","error");
            customer.setEmail("");
            request.setAttribute("message", message);
            request.setAttribute("customer", customer);

            String createUserPage ="/admin/customers/customer_form.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(createUserPage);
            requestDispatcher.forward(request,response);


        }else {
            customerDAO.update(customer);
            Message message = new Message("Update successful", "Update customer successful", "success");
            request.setAttribute("message", message);
        }

    }


}
