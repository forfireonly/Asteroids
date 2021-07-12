package sample;

import javafx.scene.shape.Polygon;

import java.util.Random;

public class Asteroid extends HeavenlyBody {

    Asteroid() {
        super(new AsteroidCreator().createAsteroids(), 0, 0);
        this.getAsteroidAngle();
    }

    public void getAsteroidAngle() {

        Random rnd = new Random();
        super.getShapeHeavenlyBody().setRotate(rnd.nextInt(90));
    }

    public void move() {
        double changeX = Math.cos(Math.toRadians(this.getShapeHeavenlyBody().getRotate()));
        double changeY = Math.sin(Math.toRadians(this.getShapeHeavenlyBody().getRotate()));

        this.getShapeHeavenlyBody().setTranslateX(getShapeHeavenlyBody().getTranslateX() + changeX * 0.5);
        this.getShapeHeavenlyBody().setTranslateY(this.getShapeHeavenlyBody().getTranslateY() + changeY * 0.5);

        if (this.getShapeHeavenlyBody().getTranslateX() < 0) {
            this.getShapeHeavenlyBody().setTranslateX(this.getShapeHeavenlyBody().getTranslateX() - Main.WIDTH);
        }

        if (this.getShapeHeavenlyBody().getTranslateX() > Main.WIDTH) {
            this.getShapeHeavenlyBody().setTranslateX(this.getShapeHeavenlyBody().getTranslateX() % Main.WIDTH);
        }

        if (this.getShapeHeavenlyBody().getTranslateY() < 0) {
            this.getShapeHeavenlyBody().setTranslateY(this.getShapeHeavenlyBody().getTranslateY() - Main.HEIGHT);
        }

        if (this.getShapeHeavenlyBody().getTranslateY() > Main.HEIGHT) {
            this.getShapeHeavenlyBody().setTranslateY(this.getShapeHeavenlyBody().getTranslateY() % Main.HEIGHT);
        }

    }
}
