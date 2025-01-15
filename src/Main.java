import org.jsfml.graphics.*;
import org.jsfml.graphics.Font;
import org.jsfml.system.*;
import java.io.IOException;
import org.jsfml.window.Window;
import org.jsfml.window.event.Event;
import org.jsfml.window.Keyboard;
import org.jsfml.window.VideoMode;
import org.jsfml.graphics.RenderWindow;
import java.util.Optional;
import javax.swing.JOptionPane;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;


public class Main {
    public static void main(String[] args) {
        Game game = new Game();
        KeyboardInput inputmanager = new KeyboardInput();
        Food smallFood = new Food(242, 5, 'o');
        Food bigFood = new Food(4, 10, 'O');
        Map map = new Map(35, 30);

        GameSettings[] settingsArray = new GameSettings[2];
        settingsArray[0] = new GameSettings("Pac-Man 1", 25, -14, 26, Color.YELLOW, Color.BLUE, Color.WHITE, Color.WHITE, Color.RED, new Color(255, 185, 193),
                Color.CYAN, new Color(255, 165, 0), new StringBuilder("OriginalName"));
        settingsArray[1] = new GameSettings("Pac-Man 2", 25, -14, 8, new Color(255, 255, 153), new Color(100, 149, 247), new Color(255, 245, 238),
                new Color(255, 228, 225), new Color(220, 20, 60), new Color(255, 105, 180), new Color(176, 234, 240), new Color(255, 140, 0), new StringBuilder("Original Name"));
        Random random = new Random();
         GameSettings settings = settingsArray[random.nextInt(1)];
        map.createMap();
        Pacman pacman = new Pacman(settings.getPacmanStartX(), settings.getPacmanStartY(), settings.getPacmanStartX(), settings.getPacmanStartY(), 0, 3, 3, 0);

        List<Ghost> ghostArray = new ArrayList<>();
        Blinky blinky = new Blinky(11, 14, 0, 3, 3, settings.getBlinkyColor());
        Pinky pinky = new Pinky(13, 14, 0, 3, 3, settings.getPinkyColor());
        Inky inky = new Inky(15, 14, 0, 3, 3, settings.getInkyColor());
        Clyde clyde = new Clyde(17, 14, 0, 3, 3, settings.getClydeColor());
        ghostArray.add(blinky);
        ghostArray.add(pinky);
        ghostArray.add(inky);
        ghostArray.add(clyde);

        TextureManager textureManager = new TextureManager();

        // Загружаем текстуры с помощью TextureManager
        Optional<Texture> cherryTexturePtr = textureManager.load("cherry", "images/cherry.png");
        Optional<Texture> appleTexturePtr = textureManager.load("apple", "images/apple.png");
        Optional<Texture> pearTexturePtr = textureManager.load("pear", "images/pear.png");
        Optional<Texture> orangeTexturePtr = textureManager.load("orange", "images/orange.png");
        Optional<Texture> watermelonTexturePtr = textureManager.load("watermelon", "images/watermelon.png");

        Sprite cherryShape = new Sprite();
        if (cherryTexturePtr.isPresent()) {
            cherryShape.setTexture(cherryTexturePtr.get());
            cherryShape.setScale(0.1f, 0.1f);
        } else {
            System.err.println("Error loading cherry texture");
        }

        Sprite appleShape = new Sprite();
        if (appleTexturePtr.isPresent()) {
            appleShape.setTexture(appleTexturePtr.get());
            appleShape.setScale(0.02f, 0.02f);
        } else {
            System.err.println("Error loading apple texture");
        }

        Sprite pearShape = new Sprite();
        if (pearTexturePtr.isPresent()) {
            pearShape.setTexture(pearTexturePtr.get());
            pearShape.setScale(0.1f, 0.1f);
        } else {
            System.err.println("Error loading pear texture");
        }

        Sprite orangeShape = new Sprite();
        if (orangeTexturePtr.isPresent()) {
            orangeShape.setTexture(orangeTexturePtr.get());
            orangeShape.setScale(0.1f, 0.1f);
        } else {
            System.err.println("Error loading orange texture");
        }

        Sprite watermelonShape = new Sprite();
        if (watermelonTexturePtr.isPresent()) {
            watermelonShape.setTexture(watermelonTexturePtr.get());
            watermelonShape.setScale(0.03f, 0.03f);
        } else {
            System.err.println("Error loading watermelon texture");
        }


        //массив фруктов
        Fruit[] fruitArray = new Fruit[5];
        fruitArray[0] = new Fruit(20, cherryShape);
        fruitArray[1] = new Fruit(30, appleShape);
        fruitArray[2] = new Fruit(40, pearShape);
        fruitArray[3] =  new Fruit(50, orangeShape);
        fruitArray[4] = new Fruit(60,  watermelonShape);
        int randFruit = 0;

        // Создаем экземпляр FontManager
        FontManager fontManager = new FontManager();
        // Загружаем шрифт с помощью FontManager
        Optional<Font> fontOpt = fontManager.load("default_font", "Unformital Medium.ttf");

        if (fontOpt.isEmpty()) {
            // Если загрузка шрифта не удалась, выводим ошибку и завершаем программу
            System.err.println("Error: Font not loaded");

            // Ждем нажатия клавиши для завершения программы
            try {
                System.in.read();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.exit(1); // Завершаем программу с ошибкой
        }

        Font font = fontOpt.get(); // Получаем шрифт, если загрузка удалась


            Text pointsText = new Text();
            pointsText.setFont(font);
            pointsText.setCharacterSize(40);
            pointsText.setColor(Color.WHITE);
            pointsText.setPosition(2 * settings.getGridSize(), settings.getGridSize());

            Text livesText = new Text();
            livesText.setFont(font);
            livesText.setCharacterSize(40);
            livesText.setColor(Color.WHITE);
            livesText.setPosition(22 * settings.getGridSize(), settings.getGridSize());

            Text resultText = new Text();
            resultText.setFont(font);
            resultText.setCharacterSize(80);
            resultText.setColor(Color.WHITE);
            resultText.setPosition(5 * settings.getGridSize(), 10 * settings.getGridSize());

            Text record = new Text();
            record.setFont(font);
            record.setCharacterSize(40);
            record.setColor(Color.WHITE);
            record.setPosition(11 * settings.getGridSize(),  settings.getGridSize());

            RenderWindow window = new RenderWindow(new VideoMode(settings.getGridSize() * map.getW(), settings.getGridSize() * map.getH()), settings.getWindowTitle());

            //мелкое клонирование
            try {
                GameSettings shallowClone = settings.clone();
                System.out.println("Исходный name: " + settings.getName());
                System.out.println("Клон name: " + shallowClone.getName());

                // Изменим name в клоне
                shallowClone.getName().append(" - Cloned");

                System.out.println("Исходный name после изменения клона: " + settings.getName());
                System.out.println("Клон name после изменения клона: " + shallowClone.getName());
            } catch (CloneNotSupportedException e) {
                System.err.println("Ошибка клонирования: " + e.getMessage());
            }


            //глубокое клонирование
            try {
                GameSettings deepClone = settings.deepClone();
                System.out.println("\nИсходный name: " + settings.getName());
                System.out.println("Клон name: " + deepClone.getName());

                // Изменим name в клоне
                deepClone.getName().append(" - Cloned");

                System.out.println("Исходный name после изменения клона: " + settings.getName());
                System.out.println("Клон name после изменения клона: " + deepClone.getName());
            } catch (CloneNotSupportedException e) {
                System.err.println("Ошибка клонирования: " + e.getMessage());
            }

            while (window.isOpen()) {
                Event event;
                while ((event = window.pollEvent()) != null) { // Получаем событие (если есть)
                    if (event.type == Event.Type.CLOSED) {   // Проверяем тип события
                        window.close();
                    }
                    if (event.type == Event.Type.KEY_PRESSED && event.asKeyEvent().key == Keyboard.Key.RETURN) {
                        game.resetGame(map, smallFood, bigFood, pacman, ghostArray, settings, resultText, fruitArray[0]);
                    }
                }

                if (!fruitArray[0].getIsActive())
                {
                    randFruit = random.nextInt(4);
                }

                window.clear(Color.BLACK);
                fruitArray[randFruit].createFruit(settings, map, window, smallFood);
                map.mazePaint(settings, window, smallFood, bigFood,  fruitArray[randFruit].getSprite());

                ResultWrapper wonOrLostResult = pacman.wonOrLost(smallFood, bigFood, resultText);
                if (wonOrLostResult.getResult() == 1) {

                    for(Ghost ghost : ghostArray){                //работа с массивом объектов
                        ghost.ghostDraw(window,settings);
                    }

                    FloatRect textBounds = resultText.getLocalBounds();
                    Vector2i windowSize = window.getSize();
                    resultText.setPosition((windowSize.x - textBounds.width) / 2f, (windowSize.y - textBounds.height) / 2f - 50);
                    window.draw(resultText);
                    pacman.updateMaxPoints(pacman.getPoints());

                } else {
                    pacman.move(map, smallFood, bigFood, fruitArray[randFruit], inputmanager);
                    blinky.blinkyMove(pacman, map, settings, window);
                    pinky.move(pacman, map, settings, window, smallFood);
                    inky.inkyMove(pacman, map, blinky, settings, window);
                    clyde.clydeMove(pacman, map, settings, window);

                    if (clyde.lose(pacman, blinky, pinky, inky) == 1) {
                        if(pacman.getLives() > 0) {
                            blinky.setAll(11, 14, 0, 3, 3, settings.getBlinkyColor());
                            pinky.setAll(13, 14, 0, 3, 3, settings.getPinkyColor());
                            inky.setAll(15, 14, 0, 3, 3, settings.getInkyColor());
                            clyde.setAll(17, 14, 0, 3, 3, settings.getClydeColor());
                            Tile newTile = new Tile(' ');
                            map.setTile(pacman.getY(), pacman.getX(), newTile);
                            newTile = new Tile('P');
                            map.setTile(settings.getPacmanStartY(), settings.getPacmanStartX(), newTile);
                            pacman.setX(settings.getPacmanStartX());
                            pacman.setY(settings.getPacmanStartY());
                            pacman.setNextX(settings.getPacmanStartX());
                            pacman.setNextY(settings.getPacmanStartY());
                            pacman.setScore(0);
                            pacman.setNextDirection(3);

                        }
                    }
                }

                //обработка строк
                String pointString = String.format("Score %d", pacman.getPoints());
                pointsText.setString(pointString);
                String livesString = String.format("Lives %d", pacman.getLives());
                livesText.setString(livesString);
                record.setString("Record " + pacman.getMaxPoints());
                window.draw(pointsText);
                window.draw(livesText);
                window.draw(record);
                window.display();
            }
        }
}