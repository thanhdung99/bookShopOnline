package com.bookstore.controller.frontend;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", value = "")
public class HomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        BookDAO bookDAO = new BookDAO();
        List<Book> listNewBooks = bookDAO.listNewBooks();
        List<Book> listBestSellingBooks = bookDAO.listBestSellingBooks();
//        List<Book> listMostFavoredBooks = bookDAO.listMostFavoredBooks();

        request.setAttribute("listNewBooks",listNewBooks);
        request.setAttribute("listBestSellingBooks", listBestSellingBooks);
//        request.setAttribute("listMostFavoredBooks", listMostFavoredBooks);

        String homePage = "/frontend/index.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(homePage);
        dispatcher.forward(request,response);
    }

}
