package snake.model;

import snake.model.exception.UnknownPositionException;
import snake.model.gameObjects.GameObject;
import snake.special.Settings;

import java.awt.*;

public class Text extends GameObject {
    private String text;
    private Color color;
    private int size;

    public Text(String text, Color color) throws UnknownPositionException {
        super(Position.position((Settings.GAME_WIDTH / 2) - (text.length() / 2 + 10), Settings.GAME_HEIGHT / 2 - 3));
        this.color = color;
        this.text = text;
        size = 300;
    }

    public Text(Position pos, String text, Color color, int size) {
        super(pos);
        this.text = text;
        this.color = color;
        this.size = size;
    }

    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(color);

        graphics.setFont(new Font("Arial", Font.BOLD, size));
        graphics.drawString(text, pos().getX() * Settings.CELL_SIZE, pos().getY() * Settings.CELL_SIZE);
    }
}
