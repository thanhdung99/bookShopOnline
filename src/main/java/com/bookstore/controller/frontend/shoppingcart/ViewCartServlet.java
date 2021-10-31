package com.bookstore.controller.frontend.shoppingcart;

import com.bookstore.dao.BookDAO;
import com.bookstore.entity.Book;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ViewCartServlet", value = "/view_cart")
public class ViewCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object cartObject = request.getSession().getAttribute("cart");

        if (cartObject == null){
            ShoppingCart shoppingCart = new ShoppingCart();
            request.getSession().setAttribute("cart", shoppingCart);
        }


        String destPage = "/frontend/cart/shopping_cart.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(destPage);
        dispatcher.forward(request, response);


    }

}
