package com.nameNotFound.javaChess.service;

import java.util.ArrayList;

import com.nameNotFound.javaChess.exceptions.InvalidPositionException;
import com.nameNotFound.javaChess.model.Board;
import com.nameNotFound.javaChess.model.pieces.Piece;
import com.nameNotFound.javaChess.utils.Movement;
import com.nameNotFound.javaChess.utils.Position;
import com.nameNotFound.javaChess.utils.SearchArray;
import com.nameNotFound.javaChess.utils.enums.ColorEnum;
import com.nameNotFound.javaChess.utils.enums.PieceEnum;
import com.nameNotFound.javaChess.utils.patterns.factory.impl.PieceFactory;
import com.nameNotFound.javaChess.utils.patterns.observer.Observable;

public class Game extends Observable {
    private final Board board;
    private ColorEnum turn;
    private final ArrayList<Piece> blackPiecesTaken;
    private final ArrayList<Piece> whitePiecesTaken;
    private final ArrayList<Movement> movements = new ArrayList<>();
    private final PieceFactory pieceFactory = PieceFactory.getInstance();
    private static Game instance;

    private Game() {
        board = Board.getInstance();
        initializeBoard();
        turn = ColorEnum.WHITE;
        blackPiecesTaken = new ArrayList<>();
        whitePiecesTaken = new ArrayList<>();
        notifyObs();
    }

    public static Game getInstance() {
        if(instance == null)
            instance = new Game();
        return instance;
    }

    //Getters And Setters

    public Board getBoard() {
        return board;
    }

    public ColorEnum getTurn() {
        return turn;
    }

    public ArrayList<Piece> getBlackPiecesTaken() {
        return blackPiecesTaken;
    }

    public ArrayList<Piece> getWhitePiecesTaken() {
        return whitePiecesTaken;
    }

    public void removePiece(ColorEnum c) {
        if(c.equals(ColorEnum.BLACK))
            this.blackPiecesTaken.remove(blackPiecesTaken.size()-1);
        else
            this.whitePiecesTaken.remove(whitePiecesTaken.size()-1);
    }

    //Methods

    public void initializeBoard() {
        String[] piecesWhite = {"rook_w","knight_w","bishop_w","king_w","queen_w","bishop_w","knight_w","rook_w"};
        String[] piecesBlack = {"rook_b","knight_b","bishop_b","king_b","queen_b","bishop_b","knight_b","rook_b"};
        for (int i = 0; i <= 7; i++)
            board.setPiece(pieceFactory.build("pawn_w"), new Position(i,6));
        for (int i = 0; i <= 7; i++)
            board.setPiece(pieceFactory.build("pawn_b"), new Position(i,1));
        for (int i = 0; i <= 7; i++)
            board.setPiece(pieceFactory.build(piecesWhite[i]), new Position(i,7));
        for (int i = 0; i <= 7; i++)
            board.setPiece(pieceFactory.build(piecesBlack[i]), new Position(i,0));
    }

    public void changeTurn() {
        if (turn.equals(ColorEnum.WHITE))
            turn = ColorEnum.BLACK;
        else
            turn = ColorEnum.WHITE;
    }

    public boolean movePiece(Position posOne, Position posTwo, Piece pieceOne, Piece pieceTwo) throws InvalidPositionException{
        System.out.println("LLEGUE");
        Movement movement = new Movement(posOne, posTwo, board.getPiece(posOne).getWasMoved());
        boolean takePiece = false;
        if (!analizeTrajectory(posOne, posTwo)) {
            throw new InvalidPositionException("Hay piezas en el camino!");
        }
        else if (pieceTwo != null) {
            if (isCastle(pieceOne, pieceTwo)) {
                String corner = getCorner(posTwo);
                if (canCastle(posOne, posTwo, pieceOne, pieceTwo, corner)) {
                    castle(posOne, posTwo, pieceOne, pieceTwo, corner);
                    return takePiece;
                }
            }
            if (turn.equals(ColorEnum.BLACK))
                whitePiecesTaken.add(board.getPiece(posTwo));
            else
                blackPiecesTaken.add(board.getPiece(posTwo));
            takePiece = true;
        }
        movements.add(movement);
        board.movePiece(posOne, posTwo);
        changeTurn();
        notifyObs();
        return takePiece;
    }

