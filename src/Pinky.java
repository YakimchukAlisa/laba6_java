import org.jsfml.graphics.Color;
import org.jsfml.graphics.RenderWindow;
import static java.lang.Double.POSITIVE_INFINITY;

public class Pinky extends Ghost {

    public Pinky() {
    }

    public Pinky(int x, int y, int score, int direction, int lastDirection, Color color) {
        super(x, y, score, direction, lastDirection, color);
    }

    void move(Pacman pacman, Map map, GameSettings settings, RenderWindow window, Food food) {
        int a = pacman.getX(), b = pacman.getY();
        switch (pacman.getNextDirection())
        {
            case 0:
                b = b - 4;
                break;
            case 1:
                b = b + 4;
                break;
            case 2:
                a = a - 4;
                break;
            case 3:
                a = a + 4;
                break;
        }
        if (food.getTotalFoodCount() < 50) //если в лабиринте осталось меньше 50 точек
        {
            float distanceUp, distanceDown, distanceLeft, distanceRight;
            double minDistance = POSITIVE_INFINITY;
            int change = 0;

            distanceUp = distance(a, b, x, y - 1);
            distanceDown = distance(a, b, x, y + 1);
            if (y == 17 && x == 1)
                distanceLeft = distance(a, b, map.getW() - 1, y);
            else distanceLeft = distance(a, b, x - 1, y);
            if (y == 17 && x == map.getW() - 1)
                distanceRight = distance(a, b, 0, y);
            else distanceRight = distance(a, b, x + 1, y);

            if (distanceRight <= minDistance && map.getTile(y, x + 1).getIsPassable() && getLastDirection() != 2) {
                minDistance = distanceRight;
                direction = 3;
            }
            if (distanceUp <= minDistance && map.getTile(y - 1, x).getIsPassable() && lastDirection != 1 && !(y == 17 && x == 0 || y == 17 && x == map.getW() - 1)) {
                minDistance = distanceUp;
                direction = 0;
            }
            if (distanceLeft <= minDistance && map.getTile(y, x - 1).getIsPassable() && lastDirection != 3) {
                minDistance = distanceLeft;
                direction = 2;
            }
            if (distanceDown <= minDistance && map.getTile(y + 1, x).getIsPassable() && lastDirection != 0 && !(y == 17 && x == 0 || y == 17 && x == map.getW() - 1)) {
                minDistance = distanceDown;
                direction = 1;
            }

            score++;
            if (score >= 100)                        //то  призрак ускоряется
            {
                change = 1;
                // Двигаемся в выбранном направлении
                switch (direction) {
                    case 0: //Движение вверх
                        y--;
                        break;
                    case 1: //Движение вниз
                        y++;
                        break;
                    case 2: //Движение влево
                        if (y == 17 && x == 1)
                            x = map.getW() - 2;
                        else
                            x--;
                        break;
                    case 3: //Движение вправо
                        if (y == 17 && x == map.getW() - 2)
                            x = 1;
                        else
                            x++;
                        break;
                    default:
                        break;
                }
                score = 0;
            }
            if (lastDirection != direction && change==1)
                lastDirection = direction;
        }
        else super.move(map, a, b);
        super.ghostDraw(window, settings);
    }
}