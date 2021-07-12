package sample;

import javafx.scene.shape.Polygon;

public class SpaceShip extends HeavenlyBody {

    SpaceShip() {
        super(new Polygon(-5,-5,10,0,-5,5), 150,100);
    }

    public void move() {

        double changeX = Math.cos(Math.toRadians(this.getShapeHeavenlyBody().getRotate()));
        double changeY = Math.sin(Math.toRadians(this.getShapeHeavenlyBody().getRotate()));

        this.getShapeHeavenlyBody().setTranslateX(getShapeHeavenlyBody().getTranslateX() + changeX);
        this.getShapeHeavenlyBody().setTranslateY(this.getShapeHeavenlyBody().getTranslateY() + changeY);

        if (this.getShapeHeavenlyBody().getTranslateX() < 0) {
            this.getShapeHeavenlyBody().setTranslateX(1);
        }

        if (this.getShapeHeavenlyBody().getTranslateX() > Main.WIDTH) {
            this.getShapeHeavenlyBody().setTranslateX(299);
        }

        if (this.getShapeHeavenlyBody().getTranslateY() < 0) {
            this.getShapeHeavenlyBody().setTranslateY(1);
        }

        if (this.getShapeHeavenlyBody().getTranslateY() > Main.HEIGHT) {
            this.getShapeHeavenlyBody().setTranslateY(199);
        }
    }
}
