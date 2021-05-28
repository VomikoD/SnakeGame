package snake.special;

import java.io.*;
import java.util.Properties;

public class InterfaceSettings {
    private final static Properties properties = new Properties();

    static {
        try(InputStream inputStream = new FileInputStream(Settings.PATH_TO_INTERFACE_SETTINGS + "\\interface.properties")) {
            properties.load(inputStream);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static final String PATH_TO_LANG_SETTINGS                       = getProperty("PATH_TO_LANG_SETTINGS");
    public static final int
                        VOMIKOD_LABEL_FONT_SIZE                            = Integer.parseInt(getProperty("VOMIKOD_LABEL_FONT_SIZE")),
                        DISTANCE_FROM_TOP_PANEL_TO_BOTTOM_PANEL            = Integer.parseInt(getProperty("DISTANCE_FROM_TOP_PANEL_TO_BOTTOM_PANEL"));

    private static String getProperty(String property) {
        return properties.getProperty(property);
    }
}
