package Algorithms;

import Models.FindRootResult;
import Models.Polynomial;
import Models.AlgorithmOptions;

import java.util.Map;
import java.util.function.Function;

public class RootFinder {
    private final Map<RootFinderMethodType, RootFinderMethod> methods;


    public RootFinder(final Map<RootFinderMethodType, RootFinderMethod> methods){
        this.methods = methods;
    }

    public FindRootResult executeDerivativeMethod(final AlgorithmOptions<Double> algorithmOptions){
        final RootFinderMethodType method = algorithmOptions.getMethod();
        final Polynomial<Double> polynomial = algorithmOptions.getPolynomial();
        final double x = algorithmOptions.getX();
        final double a = algorithmOptions.getA();
        final double b = algorithmOptions.getB();
        final long maxIter = algorithmOptions.getMaxIter();

        return executeDerivativeMethod(method, polynomial, x, a, b, maxIter);
    }

    public FindRootResult executeDerivativeMethod(final RootFinderMethodType methodType, final Function<Double, Double> f, final double x, final double a, final double b, final long maxIter){
        if(methodType == RootFinderMethodType.BISECTION){
            return bisection(f, a, b, maxIter);
        } else if(methodType == RootFinderMethodType.SECANT) {
            return secant(f, a, b, maxIter);
        } else if(methodType == RootFinderMethodType.NEWTON) {
            return newton(f, x, maxIter);
        } else if(methodType == RootFinderMethodType.HYBRID) {
            return hybrid(f, a, b, maxIter);
        } else {
            throw new RuntimeException("Invalid Method.");
        }
    }

    private FindRootResult hybrid(final Function<Double, Double> f, final double a, final double b, final long maxIter){
        final long bisecIter = maxIter / 2;
        final long newtonIter = maxIter / 2;

        final FindRootResult bisecResult = bisection(f, a, b, bisecIter);

        if(bisecResult.isConverge()){
            return bisecResult;
        }

        return newton(f, bisecResult.getRoot(), newtonIter);
    }

    private FindRootResult bisection(final Function<Double, Double> polynomial, final double a, final double b, final long maxIter){
        final Bisection bisection = (Bisection) methods.get(RootFinderMethodType.BISECTION);

        return bisection.findRoot(polynomial, null, Double.MIN_VALUE, a, b, maxIter);
    }

    private FindRootResult secant(final Function<Double, Double> polynomial, final double a, final double b, final long maxIter){
        final Secant secant = (Secant) methods.get(RootFinderMethodType.SECANT);

        return secant.findRoot(polynomial, null, Double.MIN_VALUE, a, b, maxIter);
    }

    private FindRootResult newton(final Function<Double, Double> polynomial, final double x, final long maxIter){
        final Newton newton = (Newton) methods.get(RootFinderMethodType.NEWTON);

        return newton.findRoot(polynomial, Polynomial.derivative(polynomial), x, Double.MIN_VALUE, Double.MIN_VALUE, maxIter);
    }

}
