package snake.model;

import snake.Main;
import snake.model.exception.UnknownPositionException;
import snake.model.gameObjects.*;
import snake.model.level.*;
import static snake.special.Settings.*;
import static snake.special.GraphicsSettings.*;

import snake.special.LanguageSettings;

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
    private Main main;
    private int currentLevel = 1;
    public boolean isSnakeLearn;

    public Model(Main main) {
        levelLoader = new LevelLoader(new File(PATH_TO_LEVELS));
        isSnakeLearn = true;
        this.main = main;
        init();
    }

    public void step() {
        try {
            if(gamePosition == GamePosition.PLAYING) {
                if (getSnake().isAlive) {
                    int i = getSnake().move(level, this);
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
                            isSnakeLearn = false;
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
            level = levelLoader.loadLevel(currentLevel, this);
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
            return new ArrayList<>(Arrays.asList(new Text(Position.position(7, 10), LanguageSettings.WINNING_TEXT, COLOR_GAME_WIN_TEXT, 220),
                    new Text(Position.position(18, 30), "Please press the space bar to go to the next level!", BOTTOM_LETTERING_COLOR, 100)));

        }
        if(gamePosition == GamePosition.GAME_OVER) {
            return new ArrayList<>(Arrays.asList(new Text(Position.position(7, 10), LanguageSettings.LOST_TEXT, COLOR_GAME_OVER_TEXT, 220),
                    new Text(Position.position(16, 30), "Please press the space bar to restart the game!", BOTTOM_LETTERING_COLOR, 100)));
        }

        return Collections.emptyList();
    }

    public GamePosition getGamePosition() {
        return gamePosition;
    }
}
