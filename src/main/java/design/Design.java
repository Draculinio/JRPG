package design;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class Design {
    public void showToast(String message, Pane rootPane) {
        Label toast = new Label(message);
        toast.setStyle("-fx-background-color: rgba(0,0,0,0.7); -fx-text-fill: white; -fx-padding: 10px; -fx-background-radius: 10;");
        toast.setFont(new Font("Arial", 28));
        toast.setOpacity(0);

        // Colocación: centro inferior
        toast.setLayoutX((rootPane.getWidth() - 200) / 2); // o ajustalo dinámicamente
        toast.setLayoutY(rootPane.getHeight() - 80);

        rootPane.getChildren().add(toast);

        // Animación de fade in y fade out
        FadeTransition fadeIn = new FadeTransition(Duration.millis(300), toast);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.setOnFinished(e -> {
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(ev -> {
                FadeTransition fadeOut = new FadeTransition(Duration.millis(300), toast);
                fadeOut.setFromValue(1);
                fadeOut.setToValue(0);
                fadeOut.setOnFinished(event -> rootPane.getChildren().remove(toast));
                fadeOut.play();
            });
            pause.play();
        });
        fadeIn.play();
    }

}
