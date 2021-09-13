package com.bookstore.controller.frontend;

import com.bookstore.controller.BaseServlet;
import com.bookstore.service.BookServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SearchBookServlet", value = "/search")
public class SearchBookServlet extends BaseServlet {


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookServices bookServices = new BookServices(request, response);
        bookServices.search();
    }
}