    //Castling
    public boolean isCastle(Piece pieceOne, Piece pieceTwo) {
        return pieceOne.getName().equals(PieceEnum.KING) && pieceTwo.getName().equals(PieceEnum.ROOK);
    }

    public String getCorner(Position pos) {
        if (pos.getY() == 0) {
            if(turn.equals(ColorEnum.BLACK))
                return "ul";
            return "bl";
        } else {
            if(turn.equals(ColorEnum.BLACK))
                return "ur";
            return "br";
        }
    }

    public boolean canCastle(Position posOne, Position posTwo, Piece pieceOne, Piece pieceTwo, String corner) {
        if (analizeTrajectory(posTwo, posOne)) {
            if (!pieceOne.getWasMoved() && !pieceTwo.getWasMoved()) {
                ArrayList<Position> positions = new ArrayList<>();
                switch (corner) {
                    case "ul" -> {
                        positions.add(new Position(1, 0));
                        positions.add(new Position(2, 0));
                        positions.add(new Position(3, 0));
                    }
                    case "bl" -> {
                        positions.add(new Position(2, 7));
                        positions.add(new Position(3, 7));
                        positions.add(new Position(4, 7));
                    }
                    case "ur" -> {
                        positions.add(new Position(3, 0));
                        positions.add(new Position(4, 0));
                        positions.
                        add(new Position(5, 0));
                    }
                    case "br" -> {
                        positions.add(new Position(4, 7));
                        positions.add(new Position(5, 7));
                        positions.add(new Position(6, 7));
                    }
                    default -> {

                    }
                }
                return !isAttacked(positions);
            }
        }
        return false;
    }

    public void castle(Position kingPos, Position rookPos, Piece king, Piece rook, String corner) {
        switch (corner) {
            case "ul" -> {
                movements.add(new Movement(rookPos, new Position(0,2), rook.getWasMoved()));
                board.movePiece(rookPos, new Position(0,2));
                movements.add(new Movement(kingPos, new Position(0,1), king.getWasMoved()));
                board.movePiece(kingPos, new Position(0,1));
            }
            case "ur" -> {
                movements.add(new Movement(rookPos, new Position(0,4), rook.getWasMoved()));
                board.movePiece(rookPos, new Position(0,4));
                movements.add(new Movement(kingPos, new Position(0,5), king.getWasMoved()));
                board.movePiece(kingPos, new Position(0,5));
            }
            case "bl" -> {
                movements.add(new Movement(rookPos, new Position(7,2), rook.getWasMoved()));
                board.movePiece(rookPos, new Position(7,2));
                movements.add(new Movement(kingPos, new Position(7,1), king.getWasMoved()));
                board.movePiece(kingPos, new Position(7,1));
            }
            case "br" -> {
                movements.add(new Movement(rookPos, new Position(7,4), rook.getWasMoved()));
                board.movePiece(rookPos, new Position(7,4));
                movements.add(new Movement(kingPos, new Position(7,5), king.getWasMoved()));
                board.movePiece(kingPos, new Position(7,5));
            }
            default -> {

            }
        }
    }

    //Check
    public boolean isCheck() {
        System.out.println(board.getKing(turn));
        return isAttacked(board.getKing(turn));
    }

    /*
     * analiza si el rey se ve acorralado, viendo si en caso de haber jaque, el rey tiene movimientos posibles,
     * incluso en el caso de que pueda llegar a comer una pieza rival
     */
    public boolean isCheckmate() {
        if (isCheck()) {
            ArrayList<Position> tentative_movements = notAttackedPositionsInCheck(board.getKing(turn));
            ArrayList<Position> to_remove = new ArrayList<>();
            for (Position position : tentative_movements)
                if (board.getPiece(position) != null)
                    if (board.getPiece(position).getColor().equals(turn))
                        to_remove.add(position);
                    else if (isAttacked(position))
                        to_remove.add(position);
            tentative_movements.removeAll(to_remove);
            return tentative_movements.size() == 0;
        }
        return false;
    }

