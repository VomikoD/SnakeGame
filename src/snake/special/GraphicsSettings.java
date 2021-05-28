package snake.special;

import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GraphicsSettings {
    private final static Properties properties = new Properties();

    static {
        try(InputStream inputStream = new FileInputStream(Settings.PATH_TO_GRAPHICS_SETTINGS)) {
            properties.load(inputStream);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

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
            COLOR_HEAD_DEAD_SNAKE    = getColorProperty("COLOR_HEAD_DEAD_SNAKE"),
            MAIN_MENU_COLOR          = getColorProperty("MAIN_MENU_COLOR");

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

}
