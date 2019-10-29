package Algorithms;

import Models.FindRootResult;

import java.util.function.Function;


@FunctionalInterface
public interface RootFinderMethod {

    float EPSILON = 0.00000011920929f;

    FindRootResult findRoot(Function<Double, Double> f, Function<Double, Double> derF, double x, double a, double b, long maxIter);

    static float calculateMachineEpsilonFloat() {
        float machEps = 1.0f;
        while ((float) (1.0 + (machEps / 2.0)) != 1.0)
            machEps /= 2.0f;
        return machEps;
    }

}
