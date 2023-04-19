package com.jdbc7;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.sql.*;




public class Medicinemain {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/onlineMedicalStore";
    private static final String USER = "root";
    private static final String PASS = "123456";

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            Scanner scanner = new Scanner(System.in);
            
            // User registration
            System.out.println("Welcome to Online Medical Store! Please register to continue.");
            System.out.print("Enter a username: ");
            String username = scanner.next();
            System.out.print("Enter a password: ");
            String password = scanner.next();
            System.out.print("Enter an email: ");
            String email = scanner.next();
            
            String insertUserQuery = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
            PreparedStatement insertUserStatement = conn.prepareStatement(insertUserQuery);
            insertUserStatement.setString(1, username);
            insertUserStatement.setString(2, password);
            insertUserStatement.setString(3, email);
            int rowsInserted = insertUserStatement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Registration successful.");
            }
            
            // Product management
            System.out.println("Enter 1 to add a new product, 2 to update a product, or 3 to view products:");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter product name: ");
                    String productName = scanner.next();
                    System.out.print("Enter product description: ");
                    String productDescription = scanner.next();
                    System.out.print("Enter product price: ");
                    double productPrice = scanner.nextDouble();
                    System.out.print("Enter product quantity: ");
                    int productQuantity = scanner.nextInt();
                    
                    String insertProductQuery = "INSERT INTO products (name, description, price, quantity) VALUES (?, ?, ?, ?)";
                    PreparedStatement insertProductStatement = conn.prepareStatement(insertProductQuery);
                    insertProductStatement.setString(1, productName);
                    insertProductStatement.setString(2, productDescription);
                    insertProductStatement.setDouble(3, productPrice);
                    insertProductStatement.setInt(4, productQuantity);
                    int productRowsInserted = insertProductStatement.executeUpdate();
                    if (productRowsInserted > 0) {
                        System.out.println("Product added successfully.");
                    }
                    break;
                    
                case 2:
                    System.out.print("Enter product ID to update: ");
                    int productId = scanner.nextInt();
                    System.out.print("Enter updated product quantity: ");
                    int updatedQuantity = scanner.nextInt();
                    
                    String updateProductQuery = "UPDATE products SET quantity = ? WHERE id = ?";
                    PreparedStatement updateProductStatement = conn.prepareStatement(updateProductQuery);
                    updateProductStatement.setInt(1, updatedQuantity);
                    updateProductStatement.setInt(2, productId);
                    int productRowsUpdated = updateProductStatement.executeUpdate();
                    if (productRowsUpdated > 0) {
                    System.out.println("Product updated successfully.");
                    }
                    break;
                    
                case 3:
                    String viewProductsQuery = "SELECT * FROM products";
                    Statement viewProductsStatement = conn.createStatement();
                    ResultSet resultSet = viewProductsStatement.executeQuery(viewProductsQuery);
                    
                    System.out.println("Product ID\tProduct Name\tProduct Description\tProduct Price\tProduct Quantity");
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id");
                        String name = resultSet.getString("name");
                        String description = resultSet.getString("description");
                        double price = resultSet.getDouble("price");
                        int quantity = resultSet.getInt("quantity");
                        System.out.println(id + "\t\t" + name + "\t\t" + description + "\t\t" + price + "\t\t" + quantity);
                    }
                    break;
                    
                default:
                    System.out.println("Invalid choice.");
            }
            
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }}