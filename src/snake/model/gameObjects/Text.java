package snake.model.gameObjects;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import snake.model.Position;
import snake.model.exception.UnknownPositionException;
import snake.special.Settings;

import java.util.*;

public class Text extends GameObject {
    private String text;
    private Color color;
    private int size;

    public Text(String text, Color color) throws UnknownPositionException {
        super(Position.position((Settings.GAME_WIDTH / 3) - (text.length() / 2 + 10), Settings.GAME_HEIGHT / 3 - 3));
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
    public List<Node> getDrawElements() {
        javafx.scene.text.Text textNode =
                new javafx.scene.text.Text(
                                           pos().getX() * Settings.CELL_SIZE,
                                           pos().getY() * Settings.CELL_SIZE,
                                             text);
        textNode.setFill(color);
        textNode.setFont(Font.font("Arial", FontWeight.BOLD, size));
        return Collections.singletonList(textNode);
    }

}
