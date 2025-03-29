package scene;
import elementosRoleros.InterpreteComandos;
import escenarios.Mapa;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import personajes.Enemigo;
import personajes.Personaje;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Escenario {
    private List<Image> enemyImages;
    private List<ImageView> enemyImageViews;
    private Personaje personaje;
    private Mapa mapa1;
    private Mapa mapa2;
    private Mapa mapa3;
    private Mapa mapaActual;
    private TextField textField;
    private Label resultadoComando;
    private Scene scene;
    private StackPane descripcion;
    private StackPane escena;

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

        this.mapaActual = mapa1;
    }

    public Scene crearEscenario(Stage primaryStage) {
        escena = zonaEscena();
        descripcion = zonaDescripcion();
        StackPane comandos = zonaComandos();
        StackPane personaje = zonaPersonaje();
        Button iniciarJuegoButton = (Button) comandos.lookup("#enviarComando");
        iniciarJuegoButton.setOnAction(event -> {
            InterpreteComandos ic = new InterpreteComandos();
            this.mapaActual = ic.manejarInstrucciones(this.textField.getText(), this.mapaActual);
            actualizarEnemyImageViews();
            if(this.textField.getText().toUpperCase().contains("ATACAR")){
                Platform.runLater(() -> actualizarGridPaneEnemigos(escena));// entender que es Platform.runLater
            }
            if(this.textField.getText().toUpperCase().contains("IR")){
                actualizarZonaEscena();
                actualizarZonaDescripcion();
            }
            this.resultadoComando.setText(this.mapaActual.getMensaje());
        });
        VBox root = new VBox(personaje, escena, descripcion, comandos);
        root.setSpacing(10);
        this.scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        return this.scene;
    }

    private StackPane zonaEscena(){
        Label titulo = new Label(mapaActual.getNombre());
        titulo.setStyle("-fx-font-size: 24px;");

        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/" + mapaActual.getImagen())));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(700);
        imageView.setFitHeight(408);
        imageView.setPreserveRatio(true);
        VBox zonaEscenaVBox = new VBox(titulo, imageView);
        StackPane imagenMapa = new StackPane(zonaEscenaVBox);

        //ENEMIGOS
        GridPane enemigos = gridEnemigos();

        HBox representacion = new HBox(imagenMapa, enemigos);
        representacion.setAlignment(Pos.CENTER_RIGHT);

        return new StackPane(representacion);
    }

    private GridPane gridEnemigos(){

        Label labelEnemigos = new Label("Enemigos");
        labelEnemigos.setStyle("-fx-font-size: 24px;");

        Rectangle rectangleEnemigos = new Rectangle(200,150);
        rectangleEnemigos.setFill(Color.LIGHTGRAY);
        rectangleEnemigos.setStroke(Color.BLACK);
        rectangleEnemigos.setStrokeWidth(2);
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        this.enemyImages = new ArrayList<>();
        this.enemyImageViews = new ArrayList<>();
        for (int i = 0; i < mapaActual.getEnemigos().size(); i++) { //TODO: Despues esto tiene que cambiar porque es variable y se toma del mapa
            enemyImages.add(new Image(getClass().getResourceAsStream("/images/"+mapaActual.getEnemigos().get(i).getNombre()+".jpg")));
        }
        for (Image imageE : enemyImages) {
            enemyImageViews.add(new ImageView(imageE));
        }

        for (ImageView iv : enemyImageViews){
            iv.setFitWidth(300);
            iv.setFitHeight(175);
        }
        gridPane.add(labelEnemigos,0,1);
        int i = 0;
        int i1 = 2;
        for(ImageView e: enemyImageViews){ //Coloca los enemigos (de 1 a 4) en el lugar
            gridPane.add(e,i,i1);
            i++;
            if(i==2){
                i = 0;
                i1++;
            }
        }
        return gridPane;
    }

    private StackPane zonaDescripcion(){
        Label desc = new Label(this.mapaActual.getDescripcion());
        desc.setStyle("-fx-font-size: 18px; -fx-border-color: black; -fx-border-width: 2px; -fx-background-color: lightgray; -fx-padding: 5px;");
        desc.setMaxWidth(Double.MAX_VALUE);
        return new StackPane(desc);
    }

    private StackPane zonaComandos() {
        Label label = new Label();
        label.setText("Comando:");
        this.textField = new TextField();
        Button enviar = new Button("Enviar");
        enviar.setId("enviarComando");
        HBox hbox = new HBox(label, textField, enviar);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);
        this.resultadoComando = new Label();
        this.resultadoComando.setAlignment(Pos.CENTER);
        this.resultadoComando.setStyle("-fx-font-size: 24px;");

        HBox resultadoBox = new HBox(this.resultadoComando);
        resultadoBox.setAlignment(Pos.CENTER);
        VBox vBox = new VBox(hbox,resultadoBox);
        vBox.setSpacing(10);
        return new StackPane(vBox);
    }

    private StackPane zonaPersonaje(){
        Rectangle rectangle = new Rectangle(640, 40);
        rectangle.setStrokeWidth(2);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        Label label = new Label();
        label.setText(personaje.personajeFormateado());
        label.setAlignment(Pos.CENTER);
        label.setStyle("-fx-font-size: 18px;");
        return new StackPane(rectangle,label);
    }


    //Actualizacion de enemigos TODO: Simplificar, es una porqueria todo esto
    private void actualizarEnemyImageViews() {
        this.enemyImageViews.clear();
        for (int i = 0; i < mapaActual.getEnemigos().size(); i++) {
            enemyImages.add(new Image(getClass().getResourceAsStream("/images/"+mapaActual.getEnemigos().get(i).getNombre()+".jpg")));
        }
        for (Image imageE : enemyImages) {
            enemyImageViews.add(new ImageView(imageE));
        }

        for (ImageView iv : enemyImageViews){
            iv.setFitWidth(300);
            iv.setFitHeight(175);
        }
    }


    private void actualizarGridPaneEnemigos(StackPane stackPane) {

        HBox representacion = (HBox) stackPane.getChildren().get(0);
        GridPane gridPaneEnemigos = (GridPane) representacion.getChildren().get(1);
        gridPaneEnemigos.getChildren().clear();

        // Actualiza la lista enemyImageViews
        enemyImageViews.clear();
        enemyImages.clear(); // Limpia la lista de imágenes también

        // Vuelve a crear la lista de ImageView y el GridPane con los enemigos actualizados
        for (int i = 0; i < mapaActual.getEnemigos().size(); i++) {
            Image imageE = new Image(getClass().getResourceAsStream("/images/" + mapaActual.getEnemigos().get(i).getNombre() + ".jpg"));
            enemyImages.add(imageE);
            ImageView imageView = new ImageView(imageE);
            imageView.setFitWidth(300);
            imageView.setFitHeight(175);
            enemyImageViews.add(imageView);
        }
        // Vuelve a agregar los ImageView al GridPane
        Label labelEnemigos = new Label("Enemigos");
        labelEnemigos.setStyle("-fx-font-size: 24px;");
        gridPaneEnemigos.add(labelEnemigos, 0, 1);
        int i = 0;
        int i1 = 2;
        for (ImageView e : enemyImageViews) {
            gridPaneEnemigos.add(e, i, i1);
            i++;
            if (i == 2) {
                i = 0;
                i1++;
            }
        }
    }

    private void actualizarZonaEscena() {
        escena.getChildren().clear();

        Label titulo = new Label(mapaActual.getNombre());
        titulo.setStyle("-fx-font-size: 24px;");

        Image image = new Image(getClass().getResourceAsStream("/images/" + mapaActual.getImagen()));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(700);
        imageView.setFitHeight(408);
        imageView.setPreserveRatio(true);

        VBox zonaEscenaVBox = new VBox(titulo, imageView);
        StackPane imagenMapa = new StackPane(zonaEscenaVBox);

        GridPane enemigos = gridEnemigos();

        HBox representacion = new HBox(imagenMapa, enemigos);
        representacion.setAlignment(Pos.CENTER_RIGHT);

        escena.getChildren().add(representacion);
    }

    private void actualizarZonaDescripcion(){
        descripcion.getChildren().clear();
        Label desc = new Label(this.mapaActual.getDescripcion());
        desc.setStyle("-fx-font-size: 18px; -fx-border-color: black; -fx-border-width: 2px; -fx-background-color: lightgray; -fx-padding: 5px;");
        desc.setMaxWidth(Double.MAX_VALUE);
        descripcion.getChildren().add(desc);
    }
}
