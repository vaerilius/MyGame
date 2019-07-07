package sample;

import java.util.Random;

public class Asteroid extends Creature{
    private double rotation;
    public Asteroid( int x, int y) {
        super(new AsteroidFactory().createPolygon(), x, y);
        Random random = new Random();

        super.getCreature().setRotate(random.nextInt(180));

        int speed = 1 + random.nextInt(10);
        for (int i = 0; i < speed; i++) {
            jump();
        }
        this.rotation = 2 - random.nextDouble();
    }

    @Override
    public void move() {
        super.move();
        super.getCreature().setRotate(super.getCreature().getRotate() + rotation);
    }

}
