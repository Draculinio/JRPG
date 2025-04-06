package scene.parts;

import escenarios.Map;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.StackPane;

public class DescriptionZone {
    private Label desc;
    public DescriptionZone(){
        this.desc = new Label();
        desc.setStyle("-fx-font-size: 18px; -fx-border-color: black; -fx-border-width: 2px; -fx-background-color: lightgray; -fx-padding: 5px;");
        desc.setMaxWidth(Double.MAX_VALUE);
        desc.setWrapText(true);

    }
    public StackPane descriptionStackPane(Map map){
        this.desc.setText(map.getDescripcion());
        ScrollPane descScrollPane = new ScrollPane(desc);
        descScrollPane.setFitToWidth(true);
        descScrollPane.setPrefSize(300,200);
        return new StackPane(descScrollPane);
    }

    public void updateDescriptionZone(Map map){
        this.desc.setText(map.getDescripcion());
    }
}
