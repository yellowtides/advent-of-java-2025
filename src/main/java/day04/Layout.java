package day04;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Layout {
    private Cell[][] cells;
    public final int HEIGHT;
    public final int LENGTH;

    public Layout(Cell[][] cells) {
        this.cells = cells;
        this.HEIGHT = cells.length;
        this.LENGTH = cells[0].length;
    }

    private boolean isOutOfBounds(int i, int j) {
        return i < 0 || j < 0 || i >= this.HEIGHT || j >= this.LENGTH;
    }

    public int removeAllRemovablePaperRolls(int ADJACENCY_CONDITION_MAX) {
        List<Cell> cellsWithRemovablePaperRolls = getRemovablePaperRolls(ADJACENCY_CONDITION_MAX);
        cellsWithRemovablePaperRolls.forEach(Cell::removePaperRoll);
        return cellsWithRemovablePaperRolls.size();
    }

    public List<Cell> getRemovablePaperRolls(int ADJACENCY_CONDITION_MAX) {
        return IntStream.range(0, HEIGHT).boxed().flatMap(lineIdx ->
            IntStream.range(0, LENGTH).boxed().flatMap(colIdx -> {
                Cell currentCell = getCell(lineIdx, colIdx);
                if (!currentCell.hasPaperRoll()) {
                    return Stream.of((Cell) null);
                }
                List<Cell> paperRollNeighbours = getCellsAdjacentTo(lineIdx, colIdx)
                        .stream().filter(Cell::hasPaperRoll).toList();
                boolean hasFewEnoughNeighbours = paperRollNeighbours.size() <= ADJACENCY_CONDITION_MAX;
                return hasFewEnoughNeighbours ? Stream.of(currentCell) : Stream.of((Cell) null);
            })
        ).filter(Objects::nonNull).distinct().toList();
    }

    public List<Cell> getCellsAdjacentTo(int i, int j) {
        return IntStream.rangeClosed(-1, 1).boxed().flatMap(di ->
            IntStream.rangeClosed(-1, 1).boxed().flatMap(dj -> {
                if (di == 0 && dj == 0) {
                    return null;
                }
                int ip = i + di;
                int jp = j + dj;
                if (isOutOfBounds(ip, jp)) {
                    return null;
                }
                return Stream.of(cells[ip][jp]);
            })
        ).filter(Objects::nonNull).collect(Collectors.toList());
    }

    public Cell getCell(int i, int j) {
        return cells[i][j];
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.stream(cells).map(Arrays::toString).toArray());
    }
}
