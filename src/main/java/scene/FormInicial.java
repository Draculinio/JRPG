package scene;

import elementosRoleros.Dados;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import personajes.Personaje;

import java.util.*;

public class FormInicial {

    private TextField nombreTextField;
    private ComboBox<String> claseComboBox;
    private ComboBox<String> razaComboBox;
    private ComboBox<String> sexoComboBox;

    public Scene pantallaFormulario(Stage primaryStage) {
        StackPane formulario = formulario();
        StackPane logotipo = logotipo();
        VBox root = new VBox(logotipo, formulario);
        root.setSpacing(10);
        root.setAlignment(Pos.CENTER);

        Button iniciarJuegoButton = (Button) formulario.lookup("#iniciarJuego");
        iniciarJuegoButton.setOnAction(_ -> {
            Escenario escenario = new Escenario(new Personaje(nombreTextField.getText(), claseComboBox.getValue(), razaComboBox.getValue(), sexoComboBox.getValue()));
            primaryStage.setScene(escenario.crearEscenario(primaryStage));
        });
        Scene scene = new Scene(root, 800, 600);
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
        Label nombreLabel = new Label("Nombre:");
        nombreLabel.setStyle("-fx-font-size: 20px;");
        this.nombreTextField = new TextField(nombresDefault.get(Dados.dado(0, nombresDefault.size() - 1)));
        this.nombreTextField.setStyle("-fx-font-size: 16px;");
        Label claseLabel = new Label("Clase:");
        claseLabel.setStyle("-fx-font-size: 20px;");
        ObservableList<String> clases = FXCollections.observableArrayList("Guerrero", "Mago", "Cazador");
        this.claseComboBox = new ComboBox<>(clases);
        this.claseComboBox.setStyle("-fx-font-size: 16px;");
        this.claseComboBox.setValue("Guerrero");
        Label razaLabel = new Label("Raza:");
        razaLabel.setStyle("-fx-font-size: 20px;");
        ObservableList<String> razas = FXCollections.observableArrayList("Humano", "Elfo", "Hobbit", "Semielfo");
        this.razaComboBox = new ComboBox<>(razas);
        this.razaComboBox.setStyle("-fx-font-size: 16px;");
        this.razaComboBox.setValue("Humano");
        Label sexoLabel = new Label("Sexo:");
        sexoLabel.setStyle("-fx-font-size: 20px;");
        ObservableList<String> sexos = FXCollections.observableArrayList("Hombre", "Mujer");
        this.sexoComboBox = new ComboBox<>(sexos);
        this.sexoComboBox.setStyle("-fx-font-size: 16px;");
        this.sexoComboBox.setValue("Hombre");

        Button iniciarJuego = new Button("Iniciar Juego");
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
        Image personajeImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/guerrero_humano.jpg")));
        ImageView ivPersonaje = new ImageView(personajeImage);
        ivPersonaje.setFitWidth(200);
        ivPersonaje.setFitHeight(117);
        ivPersonaje.setPreserveRatio(true);
        return ivPersonaje;
    }
}


