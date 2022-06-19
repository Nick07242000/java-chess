package com.nameNotFound.javaChess;

import com.nameNotFound.javaChess.model.Board;
import com.nameNotFound.javaChess.model.pieces.impl.King;
import com.nameNotFound.javaChess.model.pieces.impl.Pawn;
import com.nameNotFound.javaChess.model.pieces.impl.Queen;
import com.nameNotFound.javaChess.model.pieces.impl.Rook;
import com.nameNotFound.javaChess.service.Game;
import com.nameNotFound.javaChess.utils.Position;
import com.nameNotFound.javaChess.utils.enums.ColorEnum;
import com.nameNotFound.javaChess.utils.enums.PieceEnum;
import org.junit.jupiter.api.*;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class GameTests {
    @AfterEach
    void clearBoard() {
        Game game = Game.getInstance();
        for (int i = 0; i <=  7; i++) {
            for (int j = 0; j <= 7; j++) {
                game.getBoard().setPiece(null, new Position(i,j));
            }
        }
    }

    @Test
    void oneGameTest() {
        Game game1 = Game.getInstance();
        Game game2 = Game.getInstance();
        assert(game1.equals(game2));
    }

    @Test
    void boardInitializeTest() {
        Game game = Game.getInstance();
        game.initializeBoard();
        Board board = game.getBoard();
        assert(board.getPiece(new Position(0,0)).getName().equals(PieceEnum.ROOK) && board.getPiece(new Position(0,0)).getColor().equals(ColorEnum.BLACK));
        assert(board.getPiece(new Position(1,0)).getName().equals(PieceEnum.KNIGHT) && board.getPiece(new Position(0,0)).getColor().equals(ColorEnum.BLACK));
        assert(board.getPiece(new Position(2,0)).getName().equals(PieceEnum.BISHOP) && board.getPiece(new Position(0,0)).getColor().equals(ColorEnum.BLACK));
        assert(board.getPiece(new Position(3,0)).getName().equals(PieceEnum.KING) && board.getPiece(new Position(0,0)).getColor().equals(ColorEnum.BLACK));
        assert(board.getPiece(new Position(4,0)).getName().equals(PieceEnum.QUEEN) && board.getPiece(new Position(0,0)).getColor().equals(ColorEnum.BLACK));
        assert(board.getPiece(new Position(5,0)).getName().equals(PieceEnum.BISHOP) && board.getPiece(new Position(0,0)).getColor().equals(ColorEnum.BLACK));
        assert(board.getPiece(new Position(6,0)).getName().equals(PieceEnum.KNIGHT) && board.getPiece(new Position(0,0)).getColor().equals(ColorEnum.BLACK));
        assert(board.getPiece(new Position(7,0)).getName().equals(PieceEnum.ROOK) && board.getPiece(new Position(0,0)).getColor().equals(ColorEnum.BLACK));
        for (int i=0; i<=7; i++) {
            assert(board.getPiece(new Position(i,1)).getName().equals(PieceEnum.PAWN) && board.getPiece(new Position(i,1)).getColor().equals(ColorEnum.BLACK));
        }
        assert(board.getPiece(new Position(0,7)).getName().equals(PieceEnum.ROOK) && board.getPiece(new Position(0,7)).getColor().equals(ColorEnum.WHITE));
        assert(board.getPiece(new Position(1,7)).getName().equals(PieceEnum.KNIGHT) && board.getPiece(new Position(0,7)).getColor().equals(ColorEnum.WHITE));
        assert(board.getPiece(new Position(2,7)).getName().equals(PieceEnum.BISHOP) && board.getPiece(new Position(0,7)).getColor().equals(ColorEnum.WHITE));
        assert(board.getPiece(new Position(3,7)).getName().equals(PieceEnum.KING) && board.getPiece(new Position(0,7)).getColor().equals(ColorEnum.WHITE));
        assert(board.getPiece(new Position(4,7)).getName().equals(PieceEnum.QUEEN) && board.getPiece(new Position(0,7)).getColor().equals(ColorEnum.WHITE));
        assert(board.getPiece(new Position(5,7)).getName().equals(PieceEnum.BISHOP) && board.getPiece(new Position(0,7)).getColor().equals(ColorEnum.WHITE));
        assert(board.getPiece(new Position(6,7)).getName().equals(PieceEnum.KNIGHT) && board.getPiece(new Position(0,7)).getColor().equals(ColorEnum.WHITE));
        assert(board.getPiece(new Position(7,7)).getName().equals(PieceEnum.ROOK) && board.getPiece(new Position(0,7)).getColor().equals(ColorEnum.WHITE));
        for (int i=0; i<=7; i++) {
            assert(board.getPiece(new Position(i,6)).getName().equals(PieceEnum.PAWN) && board.getPiece(new Position(i,6)).getColor().equals(ColorEnum.WHITE));
        }
    }

    @Test
    void changeTurnTest() {
        Game game = Game.getInstance();
        assert(game.getTurn().equals(ColorEnum.WHITE));
        game.changeTurn();
        assert(game.getTurn().equals(ColorEnum.BLACK));
        game.changeTurn();
        assert(game.getTurn().equals(ColorEnum.WHITE));
    }

    @Test
    void checkTest() {
        Game game = Game.getInstance();
        game.getBoard().setPiece(new King(ColorEnum.WHITE),new Position(0,0));
        game.getBoard().setPiece(new Queen(ColorEnum.WHITE),new Position(0,1));
        assert(!game.isCheck());
        game.getBoard().setPiece(new Queen(ColorEnum.BLACK),new Position(0,1));
        assert(game.isCheck());
    }

    @Test
    void checkmateTest() {
        Game game = Game.getInstance();
        game.getBoard().setPiece(new King(ColorEnum.WHITE),new Position(0,0));
        game.getBoard().setPiece(new Queen(ColorEnum.WHITE),new Position(0,1));
        assert(!game.isCheckmate());
        game.getBoard().setPiece(new Queen(ColorEnum.BLACK),new Position(0,1));
        game.getBoard().setPiece(new Queen(ColorEnum.BLACK),new Position(1,1));
        game.getBoard().setPiece(new Queen(ColorEnum.BLACK),new Position(1,0));
        assert(game.isCheckmate());
    }

    @Test
    void analyzeTrajectoryTest() {
        Game game = Game.getInstance();
        Position king = new Position(3,7);
        Position rook = new Position(0,7);
        Position hindrance = new Position(1,7);
        game.getBoard().setPiece(new Rook(ColorEnum.WHITE), rook);
        game.getBoard().setPiece(new King(ColorEnum.WHITE), king);
        assert(game.analyzeTrajectory(rook,king));
        game.getBoard().setPiece(new Pawn(ColorEnum.WHITE), hindrance);
        assert(!game.analyzeTrajectory(rook,king));
    }

    @Test
    void isAttackedOneTest() {
        Game game = Game.getInstance();
        Position king = new Position(3,7);
        game.getBoard().setPiece(new King(ColorEnum.WHITE), king);
        assert(!game.isAttacked(king));
        Position enemy = new Position(2,7);
        game.getBoard().setPiece(new Queen(ColorEnum.BLACK), enemy);
        assert(game.isAttacked(king));
    }

    @Test
    void isAttackedTwoTest() {
        Game game = Game.getInstance();
        ArrayList<Position> positions = new ArrayList<>();
        Position king = new Position(3,7);
        Position rook = new Position(0,7);
        Position empty1 = new Position(1,7);
        Position empty2 = new Position(2,7);
        game.getBoard().setPiece(new King(ColorEnum.WHITE), king);
        game.getBoard().setPiece(new Rook(ColorEnum.WHITE), rook);
        positions.add(king);
        positions.add(rook);
        positions.add(empty1);
        positions.add(empty2);
        assert(!game.isAttacked(positions));
    }

    @Test
    void isCastleTest() {
        Game game = Game.getInstance();
        assert(game.isCastle(new King(ColorEnum.WHITE),new Rook(ColorEnum.WHITE)));
    }

    @Test
    void getCornerTest() {
        Game game = Game.getInstance();
        game.changeTurn();
        assert(game.getCorner(new Position(0,0)).equals("ul"));
        assert(game.getCorner(new Position(7,0)).equals("ur"));
        game.changeTurn();
        assert(game.getCorner(new Position(0,7)).equals("bl"));
        assert(game.getCorner(new Position(7,7)).equals("br"));
    }

    @Test
    void canCastleTest() {
        Game game = Game.getInstance();
        Position wKing = new Position(3,7);
        Position bKing = new Position(3,0);
        Position wRook1 = new Position(0,7);
        Position wRook2 = new Position(7,7);
        Position bRook1 = new Position(0,0);
        Position bRook2 = new Position(7,0);
        game.getBoard().setPiece(new King(ColorEnum.WHITE), wKing);
        game.getBoard().setPiece(new King(ColorEnum.BLACK), bKing);
        game.getBoard().setPiece(new Rook(ColorEnum.WHITE), wRook1);
        game.getBoard().setPiece(new Rook(ColorEnum.WHITE), wRook2);
        game.getBoard().setPiece(new Rook(ColorEnum.BLACK), bRook1);
        game.getBoard().setPiece(new Rook(ColorEnum.BLACK), bRook2);
        game.getBoard().setPiece(new Pawn(ColorEnum.WHITE), new Position(0,6));
        game.getBoard().setPiece(new Pawn(ColorEnum.WHITE), new Position(7,6));
        game.getBoard().setPiece(new Pawn(ColorEnum.BLACK), new Position(0,2));
        game.getBoard().setPiece(new Pawn(ColorEnum.BLACK), new Position(7,2));
        assert(game.canCastle(wKing,wRook1,game.getBoard().getPiece(wKing),game.getBoard().getPiece(wRook1),"bl"));
        assert(game.canCastle(wKing,wRook2,game.getBoard().getPiece(wKing),game.getBoard().getPiece(wRook2),"br"));
        game.changeTurn();
        assert(game.canCastle(bKing,bRook1,game.getBoard().getPiece(bKing),game.getBoard().getPiece(bRook1),"ul"));
        assert(game.canCastle(bKing,bRook2,game.getBoard().getPiece(bKing),game.getBoard().getPiece(bRook2),"ur"));
        game.changeTurn();
    }

    @Test
    void castleTestOne() {
        Game game = Game.getInstance();
        Position wKing = new Position(3,7);
        Position bKing = new Position(3,0);
        Position wRook = new Position(0,7);
        Position bRook = new Position(0,0);
        game.getBoard().setPiece(new King(ColorEnum.WHITE), wKing);
        game.getBoard().setPiece(new King(ColorEnum.BLACK), bKing);
        game.getBoard().setPiece(new Rook(ColorEnum.WHITE), wRook);
        game.getBoard().setPiece(new Rook(ColorEnum.BLACK), bRook);
        game.getBoard().setPiece(new Pawn(ColorEnum.WHITE), new Position(0,6));
        game.getBoard().setPiece(new Pawn(ColorEnum.BLACK), new Position(0,2));
        game.castle(wKing,wRook,game.getBoard().getPiece(wKing),game.getBoard().getPiece(wRook),"bl");
        game.castle(bKing,bRook,game.getBoard().getPiece(bKing),game.getBoard().getPiece(bRook),"ul");
        assert(game.getBoard().getPiece(new Position(2,7)).getName().equals(PieceEnum.ROOK));
        assert(game.getBoard().getPiece(new Position(1,7)).getName().equals(PieceEnum.KING));
        assert(game.getBoard().getPiece(new Position(2,0)).getName().equals(PieceEnum.ROOK));
        assert(game.getBoard().getPiece(new Position(1,0)).getName().equals(PieceEnum.KING));
    }

    @Test
    void castleTestTwo() {
        Game game = Game.getInstance();
        Position wKing = new Position(3,7);
        Position bKing = new Position(3,0);
        Position wRook = new Position(7,7);
        Position bRook = new Position(7,0);
        game.getBoard().setPiece(new King(ColorEnum.WHITE), wKing);
        game.getBoard().setPiece(new King(ColorEnum.BLACK), bKing);
        game.getBoard().setPiece(new Rook(ColorEnum.WHITE), wRook);
        game.getBoard().setPiece(new Rook(ColorEnum.BLACK), bRook);
        game.getBoard().setPiece(new Pawn(ColorEnum.WHITE), new Position(7,6));
        game.getBoard().setPiece(new Pawn(ColorEnum.BLACK), new Position(7,2));
        game.castle(wKing,wRook,game.getBoard().getPiece(wKing),game.getBoard().getPiece(wRook),"br");
        game.castle(bKing,bRook,game.getBoard().getPiece(bKing),game.getBoard().getPiece(bRook),"ur");
        assert(game.getBoard().getPiece(new Position(4,7)).getName().equals(PieceEnum.ROOK));
        assert(game.getBoard().getPiece(new Position(5,7)).getName().equals(PieceEnum.KING));
        assert(game.getBoard().getPiece(new Position(4,0)).getName().equals(PieceEnum.ROOK));
        assert(game.getBoard().getPiece(new Position(5,0)).getName().equals(PieceEnum.KING));
    }
}
