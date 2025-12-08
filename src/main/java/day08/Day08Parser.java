package day08;

import common.parsers.AbstractParser;


import java.util.ArrayList;
import java.util.List;

public class Day08Parser extends AbstractParser<Day08IntermediateType> {
    @Override
    public Day08IntermediateType parseDayInput(List<String> lines) {
        List<Point> points = new ArrayList<>();
        lines.forEach(coordinatesLine -> {
            if (coordinatesLine.isBlank()) {
                return;
            }
            String[] splitCoordinates = coordinatesLine.split(",");
            long x = Long.parseLong(splitCoordinates[0]);
            long y = Long.parseLong(splitCoordinates[1]);
            long z = Long.parseLong(splitCoordinates[2]);
            points.add(new Point(x, y, z));
        });
        return new Day08IntermediateType(new Space(points));
    }
}
