package snake.model.gameObjects;

import javafx.scene.Node;
import snake.model.Position;

import java.util.*;

public class GameObject {
    private Position pos;

    public GameObject(Position pos) {
        this.pos = pos;
    }

    public Position pos() {
        return pos;
    }

    public List<Node> getDrawElements() {
        return Collections.emptyList();
    }
}