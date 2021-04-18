package snake.model.gameObjects;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import snake.model.Position;
import snake.special.Settings;

import java.util.*;

public class Wall extends GameObject {
    public Wall(Position pos) {
        super(pos);
    }

    @Override
    public List<Node> getDrawElements() {
        Box box = new Box(Settings.CELL_SIZE, Settings.CELL_SIZE, 50);
        box.setTranslateX(pos().getX() * Settings.CELL_SIZE);
        box.setTranslateY(pos().getY() * Settings.CELL_SIZE);
        PhongMaterial material = new PhongMaterial();
//        Color diffuseColor = Settings.COLOR_WALL;
//        diffuseColor = Color.rgb((int) diffuseColor.getRed(), (int) diffuseColor.getGreen(), 255);
        material.setDiffuseColor(Settings.COLOR_WALL);
        material.setSpecularColor(Settings.COLOR_WALL);
        box.setMaterial(material);
        return Collections.singletonList(box);
//        return Collections.singletonList(new Circle(pos().getX() * Settings.CELL_SIZE,
//                                                    pos().getY() * Settings.CELL_SIZE,
//                                                           Settings.CELL_SIZE / 2, Settings.COLOR_WALL));
    }
}
