
package sample;

        import javafx.animation.AnimationTimer;
        import javafx.application.Application;
        import javafx.geometry.Point2D;
        import javafx.scene.Scene;
        import javafx.scene.input.KeyCode;
        import javafx.scene.layout.Pane;
        import javafx.scene.paint.Color;
        import javafx.scene.text.Font;
        import javafx.scene.text.Text;
        import javafx.stage.Stage;

        import java.util.HashMap;
        import java.util.Map;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        int width = 1280;
        int height = 960;

        Ship ship = new Ship(width/ 2, height/ 2);

        Pane pane = new Pane();
        pane.setPrefSize(width, height);
        pane.setStyle("-fx-background-color: #0b2651;");




        pane.getChildren().add(ship.getShip());



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
        Point2D liike = new Point2D(1, 0);


        new AnimationTimer(){

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
                ship.move();
            }
        }.start();

        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
