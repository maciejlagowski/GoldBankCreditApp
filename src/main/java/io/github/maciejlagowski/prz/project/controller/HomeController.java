package io.github.maciejlagowski.prz.project.controller;

import io.github.maciejlagowski.prz.project.view.TypeAndClients;

public class HomeController implements Controller {

    private static HomeController instance;

    private HomeController() {
    }

    public static synchronized HomeController getInstance() {
        if (instance == null) {
            instance = new HomeController();
        }
        return instance;
    }

    public void onApplyForACreditButtonClick() {
        System.out.println("clicked apply");
        FrameController.getInstance().changeView(new TypeAndClients());
    }

    public void onManageCustomersButtonClick() {
        System.out.println("clicked manage cust");
    }
}
