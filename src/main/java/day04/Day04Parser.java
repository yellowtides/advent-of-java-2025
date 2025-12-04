package day04;

import common.parsers.AbstractParser;
import day03.Day03IntermediateType.Bank;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day04Parser extends AbstractParser<Day04IntermediateType> {
    @Override
    public Day04IntermediateType parseDayInput(List<String> lines) {
        int gridHeight = lines.size();
        int gridLength = lines.getFirst().length();
        Cell[][] cells = new Cell[gridHeight][gridLength];
        IntStream.range(0, lines.size()).forEach(lineIdx -> {
            String line = lines.get(lineIdx);
            IntStream.range(0, line.length()).forEach(charIdx -> {
                cells[lineIdx][charIdx] = new Cell (line.charAt(charIdx) == '@');
            });
        });
        return new Day04IntermediateType(new Layout(cells));
    }
}
