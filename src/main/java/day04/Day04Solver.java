package day04;

import common.solvers.AbstractSolver;
import day03.Day03IntermediateType.Bank;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day04Solver extends AbstractSolver<Day04IntermediateType> {
    @Override
    public String solvePartOne(Day04IntermediateType input) {
        Layout layout = input.layout();
        return "" + layout.getRemovablePaperRolls(3).size();
    }

    @Override
    public String solvePartTwo(Day04IntermediateType input) {
        Layout layout = input.layout();
        int newlyRemovedCount = -1;
        int removedCount = 0;
        while (newlyRemovedCount != 0) {
            newlyRemovedCount = layout.removeAllRemovablePaperRolls(3);
            removedCount += newlyRemovedCount;
        }
        return Integer.toString(removedCount);
    }
}
