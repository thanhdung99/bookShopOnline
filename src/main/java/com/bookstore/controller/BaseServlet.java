package com.bookstore.controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


public abstract class BaseServlet extends HttpServlet {
    protected EntityManagerFactory entityManagerFactory;

    @Override
    public void init() throws ServletException {
        entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
    }

    @Override
    public void destroy(){
        entityManagerFactory.close();
    }
}
