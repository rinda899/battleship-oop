package org.java.battleship;

import java.util.Scanner;

public class Game {
    static int playerShips       = 0;
    static int compShips         = 0;
    static int boardLength       = 10;
    static int[][] boardMap = new int[boardLength][boardLength];

    static int waterArea         = 0;
    static int playerId          = 1;
    static int computerId        = 2;

    static char sea              = ' ';
    static char missed           = '-';
    static char guess            = '!';
    static char sunkOwnShipMark  = 'X';
    static char ships            = '@';

    static int playerMissed      = 3;
    static int computerMissed    = 4;
    static int guessed           = 5;
    static int guessedByCom      = 7;
    static int sunkOwnShip       = 6;

    public static void main(String[] args) {
        Board board = new Board();
        Player human = new HumanPlayer();
        Player comp = new ComputerPlayer();

        Scanner in = new Scanner(System.in);
        String resetGame;
        do{
            playerShips = 0;
            compShips   = 0;
            for(int i=0;i<boardLength;++i){
                for(int j=0;j<boardLength;++j){
                    boardMap[i][j]=waterArea;
                }
            }
            System.out.println("   ---------------------");
            System.out.println("   WELCOME TO BATTLESHIP");
            System.out.println("   ---------------------");
            System.out.println();
            board.board(sea);
            human.deployShip();
            comp.deployShip();
            System.out.println("\n   ---------------------");
            System.out.println("     THE WAR HAS BEGUN");
            System.out.println("   ---------------------\n");
            while(playerShips > 0 && compShips > 0){
                human.deployInAttack();
                if(compShips != 0 && playerShips !=0) {
                    comp.deployInAttack();
                }
            }
            System.out.println();
            System.out.println("   ---------------------");
            System.out.print("  "+endOfGame()+"\n");
            System.out.println("   ---------------------");
            System.out.print("> Play again (Y / N) ? ");
            resetGame = in.nextLine();System.out.println();
        }while (resetGame.equals("Y") || resetGame.equals("y"));
        System.out.println("> Thank you for playing this game !");
    }

    public static boolean validateInt(String row, String col){
        String[] number = {"1","2","3","4","5","6","7","8","9","0"};
        boolean rows = false;
        boolean cols = false;
        for (String s : number) {
            if (row.equals(s)) {
                rows = true;
                break;
            }
        }
        for (String s : number) {
            if (col.equals(s)) {
                cols = true;
                break;
            }
        }
        return rows && cols;
    }

    public static String endOfGame(){
        String message = "";
        if(playerShips == 0){
            message = "   Oops! You lose.";
        }
        if(compShips == 0){
            message = "   Hooray! You win.";
        }
        return message;
    }
}
