package sample;

import javafx.scene.shape.Polygon;
import java.util.Random;

public class AsteroidCreator {
    public  Polygon createAsteroids() {
        Random rnd = new Random();
        int size = rnd.nextInt(10) + 5 ;
        return new Polygon(size,-size,-size,-size,-size,size, size, size);
    }
}
