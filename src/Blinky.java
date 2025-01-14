import org.jsfml.graphics.*;
import org.jsfml.system.Vector2f;

public class Blinky extends Ghost {


    public Blinky() {
    }

    public Blinky(int x, int y, int score, int direction, int lastDirection, Color color) {
        super(x, y, score, direction, lastDirection, color);
    }

    // Перегрузка метода без вызова метода базового класса
    public void ghostDraw(Color color, RenderWindow window, GameSettings settings)                             {
        RectangleShape ghostShape = new RectangleShape(new Vector2f(settings.getGridSize()/1.5f, settings.getGridSize()/1.5f));
        ghostShape.setFillColor(color);
        ghostShape.setPosition(getX() * settings.getGridSize() + settings.getGridSize() / 6, getY() * settings.getGridSize() + settings.getGridSize() / 6); //другое положение и размер
        window.draw(ghostShape);
    }


    public void blinkyMove(Pacman pacman, Map map, GameSettings settings, RenderWindow window) {
        move(map, pacman.getX(), pacman.getY());
        ghostDraw(settings.getBlinkyColor(), window, settings);
    }
}