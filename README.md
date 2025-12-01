# Advent of Java 2025

My Java solutions to [Advent of Code 2025](https://adventofcode.com/2025/), a yearly programming challenge.

## Running
This project uses [Gradle](https://gradle.org/). From the project root,
1. `./gradlew build`.
2. `./gradlew run`.

You will be prompted to select a day. Select the one you wish to see the answers for.

## Developing
To add a new day (let's say, 42):
1. Create a new aptly-named package under `/src/main/java`.
   * e.g. `/src/main/java/day_42`. 
   * **Note:** the package **must** have the name `day_NN`, or the implementation will be unable to parse the given input.
2. Under the package, write your input to a new `input.txt`.
3. Under the package, invent an intermediate type to parse the input file to.
4. Under the package, add implementations of:
   * `AbstractParser`, responsible for parsing `input.txt` to your intermediate type.
   * `AbstractSolver`, responsible for transforming the intermediate type to solutions.
   * See `/src/main/java/day_01` for example implementations.
5. Finally, join your implementations by instantiating them inside of the `COMPOSERS` list in `/src/main/java/main/Main.java`.
