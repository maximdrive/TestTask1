package com.view;


import com.controller.Controller;



public class StartProcess {

    private final Controller controller;

    public StartProcess() {
        controller = new Controller();
        new LoginMenu(controller);
    }

}
