package sample;

import javafx.application.Platform;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import org.w3c.dom.css.Rect;

import java.awt.*;
import java.util.Random;

public class Dice implements PaneInterface {

    private Point diceOffset;
    private Pane diceBox;
    private Rectangle dice;
    private Circle eyeCenter;
    private Circle eyeTopLeft;
    private Circle eyeCenterLeft;
    private Circle eyeBottomLeft;
    private Circle eyeTopRight;
    private Circle eyeCenterRight;
    private Circle eyeBottomRight;

    private int currentNumber;

    @Override
    public Pane getPane() {
        return this.diceBox;
    }

    public Dice() {
        currentNumber = 6;
        diceOffset = new Point(0, 0);
        diceBox = new Pane();
        initEyes();
        draw(currentNumber);
    }

    private void initEyes() {
        eyeCenter = new Circle(diceOffset.getX() + 50, diceOffset.getY() + 50, 7);
        eyeTopLeft = new Circle(diceOffset.getX() + 25, diceOffset.getY() + 25, 7);
        eyeCenterLeft = new Circle(diceOffset.getX() + 25, diceOffset.getY() + 50, 7);
        eyeBottomLeft = new Circle(diceOffset.getX() + 25, diceOffset.getY() + 75, 7);
        eyeTopRight = new Circle(diceOffset.getX() + 75, diceOffset.getY() + 25, 7);
        eyeCenterRight = new Circle(diceOffset.getX() + 75, diceOffset.getY() + 50, 7);
        eyeBottomRight = new Circle(diceOffset.getX() + 75, diceOffset.getY() + 75, 7);
    }

    public Point getDiceOffset() {
        return diceOffset;
    }

    public void setDiceOffset(Point diceOffset) {
        this.diceOffset = diceOffset;
        initEyes();
        draw(currentNumber);
    }

    public int roll() {
        Random rand = new Random();
        int result = rand.nextInt(6) + 1;
        draw(result);
        currentNumber = result;
        return result;
    }

    private void draw(int number) {
        dice = new Rectangle(diceOffset.getX() + 0, diceOffset.getY() + 0, 100, 100);
        dice.setFill(Color.WHITESMOKE);
        diceBox.getChildren().clear();
        diceBox.getChildren().add(dice);
        if(number == 1) {
            diceBox.getChildren().add(eyeCenter);
        } else if(number == 2) {
            diceBox.getChildren().addAll(eyeBottomLeft, eyeTopRight);
        } else if(number == 3) {
            diceBox.getChildren().addAll(eyeBottomLeft, eyeCenter, eyeTopRight);
        } else if(number == 4) {
            diceBox.getChildren().addAll(eyeTopLeft, eyeBottomLeft, eyeBottomRight, eyeTopRight);
        } else if(number == 5) {
            diceBox.getChildren().addAll(eyeTopLeft, eyeBottomLeft, eyeBottomRight, eyeTopRight, eyeCenter);
        } else if(number == 6) {
            diceBox.getChildren().addAll(eyeTopLeft, eyeCenterLeft, eyeBottomLeft, eyeBottomRight, eyeCenterRight, eyeTopRight);
        }
    }

}