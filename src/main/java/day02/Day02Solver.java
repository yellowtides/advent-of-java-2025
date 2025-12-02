package day02;

import common.solvers.AbstractSolver;
import day02.Day02IntermediateType.Pair;

import java.math.BigInteger;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

import static day02.Helpers.getAllPrimesLessOrEqualTo;
import static day02.Helpers.getAllSimplePrimeCompositions;

public class Day02Solver extends AbstractSolver<Day02IntermediateType> {
    public static class Range {

        private final String lowerBound;
        private final String upperBound;

        public Range(Pair pair) {
            this.lowerBound = pair.first();
            this.upperBound = pair.last();
        }

        private static String firstKInvalidGreaterOrEqualTo(int k, String number) {
            if (number.length() % k != 0) {
                number = ("1" + "0".repeat(number.length() / k)).repeat(k);
            }
            String kPrefix = number.substring(0, number.length() / k);
            String repeatedKPrefix = kPrefix.repeat(k);
            if (repeatedKPrefix.compareTo(number) >= 0) {
                return repeatedKPrefix;
            }
            String repeatedKPrefixPlusOne = new BigInteger(kPrefix).add(BigInteger.ONE).toString();
            return repeatedKPrefixPlusOne.repeat(k);
        }

        private static String lastKInvalidSmallerOrEqualTo(int k, String number) {
            if (number.length() % k != 0) {
                number = "9".repeat(number.length() / k * k);
            }
            String kPrefix = number.substring(0, number.length() / k);
            String repeatedKPrefix = kPrefix.repeat(k);
            if (repeatedKPrefix.compareTo(number) <= 0) {
                return repeatedKPrefix;
            }
            String repeatedKPrefixMinusOne = new BigInteger(kPrefix).subtract(BigInteger.ONE).toString();
            return repeatedKPrefixMinusOne.repeat(k);
        }

        private Optional<String[]> getKInvalidRange(int k) {
            String firstKInvalid = firstKInvalidGreaterOrEqualTo(k, lowerBound);
            String lastKInvalid = lastKInvalidSmallerOrEqualTo(k, upperBound);
            if (firstKInvalid.compareTo(lastKInvalid) > 0) {
                return Optional.empty();
            }
            return Optional.of(new String[]{firstKInvalid, lastKInvalid});
        }

        private BigInteger sumBetweenKInvalids(BigInteger lowerKInvalidSegment, BigInteger upperKInvalidSegment, int k) {
            int lowerKInvalidSegmentLength = lowerKInvalidSegment.toString().length();
            int upperKInvalidSegmentLength = upperKInvalidSegment.toString().length();

            return IntStream.rangeClosed(lowerKInvalidSegmentLength, upperKInvalidSegmentLength).mapToObj(kInvalidSegmentLength -> {
                BigInteger currentLowerBound = BigInteger.TEN.pow(kInvalidSegmentLength - 1)
                        .max(lowerKInvalidSegment);
                BigInteger currentHigherBound = BigInteger.TEN.pow(kInvalidSegmentLength).subtract(BigInteger.ONE)
                        .min(upperKInvalidSegment);
                BigInteger multiplier = new BigInteger(
                        "0".repeat(kInvalidSegmentLength - 1)
                                .concat("1")
                                .repeat(k)
                );
                // m + ... + n = 1 + ... + n - (1 + ... + m-1)
                //             = n * (n+1) / 2 - (m-1) * m / 2
                BigInteger firstProduct = currentHigherBound
                        .multiply(currentHigherBound.add(BigInteger.ONE))
                        .divide(BigInteger.TWO);
                BigInteger secondProduct = currentLowerBound
                        .multiply(currentLowerBound.subtract(BigInteger.ONE))
                        .divide(BigInteger.TWO);
                return firstProduct.subtract(secondProduct).multiply(multiplier);
            }).reduce(BigInteger.ZERO, BigInteger::add);
        }

        /**
         * Part one.
         * O(size of input.txt)
         */
        public BigInteger getKInvalidRangeSum(int k) {
            Optional<String[]> kInvalidRange = getKInvalidRange(k);
            if (kInvalidRange.isEmpty()) {
                return BigInteger.ZERO;
            }
            String firstKInvalid = kInvalidRange.get()[0];
            String firstKInvalidSegment = firstKInvalid.substring(0, firstKInvalid.length() / k);
            String lastKInvalid = kInvalidRange.get()[1];
            String lastKInvalidSegment = lastKInvalid.substring(0, lastKInvalid.length() / k);
            BigInteger firstKInvalidSegmentInt = new BigInteger(firstKInvalidSegment);
            BigInteger lastKInvalidSegmentInt = new BigInteger(lastKInvalidSegment);
            return sumBetweenKInvalids(firstKInvalidSegmentInt, lastKInvalidSegmentInt, k);
        }

        /**
         * Part two.
         * O(size of input.txt)
         */
        public BigInteger getKInvalidRangeSumsOverAllK() {
            Set<Integer> primes = getAllPrimesLessOrEqualTo(upperBound.length());
            Set<Integer> primeCompositions = getAllSimplePrimeCompositions(primes);

            BigInteger optimisticSum = primes.stream()
                    .map(this::getKInvalidRangeSum)
                    .reduce(BigInteger.ZERO, BigInteger::add);
            BigInteger correction = primeCompositions.stream()
                    .filter(primeComposition -> primeComposition <= upperBound.length())
                    .map(this::getKInvalidRangeSum)
                    .reduce(BigInteger.ZERO, BigInteger::add);

            return optimisticSum.subtract(correction);
        }
    }

    @Override
    public String solvePartOne(Day02IntermediateType input) {
        BigInteger solution = BigInteger.ZERO;
        for (Pair pair : input.pairs()) {
            BigInteger rangeSum = new Range(pair).getKInvalidRangeSum(2);
            solution = solution.add(rangeSum);
        }
        return solution.toString();
    }

    @Override
    public String solvePartTwo(Day02IntermediateType input) {
        BigInteger solution = BigInteger.ZERO;
        for (Pair pair : input.pairs()) {
            BigInteger rangeSum = new Range(pair).getKInvalidRangeSumsOverAllK();
            solution = solution.add(rangeSum);
        }
        return solution.toString();
    }
}
