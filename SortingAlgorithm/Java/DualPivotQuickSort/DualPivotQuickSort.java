package DualPivotQuickSort;

/**
 * Dual-Pivot Quick Sort is based on Divide-and-conquer algorithm of quick sort,
 * but there is a difference in selecting two elements (pivot) in the list to sort.
 * @see #qsort(int[] a, int lo, int hi)
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



public class DualPivotQuickSort {
	
	

	// ============= primitive type ============//

	/*
	 * ========================== 
	 *  sorting byte type array
	 * ==========================
	 */

	public static void sort(byte[] a) {
		qsort(a, 0, a.length);
	}
	

	private static void qsort(byte[] a, int lo, int hi) {
		

		if(hi - lo < 2) {
			return;
		}

		int[] pivot = partition(a, lo, hi - 1);
		
		qsort(a, lo, pivot[0]);
		qsort(a, pivot[0] + 1, pivot[1]);
		qsort(a, pivot[1] + 1, hi);
	}
	

	private static int[] partition(byte[] a, int lo, int hi) {
		// 항상 왼쪽 피벗이 작아야함
		if (a[lo] > a[hi]) {
			swap(a, lo, hi);
		}

        /*
         * Partitioning:
         *
         *   left part           center part                   right part
         * +--------------------------------------------------------------+
         * |  < pivot1  |  pivot1 <= && <= pivot2  |    ?    |  > pivot2  |
         * +--------------------------------------------------------------+
         *               ^                          ^       ^
         *               |                          |       |
         *              less                        k     great
         *
         * Invariants:
         *
         *              all in (left, less)   < pivot1
         *    pivot1 <= all in [less, k)     <= pivot2
         *              all in (great, right) > pivot2
         *
         * Pointer k is the first index of ?-part.
         */
		
		int less = lo + 1;	
		int k = lo + 1;	
		int great = hi - 1;
		
		byte leftPivot = a[lo];
		byte rightPivot = a[hi];
		
		while(k <= great) {
			if(a[k] < leftPivot) {
				swap(a, k, less);
				less++;
			}
			
			else if(a[k] >= rightPivot) {
				while(rightPivot < a[great] && k < great) {
					great--;
				}
				swap(a, k, great);
				great--;
				
				if(a[k] < leftPivot) {
					swap(a, k ,less);
					less++;
				}
			}
			k++;
		}
		less--;
		great++;
		
		swap(a, lo, less);
		swap(a, hi, great);
		
		return new int[] {less, great};
	}
	
	private static void swap(byte[] a, int i, int j) {
		byte temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	

	/*
	 * ========================== 
	 *  sorting char type array
	 * ==========================
	 */

	public static void sort(char[] a) {
		qsort(a, 0, a.length);
	}
	

	private static void qsort(char[] a, int lo, int hi) {
		

		if(hi - lo < 2) {
			return;
		}

		int[] pivot = partition(a, lo, hi - 1);
		
		qsort(a, lo, pivot[0]);
		qsort(a, pivot[0] + 1, pivot[1]);
		qsort(a, pivot[1] + 1, hi);
	}
	

	private static int[] partition(char[] a, int lo, int hi) {
		// 항상 왼쪽 피벗이 작아야함
		if (a[lo] > a[hi]) {
			swap(a, lo, hi);
		}
		
		int less = lo + 1;	
		int k = lo + 1;	
		int great = hi - 1;
		
		char leftPivot = a[lo];
		char rightPivot = a[hi];
		
		while(k <= great) {
			if(a[k] < leftPivot) {
				swap(a, k, less);
				less++;
			}
			
			else if(a[k] >= rightPivot) {
				while(rightPivot < a[great] && k < great) {
					great--;
				}
				swap(a, k, great);
				great--;
				
				if(a[k] < leftPivot) {
					swap(a, k ,less);
					less++;
				}
			}
			k++;
		}
		less--;
		great++;
		
		swap(a, lo, less);
		swap(a, hi, great);
		
		return new int[] {less, great};
	}
	
	private static void swap(char[] a, int i, int j) {
		char temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	
	

	/*
	 * ========================== 
	 *  sorting short type array
	 * ==========================
	 */

	public static void sort(short[] a) {
		qsort(a, 0, a.length);
	}
	

	private static void qsort(short[] a, int lo, int hi) {
		

		if(hi - lo < 2) {
			return;
		}

		int[] pivot = partition(a, lo, hi - 1);
		
		qsort(a, lo, pivot[0]);
		qsort(a, pivot[0] + 1, pivot[1]);
		qsort(a, pivot[1] + 1, hi);
	}
	

	private static int[] partition(short[] a, int lo, int hi) {
		// 항상 왼쪽 피벗이 작아야함
		if (a[lo] > a[hi]) {
			swap(a, lo, hi);
		}
		
		int less = lo + 1;	
		int k = lo + 1;	
		int great = hi - 1;
		
		short leftPivot = a[lo];
		short rightPivot = a[hi];
		
		while(k <= great) {
			if(a[k] < leftPivot) {
				swap(a, k, less);
				less++;
			}
			
			else if(a[k] >= rightPivot) {
				while(rightPivot < a[great] && k < great) {
					great--;
				}
				swap(a, k, great);
				great--;
				
				if(a[k] < leftPivot) {
					swap(a, k ,less);
					less++;
				}
			}
			k++;
		}
		less--;
		great++;
		
		swap(a, lo, less);
		swap(a, hi, great);
		
		return new int[] {less, great};
	}
	
	private static void swap(short[] a, int i, int j) {
		short temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	/*
	 * ========================== 
	 *  sorting int type array
	 * ==========================
	 */
	
	public static void sort(int[] a) {
		qsort(a, 0, a.length);
	}
	

	private static void qsort(int[] a, int lo, int hi) {
		

		if(hi - lo < 2) {
			return;
		}

		int[] pivot = partition(a, lo, hi - 1);
		
		qsort(a, lo, pivot[0]);
		qsort(a, pivot[0] + 1, pivot[1]);
		qsort(a, pivot[1] + 1, hi);
	}
	

	private static int[] partition(int[] a, int lo, int hi) {
		// 항상 왼쪽 피벗이 작아야함
		if (a[lo] > a[hi]) {
			swap(a, lo, hi);
		}
		
		int less = lo + 1;	
		int k = lo + 1;	
		int great = hi - 1;
		
		int leftPivot = a[lo];
		int rightPivot = a[hi];
		
		while(k <= great) {
			if(a[k] < leftPivot) {
				swap(a, k, less);
				less++;
			}
			
			else if(a[k] >= rightPivot) {
				while(rightPivot < a[great] && k < great) {
					great--;
				}
				swap(a, k, great);
				great--;
				
				if(a[k] < leftPivot) {
					swap(a, k ,less);
					less++;
				}
			}
			k++;
		}
		less--;
		great++;
		
		swap(a, lo, less);
		swap(a, hi, great);
		
		return new int[] {less, great};
	}
	
	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	
	
	

	/*
	 * ========================== 
	 *  sorting long type array
	 * ==========================
	 */

	public static void sort(long[] a) {
		qsort(a, 0, a.length);
	}
	

	private static void qsort(long[] a, int lo, int hi) {
		

		if(hi - lo < 2) {
			return;
		}

		int[] pivot = partition(a, lo, hi - 1);
		
		qsort(a, lo, pivot[0]);
		qsort(a, pivot[0] + 1, pivot[1]);
		qsort(a, pivot[1] + 1, hi);
	}
	

	private static int[] partition(long[] a, int lo, int hi) {
		// 항상 왼쪽 피벗이 작아야함
		if (a[lo] > a[hi]) {
			swap(a, lo, hi);
		}
		
		int less = lo + 1;	
		int k = lo + 1;	
		int great = hi - 1;
		
		long leftPivot = a[lo];
		long rightPivot = a[hi];
		
		while(k <= great) {
			if(a[k] < leftPivot) {
				swap(a, k, less);
				less++;
			}
			
			else if(a[k] >= rightPivot) {
				while(rightPivot < a[great] && k < great) {
					great--;
				}
				swap(a, k, great);
				great--;
				
				if(a[k] < leftPivot) {
					swap(a, k ,less);
					less++;
				}
			}
			k++;
		}
		less--;
		great++;
		
		swap(a, lo, less);
		swap(a, hi, great);
		
		return new int[] {less, great};
	}
	
	private static void swap(long[] a, int i, int j) {
		long temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	
	

	/*
	 * ========================== 
	 *  sorting float type array
	 * ==========================
	 */

	public static void sort(float[] a) {
		qsort(a, 0, a.length);
	}
	

	private static void qsort(float[] a, int lo, int hi) {
		

		if(hi - lo < 2) {
			return;
		}

		int[] pivot = partition(a, lo, hi - 1);
		
		qsort(a, lo, pivot[0]);
		qsort(a, pivot[0] + 1, pivot[1]);
		qsort(a, pivot[1] + 1, hi);
	}
	

	private static int[] partition(float[] a, int lo, int hi) {
		// 항상 왼쪽 피벗이 작아야함
		if (a[lo] > a[hi]) {
			swap(a, lo, hi);
		}
		
		int less = lo + 1;	
		int k = lo + 1;	
		int great = hi - 1;
		
		float leftPivot = a[lo];
		float rightPivot = a[hi];
		
		while(k <= great) {
			if(a[k] < leftPivot) {
				swap(a, k, less);
				less++;
			}
			
			else if(a[k] >= rightPivot) {
				while(rightPivot < a[great] && k < great) {
					great--;
				}
				swap(a, k, great);
				great--;
				
				if(a[k] < leftPivot) {
					swap(a, k ,less);
					less++;
				}
			}
			k++;
		}
		less--;
		great++;
		
		swap(a, lo, less);
		swap(a, hi, great);
		
		return new int[] {less, great};
	}
	
	private static void swap(float[] a, int i, int j) {
		float temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	
	
	

	/*
	 * ========================== 
	 *  sorting double type array
	 * ==========================
	 */

	public static void sort(double[] a) {
		qsort(a, 0, a.length);
	}
	

	private static void qsort(double[] a, int lo, int hi) {

		if(hi - lo < 2) {
			return;
		}

		int[] pivot = partition(a, lo, hi - 1);
		
		qsort(a, lo, pivot[0]);
		qsort(a, pivot[0] + 1, pivot[1]);
		qsort(a, pivot[1] + 1, hi);
	}
	

	private static int[] partition(double[] a, int lo, int hi) {
		// 항상 왼쪽 피벗이 작아야함
		if (a[lo] > a[hi]) {
			swap(a, lo, hi);
		}
		
		int less = lo + 1;	
		int k = lo + 1;	
		int great = hi - 1;
		
		double leftPivot = a[lo];
		double rightPivot = a[hi];
		
		while(k <= great) {
			if(a[k] < leftPivot) {
				swap(a, k, less);
				less++;
			}
			
			else if(a[k] >= rightPivot) {
				while(rightPivot < a[great] && k < great) {
					great--;
				}
				swap(a, k, great);
				great--;
				
				if(a[k] < leftPivot) {
					swap(a, k ,less);
					less++;
				}
			}
			k++;
		}
		less--;
		great++;
		
		swap(a, lo, less);
		swap(a, hi, great);
		
		return new int[] {less, great};
	}
	
	private static void swap(double[] a, int i, int j) {
		double temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	
	
	
	
	
	

	/*
	 * ========================== 
	 *  sorting Object type array
	 * ==========================
	 */

	public static <T> void sort(T[] a, Comparator<? super T> c) {
		if(c == null) {
			sort(a);
		}
		else {
			qsort(a, 0, a.length, c);
		}
	}
	
	public static void sort(Object[] a) {
		qsort(a, 0, a.length);
	}
	

	private static void qsort(Object[] a, int lo, int hi) {

		if(hi - lo < 2) {
			return;
		}

		int[] pivot = partition(a, lo, hi - 1);
		
		qsort(a, lo, pivot[0]);
		qsort(a, pivot[0] + 1, pivot[1]);
		qsort(a, pivot[1] + 1, hi);
	}
	

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static int[] partition(Object[] a, int lo, int hi) {
		// 항상 왼쪽 피벗이 작아야함

		Comparable temp = ((Comparable) a[lo]);
		if (temp.compareTo(a[hi]) > 0) {
			swap(a, lo, hi);
		}
		
		int less = lo + 1;	
		int k = lo + 1;	
		int great = hi - 1;
		
		Comparable leftPivot = ((Comparable) a[lo]);
		Comparable rightPivot = ((Comparable) a[hi]);
		
		while(k <= great) {
			if(leftPivot.compareTo(a[k]) > 0) {
				swap(a, k, less);
				less++;
			}
			
			else if(rightPivot.compareTo(a[k]) <= 0) {
				while(rightPivot.compareTo(a[great]) < 0 && k < great) {
					great--;
				}
				swap(a, k, great);
				great--;
				
				if(leftPivot.compareTo(a[k]) > 0) {
					swap(a, k ,less);
					less++;
				}
			}
			k++;
		}
		less--;
		great++;
		
		swap(a, lo, less);
		swap(a, hi, great);
		
		return new int[] {less, great};
	}



	private static <T> void qsort(T[] a, int lo, int hi, Comparator<? super T> c) {

		if(hi - lo < 2) {
			return;
		}

		int[] pivot = partition(a, lo, hi - 1, c);
		
		qsort(a, lo, pivot[0], c);
		qsort(a, pivot[0] + 1, pivot[1], c);
		qsort(a, pivot[1] + 1, hi, c);
	}
	

	private static <T> int[] partition(T[] a, int lo, int hi, Comparator<? super T> c) {

		
		if (c.compare(a[lo], a[hi]) > 0) {
			swap(a, lo, hi);
		}
		
		int less = lo + 1;	
		int k = lo + 1;	
		int great = hi - 1;
		
		T leftPivot = a[lo];
		T rightPivot = a[hi];
		
		while(k <= great) {
			
			if(c.compare(leftPivot, a[k]) > 0) {
				swap(a, k, less);
				less++;
			}
			
			else if(c.compare(rightPivot, a[k]) <= 0) {
				while(c.compare(rightPivot, a[great]) < 0 && k < great) {
					great--;
				}
				swap(a, k, great);
				great--;
				
				if(c.compare(leftPivot,a[k]) > 0) {
					swap(a, k ,less);
					less++;
				}
			}
			k++;
		}
		less--;
		great++;
		
		swap(a, lo, less);
		swap(a, hi, great);
		
		return new int[] {less, great};
	}
	
	
	
	private static <T> void swap(T[] a, int i, int j) {
		T temp = a[i];
		a[i] = a[j];
		a[j] = temp;
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
