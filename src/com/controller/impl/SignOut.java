package com.controller.impl;

import com.beans.User;
import com.controller.Command;
import com.service.UserService;
import com.service.exception.ServiceException;
import com.service.factory.ServiceFactory;

public class SignOut implements Command {
    @Override
    public String execute(String request) {
        String response = "";
        ServiceFactory instance = ServiceFactory.getInstance();
        UserService service = instance.getUserService();

        try {
            service.signOut();
            response = "Bye! See you later!";
        } catch (ServiceException e) {
            response = e.getMessage();
        }
        return response;
    }

    @Override
    public String execute(User user) {
        return null;
    }
}
