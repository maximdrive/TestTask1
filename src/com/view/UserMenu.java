package com.view;

import com.beans.User;
import com.controller.CommandName;
import com.controller.Controller;



public class UserMenu {
    private InputServices input;
    private Controller controller;
    public UserMenu(Controller controller,InputServices input){
        this.controller = controller;
        this.input = input;
        userMenu();
    }

    public void userMenu() {
        while (true) {
            System.out.println("1-Show all users");
            System.out.println("2-Edit User");
            System.out.println("3-Show user info");
            System.out.println("4-Delete user");
            System.out.println("5-Sign Out");
            System.out.println("0-Exit");
            int choice = input.getNumFromRange(5);
            switch (choice) {
                case 1:
                    System.out.println(controller.executeTask("SHOW_ALL_USERS"));
                    break;
                case 2:
                    System.out.println(controller.executeTask(CommandName.EDIT_USER, editUser()));
                    break;
                case 3:
                    System.out.println("Enter id of user");
                    System.out.println(controller.executeTask("SHOW_USER_INFO " + input.getNumFromRange(1000)));
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

        }
    }

    public int deleteUser() {
        System.out.println("Enter id of user you want delete");
        System.out.println(controller.executeTask("SHOW_ALL_USERS"));
        int delId = input.getNumFromRange(1000);
        if (warning()) {
            return delId;
        }
        return -1;
    }

    public User editUser() {
        System.out.println("Enter id of user you want edit");
        System.out.println(controller.executeTask("SHOW_ALL_USERS"));
        int editId = input.getNumFromRange(1000);
        User us;

        us = input.enterAccInfo();
        us.setId(editId);
        return us;
    }

    public boolean warning() {
        System.out.println("Are you sure?");
        System.out.println("1 - Yes \n 2- No");

        return input.getNumFromRange(2) == 1;
    }



}
