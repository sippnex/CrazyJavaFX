package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

    private VBox root;

    private VBox titleBox;
    private Label labelTitle;

    private VBox statusBox;
    private Label labelSumPlayerA;
    private Label labelSumPlayerB;
    private Label labelActivePlayer;
    private Label labelGameStatus;

    private BorderPane animationBox;

    private Dice dice;

    private Pane matchBox;

    private BorderPane controlBox;
    private Button btnRole;
    private Button btnReset;
    private Slider sliderSum;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Title Box
        titleBox = new VBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(25, 10, 25, 10));
        titleBox.setStyle("-fx-background-color: #b86565");
        labelTitle = new Label("Würfelspiel :)");
        labelTitle.setScaleX(2);
        labelTitle.setScaleY(2);
        titleBox.getChildren().add(labelTitle);

        // Status Box
        statusBox = new VBox();
        statusBox.setPadding(new Insets(10, 10, 10, 10));
        statusBox.setAlignment(Pos.CENTER);
        statusBox.setStyle("-fx-background-color: #dfd8d1");
        labelSumPlayerA = new Label("Summe Spieler A");
        labelSumPlayerB = new Label("Summe Spieler B");
        labelActivePlayer = new Label("Spieler A ist dran");
        labelGameStatus = new Label("Spiel noch nicht begonnen");
        statusBox.getChildren().addAll(labelSumPlayerA, labelSumPlayerB, labelActivePlayer, labelGameStatus);

        // Animation Box
        animationBox = new BorderPane();
        animationBox.setPadding(new Insets(25, 25, 25, 25));
        animationBox.setStyle("-fx-background-color: #485b7a");
        dice = new Dice();
        dice.setDiceOffset(new Point(40, 110));
        animationBox.setLeft(dice.getPane());
        matchBox = new Pane();
        matchBox.setPadding(new Insets(25, 25, 25, 25));
        Rectangle matchField = new Rectangle(0, 0, 250, 350);
        matchField.setFill(Color.WHITESMOKE);
        matchBox.getChildren().add(matchField);
        animationBox.setRight(matchBox);

        // Control Box
        controlBox = new BorderPane();
        controlBox.setPadding(new Insets(20, 20, 20, 20));
        controlBox.setMinHeight(100);
        controlBox.setStyle("-fx-background-color: #3d3a4b");
        btnRole = new Button("Würfeln");
        btnRole.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                dice.roll();
            }
        });
        btnReset = new Button("Neu starten");
        sliderSum = new Slider();
        controlBox.setLeft(btnRole);
        controlBox.setRight(btnReset);
        controlBox.setBottom(sliderSum);

        // Root Box
        root = new VBox();
        root.getChildren().addAll(titleBox, statusBox, animationBox, controlBox);

        // Primary Stage
        primaryStage.setTitle("Uebung 4");
        primaryStage.setScene(new Scene(root, 560, 670));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

