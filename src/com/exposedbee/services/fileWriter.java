package com.exposedbee.services;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class fileWriter {
    public void quizToFile(String qname, String fileName)
            throws IOException, SQLException, InterruptedException {
        FileWriter fileWriter = new FileWriter(fileName+".txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);


        try {
            ResultSet rs = retriveQuiz(qname);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd//MM//yyyy");
            LocalDateTime now = LocalDateTime.now();
            System.out.println("Do you want the answer to be printed(y/n)");
            String c=new Scanner(System.in).nextLine();
            printWriter.printf("Quiz name :"+qname+"\n Created on: "+dtf.format(now)+"\n\n");
            int count=1;
            while (rs.next()) {
                printWriter.printf("Q"+count+" >" + rs.getString("label"));
                printWriter.printf("\n");
                if(rs.getString("optiona")!=null) {
                    printWriter.printf("\t1-" + rs.getString("optiona"));
                    printWriter.printf("\n");
                    printWriter.printf("\t1-" + rs.getString("optiona"));
                    printWriter.printf("\n");
                    printWriter.printf("\t1-" + rs.getString("optiona"));
                    printWriter.printf("\n");
                    printWriter.printf("\t1-" + rs.getString("optiona"));
                    printWriter.printf("\n");
                    if(c.equalsIgnoreCase("y")) {
                        printWriter.printf("ans >" + rs.getInt("answer"));
                        printWriter.printf("\n");
                    }
                    else{
                        printWriter.printf("ans >");
                        printWriter.printf("\n");
                    }
                    printWriter.printf("\n\n");
                }
                else{
                    printWriter.printf("ans >");
                    printWriter.printf("\n\n\n\n");
                }
                count++;
            }
        }
        catch (NullPointerException se){
            System.out.println("File cannot be created-Try with a different name");
            Thread.sleep(2000);
        }
        printWriter.close();
    }


    //Quiz retriever:to get the quiz that has to be written inside a file
    public ResultSet retriveQuiz(String qname) throws SQLException, InterruptedException {
        var connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        try {
            PreparedStatement statement = connection.prepareStatement("Select * from " + qname );
            return statement.executeQuery();
        }
        catch(SQLException se){
            System.out.println("Quiz Cannot be played(only mcq quiz can be played)\n Try writing it to a file");
            Thread.sleep(2000);
            return null;
        }
    }
}
