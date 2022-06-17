package com.nameNotFound.javaChess.utils.patterns.factory.impl;

import com.nameNotFound.javaChess.model.pieces.Piece;
import com.nameNotFound.javaChess.model.pieces.impl.*;
import com.nameNotFound.javaChess.utils.enums.ColorEnum;
import com.nameNotFound.javaChess.utils.patterns.factory.Factory;

public class PieceFactory extends Factory<Piece> {
    private static PieceFactory instance;

    private PieceFactory() {}

    public static PieceFactory getInstance() {
        if(instance == null)
            instance = new PieceFactory();
        return instance;
    }

    public final Piece build(String type) {
        return switch (type) {
            case "bishop_b" -> new Bishop(ColorEnum.BLACK);
            case "king_b" -> new King(ColorEnum.BLACK);
            case "knight_b" -> new Knight(ColorEnum.BLACK);
            case "pawn_b" -> new Pawn(ColorEnum.BLACK);
            case "queen_b" -> new Queen(ColorEnum.BLACK);
            case "rook_b" -> new Rook(ColorEnum.BLACK);
            case "bishop_w" -> new Bishop(ColorEnum.WHITE);
            case "king_w" -> new King(ColorEnum.WHITE);
            case "knight_w" -> new Knight(ColorEnum.WHITE);
            case "pawn_w" -> new Pawn(ColorEnum.WHITE);
            case "queen_w" -> new Queen(ColorEnum.WHITE);
            case "rook_w" -> new Rook(ColorEnum.WHITE);
            default -> null;
        };
    }
}
