package day06;

import common.solvers.AbstractSolver;

import java.util.List;

public class Day06Solver extends AbstractSolver<Day06IntermediateType> {
    @Override
    public String solvePartOne(Day06IntermediateType input) {
        List<Long> solutions = input.partOneProblemSheet().solveAll();
        Long solutionSum = solutions.stream().reduce(0L, Long::sum);
        return Long.toString(solutionSum);
    }

    @Override
    public String solvePartTwo(Day06IntermediateType input) {
        List<Long> solutions = input.partTwoProblemSheet().solveAll();
        Long solutionSum = solutions.stream().reduce(0L, Long::sum);
        return Long.toString(solutionSum);
    }
}
