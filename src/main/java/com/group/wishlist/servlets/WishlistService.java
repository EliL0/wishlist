package com.group.wishlist.servlets;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.group.wishlist.WishlistEntry;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


@WebServlet(name = "wishlistServlet", value = "/wishlist-request")
public class WishlistServlet extends HttpServlet {
    private List<WishlistEntry> entries;

    public void init() {
        entries = new ArrayList<>();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(entries));
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        JsonObject item = new Gson().fromJson(request.getReader(),JsonObject.class);

        String name = item.get("itemName").getAsString();
        String url = item.get("itemUrl").getAsString();

        entries.add(new WishlistEntry(name,url));

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write("{\"success\" : true}");
    }
    public void destroy() {
    }
}