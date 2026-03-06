package com.oasis.onlinequiz;

import java.sql.*;

public class Database {
    private static final String URL = "jdbc:mysql://localhost:3306/mcq_system";
    private static final String USER = "root"; // your mysql username
    private static final String PASS = "@8309136031@";     // your mysql password

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}