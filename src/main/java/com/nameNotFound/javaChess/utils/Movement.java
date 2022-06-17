package com.nameNotFound.javaChess.utils;

public class Movement {
    private Position posOne;
    private Position posTwo;
    private boolean oldWasMoved;

    public Movement(Position posOne, Position posTwo, boolean oldWasMoved) {
        this.posOne = posOne;
        this.posTwo = posTwo;
        this.oldWasMoved = oldWasMoved;
    }

    //Getters And Setters
    public Position getPosOne() {
        return posOne;
    }
    public Position getPosTwo() {
        return posTwo;
    }
    public boolean isOldWasMoved() {
        return oldWasMoved;
    }
    public void setPosOne(Position posOne) {
        this.posOne = posOne;
    }
    public void setPosTwo(Position posTwo) {
        this.posTwo = posTwo;
    }
    public void setOldWasMoved(boolean oldWasMoved) {
        this.oldWasMoved = oldWasMoved;
    }
}