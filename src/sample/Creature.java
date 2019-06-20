package sample;

import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public abstract class Creature {
    private Polygon creature;
    private Point2D move;



    public Creature(Polygon polygon, int x, int y) {
        this.creature = polygon;


        this.creature.setTranslateX(x);
        this.creature.setTranslateY(y);

        this.move = new Point2D(0, 0);
    }

    public Creature(Polygon polygon, int x, int y, Image image) {
        this.creature = polygon;



        creature.setFill(new ImagePattern(image,0,0,1,1,true));
        this.creature.setTranslateX(x);
        this.creature.setTranslateY(y);

        this.move = new Point2D(0, 0);
    }

    public Polygon getCreature() {
        return creature;
    }


    public void moveLeft() {
        this.creature.setRotate(this.creature.getRotate() - 5);
    }

    public void moveRight() {
        this.creature.setRotate(this.creature.getRotate() + 5);
    }

    public void move() {
        this.creature.setTranslateX(this.creature.getTranslateX() + this.move.getX());
        this.creature.setTranslateY(this.creature.getTranslateY() + this.move.getY());
    }

    public void jump() {
        double X = Math.cos(Math.toRadians(this.creature.getRotate()));
        double Y = Math.sin(Math.toRadians(this.creature.getRotate()));

        X *= 0.05;
        Y *= 0.05;

        this.move = this.move.add(X, Y);
    }
    public boolean collision(Creature creature) {
        Shape collinsionArea = Shape.intersect(this.creature, creature.getCreature());

        return collinsionArea.getBoundsInLocal().getWidth() != -1;
    }
}
