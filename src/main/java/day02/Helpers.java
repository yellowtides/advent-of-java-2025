package day02;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Helpers {

    public static Set<Integer> getAllPrimesLessOrEqualTo(int upperBound) {
        Set<Integer> isNotPrime = new HashSet<>();
        Set<Integer> primes = new HashSet<>();
        for (int primeCandidate = 2; primeCandidate <= upperBound; primeCandidate++) {
            if (isNotPrime.contains(primeCandidate)) {
                continue;
            }
            primes.add(primeCandidate);
            for (int marker = primeCandidate * 2; marker <= upperBound; marker += primeCandidate) {
                isNotPrime.add(marker);
            }
        }
        return primes;
    }

    public static Set<Integer> getAllSimplePrimeCompositions(Set<Integer> primes) {
        return primes.stream().flatMap(pOne ->
            primes.stream().filter(pTwo -> !pOne.equals(pTwo)).flatMap(pTwo ->
                Stream.of(pOne * pTwo)
            )
        ).collect(Collectors.toSet());
    }
}
