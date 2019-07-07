
package sample;


import javafx.scene.image.Image;
import javafx.scene.shape.Polygon;

public class Ship extends Creature{


    public Ship(int x, int y, Image image) {

        super(new Polygon(-25, -25, 50, 0, -25, 25), x, y, image);


    }

    @Override
    public void move() {
        super.move();
    }
}