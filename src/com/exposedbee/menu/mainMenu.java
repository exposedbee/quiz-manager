package com.exposedbee.menu;


import com.exposedbee.dataModels.quizTopicHolder;
import com.exposedbee.dataModels.quizTracker;
import com.exposedbee.services.fileWriter;
import com.exposedbee.services.quizEditor;
import com.exposedbee.services.quizPlayer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.Stack;

public class mainMenu extends menu {
    public static void mainMenuStart(boolean start) throws SQLException, IOException, InterruptedException {
        // write your code here

        //Info: Main Menu Display
        do {

            switch (mainMenuDisplay()) {
                case 1:
                    editMainMenu(true);
                    break;
                case 2:
                    createQuiz();
                    break;
                case 3:
                    deleteQuiz();
                    break;
                case 4:
                    playQuiz();
                    break;
                case 5:
                    write();
                    break;
                case 6:System.out.println("Thank you \n");
                    start = false;
                    break;
                default:
                    System.out.println("Incorrect input, Try again");
            }

        } while (start);
    }

    private static void write() throws IOException, SQLException, InterruptedException {
        Scanner kb=new Scanner(System.in);
        fileWriter f=new fileWriter();
        System.out.print("Enter the file name : ");
        String fname=kb.nextLine();
        System.out.println("Select the quiz : ");
        String qname=new quizTracker().selectQuiz();
        if(qname!=null)
            f.quizToFile(qname,fname);
        else
            System.out.println("No Quiz Present(Note try creating a quiz first)");
        Thread.sleep(2000);
    }

    private static void playQuiz() {
        Scanner kb=new Scanner(System.in);
        String sname, qname;
        Outer:
        try {

            System.out.print("Enter your name :");
            sname=kb.nextLine();
            System.out.println("Select which quiz you want to play :");
            qname = new quizTracker().selectQuiz();
            quizPlayer qplay=new quizPlayer();
            qplay.quizStart(sname,qname);
        }
        catch (Exception e){
            break Outer;
        }
    }

    private static void deleteQuiz() throws SQLException, InterruptedException {
        quizEditor quiz=new quizEditor();
        System.out.println("Select quiz:");
        String t = new quizTracker().selectQuiz();
//        String qname=new Scanner(System.in).nextLine();
        quiz.deleteQuiz(t);
    }

    private static void createQuiz() throws SQLException, InterruptedException {
        System.out.println("Enter Topic Choice(s):");
        quizTopicHolder tholder= new quizTopicHolder();
        Stack<String> topics;
        topics=tholder.selectTopics();
        if(!topics.empty()){
            quizEditor quiz=new quizEditor();
            System.out.println("Enter Quiz Name:");
            String qname=new Scanner(System.in).nextLine();
            quiz.generateQuiz(topics,qname);
        }
    }


    public static int mainMenuDisplay() {
        System.out.println(" > Main Menu");
        System.out.println("\t[1] Edit Questions and Topics");
        System.out.println("\t[2] Create new Quiz");
        System.out.println("\t[3] Delete quiz");
        System.out.println("\t[4] Play Quiz");
        System.out.println("\t[5] Quiz to file");
        System.out.println("\t[6] Quit");
        System.out.print("Enter your Choice:");
        return new Scanner(System.in).nextInt();
    }

}
