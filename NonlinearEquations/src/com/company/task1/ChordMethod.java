package com.company.task1;

import com.company.Function;
import com.company.Parameters;
import com.company.SolvingMethod;
import com.company.StatisticCollector;

import java.util.ArrayList;
import java.util.List;

public class ChordMethod implements SolvingMethod {
    Function func;
    double exactness;

    public ChordMethod(Function func, double exactness) {
        this.func = func;
        this.exactness = exactness;
    }

    @Override
    public double solve(double a, double b) {
        var name = "chord_a="+a+"_b="+b;
        List<Parameters> statist = new ArrayList<>();
        double x = b;
        double t;
        for (int i = 0; (t = Math.abs(func.getValue(x))) > exactness; i++) {
            statist.add(new Parameters(i, t));
            x = x - func.getValue(x) * (x - a)/ (func.getValue(x)- func.getValue(a));
        }

        StatisticCollector.collect(name, statist);
        return x;
    }
}
