package scene.parts;

import escenarios.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Pair;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class SceneZone {
    private final List<ImageView> enemyImageViews = new ArrayList<>();
    private final List<Tooltip> enemyTooltip = new ArrayList<>();
    private StackPane sceneStackPane;
    private Button attack;
    private final HashMap<String, Button> sceneButtons = new HashMap<>();

    public SceneZone(){
        sceneStackPane = new StackPane();
    }

    public StackPane generateSceneStackPane(Map map) {
        Image image = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/" + map.getImagen())));
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(700);
        imageView.setFitHeight(408);
        imageView.setPreserveRatio(true);
        VBox zonaEscenaVBox = new VBox(imageView);
        StackPane imagenMapa = new StackPane(zonaEscenaVBox);
        StackPane.setAlignment(imagenMapa, Pos.CENTER);
        GridPane actionButtons = new GridPane();
        // Enemigos y personaje
        if(!map.getEnemigos().isEmpty()){
            attack = new Button("ATTACK");
            attack.setId("attackButton");
            attack.getStyleClass().add("action_button");
            actionButtons.add(attack,0,0);
        }
        List<String> map_names = new ArrayList<>();
        for(int i=0;i<map.getConexiones().size();i++){
            map_names.add(map.getConexiones().get(i).getName());
        }
        Pair<GridPane, HashMap<String, Button>> exits = this.generateExitButtons(map_names);
        GridPane enemyGridPane = gridEnemigos(map);
        sceneButtons.putAll(exits.getValue());

        enemyGridPane.setPadding(new Insets(0, 0, 0, 20));
        HBox representation = new HBox(40,actionButtons,imagenMapa, enemyGridPane, exits.getKey());
        representation.setSpacing(20);
        representation.setPadding(new Insets(5,0,0,0));
        representation.setAlignment(Pos.CENTER);

        this.sceneStackPane.getChildren().setAll(representation);
        return this.sceneStackPane;
    }

    private GridPane gridEnemigos(Map map) {
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        updateEnemyImageViews(map, gridPane);
        return gridPane;
    }

    private void updateEnemyImageViews(Map map, GridPane gridPane) {
        enemyImageViews.clear();
        enemyTooltip.clear();
        gridPane.getChildren().removeIf(node -> GridPane.getRowIndex(node) != null && GridPane.getRowIndex(node) >= 2);


        for (var enemigo : map.getEnemigos()) {
            ImageView imageView = cargarImagen("/images/" + enemigo.getNombre() + ".jpg", 300, 175);
            Tooltip tooltip = new Tooltip(enemigo.getNombre()+"\nLife: "+enemigo.getVida());
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

    public void actualizarZonaEscena(Map map) {
        HBox representacion = (HBox) this.sceneStackPane.getChildren().getFirst();
        StackPane imagenMapa = (StackPane) representacion.getChildren().get(1);
        GridPane gridPaneEnemigos = (GridPane) representacion.getChildren().get(2);

        // Actualiza la imagen del mapa
        ImageView imageView = (ImageView) ((VBox) imagenMapa.getChildren().getFirst()).getChildren().getFirst();
        imageView.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/" + map.getImagen()))));

        // Actualiza los enemigos
        updateEnemyImageViews(map, gridPaneEnemigos);

        //Actualizo botones de salida
        List<String> map_names = new ArrayList<>();
        for(int i=0;i<map.getConexiones().size();i++){
            map_names.add(map.getConexiones().get(i).getName());
        }
        Pair<GridPane, HashMap<String, Button>> exits = this.generateExitButtons(map_names);
        sceneButtons.clear();
        sceneButtons.putAll(exits.getValue());
        representacion.getChildren().set(3, exits.getKey());
    }

    public Pair<GridPane, HashMap<String, Button>> generateExitButtons(List<String> exits) {
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(10));

        HashMap<String, Button> botonesPorId = new HashMap<>();

        int col = 0;
        int row = 0;
        for (int i = 0; i < exits.size(); i++) {
            String direccion = exits.get(i);
            String id = direccion.toLowerCase();

            Button boton = new Button(direccion);
            boton.setId(id);
            boton.getStyleClass().add("action_button");
            // Acción del botón
            boton.setOnAction(e -> {
                System.out.println("Saliste hacia: " + direccion);
                // Lógica para cambiar de mapa
            });

            // Agregar al grid y al hashmap
            grid.add(boton, col, row);
            botonesPorId.put(id, boton);

            // Controlar el layout del grid (2 por fila, por ejemplo)
            col++;
            if (col == 2) {
                col = 0;
                row++;
            }
        }

        return new Pair<>(grid, botonesPorId);
    }

    public Button getButtonById(String id) {
        return sceneButtons.get(id);
    }

    public HashMap<String, Button> getAllButtons() {
        return new HashMap<>(sceneButtons);
    }

}
