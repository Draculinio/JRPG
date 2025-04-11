package scene;

import escenarios.Map;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class CharacterInformationZone {

    public void showInformation(Map map, ImageView imageView){
        Stage playerStatusStage = new Stage();
        playerStatusStage.initOwner(imageView.getScene().getWindow());
        playerStatusStage.initModality(Modality.WINDOW_MODAL);
        playerStatusStage.setTitle(map.getCharacter().getNombre());
        Label name = new Label("Name: "+ map.getCharacter().getNombre());
        name.setStyle("--font-color:red;");
        Label charClass = new Label("Class: "+ map.getCharacter().getClase());

        VBox playerStatusVBox = new VBox(name,charClass);
        playerStatusVBox.setPadding(new Insets(10));


        Scene playerStatusScene = new Scene(playerStatusVBox,300,300);
        playerStatusStage.setScene(playerStatusScene);
        playerStatusStage.show();
    }
}
