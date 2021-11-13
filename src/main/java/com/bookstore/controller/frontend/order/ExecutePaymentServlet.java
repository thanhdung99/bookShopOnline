package com.bookstore.controller.frontend.order;

import com.bookstore.service.BookOrderServices;
import com.bookstore.service.PaymentServices;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ExecutePaymentServlet", value = "/execute_payment")
public class ExecutePaymentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PaymentServices paymentServices = new PaymentServices(request, response);
        try {
            Payment payment = paymentServices.executePayment();
            BookOrderServices bookOrderServices = new BookOrderServices(request, response);
            Integer orderId = bookOrderServices.placeOrderPaypal(payment);

            response.getWriter().println(orderId);
        } catch (PayPalRESTException e) {
            e.printStackTrace();
            throw new ServletException("Error in executing payment.");
        }
    }
}
