package de.thm.craps.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import de.thm.craps.model.Game;

public class ControlsUI implements UI {

    private BorderPane controlBox;

    private Button btnRoll;

    private Button btnReset;

    private VBox sliderBox;

    private Slider sliderSum;

    private Label labelSum;

    public ControlsUI(DiceUI dice) {
        controlBox = new BorderPane();
        controlBox.setPadding(new Insets(20, 20, 20, 20));
        controlBox.setMinHeight(100);
        controlBox.setStyle("-fx-background-color: #3d3a4b");
        btnRoll = new Button("Würfeln");
        btnRoll.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Game.getInstance().roll();
            }
        });
        btnReset = new Button("Neu starten");
        btnReset.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Game.getInstance().restart();
            }
        });
        controlBox.setLeft(btnRoll);
        controlBox.setRight(btnReset);

        sliderBox = new VBox();
        sliderBox.setPadding(new Insets(20,0,0,0));
        sliderSum = new Slider();
        sliderSum.setValue(Game.getInstance().winningSumProperty().getValue());
        sliderSum.valueProperty().addListener(new ChangeListener<Number>() {
            public void changed(ObservableValue<? extends Number> ov,
                                Number old_val, Number new_val) {
                Game.getInstance().setWinningSum(new_val.intValue());
                labelSum.setText("Gewinnschwelle: " + new_val.intValue());
            }
        });
        labelSum = new Label("Gewinnschwelle: " + Game.getInstance().winningSumProperty().getValue());
        labelSum.setTextFill(Color.web("#dfd8d1"));
        sliderBox.setAlignment(Pos.CENTER);
        sliderBox.getChildren().add(sliderSum);
        sliderBox.getChildren().add(labelSum);
        controlBox.setBottom(sliderBox);

        Game.getInstance().currentGameStateProperty().addListener((ChangeListener<? super Number>) (observable, oldValue, newValue) -> {
            if((int)newValue == Game.GAMESTATE_FINISHED) {
                btnRoll.setDisable(true);
            } else {
                btnRoll.setDisable(false);
            }
        });
    }

    @Override
    public Pane getPane() {
        return controlBox;
    }

}
