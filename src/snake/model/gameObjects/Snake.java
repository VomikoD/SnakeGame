package snake.model.gameObjects;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Sphere;
import snake.model.Direction;
import snake.model.Model;
import snake.model.Position;
import snake.model.exception.UnknownPositionException;
import snake.model.level.Level;
import snake.special.Settings;
import static snake.special.GraphicsSettings.*;

import java.util.ArrayList;
import java.util.List;

public class Snake extends GameObject {
    protected List<GameObject> snakeParts;
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

    public int move(Level level, Model model) throws UnknownPositionException {
        List<Apple> apples = level.getGameObjects().getApples();
        List<Wall> walls = level.getGameObjects().getWalls();
        GameObject newHead = createNewHead();

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
    public List<Node> getDrawElements() {
        List<Node> parts = new ArrayList<>();
        Color color;
        if(isAlive) {
            color = COLOR_HEAD_SNAKE;
        } else {
            color = COLOR_HEAD_DEAD_SNAKE;
        }

        Sphere head = new Sphere(Settings.CELL_SIZE / 2);
        head.setTranslateX(snakeParts.get(0).pos().getX() * Settings.CELL_SIZE);
        head.setTranslateY(snakeParts.get(0).pos().getY() * Settings.CELL_SIZE);
        head.setTranslateZ(10);
        PhongMaterial materialHead = new PhongMaterial();
//            Color diffuseColorBody = color;
//            diffuseColorBody = Color.rgb((int) diffuseColorBody.getRed(), (int) diffuseColorBody.getGreen(), 255);
        materialHead.setDiffuseColor(color);
        materialHead.setSpecularColor(color);
        head.setMaterial(materialHead);
        parts.add(head);
//        Sphere head = new Sphere(Settings.CELL_SIZE / 2);
//        head.setTranslateX(snakeParts.get(0).pos().getX() * Settings.CELL_SIZE);
//        head.setTranslateY(snakeParts.get(0).pos().getY() * Settings.CELL_SIZE);
//        head.setTranslateZ(10);
//        PhongMaterial materialHead = new PhongMaterial();
////        Color diffuseColorHead = color;
////        diffuseColorHead = Color.rgb((int) diffuseColorHead.getRed(), (int) diffuseColorHead.getGreen(), 255);
//        materialHead.setDiffuseColor(color);
//        materialHead.setSpecularColor(color);
//        head.setMaterial(materialHead);
//        parts.add(head);
//        parts.add(new Circle(snakeParts.get(0).pos().getX() * Settings.CELL_SIZE,
//                             snakeParts.get(0).pos().getY() * Settings.CELL_SIZE,
//                                    Settings.CELL_SIZE / 2, color));

        if(isAlive) {
            color = COLOR_BODY_SNAKE;
        } else {
            color = COLOR_BODY_DEAD_SNAKE;
        }
        for(int i = 1; i < snakeParts.size(); i++) {
            Sphere bodyElement = new Sphere(Settings.CELL_SIZE / 2);
            bodyElement.setTranslateX(snakeParts.get(i).pos().getX() * Settings.CELL_SIZE);
            bodyElement.setTranslateY(snakeParts.get(i).pos().getY() * Settings.CELL_SIZE);
            bodyElement.setTranslateZ(10);
            PhongMaterial material = new PhongMaterial();
//            Color diffuseColorBody = color;
//            diffuseColorBody = Color.rgb((int) diffuseColorBody.getRed(), (int) diffuseColorBody.getGreen(), 255);
            material.setDiffuseColor(color);
            material.setSpecularColor(color);
            bodyElement.setMaterial(material);
            parts.add(bodyElement);
//            parts.add(new Circle(snakeParts.get(i).pos().getX() * Settings.CELL_SIZE,
//                                 snakeParts.get(i).pos().getY() * Settings.CELL_SIZE,
//                                        Settings.CELL_SIZE / 2, color));
        }

        return parts;
    }

    public Direction getDirection() {
        return direction;
    }

    public int size() {
        return snakeParts.size();
    }

}
