package Models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class Polynomial<T extends Number> implements Function<T, T> {
    private final int degree;
    private final List<T> coefficients;

    public Polynomial(final int degree, final List<T> coefficients){
        this.degree = degree;
        final List<T> coeffs = new ArrayList<>(coefficients);
        Collections.reverse(coeffs);
        this.coefficients = List.copyOf(coeffs);
    }


    @Override
    @SuppressWarnings("unchecked")
    public T apply(final T x) {
        int n = coefficients.size();
        Double result = (Double) coefficients.get(0);
        for(int i = 1; i <= degree && i < n; ++i){
            result += ((Double)coefficients.get(i) * Math.pow((Double) x, i));
        }

        return (T)  result;
    }

    public int getDegree(){
        return degree;
    }

    public List<T> getCoefficients(){
        return new ArrayList<>(coefficients);
    }

    public Polynomial getDerivative(){
        return derivative(this);
    }

    @Override
    @SuppressWarnings("unchecked")
    public String toString(){
        final StringBuilder sb = new StringBuilder();
        for(int i = degree; i >= 1; --i){
            if((Double) coefficients.get(i) != 0) {
                sb.append(coefficients.get(i)).append("x^").append(i);

                if((Double) coefficients.get(i - 1) > 0){
                    sb.append(" + ");
                } else {
                    sb.append(" - ");
                }
            }
        }

        final T c = (T)((Double) Math.abs((Double) coefficients.get(0)));
        sb.append(c);

        return sb.toString();
    }

    public static <T extends Number> Polynomial<T> derivative(final Function<T,T> function){
        if(function instanceof Polynomial){
            return derivative((Polynomial<T>) function);
        } else {
            throw new RuntimeException("Function is not a polynomial");
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Number> Polynomial<T> derivative(final Polynomial<T> original){
        final int degree = original.getDegree();
        final List<Double> coefficients = (List<Double>) original.getCoefficients();
        for(int i = 1; i <= degree; ++i){
            coefficients.set(i, (coefficients.get(i) * i));
        }

        final List<T> copy = (List<T>) coefficients.subList(1, coefficients.size());

        Collections.reverse(copy);

        return new Polynomial<>((degree - 1), copy);
    }

}
