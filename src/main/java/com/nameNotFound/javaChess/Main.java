package com.nameNotFound.javaChess;

import com.nameNotFound.javaChess.service.Game;
import com.nameNotFound.javaChess.userInterface.UserInterface;
import com.nameNotFound.javaChess.utils.patterns.factory.impl.UIFactory;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UIFactory uiFactory = UIFactory.getInstance();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        System.out.println("||=============================================||");
        System.out.println("||    ¿Como desea visualizar la aplicación?    ||");
        System.out.println("||                                             ||");
        //System.out.println("|| 'window'  --> Vista por Ventanas            ||");
        System.out.println("|| 'console' --> Vista por Consola             ||");
        System.out.println("||=============================================||");
        System.out.print("\nopcion: ");
        Scanner scanner = new Scanner(System.in);
        UserInterface userInterface = uiFactory.build(scanner.nextLine());
        Game game = Game.getInstance();
        game.attach(userInterface);
        userInterface.play();
    }
}
