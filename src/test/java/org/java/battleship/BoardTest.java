package org.java.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {

    Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
        // Reset board state
        for (int i = 0; i < Game.boardLength; i++) {
            for (int j = 0; j < Game.boardLength; j++) {
                Game.boardMap[i][j] = Game.waterArea;
            }
        }
    }

    @Test
    void testBoardDisplayEmpty() {
        board.board(Game.sea);
        for (int i = 0; i < Game.boardLength; i++) {
            for (int j = 0; j < Game.boardLength; j++) {
                assertEquals(Game.waterArea, Game.boardMap[i][j], "The board should be initialized with water areas.");
            }
        }
    }

    @Test
    void testBoardWithShips() {
        Game.boardMap[0][0] = Game.playerId; // Place a player ship
        Game.boardMap[1][1] = Game.computerId; // Place a computer ship
        board.board(Game.sea);
        assertEquals(Game.playerId, Game.boardMap[0][0], "Player ship should be at (0, 0).");
        assertEquals(Game.computerId, Game.boardMap[1][1], "Computer ship should be at (1, 1).");
    }
}
