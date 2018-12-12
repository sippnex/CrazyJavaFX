package de.thm.craps.view;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import de.thm.craps.model.Game;

public class StatusUI implements UI {

    private VBox statusBox;

    private Label labelSumPlayerA;

    private Label labelSumPlayerB;

    private Label labelActivePlayer;

    private Label labelGameStatus;

    public StatusUI() {
        statusBox = new VBox();
        statusBox.setPadding(new Insets(10, 10, 10, 10));
        statusBox.setAlignment(Pos.CENTER);
        statusBox.setStyle("-fx-background-color: #dfd8d1");
        labelSumPlayerA = new Label("Summe Spieler A: 0");
        Game.getInstance().sumPlayerAProperty().addListener((ChangeListener<? super Number>) (observable, oldValue, newValue) -> {
            labelSumPlayerA.setText("Summe Spieler A: " + newValue);
        });
        labelSumPlayerB = new Label("Summe Spieler B: 0");
        Game.getInstance().sumPlayerBProperty().addListener((ChangeListener<? super Number>) (observable, oldValue, newValue) -> {
            labelSumPlayerB.setText("Summe Spieler B: " + newValue);
        });
        labelActivePlayer = new Label();
        labelActivePlayer.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        if(Game.getInstance().currentPlayerProperty().getValue() == Game.PLAYER_A) {
            labelActivePlayer.setText("Spieler A ist dran");
        } else if(Game.getInstance().currentPlayerProperty().getValue() == Game.PLAYER_B) {
            labelActivePlayer.setText("Spieler B ist dran");
        }
        Game.getInstance().currentPlayerProperty().addListener((ChangeListener<? super Number>) (observable, oldValue, newValue) -> {
            if((int)newValue == 0) {
                labelActivePlayer.setText("Spieler A ist dran");
            } else if((int)newValue == 1) {
                labelActivePlayer.setText("Spieler B ist dran");
            }
        });
        labelGameStatus = new Label();
        labelGameStatus.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        Game.getInstance().currentGameStateProperty().addListener((ChangeListener<? super Number>) (observable, oldValue, newValue) -> {
            if((int)newValue == Game.GAMESTATE_FINISHED) {
                if(Game.getInstance().getWinner() == Game.PLAYER_A) {
                    labelGameStatus.setText("Spieler A gewinnt!");
                } else if(Game.getInstance().getWinner() == Game.PLAYER_B) {
                    labelGameStatus.setText("Spieler B gewinnt!");
                } else if(Game.getInstance().getWinner() == 2) {
                    labelGameStatus.setText("Unentschieden!");
                }
            } else {
                labelGameStatus.setText("");
            }
        });
        statusBox.getChildren().addAll(labelSumPlayerA, labelSumPlayerB, labelActivePlayer, labelGameStatus);
    }

    @Override
    public Pane getPane() {
        return statusBox;
    }
}
