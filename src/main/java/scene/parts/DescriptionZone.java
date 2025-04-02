package scene.parts;

import escenarios.Mapa;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

public class DescriptionZone {
    private Label desc;
    public DescriptionZone(){
        this.desc = new Label();
        desc.setStyle("-fx-font-size: 18px; -fx-border-color: black; -fx-border-width: 2px; -fx-background-color: lightgray; -fx-padding: 5px;");
        desc.setMaxWidth(Double.MAX_VALUE);
    }
    public StackPane descriptionStackPane(Mapa mapa){
        this.desc.setText(mapa.getDescripcion());
        //Label desc = new Label(mapa.getDescripcion());

        return new StackPane(desc);
    }

    public void updateDescriptionZone(Mapa mapa){
        this.desc.setText(mapa.getDescripcion());
    }
}
