package com.view;

import com.beans.PersonInfo;
import com.beans.roles.Roles;
import com.beans.User;
import com.beans.roles.RolesService;

import java.util.*;

public class InputServices {
    Scanner in;
    public InputServices(){
        in = new Scanner(System.in);
    }

    public int getNumFromRange(int range) {
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

    public boolean multipleDataEntering() {
        int choice;
        System.out.println("Do you have one more?");
        System.out.println("1- Yes \n 2-No");
        choice = getNumFromRange(3);
        return choice == 1;
    }
    public User enterAccInfo() {
        User user = new User();
        System.out.println("Hi! You can create or edit account here, follow the instructions: ");
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
            Roles role = getCorrectRole(user);
            if(role != null)
                user.getInfo().setRole(role);
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

    public String getCorrectEmail(Scanner in) {
        String mail = in.next();
        for (int i = 0; i < 3; i++) {
            if (!PersonInfo.isEmailCorrect(mail)) {
                System.out.println("Wrong email!");
                mail = in.next();
            } else return mail;
        }
        return "";
    }

    public String getCorrectPhone(Scanner in) {
        String phone = in.next();
        for (int i = 0; i < 3; i++) {
            if (!PersonInfo.isPhoneCorrect(phone)) {
                System.out.println("Wrong phone!");
                phone = in.next();
            } else return phone;
        }
        return "";
    }

    public Roles getCorrectRole(User us){
        RolesService service = new RolesService();
        EnumMap<Roles,Integer> roleMap = service.getRoleMap();

        if (service.isSuperAdminExists(us.getInfo().getRole())){
            System.out.println("You are SUPER_ADMIN, can't choose other role");
            return null;
        }

        System.out.println("Choose your role: ");
        System.out.println("0-" + Roles.USER + " :Level 1" +"\n" +
                "1-" + Roles.CUSTOMER + " :Level 1" + "\n" +
                "2-" + Roles.ADMIN + " :Level 2" + "\n" +
                "3-" + Roles.PROVIDER +" :Level 2" + "\n" +
                "4-" + Roles.SUPER_ADMIN + " :Level 3");
        int choice = getNumFromRange(roleMap.size()-1);

        ArrayList<Roles> keys = new ArrayList<>(roleMap.keySet());
        Roles retRole = keys.get(choice);


        if(!service.isRoleLevelUnique(roleMap,retRole,us)){
            System.out.println("Level of chosen role is the same with your previous role");
            return null;
        }
        if(retRole.equals(Roles.SUPER_ADMIN)){
            us.getInfo().getRole().clear();
        }

        return retRole;
    }


}
