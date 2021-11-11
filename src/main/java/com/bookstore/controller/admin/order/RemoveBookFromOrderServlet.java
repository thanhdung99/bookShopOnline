package com.bookstore.controller.admin.order;

import com.bookstore.entity.BookOrder;
import com.bookstore.entity.OrderDetail;
import com.bookstore.service.CommonUtitlity;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@WebServlet(name = "RemoveBookFromOrderServlet", value = "/admin/remove_book_from_order")
public class RemoveBookFromOrderServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        BookOrder order = (BookOrder) session.getAttribute("order");
        request.setAttribute("mapCountries",CommonUtitlity.mapCountries());
        Collection<OrderDetail> orderDetails = order.getOrderDetailsByOrderId();
        Iterator<OrderDetail> iterator = orderDetails.iterator();
        while (iterator.hasNext()){
            OrderDetail orderDetail = iterator.next();
            if (orderDetail.getBookByBookId().getBookId() == bookId){
                double newTotal = order.getTotal() - orderDetail.getSubtotal();
                double newSubtotal = order.getSubtotal() - orderDetail.getSubtotal();
                order.setTotal(newTotal);
                order.setSubtotal(newSubtotal);
                iterator.remove();
            }
        }
        CommonUtitlity.forwardToPage("/admin/orders/order_form.jsp", request, response);
    }

}
