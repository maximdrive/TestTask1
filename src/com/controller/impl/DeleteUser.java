package com.controller.impl;

import com.beans.User;
import com.controller.Command;
import com.controller.exception.ParseException;
import com.service.UserService;
import com.service.exception.ServiceException;
import com.service.factory.ServiceFactory;

public class DeleteUser implements Command {

    @Override
    public String execute(String request) {
        int idUser;
        String response = "";
        try {
            if (request.isEmpty()) throw new ParseException("Null data");
            idUser = Integer.parseInt(request.split(" ")[1]);

            ServiceFactory instance = ServiceFactory.getInstance();
            UserService service = instance.getUserService();

            service.deleteUser(idUser);
            response = "Deleted Succesfully";
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
