package snake.controller.event;

import snake.model.Direction;

public interface EventListener {
    public void setSnakeDirection(Direction direction);
    public Direction getSnakeDirection();
    public void restart();
}
