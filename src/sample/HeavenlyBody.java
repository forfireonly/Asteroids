package sample;


import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;

public abstract class HeavenlyBody {

    private Polygon shapeHeavenlyBody;

    public HeavenlyBody(Polygon polygon, int x, int y) {
        this.shapeHeavenlyBody = polygon;
        this.shapeHeavenlyBody.setTranslateX(x);
        this.shapeHeavenlyBody.setTranslateY(y);
    }

    public Polygon getShapeHeavenlyBody() {
        return shapeHeavenlyBody;
    }

    public void turnLeft() {
        this.shapeHeavenlyBody.setRotate(this.shapeHeavenlyBody.getRotate() - 5);
    }

    public void turnRight() {
        this.shapeHeavenlyBody.setRotate(this.shapeHeavenlyBody.getRotate() + 5);
    }

    public void move() {
        //finding out the direction
        double changeX = Math.cos(Math.toRadians(this.shapeHeavenlyBody.getRotate()));
        double changeY = Math.sin(Math.toRadians(this.shapeHeavenlyBody.getRotate()));

        shapeHeavenlyBody.setTranslateX(shapeHeavenlyBody.getTranslateX() + changeX);
        shapeHeavenlyBody.setTranslateY(shapeHeavenlyBody.getTranslateY() + changeY);
    }

    public Boolean collide(Polygon other) {
        Shape collisionArea = Shape.intersect(this.shapeHeavenlyBody, other);
        return collisionArea.getBoundsInLocal().getWidth() != -1;
    }
}
