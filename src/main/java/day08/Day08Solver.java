package day08;

import common.solvers.AbstractSolver;
import day07.ManifoldDiagram;
import day07.ManifoldDiagramBeamPropagator;
import day07.ManifoldDiagramCell;
import day07.ManifoldDiagramCellType;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Day08Solver extends AbstractSolver<Day08IntermediateType> {
    @Override
    public String solvePartOne(Day08IntermediateType input) {
        Space space = input.space();
        final int N_CONNECTIONS = 1000;
        final int N_TOP = 3;
        IntStream.range(0, N_CONNECTIONS).forEach(_connection -> {
            Line nextConnection = space.getNextConnection();
            if (space.isConnected(nextConnection.p1, nextConnection.p2)) {
                return;
            }
            space.connect(nextConnection.p1, nextConnection.p2);
        });
        List<Integer> groupSizes = space.getConnectionGroups().stream().map(Set::size)
            .sorted(Comparator.reverseOrder())
            .toList();
        return "" + LongStream.range(0, N_TOP).map(idx -> groupSizes.get((int) idx))
                .reduce(1L, (x, y) -> x * y);
    }

    @Override
    public String solvePartTwo(Day08IntermediateType input) {
        Space space = input.space();
        Line lastConnection = null;
        while (!space.isFullyConnected()) {
            lastConnection = space.getNextConnection();
            if (space.isConnected(lastConnection.p1, lastConnection.p2)) {
                continue;
            }
            space.connect(lastConnection.p1, lastConnection.p2);
        }
        assert lastConnection != null;
        return "" + (long) lastConnection.p1.x() * (long) lastConnection.p2.x();
    }
}
