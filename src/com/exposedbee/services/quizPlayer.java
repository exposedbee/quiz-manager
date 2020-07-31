package com.exposedbee.services;


import java.sql.*;
import java.util.Scanner;
import java.util.Stack;

public class quizPlayer {

    public void quizStart(String sname,String qname) {
        Stack<Integer> ids=new Stack<>();
        getquestion(qname,sname);
    }

    private boolean getquestion(String qname,String sname){
        try{
            ResultSet rs=retriveQuiz(qname);
            int count=1;
            int result=0;
            while(rs.next()){
//                System.out.println(rs);
                result+=displayQuestion(rs,count);
                count++;

            }
            count--;
            System.out.println("Student name:"+sname+"/n Total Questions attended:"+count+
                    "\nYou Scored="+result);
            Thread.sleep(2000);
        }
        catch (Exception s){

        }
        return false;
    }

    public ResultSet retriveQuiz(String qname) throws SQLException, InterruptedException {
        var connection = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
        try {
            PreparedStatement statement = connection.prepareStatement("Select * from " + qname + " Where answer is not null");
            return statement.executeQuery();
        }
        catch(SQLException se){
            System.out.println("No Quiz Present(Try creating a Quiz first)");
            Thread.sleep(2000);
            return null;
        }
    }

    private int displayQuestion(ResultSet rs, int count) throws SQLException {
        Scanner kb=new Scanner(System.in);
        int choice=-1;
        System.out.println("Q"+count+" > "+rs.getString("label"));
        OUTER:
        try {
            System.out.println("[1] > " + rs.getString("optiona"));
            System.out.println("[2] > " + rs.getString("optionb"));
            System.out.println("[3] > " + rs.getString("optionc"));
            System.out.println("[4] > " + rs.getString("optiond"));
            System.out.print("ans > ");
            choice = Integer.parseInt(kb.nextLine());
        }
        catch (Exception e){
            break OUTER;
        }
        if(choice==rs.getInt("answer"))
                return 1;
        return 0;
    }
}
