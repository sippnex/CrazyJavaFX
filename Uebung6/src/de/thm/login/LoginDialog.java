package de.thm.login;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Window;
import javafx.util.Pair;

public class LoginDialog extends Dialog<Pair<String, String>> {

    private Label labelEmail;
    private TextField textEmail;

    private Label labelPassword;
    private TextField textPassword;

    ButtonType buttonTypeSubmit;
    private Node buttonSubmit;
    private Button buttonClear;

    public LoginDialog() {
        this.setTitle("Login Dialog");
        this.setHeaderText("Please login");

        GridPane formBox = new GridPane();
        formBox.setHgap(10);
        formBox.setVgap(10);
        formBox.setAlignment(Pos.CENTER);

        // email row
        labelEmail = new Label("Email ");
        textEmail = new TextField();
        formBox.add(labelEmail, 0, 0);
        formBox.add(textEmail, 1, 0);

        // password row
        labelPassword = new Label("Password ");
        textPassword = new TextField();
        formBox.add(labelPassword, 0, 1);
        formBox.add(textPassword, 1, 1);

        // buttons
        buttonTypeSubmit = new ButtonType("Submit", ButtonBar.ButtonData.OK_DONE);
        buttonSubmit = this.getDialogPane().lookupButton(buttonTypeSubmit);
        buttonSubmit = new Button("Submit");
        buttonSubmit.setDisable(true);
        textEmail.textProperty().addListener((observable, oldValue, newValue) -> {
            buttonSubmit.setDisable(newValue.trim().isEmpty());
        });
        this.getDialogPane().getButtonTypes().addAll(buttonTypeSubmit, ButtonType.CANCEL);

        this.getDialogPane().setContent(formBox);

        // Request focus on the username field by default.
        Platform.runLater(() -> textEmail.requestFocus());

        // Convert the result to a email-password-pair when the submit button is clicked.
        this.setResultConverter(dialogButton -> {
            if (dialogButton == buttonTypeSubmit) {
                return new Pair<>(textEmail.getText(), textPassword.getText());
            }
            return null;
        });

    }

}
