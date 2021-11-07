package com.bookstore.controller.admin.order;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;
import com.bookstore.entity.BookOrder;
import com.bookstore.entity.OrderDetail;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AddBookToOrderServlet", value = "/AddBookToOrderServlet")
public class AddBookToOrderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        BookDAO bookDAO = new BookDAO();
        Book book = bookDAO.get(bookId);

        HttpSession session = request.getSession();
        BookOrder order = (BookOrder) session.getAttribute("order");

        float subtotal = (float) (quantity * book.getPrice());
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setBookByBookId(book);
        orderDetail.setQuantity(quantity);
        orderDetail.setSubtotal(subtotal);

        float newTotal = (float) (order.getTotal() + subtotal);
        order.setTotal(newTotal);

        order.getOrderDetailsByOrderId().add(orderDetail);

        String resultPage = "/orders/add_book_result.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(resultPage);
        requestDispatcher.forward(request,response);
    }
}
