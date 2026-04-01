package com.safepulse.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.safepulse.util.DBConnection;

@WebServlet("/sos")
public class SOSServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userId = request.getParameter("user_id");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO alerts(user_id, message, status) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, Integer.parseInt(userId));
            ps.setString(2, "Emergency! Need help!");
            ps.setString(3, "SENT");

            int result = ps.executeUpdate();

            if (result > 0) {
                out.println("<h2 style='color:red;'>SOS Sent Successfully!</h2>");
            } else {
                out.println("<h2>Failed to send SOS</h2>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h2>Error: " + e.getMessage() + "</h2>");
        }
    }
}