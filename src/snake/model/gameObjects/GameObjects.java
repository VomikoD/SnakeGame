package snake.model.gameObjects;

import java.util.ArrayList;
import java.util.List;

public class GameObjects {
    private List<Apple> apples;
    private List<Wall> walls;
    private List<Mine> mines;
    private Snake snake;

    public GameObjects(List<Wall> walls, List<Apple> apples, List<Mine> mines, Snake snake) {
        this.apples = apples;
        this.walls = walls;
        this.snake = snake;
        this.mines = mines;
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

        gameObjects.addAll(walls);
        gameObjects.addAll(apples);
        gameObjects.addAll(mines);
        gameObjects.add(snake);

        return gameObjects;
    }

    public List<Mine> getMines() {
        return mines;
    }
}
