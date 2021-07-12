package sample;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main extends Application {

    public static int WIDTH = 300;
    public static int HEIGHT = 200;

    public static void main(String[] args) {
        launch(Main.class);
    }

    @Override
    public void start(Stage window) {
        Pane gameWindow = new Pane();
        gameWindow.setPrefSize(WIDTH,HEIGHT);

        Text text = new Text(10, 20, "Destroyed asteroids: 0");
        gameWindow.getChildren().add(text);

        Map<KeyCode, Boolean> pressedKeys = new HashMap<>();
        AtomicInteger points = new AtomicInteger();
        SpaceShip ship = new SpaceShip();
        ArrayList<Asteroid> asteroids = new ArrayList<>();
        List<Projectile> projectiles = new ArrayList<>();
        List<Asteroid> collidedAsteroids = new ArrayList<>();
        List<Projectile> collidedProjectiles = new ArrayList<>();

        generateAsteroids(gameWindow, asteroids);

        gameWindow.getChildren().add(ship.getShapeHeavenlyBody());

        Scene scene = new Scene(gameWindow);

        scene.setOnKeyPressed(event -> {
            pressedKeys.put(event.getCode(), Boolean.TRUE);
        });

        scene.setOnKeyReleased(event -> {
            pressedKeys.put(event.getCode(), Boolean.FALSE);
        });

        new AnimationTimer() {

            @Override
            public void handle(long now) {

                if(pressedKeys.getOrDefault(KeyCode.LEFT, false)) {
                    ship.turnLeft();
                }

                if(pressedKeys.getOrDefault(KeyCode.RIGHT, false)) {
                    ship.turnRight();
                }

                if(pressedKeys.getOrDefault(KeyCode.UP, false)) {
                    ship.move();
                }

                if (pressedKeys.getOrDefault(KeyCode.SPACE, false)) {
                    // we shoot
                    Projectile projectile = new Projectile((int) ship.getShapeHeavenlyBody().getTranslateX(), (int) ship.getShapeHeavenlyBody().getTranslateY());
                    projectile.getShapeHeavenlyBody().setRotate(ship.getShapeHeavenlyBody().getRotate());
                    projectiles.add(projectile);

                    gameWindow.getChildren().add(projectile.getShapeHeavenlyBody());
                }

                asteroids.stream().forEach(asteroid -> {asteroid.move();
                    if (asteroid.collide(ship.getShapeHeavenlyBody())) {
                        stop();
                    }
                });
                projectiles.forEach(projectile -> {projectile.move();

                    for (Asteroid asteroid: asteroids) {
                        if (asteroid.collide(projectile.getShapeHeavenlyBody())) {
                            collidedAsteroids.add(asteroid);
                            collidedProjectiles.add(projectile);
                            text.setText("Destroyed asteroids: " + points.addAndGet(1));
                        }
                    }
                });

                collidedProjectiles.forEach(projectile -> gameWindow.getChildren().remove(projectile.getShapeHeavenlyBody()));
                collidedAsteroids.forEach(asteroid -> gameWindow.getChildren().remove(asteroid.getShapeHeavenlyBody()));
                asteroids.removeAll(collidedAsteroids);
                projectiles.removeAll(collidedProjectiles);
                if (asteroids.size() == 0) {
                    generateAsteroids(gameWindow, asteroids);
                }
            }
        }.start();
        window.setScene(scene);
        window.setTitle("ASTEROIDS");
        window.show();
    }

    public void generateAsteroids(Pane gameWindow, ArrayList<Asteroid> asteroids){
        Random rnd = new Random();
        int numberAsteroids = rnd.nextInt(8) + 3;
        for (int i = 0; i < numberAsteroids; i++) {
            Asteroid asteroid = new Asteroid();
            asteroids.add(asteroid);
        }
        asteroids.stream().forEach(asteroid -> gameWindow.getChildren().add(asteroid.getShapeHeavenlyBody()));
    }

}
