package com.nameNotFound.javaChess.model;

import com.nameNotFound.javaChess.model.pieces.Piece;
import com.nameNotFound.javaChess.utils.Position;
import com.nameNotFound.javaChess.utils.enums.ColorEnum;
import com.nameNotFound.javaChess.utils.enums.PieceEnum;

public class Board {
    private final Piece[][] board = new Piece[8][8];
    private static Board instance;

    private Board() {
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                board[i][j] = null;
    }

    public static Board getInstance() {
        if (instance == null)
            instance = new Board();
        return instance;
    }

    public void setPiece(Piece p, Position pos) {
        board[pos.getX()][pos.getY()] = p;
    }

    public Piece getPiece(Position pos) {
        return board[pos.getX()][pos.getY()];
    }

    public Position getKing(ColorEnum c) {
        Position p = null;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++)
                p = new Position(i,j);
                if (getPiece(p) != null)
                    if (getPiece(p).getName().equals(PieceEnum.KING) && getPiece(p).getColor().equals(c))
                        return p;
        return null;
    }

    public void movePiece(Position posOne, Position posTwo) {
        board[posTwo.getX()][posTwo.getY()] = board[posOne.getX()][posOne.getY()];
        board[posOne.getX()][posOne.getY()] = null;
        if (board[posTwo.getX()][posTwo.getY()].getWasMoved())
            board[posTwo.getX()][posTwo.getY()].setWasMoved(true);
    }

    public void undoMovement(Position posOne, Position posTwo, boolean b) {
        board[posTwo.getX()][posTwo.getY()] = board[posOne.getX()][posOne.getY()];
        board[posOne.getX()][posOne.getY()] = null;
        board[posTwo.getX()][posTwo.getY()].setWasMoved(b);
    }
}
