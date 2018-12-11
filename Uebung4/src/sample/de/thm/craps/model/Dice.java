package sample.de.thm.craps.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Random;

/**
 * Singleton Pattern
 */
public class Dice {

    private static Dice instance;

    private IntegerProperty currentNumber;

    private Dice() {
        currentNumber = new SimpleIntegerProperty(1);
    }

    public static Dice getInstance() {
        if(instance == null) {
            instance = new Dice();
        }
        return instance;
    }

    public void roll() {
        Random rand = new Random();
        int result = rand.nextInt(6) + 1;
        currentNumber.setValue(result);
        Game.getInstance().increaseSumOfCurrentPlayer(result);
        Game.getInstance().nextPlayer();
    }

    public IntegerProperty currentNumberProperty() {
        return currentNumber;
    }

}
