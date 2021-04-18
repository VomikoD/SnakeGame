package snake;

import javafx.application.Application                 ;
import javafx.embed.swing.SwingNode;
import javafx.scene.*                                 ;
import javafx.scene.control.Alert                     ;
import javafx.scene.control.Button                    ;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextField                 ;
import javafx.scene.input.KeyCode                     ;
import javafx.scene.paint.Color                       ;
import javafx.scene.text.Font                         ;
import javafx.scene.text.FontWeight                   ;
import javafx.scene.text.Text                         ;
import javafx.stage.Stage                             ;
import snake.model.*                                  ;
import snake.model.exception.UnknownPositionException ;
import snake.model.gameObjects.GameObject             ;
import javax.swing.*                                  ;
import static snake.special.LanguageProperties.*      ;
import static snake.special.Settings.*                ;
import java.awt.*                                     ;
import java.io.*                                      ;
import java.util.*                                    ;
import java.util.List                                 ;

public class Main extends Application {
    private volatile Scene scene;
    private volatile Stage primaryStage;
    private volatile Model model;
    private volatile boolean isPlayingGame = false;
    private Thread thread = new Thread(() -> {
        while (true) {
            model.step();

            List<GameObject> gameObjects = null;
            try {
                gameObjects = model.getGameObjects();
            } catch (UnknownPositionException e) {
                e.printStackTrace();
            }
            List<Node> nodeList = new ArrayList<>();

            for (int i = 0; i < Objects.requireNonNull(gameObjects).size(); i++) {
                nodeList.addAll(gameObjects.get(i).getDrawElements());
            }

            Node[] nodes = new Node[nodeList.size()];

            for (int i = 0; i < nodeList.size(); i++) {
                nodes[i] = nodeList.get(i);
            }

            draw(nodes);

            try {
                Thread.sleep(DELAY_TIME_MILLIS);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        }
    });

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        model = new Model();
        primaryStage = stage;
        startScene(true);

        primaryStage.setMaximized(true);
        primaryStage.setScene(scene);
        primaryStage.setTitle(GAME_TITLE);
        primaryStage.setOnCloseRequest(event -> System.exit(0));
        primaryStage.show();
    }

    private void startScene(boolean isInit) {
        Button buttonForStart = new Button(TEXT_FOR_BUTTON_TO_START_GAME);
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        buttonForStart.setLayoutX(dimension.getWidth() / 5);
        buttonForStart.setFont(Font.font("Arial", FontWeight.BOLD, 200));
        buttonForStart.setOnMouseClicked(event -> startGame());
        Button buttonForSettings = new Button(TEXT_FOR_BUTTON_TO_OPEN_SETTINGS);
        buttonForSettings.setLayoutX(dimension.getWidth() / 6);
        buttonForSettings.setLayoutY(400);
        buttonForSettings.setFont(Font.font("Arial", FontWeight.BOLD, 200));
        buttonForSettings.setOnMouseClicked(event -> startSettingsScene());

        if (isInit) {
            scene = new Scene(new Group(buttonForStart, buttonForSettings));
            scene.setFill(Color.ORANGE);
            Camera camera = new PerspectiveCamera();
            scene.setCamera(camera);
            startKeyHandle();
            return;
        }

        draw(buttonForStart, buttonForSettings);
    }

    private void startSettingsScene() {
        Button buttonGraphics = new Button(TEXT_FOR_BUTTON_TO_OPEN_GRAPHICS_SETTING);
        buttonGraphics.setLayoutX(700);
        buttonGraphics.setFont(Font.font("Arial", FontWeight.BOLD, 200));
        buttonGraphics.setOnMouseClicked(event -> startGraphicsSetting());

        Button buttonToComplete = new Button(TEXT_FOR_BUTTON_TO_COMPLETE);
        buttonToComplete.setLayoutX(750);
        buttonToComplete.setLayoutY(900);
        buttonToComplete.setFont(Font.font("Arial", FontWeight.BOLD, 200));

        buttonToComplete.setOnMouseClicked(event -> startScene(false));

        draw(buttonGraphics, buttonToComplete);
    }

    private void startGraphicsSetting() {
        Text text = new Text(0, 200, COLOR_COMPONENT_TEXT);
        text.setFont(Font.font("Arial", FontWeight.BOLD, 100));
        text.setLayoutY(270);

        TextField textField = new TextField();
        textField.setFont(Font.font("Arial", FontWeight.BOLD, 60));
        textField.setPrefColumnCount(20);
        textField.setLayoutY(400);
        textField.setLayoutX(1100);

        Button buttonToSetColor = new Button(BUTTON_FOR_SET_COLOR_TEXT);
        buttonToSetColor.setFont(Font.font("Arial", FontWeight.BOLD, 200));
        buttonToSetColor.setOnMouseClicked(event -> setColor(textField.getText()));

        Button buttonToComplete = new Button(TEXT_FOR_BUTTON_TO_COMPLETE);
        buttonToComplete.setLayoutX(750);
        buttonToComplete.setLayoutY(900);
        buttonToComplete.setFont(Font.font("Arial", FontWeight.BOLD, 200));

        buttonToComplete.setOnMouseClicked(event -> startSettingsScene());

        draw(textField, buttonToSetColor, buttonToComplete, text);
    }

