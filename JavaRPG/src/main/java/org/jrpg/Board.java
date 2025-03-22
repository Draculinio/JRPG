package org.jrpg;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Board extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Crear un Label para la interfaz gráfica
        Label label = new Label("¡Bienvenido a JavaFX!");

        // Crear un contenedor (layout) y añadir el label
        StackPane root = new StackPane();
        root.getChildren().add(label);

        // Crear la escena y asignarla al stage
        Scene scene = new Scene(root, 400, 200);
        primaryStage.setTitle("Mi Juego JavaFX");
        primaryStage.setScene(scene);

        // Mostrar la ventana
        primaryStage.show();
}
