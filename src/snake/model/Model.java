package snake.model;

import snake.model.exception.UnknownPositionException;
import snake.model.gameObjects.*;
import snake.model.level.*;
import snake.special.Settings;

import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Model {
    private String positionGame;
    private LevelLoader levelLoader;
    private Level level;
    private GameObjects gameObjects;
    private int currentLevel = 1;

    public Model() {
        init();
        levelLoader = new LevelLoader(new File(Settings.PATH_TO_LEVELS));
    }

    public void step() {
        try {
            if(positionGame.equalsIgnoreCase("playing")) {
                if (getSnake().isAlive) {
                    int i = getSnake().move(level);
                    if(i > 0) {
                        gameObjects.getApples().remove(i - 1);
                        if(level.isAddRandomApple()) {
                            try {
                                gameObjects.getApples().add(Apple.getNewApple(level));
                            } catch (UnknownPositionException | InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        if (getSnake().size() >= level.getGoal()) {
                            positionGame = "Game Win";
                            currentLevel++;
                        }
                    }
                } else {
                    positionGame = "Game Over";
                }
            }
        } catch (UnknownPositionException e) {
            e.printStackTrace();
        }
    }

    public void init() {
        try {
            positionGame = "playing";
            level = levelLoader.loadLevel(currentLevel);
            gameObjects = level.getGameObjects();
            currentLevel = level.getLevelNumber();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Snake getSnake() {
        return gameObjects.getSnake();
    }

    public List<GameObject> getGameObjects() throws UnknownPositionException {
        if(positionGame.equalsIgnoreCase("playing")) {
            return gameObjects.getAll();
        }
        if(positionGame.equalsIgnoreCase("Game Win")) {
            return new ArrayList<>(Arrays.asList(new Text("GAME WON!!!", Settings.COLOR_GAME_WIN_TEXT),
                    new Text(Position.position(18, 30), "Please press the space bar to go to the next level!", Settings.BOTTOM_LETTERING_COLOR, 100)));

        }
        if(positionGame.equalsIgnoreCase("Game Over")) {
            return new ArrayList<>(Arrays.asList(new Text("Game Over", Settings.COLOR_GAME_OVER_TEXT),
                    new Text(Position.position(16, 30), "Please press the space bar to restart the game!", Settings.BOTTOM_LETTERING_COLOR, 100)));
        }

        return Collections.emptyList();
    }

    public String getGamePosition() {
        return positionGame;
    }
}
