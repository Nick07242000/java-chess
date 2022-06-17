package com.nameNotFound.javaChess.utils;

import java.util.ArrayList;

/*
se le pasa por argumento un arraylist de posiciones y una posicion, y el metodo retornara un booleano
en el caso de que exista, o no, alguna posicion en el arraylist con las mismas coordenadas que la posicion
que se paso por argumento de la funcion
*/
public class SearchArray {
    public static boolean searchPositionInArray(ArrayList<Position> arrayPos, Position pos) {
        for (Position position : arrayPos) {
            if (position.getX() == pos.getX() && position.getY() == pos.getY())
                return true;
        }
        return false;
    }
}
