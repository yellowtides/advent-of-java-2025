package day02;

import java.util.List;

public record Day02IntermediateType(List<Pair> pairs) {
    public record Pair(String first, String last) {
    }
}