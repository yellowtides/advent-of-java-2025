package day07;

import day07.ManifoldDiagramCell.Coordinates;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ManifoldDiagram {
    private final ManifoldDiagramCell[][] manifoldDiagramCells;
    public ManifoldDiagramCell sourceCell;
    public final int diagramWidth;
    public final int diagramHeight;

    private void findAndPopulateSource() {
        Arrays.stream(this.manifoldDiagramCells).forEach(cellLine -> {
            Optional<ManifoldDiagramCell> maybeSourceCell = Arrays.stream(cellLine)
                    .filter(cell -> cell.cellType == ManifoldDiagramCellType.SOURCE).findFirst();
            maybeSourceCell.ifPresent(sourceCell -> this.sourceCell = sourceCell);
        });
        this.sourceCell.addImmediateBeamSources(Set.of(this.sourceCell));
        this.sourceCell.nTimelinesPresent = 1L;
    }

    public ManifoldDiagram(List<String> manifoldDiagramCells) {
        this.diagramHeight = manifoldDiagramCells.size();
        this.diagramWidth = manifoldDiagramCells.getFirst().length();
        this.manifoldDiagramCells = IntStream.range(0, this.diagramHeight).mapToObj(i -> {
            String manifoldDiagramCellLine = manifoldDiagramCells.get(i);
            return IntStream.range(0, this.diagramWidth).mapToObj(j -> (
                new ManifoldDiagramCell(ManifoldDiagramCellType.of(
                    manifoldDiagramCellLine.charAt(j)
                ), new Coordinates(i, j))
            )).toArray(ManifoldDiagramCell[]::new);
        }).toArray(ManifoldDiagramCell[][]::new);
        this.findAndPopulateSource();
    }

    public boolean isOutOfBounds(Coordinates coordinates) {
        return coordinates.i() < 0 || coordinates.j() < 0
            || coordinates.i() >= this.diagramHeight || coordinates.j() >= this.diagramWidth;
    }

    public ManifoldDiagramCell get(Coordinates coordinates) {
        return manifoldDiagramCells[coordinates.i()][coordinates.j()];
    }

    public void set(Coordinates coordinates, ManifoldDiagramCell manifoldDiagramCell) {
        manifoldDiagramCells[coordinates.i()][coordinates.j()] = manifoldDiagramCell;
    }

    public Optional<ManifoldDiagramCell> getCellBelow(ManifoldDiagramCell manifoldDiagramCell) {
        Coordinates currentCoordinates = manifoldDiagramCell.coordinates;
        Coordinates nextCoordinates = new Coordinates(currentCoordinates.i()+1, currentCoordinates.j());
        if (isOutOfBounds(nextCoordinates)) {
            return Optional.empty();
        }
        return Optional.of(this.get(nextCoordinates));
    }

    public List<ManifoldDiagramCell> getSideCells(ManifoldDiagramCell manifoldDiagramCell) {
        Coordinates currentCoordinates = manifoldDiagramCell.coordinates;
        ArrayList<ManifoldDiagramCell> sideCells = new ArrayList<>();
        List.of(-1, 1).forEach(dj -> {
            Coordinates nextCoordinates = new Coordinates(currentCoordinates.i(), currentCoordinates.j() + dj);
            if (!isOutOfBounds(nextCoordinates)) {
                sideCells.add(this.get(nextCoordinates));
            }
        });
        return sideCells;
    }

    public List<ManifoldDiagramCell> getAllCellsWithType(ManifoldDiagramCellType cellType) {
        return Arrays.stream(manifoldDiagramCells).flatMap(cellList -> Arrays.stream(cellList)
                        .filter(cell -> cell.cellType.equals(cellType)))
                .toList();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Source: ").append(sourceCell.coordinates);
        stringBuilder.append('\n');
        stringBuilder.append(
            Arrays.stream(manifoldDiagramCells)
                .map(cellLine -> Arrays.stream(cellLine)
                        .map(ManifoldDiagramCell::toString)
                        .collect(Collectors.joining()))
                .map(lineStr -> lineStr + '\n')
                .collect(Collectors.joining())
        );
        return stringBuilder.toString();
    }
}
