package com.exposedbee.dataModels;


import java.sql.*;
import java.util.Scanner;
import java.util.Stack;

public class quizTracker {
    public Stack<String> quiz = new Stack<>();


//    public Stack<String> getTopics() {
//        return quiz;
//    }

    public String selectQuiz() {
        int count = 1;
        int i;
        if (!this.quiz.empty()) {
            for (String temp : this.quiz) {
                System.out.println("[" + count + "]-" + temp);
                count++;
            }
            do {
                System.out.println("Enter Your Choice: ");
                i = new Scanner(System.in).nextInt();
            } while (i >= count);
            i--;
            if (i != -1)
                return quiz.elementAt(i);
        }
        return null;
    }

    private void setQuiz() throws SQLException, InterruptedException {

        var connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        try {
            var statement = connection.prepareStatement("Select slno, name from quizs");
            var rs = statement.executeQuery();
            while (rs.next()) {
                this.quiz.push(rs.getString("name"));
            }

        } catch (SQLException se) {
            System.out.println("ALERT : NO Quiz Present");
            Thread.sleep(2000);

        }

    }

    public quizTracker() throws SQLException, InterruptedException {
        this.setQuiz();

    }


//    public int[] getKey() {
//        return key;
//    }
//
//    public void setKey(int[] key) {
//        this.key = key;
//    }

//    public Stack<String> selectQuizs() {
//        int count = 1;
//        OUTER:
//        if (!this.quiz.empty()) {
//            for (String temp : this.quiz) {
//                System.out.println("[" + count + "]-" + temp);
//                count++;
//            }
//
//            System.out.println("Enter Your Choices: ");
//            String choice = new Scanner(System.in).nextLine();
//            Stack<String> result = new Stack<>();
//            String[] splitted = choice.split("\\s+");
//            for (String i : splitted) {
//                try {
//                    result.push(this.quiz.elementAt(Integer.parseInt(i) - 1));
//                } catch (Exception s) {
//                    System.out.println("There was a error in selecting your choices");
//                    break OUTER;
//                }
//            }
//            return result;
//
//        }
//        return null;
//    }
}
