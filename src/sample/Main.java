

package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.*;
import java.util.*;

public class Main extends Application {


    private Image image;
    public static int WIDTH = 800;
    public static int HEIGHT = 600;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setWidth(WIDTH);
        primaryStage.setHeight(HEIGHT);

        Pane pane = new Pane();
        pane.setPrefSize(primaryStage.getWidth(), primaryStage.getHeight());
        pane.setStyle("-fx-background-color: #0084ff; -fx-border-color: #ff1400;");
        Image image = new Image("file:assets/pics/ship.png");


        Ship ship = new Ship( WIDTH/ 2, HEIGHT /2  , image);


        List<Asteroid> asteroids = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            Asteroid asteroid = new Asteroid(random.nextInt(WIDTH / 2), random.nextInt(HEIGHT/4 ));
            asteroids.add(asteroid);
        }


        pane.getChildren().add(ship.getCreature());
        asteroids.forEach(asteroid -> pane.getChildren().addAll(asteroid.getCreature()));


        Text text = new Text(20, 20, "Points");
        text.setFont(Font.font("Verdana", 15));
        text.setFill(Color.WHITE);

        pane.getChildren().add(text);


        primaryStage.setTitle("Game");
        Scene scene = new Scene(pane);

        Map<KeyCode, Boolean> keyPressed = new HashMap<>();

        scene.setOnKeyPressed(e -> {
            keyPressed.put(e.getCode(), Boolean.TRUE);
        });
        scene.setOnKeyReleased(e -> {
            keyPressed.put(e.getCode(), Boolean.FALSE);
        });



        new AnimationTimer() {

            @Override
            public void handle(long now) {

                if (keyPressed.getOrDefault(KeyCode.LEFT, false)) {

                    ship.moveLeft();
                }
                if (keyPressed.getOrDefault(KeyCode.RIGHT, false)) {
                    ship.moveRight();
                }
                if (keyPressed.getOrDefault(KeyCode.UP, false)) {
                    ship.jump();
                }
                asteroids.forEach(asteroid -> asteroid.move());
                ship.move();

                asteroids.forEach(asteroid -> {
                    if (ship.collision(asteroid)) {
                        stop();
                    }
                });

            }
        }.start();

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
