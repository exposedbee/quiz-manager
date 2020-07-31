package com.exposedbee;

import com.exposedbee.menu.mainMenu;
import com.exposedbee.services.createDatabase;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) throws SQLException, IOException, InterruptedException {
        createDatabase c = new createDatabase();
        c.create();
        mainMenu.mainMenuStart(true);

    }
}
