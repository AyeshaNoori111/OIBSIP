package com.oasis.onlinequiz;

import java.sql.*;
import java.util.Scanner;

public class Login {

    public static int doLogin() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = sc.nextLine();
        System.out.print("Enter password: ");
        String password = sc.nextLine();

        try (Connection conn = Database.getConnection()) {
            String query = "SELECT id FROM users WHERE username=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("Login Successful!");
                return rs.getInt("id"); // return user id
            } else {
                System.out.println("Invalid credentials.");
                return -1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }
}