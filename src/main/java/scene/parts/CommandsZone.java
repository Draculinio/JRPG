package scene.parts;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.io.IOException;
import java.io.InputStream;

public class CommandsZone {
    private TextField textField;
    private Label commandResult;
    public CommandsZone(){
        this.commandResult = new Label();
        this.commandResult.setAlignment(Pos.CENTER);
        this.commandResult.setStyle("-fx-font-size: 24px;");
    }
    public StackPane generateCommandsZone() throws IOException {
            Label label = new Label();
            label.setText("Command:");
            InputStream commandStream = getClass().getResourceAsStream("/fonts/Shadowed_Germanica.ttf");
            Font commandFont = Font.loadFont(commandStream, 24);
            label.setFont(commandFont);
            this.textField = new TextField();
            this.textField.setId("command");
            this.textField.setFont(commandFont);
            Button enviar = new Button("Send");
            enviar.setId("sendCommand");
            enviar.setFont(commandFont);
            assert commandStream != null;
            commandStream.close();
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
