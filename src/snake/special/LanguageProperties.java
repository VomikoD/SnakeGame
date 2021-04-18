package snake.special;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class LanguageProperties {
    private final static Properties properties = new Properties();

    static {
        File f = new File(Settings.PATH_TO_INTERFACE + "\\text.properties");
        try(InputStream inputStream = new FileInputStream(f)) {
            Reader reader = new BufferedReader(new InputStreamReader(inputStream, Files.readAllLines(Paths.get(new File(Settings.PATH_TO_INTERFACE + "\\charset.txt").toURI())).get(0)));

            properties.load(reader);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static String
                        TEXT_FOR_BUTTON_TO_OPEN_SETTINGS            = getProperty("TEXT_FOR_BUTTON_TO_OPEN_SETTINGS"           ),
                        TEXT_FOR_BUTTON_TO_OPEN_GRAPHICS_SETTING    = getProperty("TEXT_FOR_BUTTON_TO_OPEN_GRAPHICS_SETTING"   ),
                        TEXT_FOR_BUTTON_TO_COMPLETE                 = getProperty("TEXT_FOR_BUTTON_TO_COMPLETE"                ),
                        TEXT_FOR_BUTTON_TO_RESTART_LEVEL            = getProperty("TEXT_FOR_BUTTON_TO_RESTART_LEVEL"           ),
                        TEXT_FOR_BUTTON_TO_EXIT_TO_MAIN_MENU        = getProperty("TEXT_FOR_BUTTON_TO_EXIT_TO_MAIN_MENU"       ),
                        TEXT_FOR_BUTTON_TO_GO_TO_NEXT_LEVEL         = getProperty("TEXT_FOR_BUTTON_TO_GO_TO_NEXT_LEVEL"        ),
                        TEXT_FOR_BUTTON_TO_START_GAME               = getProperty("TEXT_FOR_BUTTON_TO_START_GAME"              ),
                        LOST_TEXT                                   = getProperty("LOST_TEXT"                                  ),
                        WINNING_TEXT                                = getProperty("WINNING_TEXT"                               ),
                        COLOR_CHOOSER_DIALOG_TITLE                  = getProperty("COLOR_CHOOSER_DIALOG_TITLE"                 ),
                        THIS_COLOR_COMPONENT_DOES_NOT_EXIST_MESSAGE = getProperty("THIS_COLOR_COMPONENT_DOES_NOT_EXIST_MESSAGE"),
                        ERROR_MESSAGE_TITLE                         = getProperty("ERROR_MESSAGE_TITLE"                        ),
                        BUTTON_FOR_SET_COLOR_TEXT                   = getProperty("BUTTON_FOR_SET_COLOR_TEXT"                  ),
                        COLOR_COMPONENT_TEXT                        = getProperty("COLOR_COMPONENT_TEXT"                       );

    private static String getProperty(String property) {
        return properties.getProperty(property);
    }
}
