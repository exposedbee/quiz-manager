package com.exposedbee.menu;

import java.sql.SQLException;
import java.util.Scanner;

public class menu extends MenuTopics {
    private static menuQuestions q1=new menuQuestions();
    static void editMainMenu(boolean event) throws SQLException, InterruptedException {
        System.out.println("Edit Menu");
        do {

            switch(mainMenuDisplay()){
                case 0: event = false;
                break;
                case 1:
                    editMenuTopicsMain(true);
                    break;
                case 2:
                    q1.editMenuQuestionsMain(true);
                case 3:
                default:System.out.println("Incorrect input, Try again");
            }

        }while (event);
    }

    public static int mainMenuDisplay(){
        try {
            System.out.println(" > Main Menu > Edit Menu");
            System.out.println("\t[1] Topics");
            System.out.println("\t[2] Questions");
//        System.out.println("\t[3] Add new Questions");
            System.out.println("[0] main Menu");
            System.out.print("Enter your Choice:");
            return new Scanner(System.in).nextInt();
        }
        catch (Exception s){
            return 10;
        }
    }
}
