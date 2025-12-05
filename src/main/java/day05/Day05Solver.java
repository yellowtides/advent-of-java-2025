package day05;

import common.solvers.AbstractSolver;

public class Day05Solver extends AbstractSolver<Day05IntermediateType> {
    @Override
    public String solvePartOne(Day05IntermediateType input) {
        RangeFinder rangeFinder = new RangeFinder(input.ranges());
        long nFreshIngredients = input.ingredients().stream()
            .filter(rangeFinder::isInAnyRange).count();
        return Long.toString(nFreshIngredients);
    }

    @Override
    public String solvePartTwo(Day05IntermediateType input) {
        RangeFinder rangeFinder = new RangeFinder(input.ranges());
        long normalizedRangeSizeSum = rangeFinder.getNormalizedRanges().stream()
            .map(Range::size).reduce(0L, Long::sum);
        return Long.toString(normalizedRangeSizeSum);
    }
}
