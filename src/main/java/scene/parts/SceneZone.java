package scene.parts;

import escenarios.Mapa;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SceneZone {
    private final List<ImageView> enemyImageViews = new ArrayList<>();
    private final List<Tooltip> enemyTooltip = new ArrayList<>();
    private StackPane sceneStackPane;

    public SceneZone(){
        sceneStackPane = new StackPane();
    }

    public StackPane generateSceneStackPane(Mapa mapa) {
        Label titulo = new Label(mapa.getNombre());
        titulo.setStyle("-fx-font-size: 24px;");

        //ImageView imageView = cargarImagen(mapa.getImagen(), 700, 408);
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/" + mapa.getImagen())));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(700);
        imageView.setFitHeight(408);
        imageView.setPreserveRatio(true);
        VBox zonaEscenaVBox = new VBox(titulo, imageView);
        StackPane imagenMapa = new StackPane(zonaEscenaVBox);

        // Enemigos y personaje
        GridPane enemigos = gridEnemigos(mapa);
        ImageView bp = botonPersonaje(mapa);

        HBox representacion = new HBox(bp, imagenMapa, enemigos);
        representacion.setSpacing(20);
        representacion.setAlignment(Pos.CENTER_RIGHT);
        this.sceneStackPane.getChildren().setAll(representacion);

        return this.sceneStackPane;
    }

    private ImageView botonPersonaje(Mapa mapa) {
        String rutaImagen = "/images/" + mapa.getPersonaje().getClase() + "_" +
                mapa.getPersonaje().getRaza() + "_" + mapa.getPersonaje().getSexo() + ".jpg";
        return cargarImagen(rutaImagen, 200, 117);
    }

    private GridPane gridEnemigos(Mapa mapa) {
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Label labelEnemigos = new Label("Enemigos");
        labelEnemigos.setStyle("-fx-font-size: 24px;");
        gridPane.add(labelEnemigos, 0, 1);

        actualizarEnemyImageViews(mapa, gridPane);
        return gridPane;
    }

    private void actualizarEnemyImageViews(Mapa mapa, GridPane gridPane) {
        enemyImageViews.clear();
        enemyTooltip.clear();
        gridPane.getChildren().clear();

        for (var enemigo : mapa.getEnemigos()) {
            ImageView imageView = cargarImagen("/images/" + enemigo.getNombre() + ".jpg", 300, 175);
            Tooltip tooltip = new Tooltip(enemigo.getNombre());
            Tooltip.install(imageView, tooltip);

            enemyImageViews.add(imageView);
            enemyTooltip.add(tooltip);
        }

        // Coloca las imágenes en una cuadrícula (máximo 2 por fila)
        int col = 0, row = 2;
        for (ImageView e : enemyImageViews) {
            gridPane.add(e, col, row);
            if (++col == 2) {
                col = 0;
                row++;
            }
        }
    }

    private ImageView cargarImagen(String ruta, double width, double height) {
        InputStream stream = getClass().getResourceAsStream(ruta);
        if (stream == null) {
            stream = getClass().getResourceAsStream("/images/generica.jpg");
        }
        ImageView imageView = new ImageView(new Image(stream));
        configurarImageView(imageView, width, height);
        return imageView;
    }

    private void configurarImageView(ImageView imageView, double width, double height) {
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(true);
    }

    public void actualizarZonaEscena(Mapa mapa) {
        HBox representacion = (HBox) this.sceneStackPane.getChildren().get(0);
        StackPane imagenMapa = (StackPane) representacion.getChildren().get(1);
        GridPane gridPaneEnemigos = (GridPane) representacion.getChildren().get(2);

        // Actualiza la imagen del mapa
        ImageView imageView = (ImageView) ((VBox) imagenMapa.getChildren().getFirst()).getChildren().get(1);
        imageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/" + mapa.getImagen()))));

        // Actualiza los enemigos
        actualizarEnemyImageViews(mapa, gridPaneEnemigos);
    }
}
