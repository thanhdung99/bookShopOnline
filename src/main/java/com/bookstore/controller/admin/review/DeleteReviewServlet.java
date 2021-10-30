package com.bookstore.controller.admin.review;

import com.bookstore.service.ReviewServices;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "DeleteReviewServlet", value = "/delete_review")
public class DeleteReviewServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ReviewServices reviewServices = new ReviewServices(request, response);
        reviewServices.deleteReview();
        reviewServices.listReviews();
    }
}