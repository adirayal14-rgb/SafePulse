package com.safepulse.util;

import java.sql.Connection;

public class TestConnection {

    public static void main(String[] args) {

        System.out.println("Trying to connect...");

        Connection con = DBConnection.getConnection();

        if (con != null) {
            System.out.println("Connected to Database!");
        } else {
            System.out.println("Connection Failed!");
        }
    }
}