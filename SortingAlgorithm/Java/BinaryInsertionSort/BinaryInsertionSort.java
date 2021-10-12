package BinaryInsertionSort;

import java.util.Comparator;

import Utils.Convert;
import Utils.Order;

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

public class BinaryInsertionSort {

	

	// ============= primitive type ============//

	/*
	 * ========================== 
	 *  sorting byte type array
	 * ==========================
	 */
	
	public static void sort(byte[] a) {
		if(a.length < 2) {
			return;
		}
		int incLength = getAscending(a, 0, a.length);
		sort(a, 0, a.length, incLength);
	}


	private static void sort(byte[] a, int lo, int hi ,int start) {

		if(lo == start) {
			start++;
		}

		for (; start < hi; start++) {
			byte target = a[start];

			int loc = binarySearch(a, target, 0, start);

			int j = start - 1;

			while (j >= loc) {
				a[j + 1] = a[j];
				j--;
			}

			a[loc] = target;
		}
	}

	private static int binarySearch(byte[] a, byte key, int lo, int hi) {

		int mid;
		while (lo < hi) {
			mid = lo + ((hi - lo) >>> 1);
			if (key < a[mid]) {
				hi = mid;
			} 
			else {
				lo = mid + 1;
			}
		}
		return lo;
	}

	private static int getAscending(byte[] a, int lo, int hi) {	

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
	
	private static void reversing(byte[] a, int lo, int hi) {
		hi--;
		while (lo < hi) {
			byte temp = a[lo];
			a[lo++] = a[hi];
			a[hi--] = temp;
		}
	}
	
	
	
	
	
	/*
	 * ========================== 
	 *  sorting char type array
	 * ==========================
	 */
	
	public static void sort(char[] a) {
		if(a.length < 2) {
			return;
		}
		int incLength = getAscending(a, 0, a.length);
		sort(a, 0, a.length, incLength);
	}


	private static void sort(char[] a, int lo, int hi ,int start) {

		if(lo == start) {
			start++;
		}

		for (; start < hi; start++) {
			char target = a[start];

			int loc = binarySearch(a, target, 0, start);

			int j = start - 1;

			while (j >= loc) {
				a[j + 1] = a[j];
				j--;
			}

			a[loc] = target;
		}
	}

	private static int binarySearch(char[] a, char key, int lo, int hi) {

		int mid;
		while (lo < hi) {
			mid = lo + ((hi - lo) >>> 1);
			if (key < a[mid]) {
				hi = mid;
			} 
			else {
				lo = mid + 1;
			}
		}
		return lo;
	}

	private static int getAscending(char[] a, int lo, int hi) {	

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
	
	private static void reversing(char[] a, int lo, int hi) {
		hi--;
		while (lo < hi) {
			char temp = a[lo];
			a[lo++] = a[hi];
			a[hi--] = temp;
		}
	}
	
	
	
	
	
	
	/*
	 * ========================== 
	 *  sorting short type array
	 * ==========================
	 */
	
	public static void sort(short[] a) {
		if(a.length < 2) {
			return;
		}
		int incLength = getAscending(a, 0, a.length);
		sort(a, 0, a.length, incLength);
	}


	private static void sort(short[] a, int lo, int hi ,int start) {

		if(lo == start) {
			start++;
		}

		for (; start < hi; start++) {
			short target = a[start];

			int loc = binarySearch(a, target, 0, start);

			int j = start - 1;

			while (j >= loc) {
				a[j + 1] = a[j];
				j--;
			}

			a[loc] = target;
		}
	}

	private static int binarySearch(short[] a, short key, int lo, int hi) {

		int mid;
		while (lo < hi) {
			mid = lo + ((hi - lo) >>> 1);
			if (key < a[mid]) {
				hi = mid;
			} 
			else {
				lo = mid + 1;
			}
		}
		return lo;
	}

	private static int getAscending(short[] a, int lo, int hi) {	

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
	
	private static void reversing(short[] a, int lo, int hi) {
		hi--;
		while (lo < hi) {
			short temp = a[lo];
			a[lo++] = a[hi];
			a[hi--] = temp;
		}
	}
	
	
	
	
	
	/*
	 * ========================== 
	 *  sorting int type array
	 * ==========================
	 */
	
	public static void sort(int[] a) {
		if(a.length < 2) {
			return;
		}
		int incLength = getAscending(a, 0, a.length);
		sort(a, 0, a.length, incLength);
	}


	private static void sort(int[] a, int lo, int hi ,int start) {

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
			mid = lo + ((hi - lo) >>> 1);
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
	
	
	
	
	
	
	/*
	 * ========================== 
	 *  sorting long type array
	 * ==========================
	 */
	
	public static void sort(long[] a) {
		if(a.length < 2) {
			return;
		}
		int incLength = getAscending(a, 0, a.length);
		sort(a, 0, a.length, incLength);
	}


	private static void sort(long[] a, int lo, int hi ,int start) {

		if(lo == start) {
			start++;
		}

		for (; start < hi; start++) {
			long target = a[start];

			int loc = binarySearch(a, target, 0, start);

			int j = start - 1;

			while (j >= loc) {
				a[j + 1] = a[j];
				j--;
			}

			a[loc] = target;
		}
	}

	private static int binarySearch(long[] a, long key, int lo, int hi) {

		int mid;
		while (lo < hi) {
			mid = lo + ((hi - lo) >>> 1);
			if (key < a[mid]) {
				hi = mid;
			} 
			else {
				lo = mid + 1;
			}
		}
		return lo;
	}

	private static int getAscending(long[] a, int lo, int hi) {	

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
	
	private static void reversing(long[] a, int lo, int hi) {
		hi--;
		while (lo < hi) {
			long temp = a[lo];
			a[lo++] = a[hi];
			a[hi--] = temp;
		}
	}
	
	
	
	
	
	/*
	 * ========================== 
	 *  sorting float type array
	 * ==========================
	 */
	
	public static void sort(float[] a) {
		if(a.length < 2) {
			return;
		}
		int incLength = getAscending(a, 0, a.length);
		sort(a, 0, a.length, incLength);
	}


	private static void sort(float[] a, int lo, int hi ,int start) {

		if(lo == start) {
			start++;
		}

		for (; start < hi; start++) {
			float target = a[start];

			int loc = binarySearch(a, target, 0, start);

			int j = start - 1;

			while (j >= loc) {
				a[j + 1] = a[j];
				j--;
			}

			a[loc] = target;
		}
	}

	private static int binarySearch(float[] a, float key, int lo, int hi) {

		int mid;
		while (lo < hi) {
			mid = lo + ((hi - lo) >>> 1);
			if (key < a[mid]) {
				hi = mid;
			} 
			else {
				lo = mid + 1;
			}
		}
		return lo;
	}

	private static int getAscending(float[] a, int lo, int hi) {	

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
	
	private static void reversing(float[] a, int lo, int hi) {
		hi--;
		while (lo < hi) {
			float temp = a[lo];
			a[lo++] = a[hi];
			a[hi--] = temp;
		}
	}
	
	
	
	
	
	
	/*
	 * ========================== 
	 *  sorting double type array
	 * ==========================
	 */
	
	public static void sort(double[] a) {
		if(a.length < 2) {
			return;
		}
		int incLength = getAscending(a, 0, a.length);
		sort(a, 0, a.length, incLength);
	}


	private static void sort(double[] a, int lo, int hi ,int start) {

		if(lo == start) {
			start++;
		}

		for (; start < hi; start++) {
			double target = a[start];

			int loc = binarySearch(a, target, 0, start);

			int j = start - 1;

			while (j >= loc) {
				a[j + 1] = a[j];
				j--;
			}

			a[loc] = target;
		}
	}

	private static int binarySearch(double[] a, double key, int lo, int hi) {

		int mid;
		while (lo < hi) {
			mid = lo + ((hi - lo) >>> 1);
			if (key < a[mid]) {
				hi = mid;
			} 
			else {
				lo = mid + 1;
			}
		}
		return lo;
	}

	private static int getAscending(double[] a, int lo, int hi) {	

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
	
	private static void reversing(double[] a, int lo, int hi) {
		hi--;
		while (lo < hi) {
			double temp = a[lo];
			a[lo++] = a[hi];
			a[hi--] = temp;
		}
	}
	
	
	
	
	/*==========================
	 * sorting Object type array 
	 *==========================*/
	
	public static <T> void sort(T[] a, Comparator<? super T> c) {
		
		if(c == null) {
			sort(a);
		}
		else {
			if(a.length < 2) {
				return;
			}
			int incLength = getAscending(a, 0, a.length, c);
			ComparatorSort(a, 0, a.length, incLength, c);
			
		}
	}
	
	// Comparable sort
	public static void sort(Object[] a) {
		if(a.length < 2) {
			return;
		}
		int incLength = getAscending(a, 0, a.length);
		ComparableSort(a, 0, a.length, incLength);
	}
	
	private static void ComparableSort(Object[] a, int lo, int hi, int start) {
		if(lo == start) {
			start++;
		}

		for (; start < hi; start++) {
			@SuppressWarnings("rawtypes")
			Comparable target = (Comparable)a[start];

			int loc = binarySearch(a, target, 0, start);

			int j = start - 1;

			while (j >= loc) {
				a[j + 1] = a[j];
				j--;
			}

			a[loc] = target;
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static int binarySearch(Object[] a, Comparable key, int lo, int hi) {

		int mid;
		while (lo < hi) {
			mid = lo + ((hi - lo) >>> 1);
			if (key.compareTo(a[mid]) < 0) {
				hi = mid;
			} 
			else {
				lo = mid + 1;
			}
		}
		return lo;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static int getAscending(Object[] a, int lo, int hi) {	

		int limit = lo + 1;
		if (limit == hi) { 
			return 1;
		}

		if (((Comparable)a[lo]).compareTo(a[limit]) <= 0) {
			while (limit < hi && ((Comparable)a[limit - 1]).compareTo(a[limit]) <= 0) {
				limit++;
			}
		}
		else {
			while (limit < hi && ((Comparable)a[limit - 1]).compareTo(a[limit]) > 0) {
				limit++;
			}
			reversing(a, lo, limit);
		}

		return limit - lo;
	}
	

    
    
    // Comparator sort
    private static <T> void ComparatorSort(T[] a, int lo, int hi, int start, Comparator<? super T> c) {
		if(lo == start) {
			start++;
		}

		for (; start < hi; start++) {
			T target = a[start];

			int loc = binarySearch(a, target, 0, start, c);

			int j = start - 1;

			while (j >= loc) {
				a[j + 1] = a[j];
				j--;
			}

			a[loc] = target;
		}
	}

	private static <T> int binarySearch(T[] a, T key, int lo, int hi, Comparator<? super T> c) {

		int mid;
		while (lo < hi) {
			mid = lo + ((hi - lo) >>> 1);
			if (c.compare(key, a[mid]) < 0) {
				hi = mid;
			} 
			else {
				lo = mid + 1;
			}
		}
		return lo;
	}
	
	private static <T> int getAscending(T[] a, int lo, int hi, Comparator<? super T> c) {	

		int limit = lo + 1;
		if (limit == hi) { 
			return 1;
		}

		if (c.compare(a[lo], a[limit]) <= 0) {
			while (limit < hi && c.compare(a[limit - 1], a[limit]) <= 0) {
				limit++;
			}
		}
		else {
			while (limit < hi && c.compare(a[limit - 1], a[limit]) > 0) {
				limit++;
			}
			reversing(a, lo, limit);
		}

		return limit - lo;
	}
	
	
    private static <T> void reversing(T[] a, int lo, int hi) {
    	hi--;
        while (lo < hi) {
        	T temp = a[lo];
            a[lo++] = a[hi];
            a[hi--] = temp;
        }
    }
    
    
    
	/*
	 * ========================== 
	 *  reverse ordering
	 * ==========================
	 */
	
	
	public static void sort(byte[] a, boolean isReverse) {
		if(isReverse) {
			Byte[] b = Convert.toByteArray(a);
			sort(b, Order.reverseOrder());
			Convert.tobyteArray(b, a);
		}
		else {
			sort(a);
		}
	}
	
	public static void sort(char[] a, boolean isReverse) {
		if(isReverse) {
			Character[] b = Convert.toCharacterArray(a);
			sort(b, Order.reverseOrder());
			Convert.tocharArray(b, a);
		}
		else {
			sort(a);
		}
	}
	
	public static void sort(short[] a, boolean isReverse) {
		if(isReverse) {
			Short[] b = Convert.toShortArray(a);
			sort(b, Order.reverseOrder());
			Convert.toshortArray(b, a);
		}
		else {
			sort(a);
		}
	}
	
	public static void sort(int[] a, boolean isReverse) {
		if(isReverse) {
			Integer[] b = Convert.toIntegerArray(a);
			sort(b, Order.reverseOrder());
			Convert.tointtArray(b, a);
		}
		else {
			sort(a);
		}
	}
	
	
	public static void sort(long[] a, boolean isReverse) {
		if(isReverse) {
			Long[] b = Convert.toLongArray(a);
			sort(b, Order.reverseOrder());
			Convert.tolongArray(b, a);
		}
		else {
			sort(a);
		}
	}
	
	public static void sort(float[] a, boolean isReverse) {
		if(isReverse) {
			Float[] b = Convert.toFloatArray(a);
			sort(b, Order.reverseOrder());
			Convert.toflostArray(b, a);
		}
		else {
			sort(a);
		}
	}
	
	public static void sort(double[] a, boolean isReverse) {
		if(isReverse) {
			Double[] b = Convert.toDoubleArray(a);
			sort(b, Order.reverseOrder());
			Convert.todoubleArray(b, a);
		}
		else {
			sort(a);
		}
	}

}

