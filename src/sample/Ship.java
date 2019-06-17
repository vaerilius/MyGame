
package sample;


import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;

public class Ship extends Creature{


    public Ship(int x, int y, Image image) {

        super(new Polygon(200, 167,262, 141,201, 116,178, 137), x, y, image);


    }

}