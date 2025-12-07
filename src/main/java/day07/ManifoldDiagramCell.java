package day07;

import java.util.HashSet;
import java.util.Set;

public class ManifoldDiagramCell {
    public record Coordinates(int i, int j) {
    }

    public ManifoldDiagramCellType cellType;
    public Set<ManifoldDiagramCell> immediateBeamSources;
    public Long nTimelinesPresent;
    public Coordinates coordinates;

    public ManifoldDiagramCell(ManifoldDiagramCellType cellType, Coordinates coordinates) {
        this.cellType = cellType;
        this.coordinates = coordinates;
        this.immediateBeamSources = new HashSet<>();
        this.nTimelinesPresent = 0L;
    }

    public void addImmediateBeamSources(Set<ManifoldDiagramCell> newBeamSources) {
        immediateBeamSources.addAll(newBeamSources);
        newBeamSources.forEach(newBeamSource -> nTimelinesPresent += newBeamSource.nTimelinesPresent);
    }

    @Override
    public String toString() {
        return cellType.toString();
    }
}
