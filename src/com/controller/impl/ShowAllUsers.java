package com.controller.impl;

import com.beans.User;
import com.controller.Command;
import com.service.UserService;
import com.service.exception.ServiceException;
import com.service.factory.ServiceFactory;

import java.util.ArrayList;

public class ShowAllUsers implements Command {
    @Override
    public String execute(String request) {
        ArrayList<User> users;
        String responce = "";

        ServiceFactory serviceFactoryObj = ServiceFactory.getInstance();
        UserService userService = serviceFactoryObj.getUserService();

        try {
            users = userService.showUsers();
            StringBuilder bld = new StringBuilder();
            for (User user : users) {
                bld.append(user).append("\n");
            }
            responce = bld.toString();

        } catch (ServiceException e) {
            e.printStackTrace();
            responce = e.getMessage();
        }
        return responce;
    }

    @Override
    public String execute(User user) {
        return null;
    }
}
