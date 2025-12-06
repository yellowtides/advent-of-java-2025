package day06;

import java.util.ArrayList;
import java.util.List;

public class Equation {
    public enum Operation {
        ADD, MULTIPLY;

        public static Operation of(char ch) {
            if (ch == '+') {
                return ADD;
            }
            if (ch == '*') {
                return MULTIPLY;
            }
            throw new IllegalArgumentException("Argument " + ch + " is not a parasble operation.");
        }
    }

    private final Operation operation;
    private final List<Equation> operands;
    private Long evaluation;

    public Equation(Operation operation, List<Equation> operands) {
        this.operation = operation;
        this.operands = operands;
        this.evaluation = null;
    }

    public Equation(Long operand) {
        this.operation = null;
        this.operands = new ArrayList<>();
        this.evaluation = operand;
    }

    public Long evaluate() {
        if (evaluation == null) {
            assert operation != null;
            evaluation = switch (operation) {
                case ADD -> operands.stream().map(Equation::evaluate)
                        .reduce(0L, Long::sum);
                case MULTIPLY -> operands.stream().map(Equation::evaluate)
                        .reduce(1L, (x, y) -> x * y);
            };
        }
        return evaluation;
    }

    @Override
    public String toString() {
        if (evaluation == null) {
            assert operation != null;
            return operation.toString() + ' ' + operands.toString();
        }
        return Long.toString(evaluation);
    }
}
