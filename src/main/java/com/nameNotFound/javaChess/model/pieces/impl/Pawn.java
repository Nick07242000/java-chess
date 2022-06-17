package com.nameNotFound.javaChess.model.pieces.impl;

import com.nameNotFound.javaChess.model.pieces.Piece;
import com.nameNotFound.javaChess.utils.Position;
import com.nameNotFound.javaChess.utils.enums.ColorEnum;
import com.nameNotFound.javaChess.utils.enums.PieceEnum;

import java.util.ArrayList;

public class Pawn extends Piece {
    public Pawn(ColorEnum c) {
        super(PieceEnum.PAWN, c, false);
    }

    public ArrayList<Position> possibleMovements(Position pos) {
        ArrayList<Position> output = new ArrayList<Position>();
        int x = pos.getX();
        int y = pos.getY();
        if (y+1 <= 7 && color == ColorEnum.BLACK)
            output.add(new Position(x, y+1));
        if (y-1 >= 0 && color == ColorEnum.WHITE)
            output.add(new Position(x, y-1));
        if (y+2 <= 7 && color == ColorEnum.BLACK && !was_moved)
            output.add(new Position(x, y+2));
        if (y-2 >= 0 && color == ColorEnum.WHITE && !was_moved)
            output.add(new Position(x, y-2));
        return output;
    }

    public ArrayList<Position> possibleTakes(Position pos) {
        ArrayList<Position> output = new ArrayList<Position>();
        int x = pos.getX();
        int y = pos.getY();
        if (y+1 <= 7 && x+1 <= 7 && color == ColorEnum.BLACK)
            output.add(new Position(x+1, y+1));
        if (y-1 >= 0 && x+1 <= 7 && color == ColorEnum.WHITE)
            output.add(new Position(x+1, y-1));
        if (y+1 <= 7 && x-1 >= 0 && color == ColorEnum.BLACK)
            output.add(new Position(x-1, y+1));
        if (y-1 >= 0 && x-1 >= 0 && color == ColorEnum.WHITE)
            output.add(new Position(x-1, y-1));
        return output;
    }
}
