package com.nameNotFound.javaChess.userInterface.impl;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import com.nameNotFound.javaChess.service.GameService;
import com.nameNotFound.javaChess.userInterface.UserInterface;

public class WindowUI extends UserInterface implements ActionListener {
    private GameService gameService = GameService.getInstance();
    private JFrame menuWindow;
    private JFrame boardWindow;
    private JFrame takenPiecesWindow;

    public WindowUI() {
        createMenuWindow();
        update();
    }

    @Override
    public void requestUserInput(String message) {

    }

    @Override
    public String getInput() {
        return null;
    }

    @Override
    public void showBoard() {

    }

    public void resetBoard() {

    }

    @Override
    public void showTakenPieces() {

    }

    @Override
    public void movePiece() {

    }

    @Override
    public void showTurn() {

    }

    @Override
    public void update() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        switch (command) {
            case "Un Jugador" -> createGameWindows(1);
            case "Dos Jugadores" -> createGameWindows(2);
        }
    }

    private void createGameWindows(int players) {
        boardWindow = new JFrame("Board");
        takenPiecesWindow = new JFrame("Taken Pieces");
        Container boardContainer = boardWindow.getContentPane();
        Container takenPiecesContainer = takenPiecesWindow.getContentPane();
        JButton singlePlayer = new JButton("Un Jugador");
        boardContainer.add(singlePlayer);
        boardWindow.pack();
        boardWindow.setVisible(true);
    }

    private void createMenuWindow() {
        menuWindow = new JFrame("Menu");
        Container menuContainer = menuWindow.getContentPane();
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Options");
        JMenuItem opt1 = new JMenuItem("Option 1");
        opt1.addActionListener(this);
        JMenuItem opt2 = new JMenuItem("Option 2");
        opt2.addActionListener(this);
        menu.add(opt1);
        menu.add(opt2);
        menuBar.add(menu);
        menuContainer.add(menuBar, BorderLayout.PAGE_START);
        JPanel startButtons = new JPanel();
        startButtons.setLayout(new GridLayout(2,1));
        JButton singlePlayer = new JButton("Un Jugador");
        singlePlayer.addActionListener(this);
        JButton multiPlayer = new JButton("Dos Jugadores");
        multiPlayer.addActionListener(this);
        startButtons.add(singlePlayer);
        startButtons.add(multiPlayer);
        menuContainer.add(startButtons, BorderLayout.CENTER);
        menuWindow.pack();
        menuWindow.setVisible(true);
    }
}
