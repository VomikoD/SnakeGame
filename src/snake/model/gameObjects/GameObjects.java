package snake.model.gameObjects;

import javafx.scene.Node;

import java.util.ArrayList;
import java.util.List;

public class GameObjects {
    private List<Apple> apples;
    private List<Wall> walls;
    private Snake snake;

    public GameObjects(List<Wall> walls, List<Apple> apples, Snake snake) {
        this.apples = apples;
        this.walls = walls;
        this.snake = snake;
    }

    public List<Apple> getApples() {
        return apples;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public Snake getSnake() {
        return snake;
    }

    public List<GameObject> getAll() {
        List<GameObject> gameObjects = new ArrayList<>();
        gameObjects.addAll(apples);
        gameObjects.add(snake);
        gameObjects.addAll(walls);

        return gameObjects;
    }

    public static List<Node> getAllNodes(GameObjects gameObjects) {
        List<Node> nodes = new ArrayList<>();
        for(int i = 0; i < gameObjects.getAll().size(); i++) {
            nodes.addAll(gameObjects.getAll().get(i).getDrawElements());
        }

        return nodes;
    }
}
