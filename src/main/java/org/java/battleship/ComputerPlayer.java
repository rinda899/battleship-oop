package org.java.battleship;

import java.util.Random;

public class ComputerPlayer extends Player{
    Board board = new Board();

    @Override
    public void deployShip() {
        while(compShips < 5){
            int awhileRow = new Random().nextInt(boardLength);
            int awhileCol = new Random().nextInt(boardLength);
            if(boardMap[awhileRow][awhileCol]==waterArea) {
                boardMap[awhileRow][awhileCol] = computerId;
                compShips++;
                System.out.println("> Computer ship deployed .");
            }
        }
        System.out.println();
    }

    @Override
    public void deployInAttack() {
        System.out.println("> Computer's turn.");
        int compRow = new Random().nextInt(boardLength);
        int compCol = new Random().nextInt(boardLength);
        attacking(compRow,compCol);
    }

    @Override
    public void attacking(int row, int col){
        int map = boardMap[row][col];
        if(map!=waterArea) {
            if (map == playerMissed || map == computerMissed || map == sunkOwnShip || map == guessedByCom || map == guessed) {
                System.out.println("< Already guessed !");
                deployInAttack();
            } else if (map == playerId) {
                System.out.println("< The computer sunk your ship !");
                boardMap[row][col] = guessedByCom;
                playerShips--;
                board.board(sea);
            } else if (map == computerId){
                System.out.println("> The computer sunk their own ship !");
                boardMap[row][col] = guessed;
                compShips--;
                board.board(sea);
            }
        }else {
            System.out.println("< Computer miss !\n");
            boardMap[row][col] = computerMissed;
            board.board(sea);
        }
    }
}
