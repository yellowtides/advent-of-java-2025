package common.types;

import java.util.Arrays;
import java.util.Optional;

/**
    An enum of days currently solved.
 */
public enum Day {
    DAY_01(1),
    DAY_02(2),
    DAY_03(3),
    DAY_04(4),
    DAY_05(5);

    public final int ordinal;
    Day(int ordinal) {
        this.ordinal = ordinal;
    }

    public static Optional<Day> valueOf(int ordinal) {
        return Arrays.stream(Day.values()).filter(day -> day.ordinal == ordinal).findFirst();
    }
}
