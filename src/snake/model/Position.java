package snake.model;

import snake.model.exception.UnknownPositionException;

import java.util.Objects;

public class Position {
    private int x, y;

    public static Position position(int x, int y) throws UnknownPositionException {
//        if(x < 0 || x >= Settings.GAME_WIDTH) {
//            throw new UnknownPositionException();
//        }
//        if(y < 0 || y >= Settings.GAME_HEIGHT) {
//            throw new UnknownPositionException();
//        }
        return new Position(x, y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    private Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Position)) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
