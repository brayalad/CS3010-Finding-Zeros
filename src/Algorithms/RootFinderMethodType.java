package Algorithms;

import java.util.HashMap;
import java.util.Map;

public enum RootFinderMethodType {

    BISECTION("Bisection", "--bisec"),
    NEWTON("Newton", "--newt"),
    SECANT("Secant", "--sec"),
    HYBRID("Hybrid", "--hybrid");

    private final String type;
    private final String flag;

    RootFinderMethodType(final String type, final String flag){
        this.type = type;
        this.flag = flag;
    }

    public String type(){ return type; }

    public String flag(){ return flag; }

    public static Map<String, RootFinderMethodType> flagMap(){
        final Map<String, RootFinderMethodType> map = new HashMap<>();
        for(final RootFinderMethodType type : RootFinderMethodType.values()){
            map.put(type.flag(), type);
        }

        return map;
    }

}
