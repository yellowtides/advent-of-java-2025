package common.composer;

import common.parsers.AbstractParser;
import common.solvers.AbstractSolver;
import common.types.Day;
import common.types.Solution;

import java.io.IOException;
import java.util.Optional;

public record Composer<T>(Day day, AbstractParser<T> parser, AbstractSolver<T> solver) {

    public Solution parseAndSolve() throws IOException {
        T parsedInput = parser.parseDayInput(day);
        Optional<String> partOneSolution = Optional.ofNullable(solver.solvePartOne(parsedInput));
        Optional<String> partTwoSolution = Optional.ofNullable(solver.solvePartTwo(parsedInput));
        return new Solution(partOneSolution, partTwoSolution);
    }
}
