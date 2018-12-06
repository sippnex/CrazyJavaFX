package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane formBox = new GridPane();
        formBox.setHgap(10);
        formBox.setVgap(10);
        formBox.setAlignment(Pos.CENTER);

        // email row
        formBox.add(new Label("Email "), 0, 0);
        formBox.add(new TextField(), 1, 0);

        // password row
        formBox.add(new Label("Password "), 0, 1);
        formBox.add(new TextField(), 1, 1);

        // controls row
        formBox.add(new Button("Submit"), 0, 2);
        formBox.add(new Button("Clear"), 1, 2);

        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(formBox, 300, 275));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
