package day08;

import java.util.Objects;

public record Point(double x, double y, double z) {
    public double distance(Point otherPoint) {
        return Math.sqrt(Math.pow(x - otherPoint.x(), 2)
                + Math.pow(y - otherPoint.y(), 2)
                + Math.pow(z - otherPoint.z(), 2));
    }

    @Override
    public String toString() {
        return "x: " + x + ", y: " + y + ", z: " + z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
