package com.group.wishlist.servlets;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.group.wishlist.WishlistEntry;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet(name = "loginServlert", value = "/login-request")
public class LoginServlet extends HttpServlet {
    private HashMap<String, String> accounts;

    public void init() {
        accounts = new HashMap<>();

        accounts.put("Username", "Password");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject item = new Gson().fromJson(request.getReader(),JsonObject.class);

        String username = item.get("username").getAsString();
        String password = item.get("password").getAsString();

        System.out.println(username + " " + password);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if(accounts.get(username) == null || !accounts.get(username).equals(password)){
            response.getWriter().write("{\"success\" : false}");
        }else{
            response.getWriter().write("{\"success\" : true, \"redirect\" : \"/dashboard.html\"}");
        }
    }
    public void destroy() {
    }
}