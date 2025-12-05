package day05;


import java.util.*;

public class RangeFinder {
    private final TreeSet<Range> ranges;

    public RangeFinder(List<Range> ranges) {
        this.ranges = normalize(ranges);
    }

    private TreeSet<Range> normalize(List<Range> ranges) {
        ranges.sort(Comparator.naturalOrder());
        Stack<Range> mergedSortedRanges = new Stack<>();
        for (Range range : ranges) {
            while (!mergedSortedRanges.empty() && mergedSortedRanges.peek().overlapsWith(range)) {
                range = mergedSortedRanges.pop().unionWith(range);
                assert range != null;
            }
            mergedSortedRanges.push(range);
        }
        return new TreeSet<>(mergedSortedRanges);
    }

    public boolean isInAnyRange(Long element) {
        Range candidateRange = ranges.lower(new Range(element + 1, Long.MAX_VALUE));
        if (Objects.isNull(candidateRange)) {
            return false;
        }
        return candidateRange.contains(element);
    }

    public TreeSet<Range> getNormalizedRanges() {
        return ranges;
    }
}
