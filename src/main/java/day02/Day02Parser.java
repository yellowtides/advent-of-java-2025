package day02;

import common.parsers.AbstractParser;
import day02.Day02IntermediateType.Pair;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Day02Parser extends AbstractParser<Day02IntermediateType> {
    @Override
    public Day02IntermediateType parseDayInput(List<String> lines) {
        Stream<String> unparsedFirstLastPairs = Arrays.stream(
            lines.stream().filter(line -> !line.isBlank()).findFirst().get()
            .split(",")
        );
        return new Day02IntermediateType(unparsedFirstLastPairs.map(firstLastPair -> {
            String[] firstLastElement = firstLastPair.split("-");
            return new Pair(firstLastElement[0], firstLastElement[1]);
        }).collect(Collectors.toList()));
    }
}
