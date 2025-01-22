package org.java.battleship;

public abstract class Player extends Game{
    public void deployShip(){}

    public void deployInAttack(boolean isTesting, int tRow, int tCol){}

    public void attacking(int row, int col){}

    public void deployInAttack(){
        deployInAttack(false, 0, 0);
    }
}
