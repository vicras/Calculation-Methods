package com.company.task1;

import com.company.Function;
import com.company.Parameters;
import com.company.SolvingMethod;
import com.company.StatisticCollector;


import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;

public class BisectionMethod implements SolvingMethod {
    Function func;
    double exactness;

    public BisectionMethod(Function func, double exactness) {
        this.func = func;
        this.exactness = exactness;
    }

    @Override
    public double solve(double a, double b) {
        var name = "bisection_a="+a+"_b="+b;
        List<Parameters> statist = new ArrayList<>();
        double x = a;
        double t=0;
        for (int i=0;(t = (b - a)) > exactness * 2; i++) {
            statist.add(new Parameters(i,t));
            x = (a + b) / 2;
            if ((func.getValue(a) * func.getValue(x)) < 0) {
                b = x;
            } else {
                a = x;
            }
        }
        StatisticCollector.collect(name,statist);
        return x;
    }

}
