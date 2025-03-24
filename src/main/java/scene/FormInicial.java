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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import personajes.Personaje;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FormInicial {
    private Personaje personaje;
    private Escenario escenario;

    private TextField nombreTextField;
    private ComboBox<String> claseComboBox;
    private ComboBox<String> razaComboBox;
    private ComboBox<String> sexoComboBox;

    public Scene pantallaFormulario(Stage primaryStage) {
        StackPane formulario = formulario();
        StackPane logotipo = logotipo();
        VBox root = new VBox(logotipo, formulario);
        root.setSpacing(10);
        Button iniciarJuegoButton = (Button) formulario.lookup("#iniciarJuego");
        iniciarJuegoButton.setOnAction(event -> {
            Escenario escenario = new Escenario(new Personaje(nombreTextField.getText(), claseComboBox.getValue(), razaComboBox.getValue(), sexoComboBox.getValue()));
            primaryStage.setScene(escenario.crearEscenario(primaryStage));
        });
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        //primaryStage.setFullScreen(true);
        return scene;
    }

    public StackPane logotipo(){
        Image image = new Image(getClass().getResourceAsStream("/images/logo1.jpg"));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(600);
        imageView.setFitHeight(350);
        imageView.setPreserveRatio(true);
        return new StackPane(imageView);
    }

    public StackPane formulario(){
        Rectangle rectangle = new Rectangle(400,200);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(1);
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        List<String> nombresDefault = new ArrayList<>();
        nombresDefault.addAll(Arrays.asList("Rigoberto", "Camila", "Sofía", "David"));
        Label nombreLabel = new Label("Nombre:");
        this.nombreTextField = new TextField();
        nombreTextField.setText(nombresDefault.get(Dados.dado(0,nombresDefault.size())));
        Label claseLabel = new Label("Clase:");
        ObservableList<String> clases = FXCollections.observableArrayList("Guerrero", "Mago", "Cazador");
        this.claseComboBox = new ComboBox<>(clases);
        this.claseComboBox.setValue("Guerrero");
        Label razaLabel = new Label("Raza:");
        ObservableList<String> razas = FXCollections.observableArrayList("Humano","Elfo","Hobbit","Semielfo");
        this.razaComboBox = new ComboBox<>(razas);
        this.razaComboBox.setValue("Humano");
        Label sexoLabel = new Label("Sexo:");
        ObservableList<String> sexos = FXCollections.observableArrayList("Hombre", "Mujer");
        this.sexoComboBox = new ComboBox<>(sexos);
        sexoComboBox.setValue("Hombre");
        Button iniciarJuego = new Button("Iniciar Juego");
        iniciarJuego.setId("iniciarJuego");
        gridPane.add(nombreLabel,0,1);
        gridPane.add(this.nombreTextField,1,1);
        gridPane.add(claseLabel,0,2);
        gridPane.add(this.claseComboBox,1,2);
        gridPane.add(razaLabel,0,3);
        gridPane.add(this.razaComboBox,1,3);
        gridPane.add(sexoLabel,0,4);
        gridPane.add(this.sexoComboBox,1,4);
        gridPane.add(iniciarJuego,1,5);

        return new StackPane(rectangle, gridPane);


    }
}


