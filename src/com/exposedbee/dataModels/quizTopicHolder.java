package com.exposedbee.dataModels;

import java.sql.*;
import java.util.Scanner;
import java.util.Stack;

public class quizTopicHolder {
    public Stack<String> topics = new Stack<>();
//    private int[] key;

//    public Stack<String> getTopics() {
//        return topics;
//    }

    public String selectTopic() {
        int count = 1;
        int i;
        if (!this.topics.empty()) {
            for (String temp : this.topics) {
                System.out.println("[" + count + "]-" + temp);
                count++;
            }
            do {
                System.out.println("[0] To Go Back");
                System.out.println("Enter Your Choice: ");
                i = new Scanner(System.in).nextInt();
            } while (i >= count);
            i--;
            if (i != -1)
                return topics.elementAt(i);
        }
        return null;
    }

    private void setTopics() throws SQLException {

        var connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        try {
            var statement = connection.prepareStatement("Select slno, name from topics");
            var rs = statement.executeQuery();
            while (rs.next()) {
                this.topics.push(rs.getString("name"));
            }

        } catch (SQLException se) {
            System.out.println("ALERT : NO TOPICS PERSENT");

        }

    }

    public quizTopicHolder() throws SQLException {
        this.setTopics();

    }


//    public int[] getKey() {
//        return key;
//    }
//
//    public void setKey(int[] key) {
//        this.key = key;
//    }

    public Stack<String> selectTopics() throws InterruptedException {
        int count = 1;
        OUTER:
        if (!this.topics.empty()) {
            for (String temp : this.topics) {
                System.out.println("[" + count + "]-" + temp);
                count++;
            }

//            System.out.println("Enter Your Choices: ");
            String choice = new Scanner(System.in).nextLine();
            Stack<String> result = new Stack<>();
            String[] splitted = choice.split("\\s+");
            for (String i : splitted) {
                try {
                    result.push(this.topics.elementAt(Integer.parseInt(i) - 1));
                } catch (Exception s) {
                    System.out.println("There was a error in selecting your choices");
                    Thread.sleep(1000);
                    break OUTER;
                }
            }
            return result;

        }
        return null;
    }
}
