package Algorithms;

import Models.FindRootResult;

import java.util.function.Function;

public class Newton extends AbstractRootFinderMethod implements RootFinderMethod {

    @Override
    public FindRootResult findRoot(final Function<Double,Double> f, final Function<Double,Double> derF, final double x, final double a, final double b, final long maxIter) {
        return newton(f, derF, x, maxIter,(f.apply(x) / derF.apply(x)));
    }

    private FindRootResult newton(final Function<Double, Double> f, final Function<Double,Double> derF, double x, final long maxIter, final double delta){
        double fx = f.apply(x);

        long it = 0;
        for(; it < maxIter; ++it){
            double fd = derF.apply(x);

            if(Math.abs(fd) < delta){
                System.out.println(SMALL_SLOPE_MESSAGE);
                System.out.println(generateResult(x, 0, false));

                return FindRootResult.generate(x, 0, false);
            }

            double d = (fx / fd);
            x -= d;
            fx = f.apply(x);

            if(Math.abs(d) < EPSILON){
                System.out.println(String.format(CONVERGING_MESSAGE_FORMAT, it));
                System.out.println(generateResult(x, it, true));

                return FindRootResult.generate(x, it, true);
            }

        }

        System.out.println(MAX_ITERATIONS_MESSAGE);
        System.out.println(generateResult(x, it, false));


        return FindRootResult.generate(x, it, false);
    }

}
