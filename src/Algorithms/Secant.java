package Algorithms;

import Models.FindRootResult;

import java.util.function.Function;

public class Secant extends AbstractRootFinderMethod implements RootFinderMethod {

    @Override
    public FindRootResult findRoot(final Function<Double,Double> f, final Function<Double,Double> derF, final double x, final double a, final double b, final long maxIter) {
        return secant(f, a, b, maxIter);
    }

    private FindRootResult secant(final Function<Double, Double> f, double a, double b, final long maxIter){
        double fa = f.apply(a);
        double fb = f.apply(b);

        if(Math.abs(fa) > Math.abs(fb)){
            double temp = a;
            a = b;
            b = temp;

            double fTemp = fa;
            fa = fb;
            fb = fTemp;
        }

        long it = 0;
        for(; it < maxIter; ++it){
            if(Math.abs(fa) > Math.abs(fb)){
                double temp = a;
                a = b;
                b = temp;

                double fTemp = fa;
                fa = fb;
                fb = fTemp;
            }

            double d = ((b - a) / (fb - fa));
            b = a;
            fb = fa;
            d *= fa;

            if(Math.abs(d) < EPSILON){
                System.out.println(String.format(CONVERGING_MESSAGE_FORMAT, it));
                System.out.println(generateResult(a, it, true));

                return FindRootResult.generate(a, it, true);
            }

            a -= d;
            fa = f.apply(a);
        }

        System.out.println(MAX_ITERATIONS_MESSAGE);
        System.out.println(generateResult(a, it, false));


        return FindRootResult.generate(a, it, false);
    }

}
