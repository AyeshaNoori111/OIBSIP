package com.oasis.onlinequiz;

import java.sql.*;
import java.util.Scanner;

public class Quiz {

    public static void startQuiz(int userId) {
        try (Connection conn = Database.getConnection();
             Scanner sc = new Scanner(System.in)) {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM questions");

            int score = 0;

            System.out.println("You have 60 seconds to complete the quiz!");
            long endTime = System.currentTimeMillis() + 60 * 1000; // 60 sec timer

            while (rs.next() && System.currentTimeMillis() < endTime) {
                System.out.println(rs.getInt("id") + ". " + rs.getString("question_text"));
                System.out.println("1. " + rs.getString("option1"));
                System.out.println("2. " + rs.getString("option2"));
                System.out.println("3. " + rs.getString("option3"));
                System.out.println("4. " + rs.getString("option4"));

                System.out.print("Enter your option (1-4): ");
                int answer = sc.nextInt();

                if (answer == rs.getInt("correct_option"))
                    score++;
            }

            System.out.println("Time's up! Your score: " + score);

            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO results(user_id, score) VALUES (?, ?)"
            );
            ps.setInt(1, userId);
            ps.setInt(2, score);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}