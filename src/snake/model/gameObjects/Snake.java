package snake.model.gameObjects;

import snake.model.Direction;
import snake.model.Position;
import snake.model.exception.UnknownPositionException;
import snake.model.level.Level;
import snake.special.Settings;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake extends GameObject {
    private List<GameObject> snakeParts;
    private Direction direction;
    public boolean isAlive;

    public Snake(List<GameObject> parts, GameObject head, Direction startDirection) throws UnknownPositionException {
        super(null);
        snakeParts = new ArrayList<>();
        snakeParts.add(head);
        snakeParts.addAll(parts);

        direction = startDirection;
        isAlive = true;
    }

    private GameObject createNewHead() {
        int x = snakeParts.get(0).pos().getX(), y = snakeParts.get(0).pos().getY();

        if(direction == Direction.DOWN) {
            y++;
        } else if(direction == Direction.RIGHT) {
            x++;
        } else if(direction == Direction.UP) {
            y--;
        } else if(direction == Direction.LEFT) {
            x--;
        }

        try {
            return new GameObject(Position.position(x, y));
        } catch (UnknownPositionException ignored) {
        }

        return null;
    }

    private void removeTail() {
        snakeParts.remove(snakeParts.size() - 1);
    }

    public int move(Level level) throws UnknownPositionException {
        List<Apple> apples = level.getGameObjects().getApples();
        List<Wall> walls = level.getGameObjects().getWalls();
        List<Mine> mines = level.getGameObjects().getMines();
        GameObject newHead = createNewHead();

        for(int i = 0; i < mines.size(); i++) {
            Mine mine = mines.get(i);

            assert newHead != null;
            if(newHead.pos().equals(mine.pos())) {
                isAlive = false;
                return -1;
            }
        }

        for(int i = 0; i < walls.size(); i++) {
            Wall wall = walls.get(i);

            assert newHead != null;
            if(newHead.pos().equals(wall.pos())) {
                isAlive = false;
                return -1;
            }
        }


        for(int i = 0; i < size(); i++) {
            assert newHead != null;
            if(newHead.pos().equals(snakeParts.get(i).pos())) {
                isAlive = false;
                return -1;
            }
        }

        snakeParts.add(0, newHead);

        assert newHead != null;
        for(int i = 0; i < apples.size(); i++) {
            Apple apple = apples.get(i);

            if(newHead.pos().equals(apple.pos())) {
                isAlive = true;
                return i + 1;
            }
        }
        removeTail();
        return 0;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void draw(Graphics graphics) {
        if(isAlive) {
            graphics.setColor(Color.BLUE);
        } else {
            graphics.setColor(Settings.COLOR_DEAD_SNAKE);
        }
        graphics.fillOval(snakeParts.get(0).pos().getX() * Settings.CELL_SIZE,
                snakeParts.get(0).pos().getY() * Settings.CELL_SIZE,
                Settings.CELL_SIZE, Settings.CELL_SIZE);
        if(isAlive) {
            graphics.setColor(Color.ORANGE);
        } else {
            graphics.setColor(Settings.COLOR_DEAD_SNAKE);
        }
        for(int i = 1; i < snakeParts.size(); i++) {
            graphics.fillOval(snakeParts.get(i).pos().getX() * Settings.CELL_SIZE,
                              snakeParts.get(i).pos().getY() * Settings.CELL_SIZE,
                                Settings.CELL_SIZE, Settings.CELL_SIZE);
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public int size() {
        return snakeParts.size();
    }

    public List<GameObject> getSnakeParts() {
        return snakeParts;
    }
}
