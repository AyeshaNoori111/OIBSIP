package com.oasis.onlinequiz;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int userId = Login.doLogin();

        if (userId != -1) {
            System.out.println("1. Update Profile");
            System.out.println("2. Start Quiz");
            System.out.println("3. Logout");
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    Profile.updateProfile(userId);
                    break;
                case 2:
                    Quiz.startQuiz(userId);
                    break;
                case 3:
                    System.out.println("Logged out successfully!");
                    break;
            }
        }
    }
}