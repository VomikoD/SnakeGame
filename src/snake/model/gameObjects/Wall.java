package snake.model.gameObjects;

import snake.model.Position;
import snake.special.Settings;

import java.awt.*;

public class Wall extends GameObject {
    public Wall(Position pos) {
        super(pos);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Settings.COLOR_WALL);
        graphics.fillRect(pos().getX() * Settings.CELL_SIZE,
                          pos().getY() * Settings.CELL_SIZE,
                            Settings.CELL_SIZE, Settings.CELL_SIZE);


    }
}
