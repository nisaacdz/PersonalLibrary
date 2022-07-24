package functionalpackages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Distributions {

	public static void main(String[] args) {

	}

	public static List<Double> toList(double[] r) {
		List<Double> list = new ArrayList<>();

		for (double l : r) {
			list.add(l);
		}

		return list;
	}

	public static int[] insertionSort(int[] arr) {

		for (int i = 1; i < arr.length; i++) {
			int x = i - 1;
			int temp = arr[i];

			while (x >= 0 && arr[x] > temp) {
				arr[x + 1] = arr[x];
				x -= 1;
			}

			arr[x + 1] = temp;
		}

		return arr;
	}

	// The dynamicSort sorts the sorts both the sortable and the dynamic according
	// to
	// the sortable.
	// Both the sortable and the dynamic have the same size
	// The sort uses the quicksort algorithm
	public static int[][] dynamicSort(int[] sortable, int[] dynamic, int a, int z) {

		if (a >= z) {
			return new int[][] { sortable, dynamic };
		}

		int pivot = sortable[z];

		int pointL = a;
		int pointR = z;

		while (pointL < pointR) {
			while (sortable[pointL] <= pivot && pointL < pointR) {
				pointL++;
			}

			while (sortable[pointR] >= pivot && pointL < pointR) {
				pointR--;
			}
			swap(sortable, pointL, pointR);
			swap(dynamic, pointL, pointR);
		}

		swap(sortable, pointL, z);
		swap(dynamic, pointL, z);

		dynamicSort(sortable, dynamic, a, pointL - 1);
		dynamicSort(sortable, dynamic, pointL + 1, z);

		return new int[][] { sortable, dynamic };
	}

	private static void swap(int[] arr, int indexA, int indexB) {
		int temp = arr[indexA];
		arr[indexA] = arr[indexB];
		arr[indexB] = temp;
	}

	public static int[] quickSort(int[] arr) {

		int n = arr.length;

		int[] ans = new int[n];

		// Obviously we cannot sort a one element array
		if (n >= 1)
			return arr;

		int index = n / 2;

		int pivot = arr[index];

		int[] less = new int[0];
		int[] more = new int[0];

		for (int i = 0; i < n; i++) {
			if (i == index)
				continue;

			int value = arr[i];

			if (value <= pivot) {
				int pos = less.length;
				less = new int[pos + 1];
				less[pos] = value;
			} else {
				int pos = more.length;
				more = new int[pos + 1];
				more[pos] = value;
			}
		}

		int i = 0;

		less = quickSort(less);
		more = quickSort(more);

		for (int num : less) {
			ans[i] = num;
			i++;
		}

		ans[i] = pivot;
		i++;

		for (int num : more) {
			ans[i] = num;
			i++;
		}

		return ans;
	}

	public static boolean isAlphanumeric(char c) {
		String a = "abcdefghijklmnopqrstuvwxyz";
		String A = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		List<Object> a1 = toList(a.toCharArray());
		List<Object> A1 = toList(A.toCharArray());

		return a1.contains(c) || A1.contains(c);
	}

	public static long factorial(int Number) {
		if (Number > 1) {
			return (long) Number * factorial(Number - 1);
		}
		return 1;
	}

	public static List<Double> newtonRegression(List<Double> xValues, List<Double> yValues) {

		List<Double> mc = new ArrayList<>();
		List<Double> tempX = new ArrayList<>(xValues);
		List<Double> tempY = new ArrayList<>(yValues);

		while (tempX.size() > 3) {
			tempX = twoRecur(tempX);
			tempY = twoRecur(tempY);
		}

		double m = (tempY.get(0) - tempY.get(tempY.size() - 1)) / (tempX.get(0) - tempX.get(tempX.size() - 1));
		double c = tempY.get(0) - (m * tempX.get(0));

		mc.add(m);
		mc.add(c);

		return mc;

	}

	private static List<Double> twoRecur(List<Double> l) {
		List<Double> temp = new ArrayList<>();
		while (l.size() > 1) {
			double aX = l.remove(0);
			double bX = l.remove(0);

			double abX = (aX + bX) / 2;

			temp.add(abX);

		}
		return temp;
	}

	public static List<Double> leastSquareRegression(List<Double> xValues, List<Double> yValues) {
		int N = xValues.size();
		List<Double> mc = new ArrayList<>();

		double sumX = sumAll(xValues);
		double sumY = sumAll(yValues);

		double sumXY = 0;

		for (int i = 0; i < N; i++) {
			sumXY += xValues.get(i) * yValues.get(i);
		}

		double sumX2 = 0;
		for (int i = 0; i < N; i++) {
			sumX2 += xValues.get(i) * xValues.get(i);
		}

		double m = ((N * sumXY) - (sumX * sumY)) / ((N * sumX2) - (sumX * sumX));
		double c = (sumY - (m * sumX)) / N;

		mc.add(m);
		mc.add(c);

		return mc;
	}

	public static List<Double> regression(List<Double> xValues, List<Double> yValues) {
		int N = xValues.size();
		List<Double> mc = new ArrayList<>();

		double meanX = sumAll(xValues) / N;

		double meanY = sumAll(yValues) / N;

		List<Double> xMx = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			xMx.add(meanX - xValues.get(i));
		}

		List<Double> yMy = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			yMy.add(meanY - yValues.get(i));
		}
		double sumXX = 0;

		for (int i = 0; i < N; i++) {
			sumXX += (xMx.get(i)) * (xMx.get(i));
		}

		double sumXY = 0;

		for (int i = 0; i < N; i++) {
			sumXY += (xMx.get(i)) * (yMy.get(i));
		}

		double m = sumXY / sumXX;
		double c = meanY - (m * meanX);

		mc.add(m);
		mc.add(c);
		return mc;
	}

	private static double sumAll(List<Double> list) {
		double total = 0;
		for (double x : list) {
			total += x;
		}
		return total;
	}

	public static long permutations(int n, int r) {
		return factorial(n) / factorial(n - r);
	}

	public static long combination(int n, int r) {
		return factorial(n) / (factorial(n - r) * factorial(r));
	}

	public static List<Object> toList(char[] arr) {
		List<Object> l1 = new ArrayList<>();
		for (char s : arr) {
			l1.add(s);
		}
		return l1;
	}

	public static List<String> toList(String[] arr) {
		List<String> l1 = new ArrayList<>();
		for (String s : arr) {
			l1.add(s);
		}
		return l1;
	}

	public static List<Object> toList(Object[] arr) {
		List<Object> l1 = new ArrayList<>();
		for (Object s : arr) {
			l1.add(s);
		}
		return l1;
	}

	public static List<Integer> toList(int[] arr) {
		List<Integer> l1 = new ArrayList<>();
		for (int s : arr) {
			l1.add(s);
		}
		return l1;
	}

	public static List<String> possibleSubstrings(String s, int number) {
		int j = s.length();
		int N = j - number + 1;
		List<String> returnValue = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			String temp = s.substring(i, i + number);
			returnValue.add(temp);
		}

		return returnValue;
	}

	public static List<List<Integer>> possibleLists(List<Integer> s) {
		int j = s.size();
		List<List<Integer>> returnValue = new ArrayList<>();

		for (int number = 1; number <= s.size(); number++) {
			int N = j - number + 1;
			for (int i = 0; i < N; i++) {
				List<Integer> temp = s.subList(i, i + number);
				returnValue.add(temp);
			}
		}
		return returnValue;
	}

	private static List<List<Integer>> pairs(int n) {

		List<List<Integer>> list = new ArrayList<>();

		List<Integer> temp = new ArrayList<>();
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < (n - 1 - i); j++) {
				temp = new ArrayList<>();
				temp.add(i);
				temp.add(j + i + 1);
				list.add(temp);
			}
		}

		return list;
	}

	public static List<List<List<Integer>>> possibleTwoSegments(List<Integer> s) {
		int j = s.size();
		List<List<Integer>> value1 = new ArrayList<>();
		List<List<Integer>> value2 = new ArrayList<>();

		for (int number = 0; number <= s.size(); number++) {

			List<Integer> temp1 = s.subList(0, number);
			List<Integer> temp2 = s.subList(number, s.size());

			value1.add(temp1);
			value2.add(temp2);
		}

		List<List<List<Integer>>> returnValue = new ArrayList<>();
		returnValue.add(value1);
		returnValue.add(value2);
		return returnValue;
	}

	/*
	 * public static List<List<Object>> possibleCombinations(List<Object> list, int
	 * numberAtATime) { List<Object> sq = list; int dSize = list.size();
	 * 
	 * List<Object> dt = new ArrayList<>(); List<List<Object>> b = new
	 * ArrayList<>(); int r = numberAtATime; int start = 0; int end = dSize - 1; int
	 * index = 0;
	 * 
	 * System.out.println(sq); b = com(sq, dt, start, end, index, r, b);
	 * 
	 * return b; }
	 * 
	 * private static List<List<Object>> com(List<Object> s, List<Object> d, int
	 * start, int end, int index, int r, List<List<Object>> b) { if (index == r) {
	 * System.out.println("..."); System.out.println("index equals r... ");
	 * List<Object> temp = new ArrayList<>(r); for (int j = 0; j < r; j++) {
	 * temp.add(d.get(j)); } b.add(temp); System.out.println("b is updated : " + b);
	 * System.out.println("..."); } for (int i = start; i <= end && ((end - i + 1)
	 * >= (r - index)); i++) { d.add(index, s.get(i));
	 * System.out.println("d is updated : " + d); com(s, d, i + 1, end, index + 1,
	 * r, b); } return b; }
	 */

	public static List<List<Integer>> possibleSubsets(List<Integer> l) {
		int N = l.size();
		List<List<Integer>> returnValue = new ArrayList<>();
		for (int n = 1; n <= N; n++) {
			List<List<Integer>> temp = possibleCombinations(l, n);

			for (List<Integer> o : temp) {
				returnValue.add(o);
			}
		}

		return returnValue;

	}

	public static List<List<Integer>> possibleCombinations(List<Integer> list, int numberAtATime) {
		List<Integer> sq = list;
		int dSize = list.size();

		List<Integer> dt = new ArrayList<>();
		List<List<Integer>> b = new ArrayList<>();
		int r = numberAtATime;
		int start = 0;
		int end = dSize - 1;
		int index = 0;

		b = comA(sq, dt, start, end, index, r, b);

		return b;
	}

	private static List<List<Integer>> comA(List<Integer> s, List<Integer> d, int start, int end, int index, int r,
			List<List<Integer>> b) {
		if (index == r) {
			List<Integer> temp = new ArrayList<>(r);
			for (int j = 0; j < r; j++) {
				temp.add(d.get(j));
			}
			b.add(temp);
		}
		for (int i = start; i <= end && ((end - i + 1) >= (r - index)); i++) {
			d.add(index, s.get(i));
			comA(s, d, i + 1, end, index + 1, r, b);
		}
		return b;
	}

	public static List<Object> convertToObjectList(List<String> list) {
		List<Object> obList = new ArrayList<>();
		for (Object o : list) {
			obList.add(o);
		}
		return obList;
	}

	public static List<Integer> generateList(int size) {
		List<Integer> n = new ArrayList<>();
		for (int i = 0; i < size; i++) {
			n.add(i);
		}
		return n;
	}

	public static List<Integer> generateList(int end, int step) {
		List<Integer> n = new ArrayList<>();
		for (int i = 0; i < end; i += step) {
			n.add(i);
		}
		return n;
	}

	public static List<Integer> generateList(int start, int end, int step) {
		List<Integer> n = new ArrayList<>();
		for (int i = start; i < end; i += step) {
			n.add(i);
		}
		return n;
	}

	public static int[] generateOrderlyArray(int size) {
		int[] arr = new int[size];

		int start = 3;

		for (int i = 0; i < size; i++) {
			start += i;
			arr[i] = start;
		}

		return arr;
	}

	public static int[] generateRandomizedArray(int size, int capacity) {
		int[] arr = new int[size];

		Random rd = new Random();

		for (int i = 0; i < size; i++) {
			arr[i] = rd.nextInt(capacity);
		}

		return arr;
	}

	public static List<Integer> generateRandomList(int lowerlimit, int upperlimit, int size) {
		List<Integer> n = new ArrayList<>();
		Random r = new Random();
		for (int i = 0; i < size; i++) {
			int t = r.nextInt(upperlimit - lowerlimit);
			n.add(t + lowerlimit);
		}
		return n;
	}

	public static int binSearch(int[] nums, int target, int start, int end) {

		int n = end - start;

		if (n == 0 || (n == 1 && nums[start] != target))
			return -1;

		if (n == 1 && nums[start] == target)
			return start;

		int index = start + n / 2;

		int mid = nums[index];

		if (mid == target)
			return index;

		if (target < mid)
			return binSearch(nums, target, start, index);

		return binSearch(nums, target, index + 1, end);
	}

	// The exclusive works similar to the binary search just that it returns the
	// position of the
	// Element it is looking for if it cannot find it.
	public static int exclusiveBinSearch(int[] nums, int target, int start, int end) {

		int n = end - start;

		if (n == 0 || (n == 1 && nums[start] >= target))
			return start;

		if (n == 1 && nums[start] < target)
			return start + 1;

		int index = start + n / 2;

		int mid = nums[index];

		if (mid == target)
			return index;

		if (target < mid)
			return exclusiveBinSearch(nums, target, start, index);

		return exclusiveBinSearch(nums, target, index + 1, end);
	}

	public static int search(int[] nums, int target) {
		Arrays.sort(nums);
		return binSearch(nums, target, 0, nums.length);
	}
}
