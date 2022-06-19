package com.nameNotFound.javaChess;

import com.nameNotFound.javaChess.model.Board;
import com.nameNotFound.javaChess.model.pieces.Piece;
import com.nameNotFound.javaChess.model.pieces.impl.King;
import com.nameNotFound.javaChess.model.pieces.impl.Pawn;
import com.nameNotFound.javaChess.service.Game;
import com.nameNotFound.javaChess.utils.Position;
import com.nameNotFound.javaChess.utils.enums.ColorEnum;
import com.nameNotFound.javaChess.utils.enums.PieceEnum;
import org.junit.jupiter.api.Test;

public class BoardTests {
    @Test
    void onlyOneBoardTest() {
        Board board1 = Board.getInstance();
        Board board2 = Board.getInstance();
        assert(board1.equals(board2));
    }

    @Test
    void movePieceTest() {
        Board board = Board.getInstance();
        Position startPos = new Position(0,1);
        Position endPos = new Position(0,3);
        board.setPiece(new Pawn(ColorEnum.WHITE),startPos);
        assert(board.getPiece(startPos).getName().equals(PieceEnum.PAWN));
        assert(board.getPiece(endPos) == null);
        board.movePiece(startPos,endPos);
        assert(board.getPiece(endPos).getName().equals(PieceEnum.PAWN));
        assert(board.getPiece(endPos).getWasMoved());
    }

    @Test
    void findKingTest() {
        Board board = Board.getInstance();
        Position kingPos = new Position(3,4);
        board.setPiece(new King(ColorEnum.WHITE),kingPos);
        Position foundPos = board.getKing(ColorEnum.WHITE);
        assert(kingPos.getX() == foundPos.getX());
        assert(kingPos.getY() == foundPos.getY());
    }

    @Test
    void undoMovementTest() {
        Board board = Board.getInstance();
        board.setPiece(new Pawn(ColorEnum.WHITE),new Position(0,3));
        board.undoMovement(new Position(0,3),new Position(0,1),false);
        assert(board.getPiece(new Position(0,1)).getName().equals(PieceEnum.PAWN));
        assert(!board.getPiece(new Position(0,1)).getWasMoved());
    }
}
