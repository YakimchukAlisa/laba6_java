import java.awt.*;
import java.awt.event.*;
import java.util.HashSet;
import java.util.Set;

// Интерфейс для управления вводом
public interface InputManager {
    int getDirection(); // 0: вверх, 1: вниз, 2: влево, 3: вправо, -1: нет ввода
}


