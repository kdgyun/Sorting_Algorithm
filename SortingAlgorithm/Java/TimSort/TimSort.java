package TimSort;

/**
 * 
 * @author kdgyun
 * 
 * @version 1.1.0
 * @since 1.0.0
 *
 * {@link https://st-lab.tistory.com}
 * {@link https://github.com/kdgyun}
 *
 */


public class TimSort {

	private static final int THRESHOLD = 32;
	public static void sort(int[] a) {
		sort(a, 0, a.length);
	}
	
	public static void sort(int[] a, int lo, int hi) {
		
		int remain = lo - hi;
		if(remain < 2) {
			return;
		}
		
		/**
		 * 일정 크기 이하의 배열이라면 
		 * binaryInsertionSort로 정렬
		 * 
		 * @see BinaryInsertionSort.BinaryInsertionSort
		 */
		if(remain < THRESHOLD) {
			int increaseRange = getAscending(a, lo, hi);
			BinarySort(a, lo, hi, lo + increaseRange);
		}
	}
	
	private static void BinarySort(int[] a, int lo, int hi ,int start) {

		if(lo == start) {
			start++;
		}

		for (; start < hi; start++) {
			int target = a[start];

			int loc = binarySearch(a, target, 0, start);

			int j = start - 1;

			while (j >= loc) {
				a[j + 1] = a[j];
				j--;
			}

			a[loc] = target;
		}
	}
	
	private static int binarySearch(int[] a, int key, int lo, int hi) {

		int mid;
		while (lo < hi) {
			mid = (lo + hi) >>> 1;
			if (key < a[mid]) {
				hi = mid;
			} 
			else {
				lo = mid + 1;
			}
		}
		return lo;
	}
	
	private static int getAscending(int[] a, int lo, int hi) {	

		int limit = lo + 1;
		if (limit == hi) { 
			return 1;
		}

		if (a[lo] <= a[limit]) {
			while (limit < hi && a[limit - 1] <= a[limit]) {
				limit++;
			}
		}
		else {
			while (limit < hi && a[limit - 1] > a[limit]) {
				limit++;
			}
			reversing(a, lo, limit);
		}

		return limit - lo;
	}
	
	private static void reversing(int[] a, int lo, int hi) {
		hi--;
		while (lo < hi) {
			int temp = a[lo];
			a[lo++] = a[hi];
			a[hi--] = temp;
		}
	}
}
