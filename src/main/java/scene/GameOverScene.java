package scene;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.scene.control.Label;

import java.util.Objects;

public class GameOverScene {
    public Scene gameOverScene(){
        Label gameOverLabel = new Label("Game Over");
        Image gameOverImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/gameOverImage1.jpg")));
        ImageView gameOverImageView = new ImageView(gameOverImage);
        gameOverImageView.setFitHeight(600);
        gameOverImageView.setFitWidth(480);
        gameOverImageView.setPreserveRatio(true);
        VBox gameOverVbox = new VBox(gameOverLabel,gameOverImageView);
        StackPane gameOverStackPane = new StackPane(gameOverVbox);
        gameOverStackPane.setPrefSize(Screen.getPrimary().getBounds().getWidth(),
                Screen.getPrimary().getBounds().getHeight());
        return new Scene(gameOverStackPane);
    }
}