    /*
     * se le pasa una posicion que debe ser la del rey, y retorna un arraylist con las posiciones que no se ven atacadas alrrededor del rey
     */
    private ArrayList<Position> notAttackedPositionsInCheck(Position pos) {
        ArrayList<Position> output = new ArrayList<>();
        ArrayList<Position> around_positions = board.getPiece(pos).possibleMovements(pos);
        for (Position position : around_positions)
            if (!isAttacked(position))
                output.add(position);
        return output;
    }

    public void returnMovementBackwards() {
        Movement movement = movements.get(movements.size()-1);
        board.undoMovement(movement.getPosTwo(), movement.getPosOne(), movement.isOldWasMoved());
        movements.remove(movements.size()-1);
        changeTurn();
        notifyObs();
    }

    //Logic Helpers

    /*
     * verifica que, si la ficha realiza una trayectoria larga, no colisione con otras piezas en el trayecto,
     * a excepcion de la posicion final donde puede llegar a haber una pieza cualquiera
     */
    private boolean analizeTrajectory(Position posOne, Position posTwo) {
        if (board.getPiece(posOne).getLongMovement()) { //veo si la pieza realiza movimientos de trayectoria
            boolean no_obstruction = true;
            int x1 = posOne.getX();
            int y1 = posOne.getY();
            int x2 = posTwo.getX();
            int y2 = posTwo.getY();
            int MAX;
            if (x2 == x1) { //verifico si me muevo unicamente sobre el eje Y
                if (y2 > y1) { //analizo movimiento para +Y
                    MAX = y2-y1; //seteo el limite de iteraciones para no salirme del tablero para +Y
                    for (int i = 1; i < MAX; i++) { //analizo movimiento para +Y
                        if (board.getPiece(new Position(x1, y1+i)) != null && y1+i != y2)
                            no_obstruction = false;
                        if (y1+i == y2) //analizo si llego a la posicion final y se retorna si hay obstruccion
                            return no_obstruction;
                    }
                }
                else { //analizo movimiento para -Y
                    MAX = y1-y2; //seteo el limite de iteraciones para no salirme del tablero para -Y
                    for (int i = 1; i < MAX; i++) {
                        if (board.getPiece(new Position(x1, y1-i)) != null && y1-i != y2)
                            no_obstruction = false;
                        if (y1-i == y2) //analizo si llego a la posicion final y se retorna si hay obstruccion
                            return no_obstruction;
                    }
                }
            }
            else if (y2 == y1) { //verifico si me muevo unicamente sobre el eje X
                if (x2 > x1) { //analizo movimiento para +X
                    MAX = x2-x1; //seteo el limite de iteraciones para no salirme del tablero para +X
                    for (int i = 1; i < MAX; i++) { //analizo movimiento para +X
                        if (board.getPiece(new Position(x1+i, y1)) != null && x1+i != x2)
                            no_obstruction = false;
                        if (x1+i == x2) //analizo si llego a la posicion final y se retorna si hay obstruccion
                            return no_obstruction;
                    }
                }
                else { //analizo movimiento para -X
                    MAX = x1-x2; //seteo el limite de iteraciones para no salirme del tablero para -X
                    for (int i = 1; i < MAX; i++) {
                        if (board.getPiece(new Position(x1-i, y1)) != null && x1-i != x2)
                            no_obstruction = false;
                        if (x1-i == x2) //analizo si llego a la posicion final y se retorna si hay obstruccion
                            return no_obstruction;
                    }
                }
            }
            else if (x2 > x1 && y2 > y1) { //verifico si me muevo unicamente en direccion +X+Y
                if (8-x1 < 8-y1) //seteo el limite de iteraciones para no salirme del tablero buscando el eje mas chico
                    MAX = x2-x1;
                else
                    MAX = y2-y1;
                for (int i = 1; i < MAX; i++) { //analizo movimiento
                    if (board.getPiece(new Position(x1+i, y1+i)) != null && y1+i != y2)
                        no_obstruction = false;
                    if (y1+i == y2) //analizo si llego a la posicion final y se retorna si hay obstruccion
                        return no_obstruction;
                }
            }
            else if (x2 > x1) { //verifico si me muevo unicamente en el eje +X-Y
                if (8-x1 < y1+1) //seteo el limite de iteraciones para no salirme del tablero buscando el eje mas chico
                    MAX = x2-x1;
                else
                    MAX = y1-y2;
                for (int i = 1; i < MAX; i++) { //analizo movimiento
                    if (board.getPiece(new Position(x1+i, y1-i)) != null && y1-i != y2)
                        no_obstruction = false;
                    if (y1-i == y2) //analizo si llego a la posicion final y se retorna si hay obstruccion
                        return no_obstruction;
                }
            }
            else if (y2 > y1) { //verifico si me muevo unicamente en el eje -X+Y
                if (8-x1 < 8-y1) //seteo el limite de iteraciones para no salirme del tablero buscando el eje mas chico
                    MAX = x1-x2;
                else
                    MAX = y2-y1;
                for (int i = 1; i < MAX; i++) { //analizo movimiento
                    if (board.getPiece(new Position(x1-i, y1+i)) != null && y1+i != y2)
                        no_obstruction = false;
                    if (y1+i == y2) //analizo si llego a la posicion final y se retorna si hay obstruccion
                        return no_obstruction;
                }
            }
            else { //verifico si me muevo unicamente en el eje -X-Y
                if (8-x1 < 8-y1) //seteo el limite de iteraciones para no salirme del tablero buscando el eje mas chico
                    MAX = x1-x2;
                else
                    MAX = y1-y2;
                for (int i = 1; i < MAX; i++) { //analizo movimiento
                    if (board.getPiece(new Position(x1-i, y1-i)) != null && y1-i != y2)
                        no_obstruction = false;
                    if (y1-i == y2) //analizo si llego a la posicion final y se retorna si hay obstruccion
                        return no_obstruction;
                }
            }
            return no_obstruction;
        }
        else
            return true;
    }

