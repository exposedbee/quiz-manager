package com.exposedbee.services;

import java.sql.*;

public class topics {

    private String newTopic;

    public String getNewTopic() {
        return newTopic;
    }

    public void setNewTopic(String newTopic) {
        this.newTopic = newTopic;
    }

    public void topicsAdd() throws SQLException {
        //TODO:Existing topics display

        Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");

        PreparedStatement statement;
        statement = connection.prepareStatement("INSERT INTO topics(name) VALUES('" + getNewTopic() + "')");
        int rs = statement.executeUpdate();
        System.out.println("New Topic added");


    }


    public void removeTopics(String topic) throws SQLException, InterruptedException {

//        System.out.println("you are in remove topics");
        Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        try {
            PreparedStatement statement;
            statement = connection.prepareStatement("DELETE FROM topics WHERE name='" + topic + "'");
            statement.executeUpdate();
            System.out.println("Topic removed");
            Thread.sleep(2000);
            statement = connection.prepareStatement("DELETE FROM questions WHERE topic=" + topic);
            statement.executeUpdate();
        }
        catch (SQLException se){
            System.out.println("Topic could not be removed(pls try again later)");
            Thread.sleep(2000);
        }

    }


}
