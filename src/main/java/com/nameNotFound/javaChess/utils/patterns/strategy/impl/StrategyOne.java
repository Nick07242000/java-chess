package com.nameNotFound.javaChess.utils.patterns.strategy.impl;

import com.nameNotFound.javaChess.exceptions.InvalidPositionException;
import com.nameNotFound.javaChess.model.Board;
import com.nameNotFound.javaChess.model.pieces.Piece;
import com.nameNotFound.javaChess.service.Game;
import com.nameNotFound.javaChess.utils.Position;
import com.nameNotFound.javaChess.utils.SearchArray;
import com.nameNotFound.javaChess.utils.enums.ColorEnum;
import com.nameNotFound.javaChess.utils.patterns.strategy.StrategyAI;

import java.util.ArrayList;

public class StrategyOne implements StrategyAI{
    private static StrategyOne instance;

    private StrategyOne() {}

    public static StrategyOne getInstance() {
        if(instance == null)
            instance = new StrategyOne();
        return instance;
    }

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
        while (piece.getColor() != com);

        // busco una posTwo que sea realizable
        do {
            do
                do {
                    posTwo = generateRandomPosition();
                    piece = board.getPiece(posTwo);
                } while (piece == null);
            while (piece.getColor() != player);
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
        ArrayList<Position> possible_takes = Game.getInstance().getBoard().getPiece(posOne).possibleTakes(posOne);
        if (SearchArray.searchPositionInArray(possible_takes, posTwo))
            return Game.getInstance().analyzeTrajectory(posOne, posTwo);
        else
            return false;
    }
}
