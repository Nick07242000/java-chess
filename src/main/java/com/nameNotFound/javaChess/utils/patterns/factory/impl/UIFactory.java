package com.nameNotFound.javaChess.utils.patterns.factory.impl;

import com.nameNotFound.javaChess.userInterface.UserInterface;
import com.nameNotFound.javaChess.userInterface.impl.ConsoleUI;
import com.nameNotFound.javaChess.userInterface.impl.WindowUI;
import com.nameNotFound.javaChess.utils.patterns.factory.Factory;

public class UIFactory extends Factory {
    private static UIFactory instance;

    private UIFactory() {}

    public static UIFactory getInstance() {
        if (instance == null)
            instance = new UIFactory();
        return instance;
    }

    @Override
    public final UserInterface build(String type) {
        return switch (type) {
            case "window" -> new WindowUI();
            default -> new ConsoleUI();
        };
    }
}
