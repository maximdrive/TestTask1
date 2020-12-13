package com.view;

import com.controller.CommandName;
import com.controller.Controller;

import java.util.Scanner;

public class LoginMenu {

    private InputServices input;
    private Controller controller;


    public LoginMenu(Controller controller){
        this.controller = controller;
        input = new InputServices();
        startMenu();
    }

    public void startMenu() {
        String sendCommand = "";
        while (true) {
            System.out.println("1-Sign In");
            System.out.println("2-Register new accaunt");
            System.out.println("0-Exit");

            int choice = input.getNumFromRange(2);

            switch (choice) {
                case 1:
                    sendCommand = controller.executeTask("SIGN_IN " + enterLogin());
                    int id = Integer.parseInt(sendCommand.split("!!")[0]);
                    if (id >= 0) {
                        System.out.println("You are welcome");
                        new UserMenu(controller,input);
                    } else System.out.println("Wrong login or password");
                    break;
                case 2:
                    System.out.println(controller.executeTask(CommandName.REGISTRATION,input.enterAccInfo()));
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
