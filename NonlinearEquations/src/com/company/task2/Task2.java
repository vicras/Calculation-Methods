package com.company.task2;

import com.company.Function;
import com.company.ThreeFunction;

import java.util.Arrays;
import java.util.List;

public class Task2 {


    ThreeFunction[] list = {
            (x1, x2, x3) -> x1 * x1 + x2 * x2 + x3 * x3 - 9,
            (x1, x2, x3) -> x1 + x2 - x3 - 2,
            (x1, x2, x3) -> x1 * x1 + (x2 - 2) * (x2 - 2) + 2 * x3 * x3 - 9
    };

    ThreeFunction[][] yakobi = {
            {(x1, x2, x3) -> 2* x1 ,
                    (x1, x2, x3) ->  2* x2,
                    (x1, x2, x3) ->  2 * x3 },
            {(x1, x2, x3) -> 1 ,
                    (x1, x2, x3) -> 1,
                    (x1, x2, x3) ->  - 1 },
            {(x1, x2, x3) -> 2 * x1 ,
                    (x1, x2, x3) -> 2 * (x2) -4 ,
                    (x1, x2, x3) -> 4 * x3 }
    };


    private final double exactness = Math.pow(10, -10);


    public void solve(){
        NewtonMethod newtonMethod = new NewtonMethod(list,yakobi,exactness, new GauseSolver());

        double[] solve1 = newtonMethod.solve((new double[]{-4, 4, -4}));
        System.out.println("solve1 = " + Arrays.toString(solve1));
        double[] solve2 = newtonMethod.solve(new double[]{4, 2, 4});
        System.out.println("solve2 = " + Arrays.toString(solve2));
    }
}
