package sample.de.thm.craps.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class TitleUI implements UI {

    private VBox titleBox;

    private Label labelTitle;

    public TitleUI() {
        titleBox = new VBox();
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(25, 10, 25, 10));
        titleBox.setStyle("-fx-background-color: #b86565");
        labelTitle = new Label("WÜRFELSPIEL");
        labelTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 40));
        titleBox.getChildren().add(labelTitle);
    }

    @Override
    public Pane getPane() {
        return titleBox;
    }
}
