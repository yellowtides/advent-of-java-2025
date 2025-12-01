package day_01;

import java.util.List;

public record Day01IntermediateType(List<Action> actions) {

    public enum Direction {
        LEFT, RIGHT
    }

    public record Action(Direction direction, int times) {}
}
