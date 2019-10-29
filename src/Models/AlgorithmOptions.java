package Models;

import Algorithms.RootFinderMethodType;

import java.util.Map;

public class AlgorithmOptions<T extends Number> {
    private static final String TO_STRING_FORMAT = "Algorithm Options:" +
                                        "\n Method: %s" +
                                        "\n Polynomial: %s" +
                                        "\n X: %s" +
                                        "\n A: %s" +
                                        "\n B: %s" +
                                        "\n MaxIterations: %s";

    private final String fileName;
    private final RootFinderMethodType method;
    private final Polynomial<T> polynomial;
    private final double x;
    private final double a;
    private final double b;
    private final long maxIter;

    private AlgorithmOptions(
            final String fileName,
            final RootFinderMethodType method,
            final Polynomial<T> polynomial,
            final double x,
            final double a,
            final double b,
            final long maxIter
    ){
        this.fileName = fileName;
        this.method = method;
        this.polynomial = polynomial;
        this.x = x;
        this.a = a;
        this.b = b;
        this.maxIter = maxIter;
    }

    public String getFileName(){ return fileName; }

    public RootFinderMethodType getMethod(){ return method; }

    public Polynomial<T> getPolynomial(){ return polynomial; }

    public double getX(){ return x; }

    public double getA(){ return a; }

    public double getB(){ return b; }

    public long getMaxIter(){ return maxIter; }

    public static Builder builder(){
        return new Builder();
    }

    public String toString(){
        final String na = "N/A";

        final String sX = x == Double.MIN_VALUE ? na : Double.toString(x);
        final String sA = a == Double.MIN_VALUE ? na : Double.toString(a);
        final String sB = b == Double.MIN_VALUE ? na : Double.toString(b);


        return String.format(
                TO_STRING_FORMAT,
                method.toString(),
                polynomial.toString(),
                sX,
                sA,
                sB,
                Long.toString(maxIter)
        );
    }

    public static class Builder<T extends Number> {
        private String fileName;
        private String methodFlag;
        private Polynomial<T> polynomial;
        private double x;
        private double a;
        private double b;
        private long maxIter;

        private Builder(){
            this.methodFlag = "--bisec";
            this.maxIter = 10000;
            this.x = Double.MIN_VALUE;
            this.a = Double.MIN_VALUE;
            this.b = Double.MIN_VALUE;
        }

        public Builder withFileName(final String fileName){
            this.fileName = fileName;
            return this;
        }

        public Builder withMethodFlag(final String methodFlag) {
            this.methodFlag = methodFlag;
            return this;
        }

        public Builder withPolynomial(final Polynomial<T> polynomial) {
            this.polynomial = polynomial;
            return this;
        }

       public Builder withX(final double x){
            this.x = x;
            return this;
       }

        public Builder withA(final double a){
            this.a = a;
            return this;
        }

        public Builder withB(final double b){
            this.b = b;
            return this;
        }

        public Builder withMaxIter(final int maxIter){
            this.maxIter = maxIter;
            return this;
        }

        public AlgorithmOptions<T> build(){
            final Map<String, RootFinderMethodType> map = RootFinderMethodType.flagMap();

            return new AlgorithmOptions<>(this.fileName, map.get(this.methodFlag), this.polynomial, this.x, this.a, this.b, this.maxIter);
        }

    }

}
