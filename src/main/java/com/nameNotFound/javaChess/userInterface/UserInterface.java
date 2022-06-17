package com.nameNotFound.javaChess.userInterface;

//import com.nameNotFound.javaChess.utils.patterns.observer.Observer;

public abstract class UserInterface /*implements Observer*/ {
    protected final static String TURN_MESSAGE = "Turno De ";
    protected final static String PIECE_TO_MOVE_MESSAGE = "Seleccione Pieza a Mover (Formato LETRANUMERO):";
    protected final static String WHERE_TO_MOVE_MESSAGE = "Seleccione Destino:";
    protected final static String CHOOSE_PIECE_MESSAGE = "Seleccione una Pieza:";
    protected final static String INVALID_POSITION_MESSAGE = "Posicion No Valida!";
    public final static String CHECK_MESSAGE = "Hay Jaque al Rey!";
    public final static String CHECKMATE_MESSAGE = "Jaque Mate!";

    public abstract void requestUserInput(String message);
    public abstract String getInput();
    public abstract void showBoard();
    public abstract void showTakenPieces();
    public abstract void movePiece();
    public abstract void showTurn();
}
