package com.nameNotFound.javaChess.model.pieces.impl;

import com.nameNotFound.javaChess.model.pieces.Piece;
import com.nameNotFound.javaChess.utils.Position;
import com.nameNotFound.javaChess.utils.enums.ColorEnum;
import com.nameNotFound.javaChess.utils.enums.PieceEnum;

import java.util.ArrayList;

public class King extends Piece {
    public King(ColorEnum c) {
        super(PieceEnum.KING, c, false);
    }

    public ArrayList<Position> possibleMovements(Position pos) {
        ArrayList<Position> output = new ArrayList<Position>();
        int x = pos.getX();
        int y = pos.getY();
        if (y+1 <= 7) {
            output.add(new Position(x, y+1));
            if (x+1 <= 7)
                output.add(new Position(x+1, y+1));
            if (x-1 >= 0)
                output.add(new Position(x-1, y+1));
        }
        if (y-1 >= 0) {
            output.add(new Position(x, y-1));
            if (x+1 <= 7)
                output.add(new Position(x+1, y-1));
            if (x-1 >= 0)
                output.add(new Position(x-1, y-1));
        }
        if (x+1 <= 7)
            output.add(new Position(x+1, y));
        if (x-1 >= 0)
            output.add(new Position(x-1, y));
        return output;
    }

    public ArrayList<Position> possibleTakes(Position pos) {
        ArrayList<Position> output = new ArrayList<Position>();
        int x = pos.getX();
        int y = pos.getY();
        if (y+1 <= 7) {
            output.add(new Position(x, y+1));
            if (x+1 <= 7)
                output.add(new Position(x+1, y+1));
            if (x-1 >= 0)
                output.add(new Position(x-1, y+1));
        }
        if (y-1 >= 0) {
            output.add(new Position(x, y-1));
            if (x+1 <= 7)
                output.add(new Position(x+1, y-1));
            if (x-1 >= 0)
                output.add(new Position(x-1, y-1));
        }
        if (x+1 <= 7)
            output.add(new Position(x+1, y));
        if (x-1 >= 0)
            output.add(new Position(x-1, y));
        return output;
    }
}
