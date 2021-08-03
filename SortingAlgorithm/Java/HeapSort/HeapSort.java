package HeapSort;

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

import java.util.Comparator;

import Utils.Convert;
import Utils.Order;

public class HeapSort {

	private static int getParent(int child) {
		return (child - 1) >>> 1;
	}

	// ============= primitive type ============//

	/*
	 * ========================== 
	 *  sorting byte type array
	 * ==========================
	 */

	private static void swap(byte[] a, int i, int j) {
		byte temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void sort(byte[] a) {
		sort(a, a.length);
	}

	private static void sort(byte[] a, int size) {
		if(size < 2) {
			return;
		}
		int last = size - 1;
		int par = getParent(last);

		while (par >= 0) {
			heapify(a, par, last);
			par--;
		}

		// sort
		int end = size - 1;
		while (end > 0) {
			swap(a, 0, end);
			end--;
			heapify(a, 0, end);
		}
	}

	private static void heapify(byte[] a, int cur, int last) {

		int left;
		int right;
		int large;

		while ((cur << 1) + 1 <= last) {
			left = (cur << 1) + 1;
			right = (cur << 1) + 2;
			large = cur;

			if (a[left] > a[large]) {
				large = left;
			}

			if (right <= last && a[right] > a[large]) {
				large = right;
			}

			if (large != cur) {
				swap(a, cur, large);
				cur = large;
			} else {
				return;
			}
		}
	}

	/*
	 * ==========================
	 *  sorting char type array
	 * ==========================
	 */

	private static void swap(char[] a, int i, int j) {
		char temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void sort(char[] a) {
		sort(a, a.length);
	}

	private static void sort(char[] a, int size) {
		if(size < 2) {
			return;
		}
		int last = size - 1;
		int par = getParent(last);

		while (par >= 0) {
			heapify(a, par, last);
			par--;
		}
		int end = size - 1;
		while (end > 0) {
			swap(a, 0, end);
			end--;
			heapify(a, 0, end);
		}
	}

	private static void heapify(char[] a, int cur, int last) {

		int left;
		int right;
		int large;

		while ((cur << 1) + 1 <= last) {
			left = (cur << 1) + 1;
			right = (cur << 1) + 2;
			large = cur;

			if (a[left] > a[large]) {
				large = left;
			}

			if (right <= last && a[right] > a[large]) {
				large = right;
			}

			if (large != cur) {
				swap(a, cur, large);
				cur = large;
			} else {
				return;
			}
		}
	}

	/*
	 * ==========================
	 *  sorting short type array
	 * ==========================
	 */

	private static void swap(short[] a, int i, int j) {
		short temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void sort(short[] a) {
		sort(a, a.length);
	}

	private static void sort(short[] a, int size) {
		if(size < 2) {
			return;
		}
		int last = size - 1;
		int par = getParent(last);

		while (par >= 0) {
			heapify(a, par, last);
			par--;
		}

		int end = size - 1;
		while (end > 0) {
			swap(a, 0, end);
			end--;
			heapify(a, 0, end);
		}
	}

	private static void heapify(short[] a, int cur, int last) {

		int left;
		int right;
		int large;

		while ((cur << 1) + 1 <= last) {
			left = (cur << 1) + 1;
			right = (cur << 1) + 2;
			large = cur;

			if (a[left] > a[large]) {
				large = left;
			}

			if (right <= last && a[right] > a[large]) {
				large = right;
			}

			if (large != cur) {
				swap(a, cur, large);
				cur = large;
			} else {
				return;
			}
		}
	}

	/*
	 * ========================== 
	 *  sorting int type array 
	 * ==========================
	 */

	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void sort(int[] a) {
		sort(a, a.length);
	}

	private static void sort(int[] a, int size) {
		if(size < 2) {
			return;
		}
		int last = size - 1;
		int par = getParent(last);

		while (par >= 0) {
			heapify(a, par, last);
			par--;
		}

		int end = size - 1;
		while (end > 0) {
			swap(a, 0, end);
			end--;
			heapify(a, 0, end);
		}
	}

	private static void heapify(int[] a, int cur, int last) {

		int left;
		int right;
		int large;

		while ((cur << 1) + 1 <= last) {
			left = (cur << 1) + 1;
			right = (cur << 1) + 2;
			large = cur;

			if (a[left] > a[large]) {
				large = left;
			}

			if (right <= last && a[right] > a[large]) {
				large = right;
			}

			if (large != cur) {
				swap(a, cur, large);
				cur = large;
			} else {
				return;
			}
		}
	}

	/*
	 * ========================== 
	 * sorting long type array 
	 * ==========================
	 */

	private static void swap(long[] a, int i, int j) {
		long temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void sort(long[] a) {
		sort(a, a.length);
	}

	private static void sort(long[] a, int size) {
		if(size < 2) {
			return;
		}
		int last = size - 1;
		int par = getParent(last);

		while (par >= 0) {
			heapify(a, par, last);
			par--;
		}

		int end = size - 1;
		while (end > 0) {
			swap(a, 0, end);
			end--;
			heapify(a, 0, end);
		}
	}

	private static void heapify(long[] a, int cur, int last) {

		int left;
		int right;
		int large;

		while ((cur << 1) + 1 <= last) {
			left = (cur << 1) + 1;
			right = (cur << 1) + 2;
			large = cur;

			if (a[left] > a[large]) {
				large = left;
			}

			if (right <= last && a[right] > a[large]) {
				large = right;
			}

			if (large != cur) {
				swap(a, cur, large);
				cur = large;
			} else {
				return;
			}
		}
	}

	/*
	 * ========================== 
	 * sorting float type array
	 * ==========================
	 */

	private static void swap(float[] a, int i, int j) {
		float temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void sort(float[] a) {
		sort(a, a.length);
	}

	private static void sort(float[] a, int size) {
		if(size < 2) {
			return;
		}
		int last = size - 1;
		int par = getParent(last);

		while (par >= 0) {
			heapify(a, par, last);
			par--;
		}

		int end = size - 1;
		while (end > 0) {
			swap(a, 0, end);
			end--;
			heapify(a, 0, end);
		}
	}

	private static void heapify(float[] a, int cur, int last) {

		int left;
		int right;
		int large;

		while ((cur << 1) + 1 <= last) {
			left = (cur << 1) + 1;
			right = (cur << 1) + 2;
			large = cur;

			if (a[left] > a[large]) {
				large = left;
			}

			if (right <= last && a[right] > a[large]) {
				large = right;
			}

			if (large != cur) {
				swap(a, cur, large);
				cur = large;
			} else {
				return;
			}
		}
	}

	
	/*
	 * ========================== 
	 * sorting double type array
	 * ==========================
	 */

	private static void swap(double[] a, int i, int j) {
		double temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void sort(double[] a) {
		sort(a, a.length);
	}

	private static void sort(double[] a, int size) {
		if(size < 2) {
			return;
		}
		int last = size - 1;
		int par = getParent(last);

		while (par >= 0) {
			heapify(a, par, last);
			par--;
		}

		// sort
		int end = size - 1;
		while (end > 0) {
			swap(a, 0, end);
			end--;
			heapify(a, 0, end);
		}
	}

	private static void heapify(double[] a, int cur, int last) {

		int left;
		int right;
		int large;

		while ((cur << 1) + 1 <= last) {
			left = (cur << 1) + 1;
			right = (cur << 1) + 2;
			large = cur;

			if (a[left] > a[large]) {
				large = left;
			}

			if (right <= last && a[right] > a[large]) {
				large = right;
			}

			if (large != cur) {
				swap(a, cur, large);
				cur = large;
			} else {
				return;
			}
		}
	}

	// ============= Object type ============//

	/*
	 * ========================== 
	 * sorting Object type array
	 * ==========================
	 */

	private static <T> void swap(T[] a, int i, int j) {
		T temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void sort(Object[] a) {
		sort(a, a.length);
	}

	private static void sort(Object[] a, int size) {
		if(size < 2) {
			return;
		}
		int last = size - 1;
		int par = getParent(last);

		while (par >= 0) {
			heapify(a, par, last);
			par--;
		}

		int end = size - 1;
		while (end > 0) {
			swap(a, 0, end);
			end--;
			heapify(a, 0, end);
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void heapify(Object[] a, int cur, int last) {

		int left;
		int right;
		int large;

		while ((cur << 1) + 1 <= last) {
			left = (cur << 1) + 1;
			right = (cur << 1) + 2;
			large = cur;

			if (((Comparable) a[large]).compareTo(a[left]) < 0) {
				large = left;
			}

			if (right <= last && ((Comparable) a[large]).compareTo(a[right]) < 0) {
				large = right;
			}

			if (large != cur) {
				swap(a, cur, large);
				cur = large;
			} else {
				return;
			}
		}
	}

	public static <T> void sort(T[] a, Comparator<? super T> c) {
		if (c == null) {
			sort(a, a.length);
		} else {
			sort(a, a.length, c);
		}
	}

	
	private static <T> void sort(T[] a, int size, Comparator<? super T> c) {
		if(size < 2) {
			return;
		}
		int last = size - 1;
		int par = getParent(last);

		while (par >= 0) {
			heapify(a, par, last, c);
			par--;
		}

		// sort
		int end = size - 1;
		while (end > 0) {
			swap(a, 0, end);
			end--;
			heapify(a, 0, end, c);
		}
	}

	private static <T> void heapify(T[] a, int cur, int last, Comparator<? super T> c) {

		int left;
		int right;
		int large;

		while ((cur << 1) + 1 <= last) {
			left = (cur << 1) + 1;
			right = (cur << 1) + 2;
			large = cur;

			if (c.compare(a[large], a[left]) < 0) {
				large = left;
			}

			if (right <= last && (c.compare(a[large], a[right]) < 0)) {
				large = right;
			}

			if (large != cur) {
				swap(a, cur, large);
				cur = large;
			} else {
				return;
			}
		}
	}
	
	
	// reverse ordering
	
	public static void sort(byte[] a, boolean isReverse) {
		
		// reverse order
		if(isReverse) {
			Byte[] b = Convert.toByteArray(a);
			sort(b, Order.reverseOrder());
			Convert.tobyteArray(b, a);
		}
		else {
			sort(a, a.length);
		}
	}

	public static void sort(char[] a, boolean isReverse) {
		
		// reverse order
		if(isReverse) {
			Character[] b = Convert.toCharacterArray(a);
			sort(b, Order.reverseOrder());
			Convert.tocharArray(b, a);
		}
		else {
			sort(a, a.length);
		}
	}
	
	public static void sort(short[] a, boolean isReverse) {
		
		// reverse order
		if(isReverse) {
			Short[] b = Convert.toShortArray(a);
			sort(b, Order.reverseOrder());
			Convert.toshortArray(b, a);
		}
		else {
			sort(a, a.length);
		}
	}
	
	public static void sort(int[] a, boolean isReverse) {
		
		// reverse order
		if(isReverse) {
			Integer[] b = Convert.toIntegerArray(a);
			sort(b, Order.reverseOrder());
			Convert.tointtArray(b, a);
		}
		else {
			sort(a, a.length);
		}
	}
	
	public static void sort(long[] a, boolean isReverse) {
		
		// reverse order
		if(isReverse) {
			Long[] b = Convert.toLongArray(a);
			sort(b, Order.reverseOrder());
			Convert.tolongArray(b, a);
		}
		else {
			sort(a, a.length);
		}
	}
	
	public static void sort(float[] a, boolean isReverse) {
		
		// reverse order
		if(isReverse) {
			Float[] b = Convert.toFloatArray(a);
			sort(b, Order.reverseOrder());
			Convert.toflostArray(b, a);
		}
		else {
			sort(a, a.length);
		}
	}
	
	public static void sort(double[] a, boolean isReverse) {
		
		// reverse order
		if(isReverse) {
			Double[] b = Convert.toDoubleArray(a);
			sort(b, Order.reverseOrder());
			Convert.todoubleArray(b, a);
		}
		else {
			sort(a, a.length);
		}
	}
	
	
}
