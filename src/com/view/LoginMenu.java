package com.view;

import com.controller.CommandName;
import com.controller.Controller;

import java.util.Scanner;

public class LoginMenu {

    private Controller controller;
    private String sendCommand = "";

    public LoginMenu(Controller controller){
        this.controller = controller;
        startMenu();
    }

    private int id = -1;
    public void startMenu() {
        while (true) {
            System.out.println("1-Sign In");
            System.out.println("2-Register new accaunt");
            System.out.println("0-Exit");

            int choice = InputServices.getNumFromRange(2);

            switch (choice) {
                case 1:
                    sendCommand = controller.executeTask("SIGN_IN " + enterLogin());
                    id = Integer.parseInt(sendCommand.split("!!")[0]);
                    if (id > 0) {
                        System.out.println("You are welcome");
                        new UserMenu(controller);
                    } else System.out.println("Wrong login or password");
                    break;
                case 2:
                    System.out.println(controller.executeTask(CommandName.REGISTRATION, InputServices.enterAccInfo()));
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Choose the option");
            }
        }
    }

    public String enterLogin() {
        String login;
        String password;
        System.out.println("Enter your login: ");
        Scanner in = new Scanner(System.in);
        login = in.next();

        System.out.println("Enter your password: ");
        password = in.next();
        return login + "!!" + password;
    }
}
