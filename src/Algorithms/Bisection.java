package Algorithms;

import Models.FindRootResult;

import java.util.function.Function;

public class Bisection extends AbstractRootFinderMethod implements RootFinderMethod {

    @Override
    public FindRootResult findRoot(final Function<Double,Double> f, final Function<Double,Double> derF, final double x, final double a, final double b, final long maxIter) {
        return bisection(f, a, b, maxIter);
    }

    private FindRootResult bisection(final Function<Double, Double> f, double a, double b, final long maxIter){
        double fa = f.apply(a);
        double fb = f.apply(b);

        if((fa * fb) >= 0.0){
            System.out.println(INADEQUATE_VALUES_MESSAGE);
            System.out.println(generateResult(-1.0, 0, false));

            return FindRootResult.generate(-1.0, 0, false);
        }

        double c = 0.0;
        double error = b - a;
        long it = 0;
        for(; it < maxIter; ++it){
            error /= 2.0;
            c = a + error;
            double fc = f.apply(c);

            if(Math.abs(error) < EPSILON || fc == 0.0){
                System.out.println(String.format(CONVERGING_MESSAGE_FORMAT, it));
                System.out.println(generateResult(c, it, true));

                return FindRootResult.generate(c, it, true);
            }

            if((fa * fc) < 0.0){
                b = c;
                fb = fc;
            } else {
                a = c;
                fa = fc;
            }
        }

        System.out.println(MAX_ITERATIONS_MESSAGE);
        System.out.println(generateResult(c, it, false));

        return FindRootResult.generate(c, it, false);
    }

}
