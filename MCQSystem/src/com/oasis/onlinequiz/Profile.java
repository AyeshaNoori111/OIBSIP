package com.oasis.onlinequiz;

import java.sql.*;
import java.util.Scanner;

public class Profile {

    public static void updateProfile(int userId) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter new fullname: ");
        String fullname = sc.nextLine();
        System.out.print("Enter new password: ");
        String password = sc.nextLine();

        try (Connection conn = Database.getConnection()) {
            String query = "UPDATE users SET fullname=?, password=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, fullname);
            ps.setString(2, password);
            ps.setInt(3, userId);

            int rows = ps.executeUpdate();
            if (rows > 0)
                System.out.println("Profile updated successfully!");
            else
                System.out.println("Update failed!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}