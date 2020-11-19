package com.controller.impl;

import com.beans.User;
import com.controller.Command;
import com.service.UserService;
import com.service.exception.ServiceException;
import com.service.factory.ServiceFactory;

public class Register implements Command {

    @Override
    public String execute(String request) {
        return null;
    }

    @Override
    public String execute(User user) {
        String response = "";
        ServiceFactory istance = ServiceFactory.getInstance();
        UserService service = istance.getUserService();

        try{
            service.Registration(user);
            response = "Registered succesfully";
        } catch (ServiceException e) {
            response = e.getMessage();
        }
        return response;
    }
}
