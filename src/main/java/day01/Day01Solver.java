package day01;

import common.solvers.AbstractSolver;
import day01.Day01IntermediateType.Action;
import day01.Day01IntermediateType.Direction;

import java.util.List;

public class Day01Solver extends AbstractSolver<Day01IntermediateType> {

    private static class Dial {
        private final int DIAL_MOD;
        private int previousPosition;
        private Action lastAction;
        private int position;

        public Dial(int startingPosition, int DIAL_MOD) {
            this.previousPosition = -1;
            this.position = startingPosition;
            this.DIAL_MOD = DIAL_MOD;
        }

        public int getPreviousClickCount() {
            int nClicks = lastAction.times() / DIAL_MOD;
            if (previousPosition == 0) {
                return nClicks;
            }
            if (position == 0) {
                return nClicks + 1;
            }
            if (position > previousPosition && lastAction.direction().equals(Direction.LEFT)) {
                return nClicks + 1;
            }
            if (position < previousPosition && lastAction.direction().equals(Direction.RIGHT)) {
                return nClicks + 1;
            }
            return nClicks;
        }

        public void rotate(Action action) {
            this.lastAction = action;
            this.previousPosition = position;

            if (action.direction().equals(Direction.LEFT)) {
                position -= action.times();
            }
            else {
                position += action.times();
            }
            position %= DIAL_MOD;
            if (position < 0) {
                position += DIAL_MOD;
            }
        }

        public int getPosition() {
            return this.position;
        }
    }


    @Override
    public String solvePartOne(Day01IntermediateType input) {
        List<Action> actions = input.actions();
        Dial dial = new Dial(50, 100);

        int zeroes = 0;
        for (Action action : actions) {
            dial.rotate(action);
            if (dial.getPosition() == 0) {
                zeroes++;
            }
        }

        return String.valueOf(zeroes);
    }

    @Override
    public String solvePartTwo(Day01IntermediateType input) {
        List<Action> actions = input.actions();
        Dial dial = new Dial(50, 100);

        int zeroes = 0;
        for (Action action : actions) {
            dial.rotate(action);
            zeroes += dial.getPreviousClickCount();
        }

        return String.valueOf(zeroes);
    }
}
