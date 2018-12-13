package de.thm.cards;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private Label labelOpacityDiamonds;
    private Slider sliderOpacityDiamonds;
    private VBox boxOpacityDiamonds;

    private Label labelOpacityHearts;
    private Slider sliderOpacityHearts;
    private VBox boxOpacityHearts;

    private HBox boxOpacityControls;

    private Image imageDiamondsCard;
    private ImageView imageViewDiamondsCard;
    private Image imageHeartsCard;
    private ImageView imageViewHeartsCard;
    private Pane boxImage;

    private VBox root;

    private int callIndex = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {

        labelOpacityDiamonds = new Label("Opacity Diamonds");
        sliderOpacityDiamonds = new Slider();
        sliderOpacityDiamonds.setValue(100);
        boxOpacityDiamonds = new VBox();
        boxOpacityDiamonds.getChildren().addAll(labelOpacityDiamonds, sliderOpacityDiamonds);

        labelOpacityHearts = new Label("Opacity Hearts");
        sliderOpacityHearts = new Slider();
        sliderOpacityHearts.setValue(100);
        boxOpacityHearts = new VBox();
        boxOpacityHearts.getChildren().addAll(labelOpacityHearts, sliderOpacityHearts);

        boxOpacityControls = new HBox();
        boxOpacityControls.setAlignment(Pos.CENTER);
        boxOpacityControls.setPadding(new Insets(10, 10, 10, 10));
        boxOpacityControls.getChildren().addAll(boxOpacityDiamonds, boxOpacityHearts);

        boxImage = new Pane();
        imageDiamondsCard = new Image(getClass().getResourceAsStream("4_of_diamonds.png"));
        imageViewDiamondsCard = new ImageView(imageDiamondsCard);
        imageHeartsCard = new Image(getClass().getResourceAsStream("4_of_hearts.png"));
        imageViewHeartsCard = new ImageView(imageHeartsCard);
        boxImage.getChildren().addAll(imageViewDiamondsCard, imageViewHeartsCard);

        addOpacityBinding(sliderOpacityDiamonds, imageViewDiamondsCard);
        addOpacityBinding(sliderOpacityHearts, imageViewHeartsCard);

        // Root Box
        root = new VBox();
        root.getChildren().addAll(boxOpacityControls, boxImage);

        // Primary Stage
        primaryStage.setTitle("Kartenspiel");
        primaryStage.setScene(new Scene(root, 500, 778));
        primaryStage.show();

    }

    private void addOpacityBinding(Slider slider, ImageView imageView) {

        // Variante A
        slider.valueProperty().addListener((ov, old_val, new_val) -> {
            imageView.setOpacity(new_val.doubleValue() / 100);
        });

        // Variante B
        /*slider.valueProperty().addListener((ov, old_val, new_val) -> {
            if(!slider.isValueChanging()) {
                imageView.setOpacity(new_val.doubleValue() / 100);
            }
        });
        slider.valueChangingProperty().addListener((ov, old_val, new_val) -> {
            imageView.setOpacity(slider.getValue() / 100);
        });*/
    }
}
