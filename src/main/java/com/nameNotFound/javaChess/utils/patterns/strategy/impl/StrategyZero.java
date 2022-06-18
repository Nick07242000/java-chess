package com.nameNotFound.javaChess.utils.patterns.strategy.impl;

import java.util.ArrayList;

import com.nameNotFound.javaChess.exceptions.InvalidPositionException;
import com.nameNotFound.javaChess.model.Board;
import com.nameNotFound.javaChess.model.pieces.Piece;
import com.nameNotFound.javaChess.service.Game;
import com.nameNotFound.javaChess.utils.Position;
import com.nameNotFound.javaChess.utils.SearchArray;
import com.nameNotFound.javaChess.utils.enums.ColorEnum;
import com.nameNotFound.javaChess.utils.patterns.strategy.StrategyAI;

public class StrategyZero implements StrategyAI {
    private static StrategyZero instance;

    private StrategyZero() {}

    public static StrategyZero getInstance() {
        if(instance == null)
            instance = new StrategyZero();
        return instance;
    }
    /*
     * selecciona una pieza que pueda ser movida, tanto como movimiento normal
     * como movimiento para tomar una rival, y realiza un movimiento dentro de
     * las posibilidades validas que existan
     */
    @Override
    public void play() {
        Game game = Game.getInstance();
        Board board = game.getBoard();
        Piece piece;
        Position posOne;
        Position posTwo;
        ColorEnum com = game.getTurn();
        ColorEnum player;
        if(com.equals(ColorEnum.BLACK))
            player = ColorEnum.WHITE;
        else
            player = ColorEnum.BLACK;

        // busco una posOne que sea una pieza propia
        do
            do {
                posOne = generateRandomPosition();
                piece = board.getPiece(posOne);
            } while (piece == null);
        while (piece.getColor() != com); // la COM es BLACK

        // busco una posTwo que sea realizable
        do {
            posTwo = generateRandomPosition();
        } while (!this.isValidMovement(posOne, posTwo));
        try {
            game.movePiece(posOne, posTwo, board.getPiece(posOne), board.getPiece(posTwo));
        } catch (InvalidPositionException ignored) {}
    }

    /*
     * genera y retorna una posicion random del tablero
     */
    private Position generateRandomPosition() {
        return new Position((int) Math.floor(Math.random()*(-6)+7), (int) Math.floor(Math.random()*(-6)+7));
    }

    /*
     * analiza si el movimiento entre posOne y posTwo es valido para la pieza en el tablero
     */
    private boolean isValidMovement(Position posOne, Position posTwo) {
        ArrayList<Position> possible_movements = Game.getInstance().getBoard().getPiece(posOne).possibleMovements(posOne);
        ArrayList<Position> possible_takes = Game.getInstance().getBoard().getPiece(posOne).possibleTakes(posOne);
        if (SearchArray.searchPositionInArray(possible_takes, posTwo) && Game.getInstance().getBoard().getPiece(posTwo) != null)
            return Game.getInstance().analyzeTrajectory(posOne, posTwo);
        else if (SearchArray.searchPositionInArray(possible_movements, posTwo) && Game.getInstance().getBoard().getPiece(posTwo) == null)
            return Game.getInstance().analyzeTrajectory(posOne, posTwo);
        else
            return false;
    }
}
