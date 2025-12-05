package day05;

import common.parsers.AbstractParser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day05Parser extends AbstractParser<Day05IntermediateType> {
    @Override
    public Day05IntermediateType parseDayInput(List<String> lines) {
        int currentLineIdx = 0;
        List<Range> ranges = new ArrayList<>();
        for (;!lines.get(currentLineIdx).isBlank(); currentLineIdx++) {
            List<Long> rangeEndpoints = Arrays.stream(lines.get(currentLineIdx)
                    .split("-")).map(Long::valueOf).toList();
            ranges.add(new Range(rangeEndpoints.get(0), rangeEndpoints.get(1)));
        }
        currentLineIdx++;
        List<Long> ingredients = new ArrayList<>();
        for (;currentLineIdx < lines.size(); currentLineIdx++) {
            ingredients.add(Long.valueOf(lines.get(currentLineIdx)));
        }
        return new Day05IntermediateType(ranges, ingredients);
    }
}
