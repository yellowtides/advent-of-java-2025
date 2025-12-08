package day08;

public class Line implements Comparable<Line> {
    public Point p1;
    public Point p2;

    public Line(Point p1, Point p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public double length() {
        return p1.distance(p2);
    }

    @Override
    public int compareTo(Line otherLine) {
        return Double.compare(this.length(), otherLine.length());
    }

    @Override
    public String toString() {
        return "line from: " + p1.toString() + " to: " + p2.toString();
    }
}
