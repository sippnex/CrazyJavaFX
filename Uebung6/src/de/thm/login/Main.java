package de.thm.login;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.Optional;

public class Main extends Application {

    Label labelTitle;

    Button loginButton;

    Label labelResult;

    VBox root;

    @Override
    public void start(Stage primaryStage) throws Exception {

        labelTitle = new Label("A Dialog");
        labelTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 40));

        loginButton = new Button("Anmelden");
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoginDialog loginDialog = new LoginDialog();
                Optional<Pair<String, String>> result = loginDialog.showAndWait();
                result.ifPresent(emailPasswordKeyPair -> {
                    labelResult.setText("Result: " + emailPasswordKeyPair.getKey() + ", " + emailPasswordKeyPair.getValue());
                });
            }
        });

        labelResult = new Label();
        labelResult.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        labelResult.setTextFill(Color.DARKRED);

        // Root Box
        root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(10, 10, 10, 10));
        root.getChildren().addAll(labelTitle, loginButton, labelResult);

        // Primary Stage
        primaryStage.setTitle("Anmeldung");
        primaryStage.setScene(new Scene(root, 500, 778));
        primaryStage.show();

    }
}
