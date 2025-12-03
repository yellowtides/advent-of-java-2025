package day03;

import java.util.List;

public record Day03IntermediateType(List<Bank> banks) {
    public record Bank(List<Integer> batteryJolts) {
    }
}