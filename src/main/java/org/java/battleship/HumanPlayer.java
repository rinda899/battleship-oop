package org.java.battleship;

import java.util.Scanner;


public class HumanPlayer extends Player{
    Board board = new Board();

    @Override
    public void deployShip(){
        Scanner in = new Scanner(System.in);
        System.out.println("=== Deploy your ships ===");
        int i=1;
        while(playerShips < 5){
            System.out.print("> Input the Y coordinate for your "+i+". Ship : ");
            String row = in.next();
            System.out.print("> Input the X coordinate for your "+i+". Ship : ");
            String col = in.next();
            if(!validateInt(row, col)) {
                System.out.println("< Invalid coordinates !");
            }else {
                int rows = Integer.parseInt(row);
                int cols = Integer.parseInt(col);
                if (rows < boardLength && cols < boardLength && cols >= 0 && rows >= 0) {
                    if (boardMap[rows][cols] != waterArea) {
                        System.out.println("< A ship was already placed there !");
                    } else {
                        boardMap[rows][cols] = playerId;
                        System.out.println("< Ship was successfully deployed !");
                        playerShips++;++i;
                    }
                } else {
                    System.out.println("< Invalid coordinates !");
                }
            }
        }
        System.out.println();
        System.out.println("\n> Computer is deploying its ships!\n");
        board.board(sea);
    }

    @Override
    public void deployInAttack(boolean isTesting, int tRow, int tCol) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("> Player's turn.");
            System.out.print("> Insert Y coordinate to attack: ");
            String row = isTesting ? String.valueOf(tRow) : scanner.next();
            System.out.print("> Insert X coordinate to attack: ");
            String col = isTesting ? String.valueOf(tCol) : scanner.next();

            if (!validateInt(row, col)) {
                System.out.println("< Invalid coordinate! Please try again.");
                continue; // Retry for valid input
            }

            int rows = Integer.parseInt(row);
            int cols = Integer.parseInt(col);
            if (rows < boardLength && cols < boardLength && rows >= 0 && cols >= 0) {
                attacking(rows, cols);
                break; // Exit loop after a valid attack
            } else {
                System.out.println("< Invalid coordinate! Please try again.");
            }
        }
    }


    @Override
    public void attacking(int row, int col){
        int map = boardMap[row][col];
        if(map!=waterArea) {
            if (map == playerMissed || map == computerMissed || map == sunkOwnShip || map == guessedByCom || map == guessed) {
                System.out.println("< Already guessed !");
                deployInAttack();
            } else if (map == playerId) {
                System.out.println("< You sunk your own ship !");
                boardMap[row][col] = sunkOwnShip;
                playerShips--;
                board.board(sea);
            } else if (map == computerId){
                System.out.println("> You sunk the enemy's ship !");
                boardMap[row][col] = guessed;
                compShips--;
                board.board(sea);
            }
        }else {
            System.out.println("< Oops, you miss !\n");
            boardMap[row][col] = playerMissed;
            board.board(sea);
        }
    }

    public String getUserInput() {
        Scanner in = new Scanner(System.in);
        return in.next();
    }
}
