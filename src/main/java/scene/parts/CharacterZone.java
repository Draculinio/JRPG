package scene.parts;

import escenarios.Mapa;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CharacterZone {

    public StackPane generateCharacterZone(Mapa mapa){
        Rectangle rectangle = new Rectangle(640, 40);
        rectangle.setStrokeWidth(2);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        Label label = new Label();
        label.setText(mapa.getCharacter().personajeFormateado());
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-font-size: 18px;");
        return new StackPane(rectangle,label);
    }
}