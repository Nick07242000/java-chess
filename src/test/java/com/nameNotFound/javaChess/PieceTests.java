package com.nameNotFound.javaChess;

import com.nameNotFound.javaChess.model.pieces.impl.*;
import com.nameNotFound.javaChess.utils.Position;
import com.nameNotFound.javaChess.utils.enums.ColorEnum;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class PieceTests {
    @Test
    void pawnMovementTest() {
        Pawn pawn = new Pawn(ColorEnum.WHITE);
        ArrayList<Position> movements = pawn.possibleMovements(new Position(0,7));
        assert(movements.size() == 2);
        pawn.setWasMoved(true);
        movements = pawn.possibleMovements(new Position(0,7));
        assert(movements.size() == 1);
    }

    @Test
    void pawnTakesTest() {
        Pawn pawn = new Pawn(ColorEnum.WHITE);
        ArrayList<Position> movements = pawn.possibleTakes(new Position(0,7));
        assert(movements.size() == 1);
        movements = pawn.possibleTakes(new Position(3,7));
        assert(movements.size() == 2);
    }

    @Test
    void bishopMovementTest() {
        Bishop bishop = new Bishop(ColorEnum.WHITE);
        ArrayList<Position> movements = bishop.possibleMovements(new Position(0,7));
        assert(movements.size() == 7);
        movements = bishop.possibleMovements(new Position(2,5));
        assert(movements.size() == 11);
    }

    @Test
    void bishopTakesTest() {
        Bishop bishop = new Bishop(ColorEnum.WHITE);
        ArrayList<Position> movements = bishop.possibleTakes(new Position(0,7));
        assert(movements.size() == 7);
        movements = bishop.possibleTakes(new Position(2,5));
        assert(movements.size() == 11);
    }

    @Test
    void kingMovementTest() {
        King king = new King(ColorEnum.WHITE);
        ArrayList<Position> movements = king.possibleMovements(new Position(0,7));
        assert(movements.size() == 3);
        movements = king.possibleMovements(new Position(2,5));
        assert(movements.size() == 8);
    }

    @Test
    void kingTakesTest() {
        King king = new King(ColorEnum.WHITE);
        ArrayList<Position> movements = king.possibleTakes(new Position(0,7));
        assert(movements.size() == 3);
        movements = king.possibleTakes(new Position(2,5));
        assert(movements.size() == 8);
    }

    @Test
    void knightMovementTest() {
        Knight knight = new Knight(ColorEnum.WHITE);
        ArrayList<Position> movements = knight.possibleMovements(new Position(0,7));
        assert(movements.size() == 2);
        movements = knight.possibleMovements(new Position(2,5));
        assert(movements.size() == 8);
    }

    @Test
    void knightTakesTest() {
        Knight knight = new Knight(ColorEnum.WHITE);
        ArrayList<Position> movements = knight.possibleMovements(new Position(0,7));
        assert(movements.size() == 2);
        movements = knight.possibleMovements(new Position(2,5));
        assert(movements.size() == 8);
    }

    @Test
    void QueenMovementTest() {
        Queen queen = new Queen(ColorEnum.WHITE);
        ArrayList<Position> movements = queen.possibleMovements(new Position(0,7));
        assert(movements.size() == 21);
        movements = queen.possibleMovements(new Position(2,5));
        assert(movements.size() == 25);
    }

    @Test
    void QueenTakesTest() {
        Queen queen = new Queen(ColorEnum.WHITE);
        ArrayList<Position> movements = queen.possibleTakes(new Position(0,7));
        assert(movements.size() == 21);
        movements = queen.possibleTakes(new Position(2,5));
        assert(movements.size() == 25);
    }

    @Test
    void RookMovementTest() {
        Rook rook = new Rook(ColorEnum.WHITE);
        ArrayList<Position> movements = rook.possibleMovements(new Position(0,7));
        assert(movements.size() == 14);
        movements = rook.possibleMovements(new Position(2,5));
        assert(movements.size() == 14);
    }

    @Test
    void RookTakesTest() {
        Rook rook = new Rook(ColorEnum.WHITE);
        ArrayList<Position> movements = rook.possibleTakes(new Position(0,7));
        assert(movements.size() == 14);
        movements = rook.possibleTakes(new Position(2,5));
        assert(movements.size() == 14);
    }
}
