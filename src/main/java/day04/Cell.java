package day04;

public class Cell {
    private boolean hasPaperRoll;

    public Cell(boolean hasPaperRoll) {
        this.hasPaperRoll = hasPaperRoll;
    }

    public boolean hasPaperRoll() {
        return hasPaperRoll;
    }

    public boolean removePaperRoll() {
        if (hasPaperRoll) {
            hasPaperRoll = false;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return hasPaperRoll ? "@" : ".";
    }
}