    /*
     * se le pasa una posicion por argumento y devuelve un booleano en el caso de
     * que esa posicion se vea atacada por alguna pieza enemiga
     */
    private boolean isAttacked(Position pos) {
        ColorEnum opponent;
        ArrayList<Position> positions = new ArrayList<>();
        if (turn.equals(ColorEnum.BLACK))
            opponent = ColorEnum.WHITE;
        else
            opponent = ColorEnum.BLACK;
        for (int i = 0; i < 8; i++) //cargo todas las piezas rivales del tablero
            for (int j = 0; j < 8; j++)
                if (board.getPiece(new Position(i, j)) != null)
                    if (board.getPiece(new Position(i, j)).getColor().equals(opponent))
                        positions.add(new Position(i, j));
        for (Position rival_piece : positions) //analizo los movimientos de todas las fichas enemigas recopiladas y debo corroborar si atacan las posiciones pasadas por parametro
            if (isValidMovement(rival_piece, pos))
                return true;
        return false;
    }

    /*
     * se le pasa un arraylist de posiciones por argumento y devuelve un booleano en el caso de
     * que alguna de esas posiciones se vea atacada po alguna pieza enemiga
     */
    private boolean isAttacked(ArrayList<Position> pos) {
        ColorEnum opponent;
        ArrayList<Position> positions = new ArrayList<>();
        if (turn.equals(ColorEnum.BLACK))
            opponent = ColorEnum.WHITE;
        else
            opponent = ColorEnum.BLACK;
        for (int i = 0; i < 8; i++) //cargo todas las piezas rivales del tablero
            for (int j = 0; j < 8; j++)
                if (board.getPiece(new Position(i, j)) != null)
                    if (board.getPiece(new Position(i, j)).getColor().equals(opponent))
                        positions.add(new Position(i, j));
        for (Position position_to_analize : pos) //analizo los movimientos de todas las fichas enemigas recopiladas y debo corroborar si atacan las posiciones pasadas por parametro
            for (Position rival_piece : positions)
                if (analizeTrajectory(rival_piece, position_to_analize))
                    return true;
        return false;
    }

    private boolean isValidMovement(Position posOne, Position posTwo) {
        ArrayList<Position> possibleTakes = board.getPiece(posOne).possibleTakes(posOne);
        if (SearchArray.searchPositionInArray(possibleTakes, posTwo) && board.getPiece(posTwo) != null) //veo si la posicion final se encuentra en una posicion de toma valido de la pieza
            return analizeTrajectory(posOne, posTwo);
        else
            return false;
    }
}
