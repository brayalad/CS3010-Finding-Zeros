import Algorithms.*;
import Engine.Engine;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.Map;

public class polRoot {

    public static void main(String[] args) {
        final Map<RootFinderMethodType, RootFinderMethod> methodsMap = new EnumMap<>(RootFinderMethodType.class);
        methodsMap.put(RootFinderMethodType.BISECTION, new Bisection());
        methodsMap.put(RootFinderMethodType.NEWTON, new Newton());
        methodsMap.put(RootFinderMethodType.SECANT, new Secant());


        final RootFinder methods = new RootFinder(methodsMap);

        final Engine engine = new Engine(methods);
        engine.run(Arrays.asList(args));
    }
}
