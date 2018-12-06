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

    private VBox animationBox;

    private VBox controlBox;

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
        animationBox = new VBox();
        animationBox.setPadding(new Insets(10, 10, 10, 10));
        animationBox.setAlignment(Pos.CENTER);
        animationBox.setStyle("-fx-background-color: #485b7a");

        // Control Box
        controlBox = new VBox();
        controlBox.setPadding(new Insets(10, 10, 10, 10));
        controlBox.setAlignment(Pos.CENTER);
        controlBox.setStyle("-fx-background-color: #3d3a4b");

        // Root Box
        root = new VBox();
        root.getChildren().addAll(titleBox, statusBox, animationBox, controlBox);


        // Primary Stage
        primaryStage.setTitle("Uebung 4");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

