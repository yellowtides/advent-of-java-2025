package day07;

import common.solvers.AbstractSolver;

import java.util.List;

public class Day07Solver extends AbstractSolver<Day07IntermediateType> {
    @Override
    public String solvePartOne(Day07IntermediateType input) {
        ManifoldDiagram manifoldDiagram = input.manifoldDiagram();
        ManifoldDiagramBeamPropagator beamPropagator = new ManifoldDiagramBeamPropagator(manifoldDiagram);
        //noinspection StatementWithEmptyBody
        while (beamPropagator.propagateOneStep());
        return String.valueOf(beamPropagator.splitCount);
    }

    @Override
    public String solvePartTwo(Day07IntermediateType input) {
        ManifoldDiagram manifoldDiagram = input.manifoldDiagram();
        ManifoldDiagramBeamPropagator beamPropagator = new ManifoldDiagramBeamPropagator(manifoldDiagram);
        //noinspection StatementWithEmptyBody
        while (beamPropagator.propagateOneStep());
        List<ManifoldDiagramCell> sinkCells = manifoldDiagram.getAllCellsWithType(ManifoldDiagramCellType.SINK);
        Long nTimelines = sinkCells.stream().map(cell -> cell.nTimelinesPresent).reduce(0L, Long::sum);
        return Long.toString(nTimelines);
    }
}
