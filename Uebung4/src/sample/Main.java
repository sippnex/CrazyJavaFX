package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    private VBox root;

    private VBox titleBox;
    private Label labelTitle;

    private VBox statusBox;
    private Label labelSumPlayerA;
    private Label labelSumPlayerB;
    private Label labelActivePlayer;
    private Label labelGameStatus;

    @Override
    public void start(Stage primaryStage) throws Exception {

        labelTitle = new Label("Würfelspiel :)");
        labelTitle.setScaleX(2);
        labelTitle.setScaleY(2);
        titleBox = new VBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(25, 10, 25, 10));
        titleBox.setStyle("-fx-background-color: #b86565");
        titleBox.getChildren().add(labelTitle);

        labelSumPlayerA = new Label("Summe Spieler A");
        labelSumPlayerB = new Label("Summe Spieler B");
        labelActivePlayer = new Label("Spieler A ist dran");
        labelGameStatus = new Label("Spiel noch nicht begonnen");

        statusBox = new VBox();
        statusBox.setPadding(new Insets(10, 10, 10, 10));
        statusBox.setAlignment(Pos.CENTER);
        statusBox.setStyle("-fx-background-color: #dfd8d1");
        statusBox.getChildren().addAll(labelSumPlayerA, labelSumPlayerB, labelActivePlayer, labelGameStatus);

        root = new VBox();
        root.getChildren().addAll(titleBox, statusBox);

        primaryStage.setTitle("Uebung 4");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

