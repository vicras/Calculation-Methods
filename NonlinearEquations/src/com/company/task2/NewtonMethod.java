package com.company.task2;

import com.company.Parameters;
import com.company.StatisticCollector;
import com.company.ThreeFunction;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NewtonMethod  {

    ThreeFunction[] func;
    ThreeFunction[][] yakobi;
    final double exactness;
    SLAESolver solver;

    public NewtonMethod(ThreeFunction[] func, ThreeFunction[][] yakobi, double exactness, SLAESolver solver) {
        this.func = func;
        this.yakobi = yakobi;
        this.exactness = exactness;
        this.solver = solver;
    }

    public double[] solve(double[] x0) {
        var name = "newton_x0="+ Arrays.toString(x0);
        List<Parameters> statist = new ArrayList<>();
        var ans =x0;
        var prevDisp=1.;
        var disp = 1.;
        for(int i=0;(disp  = getDiscrepancy(countVector(func, ans)))>exactness; i++){
//            if(disp>prevDisp){
//                throw new RuntimeException("bad discrepancy disc " + disp+" discr prec: "+ prevDisp+" sub "+ (disp-prevDisp));
//            }
            statist.add(new Parameters(i, disp));
            prevDisp = disp;

            var a = countMatrix(yakobi, ans);
            var b = changeSign(countVector(func, ans));
            var diff=solver.solve(a,b);
            ans = arraysSum(ans, diff);
        }
        StatisticCollector.collect(name, statist);
        return ans;
    }

    private double[] changeSign(double[] ans){
        return Arrays.stream(ans).map(e->-e).toArray();
    }
    private double[][] countMatrix(ThreeFunction[][] function, double[] variable){
        var ans = new double[function.length][function[0].length];
        for (int i = 0; i < function.length; i++) {
            for (int j = 0; j < function.length; j++) {
                ans[i][j] = function[i][j].getValue(variable[0], variable[1], variable[2]);
            }
        }
        return ans;
    }

    private double[] countVector(ThreeFunction[] function, double[] variable){
        var ans = new double[function.length];
        for (int i = 0; i < function.length; i++) {
                ans[i] = function[i].getValue(variable[0], variable[1], variable[2]);
        }
        return ans;
    }

    private double[] arraysSum(double[] a, double[] b){
        var ans = new double[a.length];

        for (int i = 0; i < a.length; i++) {
            ans[i] = a[i] + b[i];
        }
        return ans;
    }

    private double getDiscrepancy(double[] ans) {
        return Math.sqrt(Arrays.stream(ans).map((e)->e*e).sum());
    }

}
