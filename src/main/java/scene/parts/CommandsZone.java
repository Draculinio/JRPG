package scene.parts;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class CommandsZone {
    private TextField textField;
    private Label commandResult;
    public CommandsZone(){
        this.commandResult = new Label();
        this.commandResult.setAlignment(Pos.CENTER);
        this.commandResult.setStyle("-fx-font-size: 24px;");
    }
    public StackPane generateCommandsZone(){
            Label label = new Label();
            label.setText("Comando:");
            this.textField = new TextField();
            this.textField.setId("command");
            Button enviar = new Button("Enviar");
            enviar.setId("sendCommand");
            HBox hbox = new HBox(label, textField, enviar);
            hbox.setAlignment(Pos.CENTER);
            hbox.setSpacing(10);
            HBox resultadoBox = new HBox(this.commandResult);
            resultadoBox.setAlignment(Pos.CENTER);
            VBox vBox = new VBox(hbox,resultadoBox);
            vBox.setSpacing(10);
            return new StackPane(vBox);
    }
    public void updateResult(String resultad) {
        this.commandResult.setText(resultad);
    }
}
