package sample.de.thm.craps.view;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import sample.de.thm.craps.model.Game;

public class MatchFieldUI implements UI {

    private int winningSum;
    private int sumPlayerA;
    private int sumPlayerB;

    private Pane matchBox;

    private Rectangle matchField;

    private Label labelGoal;

    private Rectangle goalLine;

    private Label labelPlayerA;
    private Rectangle barPlayerA;

    private Label labelPlayerB;
    private Rectangle barPlayerB;

    public MatchFieldUI() {
        winningSum = Game.getInstance().winningSumProperty().getValue();
        sumPlayerA = 0;
        sumPlayerB = 0;
        matchBox = new Pane();
        matchBox.setPadding(new Insets(25, 25, 25, 25));
        matchField = new Rectangle(0, 0, 250, 350);
        matchField.setFill(Color.WHITESMOKE);

        labelGoal = new Label("Sieg");
        labelGoal.layoutXProperty().setValue(25);
        labelGoal.layoutYProperty().setValue(25);
        goalLine = new Rectangle(60, 30, 165, 6);
        goalLine.setFill(Color.DARKGREEN);

        labelPlayerA = new Label("A");
        labelPlayerA.layoutXProperty().setValue(100);
        labelPlayerA.layoutYProperty().setValue(315);
        barPlayerA = new Rectangle(90, 300, 30, 0);
        barPlayerA.setFill(Color.GRAY);

        labelPlayerB = new Label("B");
        labelPlayerB.layoutXProperty().setValue(185);
        labelPlayerB.layoutYProperty().setValue(315);
        barPlayerB = new Rectangle(175, 300, 30, 0);
        barPlayerB.setFill(Color.GRAY);

        matchBox.getChildren().addAll(matchField, labelGoal, goalLine, labelPlayerA, barPlayerA, labelPlayerB, barPlayerB);
        goalLine.toFront();

        Game.getInstance().winningSumProperty().addListener((ChangeListener<? super Number>) (observable, oldValue, newValue) -> {
            winningSum = (int)newValue;
            if(Game.getInstance().currentGameStateProperty().getValue() == Game.GAMESTATE_RUNNING) {
                drawBars();
            }
        });

        Game.getInstance().sumPlayerAProperty().addListener((ChangeListener<? super Number>) (observable, oldValue, newValue) -> {
            sumPlayerA = (int) newValue;
            if(Game.getInstance().currentGameStateProperty().getValue() == Game.GAMESTATE_RUNNING) {
                drawBars();
            }
        });

        Game.getInstance().sumPlayerBProperty().addListener((ChangeListener<? super Number>) (observable, oldValue, newValue) -> {
            sumPlayerB = (int) newValue;
            if(Game.getInstance().currentGameStateProperty().getValue() == Game.GAMESTATE_RUNNING) {
                drawBars();
            }
        });

        Game.getInstance().currentGameStateProperty().addListener((ChangeListener<? super Number>) (observable, oldValue, newValue) -> {
            if((int)newValue == Game.GAMESTATE_FINISHED) {
                if(Game.getInstance().getWinner() == Game.PLAYER_A) {
                    barPlayerA.setFill(Color.GREEN);
                } else if(Game.getInstance().getWinner() == Game.PLAYER_B) {
                    barPlayerB.setFill(Color.GREEN);
                }
            } else if((int)newValue == Game.GAMESTATE_NOT_STARTED) {
                reset();
            }
        });
    }

    @Override
    public Pane getPane() {
        return matchBox;
    }


    public void reset() {
        sumPlayerA = 0;
        sumPlayerB = 0;
        barPlayerA.setFill(Color.GRAY);
        barPlayerB.setFill(Color.GRAY);
        drawBars();
    }

    public void drawBars() {

        // calculate and draw player A bar
        float percentagePlayerA = getPercentage(sumPlayerA, winningSum);
        barPlayerA.setY(300 - (265*percentagePlayerA));
        barPlayerA.setHeight(265*percentagePlayerA);

        // calculate and draw player B bar
        float percentagePlayerB = getPercentage(sumPlayerB, winningSum);
        barPlayerB.setY(300 - (265*percentagePlayerB));
        barPlayerB.setHeight(265*percentagePlayerB);
    }

    private float getPercentage(int currentSum, int winningSum) {
        // calculate percentage value
        float percentage = (float)currentSum / (float)winningSum;
        // Avoid overflow of bar with 0.1 tolerance
        if(percentage > 1.1) {
            percentage = 1.1f;
        }
        return percentage;
    }

}
