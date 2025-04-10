package scene;
import design.Design;
import elementosRoleros.MapChanger;
import elementosRoleros.InterpreteComandos;
import escenarios.Map;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import personajes.Enemigo;
import personajes.Character;
import scene.parts.CharacterZone;
import scene.parts.CommandsZone;
import scene.parts.DescriptionZone;
import scene.parts.SceneZone;

import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class GameScenario {
    private Character character;
    private Map map1;
    private Map map2;
    private Map map3;
    private Map map4;
    private Map mapActual;
    private TextField textField;
    private Label resultadoComando;
    private Scene scene;
    private SceneZone sceneZone;
    private DescriptionZone descriptionZone;
    private CharacterZone characterZone;
    private CommandsZone commandsZone;
    private StackPane scenePane;
    private VBox root;
    private StackPane rootContent;
    public GameScenario(Character character){
        this.character = character;
        map1 = new Map("1", this.character);
        map2 = new Map("2", this.character);
        map3 = new Map("3", this.character);
        map4 = new Map("4", this.character);
        map1.conectar(map2);
        map2.conectar(map3);
        map3.conectar(map4);
        List<Enemigo> nuevosEnemigos = new ArrayList<>(Arrays.asList(
                new Enemigo("ZOMBIE"),
                new Enemigo("ZOMBIE"),
                new Enemigo("ZOMBIE"),
                new Enemigo("ZOMBIE")
        ));
        map1.setEnemy(nuevosEnemigos);
        this.sceneZone = new SceneZone();
        this.descriptionZone = new DescriptionZone();
        this.characterZone = new CharacterZone();
        this.commandsZone = new CommandsZone();
        this.mapActual = map1;
    }

    public Scene principalSceneCreation(Stage primaryStage) throws IOException {
        this.scenePane = this.sceneZone.generateSceneStackPane(this.mapActual);
        Button attackCommand = (Button) this.scenePane.lookup("#attackButton");
        this.configureExitButtons();
        attackCommand.setOnAction(actionEvent -> {
            InterpreteComandos ic = new InterpreteComandos();
            this.mapActual = ic.manejarInstrucciones("attack "+this.mapActual.getEnemigos().getFirst().getNombre(), this.mapActual);
            this.manageAttack();
            this.evaluateDeath(primaryStage);
            Platform.runLater(this::configureExitButtons);

        });
        descriptionZone.descriptionStackPane(this.mapActual);
        StackPane commands = commandsZone.generateCommandsZone();
        Button enviarComando = (Button) commands.lookup("#sendCommand");
        TextField comando = (TextField) commands.lookup("#command") ;

        comando.setOnAction(event -> {enviarComando.fire();});
        enviarComando.setOnAction(event -> {
            InterpreteComandos ic = new InterpreteComandos();
            this.mapActual = ic.manejarInstrucciones(comando.getText(), this.mapActual);
            if(comando.getText().toUpperCase().contains("ATACAR") || comando.getText().toUpperCase().contains("ATTACK")){
                this.manageAttack();
                this.evaluateDeath(primaryStage);
            }
            if(comando.getText().toUpperCase().contains("IR") || comando.getText().toUpperCase().contains("GO")){
                this.sceneZone.actualizarZonaEscena(this.mapActual);
                this.descriptionZone.updateDescriptionZone(this.mapActual);
                this.characterZone.updateMapZone(this.mapActual);
                this.configureExitButtons();
            }
            commandsZone.updateResult(this.mapActual.getMensaje());

        });
        this.root = new VBox(this.characterZone.generateCharacterZone(this.mapActual), scenePane, this.descriptionZone.descriptionStackPane(this.mapActual), commands);
        this.root.setSpacing(10);
        this.rootContent = new StackPane(this.root);
        this.scene = new Scene(this.rootContent, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        this.scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/scene.css")).toExternalForm());
        return this.scene;
    }

    private void configureExitButtons(){
        HashMap<String, Button> botones = sceneZone.getAllButtons();
        for (Entry<String, Button> entry : botones.entrySet()) {
            Button exitButton = entry.getValue();
            exitButton.setOnAction(actionEvent -> {
                InterpreteComandos ic = new InterpreteComandos();
                this.mapActual = ic.manejarInstrucciones("ir "+entry.getKey(), this.mapActual);
                this.sceneZone.actualizarZonaEscena(this.mapActual);
                this.descriptionZone.updateDescriptionZone(this.mapActual);
                this.characterZone.updateMapZone(this.mapActual);
                Design design = new Design();
                design.showToast(this.mapActual.getMensaje(),this.rootContent);
                this.configureExitButtons();
            });
        }
    }

    public void manageAttack(){
        Platform.runLater(() -> this.sceneZone.actualizarZonaEscena(this.mapActual));
        if(!this.mapActual.getEnemigos().isEmpty()){
            MapChanger cm = new MapChanger();
            Design design = new Design();
            this.mapActual = cm.recibirAtaque(this.mapActual);
            design.showToast(this.mapActual.getMensaje(),this.rootContent);
            this.mapActual.clearMessage();
            Platform.runLater(() -> this.characterZone.updateCharacterZone(this.mapActual));
        }
    }

    public void evaluateDeath(Stage primaryStage){
        if(this.mapActual.getCharacter().getVida()<1){
            GameOverScene gos = new GameOverScene(primaryStage);
            try {
                primaryStage.setScene(gos.gameOverScene(this.mapActual));
                primaryStage.centerOnScreen();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            primaryStage.show();
        }
    }
}