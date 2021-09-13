package com.bookstore.service;

import com.bookstore.dao.UserDAO;
import com.bookstore.entity.Users;
import com.bookstore.store.Message;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserServices {

    private UserDAO userDAO;
    private HttpServletRequest request;
    private HttpServletResponse response;

    public UserServices( HttpServletRequest request, HttpServletResponse response){

        userDAO = new UserDAO();
        this.request = request;
        this.response = response;
    }
    public void listUsers() throws ServletException, IOException {
        List<Users> usersList = userDAO.listAll();

        request.setAttribute("usersList", usersList);
        String listPage ="/admin/users/users_list.jsp";

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(listPage);
        requestDispatcher.forward(request,response);

    }
    public void createUser() throws ServletException, IOException {
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");

        Users existUser = userDAO.findByEmail(email);
        if(existUser != null){
            Message message = new Message("Could not create user", "A user with email " +email +" already exist","error");
            Users user = new Users();
            user.setFullName(fullName);

            request.setAttribute("message", message);
            request.setAttribute("user", user);

            String createUserPage ="/admin/users/user_form.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(createUserPage);
            requestDispatcher.forward(request,response);
        }else {
            Users newUser = new Users(email, password, fullName);
            userDAO.create(newUser);

            Message message = new Message("Create successful", "Create user successful", "success");
            request.setAttribute("message", message);
        }
    }
    public void editUser()throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        Users user = userDAO.get(userId);

        if (user == null) {
            Message message = new Message("User not found", "Could not find user with ID " + userId,"error");
            request.setAttribute("message", message);
            List<Users> usersList = userDAO.listAll();
            request.setAttribute("usersList", usersList);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/users/users_list.jsp");
            dispatcher.forward(request, response);

        }
        else {
            request.setAttribute("user", user);
            String editUserPage ="/admin/users/user_form.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(editUserPage);
            requestDispatcher.forward(request,response);
        }

    }
    public void updateUser() throws ServletException, IOException {

        int userId = Integer.parseInt(request.getParameter("userId"));
        String email = request.getParameter("email");
        String fullName = request.getParameter("fullName");
        String password = request.getParameter("password");

        Users existUser = userDAO.findByEmail(email);
        if(existUser != null && existUser.getUserId() != userId){
            Message message = new Message("Could not update user", "A user with email " +email +" already exist","error");
            Users user = new Users(userId, "", password, fullName );

            request.setAttribute("message", message);
            request.setAttribute("user", user);

            String createUserPage ="/admin/users/user_form.jsp";
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(createUserPage);
            requestDispatcher.forward(request,response);


        }else {
            Users user = new Users(userId, email, password, fullName );
            userDAO.update(user);
            Message message = new Message("Update successful", "Update user successful", "success");
            request.setAttribute("message", message);
        }

    }
    public void deleteUser() {
        int userId = Integer.parseInt(request.getParameter("userId"));

        if(userId == 1 ){
            Message message = new Message("Could not delete user", "The default admin user account cannot be deleted.", "error");
            request.setAttribute("message", message);
        }
        else {
            Users user = userDAO.get(userId);
            if(user == null){
                Message message = new Message("Could not delete user", "Could not find user", "error");
                request.setAttribute("message", message);
            }else{
                userDAO.delete(userId);
                Message message = new Message("Delete successful", "Delete user successful", "success");
                request.setAttribute("message", message);
            }
        }
    }

    public void login() throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        boolean isLogin = userDAO.checkLogin(email, password);

        if(isLogin){
            request.getSession().setAttribute("userEmail", email);
            Message message = new Message("Login successful", "User is authenticated", "success");
            request.setAttribute("message", message);
            listUsers();
        }else {
            Message message = new Message("Login fail", "Please enter email and password is registered", "error");
            request.setAttribute("message", message);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/login.jsp");
            dispatcher.forward(request, response);
        }
    }
}
