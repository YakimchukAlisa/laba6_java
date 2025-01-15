import org.jsfml.graphics.Color;

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

    public GameSettings() {
    }


    public GameSettings(String windowTitle, int gridSize,
                        int pacmanStartX, int pacmanStartY,
                        Color pacmanColor, Color squareColor, Color smallCircleColor,
                        Color bigCircleColor, Color blinkyColor, Color pinkyColor,
                        Color inkyColor, Color clydeColor) {
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

    public void setPacmanColor(Color color) {pacmanColor = color;}

    // Мелкое клонирование (используется метод clone() по умолчанию)
    @Override
    public GameSettings clone() throws CloneNotSupportedException {
        return (GameSettings) super.clone();
    }

    // Глубокое клонирование
    public GameSettings deepClone() {
        try {
            GameSettings clonedSettings = (GameSettings) super.clone();
            // Клонирование объектов Color
            clonedSettings.pacmanColor = new Color(this.pacmanColor.r, this.pacmanColor.g, this.pacmanColor.b);
            clonedSettings.squareColor = new Color(this.squareColor.r, this.squareColor.g, this.squareColor.b);
            clonedSettings.smallCircleColor = new Color(this.smallCircleColor.r, this.smallCircleColor.g, this.smallCircleColor.b);
            clonedSettings.bigCircleColor = new Color(this.bigCircleColor.r, this.bigCircleColor.g, this.bigCircleColor.b);
            clonedSettings.blinkyColor = new Color(this.blinkyColor.r, this.blinkyColor.g, this.blinkyColor.b);
            clonedSettings.pinkyColor = new Color(this.pinkyColor.r, this.pinkyColor.g, this.pinkyColor.b);
            clonedSettings.inkyColor = new Color(this.inkyColor.r, this.inkyColor.g, this.inkyColor.b);
            clonedSettings.clydeColor = new Color(this.clydeColor.r, this.clydeColor.g, this.clydeColor.b);

            return clonedSettings;
        } catch (CloneNotSupportedException e) {
            // Этот Exception не должен возникать, так как мы реализуем Cloneable
            throw new AssertionError("CloneNotSupportedException was unexpectedly thrown", e);
        }
    }
}
