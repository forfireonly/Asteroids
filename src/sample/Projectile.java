package sample;

import javafx.scene.shape.Polygon;

public class Projectile extends HeavenlyBody {

    public Projectile(int x, int y) {
        super(new Polygon(2, -2, 2, 2, -2, 2, -2, -2), x, y);
    }

    public void move() {
        double changeX = Math.cos(Math.toRadians(this.getShapeHeavenlyBody().getRotate()));
        double changeY = Math.sin(Math.toRadians(this.getShapeHeavenlyBody().getRotate()));

        this.getShapeHeavenlyBody().setTranslateX(getShapeHeavenlyBody().getTranslateX() + changeX * 3);
        this.getShapeHeavenlyBody().setTranslateY(this.getShapeHeavenlyBody().getTranslateY() + changeY * 3);
    }
}
