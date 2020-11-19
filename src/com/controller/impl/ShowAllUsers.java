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
        try {
            ArrayList<User> users;
            String responce = "";

            ServiceFactory serviceFactoryObj = ServiceFactory.getInstance();
            UserService userService = serviceFactoryObj.getUserService();

            try {
                users = userService.showUsers();
                for (User user : users) {
                    responce += user + "\n";
                }
                return responce;

            } catch (ServiceException e) {
                e.printStackTrace();
                responce = e.getMessage();
            }
            return responce;
        }catch (Exception e){
            return e.getLocalizedMessage();
        }
    }

    @Override
    public String execute(User user) {
        return null;
    }
}
