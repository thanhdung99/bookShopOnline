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
        String listPage ="/admin/orders/orders_list.jsp";

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
        requestDispatcher.forward(request,response);
    }
    public void viewOrderDetailForAdmin() throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter(""));
        BookOrder order = new BookOrder();
        request.setAttribute("order",order);

        String detailPage = "/admin/orders/order_detail.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(detailPage);
        requestDispatcher.forward(request,response);
    }

    public void showCheckOutForm() throws ServletException, IOException {
        String checkoutPage ="/frontend/order/checkout.jsp";

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(checkoutPage);
        requestDispatcher.forward(request,response);
    }

    public void placeOrder() {

        String recipientName = request.getParameter("recipientName");
        String recipientPhone =  request.getParameter("recipientPhone");
        String address = request.getParameter("address");
        String city = request.getParameter("city");
        String zipcode = request.getParameter("zipcode");
        String country = request.getParameter("country");
        String paymentMethod = request.getParameter("paymentMethod");
        String  shippingAddress = address + "," + city + "," + zipcode + "," + country;

        BookOrder order = new BookOrder();
        order.setRecipientName(recipientName);
        order.setRecipientPhone(recipientPhone);
        order.setShippingAddress(shippingAddress);
        order.setRecipientMethod(paymentMethod);

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
        order.setTotal(shoppingCart.getTotalAmount());

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

        String historyPage ="/frontend/order/order_list.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(historyPage);
        requestDispatcher.forward(request,response);

    }
}
