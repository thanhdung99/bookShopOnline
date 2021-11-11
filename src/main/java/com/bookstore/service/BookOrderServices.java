package com.bookstore.service;

import com.bookstore.controller.frontend.shoppingcart.ShoppingCart;
import com.bookstore.dao.OrderDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.Customer;
import com.bookstore.entity.OrderDetail;
import com.bookstore.store.Message;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

public class BookOrderServices {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private OrderDAO orderDAO;

    public BookOrderServices(HttpServletRequest request, HttpServletResponse response) {
        orderDAO = new OrderDAO();
        this.request = request;
        this.response = response;
    }
    public void listOrders() throws ServletException, IOException {
        List<BookOrder> ordersList = orderDAO.listAll();
        request.setAttribute("ordersList",ordersList);
        CommonUtitlity.forwardToPage("/admin/orders/orders_list.jsp", request, response);
    }

    public void showCheckOutForm() throws ServletException, IOException {
        HttpSession session = request.getSession();
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");

        float tax = shoppingCart.getTotalAmount() * 0.1f;
        float shippingFee = shoppingCart.getTotalQuantity() * 0.1f;
        float total = shoppingCart.getTotalAmount() + tax + shippingFee;

        request.setAttribute("tax", tax);
        request.setAttribute("shippingFee", shippingFee);
        request.setAttribute("total", total);
        request.setAttribute("mapCountries",CommonUtitlity.mapCountries());

        CommonUtitlity.forwardToPage("/frontend/order/checkout.jsp", request, response);
    }

    public void placeOrder() {

        String rFirstname = request.getParameter("rFirstname");
        String rLastname = request.getParameter("rLastname");
        String rPhone =  request.getParameter("rPhone");
        String rAddressLine1 = request.getParameter("rAddressLine1");
        String rAddressLine2 = request.getParameter("rAddressLine2");
        String rState = request.getParameter("rState");
        String rCity = request.getParameter("rCity");
        String rZipcode = request.getParameter("rZipcode");
        String rCountry = request.getParameter("rCountry");
        String paymentMethod = request.getParameter("paymentMethod");

        BookOrder order = new BookOrder();
        order.setrFirstname(rFirstname);
        order.setrLastname(rLastname);
        order.setrPhone(rPhone);
        order.setrAddressLine1(rAddressLine1);
        order.setrAddressLine2(rAddressLine2);
        order.setrState(rState);
        order.setrCity(rCity);
        order.setrZipcode(rZipcode);
        order.setrCountry(rCountry);
        order.setPaymentMethod(paymentMethod);

        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("");
        order.setCustomerByCustomerId(customer);

        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute("cart");
        Map<Book, Integer> items = shoppingCart.getItems();

        Iterator<Book> iterator = items.keySet().iterator();

        Set<OrderDetail> orderDetails = new HashSet<>();

        while (iterator.hasNext()){
            Book book = iterator.next();
            Integer quantity = items.get(book);
            float subtotal = (float) (quantity * book.getPrice());

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setBookOrderByOrderId(order);
            orderDetail.setBookByBookId(book);
            orderDetail.setQuantity(quantity);
            orderDetail.setSubtotal(subtotal);

            orderDetails.add(orderDetail);
        }
        order.setOrderDetailsByOrderId(orderDetails);
        double tax = (Double) session.getAttribute("tax");
        double shippingFee = (Double) session.getAttribute("shippingFee");
        double total = (Double) session.getAttribute("total");

        order.setSubtotal((double) shoppingCart.getTotalAmount());
        order.setTax(tax);
        order.setShippingFee(shippingFee);
        order.setTotal(total);

        orderDAO.create(order);
        shoppingCart.clear();
        Message message = new Message("Thank you, your order has been received.",
                "We will deliver your book within a few days. ",
                "error");
        request.setAttribute("message", message);
    }

