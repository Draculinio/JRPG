package scene;

import elementosRoleros.Dados;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import personajes.Character;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class InitialForm {
    private Image personajeImage;
    private final ImageView ivPersonaje;
    private TextField nombreTextField;
    private ComboBox<String> claseComboBox;
    private ComboBox<String> razaComboBox;
    private ComboBox<String> sexoComboBox;

    public InitialForm() {
        this.ivPersonaje = new ImageView();
        this.ivPersonaje.setFitWidth(200);
        this.ivPersonaje.setFitHeight(117);
        this.ivPersonaje.setPreserveRatio(true);

    }
    public Scene selectCharacterScene(Stage primaryStage) {
        StackPane form = formulario();
        StackPane logotipo = logotipo();
        VBox root = new VBox(logotipo, form);
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);
        Node node = form.lookup("#iniciarJuego");
        if(node instanceof Button initGameButton){
            initGameButton.setOnAction(_ -> {
                GameScenario gameScenario = new GameScenario(new Character(nombreTextField.getText(), claseComboBox.getValue(), razaComboBox.getValue(), sexoComboBox.getValue()));
                try {
                    primaryStage.setScene(gameScenario.principalSceneCreation(primaryStage));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        node = form.lookup("#raza");
        if(node instanceof ComboBox) {
            @SuppressWarnings("unchecked")
            ComboBox<String> razaCombo = (ComboBox<String>) node;
            razaCombo.setOnAction(_ -> actualizar_player());
        }
        node = form.lookup("#clase");
        if(node instanceof ComboBox) {
            @SuppressWarnings("unchecked")
            ComboBox<String> claseCombo = (ComboBox<String>) node;
            claseCombo.setOnAction(_ -> actualizar_player());
        }
        node = form.lookup("#sexo");
        if(node instanceof ComboBox) {
            @SuppressWarnings("unchecked")
            ComboBox<String> sexoCombo = (ComboBox<String>) node;
            sexoCombo.setOnAction(_ -> actualizar_player());
        }

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/styles/main_styles.css")).toExternalForm());
        primaryStage.setScene(scene);
        return scene;
    }

    private StackPane logotipo(){
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/logo1.jpg")));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(600);
        imageView.setFitHeight(350);
        imageView.setPreserveRatio(true);
        return new StackPane(imageView);
    }

    public StackPane formulario() {
        ImageView player = player();
        GridPane gridFormulario = new GridPane();
        gridFormulario.setAlignment(Pos.CENTER);
        Insets padding = new Insets(20);
        gridFormulario.setPadding(padding);
        gridFormulario.setHgap(10);
        gridFormulario.setVgap(10);
        gridFormulario.setStyle("-fx-border-color: black; " +
                "-fx-border-width: 2px; " +
                "-fx-border-radius: 10px; " +
                "-fx-background-color: white;");

        List<String> nombresDefault = Arrays.asList("Rigoberto", "Camila", "Sofía", "David");
        Label nombreLabel = new Label("Name:");
        nombreLabel.setStyle("-fx-font-size: 20px;");
        this.nombreTextField = new TextField(nombresDefault.get(Dados.dado(0, nombresDefault.size() - 1)));
        this.nombreTextField.setStyle("-fx-font-size: 16px;");
        Label claseLabel = new Label("Class:");
        claseLabel.setStyle("-fx-font-size: 20px;");
        ObservableList<String> clases = FXCollections.observableArrayList("Warrior", "Wizard", "Hunter");
        this.claseComboBox = new ComboBox<>(clases);
        this.claseComboBox.setId("clase");
        this.claseComboBox.setStyle("-fx-font-size: 16px;");
        this.claseComboBox.setValue("Warrior");
        Label razaLabel = new Label("Race:");
        razaLabel.setStyle("-fx-font-size: 20px;");
        ObservableList<String> razas = FXCollections.observableArrayList("Human", "Elf", "Hobbit", "Halfelf");
        this.razaComboBox = new ComboBox<>(razas);
        this.razaComboBox.setId("raza");
        this.razaComboBox.setStyle("-fx-font-size: 16px;");
        this.razaComboBox.setValue("Human");
        Label sexoLabel = new Label("Sex:");
        sexoLabel.setStyle("-fx-font-size: 20px;");
        ObservableList<String> sexos = FXCollections.observableArrayList("Man", "Woman");
        this.sexoComboBox = new ComboBox<>(sexos);
        this.sexoComboBox.setId("sexo");
        this.sexoComboBox.setStyle("-fx-font-size: 16px;");
        this.sexoComboBox.setValue("Man");

        Button iniciarJuego = new Button("Start Game");
        iniciarJuego.setStyle("-fx-font-size: 16px;");
        iniciarJuego.setId("iniciarJuego");

        gridFormulario.add(nombreLabel, 0, 1);
        gridFormulario.add(this.nombreTextField, 1, 1);
        gridFormulario.add(claseLabel, 0, 2);
        gridFormulario.add(this.claseComboBox, 1, 2);
        gridFormulario.add(razaLabel, 0, 3);
        gridFormulario.add(this.razaComboBox, 1, 3);
        gridFormulario.add(sexoLabel, 0, 4);
        gridFormulario.add(this.sexoComboBox, 1, 4);
        gridFormulario.add(iniciarJuego, 1, 5);

        GridPane gridImagen = new GridPane();
        gridImagen.setPadding(new Insets(20));
        StackPane.setMargin(gridImagen, new Insets(0, 0, 80, 0));
        ColumnConstraints colForm = new ColumnConstraints();
        colForm.setPercentWidth(80);
        ColumnConstraints colImagen = new ColumnConstraints();
        colImagen.setPercentWidth(20);
        gridImagen.getColumnConstraints().addAll(colForm, colImagen);

        gridImagen.add(gridFormulario, 0, 0);
        gridImagen.add(player, 1, 0);

        GridPane.setHalignment(gridFormulario, Pos.CENTER.getHpos());
        GridPane.setHalignment(player, Pos.CENTER_RIGHT.getHpos());
        StackPane form = new StackPane(gridImagen);
        StackPane.setAlignment(gridImagen, Pos.CENTER);

        return form;
    }


    private ImageView player(){
        this.personajeImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/warrior_human_man.jpg")));
        ivPersonaje.setImage(this.personajeImage);
        return ivPersonaje;
    }

    private void actualizar_player(){
        //usar clase_raza_sexo.jpg
        String rutaImagen = "/images/" + this.claseComboBox.getValue().toLowerCase() + "_" + this.razaComboBox.getValue().toLowerCase() + "_" + this.sexoComboBox.getValue().toLowerCase() + ".jpg";
        InputStream imagenStream = getClass().getResourceAsStream(rutaImagen);
        if (imagenStream == null) {
            // Si la imagen no existe, cargar una imagen genérica
            imagenStream = getClass().getResourceAsStream("/images/generica.jpg");
        }
        this.personajeImage = new Image(Objects.requireNonNull(imagenStream));
        ivPersonaje.setImage(this.personajeImage);
    }
}
