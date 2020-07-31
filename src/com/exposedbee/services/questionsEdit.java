package com.exposedbee.services;

import com.exposedbee.dataModels.options;
import com.exposedbee.dataModels.questions;
import com.exposedbee.dataModels.quizTopicHolder;

import java.sql.*;
import java.util.Scanner;

public class questionsEdit {
    public void questionByTopics(String topic) throws SQLException, InterruptedException {
        Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        try {
//            System.out.println("insert into questions(topic, label, difficulty) " +
//                    "values('"+temp.topic+"','"+temp.label+"',"+temp.level+")");
            PreparedStatement statement = connection.prepareStatement("SELECT * from questions where topic='" + topic + "'");

            ResultSet rs = statement.executeQuery();
            int count = 1;
            int back = -1;

            OUTER_LOOP:
            while (rs.next() && back != 0) {
                System.out.println("Question" + count);
                System.out.println("Label : " + rs.getString("label"));
                System.out.println("Topic : " + rs.getString("topic"));
                System.out.println("diff : " + rs.getInt("difficulty"));
//                System.out.println("Answer: "+rs.getInt("ans"));

                String check = rs.getString("optiona");
                if (check != null) {
                    System.out.println("Options");
                    System.out.println("[1]" + rs.getString("optiona"));
                    System.out.println("[2]" + rs.getString("optionb"));
                    System.out.println("[3]" + rs.getString("optionc"));
                    System.out.println("[4]" + rs.getString("optiond"));
                    System.out.println("ans :" + rs.getInt("answer"));
                    System.out.println(rs.getInt(1));
                }

                int choice = 1;
                int no = rs.getInt("no");
                while (choice != 6) {
                    System.out.println("Edit [1]label  [2]Topic [3]Diff [4]Options [5]del [6]Next [0]back");
                    choice = new Scanner(System.in).nextInt();
                    switch (choice) {
                        case 0:
                            break OUTER_LOOP;
                        case 1:
                            System.out.println("Enter new label:");
                            String temp = new Scanner(System.in).nextLine();
                            statement = connection.prepareStatement("Update questions set label='" + temp + "' WHERE no=" + no);
                            statement.executeUpdate();
                            break;
                        case 2:
                            System.out.println("Enter new Topic:");
                            String t = new quizTopicHolder().selectTopic();
                            statement = connection.prepareStatement("Update questions set topic='" + t + "' WHERE no=" + no);
                            statement.executeUpdate();

                            break;
                        case 3:
                            System.out.println("Enter new diff <1,2,3>");
                            int diff = new Scanner(System.in).nextInt();
                            statement = connection.prepareStatement("Update questions set difficulty='" + diff + "' WHERE no=" + no);
                            statement.executeUpdate();
                            break;
                        case 4:
                            System.out.println("Enter new Options");
                            options op = new options();
                            op = optionacceptor();
                            statement = connection.prepareStatement("Update questions set optiona='" + op.getO1() + "', optionb='" + op.getO2() + "', optionc='" + op.getO3() + "', optiond='" + op.getO4() + "', answer=" + op.getAns() + " WHERE no=" + no);
                            statement.executeUpdate();
                            break;
                        case 5:
                            statement = connection.prepareStatement("DELETE FROM questions WHERE no=" + no);
                            statement.executeUpdate();
                            System.out.println("Question Deleted");
                            choice = 6;
                            break;
                        case 6:
                            break;
                        default:
                            System.out.println("Incorrect choice");
                    }
                }
                count++;
            }
            if(count==1){
                System.out.println("No Questions Present");
            }


        } catch (SQLException se) {
            System.out.println("ALERT : Question not Updated" + se);
            Thread.sleep(2000);

        }

    }

//    private static void editDiff(int diff, int no) throws SQLException {
//        System.out.println("In fin");
//        Connection connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
//
//    }

//    private static void editTopic(String t, int no) {
//    }
//
//    private static void editLabel(String temp, int no) {
//    }


    protected boolean questionAdd() throws SQLException {
        Scanner kb = new Scanner(System.in);
        quizTopicHolder tholder = new quizTopicHolder();
        System.out.println("Enter the total number of questions :");
        int count = Integer.parseInt(kb.nextLine());
        System.out.println("Do the questions have choices? y/n");
        String c = kb.nextLine();
//        System.out.println("Do the questions have options?");
//        String c=kb.nextLine();
        questions qholder = new questions();
        try {
            for (int i = 0; i < count; i++) {
                System.out.print("Q" + (i + 1) + " ");
                System.out.print("Label :");
                String tempLabel = kb.nextLine();
                System.out.println("Level <1.2.3>");
                int tempLevel = Integer.parseInt(kb.nextLine());
                System.out.println("Topic");
                String tempTopic = tholder.selectTopic();
                qholder.setLabel(tempLabel);
                qholder.setLevel(tempLevel);
                qholder.setTopic(tempTopic);
                options op = new options();
                if (c.equalsIgnoreCase("y")) {
                    int j = 1;
                    op = optionacceptor();
                    qholder.setO1(op.getO1());
                    qholder.setO2(op.getO2());
                    qholder.setO3(op.getO3());
                    qholder.setO4(op.getO4());
                    qholder.setAns(op.getAns());
                    qholder.setmultiplechoicequestions(qholder);

                } else {
                    qholder.setnonmultiplechoicequestions(qholder);
                }

            }
            return true;
        }
        catch (Exception se){
            return false;
        }

    }

    public options optionacceptor() {
        options op = new options();
        Scanner kb = new Scanner(System.in);
        int j = 1;
        System.out.println("Enter options:");
        System.out.print("[" + j++ + "]");
        op.setO1(kb.nextLine());
        System.out.print("[" + j++ + "]");
        op.setO2(kb.nextLine());
        System.out.print("[" + j++ + "]");
        op.setO3(kb.nextLine());
        System.out.print("[" + j++ + "]");
        op.setO4(kb.nextLine());
        System.out.print("ans=");
        op.setAns(Integer.parseInt(kb.nextLine()));
        return op;
    }

}
