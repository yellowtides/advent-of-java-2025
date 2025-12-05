package day05;

import java.util.Optional;

public record Range(long lowerBound, long higherBound) implements Comparable<Range> {
    public boolean contains(long k) {
        return lowerBound <= k && k <= higherBound;
    }

    public boolean overlapsWith(Range otherRange) {
        return otherRange.contains(lowerBound) || otherRange.contains(higherBound) ||
                contains(otherRange.lowerBound) || contains(otherRange.higherBound);
    }

    public Range unionWith(Range otherRange) {
        if (overlapsWith(otherRange)) {
            return new Range(Math.min(lowerBound, otherRange.lowerBound),
                    Math.max(higherBound, otherRange.higherBound));
        }
        return null;
    }

    public long size() {
        return higherBound - lowerBound + 1;
    }

    @Override
    public int compareTo(Range otherRange) {
        if (lowerBound != otherRange.lowerBound) {
            return Long.compare(lowerBound, otherRange.lowerBound);
        }
        return Long.compare(higherBound, otherRange.higherBound);
    }
}
