package Models;

public class FindRootResult {
    private static final String RESULTS_FORMAT = "%f %d %s";
    private final double root;
    private final long iterations;
    private final boolean converge;

    private FindRootResult(final double root, final long iterations, final boolean converge){
        this.root = root;
        this.iterations = iterations;
        this.converge = converge;
    }

    public double getRoot(){ return root; }

    public long getIterations(){ return iterations; }

    public boolean isConverge(){ return converge; }

    @Override
    public String toString(){
        if(converge){
            return String.format(RESULTS_FORMAT, root, iterations, "success");
        } else {
            return String.format(RESULTS_FORMAT, root, iterations, "failed");
        }
    }

    public static FindRootResult generate(final double root, final long iterations, final boolean converge){
        return new FindRootResult(root, iterations, converge);
    }

}
