package day07;

import common.parsers.AbstractParser;
import day06.Equation.Operation;
import day06.ProblemSheet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day07Parser extends AbstractParser<Day07IntermediateType> {
    @Override
    public Day07IntermediateType parseDayInput(List<String> lines) {
        List<String> nonBlankLines = new ArrayList<>(
            lines.stream().filter(line -> !line.isBlank()).toList()
        );
        nonBlankLines.add("#".repeat(lines.getFirst().length()));
        return new Day07IntermediateType(new ManifoldDiagram(nonBlankLines));
    }
}
