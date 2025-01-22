package org.java.battleship;

public class Board extends Game{
    public void board(char sea){
        System.out.print("    ");
        for(int i = 0;i<boardLength;i++){
            System.out.print(i+" ");
        }
        System.out.println();
        for(int row = 0;row<boardLength;++row){
            System.out.print(row+" | ");
            for(int col = 0;col<boardLength;++col) {
                int map = boardMap[row][col];
                if(map == waterArea){
                    System.out.print(sea+" ");
                }else if(map == playerId){
                    System.out.print(ships+" ");
                }else if(map == computerId){
                    System.out.print(sea+" ");
                }else if(map == playerMissed){
                    System.out.print(missed+" ");
                }else if(map == guessed){
                    System.out.print(guess+" ");
                }else if(map == sunkOwnShip){
                    System.out.print(sunkOwnShipMark+" ");
                }else if(map == computerMissed){
                    System.out.print(sea+" ");
                }else if(map == guessedByCom){
                    System.out.print(sunkOwnShipMark+" ");
                }else{ System.out.print("?"); }
            }
            System.out.print("| "+row);
            System.out.println();
        }
        System.out.print("    ");
        for(int i = 0;i<boardLength;i++){
            System.out.print(i+" ");
        }
        System.out.println();
        System.out.println("Player ships : "+playerShips+"  |  Computer ships : "+compShips+"\n");
    }
}
