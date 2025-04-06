package scene.parts;

import escenarios.Map;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.InputStream;

public class CharacterZone {

    private ImageView characterButton;
    private Label characterDataLabel;

    public CharacterZone(){
        this.characterButton = new ImageView();
        this.characterDataLabel = new Label();
    }
    public StackPane generateCharacterZone(Map map){
        Rectangle rectangle = new Rectangle(640, 40);
        rectangle.setStrokeWidth(2);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        this.characterDataLabel.setText(map.getCharacter().personajeFormateado());
        this.characterDataLabel.setAlignment(Pos.CENTER);
        this.characterDataLabel.setStyle("-fx-font-size: 18px;");
        this.characterButton = this.getCharacterButton(map);
        HBox characterHBox = new HBox(10,this.characterButton,this.characterDataLabel);
        characterHBox.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 5;");
        characterHBox.setAlignment(Pos.CENTER);
        return new StackPane(characterHBox);
    }

    private ImageView getCharacterButton(Map map) {
        String rutaImagen = "/images/" + map.getCharacter().getClase() + "_" +
                map.getCharacter().getRaza() + "_" + map.getCharacter().getSexo() + ".jpg";
        return cargarImagen(rutaImagen, 200, 117);
    }

    public void updateCharacterZone(Map map){
        this.characterDataLabel.setText(map.getCharacter().personajeFormateado());
    }

    private ImageView cargarImagen(String ruta, double width, double height) {
        InputStream stream = getClass().getResourceAsStream(ruta);
        if (stream == null) {
            stream = getClass().getResourceAsStream("/images/generica.jpg");
        }
        ImageView imageView = new ImageView(new Image(stream));
        configurarImageView(imageView, width, height);
        return imageView;
    }

    private void configurarImageView(ImageView imageView, double width, double height) {
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(true);
    }
}