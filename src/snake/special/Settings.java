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
            properties.load(new FileInputStream(".\\res\\settings.properties"));
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

    // graphics settings
    public static final Color
    GAME_BACKGROUND          = getColorProperty("GAME_BACKGROUND"),
    COLOR_GAME_OVER_TEXT     = getColorProperty("COLOR_GAME_OVER_TEXT"),
    COLOR_GAME_WIN_TEXT      = getColorProperty("COLOR_GAME_WIN_TEXT"),
    COLOR_WALL               = getColorProperty("COLOR_WALL"),
    COLOR_APPLE              = getColorProperty("APPLE_COLOR"),
    BOTTOM_LETTERING_COLOR   = getColorProperty("BOTTOM_LETTERING_COLOR"),
    COLOR_BODY_SNAKE         = getColorProperty("COLOR_BODY_SNAKE"),
    COLOR_HEAD_SNAKE         = getColorProperty("COLOR_HEAD_SNAKE"),
    COLOR_BODY_DEAD_SNAKE    = getColorProperty("COLOR_BODY_DEAD_SNAKE"),
    COLOR_HEAD_DEAD_SNAKE    = getColorProperty("COLOR_HEAD_DEAD_SNAKE");

    // interface settings
    public static final String PATH_TO_INTERFACE = getProperty("PATH_TO_INTERFACE");

    // levels settings
    public static final int COUNT_LEVELS = Integer.parseInt(getProperty("COUNT_LEVELS"));
    public static final String PATH_TO_LEVELS = getProperty("PATH_TO_LEVELS");

    private static String getProperty(String property) {
        return properties.getProperty(property);
    }

    private static Color getColorProperty(String property) {
        return getColor(getProperty(property));
    }

    private static Color getColor(String colorString) {
        int color = (int)Long.parseLong(colorString, 16),
            r     = (color >> 16) & 0xFF,
            g     = (color >> 8) & 0xFF,
            b     = color & 0xFF;

        return Color.rgb(r, g, b);
    }

    private static Dimension getDimension() {
        return Toolkit.getDefaultToolkit().getScreenSize();
    }
}