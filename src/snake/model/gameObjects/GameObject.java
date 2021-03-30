package snake.model.gameObjects;

import snake.model.Position;

import java.awt.*;

public class GameObject {
    private Position pos;

    public GameObject(Position pos) {
        this.pos = pos;
    }

    public Position pos() {
        return pos;
    }

    public void draw(Graphics graphics) {}
}