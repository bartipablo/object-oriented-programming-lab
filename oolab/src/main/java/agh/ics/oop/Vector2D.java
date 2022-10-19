package agh.ics.oop;

import java.util.Objects;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Vector2D {
    public final int x;
    public final int y;

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public boolean precedes(Vector2D otherVector) {
        return (x <= otherVector.x) && (y <= otherVector.y);
    }

    public boolean follows(Vector2D otherVector) {
        return (x >= otherVector.x) && (y >= otherVector.y);
    }

    public Vector2D add(Vector2D otherVector) {
        return new Vector2D(x + otherVector.x, y + otherVector.y);
    }

    public Vector2D substract(Vector2D otherVector) {
        return new Vector2D(x - otherVector.x, y - otherVector.y);
    }

    public Vector2D upperRight(Vector2D otherVector) {
        return new Vector2D(max(x, otherVector.x), max(y, otherVector.y));
    }

    public Vector2D lowerLeft(Vector2D otherVector) {
        return new Vector2D(min(x, otherVector.x), min(y, otherVector.y));
    }

    public Vector2D opposite() {
        return new Vector2D(-x, -y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2D vector2D = (Vector2D) o;
        return x == vector2D.x && y == vector2D.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
