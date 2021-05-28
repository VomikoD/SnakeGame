package snake.special;

import javafx.scene.paint.Color;

import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Settings {
    private static final Properties properties = new Properties();
    static {
        try {
            properties.load(new FileInputStream(".\\settings\\main.properties"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    // game settings
    public static final int
    CELL_SIZE                = Integer.parseInt(getProperty("CELL_SIZE")),
    GAME_WIDTH               = getDimension().width / CELL_SIZE + 1,
    GAME_HEIGHT              = getDimension().height / CELL_SIZE,
    DELAY_TIME_MILLIS        = Integer.parseInt(getProperty("DELAY_TIME_MILLIS"));
    public static final String GAME_TITLE = getProperty("GAME_TITLE");

    // paths to other settings
    public static final String PATH_TO_INTERFACE_SETTINGS = getProperty("PATH_TO_INTERFACE_SETTINGS");
    public static final String PATH_TO_GRAPHICS_SETTINGS = getProperty("PATH_TO_GRAPHICS_SETTINGS");

    // levels settings
    public static final int COUNT_LEVELS = Integer.parseInt(getProperty("COUNT_LEVELS"));
    public static final String PATH_TO_LEVELS = getProperty("PATH_TO_LEVELS");

    private static String getProperty(String property) {
        return properties.getProperty(property);
    }

    private static Dimension getDimension() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
}