package com.exposedbee.services;

import java.sql.*;

public class createDatabase {
    public static void create() throws SQLException {
        Connection connection= DriverManager.getConnection("jdbc:h2:~/test","sa","");
        PreparedStatement statement;
        try {
            statement = connection.prepareStatement("CREATE TABLE topics (slno int AUTO_INCREMENT, name varchar(255) NOT NULL, PRIMARY KEY(slno))");
            statement.executeUpdate();
//            System.out.println("success");

        }
        catch (SQLException se){
//            System.out.print(se);
        }
        try {
            statement = connection.prepareStatement("CREATE TABLE quizs (slno int AUTO_INCREMENT, name varchar(255) NOT NULL, PRIMARY KEY(slno))");
            statement.executeUpdate();
//            System.out.println("success");

        }
        catch (SQLException se){
//            System.out.print(se);
        }
        try {

            statement = connection.prepareStatement("CREATE TABLE questions (no int AUTO_INCREMENT, topic varchar(255) NOT NULL, label varchar(max) NOT NULL, difficulty int NOT NULL, answer int, optiona varchar(255), optionb varchar(255), optionc varchar(255), optiond varchar(255))");
            statement.executeUpdate();
//            System.out.println("success");

        }
        catch (SQLException se){
//            System.out.print(se);
        }
    }
}
