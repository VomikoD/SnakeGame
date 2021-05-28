package snake.model.gameObjects;

import javafx.scene.Node;
import javafx.scene.paint.*;
import javafx.scene.shape.Sphere;
import snake.model.Position;
import snake.model.exception.UnknownPositionException;
import snake.model.level.Level;
import snake.special.*;
import static snake.special.GraphicsSettings.*;

import java.util.*;

public class Apple extends GameObject {
    public Apple(Position pos) {
        super(pos);
    }

    @Override
    public List<Node> getDrawElements() {
        Sphere sphere = new Sphere(Settings.CELL_SIZE / 2);
        sphere.setTranslateX(pos().getX() * Settings.CELL_SIZE);
        sphere.setTranslateY(pos().getY() * Settings.CELL_SIZE);
        sphere.setTranslateZ(10);
        PhongMaterial material = new PhongMaterial();
//        Color diffuseColor = Settings.COLOR_APPLE;
//        diffuseColor = Color.rgb((int) diffuseColor.getRed(), (int) diffuseColor.getGreen(), 255);
        material.setDiffuseColor(COLOR_APPLE);
        material.setSpecularColor(COLOR_APPLE);
        sphere.setMaterial(material);
        return Collections.singletonList(sphere);

//        Circle apple = new Circle(pos().getX() * Settings.CELL_SIZE,
//                                  pos().getY() * Settings.CELL_SIZE,
//                                         Settings.CELL_SIZE / 2);

//        apple.setFill(Settings.COLOR_APPLE);
//        return Collections.singletonList(apple);
    }

    public static Apple getNewApple(Level level) throws UnknownPositionException, InterruptedException {
        List<GameObject> gameObjects = level.getGameObjects().getAll();
        Position[][] positions = new Position[level.getHeight()][level.getWidth()];
        List<Position> freePositions = new ArrayList<>();
        try {
            for (int xx = 0; xx < level.getWidth(); xx++) {
                for (int yy = 0; yy < level.getHeight(); yy++) {
                    positions[yy][xx] = Position.position(xx, yy);
                    boolean b = true;

                    for(int i = 0; i < gameObjects.size(); i++) {
                        if (positions[yy][xx].equals(gameObjects.get(i).pos())) {
                            b = false;
                        }
                    }

                    if(b) {
                        freePositions.add(positions[yy][xx]);
                    }
                }
            }
        } catch (Exception ignored) {}

        return new Apple(freePositions.get(Special.getRandom().nextInt(freePositions.size())));
    }
}
