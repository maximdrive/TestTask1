package com.controller;

import com.beans.User;

public class Controller {
    private final CommandProvider provider = new CommandProvider();


    public String executeTask(String request) {
        String commandName;
        Command execCommand;

        commandName = request.split(" ")[0];
        execCommand = provider.getCommand(commandName);

        String response;
        response = execCommand.execute(request);

        return response;
    }

    public String executeTask(CommandName com, User user) {

        Command execCommand;
        execCommand = provider.getCommand(com.toString());
        return execCommand.execute(user);
    }
}
