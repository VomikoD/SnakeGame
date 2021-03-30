package snake.model.gameObjects;

import snake.model.Position;
import snake.special.Settings;

import java.awt.*;

public class Mine extends GameObject {
    public Mine(Position pos) {
        super(pos);
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(Settings.COLOR_MINE);

        graphics.fillOval(pos().getX() * Settings.CELL_SIZE,
                          pos().getY() * Settings.CELL_SIZE,
                            Settings.CELL_SIZE, Settings.CELL_SIZE);
    }
}