    private void setColor(String colorComponent) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(".\\res\\settings.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!properties.containsKey(colorComponent)) {
            Alert alert = new Alert(Alert.AlertType.ERROR, THIS_COLOR_COMPONENT_DOES_NOT_EXIST_MESSAGE);
            alert.setTitle(ERROR_MESSAGE_TITLE);
            alert.setHeaderText(ERROR_MESSAGE_TITLE);
            alert.show();
            return;
        }
        ColorPicker colorPicker = new ColorPicker(Color.BLUE);

        colorPicker.setPrefWidth(600);
        colorPicker.setPrefHeight(500);
        colorPicker.setOnAction(event -> {
            Color color = colorPicker.getValue();

            int colorInt = (int) (color.getRed() * 255 * 65536 + color.getGreen() * 255 * 256 + color.getBlue() * 255);

            properties.setProperty(colorComponent, String.format("%06X", colorInt));
            try {
                properties.store(new FileOutputStream(".\\res\\settings.properties"), null);
            } catch (IOException e) {
                e.printStackTrace();
            }
            startGraphicsSetting();
        });
        draw(colorPicker);
    }

    private void startGame() {
        scene.setFill(GAME_BACKGROUND);
        isPlayingGame = true;
        model.init();

        thread = new Thread(() -> {
            while (true) {
                model.step();

                List<GameObject> gameObjects = null;
                try {
                    gameObjects = model.getGameObjects();
                } catch (UnknownPositionException e) {
                    e.printStackTrace();
                }
                List<Node> nodeList = new ArrayList<>();

                for (int i = 0; i < Objects.requireNonNull(gameObjects).size(); i++) {
                    nodeList.addAll(gameObjects.get(i).getDrawElements());
                }

                Node[] nodes = new Node[nodeList.size()];

                for (int i = 0; i < nodeList.size(); i++) {
                    nodes[i] = nodeList.get(i);
                    nodes[i].setLayoutX(nodes[i].getLayoutX() + CELL_SIZE / 2);
                    nodes[i].setLayoutY(nodes[i].getLayoutY() + CELL_SIZE / 2);
                }

                draw(nodes);

                try {
                    Thread.sleep(DELAY_TIME_MILLIS);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private void startKeyHandle() {
        scene.setOnKeyPressed((event) -> {
            KeyCode keyCode = event.getCode();

            if (keyCode == KeyCode.LEFT && isPlayingGame) {
                if (!(getDirection() == Direction.RIGHT))
                    setDirection(Direction.LEFT);
            } else if (keyCode == KeyCode.RIGHT && isPlayingGame) {
                if (!(getDirection() == Direction.LEFT))
                    setDirection(Direction.RIGHT);
            } else if (keyCode == KeyCode.UP && isPlayingGame) {
                if (!(getDirection() == Direction.DOWN))
                    setDirection(Direction.UP);
            } else if (keyCode == KeyCode.DOWN && isPlayingGame) {
                if (!(getDirection() == Direction.UP))
                    setDirection(Direction.DOWN);
            } else if (keyCode == KeyCode.SPACE && isPlayingGame) {
                restart();
            } else if (keyCode == KeyCode.ESCAPE && isPlayingGame) {
                System.exit(0);
            }
        });
    }

    private Direction getDirection() {
        return model.getSnake().getDirection();
    }

    private void setDirection(Direction direction) {
        model.getSnake().setDirection(direction);
    }

    private void restart() {
        thread.stop();
        isPlayingGame = false;
        Button buttonToRestartGame = new Button(TEXT_FOR_BUTTON_TO_RESTART_LEVEL);
        if (model.getGamePosition() == GamePosition.GAME_WON) {
            buttonToRestartGame = new Button(TEXT_FOR_BUTTON_TO_GO_TO_NEXT_LEVEL);
        }
        buttonToRestartGame.setFont(Font.font("Arial", FontWeight.BOLD, 200));
        Button buttonToGoToMainMenu = new Button(TEXT_FOR_BUTTON_TO_EXIT_TO_MAIN_MENU);
        buttonToGoToMainMenu.setFont(Font.font("Arial", FontWeight.BOLD, 200));
        buttonToGoToMainMenu.setLayoutX(300);
        buttonToGoToMainMenu.setLayoutY(400);
        buttonToRestartGame.setOnMouseClicked((event) -> startGame());
        buttonToGoToMainMenu.setOnMouseClicked((event) -> startScene(false));

        scene.setFill(Color.ORANGE);

        draw(buttonToGoToMainMenu, buttonToRestartGame);
    }

    private void draw(Node... elements) {
        scene.setRoot(new Group(elements));
    }
}