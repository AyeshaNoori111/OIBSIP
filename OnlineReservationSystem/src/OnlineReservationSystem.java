import java.sql.*;
import java.util.*;

public class OnlineReservationSystem {

    static final String URL = "jdbc:mysql://localhost:3306/reservation_db";
    static final String USER = "root";
    static final String PASSWORD = "@8309136031@";

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== ONLINE RESERVATION SYSTEM =====");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            if (choice == 1) {
                if (login()) {
                    userMenu();
                }
            } else if (choice == 2) {
                System.out.println("Thank you!");
                break;
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    static boolean login() {

        System.out.print("Enter Username: ");
        String username = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            String query = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("Login Successful!");
                return true;
            } else {
                System.out.println("Invalid Login!");
                return false;
            }

        } catch (Exception e) {
            System.out.println("Database Error: " + e.getMessage());
            return false;
        }
    }

    static void userMenu() {

        System.out.println("\n1. Reservation");
        System.out.println("2. Cancellation");
        System.out.print("Choose option: ");
        int option = sc.nextInt();
        sc.nextLine();

        if (option == 1) {
            reservation();
        } else if (option == 2) {
            cancellation();
        } else {
            System.out.println("Invalid option!");
        }
    }

    static void reservation() {

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Train Number: ");
        String trainNo = sc.nextLine();

        System.out.print("Enter Class Type: ");
        String classType = sc.nextLine();

        System.out.print("From: ");
        String from = sc.nextLine();

        System.out.print("To: ");
        String to = sc.nextLine();

        System.out.print("Journey Date: ");
        String date = sc.nextLine();

        String pnr = "PNR" + (1000 + new Random().nextInt(9000));

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            String query = "INSERT INTO reservations (name, train_no, class_type, from_place, to_place, journey_date, pnr) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, name);
            ps.setString(2, trainNo);
            ps.setString(3, classType);
            ps.setString(4, from);
            ps.setString(5, to);
            ps.setString(6, date);
            ps.setString(7, pnr);

            ps.executeUpdate();

            System.out.println("Reservation Successful!");
            System.out.println("Generated PNR: " + pnr);

        } catch (Exception e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }

    // ✅ IMPROVED CANCELLATION METHOD
    static void cancellation() {

        System.out.print("Enter PNR Number: ");
        String pnr = sc.nextLine().trim();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD)) {

            // Check if PNR exists
            String checkQuery = "SELECT * FROM reservations WHERE pnr=?";
            PreparedStatement checkPs = con.prepareStatement(checkQuery);
            checkPs.setString(1, pnr);

            ResultSet rs = checkPs.executeQuery();

            if (rs.next()) {

                String deleteQuery = "DELETE FROM reservations WHERE pnr=?";
                PreparedStatement deletePs = con.prepareStatement(deleteQuery);
                deletePs.setString(1, pnr);

                deletePs.executeUpdate();

                System.out.println("Reservation Cancelled Successfully!");

            } else {
                System.out.println("PNR Not Found!");
            }

        } catch (Exception e) {
            System.out.println("Database Error: " + e.getMessage());
        }
    }
}