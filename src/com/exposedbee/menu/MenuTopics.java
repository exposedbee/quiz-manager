package com.exposedbee.menu;

import com.exposedbee.dataModels.quizTopicHolder;
import com.exposedbee.services.topics;

import java.sql.SQLException;
import java.util.Scanner;

public class MenuTopics {
    public static void editMenuTopicsMain(boolean event) throws SQLException, InterruptedException {
        topics t1=new topics();
        String temp=null;
        quizTopicHolder hold1=new quizTopicHolder();
        do {

            switch(mainMenuDisplay()){
                case 0: event = false;
                    break;
                case 1:
                    System.out.println("Enter the new topic: ");
                    temp=new Scanner(System.in).nextLine();
                    if(!temp.isBlank()) {
                        t1.setNewTopic(temp);
                        hold1.topics.push(t1.getNewTopic());
                        t1.topicsAdd();
                        temp = null;
                    }
                    break;
                case 2:
                    temp = hold1.selectTopic();
                    if (temp!=null) {
                        System.out.println(temp);

                        t1.removeTopics(temp);
                        hold1.topics.remove(temp);
                        temp=null;
                        break;
                    }
                    else
                        System.out.println("No Topic Present");
                    break;
                case 3:
                default:System.out.println("Incorrect input, Try again");
            }

        }while (event);
    }

    public static int mainMenuDisplay(){
        System.out.println(" > Main Menu > Edit Menu > Topics");
        System.out.println("\t[1] Add Topics");
        System.out.println("\t[2] Remove Topics");
//        System.out.println("\t[3] Rename Topics");
        System.out.println("[0] Edit Menu");
        System.out.print("Enter your Choice:");
        return new Scanner(System.in).nextInt();
    }

}
