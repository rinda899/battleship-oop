package org.java.battleship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

public class HumanPlayerTest {

    private HumanPlayer humanPlayer;

    @BeforeEach
    public void setUp() {
        humanPlayer = new HumanPlayer();
        Game.boardMap = new int[Game.boardLength][Game.boardLength]; // Reset board
        Game.playerShips = 0;
        Game.compShips = 0;
    }

    @Test
    public void testDeployShip() {
        String simulatedInput = "0\n0\n1\n1\n2\n2\n3\n3\n4\n4\n"; // Simulate user input
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        try {
            humanPlayer.deployShip();

            // Verify 5 ships were deployed
            assertEquals(5, Game.playerShips, "Player should have deployed 5 ships.");

            // Verify ship placement on the board
            assertEquals(Game.playerId, Game.boardMap[0][0], "Ship should be placed at (0, 0).");
            assertEquals(Game.playerId, Game.boardMap[1][1], "Ship should be placed at (1, 1).");
            assertEquals(Game.playerId, Game.boardMap[2][2], "Ship should be placed at (2, 2).");
            assertEquals(Game.playerId, Game.boardMap[3][3], "Ship should be placed at (3, 3).");
            assertEquals(Game.playerId, Game.boardMap[4][4], "Ship should be placed at (4, 4).");
        } finally {
            System.setIn(originalIn); // Restore original System.in
        }
    }

    @Test
    public void testDeployInAttackMiss() {
        String simulatedInput = "2\n2\n"; // Simulate user input for attack
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        try {
            humanPlayer.deployInAttack(false, 2, 2);

            // Verify the cell at (2, 2) is marked as missed
            assertEquals(Game.playerMissed, Game.boardMap[2][2], "The cell at (2, 2) should be marked as missed.");
        } finally {
            System.setIn(originalIn); // Restore original System.in
        }
    }

    @Test
    public void testDeployInAttackHitEnemyShip() {
        // Place an enemy ship on the board
        Game.boardMap[3][3] = Game.computerId;
        Game.compShips = 1;

        String simulatedInput = "3\n3\n"; // Simulate user input for attack
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        try {
            humanPlayer.deployInAttack(false, 3, 3);

            // Verify the cell at (3, 3) is marked as guessed
            assertEquals(Game.guessed, Game.boardMap[3][3], "The cell at (3, 3) should be marked as guessed.");

            // Verify computer ships count decreases
            assertEquals(0, Game.compShips, "Computer ships count should decrease after being hit.");
        } finally {
            System.setIn(originalIn); // Restore original System.in
        }
    }

    @Test
    public void testDeployInAttackSinkOwnShip() {
        // Place a player ship on the board
        Game.boardMap[4][4] = Game.playerId;
        Game.playerShips = 1;

        String simulatedInput = "4\n4\n"; // Simulate user input for attack
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        try {
            humanPlayer.deployInAttack(false, 4, 4);

            // Verify the cell at (4, 4) is marked as sunkOwnShip
            assertEquals(Game.sunkOwnShip, Game.boardMap[4][4], "The cell at (4, 4) should be marked as sunkOwnShip.");

            // Verify player ships count decreases
            assertEquals(0, Game.playerShips, "Player ships count should decrease after sinking own ship.");
        } finally {
            System.setIn(originalIn); // Restore original System.in
        }
    }
}
