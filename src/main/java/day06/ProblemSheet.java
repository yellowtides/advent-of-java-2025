package day06;

import day06.Equation.Operation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProblemSheet {
    private final List<Equation> problems;

    public ProblemSheet() {
        this.problems = new ArrayList<>();
    }

    public ProblemSheet(List<Equation> problems) {
        this.problems = problems;
    }

    public void addProblem(Operation operation, List<Long> operands) {
        List<Equation> boxedOperands = operands.stream().map(Equation::new)
            .collect(Collectors.toList());
        this.problems.add(new Equation(operation, boxedOperands));
    }

    public List<Long> solveAll() {
        return problems.stream().map(Equation::evaluate).toList();
    }

    @Override
    public String toString() {
        return problems.toString();
    }
}
