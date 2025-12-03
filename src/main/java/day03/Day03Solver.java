package day03;

import common.solvers.AbstractSolver;
import day03.Day03IntermediateType.Bank;

import java.math.BigInteger;
import java.util.List;
import java.util.PriorityQueue;

public class Day03Solver extends AbstractSolver<Day03IntermediateType> {

    private static class IntegerWithIndex implements Comparable<IntegerWithIndex> {
        public Integer number;
        public Integer index;

        public IntegerWithIndex(int number, int index) {
            this.number = number;
            this.index = index;
        }

        @Override
        public int compareTo(IntegerWithIndex other) {
            if (this.number.compareTo(other.number) != 0) {
                // If integers aren't the same, we want the larger one to be first.
                return other.number.compareTo(this.number);
            }
            // Otherwise, we want the smallest index to be first.
            return this.index.compareTo(other.index);
        }
    }

    /*
        Parts 1 & 2.
        O(n log(n-k))
     */
    private BigInteger findOptimalJoltage(Bank bank, int k) {
        List<Integer> batteryJolts = bank.batteryJolts();
        PriorityQueue<IntegerWithIndex> bestSoFar = new PriorityQueue<>();
        for (int batteryIdx = 0; batteryIdx < batteryJolts.size()-k; batteryIdx++) {
            bestSoFar.add(new IntegerWithIndex(batteryJolts.get(batteryIdx), batteryIdx));
        }

        StringBuilder solution = new StringBuilder();
        int largestIdxConsidered = -1;
        for (int batteryIdx = batteryJolts.size()-k; batteryIdx < batteryJolts.size(); batteryIdx++) {
            bestSoFar.add(new IntegerWithIndex(batteryJolts.get(batteryIdx), batteryIdx));

            // For the last k batteries, start using the PQ.
            IntegerWithIndex candidateIntegerWithIndex;
            int solutionDigit;
            do {
                // Discard candidates that come before the largest index already greedily put in the solution.
                candidateIntegerWithIndex = bestSoFar.remove();
                solutionDigit = candidateIntegerWithIndex.number;
            } while (candidateIntegerWithIndex.index <= largestIdxConsidered);

            largestIdxConsidered = candidateIntegerWithIndex.index;
            solution.append(solutionDigit);
        }

        return new BigInteger(solution.toString());
    }

    @Override
    public String solvePartOne(Day03IntermediateType input) {
        return input.banks().stream()
                .map(bank -> findOptimalJoltage(bank, 2))
                .reduce(BigInteger.ZERO, BigInteger::add)
                .toString();
    }

    @Override
    public String solvePartTwo(Day03IntermediateType input) {
        return input.banks().stream()
                .map(bank -> findOptimalJoltage(bank, 12))
                .reduce(BigInteger.ZERO, BigInteger::add)
                .toString();
    }
}
