package scene;
import elementosRoleros.CambiaMapas;
import elementosRoleros.InterpreteComandos;
import escenarios.Mapa;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import personajes.Enemigo;
import personajes.Personaje;
import scene.parts.CharacterZone;
import scene.parts.CommandsZone;
import scene.parts.DescriptionZone;
import scene.parts.SceneZone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Escenario {
    private Personaje personaje;
    private Mapa mapa1;
    private Mapa mapa2;
    private Mapa mapa3;
    private Mapa mapaActual;
    private TextField textField;
    private Label resultadoComando;
    private Scene scene;
    private SceneZone sceneZone;
    private DescriptionZone descriptionZone;
    private CharacterZone characterZone;
    private CommandsZone commandsZone;

    public Escenario(Personaje personaje){
        this.personaje = personaje;
        mapa1 = new Mapa("1", this.personaje);
        mapa2 = new Mapa("2", this.personaje);
        mapa3 = new Mapa("3", this.personaje);
        mapa1.conectar(mapa2);
        mapa2.conectar(mapa3);
        List<Enemigo> nuevosEnemigos = new ArrayList<>(Arrays.asList(
                new Enemigo("ZOMBIE"),
                new Enemigo("ZOMBIE"),
                new Enemigo("ZOMBIE"),
                new Enemigo("ZOMBIE")
        ));
        mapa1.setEnemigo(nuevosEnemigos);
        this.sceneZone = new SceneZone();
        this.descriptionZone = new DescriptionZone();
        this.characterZone = new CharacterZone();
        this.commandsZone = new CommandsZone();
        this.mapaActual = mapa1;
    }

    public Scene principalSceneCreation(Stage primaryStage) {
        descriptionZone.descriptionStackPane(this.mapaActual);
        StackPane commands = commandsZone.generateCommandsZone();
        Button enviarComando = (Button) commands.lookup("#sendCommand");
        TextField comando = (TextField) commands.lookup("#command") ;
        comando.setOnAction(event -> {enviarComando.fire();});
        enviarComando.setOnAction(event -> {
            InterpreteComandos ic = new InterpreteComandos();
            this.mapaActual = ic.manejarInstrucciones(comando.getText(), this.mapaActual);
            if(comando.getText().toUpperCase().contains("ATACAR")){
                Platform.runLater(() -> this.sceneZone.actualizarZonaEscena(this.mapaActual));
                if(!this.mapaActual.getEnemigos().isEmpty()){
                    CambiaMapas cm = new CambiaMapas();
                    this.mapaActual = cm.recibirAtaque(this.mapaActual);
                    //TODO: Hacer la muerte del personaje si su vida es menor a cero
                    if(this.mapaActual.getPersonaje().getVida()<1){
                        GameOverScene gos = new GameOverScene();

                        primaryStage.setScene(gos.gameOverScene());
                        //primaryStage.setMaximized(true);
                        primaryStage.show();
                    }
                }
            }
            if(comando.getText().toUpperCase().contains("IR")){
                this.sceneZone.actualizarZonaEscena(this.mapaActual);
                this.descriptionZone.updateDescriptionZone(this.mapaActual);
            }
            commandsZone.updateResult(this.mapaActual.getMensaje());

        });
        VBox root = new VBox(this.characterZone.generateCharacterZone(this.mapaActual), this.sceneZone.generateSceneStackPane(this.mapaActual), this.descriptionZone.descriptionStackPane(this.mapaActual), commands);
        root.setSpacing(10);
        this.scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        return this.scene;
    }
}