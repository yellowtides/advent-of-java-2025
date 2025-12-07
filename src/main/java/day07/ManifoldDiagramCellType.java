package day07;

import java.util.Arrays;
import java.util.Optional;

public enum ManifoldDiagramCellType {
    SOURCE('S'), BEAM('|'), SPLITTER('^'), EMPTY('.'), SINK('#');

    public final Character charFormat;

    ManifoldDiagramCellType(Character charFormat) {
        this.charFormat = charFormat;
    }

    public static ManifoldDiagramCellType of(Character ch) {
        Optional<ManifoldDiagramCellType> maybeCellType = Arrays.stream(ManifoldDiagramCellType.values())
            .filter(cellType -> cellType.charFormat.equals(ch))
            .findFirst();
        if (maybeCellType.isEmpty()) {
            throw new IllegalArgumentException("Attempted to parse invalid cell: " + ch );
        }
        return maybeCellType.get();
    }

    @Override
    public String toString() {
        return String.valueOf(charFormat);
    }
}
