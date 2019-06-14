
package sample;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ship {
    private ImageView imageView;
    private Image ship;
    private Point2D move;


    public Ship(int x, int y) {
        this.imageView = new ImageView();
        this.ship = new Image("File:assets/pics/ship.png");
        imageView.setFitWidth(80);
        imageView.setFitHeight(60);
        this.imageView.setImage(ship);
        this.imageView.setTranslateX(x);
        this.imageView.setTranslateY(y);


        this.move = new Point2D(0, 0);
    }

    public ImageView getShip() {
        return imageView;
    }


    public void moveLeft() {
        this.imageView.setRotate(this.imageView.getRotate() - 5);
    }

    public void moveRight() {
        this.imageView.setRotate(this.imageView.getRotate() + 5);
    }

    public void move() {
        this.imageView.setTranslateX(this.imageView.getTranslateX() + this.move.getX());
        this.imageView.setTranslateY(this.imageView.getTranslateY() + this.move.getY());
    }

    public void jump() {
        double X = Math.cos(Math.toRadians(this.imageView.getRotate()));
        double Y = Math.sin(Math.toRadians(this.imageView.getRotate()));

        X *= 0.05;
        Y *= 0.05;

        this.move = this.move.add(X, Y);
    }
}