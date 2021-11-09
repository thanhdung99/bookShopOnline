package com.bookstore.service;

import com.bookstore.store.Message;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CommonUtitlity {
    public static void forwardToPage(String page, Message message, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher(page).forward(request, response);
    }
    public static void forwardToPage(String page, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher(page).forward(request, response);
    }
    public static Map<String, String> mapCountries(){
        String[] countryCodes = Locale.getISOCountries();
        Map<String, String> mapCountries = new HashMap<>();

        for (String countryCode:countryCodes){
            Locale locale = new Locale("",countryCode);
            String code = locale.getCountry();
            String name = locale.getDisplayCountry();

            mapCountries.put(name, code);
        }
        return mapCountries;
    }

}
