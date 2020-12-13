package com.controller;

import com.controller.impl.*;


import java.util.EnumMap;


final class CommandProvider {
    private final EnumMap<CommandName, Command> repository = new EnumMap<>(CommandName.class);

    CommandProvider() {
        repository.put(CommandName.SIGN_IN, new SignIn());
        repository.put(CommandName.REGISTRATION, new Register());
        repository.put(CommandName.SIGN_OUT, new SignOut());
        repository.put(CommandName.EDIT_USER, new EditUser());
        repository.put(CommandName.DELETE_USER, new DeleteUser());
        repository.put(CommandName.SHOW_ALL_USERS, new ShowAllUsers());
        repository.put(CommandName.SHOW_USER_INFO, new ShowUserInfo());


    }

    Command getCommand(String name) {
        CommandName comName;
        Command com;

        try {
            comName = CommandName.valueOf(name.toUpperCase());
            com = repository.get(comName);
        } catch (IllegalArgumentException | NullPointerException e) {
            com = repository.get(CommandName.WRONG_REQUEST);
        }
        return com;
    }
}
