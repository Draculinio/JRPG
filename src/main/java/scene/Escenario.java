package scene;

import escenarios.Mapa;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
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
import java.util.List;

public class Escenario {
    private List<Image> enemyImages;
    private List<ImageView> enemyImageViews;

    private Personaje personaje;
    private Mapa mapa1;
    private Mapa mapa2;
    private Mapa mapaActual;
    public Escenario(Personaje personaje){
        this.personaje = personaje;
        Enemigo zombi = new Enemigo("ZOMBIE");
        Enemigo otroZombie = new Enemigo("ZOMBIE");
        mapa1 = new Mapa(1,"Ciudad Esmeralda - Casa", "Esta es la casa humilde del personaje", "interior_casa.jpg");
        mapa2 = new Mapa(2, "Ciudad Esmeralda - Portico", "Estas en la puerta de tu casa", "portico.jpg");
        mapa1.conectar(mapa2);
        mapa1.setEnemigo(zombi);
        List<Enemigo> nuevosEnemigos = new ArrayList<>();
        nuevosEnemigos.add(new Enemigo("ZOMBIE"));
        nuevosEnemigos.add(new Enemigo("ZOMBIE"));

        mapa1.setEnemigo(nuevosEnemigos);
        mapa1.setEnemigo(otroZombie);
        this.mapaActual = mapa1;
    }

    public Scene crearEscenario(Stage primaryStage) {
        StackPane escena = zonaEscena();
        StackPane descripcion = zonaDescripcion();
        StackPane comandos = zonaComandos();
        StackPane personaje = zonaPersonaje();
        VBox root = new VBox(personaje, escena, descripcion, comandos);
        root.setSpacing(10);
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        //primaryStage.setFullScreen(true);
        primaryStage.setMaximized(true);
        return scene;
    }

    private StackPane zonaEscena(){
        Label titulo = new Label(mapaActual.getNombre());
        titulo.setStyle("-fx-font-size: 24px;");

        Image image = new Image(getClass().getResourceAsStream("/images/"+mapaActual.getImagen()));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(700);
        imageView.setFitHeight(408);
        imageView.setPreserveRatio(true);
        VBox zonaEscenaVBox = new VBox(titulo, imageView);
        StackPane imagenMapa = new StackPane(zonaEscenaVBox);

        //ENEMIGOS
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
        gridPane.add(enemyImageViews.get(0),0,2);
        gridPane.add(enemyImageViews.get(1),1,2);
        gridPane.add(enemyImageViews.get(2),0,3);
        gridPane.add(enemyImageViews.get(3),1,3);
        HBox representacion = new HBox(imagenMapa, gridPane);
        representacion.setAlignment(Pos.CENTER_RIGHT);

        return new StackPane(representacion);
    }

    private StackPane zonaDescripcion(){
        Rectangle rectangle = new Rectangle(200, 100); // Ancho y alto del rectángulo
        rectangle.setFill(Color.WHITE); // Color de fondo del rectángulo
        rectangle.setStroke(Color.BLACK); // Color del borde del rectángulo
        rectangle.setStrokeWidth(1); // Ancho del borde
        TextArea textArea = new TextArea();
        textArea.setEditable(false); // Hace que el cuadro de texto no sea editable
        textArea.setText(this.mapaActual.getDescripcion()); // Establece el texto inicial
        textArea.setWrapText(true); // Permite que el texto se ajuste al ancho del cuadro de texto
        return new StackPane(rectangle,textArea);
    }

    private StackPane zonaComandos() {
        Rectangle rectangle = new Rectangle(640, 40);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(2);
        Label label = new Label();
        label.setText("Comando:");
        TextField textField = new TextField();
        Button button = new Button("Enviar");
        HBox hbox = new HBox(label, textField, button);
        hbox.setAlignment(Pos.CENTER);
        hbox.setSpacing(10);

        return new StackPane(rectangle, hbox);
    }

    private StackPane zonaPersonaje(){
        Rectangle rectangle = new Rectangle(640, 40);
        rectangle.setStrokeWidth(2);
        rectangle.setFill(Color.WHITE);
        rectangle.setStroke(Color.BLACK);
        Label label = new Label();
        label.setText(personaje.personajeFormateado());
        return new StackPane(rectangle,label);
    }

}
