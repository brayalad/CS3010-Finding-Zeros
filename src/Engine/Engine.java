package Engine;

import Algorithms.RootFinder;
import Algorithms.RootFinderMethodType;
import Models.FindRootResult;
import Models.Polynomial;
import Models.AlgorithmOptions;
import Utils.FunctionGenerator;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class Engine {
    private static final String POLYNOMIAL_FILE_EXTENSION = ".pol";
    private static final String SOLUTION_FILE_EXTENSION = ".sol";
    private static final String MAX_ITER_FLAG = "--maxit";
    private static final String HYBRID_FLAG = "--hybrid";
    private final RootFinder rootFinder;

    public Engine(final RootFinder rootFinder){
        this.rootFinder = rootFinder;
    }

    public void run(final List<String> arguments){
        try{
            final AlgorithmOptions<Double> options = parseArguments(arguments);

            final FindRootResult result = rootFinder.executeDerivativeMethod(options);

            writeToFile(result, options.getFileName().replaceAll(POLYNOMIAL_FILE_EXTENSION, SOLUTION_FILE_EXTENSION));

        } catch (RuntimeException | IOException e){
            e.printStackTrace();
        }


    }

    private void writeToFile(final FindRootResult result, final String fileName) throws IOException{
        final PrintWriter pw = new PrintWriter(new FileWriter(fileName));
        pw.println(result);
        pw.close();
    }

    @SuppressWarnings("unchecked")
    private AlgorithmOptions<Double> parseArguments(final List<String> args) throws IOException{
        final List<String> arguments = new ArrayList<>(args);

        int n = arguments.size();
        final String fileName = arguments.get(n - 1);

        final Polynomial<Double> polynomial = (Polynomial<Double>) FunctionGenerator.readFromFile(fileName);

        final AlgorithmOptions.Builder algorithmOptions = AlgorithmOptions.builder()
                                                            .withFileName(fileName)
                                                            .withPolynomial(polynomial);

        arguments.remove(fileName);

        if(arguments.contains(RootFinderMethodType.SECANT.flag())) {
            algorithmOptions.withMethodFlag(RootFinderMethodType.SECANT.flag());
            arguments.remove(RootFinderMethodType.SECANT.flag());
        } else if (arguments.contains(RootFinderMethodType.NEWTON.flag())) {
            algorithmOptions.withMethodFlag(RootFinderMethodType.NEWTON.flag());
            arguments.remove(RootFinderMethodType.NEWTON.flag());
        } else if (arguments.contains(HYBRID_FLAG)) {
            algorithmOptions.withMethodFlag(HYBRID_FLAG);
            arguments.remove(HYBRID_FLAG);
        } else {
            algorithmOptions.withMethodFlag(RootFinderMethodType.BISECTION.flag());
            arguments.remove(RootFinderMethodType.BISECTION.flag());
        }

        if(arguments.contains(MAX_ITER_FLAG)){
            final int i = arguments.indexOf(MAX_ITER_FLAG);
            final int maxIter = Integer.parseInt(arguments.get(i + 1));
            algorithmOptions.withMaxIter(maxIter);

            arguments.remove(i + 1);
            arguments.remove(MAX_ITER_FLAG);
        }

        n = arguments.size();
        if(n == 1){
            algorithmOptions.withX(Float.parseFloat(arguments.get(0)));
        }

        if(n == 2){
            algorithmOptions.withA(Float.parseFloat(arguments.get(0)));
            algorithmOptions.withB(Float.parseFloat(arguments.get(1)));
        }

        return algorithmOptions.build();
    }

}
