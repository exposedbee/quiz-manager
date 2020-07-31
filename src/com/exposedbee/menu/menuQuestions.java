package com.exposedbee.menu;

import com.exposedbee.dataModels.quizTopicHolder;
import com.exposedbee.services.questionsEdit;

import java.sql.SQLException;
import java.util.Scanner;

public class menuQuestions extends questionsEdit {
    public void editMenuQuestionsMain(boolean event) throws SQLException, InterruptedException {
//        questions[] q1=new questions[];
        String temp = null;
        quizTopicHolder hold1 = new quizTopicHolder();
        do {

            switch (mainMenuDisplay()) {
                case 0:
                    event = false;
                    break;
                case 1:
                    temp = hold1.selectTopic();
                    if (temp != null) {
                        questionByTopics(temp);
                    }
                    break;
                case 2:
                    questionAdd();
                    break;
                case 3:
                    break;
                default:
                    System.out.println("Incorrect input, Try again");
            }

        } while (event);
    }

    public static int mainMenuDisplay() {
        System.out.println(" > Main Menu > Edit Menu > Questions");
        System.out.println("\t[1] View and edit Questions");
        System.out.println("\t[2] Add new Questions");
//        System.out.println("\t[3] Delete all questions");
        System.out.println("[0] Edit Menu");
        System.out.print("Enter your Choice:");
        return new Scanner(System.in).nextInt();
    }
}
