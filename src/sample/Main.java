
package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;

public class Main extends Application {

    private Image image;

    @Override
    public void start(Stage primaryStage) throws Exception {
        int width = 1280;
        int height = 960;
        Pane pane = new Pane();
        pane.setPrefSize(width, height);
        pane.setStyle("-fx-background-color: #0b2651;");
        Image image = new Image("file:assets/pics/ship.png");


        Ship ship = new Ship(width / 2, height / 2, image);


        List<Asteroid> asteroids = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Random random = new Random();
            Asteroid asteroid = new Asteroid(random.nextInt(1000), random.nextInt(400));
            asteroids.add(asteroid);
        }


        pane.getChildren().add(ship.getCreature());
        asteroids.forEach(asteroid -> pane.getChildren().addAll(asteroid.getCreature()));


        Text text = new Text(20, 20, "Game");
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


        new AnimationTimer() { // ihana testi kommentti <333
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
