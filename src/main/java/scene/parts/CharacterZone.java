package scene.parts;

import escenarios.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class CharacterZone {

    private ImageView characterButton;
    private Label characterDataLabel;
    private Label mapName;

    public CharacterZone(){
        this.characterButton = new ImageView();
        this.characterDataLabel = new Label();
        this.mapName = new Label();
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
        this.characterButton.setId("imageButton");
        this.mapName.setText("Zone: " + map.getName());
        this.mapName.setStyle("-fx-font-size: 24px;-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 5; -fx-padding: 5;");
        Region spacer = new Region();
        spacer.setMinWidth(0);
        HBox.setHgrow(spacer, Priority.ALWAYS);
        HBox characterHBox = new HBox(10,this.characterButton,this.characterDataLabel, spacer,this.mapName);
        characterHBox.setStyle("-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 5;");
        characterHBox.setPadding(new Insets(0, 20, 0, 20));
        characterHBox.setAlignment(Pos.CENTER);
        return new StackPane(characterHBox);
    }

    private ImageView getCharacterButton(Map map) {
        String imageAddress = "/images/" + map.getCharacter().getClase() + "_" +
                map.getCharacter().getRaza() + "_" + map.getCharacter().getSexo() + ".jpg";
        Image image = new Image(imageAddress);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(200);
        imageView.setFitHeight(117);
        imageView.setPreserveRatio(true);
        return imageView;

        //return cargarImagen(imageAddress, 200, 117);
    }

    public void updateCharacterZone(Map map){
        this.characterDataLabel.setText(map.getCharacter().personajeFormateado());
    }

    public void updateMapZone(Map map){
        this.mapName.setText(map.getName());
    }
}