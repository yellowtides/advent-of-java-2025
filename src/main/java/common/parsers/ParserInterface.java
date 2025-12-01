package common.parsers;

import common.types.Day;

import java.io.IOException;

public interface ParserInterface<OT> {
    OT parseDayInput(Day day) throws IOException;
}
