package Utils;

import Models.Polynomial;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class FunctionGenerator {
    private static final String DELIMITER = " ";


    private FunctionGenerator(){}

    public static Function<Double, Double> readFromFile(final String fileName) throws IOException {
        final Path filePath = Paths.get(fileName);
        final LinkedList<String> linesInFile = new LinkedList<>(Files.readAllLines(filePath));

        final int degree = Integer.parseInt(linesInFile.getFirst());
        final List<Double> coefficients = Arrays.stream(linesInFile.getLast().split(DELIMITER))
                                                .filter(Objects::nonNull)
                                                .filter(s -> !s.isEmpty())
                                                .map(Double::parseDouble)
                                                .collect(Collectors.toList());

        return new Polynomial<>(degree, coefficients);
    }

}
