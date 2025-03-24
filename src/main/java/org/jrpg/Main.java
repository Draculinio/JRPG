package org.jrpg;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.stage.Stage;
import scene.FormInicial;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main extends  Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        FormInicial formInicial = new FormInicial();
        Scene scene = formInicial.pantallaFormulario(primaryStage);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaQuest");
        //primaryStage.setMaximized(true);
        primaryStage.show();
    }
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        launch(args);
    }
}
