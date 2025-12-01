package day_01;

import common.parsers.AbstractParser;
import day_01.Day01IntermediateType.Direction;
import day_01.Day01IntermediateType.Action;

import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

public class Day01Parser extends AbstractParser<Day01IntermediateType> {

    String regex = "([LR])(\\d+)";
    Pattern pattern = Pattern.compile(regex);

    @Override
    public Day01IntermediateType parseDayInput(List<String> lines) {
        return new Day01IntermediateType(lines.stream().filter(line -> !line.isBlank()).map(
            line -> {
                Matcher matcher = pattern.matcher(line);
                boolean _found = matcher.find();
                String unparsedDirection = matcher.group(1);
                Direction direction = unparsedDirection.equals("L") ? Direction.LEFT : Direction.RIGHT;
                String unparsedTimes = matcher.group(2);
                int times = Integer.parseInt(unparsedTimes);
                return new Action(direction, times);
            }
        ).collect(Collectors.toList()));
    }
}
