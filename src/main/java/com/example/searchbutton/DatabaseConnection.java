package com.example.searchbutton;


import java.sql.*;

public class DatabaseConnection {






Connection connect = null;

    public Connection connection() {
         String url = "jdbc:mysql://localhost:3306/pocBase";
         String usr = "root";
         String pass = "";

        try {
            connect = DriverManager.getConnection(url, usr, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connect;
    }



}
