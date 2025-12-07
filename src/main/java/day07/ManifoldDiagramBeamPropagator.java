package day07;

import day07.ManifoldDiagramCell.Coordinates;

import java.util.*;

import static day07.ManifoldDiagramCellType.BEAM;
import static day07.ManifoldDiagramCellType.SPLITTER;

public class ManifoldDiagramBeamPropagator {

    public ManifoldDiagram manifoldDiagram;
    private List<ManifoldDiagramCell> nextStepPendingBeamLocations;
    public List<ManifoldDiagramCell> pendingBeamLocations;
    public Long splitCount;

    public ManifoldDiagramBeamPropagator(ManifoldDiagram manifoldDiagram) {
        this.manifoldDiagram = manifoldDiagram;
        this.pendingBeamLocations = new ArrayList<>();
        this.nextStepPendingBeamLocations = new ArrayList<>();
        this.pendingBeamLocations.add(this.manifoldDiagram.sourceCell);
        this.splitCount = 0L;
    }

    private void fillNonSplitterCell(Set<ManifoldDiagramCell> newImmediateBeamSources, ManifoldDiagramCell cellToBeFilled) {
        Coordinates coordinatesToBeFilled = cellToBeFilled.coordinates;
        switch (cellToBeFilled.cellType) {
            case SOURCE:
                return;
            case BEAM:
            case SINK:
                cellToBeFilled.addImmediateBeamSources(newImmediateBeamSources);
                return;
            case EMPTY:
                ManifoldDiagramCell newBeamCell = new ManifoldDiagramCell(BEAM, coordinatesToBeFilled);
                newBeamCell.addImmediateBeamSources(newImmediateBeamSources);
                this.manifoldDiagram.set(coordinatesToBeFilled, newBeamCell);
                this.nextStepPendingBeamLocations.add(newBeamCell);
        }
    }

    public boolean propagateOneStep() {
        if (this.pendingBeamLocations.isEmpty()) {
            return false;
        }
        this.pendingBeamLocations.forEach(sourceCell -> {
            Optional<ManifoldDiagramCell> maybeNextCellInPath = this.manifoldDiagram.getCellBelow(sourceCell);
            if (maybeNextCellInPath.isEmpty()) {
                return;
            }
            ManifoldDiagramCell nextCellInPath = maybeNextCellInPath.get();
            if (!nextCellInPath.cellType.equals(SPLITTER)) {
                // For non-splitter cells, the immediate beam sources don't change.
                fillNonSplitterCell(sourceCell.immediateBeamSources, nextCellInPath);
                return;
            }
            // We've reached a splitter. First, propagate beam sources.
            nextCellInPath.addImmediateBeamSources(sourceCell.immediateBeamSources);
            // Split the beam.
            splitCount += 1;
            List<ManifoldDiagramCell> nextNextCellsInPath = this.manifoldDiagram.getSideCells(nextCellInPath);
            // Forget previous immediate beam sources, since this splitter is the new immediate beam source.
            nextNextCellsInPath.forEach(nextNextCellInPath -> fillNonSplitterCell(
                Set.of(nextCellInPath), nextNextCellInPath)
            );
        });
        this.pendingBeamLocations = nextStepPendingBeamLocations;
        this.nextStepPendingBeamLocations = new ArrayList<>();
        return true;
    }
}
