package snake.view;

import snake.model.Direction;
import snake.model.exception.UnknownPositionException;
import snake.model.gameObjects.GameObject;
import snake.special.Settings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.List;

class Field extends JPanel {
    private View view;

    public Field(View view) {
        this.view = view;
        addKeyListener(new KeyHandler(view));
        setFocusable(true);
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Settings.GAME_BACKGROUND);
        g.fillRect(0, 0, Settings.GAME_WIDTH * Settings.CELL_SIZE, Settings.GAME_HEIGHT * Settings.CELL_SIZE);

        List<GameObject> gameObjects = null;
        try {
            gameObjects = view.getGameObjects();
        } catch (UnknownPositionException e) {
            e.printStackTrace();
        }

        for(int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).draw(g);
        }
    }


    private static class KeyHandler extends KeyAdapter {
        private View view;

        public KeyHandler(View view) {
            this.view = view;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            Direction direction = view.getController().getSnakeDirection();

            if(e.getKeyCode() == KeyEvent.VK_LEFT) {
                if(!(direction == Direction.RIGHT))
                view.getController().setSnakeDirection(Direction.LEFT);
            } else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
                if(!(direction == Direction.LEFT))
                view.getController().setSnakeDirection(Direction.RIGHT);
            } else if(e.getKeyCode() == KeyEvent.VK_UP) {
                if(!(direction == Direction.DOWN))
                view.getController().setSnakeDirection(Direction.UP);
            } else if(e.getKeyCode() == KeyEvent.VK_DOWN) {
                if(!(direction == Direction.UP))
                view.getController().setSnakeDirection(Direction.DOWN);
            } else if(e.getKeyCode() == KeyEvent.VK_SPACE) {
                view.getController().restart();
            } else if(e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                System.exit(0);
            }
        }
    }
}
