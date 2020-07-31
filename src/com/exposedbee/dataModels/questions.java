package com.exposedbee.dataModels;

import java.sql.*;

public class questions extends options {
    private String label, topic;
    private int level;

//    public String getLabel() {
//        return label;
//    }

    public void setLabel(String label) {
        this.label = label;
    }

//    public int getLevel() {
//        return level;
//    }

    public void setLevel(int level) {
        this.level = level;
    }

//    public String getTopic() {
//        return topic;
//    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setmultiplechoicequestions(questions temp) throws SQLException {


        Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        try {
//            System.out.println("insert into questions(topic, label, difficulty) " +
//                    "values('"+temp.topic+"','"+temp.label+"',"+temp.level+")");
            PreparedStatement statement = connection.prepareStatement("insert into questions(topic, label, difficulty, answer, optiona, optionb, optionc, optiond) " +
                    "values('" + temp.topic + "','" + temp.label + "'," + temp.level + "," + temp.ans + ",'" + temp.o1 + "','" + temp.o2 + "','" + temp.o3 + "','" + temp.o4 + "');");

            statement.executeUpdate();


        } catch (SQLException se) {
            System.out.println("ALERT : Question not added");

        }

    }

    public void setnonmultiplechoicequestions(questions temp) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        try {
//            System.out.println("insert into questions(topic, label, difficulty) " +
//                    "values('"+temp.topic+"','"+temp.label+"',"+temp.level+")");
            PreparedStatement statement = connection.prepareStatement("insert into questions(topic, label, difficulty) " +
                    "values('" + temp.topic + "','" + temp.label + "'," + temp.level + ")");

            statement.executeUpdate();


        } catch (SQLException se) {
            System.out.println("ALERT : Question not added");

        }
    }
}
