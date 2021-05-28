package snake.model.level;

import snake.model.Direction;
import snake.model.Model;
import snake.model.Position;
import snake.model.exception.UnknownPositionException;
import snake.model.gameObjects.*;
import snake.special.Settings;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LevelLoader {
    private File file;

    public LevelLoader(File file) {
        this.file = file;
    }

    public Level loadLevel(int level, Model model) {
        List<Apple> apples = new ArrayList<>();
        List<Wall> walls = new ArrayList<>();
        Snake snake = null;
        int goal = 15;
        boolean isAddRandomApple = false;
        int width = 0;
        int height = 0;

        int loopLevel = level;
        if (level > Settings.COUNT_LEVELS) {
            loopLevel = 1;
        }

        try (Scanner scanner = new Scanner(file)) {
            int readLevel = 0;
            int x;
            int y = 0;
            boolean isLevelMap = false;
            Direction startDirection = null;
            List<GameObject> snakeParts = new ArrayList<>();
            GameObject head = null;

            while(scanner.hasNextLine()) {

                String line = scanner.nextLine();

                if(line.contains("Level: ")) {
                    readLevel = Integer.parseInt(line.split(" ")[1]);
                }
                if(line.contains("StartDirection: ")) {
                    startDirection = Direction.valueOf(line.split(" ")[1]);
                }
                if(line.contains("Goal: ")) {
                    goal = Integer.parseInt(line.split(" ")[1]);
                }

                if(line.contains("Width: ")) {
                    width = Integer.parseInt(line.split(" ")[1]);
                }

                if(line.contains("Height: ")) {
                    height = Integer.parseInt(line.split(" ")[1]);
                }

                if(line.contains("IsAddRandomApple")) {
                    isAddRandomApple = true;
                }

                if (readLevel == loopLevel) {
                    if (line.length() == 0) {
                        boolean isEnd = isLevelMap;

                        isLevelMap = !isLevelMap;

                        if (isEnd && !isLevelMap) {
                            break;
                        } else {
                            continue;
                        }
                    }

                    if(isLevelMap) {
                        char[] chars = line.toCharArray();

                        x = 0;
                        for(char c : chars) {
                            switch (c) {
                                case 'H':
                                    walls.add(new Wall(Position.position(x, y)));
                                    break;
                                case '@':
                                    head = new GameObject(Position.position(x, y));
                                    break;
                                case '*':
                                    snakeParts.add(new GameObject(Position.position(x, y)));
                                    break;
                                case '$':
                                    apples.add(new Apple(Position.position(x, y)));
                                    break;
                            }
                            x++;
                        }
                        y++;
                    }
                }
            }
            snake = new Snake(snakeParts, head, startDirection);
        } catch (IOException | UnknownPositionException e) {
            e.printStackTrace();
        }

        return new Level(loopLevel, new GameObjects(walls, apples, snake), goal, isAddRandomApple, width, height);
    }
}
