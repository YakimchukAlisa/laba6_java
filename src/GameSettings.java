
import org.jsfml.graphics.Color;

import java.util.Objects;


public class GameSettings implements Cloneable {
    private String windowTitle;
    private Color pacmanColor;
    private Color squareColor;
    private Color smallCircleColor;
    private Color bigCircleColor;
    private Color blinkyColor;
    private Color pinkyColor;
    private Color inkyColor;
    private Color clydeColor;
    private int gridSize;
    private int pacmanStartX;
    private int pacmanStartY;
    private StringBuilder name;

    public GameSettings() {
    }


    public GameSettings(String windowTitle, int gridSize,
                        int pacmanStartX, int pacmanStartY,
                        Color pacmanColor, Color squareColor, Color smallCircleColor,
                        Color bigCircleColor, Color blinkyColor, Color pinkyColor,
                        Color inkyColor, Color clydeColor, StringBuilder name) {
        this.windowTitle = windowTitle;           //использование оператора this
        this.gridSize = gridSize;
        this.pacmanStartX = pacmanStartX;
        this.pacmanStartY = pacmanStartY;
        this.pacmanColor = pacmanColor;
        this.squareColor = squareColor;
        this.smallCircleColor = smallCircleColor;
        this.bigCircleColor = bigCircleColor;
        this.blinkyColor = blinkyColor;
        this.pinkyColor = pinkyColor;
        this.inkyColor = inkyColor;
        this.clydeColor = clydeColor;
        this.name=name;


        try {
            if (gridSize <= 0) {
                throw new IllegalArgumentException("Grid size must be positive.");
            }
            this.gridSize = gridSize;
        } catch (IllegalArgumentException e) {
            System.err.println("Error during GameSettings initialization: " + e.getMessage());
            // Если gridsize неподходящий, то устанавливаем значение по умолчанию
            this.gridSize = 25;
        }

        try {
            if (pacmanStartX < 0 || pacmanStartY < 0) {
                throw new IllegalArgumentException("Pacman start coordinates must be positive.");
            }
            this.pacmanStartX = pacmanStartX;
            this.pacmanStartY = pacmanStartY;
        } catch (IllegalArgumentException e) {
            System.err.println("Error during GameSettings initialization: " + e.getMessage());
            // Если координаты неподходящие, то устанавливаем значения по умолчанию
            this.pacmanStartX = 14;
            this.pacmanStartY = 26;
        }
    }

    //метод для мелкого клонирования
    public GameSettings clone() throws CloneNotSupportedException {
        return (GameSettings) super.clone();
    }

   //метод для глубокого клонирования
    public GameSettings deepClone() throws CloneNotSupportedException {
        GameSettings clone = (GameSettings) super.clone();
        clone.name = new StringBuilder(this.name.toString()); // Глубокая копия StringBuilder
        return clone;
    }


    public String getWindowTitle() {
        return windowTitle;
    }

    public int getGridSize() {
        return gridSize;
    }

    public int getPacmanStartX() {
        return pacmanStartX;
    }

    public int getPacmanStartY() {
        return pacmanStartY;
    }

    public Color getPacmanColor() {
        return pacmanColor;
    }

    public Color getSquareColor() {
        return squareColor;
    }

    public Color getSmallCircleColor() {
        return smallCircleColor;
    }

    public Color getBigCircleColor() {
        return bigCircleColor;
    }

    public Color getBlinkyColor() {
        return blinkyColor;
    }

    public Color getPinkyColor() {
        return pinkyColor;
    }

    public Color getInkyColor() {
        return inkyColor;
    }

    public Color getClydeColor() {
        return clydeColor;
    }

    public void setGridSize(int gridSize) {this.gridSize= gridSize;}

    public void setPacmanColor(Color color) {pacmanColor = color;}

    public StringBuilder getName() {return name;}

    public void setName(StringBuilder name) {this.name = name;}


}
