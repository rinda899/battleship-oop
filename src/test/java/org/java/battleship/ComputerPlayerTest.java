package org.java.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComputerPlayerTest {

    ComputerPlayer computerPlayer;

    @BeforeEach
    void setUp() {
        computerPlayer = new ComputerPlayer();
        // Reset board state and ships
        for (int i = 0; i < Game.boardLength; i++) {
            for (int j = 0; j < Game.boardLength; j++) {
                Game.boardMap[i][j] = Game.waterArea;
            }
        }
        Game.playerShips = 0;
        Game.compShips = 0;
    }

    @Test
    void testDeployShip() {
        computerPlayer.deployShip();
        assertEquals(5, Game.compShips, "Computer should deploy exactly 5 ships.");

        int shipCount = 0;
        for (int i = 0; i < Game.boardLength; i++) {
            for (int j = 0; j < Game.boardLength; j++) {
                if (Game.boardMap[i][j] == Game.computerId) {
                    shipCount++;
                }
            }
        }
        assertEquals(5, shipCount, "There should be 5 computer ships on the board.");
    }

    @Test
    void testDeployInAttackMiss() {
        int testRow = 2;
        int testCol = 2;
        Game.boardMap[testRow][testCol] = Game.waterArea; // Ensure the target is water

        // Directly call attacking method with controlled input
        computerPlayer.attacking(testRow, testCol);

        assertEquals(Game.computerMissed, Game.boardMap[testRow][testCol],
                "The cell at (" + testRow + ", " + testCol + ") should be marked as missed after the attack.");
    }


    @Test
    void testDeployInAttackHitPlayerShip() {
        // Arrange: Place a player ship at a specific location
        int targetRow = 3;
        int targetCol = 3;
        Game.boardMap[targetRow][targetCol] = Game.playerId; // Place a player ship
        Game.playerShips = 1; // Set player ship count to 1

        // Act: Directly simulate the computer's attack on the known location
        computerPlayer.attacking(targetRow, targetCol);

        // Assert: Verify the attack hit the player ship and updated the board correctly
        assertEquals(Game.guessedByCom, Game.boardMap[targetRow][targetCol],
                "The cell at (3, 3) should be marked as guessed by the computer after the attack.");
        assertEquals(0, Game.playerShips,
                "Player ship count should decrease to 0 after being hit.");
    }

}
