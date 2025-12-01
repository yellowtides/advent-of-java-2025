package common.types;


import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

public record Solution(Optional<String> partOneSolution, Optional<String> partTwoSolution) {
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        List<Optional<String>> solutions = List.of(partOneSolution, partTwoSolution);
        IntStream.range(0, solutions.size()).forEach(solutionIdx -> {
            final Optional<String> solution = solutions.get(solutionIdx);
            stringBuilder.append("Part ").append(solutionIdx + 1);
            if (solution.isEmpty()) {
                stringBuilder.append(" not implemented.");
            }
            else {
                stringBuilder.append(": ").append(solution.get());
            }
            stringBuilder.append("\n");
        });
        return stringBuilder.toString();
    }
}