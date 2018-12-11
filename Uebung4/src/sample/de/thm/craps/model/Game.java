package sample.de.thm.craps.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Random;

/**
 * Singleton Pattern
 */
public class Game {

    public static final int PLAYER_A = 0;
    public static final int PLAYER_B = 1;

    public static final int GAMESTATE_NOT_STARTED = 0;
    public static final int GAMESTATE_RUNNING = 1;
    public static final int GAMESTATE_FINISHED = 2;

    private static Game instance;

    private IntegerProperty winningSum;

    private IntegerProperty sumPlayerA;

    private IntegerProperty sumPlayerB;

    private IntegerProperty currentPlayer; // 0 -> Player A, 1 -> Player B

    private IntegerProperty currentGameState; // 0 -> NotStarted, 1 -> Running, 2 -> Finished

    private Game() {
        winningSum = new SimpleIntegerProperty(20);
        sumPlayerA = new SimpleIntegerProperty(0);
        sumPlayerB = new SimpleIntegerProperty(0);
        currentPlayer = new SimpleIntegerProperty(0);
        currentGameState = new SimpleIntegerProperty(0);

        // use random function for deciding which player begins
        Random rand = new Random();
        currentPlayer.setValue(rand.nextInt(2));
    }

    public static Game getInstance() {
        if(instance == null) {
            instance = new Game();
        }
        return instance;
    }

    public void roll() {
        if(currentGameState.getValue() == GAMESTATE_NOT_STARTED) {
            currentGameState.setValue(GAMESTATE_RUNNING);
        }
        Dice.getInstance().roll();
    }

    public IntegerProperty winningSumProperty() {
        return winningSum;
    }

    public void setWinningSum(int winningSum) {
        this.winningSum.setValue(winningSum);
        if(getWinner() != -1) {
            currentGameState.setValue(GAMESTATE_FINISHED); // Set game to finish
        }
    }

    public IntegerProperty sumPlayerAProperty() {
        return sumPlayerA;
    }

    public IntegerProperty sumPlayerBProperty() {
        return sumPlayerB;
    }

    public void increaseSumOfCurrentPlayer(int increaseValue) {
        if(currentPlayer.getValue() == PLAYER_A) {
            this.sumPlayerA.setValue(this.sumPlayerA.intValue() + increaseValue);
        } else if(currentPlayer.getValue() == PLAYER_B) {
            this.sumPlayerB.setValue(this.sumPlayerB.intValue() + increaseValue);
        }

        if(getWinner() != -1) {
            currentGameState.setValue(GAMESTATE_FINISHED); // Set game to finish
        }
    }

    public IntegerProperty currentPlayerProperty() {
        return currentPlayer;
    }

    public IntegerProperty currentGameStateProperty() {
        return currentGameState;
    }

    public int nextPlayer() {
        currentPlayer.setValue((currentPlayer.getValue() + 1 ) % 2);
        return currentPlayer.getValue();
    }

    public int getWinner() {
        int winningSum = winningSumProperty().getValue();
        int sumPlayerA = sumPlayerAProperty().getValue();
        int sumPlayerB = sumPlayerBProperty().getValue();
        if(sumPlayerA >= winningSum && sumPlayerB >= winningSum) {
            return 2; // Draw
        } else if(sumPlayerA >= winningSum) {
            return PLAYER_A; // Player A wins
        } else if(sumPlayerB >= winningSum) {
            return PLAYER_B; // Player B wins
        } else {
            return -1; // No result yet
        }
    }

    public void restart() {
        sumPlayerA.setValue(0);
        sumPlayerB.setValue(0);
        currentGameState.setValue(GAMESTATE_NOT_STARTED);
        // use random function for deciding which player begins
        Random rand = new Random();
        currentPlayer.setValue(rand.nextInt(2));
    }

}
