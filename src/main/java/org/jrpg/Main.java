package org.jrpg;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.Scene;
import javafx.stage.Stage;
import scene.InitialForm;

import java.util.Objects;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends  Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            InitialForm initialForm = new InitialForm();
            Scene scene = initialForm.pantallaFormulario(primaryStage);
            primaryStage.setScene(scene);
            primaryStage.setTitle("JavaQuest");
            Image icon = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/logo1.jpg")));
            primaryStage.getIcons().add(icon);
            primaryStage.show();
        } catch(RuntimeException e) {
            e.printStackTrace();
        }

    }
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        launch(args);
    }
}
