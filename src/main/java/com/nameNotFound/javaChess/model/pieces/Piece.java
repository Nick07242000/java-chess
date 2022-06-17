package com.nameNotFound.javaChess.model.pieces;

import com.nameNotFound.javaChess.utils.Position;
import com.nameNotFound.javaChess.utils.enums.ColorEnum;
import com.nameNotFound.javaChess.utils.enums.PieceEnum;

import java.util.ArrayList;

public abstract class Piece {
    protected PieceEnum name;
    protected ColorEnum color;
    protected boolean long_movement;
    protected boolean was_moved;

    public Piece(PieceEnum n, ColorEnum c, boolean l_m) {
        name = n;
        color = c;
        long_movement = l_m;
        was_moved = false;
    }

    public abstract ArrayList<Position> possibleMovements(Position pos);
    public abstract ArrayList<Position> possibleTakes(Position pos);

    public final PieceEnum getName() {
        return name;
    }
    public final ColorEnum getColor() {
        return color;
    }
    public final boolean getLongMovement() {
        return long_movement;
    }
    public final boolean getWasMoved() {
        return was_moved;
    }
    public final void setWasMoved(boolean b) {
        was_moved = b;
    }
}
