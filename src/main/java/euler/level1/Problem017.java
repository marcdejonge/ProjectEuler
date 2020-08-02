package euler.level1;

import euler.IntegerProblem;

public class Problem017 extends IntegerProblem {
    @Override
    public long solve() {
        final int onetonine = "onetwothreefourfivesixseveneightnine".length();
        final int tentonineteen = "teneleventwelvethirteenfourteenfifteensixteenseventeeneighteennineteen".length();
        final int and = "and".length();
        final int twentytoninety = "twentythirtyfortyfiftysixtyseventyeightyninety".length();
        final int hundred = "hundred".length();
        final int thousand = "thousand".length();
        return "one".length() + thousand
               + 900
               * hundred
               + 100
               * onetonine
               + 100
               * twentytoninety
               + 891
               * and
               + 80
               * onetonine
               + 10
               * (onetonine + tentonineteen);
    }
}
