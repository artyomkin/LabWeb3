package com.example.demo;

import javax.enterprise.inject.Produces;
import java.io.Serializable;

public class HitChecker {

    public static boolean checkHit(DotBean dot){
        Double x = dot.getX();
        Double y = dot.getY();
        Double r = dot.getR();
        return x <= 0 && y >= 0 && x*x + y*y <= r/2 * r/2 ||
                x <= 0 && y <= 0 && x >= -r/2 && y >= -r ||
                x >= 0 && y <= 0 && x-r >= y;

    }

}
