package com.controller;

import com.beans.User;

public interface Command {
    public String execute(String request);
    public String execute(User user);
}
