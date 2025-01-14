import org.jsfml.window.Keyboard;

// Класс для управления вводом с клавиатуры (W, A, S, D)
class KeyboardInput implements InputManager {
    @Override
    public int getDirection() {
        if (Keyboard.isKeyPressed(Keyboard.Key.W)) return 0;  // Вверх (W)
        if (Keyboard.isKeyPressed(Keyboard.Key.S)) return 1;  // Вниз (S)
        if (Keyboard.isKeyPressed(Keyboard.Key.A)) return 2;  // Влево (A)
        if (Keyboard.isKeyPressed(Keyboard.Key.D)) return 3;  // Вправо (D)
        return -1; // Нет ввода
    }
}