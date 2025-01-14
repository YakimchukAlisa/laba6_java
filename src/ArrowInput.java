import org.jsfml.window.Keyboard;

// Класс для управления вводом со стрелок
class ArrowInput implements InputManager {
    @Override
    public int getDirection() {
        if (Keyboard.isKeyPressed(Keyboard.Key.UP)) return 0;
        if (Keyboard.isKeyPressed(Keyboard.Key.DOWN)) return 1;
        if (Keyboard.isKeyPressed(Keyboard.Key.LEFT)) return 2;
        if (Keyboard.isKeyPressed(Keyboard.Key.RIGHT)) return 3;
        return -1;
    }
}