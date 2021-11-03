package com.bookstore.service;

import com.bookstore.dao.OrderDAO;
import com.bookstore.entity.BookOrder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
        String  shippingAddress = address + "," + city + "," + zipcode + "," + country;

    }
}
