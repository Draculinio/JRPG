package scene.parts;

import escenarios.Mapa;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CharacterZone {

    private Label characterDataLabel;
    public CharacterZone(){
        this.characterDataLabel = new Label();
    }
    public StackPane generateCharacterZone(Mapa mapa){
        Rectangle rectangle = new Rectangle(640, 40);
        rectangle.setStrokeWidth(2);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        this.characterDataLabel.setText(mapa.getCharacter().personajeFormateado());
        this.characterDataLabel.setAlignment(Pos.CENTER);
        this.characterDataLabel.setStyle("-fx-font-size: 18px;");
        return new StackPane(rectangle,this.characterDataLabel);
    }

    public void updateCharacterZone(Mapa mapa){
        this.characterDataLabel.setText(mapa.getCharacter().personajeFormateado());
    }
}