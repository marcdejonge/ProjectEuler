package euler.level1;

import euler.Problem;

public class Problem017 extends Problem<Integer> {
	@Override
	public Integer solve() {
		int onetonine = "onetwothreefourfivesixseveneightnine".length();
		int tentonineteen = "teneleventwelvethirteenfourteenfifteensixteenseventeeneighteennineteen".length();
		int and = "and".length();
		int twentytoninety = "twentythirtyfortyfiftysixtyseventyeightyninety".length();
		int hundred = "hundred".length();
		int thousand = "thousand".length();
		return "one".length() + thousand + 900 * hundred + 100 * onetonine + 100 * twentytoninety
				+ 891 * and + 80 * onetonine + 10 * (onetonine + tentonineteen);
	}
}
