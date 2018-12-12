package de.thm.craps;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import de.thm.craps.view.*;

import java.awt.*;

public class Main extends Application {

    private TitleUI title;

    private StatusUI status;

    private BorderPane animationBox;

    private DiceUI dice;

    private MatchFieldUI matchField;

    private ControlsUI controls;

    private VBox root;

    @Override
    public void start(Stage primaryStage) throws Exception {

        // Title UI
        title = new TitleUI();

        // Status UI
        status = new StatusUI();

        // Animation Box
        animationBox = new BorderPane();
        animationBox.setPadding(new Insets(50, 25, 0, 25));
        animationBox.setStyle("-fx-background-color: #485b7a");

        // Dice UI
        dice = new DiceUI();
        dice.setDiceOffset(new Point(40, 110));
        animationBox.setLeft(dice.getPane());

        // MatchField UI
        matchField = new MatchFieldUI();
        animationBox.setRight(matchField.getPane());

        // Control UI
        controls = new ControlsUI(dice);

        // Root Box
        root = new VBox();
        root.getChildren().addAll(title.getPane(), status.getPane(), animationBox, controls.getPane());

        // Primary Stage
        primaryStage.setTitle("Würfelspiel");
        primaryStage.setScene(new Scene(root, 560, 758));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

