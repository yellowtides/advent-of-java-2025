package day08;

import java.util.*;
import java.util.stream.IntStream;

public class Space {
    public List<Point> points;
    private final PriorityQueue<Line> candidateLines;
    private final HashMap<Point, Set<Point>> connectionGroupOf;

    public Space(List<Point> points) {
        this.points = points;
        this.connectionGroupOf = new HashMap<>();
        this.candidateLines = new PriorityQueue<>();
        points.forEach(point -> {
            HashSet<Point> pointHashSet = new HashSet<>();
            pointHashSet.add(point);
            connectionGroupOf.put(point, pointHashSet);
        });
        IntStream.range(0, points.size()).forEach(p1Idx ->
            IntStream.range(p1Idx+1, points.size()).forEach(p2Idx -> {
                Point p1 = points.get(p1Idx);
                Point p2 = points.get(p2Idx);
                candidateLines.add(new Line(p1, p2));
            })
        );
    }

    public Line getNextConnection() {
        return candidateLines.poll();
    }

    public void connect(Point p1, Point p2) {
        Set<Point> p1Connections = connectionGroupOf.get(p1);
        p1Connections.addAll(connectionGroupOf.get(p2));
        p1Connections.forEach(p -> connectionGroupOf.put(p, p1Connections));
    }

    public boolean isConnected(Point p1, Point p2) {
        return connectionGroupOf.get(p1).contains(p2);
    }

    public Set<Set<Point>> getConnectionGroups() {
        return new HashSet<>(this.connectionGroupOf.values());
    }

    public boolean isFullyConnected() {
        return connectionGroupOf.get(points.getFirst()).size() == points.size();
    }

    @Override
    public String toString() {
        return getConnectionGroups().stream()
            .map(connectionGroup -> connectionGroup.stream().toList().toString())
            .toList().toString();
    }
}
