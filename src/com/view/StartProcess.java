package com.view;

import com.beans.User;
import com.controller.CommandName;
import com.controller.Controller;
import com.service.UserService;
import com.service.factory.ServiceFactory;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StartProcess {
    int id = -1;

    Controller controller;
    ServiceFactory instance = ServiceFactory.getInstance();
    UserService service = instance.getUserService();

    public StartProcess() {
        controller = new Controller();
        startMenu();
    }

    String sendCommand = "";

    public void startMenu() {
        while (true) {
            System.out.println("1-Sign In");
            System.out.println("2-Register new accaunt");
            System.out.println("0-Exit");

            int choice = getNumFromRange(2);

            switch (choice) {
                case 1:
                    sendCommand = controller.executeTask("SIGN_IN " + enterLogin());
                    id = Integer.parseInt(sendCommand.split("!!")[0]);
                    if (id > 0) {
                        System.out.println("You are welcome");
                        userMenu();
                    } else System.out.println("Wrong login or password");
                    break;
                case 2:
                    System.out.println(controller.executeTask(CommandName.REGISTRATION, enterAccInfo()));
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Choose the option");
            }
        }
    }

    public void userMenu() {
        while (true) {
            System.out.println("1-Show all users");
            System.out.println("2-Edit User");
            System.out.println("3-Show user info");
            System.out.println("4-Delete user");
            System.out.println("5-Sign Out");
            System.out.println("0-Exit");
            int choice = getNumFromRange(5);
            switch (choice) {
                case 1:
                    System.out.println(controller.executeTask("SHOW_ALL_USERS"));
                    break;
                case 2:
                    System.out.println(controller.executeTask(CommandName.EDIT_USER, editUser()));
                    break;
                case 3:
                    System.out.println("Enter id of user");
                    System.out.println(controller.executeTask("SHOW_USER_INFO " + getNumFromRange(1000)));
                    break;
                case 4:
                    System.out.println(controller.executeTask("DELETE_USER " + deleteUser()));
                    break;
                case 0:
                case 5:
                    System.out.println(controller.executeTask("SIGN_OUT"));
                    return;

                default:
                    System.out.println("Choose the option");
            }
            clearScreen();
        }
    }

    public int getNumFromRange(int range) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            if (in.hasNextInt()) {
                int val = in.nextInt();
                if (val >= 0 && val <= range) {
                    return val;
                } else System.out.println("Wrong input");
            } else {
                System.out.println("Enter digit");
                in.next();
            }
        }
        return -1;
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

    public int deleteUser() {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter id of user you want delete");
        System.out.println(controller.executeTask("SHOW_ALL_USERS"));
        int delId = getNumFromRange(1000);
        if (warning(in)) {
            return delId;
        }
        return -1;
    }

    public User editUser() {
        System.out.println("Enter id of user you want edit");
        System.out.println(controller.executeTask("SHOW_ALL_USERS"));
        int editId = getNumFromRange(1000);
        User us = new User();

        us = enterAccInfo();
        us.setId(editId);
        return us;
    }

    public boolean warning(Scanner in) {
        System.out.println("Are you sure?");
        System.out.println("1 - Yes \n 2- No");

        return in.nextInt() == 1;
    }


    public User enterAccInfo() {
        User user = new User();
        System.out.println("Hi! You can create or edit account here, follow the instructions: ");
        Scanner in = new Scanner(System.in);
        System.out.println("Enter new login: ");
        user.setLogin(in.next());
        System.out.println("Enter new password: ");
        user.setPassword(in.next());
        System.out.println("Enter your Name: ");
        user.getInfo().setName(in.next());
        System.out.println("Enter your Surname: ");
        user.getInfo().setSurname(in.next());
        System.out.println("Enter your email: ");

        user.getInfo().setEmail(getCorrectEmail(in));
        System.out.println("Enter your roles (3 is maximum): ");
        for (int i = 0; i < 3; i++) {
            user.getInfo().setRole(in.next());
            if (!multipleDataEntering())
                break;
            if (i == 2) System.out.println("You can't add more roles!");
        }
        System.out.println("Enter your phones(3 is maximum): ");
        for (int i = 0; i < 3; i++) {
            user.getInfo().setPhone(getCorrectPhone(in));
            if (!multipleDataEntering())
                break;
            if (i == 2) System.out.println("You can't add more phones!");
        }

        return user;
    }


    public static void clearScreen() {

        System.out.print("\033[H\033[2J");

        System.out.flush();

    }


    public boolean multipleDataEntering() {
        int choice;
        System.out.println("Do you have one more?");
        System.out.println("1- Yes \n 2-No");
        choice = getNumFromRange(3);
        return choice == 1;
    }


    String getCorrectEmail(Scanner in) {
        String mail = in.next();
        for (int i = 0; i < 3; i++) {
            if (!isEmailCorrect(mail)) {
                System.out.println("Wrong email!");
                mail = in.next();
            } else return mail;
        }
        return "";
    }

    String getCorrectPhone(Scanner in) {
        String phone = in.next();
        for (int i = 0; i < 3; i++) {
            if (!isPhoneCorrect(phone)) {
                System.out.println("Wrong phone!");
                phone = in.next();
            } else return phone;
        }
        return "";
    }

    private boolean isEmailCorrect(String mail) {
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }

    private boolean isPhoneCorrect(String phone) {
        Pattern pattern = Pattern.compile("(\\+*)375\\d{9}");
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}
