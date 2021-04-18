package snake.model;

import snake.model.exception.UnknownPositionException;
import snake.model.gameObjects.*;
import snake.model.level.*;
import static snake.special.Settings.*;
import snake.special.LanguageProperties;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Model {
    private GamePosition gamePosition;
    private LevelLoader levelLoader;
    private Level level;
    private GameObjects gameObjects;
    private int currentLevel = 1;

    public Model() {
        levelLoader = new LevelLoader(new File(PATH_TO_LEVELS));
        init();
    }

    public void step() {
        try {
            if(gamePosition == GamePosition.PLAYING) {
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
                            gamePosition = GamePosition.GAME_WON;
                            currentLevel++;
                        }
                    }
                } else {
                    gamePosition = GamePosition.GAME_OVER;
                }
            }
        } catch (UnknownPositionException e) {
            e.printStackTrace();
        }
    }

    public void init() {
        try {
            gamePosition = GamePosition.PLAYING;
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
        if(gamePosition == GamePosition.PLAYING) {
            return gameObjects.getAll();
        }
        if(gamePosition == GamePosition.GAME_WON) {
            return new ArrayList<>(Arrays.asList(new Text(Position.position(7, 10), LanguageProperties.WINNING_TEXT, COLOR_GAME_WIN_TEXT, 300),
                    new Text(Position.position(18, 30), "Please press the space bar to go to the next level!", BOTTOM_LETTERING_COLOR, 100)));

        }
        if(gamePosition == GamePosition.GAME_OVER) {
            return new ArrayList<>(Arrays.asList(new Text(LanguageProperties.LOST_TEXT, COLOR_GAME_OVER_TEXT),
                    new Text(Position.position(16, 30), "Please press the space bar to restart the game!", BOTTOM_LETTERING_COLOR, 100)));
        }

        return Collections.emptyList();
    }

    public GamePosition getGamePosition() {
        return gamePosition;
    }
}
