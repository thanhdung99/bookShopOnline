package com.bookstore.service;

import com.bookstore.dao.CustomerDAO;
import com.bookstore.dao.HashGenerator;
import com.bookstore.dao.ReviewDAO;
import com.bookstore.entity.Customer;
import com.bookstore.store.Message;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CustomerServices {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private CustomerDAO customerDAO;
    private ReviewDAO reviewDAO;
    public CustomerServices(HttpServletRequest request, HttpServletResponse response) {
        customerDAO = new CustomerDAO();
        reviewDAO = new ReviewDAO();
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
        String email = request.getParameter("email");
        if(email != null && !email.equals("")){
            customer.setEmail(email);
        }

        if (password != null && !password.isEmpty()) {
            String encryptedPassword = HashGenerator.generateMD5(password);
            customer.setPassword(encryptedPassword);
        }

        customer.setFullName(fullName);
        customer.setPhone(phone);
        customer.setAddress(address);
        customer.setCity(city);
        customer.setZipcode(zipcode);
        customer.setCountry(country);
    }
    public void createCustomer() throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        Customer existCustomer = customerDAO.findByEmail(email);
        Customer customer = new Customer();
        if(existCustomer != null){
            Message message = new Message("Could not create customer", "A customer with email " +email +" already exist","error");

            request.setAttribute("message", message);
            request.setAttribute("customer", customer);

            String createUserPage ="/admin/customers/customer_form.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(createUserPage);
            requestDispatcher.forward(request,response);
        }else {
            customer.setEmail(email);
            customer.setFullName(fullName);
            customer.setPassword(password);
            customerDAO.create(customer);
            Message message = new Message("Create successful", "Create customer successful", "success");
            request.setAttribute("message", message);
        }
    }
    public void registerCustomer() throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String fullName = request.getParameter("fullName");
        Customer existCustomer = customerDAO.findByEmail(email);
        Customer customer = new Customer();
        readCustomerFields(customer);
        if(existCustomer != null){
            Message message = new Message("Could not register", "A customer with email " +email +" already exist","error");
            request.setAttribute("message", message);
            request.setAttribute("customer", customer);
            showLogin();
        }else {
            customer.setEmail(email);
            customer.setFullName(fullName);
            customer.setPassword(password);
            customerDAO.create(customer);
            Message message = new Message("Register successful", "Register new account successful", "success");
            request.setAttribute("message", message);
            String loginPage = "/frontend/index.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(loginPage);
            requestDispatcher.forward(request,response);
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

    public void deleteCustomer() {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        Customer customer = customerDAO.get(customerId);
        if(customer == null){
            Message message = new Message("Could not delete customer", "Could not find customer", "error");
            request.setAttribute("message", message);
        }else{
            if(reviewDAO.findByCustomer(customerId).size() > 0){
                Message message = new Message("Delete failure", "Could not delete customer (ID: " + customerId
                        + " ) because it currently contains some reviews", "error");
                request.setAttribute("message", message);
            }else if (customer.getBookOrdersByCustomerId().size() > 0){
                Message message = new Message("Delete failure", "Could not delete customer (ID: " + customerId
                        + " ) because it currently contains some orders", "error");
                request.setAttribute("message", message);
            } else {
                customerDAO.delete(customerId);
                Message message = new Message("Delete successful", "Delete customer successful", "success");
                request.setAttribute("message", message);
            }

        }
    }

    public void showLogin() throws ServletException, IOException {
        String loginPage = "/frontend/customer/register_form.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(loginPage);
        requestDispatcher.forward(request,response);
    }

    public void doLogin() throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        Customer customer = customerDAO.checkLogin(email, password);

        if(customer != null){
            Message message = new Message("Login successful", "User is authenticated", "success");
            request.setAttribute("message", message);

            HttpSession session = request.getSession();
            session.setAttribute("loggedCustomer", customer);
            Object objRedirectURL = session.getAttribute("redirectURL");


            if (objRedirectURL != null) {
                String redirectPage = (String) objRedirectURL;
                session.removeAttribute("redirectURL");
                response.sendRedirect(redirectPage);
            } else {
                String redirectPage = "/frontend/index.jsp";
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(redirectPage);
                requestDispatcher.forward(request,response);
            }
        }else {
            Message message = new Message("Login fail", "Please enter email and password is registered", "error");
            request.setAttribute("message", message);
            showLogin();
        }
    }

    public void  updateCustomerProfile() {
        Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
        readCustomerFields(customer);
        customerDAO.update(customer);
    }
}
