package scene;

import escenarios.Map;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class GameOverScene {

    private Stage primaryStage;

    public GameOverScene(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Scene gameOverScene(Map map) throws IOException {
        GridPane finalStatsGrid = new GridPane();
        Label character_name_title = new Label("Name: ");
        Label character_name = new Label(map.getCharacter().getNombre());
        Label character_class_title = new Label("Class: ");
        Label character_class = new Label(map.getCharacter().getClase().toString());
        Label character_level_title = new Label("Level: ");
        Label character_level = new Label(Integer.toString(map.getCharacter().getNivel()));
        Label character_race = new Label(map.getCharacter().getRaza().toString());
        Label character_race_title = new Label("Race: ");
        Label character_sex_title = new Label("Sex: ");
        Label character_sex = new Label(map.getCharacter().getSexo().toString());

        InputStream statsStream = getClass().getResourceAsStream("/fonts/Achafexp.ttf");
        Font statsFont = Font.loadFont(statsStream, 32);
        character_name_title.setFont(statsFont);
        character_name.setFont(statsFont);
        character_class_title.setFont(statsFont);
        character_class.setFont(statsFont);
        character_level_title.setFont(statsFont);
        character_level.setFont(statsFont);
        character_sex_title.setFont(statsFont);
        character_sex.setFont(statsFont);
        character_race.setFont(statsFont);
        character_race_title.setFont(statsFont);
        assert statsStream != null;
        statsStream.close();
        finalStatsGrid.add(character_name_title,0,0);
        finalStatsGrid.add(character_name,1,0);
        finalStatsGrid.add(character_class_title,0,1);
        finalStatsGrid.add(character_class,1,1);
        finalStatsGrid.add(character_race_title,0,2);
        finalStatsGrid.add(character_race,1,2);
        finalStatsGrid.add(character_sex_title,0,3);
        finalStatsGrid.add(character_sex,1,3);
        finalStatsGrid.add(character_level_title,0,4);
        finalStatsGrid.add(character_level,1,4);
        BorderStroke finalSTatsGridBorderStroke = new BorderStroke(
                Color.BLACK,             // Color del borde
                BorderStrokeStyle.SOLID, // Estilo del borde (sólido, dashed, dotted, etc.)
                CornerRadii.EMPTY,      // Radios de las esquinas (EMPTY para esquinas rectas)
                BorderWidths.DEFAULT     // Ancho del borde (DEFAULT es generalmente 1 píxel)
        );
        Border finalStatsGridBorder = new Border(finalSTatsGridBorderStroke);
        finalStatsGrid.setBorder(finalStatsGridBorder);
        VBox statsVBox = new VBox(finalStatsGrid);
        statsVBox.setPrefWidth(300);
        statsVBox.setMaxWidth(300);
        statsVBox.setStyle("-fx-background-color: light gray;");

        statsVBox.setAlignment(Pos.CENTER);
        statsVBox.setPadding(new Insets(0, 0, 0, 20));
        InputStream fontStream = getClass().getResourceAsStream("/fonts/Embossed_Germanica.ttf");
        Font customFont = Font.loadFont(fontStream, 72);
        Label gameOverLabel = new Label("Game Over");
        gameOverLabel.setFont(customFont);
        assert fontStream != null;
        fontStream.close();
        Image gameOverImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/images/gameOverImage1.jpg")));
        ImageView gameOverImageView = new ImageView(gameOverImage);
        gameOverImageView.setFitHeight(600);
        gameOverImageView.setFitWidth(480);
        gameOverImageView.setPreserveRatio(true);
        Button restart = new Button("Start again my friend");
        restart.setOnAction(actionEvent -> {
            InitialForm initialForm = new InitialForm();
            try{
                this.primaryStage.setScene(initialForm.selectCharacterScene(this.primaryStage));
                primaryStage.centerOnScreen();
                this.primaryStage.show();
            } catch(Exception e){
                e.printStackTrace();
            }
        });
        VBox gameOverVbox = new VBox(gameOverLabel,gameOverImageView, restart);
        gameOverVbox.setAlignment(Pos.CENTER);
        gameOverVbox.setSpacing(30);
        StackPane gameOverStackPane = new StackPane(statsVBox, gameOverVbox);
        gameOverStackPane.setAlignment(statsVBox, Pos.CENTER_LEFT);
        gameOverStackPane.setAlignment(gameOverVbox,Pos.CENTER);
        gameOverStackPane.setPrefSize(Screen.getPrimary().getBounds().getWidth(),
                Screen.getPrimary().getBounds().getHeight());
        return new Scene(gameOverStackPane);
    }
}
