package day03;

import common.parsers.AbstractParser;
import day02.Day02IntermediateType.Pair;
import day03.Day03IntermediateType.Bank;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day03Parser extends AbstractParser<Day03IntermediateType> {
    @Override
    public Day03IntermediateType parseDayInput(List<String> lines) {
        return new Day03IntermediateType(
            lines.stream().filter(line -> !line.isBlank())
                .map(line -> new Bank(line.chars().map(ch -> ch - (int) '0').boxed()
                    .collect(Collectors.toList())
                )).collect(Collectors.toList())
        );
    }
}
