package common.parsers;

import common.types.Day;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class AbstractParser<IT> implements ParserInterface<IT> {

    abstract public IT parseDayInput(List<String> lines);
    public IT parseDayInput(Day day) throws IOException {
        String dayBit = "day_" + String.format("%02d", day.ordinal);
        Path filePath = Paths.get("src", "main", "java", dayBit, "input.txt");
        List<String> lines = Files.readAllLines(filePath);
        return parseDayInput(lines);
    }
}
