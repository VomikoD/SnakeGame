package snake.controller;

import snake.controller.event.EventListener;
import snake.model.Direction;
import snake.model.Model;
import snake.model.exception.UnknownPositionException;
import snake.model.gameObjects.GameObject;
import snake.special.Settings;
import snake.view.View;

import java.util.List;

public class Controller implements EventListener {
    private Model model;
    private View view;

    public Controller() {
    }

    private void init() {
        model = new Model();
        view = new View(this);
        model.init();
        view.init();
    }

    public void start() {
        init();
        view.start();

        while(true) {
            model.step();
            view.update();
            try {
                Thread.sleep(Settings.DELAY_TIME_MILLIS);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    }

    @Override
    public void setSnakeDirection(Direction direction) {
        model.getSnake().setDirection(direction);
    }

    @Override
    public Direction getSnakeDirection() {
        return model.getSnake().getDirection();
    }

    public List<GameObject> getGameObjects() throws UnknownPositionException {
        return model.getGameObjects();
    }

    @Override
    public void restart() {
        if(!model.getGamePosition().equalsIgnoreCase("playing")) {
            model.init();
        }
    }
}
