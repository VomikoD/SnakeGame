package snake.model.gameObjects;

import snake.model.Position;
import snake.model.exception.UnknownPositionException;
import snake.model.level.Level;
import snake.special.Settings;
import snake.special.Special;

import java.util.ArrayList;
import java.util.List;

import java.awt.*;

public class Apple extends GameObject {
    private static Object object = new Object();

    public Apple(Position pos) {
        super(pos);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Settings.APPLE_COLOR);

        graphics.fillOval(pos().getX() * Settings.CELL_SIZE,
                          pos().getY() * Settings.CELL_SIZE,
                            Settings.CELL_SIZE, Settings.CELL_SIZE);
    }

    public static Apple getNewApple(Level level) throws UnknownPositionException, InterruptedException {
        List<GameObject> gameObjects = level.getGameObjects().getAll();
        Position[][] positions = new Position[level.getHeight()][level.getWidth()];
        List<Position> freePositions = new ArrayList<>();
        try {
            for (int xx = 0; xx < level.getWidth(); xx++) {
                for (int yy = 0; yy < level.getHeight(); yy++) {
                    positions[yy][xx] = Position.position(xx, yy);
                    boolean b = true;

                    for(int i = 0; i < gameObjects.size(); i++) {
                        if (positions[yy][xx].equals(gameObjects.get(i).pos())) {
                            b = false;
                        }
                    }

                    if(b) {
                        freePositions.add(positions[yy][xx]);
                    }
                }
            }
        } catch (Exception ignored) {}

        return new Apple(freePositions.get(Special.getRandom().nextInt(freePositions.size())));
    }
}
