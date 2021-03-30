package snake.view;

import snake.controller.Controller;
import snake.model.exception.UnknownPositionException;
import snake.model.gameObjects.GameObject;
import snake.special.Settings;

import javax.swing.*;
import java.util.List;

public class View {
    private JFrame frame;
    private Field field;
    private Controller controller;


    public View(Controller controller) {
        this.controller = controller;
    }

    public void init() {
        field = new Field(this);
        frame = new JFrame(Settings.GAME_TITLE);

        frame.setBounds(0, 0, Settings.GAME_WIDTH * Settings.CELL_SIZE + 15, Settings.GAME_HEIGHT * Settings.CELL_SIZE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(field);
    }

    public List<GameObject> getGameObjects() throws UnknownPositionException {
        return controller.getGameObjects();
    }

    public void update() {
        field.repaint();
    }

    public void show() {
        frame.setVisible(true);
    }

    public void start() {
        show();
    }

    public Controller getController() {
        return controller;
    }
}
