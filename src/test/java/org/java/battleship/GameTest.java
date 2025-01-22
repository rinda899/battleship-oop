package org.java.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @BeforeEach
    void setUp() {
        // Reset game state
        for (int i = 0; i < Game.boardLength; i++) {
            for (int j = 0; j < Game.boardLength; j++) {
                Game.boardMap[i][j] = Game.waterArea;
            }
        }
        Game.playerShips = 0;
        Game.compShips = 0;
    }

    @Test
    void testValidateInt() {
        assertTrue(Game.validateInt("1", "5"), "Valid integer inputs should return true.");
        assertFalse(Game.validateInt("a", "3"), "Invalid row input should return false.");
        assertFalse(Game.validateInt("2", "b"), "Invalid column input should return false.");
        assertFalse(Game.validateInt("x", "y"), "Non-integer inputs should return false.");
    }

    @Test
    void testEndOfGamePlayerLose() {
        Game.playerShips = 0;
        Game.compShips = 1;
        String result = Game.endOfGame();
        assertEquals("   Oops! You lose.", result, "Player losing condition message is incorrect.");
    }

    @Test
    void testEndOfGamePlayerWin() {
        Game.playerShips = 1;
        Game.compShips = 0;
        String result = Game.endOfGame();
        assertEquals("   Hooray! You win.", result, "Player winning condition message is incorrect.");
    }
}
