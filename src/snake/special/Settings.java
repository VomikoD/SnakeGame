package snake.special;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Settings {
    private static final Properties properties = new Properties();
    static {
        try {
            properties.load(new FileInputStream(".\\res\\settings.properties"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    // game settings
    public static final int CELL_SIZE = Integer.parseInt(properties.getProperty("CELL_SIZE"));
    public static final int GAME_WIDTH = getDimension().width / CELL_SIZE + 1;
    public static final int GAME_HEIGHT = getDimension().height / CELL_SIZE;
    public static final String GAME_TITLE = properties.getProperty("GAME_TITLE");
    public static final int DELAY_TIME_MILLIS = Integer.parseInt(properties.getProperty("DELAY_TIME_MILLIS"));

    // graphics settings
    public static final Color GAME_BACKGROUND = getColor(properties.getProperty("GAME_BACKGROUND"));
    public static final Color COLOR_GAME_OVER_TEXT = getColor(properties.getProperty("COLOR_GAME_OVER_TEXT"));
    public static final Color COLOR_GAME_WIN_TEXT = getColor(properties.getProperty("COLOR_GAME_WIN_TEXT"));
    public static final Color COLOR_WALL = getColor(properties.getProperty("COLOR_WALL"));
    public static final Color COLOR_MINE = getColor(properties.getProperty("MINE_COLOR"));
    public static final Color APPLE_COLOR = getColor(properties.getProperty("APPLE_COLOR"));
    public static final Color COLOR_DEAD_SNAKE = getColor(properties.getProperty("COLOR_DEAD_SNAKE"));
    public static final Color BOTTOM_LETTERING_COLOR = getColor(properties.getProperty("BOTTOM_LETTERING_COLOR"));

    // levels settings
    public static final int COUNT_LEVELS = Integer.parseInt(properties.getProperty("COUNT_LEVELS"));
    public static final String PATH_TO_LEVELS = properties.getProperty("PATH_TO_LEVELS");

    private static Color getColor(String colorString) {
        int color = (int)Long.parseLong(colorString, 16);
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = (color >> 0) & 0xFF;

        return new Color(r, g, b);
    }

    private static Dimension getDimension() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
}