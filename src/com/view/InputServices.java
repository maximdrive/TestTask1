package com.view;

import com.beans.PersonInfo;
import com.beans.User;

import java.util.Scanner;

public class InputServices {
    public static int getNumFromRange(int range) {
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

    public static boolean multipleDataEntering() {
        int choice;
        System.out.println("Do you have one more?");
        System.out.println("1- Yes \n 2-No");
        choice = getNumFromRange(3);
        return choice == 1;
    }
    public static User enterAccInfo() {
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
            if (!InputServices.multipleDataEntering())
                break;
            if (i == 2) System.out.println("You can't add more roles!");
        }
        System.out.println("Enter your phones(3 is maximum): ");
        for (int i = 0; i < 3; i++) {
            user.getInfo().setPhone(getCorrectPhone(in));
            if (!InputServices.multipleDataEntering())
                break;
            if (i == 2) System.out.println("You can't add more phones!");
        }

        return user;
    }

    public static String getCorrectEmail(Scanner in) {
        String mail = in.next();
        for (int i = 0; i < 3; i++) {
            if (!PersonInfo.isEmailCorrect(mail)) {
                System.out.println("Wrong email!");
                mail = in.next();
            } else return mail;
        }
        return "";
    }

    public static String getCorrectPhone(Scanner in) {
        String phone = in.next();
        for (int i = 0; i < 3; i++) {
            if (!PersonInfo.isPhoneCorrect(phone)) {
                System.out.println("Wrong phone!");
                phone = in.next();
            } else return phone;
        }
        return "";
    }
}
