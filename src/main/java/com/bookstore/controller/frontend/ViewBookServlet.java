package com.bookstore.controller.frontend;

import com.bookstore.controller.BaseServlet;
import com.bookstore.service.BookServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ViewBookServlet", value = "/view_book")
public class ViewBookServlet extends BaseServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookServices bookServices = new BookServices(request, response);
        bookServices.viewBookDetail();
    }

}
