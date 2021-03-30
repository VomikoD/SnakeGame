package snake.model.level;

import snake.model.gameObjects.GameObjects;

public class Level {
    private int height;
    private int width;
    private int levelNumber;
    private GameObjects gameObjects;
    private int goal;
    private boolean isAddRandomApple;

    public Level(int levelNumber, GameObjects gameObjects, int goal, boolean isAddRandomApple, int width, int height) {
        this.levelNumber = levelNumber;
        this.gameObjects = gameObjects;
        this.goal = goal;
        this.isAddRandomApple = isAddRandomApple;
        this.width = width;
        this.height = height;
    }

    public int getLevelNumber() {
        return levelNumber;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public int getGoal() {
        return goal;
    }

    public boolean isAddRandomApple() {
        return isAddRandomApple;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