    public void listOrdersbyCustomer() throws ServletException, IOException {
        Customer customer = (Customer) request.getSession().getAttribute("loggedCustomer");
        List<BookOrder> listOrders = orderDAO.listByCustomer(customer.getCustomerId());
        request.setAttribute("listOrders", listOrders);

        CommonUtitlity.forwardToPage("/frontend/order/orders_list.jsp", request, response);

    }
    public void viewOrderDetailForAdmin() throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("id"));
        BookOrder order = orderDAO.get(orderId);
        request.setAttribute("order",order);

        CommonUtitlity.forwardToPage("/admin/orders/order_detail.jsp", request, response);
    }
    public void showEditOrderForm() throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("id"));
        BookOrder order = orderDAO.get(orderId);

        if (order == null) {
            Message message = new Message("Edit order fail", "Could not find order with ID " + orderId,"error");
            CommonUtitlity.forwardToPage("/admin/list_orders", message, request, response);
        }

        HttpSession session = request.getSession();
        Object isPendingBook = session.getAttribute("NewBookPendingToAddToOrder");
        request.setAttribute("mapCountries",CommonUtitlity.mapCountries());

        if (isPendingBook == null) {
            session.setAttribute("order", order);
        } else {
            session.removeAttribute("NewBookPendingToAddToOrder");
        }

        CommonUtitlity.forwardToPage("/admin/orders/order_form.jsp", request, response);
    }

    public void showOrderDetailForCustomer() throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("loggedCustomer");

        BookOrder order = orderDAO.get(orderId, customer.getCustomerId());
        request.setAttribute("order",order);

        CommonUtitlity.forwardToPage("/frontend/order/order_details.jsp", request, response);
    }
    public void updateOrder(){
        HttpSession session = request.getSession();
        BookOrder order = (BookOrder) session.getAttribute("order");

        String rFirstname = request.getParameter("rFirstname");
        String rLastname = request.getParameter("rLastname");
        String rAddressLine1 = request.getParameter("rAddressLine1");
        String rAddressLine2 = request.getParameter("rAddressLine2");
        String rPhone = request.getParameter("rPhone");
        String rCity = request.getParameter("rCity");
        String rState = request.getParameter("rState");
        String rZipcode = request.getParameter("rZipcode");
        String rCountry = request.getParameter("rCountry");

        Double shippingFee = Double.parseDouble(request.getParameter("shippingFee"));
        Double tax = Double.parseDouble(request.getParameter("tax"));

        String paymentMethod = request.getParameter("paymentMethod");
        String status = request.getParameter("status");

        order.setrFirstname(rFirstname);
        order.setrLastname(rLastname);
        order.setrPhone(rPhone);
        order.setrAddressLine1(rAddressLine1);
        order.setrAddressLine2(rAddressLine2);
        order.setrCity(rCity);
        order.setrState(rState);
        order.setrZipcode(rZipcode);
        order.setShippingFee(shippingFee);
        order.setTax(tax);
        order.setrCountry(rCountry);

        order.setPaymentMethod(paymentMethod);
        order.setStatus(status);

        String[] arrayBookId = request.getParameterValues("bookId");
        String[] arrayPrice = request.getParameterValues("price");
        String[] arrayQuantity = new String[arrayBookId.length];

        for (int i = 1; i <= arrayQuantity.length; i++){
            arrayQuantity[i-1] = request.getParameter("quantity"+i);
        }
        Collection<OrderDetail> orderDetails = order.getOrderDetailsByOrderId();
        orderDetails.clear();

        Double totalAmount = 0.0;
        for (int i = 0; i < arrayBookId.length; i++){
            int bookId = Integer.parseInt(arrayBookId[i]);
            int quantity = Integer.parseInt(arrayQuantity[i]);
            Double subtotal = Double.parseDouble(arrayPrice[i]);

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setBookByBookId(new Book(bookId));
            orderDetail.setQuantity(quantity);
            orderDetail.setSubtotal(subtotal);

            orderDetail.setBookOrderByOrderId(order);

            orderDetails.add(orderDetail);
            totalAmount += subtotal;
        }
        order.setSubtotal(totalAmount);
        totalAmount += shippingFee;
        totalAmount += tax;
        order.setTotal(totalAmount);
        orderDAO.update(order);

        Message message = new Message("Update order", "", "");


    }

    public void deleteOrder() throws ServletException, IOException {
        Integer orderId = Integer.parseInt(request.getParameter("id"));
        BookOrder order = orderDAO.get(orderId);

        if (order != null) {
            orderDAO.delete(orderId);

            Message message = new Message("Delete successful",
                    "The order ID " + orderId + " has been deleted.",
                    "success");
            request.setAttribute("message", message);
        } else {
            Message message = new Message("Could not delete order",
                    "Could not find order with ID " + orderId +
                            ", or it might have been deleted by another admin.",
                    "error");
            request.setAttribute("message", message);
        }
    }
}
