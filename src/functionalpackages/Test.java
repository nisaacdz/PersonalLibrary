package functionalpackages;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

class Result {

	/*
	 * Complete the 'superDigit' function below.
	 *
	 * The function is expected to return an INTEGER. The function accepts following
	 * parameters: 1. STRING n 2. INTEGER k
	 */

	public static int superDigit(String n, int k) {
		// Write your code here
		int l = n.length();
		int length = l * k;

		if (length == 1)
			return Integer.parseInt(n);

		BigInteger sum = BigInteger.ZERO;

		for (int i = 0; i < l; i++) {
			char x = n.charAt(i);
			Long ans = Long.parseLong(String.valueOf(x)) * k;
			sum = sum.add(new BigInteger(String.valueOf(ans)));
		}

		return superDigit(String.valueOf(sum), 1);
	}

}

public class Test {
	public static void main(String[] args) throws IOException {
		int[] arr = { 0, 1, 2, 1, 2, 3 };
		System.out.println(longestSubarray(Distributions.toList(arr)));
	}

	public static int longestSubarray(List<Integer> arr) {
		// Write your code here

		int ans = 0;

		int prev = Integer.MAX_VALUE;

		int n = arr.size();

		for (int i = 0; i < n; i++) {

			int value = arr.get(i);

			if (i > 0 && prev == value)
				continue;

			prev = value;

			int countF = 1;
			boolean assigned = false;
			boolean sign = false;

			for (int j = i + 1; j < n; j++) {
				int test = arr.get(j);

				if (Math.abs(test - value) > 1) {
					break;
				} else {
					if (!assigned) {
						assigned = test > value || test < value;
						countF++;

						if (assigned)
							sign = test > value;
					} else {
						if (test == value) {
							countF++;
						} else if (test > value == sign) {
							countF++;
						} else {
							break;
						}
					}

				}
			}
			ans = countF > ans ? countF : ans;
		}

		return ans;
	}
}
