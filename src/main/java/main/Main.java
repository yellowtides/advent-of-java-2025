package main;

import common.composer.Composer;
import common.types.Day;
import common.types.Solution;
import day01.Day01Parser;
import day01.Day01Solver;
import day02.Day02Parser;
import day02.Day02Solver;
import jdk.jshell.spi.ExecutionControl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


public class Main {

    /**
     * Add your composer here after implementing a day.
     */
    private static final List<Composer<?>> COMPOSERS = List.of(
        new Composer<>(Day.DAY_01, new Day01Parser(), new Day01Solver()),
        new Composer<>(Day.DAY_02, new Day02Parser(), new Day02Solver())
    );

    private static Composer<?> composerFor(Day day) throws ExecutionControl.NotImplementedException {
        Optional<Composer<?>> maybeComposer = COMPOSERS.stream().filter(composer -> composer.day().equals(day)).findFirst();
        if (maybeComposer.isEmpty()) {
            throw new ExecutionControl.NotImplementedException("Composer for day " + day.ordinal + " is not implemented.");
        }
        return maybeComposer.get();
    }

    public static void main(String[] args) throws ExecutionControl.NotImplementedException, IOException {
        Optional<Day> maybeSelectedDay = Optional.empty();

        while (maybeSelectedDay.isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Select day:");
            int ordinal = scanner.nextInt();
            maybeSelectedDay = Day.valueOf(ordinal);
            if (maybeSelectedDay.isEmpty()) {
                System.out.println("Day " + ordinal + " not implemented. Select another day.");
            }
        }

        Day selectedDay = maybeSelectedDay.get();
        Composer<?> composer;

        // throws ExecutionControl.NotImplementedException
        composer = composerFor(selectedDay);
        // throws IOException
        Solution solution = composer.parseAndSolve();

        System.out.println(solution);
    }
}