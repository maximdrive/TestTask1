package com.controller.impl;

import com.beans.User;
import com.controller.Command;
import com.controller.exception.ParseException;
import com.service.UserService;
import com.service.exception.ServiceException;
import com.service.factory.ServiceFactory;

public class SignIn implements Command {
    @Override
    public String execute(String request) {
        String delimiter = "!!";

        String login;
        String password;
        String responce;

        int id;


        try {
            String data = request.split(" ", 2)[1];
            String[] parseData = data.split(delimiter);
            if (parseData.length < 2) throw new ParseException("Incorrect login or password");
            login = parseData[0];
            password = parseData[1];

            ServiceFactory serviceFactoryObj = ServiceFactory.getInstance();
            UserService userService = serviceFactoryObj.getUserService();

            id = userService.signIn(login, password);
            responce = String.valueOf(id);

        } catch (ParseException e) {
            return "-1" + "!!" + e.getMessage();
        } catch (ServiceException e) {
            responce = "-1" + "!!" + e.getMessage();
        }
        return responce;
    }

    @Override
    public String execute(User user) {
        return null;
    }
}
