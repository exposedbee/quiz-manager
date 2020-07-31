package com.exposedbee.services;

import java.sql.*;

class quiz {

    private String newQuiz;

    public String getNewTopic() {
        return newQuiz;
    }

    public void setNewQuiz(String newTopic) {
        this.newQuiz = newTopic;
    }

    public void quizAdd() throws SQLException, InterruptedException {
        //TODO:Existing topics display

        Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");

        PreparedStatement statement;
        statement = connection.prepareStatement("INSERT INTO quizs(name) VALUES('" + getNewTopic() + "')");
        statement.executeUpdate();
        System.out.println("New Quiz added");
        Thread.sleep(1000);


    }


    public void removequiz(String topic) throws SQLException {

//        System.out.println("you are in remove topics");
        Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");

        PreparedStatement statement;
        statement = connection.prepareStatement("DELETE FROM quizs WHERE name='" + topic + "'");
        statement.executeUpdate();
    }


}
