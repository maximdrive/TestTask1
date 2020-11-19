package com.controller.impl;

import com.beans.User;
import com.controller.Command;
import com.controller.exception.ParseException;
import com.service.UserService;
import com.service.exception.ServiceException;
import com.service.factory.ServiceFactory;

import java.util.ArrayList;

public class ShowUserInfo implements Command {

    @Override
    public String execute(String request) {
        int idUser;
        String response;

        try{
            if(request.isEmpty()) throw new ParseException("Null data");
            idUser = Integer.parseInt(request.split(" ")[1]);

            ServiceFactory instance = ServiceFactory.getInstance();
            UserService service = instance.getUserService();

            User user = service.showUserInfo(idUser);
            response = user.toString();
        } catch (ParseException | ServiceException e) {
            response = e.getMessage();
        }
        return response;
    }

    @Override
    public String execute(User user) {
        return null;
    }
}
