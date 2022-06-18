package com.nameNotFound.javaChess;

import com.nameNotFound.javaChess.service.Game;
import com.nameNotFound.javaChess.userInterface.UserInterface;
import com.nameNotFound.javaChess.utils.patterns.factory.impl.UIFactory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UIFactory uiFactory = UIFactory.getInstance();
//        System.out.println("Como desea visualizar la aplicacion?");
//        Scanner scanner = new Scanner(System.in);
        UserInterface userInterface = uiFactory.build("console");
        Game game = Game.getInstance();
        game.attach(userInterface);
        while(!game.isCheckmate()) {
            userInterface.showTurn();
            userInterface.movePiece();
        }
        System.out.println(UserInterface.CHECKMATE_MESSAGE);
    }
}
