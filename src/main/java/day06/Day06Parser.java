package day06;

import common.parsers.AbstractParser;
import day06.Equation.Operation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Day06Parser extends AbstractParser<Day06IntermediateType> {
    public List<String> getOperandLines(List<String> lines) {
        return IntStream.range(0, lines.size()-1).mapToObj(lines::get).toList();
    }

    public List<List<Long>> parsePartOneOperands(List<String> operandLines) {
        List<List<Long>> transposedOperands = operandLines.stream().map(operandLine ->
                Arrays.stream(operandLine.split("\\s")).filter(split -> !split.isBlank())
                        .map(Long::valueOf).toList()
        ).toList();
        return IntStream.range(0, transposedOperands.getFirst().size()).mapToObj(operandIdx ->
            transposedOperands.stream()
                .map(operandsLine -> operandsLine.get(operandIdx))
                .toList()
        ).toList();
    }

    public List<List<Long>> parsePartTwoOperands(List<String> operandLines) {
        List<List<Long>> parsedOperands = new ArrayList<>();
        List<Long> currentOperands = new ArrayList<>();
        for (int digitIdx = 0; digitIdx < operandLines.getFirst().length(); digitIdx++) {
            int finalDigitIdx = digitIdx;
            String maybeOperand = operandLines.stream()
                    .map(operandLine -> String.valueOf(operandLine.charAt(finalDigitIdx)))
                    .collect(Collectors.joining());
            if (maybeOperand.isBlank() && !currentOperands.isEmpty()) {
                parsedOperands.add(currentOperands);
                currentOperands = new ArrayList<>();
                continue;
            }
            if (!maybeOperand.isBlank()) {
                currentOperands.add(Long.valueOf(maybeOperand.strip()));
            }
        }

        if (!currentOperands.isEmpty()) {
            parsedOperands.add(currentOperands);
        }
        return parsedOperands;
    }

    public List<Operation> parseOperations(List<String> lines) {
        String operationLine = lines.getLast();
        return Arrays.stream(operationLine.split("\\s"))
            .filter(split -> !split.isBlank())
            .map(operationStr -> operationStr.charAt(0))
            .map(Operation::of)
            .toList();
    }

    public ProblemSheet parseProblemSheet(List<String> nonBlankLines, Function<List<String>, List<List<Long>>> parseOperands) {
        List<Operation> parsedOperations = parseOperations(nonBlankLines);

        List<String> operandLines = getOperandLines(nonBlankLines);
        List<List<Long>> parsedOperands = parseOperands.apply(operandLines);

        ProblemSheet problemSheet = new ProblemSheet();
        IntStream.range(0, parsedOperations.size()).forEach(problemIdx -> {
            Operation operation = parsedOperations.get(problemIdx);
            List<Long> operands = parsedOperands.get(problemIdx);
            problemSheet.addProblem(operation, operands);
        });
        return problemSheet;
    }

    @Override
    public Day06IntermediateType parseDayInput(List<String> lines) {
        List<String> nonBlankLines = lines.stream().filter(line -> !line.isBlank()).toList();
        ProblemSheet partOneProblemSheet = parseProblemSheet(nonBlankLines, this::parsePartOneOperands);
        ProblemSheet partTwoProblemSheet = parseProblemSheet(nonBlankLines, this::parsePartTwoOperands);
        return new Day06IntermediateType(partOneProblemSheet, partTwoProblemSheet);
    }
}
