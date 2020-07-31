package com.exposedbee.services;

import com.exposedbee.dataModels.quizTracker;

import java.sql.*;
import java.util.Scanner;
import java.util.Stack;

public class quizEditor {

    public void deleteQuiz(String qname) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        try {
//            System.out.println("insert into questions(topic, label, difficulty) " +
//                    "values('"+temp.topic+"','"+temp.label+"',"+temp.level+")");
            PreparedStatement statement = connection.prepareStatement("drop table "+qname);
            statement.executeUpdate();
            System.out.println("Quiz Removed");
            quizTracker qt1=new quizTracker();
            quiz t1=new quiz();
            t1.removequiz(qname);
            qt1.quiz.remove(qname);

        }
        catch (SQLException | InterruptedException se){
            System.out.println("Quiz not present");
        }
    }
    private void quizGenerator(String first) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        try {
//            System.out.println("insert into questions(topic, label, difficulty) " +
//                    "values('"+temp.topic+"','"+temp.label+"',"+temp.level+")");
//            System.out.println(first);
            PreparedStatement statement = connection.prepareStatement(first);
            int r=statement.executeUpdate();

        }
        catch (SQLException se){
            System.out.println("Quiz not created");
        }
    }

    public void generateQuiz(Stack<String> topics, String qname) throws SQLException, InterruptedException {

//        String second=generatetopic(topics);
//        System.out.println(second);
        String first=generatefirst(qname,topics);
        quizGenerator(first);
        quizTracker qt1=new quizTracker();
        quiz t1=new quiz();
        t1.setNewQuiz(qname);
        qt1.quiz.push(t1.getNewTopic());
        t1.quizAdd();

    }

    private String generatetopic(Stack<String> topics) {
        StringBuilder t = new StringBuilder("topic= '" + topics.pop() + "' ");
        for (String temp : topics) {
            t.append("or topic='").append(temp).append("'");
        }
        return t.toString();
    }
    private String generatefirst(String qname, Stack<String> topics){
        int choice = 0, qlength = 0;
        OUTER_LOOP:
        try {
            System.out.println("Type of quiz");
            System.out.println("[1]Multiple choice quiz");
            System.out.println("[2]Written quiz");
            System.out.print("Enter your choice:");
            choice = Integer.parseInt(new Scanner(System.in).nextLine());
            System.out.print(("Enter total number of questions:"));
            qlength = Integer.parseInt(new Scanner(System.in).nextLine());
            if(choice==1)
                return "Create Table "+qname+" AS (select * from questions WHERE ("+generatetopic(topics)+") and answer is not null ORDER BY RAND() LIMIT "+qlength+")";
            else
                return "Create Table "+qname+" AS (select * from questions WHERE ("+generatetopic(topics)+") and answer is null ORDER BY RAND() LIMIT "+qlength+")";
        }
        catch (Exception s){
            System.out.println("Incorrect Input: Please try again");
            break OUTER_LOOP;
        }
        return null;
    }



}
