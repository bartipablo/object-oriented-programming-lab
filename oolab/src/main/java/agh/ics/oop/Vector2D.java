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
        return "(%d, %d)".formatted(this.x, this.y);
    }

    public boolean precedes(Vector2D otherVector) {
        return (this.x <= otherVector.x) && (this.y <= otherVector.y);
    }

    public boolean follows(Vector2D otherVector) {
        return (this.x >= otherVector.x) && (this.y >= otherVector.y);
    }

    public Vector2D add(Vector2D otherVector) {
        return new Vector2D(this.x + otherVector.x, this.y + otherVector.y);
    }

    public Vector2D substract(Vector2D otherVector) {
        return new Vector2D(this.x - otherVector.x, this.y - otherVector.y);
    }

    public Vector2D upperRight(Vector2D otherVector) {
        return new Vector2D(max(this.x, otherVector.x), max(this.y, otherVector.y));
    }

    public Vector2D lowerLeft(Vector2D otherVector) {
        return new Vector2D(min(this.x, otherVector.x), min(this.y, otherVector.y));
    }

    public Vector2D opposite() {
        return new Vector2D(-this.x, -this.y);
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vector2D vector2D = (Vector2D) o;
        return this.x == vector2D.x && this.y == vector2D.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.x, this.y);
    }
}
