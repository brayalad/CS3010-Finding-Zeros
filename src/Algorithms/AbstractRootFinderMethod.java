package Algorithms;

abstract class AbstractRootFinderMethod {
    private static final String SUCCESS = "success";
    private static final String FAIL = "failed";
    private static final String RESULTS_FORMAT = "%f %d %s";
    static final String SMALL_SLOPE_MESSAGE = "Small slope!";
    static final String INADEQUATE_VALUES_MESSAGE = "Inadequate values for a and b.";
    static final String CONVERGING_MESSAGE_FORMAT = "Algorithm has converged after %d iterations!";
    static final String MAX_ITERATIONS_MESSAGE = "Max iterations reached without convergence...";


    String generateResult(double root, long iterations, boolean converge){
        if(converge){
            return String.format(RESULTS_FORMAT, root, iterations, SUCCESS);
        } else {
            return String.format(RESULTS_FORMAT, root, iterations, FAIL);
        }
    }

}
