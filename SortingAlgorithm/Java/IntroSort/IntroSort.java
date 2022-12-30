package IntroSort;

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

public class IntroSort {

	private static final int THREADHOLD = 16;
	
	private static final int log2(int n) {
		return (int)(Math.log(n) / Math.log(2));
	}
	
	//============= primitive type ============//
	
	
	/*==========================
	 * sorting byte type array 
	 *==========================*/
	
	public static void sort(byte[] a) {
		sort(a, 0, a.length);
	}
	
	private static void sort(byte[] a, int lo, int hi) {
		if(hi - lo < 2) {
			return;
		}
		if(hi - lo <= THREADHOLD) {
			__insertionSort(a, lo, hi);
			return;
		}
		int limit = 2 * log2(hi - lo);
		__quickSort(a, lo, hi, limit);
		__insertionSort(a, lo, hi);
		
	}

	private static void __insertionSort(byte[] a, int lo, int hi) {
		byte target;
		int j;
		for(int i = lo + 1; i < hi; i++) {
			target = a[i];
			j = i - 1;
			
			while(j >= lo && target < a[j]) {
				a[j + 1] = a[j];
				j--;
			}
			a[j+1] = target;
		}
	}
	
	private static void __heapSort(byte[] a, int lo, int hi) {
		if(hi - lo < 2) {
			return;
		}
		int last = hi - 1;
		int parent = ((last - 1) >>> 1);
		
		while(parent >= lo) {
			__heapify(a, parent, last);
			parent--;
		}
		int end = hi - 1;
		while(end > lo) {
			swap(a, lo, end);
			end--;
			__heapify(a, lo, end);
		}
	}

	private static void __heapify(byte[] a, int cur, int last) {
		int left, right, large;
		
		while(((cur << 1) + 1) <= last) {
			left = (cur << 1) + 1;
			right = (cur << 1) + 2;
			large = cur;
			
			if(a[left] > a[large]) {
				large = left;
			}
			if((right <= last) && a[right] > a[large]) {
				large = right;
			}
			if(large != cur) {
				swap(a, cur, large);
				cur = large;
			} else {
				return;
			}
			
		}
	}
	
	private static void __quickSort(byte[] a, int lo, int hi, int depthLimit) {
		
		if(hi - lo <= THREADHOLD) {
			__insertionSort(a, lo, hi);
			return;
		}
		if(depthLimit == 0) {
			__heapSort(a, lo, hi);
			return;
		}
		int pivot = __partition(a, lo, hi - 1);
		__quickSort(a, lo, pivot, depthLimit - 1);
		__quickSort(a, pivot, hi, depthLimit - 1);
	}
	
	private static int __partition(byte[] a, int left, int right) {
		int lo = left;
		int hi = right;
		byte pivot = a[left + ((right - left) >>> 1)];
		
		while(true) {
			while(a[lo] < pivot) lo++;
			while(a[hi] > pivot && lo <= hi) hi--;
			
			if(lo >= hi) break;
			swap(a, lo, hi);
		}
		return hi;
	}
	
	private static void swap(byte[] a, int i, int j) {
		byte temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	
	
	/*==========================
	 * sorting char type array 
	 *==========================*/
	
	public static void sort(char[] a) {
		sort(a, 0, a.length);
	}
	
	private static void sort(char[] a, int lo, int hi) {
		if(hi - lo < 2) {
			return;
		}
		if(hi - lo <= THREADHOLD) {
			__insertionSort(a, lo, hi);
			return;
		}
		int limit = 2 * log2(hi - lo);
		__quickSort(a, lo, hi, limit);
		__insertionSort(a, lo, hi);
		
	}

	private static void __insertionSort(char[] a, int lo, int hi) {
		char target;
		int j;
		for(int i = lo + 1; i < hi; i++) {
			target = a[i];
			j = i - 1;
			
			while(j >= lo && target < a[j]) {
				a[j + 1] = a[j];
				j--;
			}
			a[j+1] = target;
		}
	}
	
	private static void __heapSort(char[] a, int lo, int hi) {
		if(hi - lo < 2) {
			return;
		}
		int last = hi - 1;
		int parent = ((last - 1) >>> 1);
		
		while(parent >= lo) {
			__heapify(a, parent, last);
			parent--;
		}
		int end = hi - 1;
		while(end > lo) {
			swap(a, lo, end);
			end--;
			__heapify(a, lo, end);
		}
	}

	private static void __heapify(char[] a, int cur, int last) {
		int left, right, large;
		
		while(((cur << 1) + 1) <= last) {
			left = (cur << 1) + 1;
			right = (cur << 1) + 2;
			large = cur;
			
			if(a[left] > a[large]) {
				large = left;
			}
			if((right <= last) && a[right] > a[large]) {
				large = right;
			}
			if(large != cur) {
				swap(a, cur, large);
				cur = large;
			} else {
				return;
			}
			
		}
	}
	
	private static void __quickSort(char[] a, int lo, int hi, int depthLimit) {
		
		if(hi - lo <= THREADHOLD) {
			__insertionSort(a, lo, hi);
			return;
		}
		if(depthLimit == 0) {
			__heapSort(a, lo, hi);
			return;
		}
		int pivot = __partition(a, lo, hi - 1);
		__quickSort(a, lo, pivot, depthLimit - 1);
		__quickSort(a, pivot, hi, depthLimit - 1);
	}
	
	private static int __partition(char[] a, int left, int right) {
		int lo = left;
		int hi = right;
		char pivot = a[left + ((right - left) >>> 1)];
		
		while(true) {
			while(a[lo] < pivot) lo++;
			while(a[hi] > pivot && lo <= hi) hi--;
			
			if(lo >= hi) break;
			swap(a, lo, hi);
		}
		return hi;
	}
	
	private static void swap(char[] a, int i, int j) {
		char temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	

	
	
	
	/*==========================
	 * sorting short type array 
	 *==========================*/
	
	public static void sort(short[] a) {
		sort(a, 0, a.length);
	}
	
	private static void sort(short[] a, int lo, int hi) {
		if(hi - lo < 2) {
			return;
		}
		if(hi - lo <= THREADHOLD) {
			__insertionSort(a, lo, hi);
			return;
		}
		int limit = 2 * log2(hi - lo);
		__quickSort(a, lo, hi, limit);
		__insertionSort(a, lo, hi);
		
	}

	private static void __insertionSort(short[] a, int lo, int hi) {
		short target;
		int j;
		for(int i = lo + 1; i < hi; i++) {
			target = a[i];
			j = i - 1;
			
			while(j >= lo && target < a[j]) {
				a[j + 1] = a[j];
				j--;
			}
			a[j+1] = target;
		}
	}
	
	private static void __heapSort(short[] a, int lo, int hi) {
		if(hi - lo < 2) {
			return;
		}
		int last = hi - 1;
		int parent = ((last - 1) >>> 1);
		
		while(parent >= lo) {
			__heapify(a, parent, last);
			parent--;
		}
		int end = hi - 1;
		while(end > lo) {
			swap(a, lo, end);
			end--;
			__heapify(a, lo, end);
		}
	}

	private static void __heapify(short[] a, int cur, int last) {
		int left, right, large;
		
		while(((cur << 1) + 1) <= last) {
			left = (cur << 1) + 1;
			right = (cur << 1) + 2;
			large = cur;
			
			if(a[left] > a[large]) {
				large = left;
			}
			if((right <= last) && a[right] > a[large]) {
				large = right;
			}
			if(large != cur) {
				swap(a, cur, large);
				cur = large;
			} else {
				return;
			}
			
		}
	}
	
	private static void __quickSort(short[] a, int lo, int hi, int depthLimit) {
		
		if(hi - lo <= THREADHOLD) {
			__insertionSort(a, lo, hi);
			return;
		}
		if(depthLimit == 0) {
			__heapSort(a, lo, hi);
			return;
		}
		int pivot = __partition(a, lo, hi - 1);
		__quickSort(a, lo, pivot, depthLimit - 1);
		__quickSort(a, pivot, hi, depthLimit - 1);
	}
	
	private static int __partition(short[] a, int left, int right) {
		int lo = left;
		int hi = right;
		short pivot = a[left + ((right - left) >>> 1)];
		
		while(true) {
			while(a[lo] < pivot) lo++;
			while(a[hi] > pivot && lo <= hi) hi--;
			
			if(lo >= hi) break;
			swap(a, lo, hi);
		}
		return hi;
	}
	
	private static void swap(short[] a, int i, int j) {
		short temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	

	
	
	/*==========================
	 * sorting int type array 
	 *==========================*/
	
	public static void sort(int[] a) {
		sort(a, 0, a.length);
	}
	
	private static void sort(int[] a, int lo, int hi) {
		if(hi - lo < 2) {
			return;
		}
		if(hi - lo <= THREADHOLD) {
			__insertionSort(a, lo, hi);
			return;
		}
		int limit = 2 * log2(hi - lo);
		__quickSort(a, lo, hi, limit);
		__insertionSort(a, lo, hi);
		
	}

	private static void __insertionSort(int[] a, int lo, int hi) {
		int target;
		int j;
		for(int i = lo + 1; i < hi; i++) {
			target = a[i];
			j = i - 1;
			
			while(j >= lo && target < a[j]) {
				a[j + 1] = a[j];
				j--;
			}
			a[j+1] = target;
		}
	}
	
	private static void __heapSort(int[] a, int lo, int hi) {
		if(hi - lo < 2) {
			return;
		}
		int last = hi - 1;
		int parent = ((last - 1) >>> 1);
		
		while(parent >= lo) {
			__heapify(a, parent, last);
			parent--;
		}
		int end = hi - 1;
		while(end > lo) {
			swap(a, lo, end);
			end--;
			__heapify(a, lo, end);
		}
	}

	private static void __heapify(int[] a, int cur, int last) {
		int left, right, large;
		
		while(((cur << 1) + 1) <= last) {
			left = (cur << 1) + 1;
			right = (cur << 1) + 2;
			large = cur;
			
			if(a[left] > a[large]) {
				large = left;
			}
			if((right <= last) && a[right] > a[large]) {
				large = right;
			}
			if(large != cur) {
				swap(a, cur, large);
				cur = large;
			} else {
				return;
			}
			
		}
	}
	
	private static void __quickSort(int[] a, int lo, int hi, int depthLimit) {
		
		if(hi - lo <= THREADHOLD) {
			__insertionSort(a, lo, hi);
			return;
		}
		if(depthLimit == 0) {
			__heapSort(a, lo, hi);
			return;
		}
		int pivot = __partition(a, lo, hi - 1);
		__quickSort(a, lo, pivot, depthLimit - 1);
		__quickSort(a, pivot, hi, depthLimit - 1);
	}
	
	private static int __partition(int[] a, int left, int right) {
		int lo = left;
		int hi = right;
		int pivot = a[left + ((right - left) >>> 1)];
		
		while(true) {
			while(a[lo] < pivot) lo++;
			while(a[hi] > pivot && lo <= hi) hi--;
			
			if(lo >= hi) break;
			swap(a, lo, hi);
		}
		return hi;
	}
	
	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	

	
	
	/*==========================
	 * sorting long type array 
	 *==========================*/
	
	public static void sort(long[] a) {
		sort(a, 0, a.length);
	}
	
	private static void sort(long[] a, int lo, int hi) {
		if(hi - lo < 2) {
			return;
		}
		if(hi - lo <= THREADHOLD) {
			__insertionSort(a, lo, hi);
			return;
		}
		int limit = 2 * log2(hi - lo);
		__quickSort(a, lo, hi, limit);
		__insertionSort(a, lo, hi);
		
	}

	private static void __insertionSort(long[] a, int lo, int hi) {
		long target;
		int j;
		for(int i = lo + 1; i < hi; i++) {
			target = a[i];
			j = i - 1;
			
			while(j >= lo && target < a[j]) {
				a[j + 1] = a[j];
				j--;
			}
			a[j+1] = target;
		}
	}
	
	private static void __heapSort(long[] a, int lo, int hi) {
		if(hi - lo < 2) {
			return;
		}
		int last = hi - 1;
		int parent = ((last - 1) >>> 1);
		
		while(parent >= lo) {
			__heapify(a, parent, last);
			parent--;
		}
		int end = hi - 1;
		while(end > lo) {
			swap(a, lo, end);
			end--;
			__heapify(a, lo, end);
		}
	}

	private static void __heapify(long[] a, int cur, int last) {
		int left, right, large;
		
		while(((cur << 1) + 1) <= last) {
			left = (cur << 1) + 1;
			right = (cur << 1) + 2;
			large = cur;
			
			if(a[left] > a[large]) {
				large = left;
			}
			if((right <= last) && a[right] > a[large]) {
				large = right;
			}
			if(large != cur) {
				swap(a, cur, large);
				cur = large;
			} else {
				return;
			}
			
		}
	}
	
	private static void __quickSort(long[] a, int lo, int hi, int depthLimit) {
		
		if(hi - lo <= THREADHOLD) {
			__insertionSort(a, lo, hi);
			return;
		}
		if(depthLimit == 0) {
			__heapSort(a, lo, hi);
			return;
		}
		int pivot = __partition(a, lo, hi - 1);
		__quickSort(a, lo, pivot, depthLimit - 1);
		__quickSort(a, pivot, hi, depthLimit - 1);
	}
	
	private static int __partition(long[] a, int left, int right) {
		int lo = left;
		int hi = right;
		long pivot = a[left + ((right - left) >>> 1)];
		
		while(true) {
			while(a[lo] < pivot) lo++;
			while(a[hi] > pivot && lo <= hi) hi--;
			
			if(lo >= hi) break;
			swap(a, lo, hi);
		}
		return hi;
	}
	
	private static void swap(long[] a, int i, int j) {
		long temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	
	
	/*==========================
	 * sorting float type array 
	 *==========================*/
	
	public static void sort(float[] a) {
		sort(a, 0, a.length);
	}
	
	private static void sort(float[] a, int lo, int hi) {
		if(hi - lo < 2) {
			return;
		}
		if(hi - lo <= THREADHOLD) {
			__insertionSort(a, lo, hi);
			return;
		}
		int limit = 2 * log2(hi - lo);
		__quickSort(a, lo, hi, limit);
		__insertionSort(a, lo, hi);
		
	}

	private static void __insertionSort(float[] a, int lo, int hi) {
		float target;
		int j;
		for(int i = lo + 1; i < hi; i++) {
			target = a[i];
			j = i - 1;
			
			while(j >= lo && target < a[j]) {
				a[j + 1] = a[j];
				j--;
			}
			a[j+1] = target;
		}
	}
	
	private static void __heapSort(float[] a, int lo, int hi) {
		if(hi - lo < 2) {
			return;
		}
		int last = hi - 1;
		int parent = ((last - 1) >>> 1);
		
		while(parent >= lo) {
			__heapify(a, parent, last);
			parent--;
		}
		int end = hi - 1;
		while(end > lo) {
			swap(a, lo, end);
			end--;
			__heapify(a, lo, end);
		}
	}

	private static void __heapify(float[] a, int cur, int last) {
		int left, right, large;
		
		while(((cur << 1) + 1) <= last) {
			left = (cur << 1) + 1;
			right = (cur << 1) + 2;
			large = cur;
			
			if(a[left] > a[large]) {
				large = left;
			}
			if((right <= last) && a[right] > a[large]) {
				large = right;
			}
			if(large != cur) {
				swap(a, cur, large);
				cur = large;
			} else {
				return;
			}
			
		}
	}
	
	private static void __quickSort(float[] a, int lo, int hi, int depthLimit) {
		
		if(hi - lo <= THREADHOLD) {
			__insertionSort(a, lo, hi);
			return;
		}
		if(depthLimit == 0) {
			__heapSort(a, lo, hi);
			return;
		}
		int pivot = __partition(a, lo, hi - 1);
		__quickSort(a, lo, pivot, depthLimit - 1);
		__quickSort(a, pivot, hi, depthLimit - 1);
	}
	
	private static int __partition(float[] a, int left, int right) {
		int lo = left;
		int hi = right;
		float pivot = a[left + ((right - left) >>> 1)];
		
		while(true) {
			while(a[lo] < pivot) lo++;
			while(a[hi] > pivot && lo <= hi) hi--;
			
			if(lo >= hi) break;
			swap(a, lo, hi);
		}
		return hi;
	}
	
	private static void swap(float[] a, int i, int j) {
		float temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	
	
	/*==========================
	 * sorting double type array 
	 *==========================*/
	
	public static void sort(double[] a) {
		sort(a, 0, a.length);
	}
	
	private static void sort(double[] a, int lo, int hi) {
		if(hi - lo < 2) {
			return;
		}
		if(hi - lo <= THREADHOLD) {
			__insertionSort(a, lo, hi);
			return;
		}
		int limit = 2 * log2(hi - lo);
		__quickSort(a, lo, hi, limit);
		__insertionSort(a, lo, hi);
		
	}

	private static void __insertionSort(double[] a, int lo, int hi) {
		double target;
		int j;
		for(int i = lo + 1; i < hi; i++) {
			target = a[i];
			j = i - 1;
			
			while(j >= lo && target < a[j]) {
				a[j + 1] = a[j];
				j--;
			}
			a[j+1] = target;
		}
	}
	
	private static void __heapSort(double[] a, int lo, int hi) {
		if(hi - lo < 2) {
			return;
		}
		int last = hi - 1;
		int parent = ((last - 1) >>> 1);
		
		while(parent >= lo) {
			__heapify(a, parent, last);
			parent--;
		}
		int end = hi - 1;
		while(end > lo) {
			swap(a, lo, end);
			end--;
			__heapify(a, lo, end);
		}
	}

	private static void __heapify(double[] a, int cur, int last) {
		int left, right, large;
		
		while(((cur << 1) + 1) <= last) {
			left = (cur << 1) + 1;
			right = (cur << 1) + 2;
			large = cur;
			
			if(a[left] > a[large]) {
				large = left;
			}
			if((right <= last) && a[right] > a[large]) {
				large = right;
			}
			if(large != cur) {
				swap(a, cur, large);
				cur = large;
			} else {
				return;
			}
			
		}
	}
	
	private static void __quickSort(double[] a, int lo, int hi, int depthLimit) {
		
		if(hi - lo <= THREADHOLD) {
			__insertionSort(a, lo, hi);
			return;
		}
		if(depthLimit == 0) {
			__heapSort(a, lo, hi);
			return;
		}
		int pivot = __partition(a, lo, hi - 1);
		__quickSort(a, lo, pivot, depthLimit - 1);
		__quickSort(a, pivot, hi, depthLimit - 1);
	}
	
	private static int __partition(double[] a, int left, int right) {
		int lo = left;
		int hi = right;
		double pivot = a[left + ((right - left) >>> 1)];
		
		while(true) {
			while(a[lo] < pivot) lo++;
			while(a[hi] > pivot && lo <= hi) hi--;
			
			if(lo >= hi) break;
			swap(a, lo, hi);
		}
		return hi;
	}
	
	private static void swap(double[] a, int i, int j) {
		double temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	
	//============= Object type ============//
	
	
	/*==========================
	 * sorting Object type array 
	 *==========================*/
	
	public static void sort(Object[] a) {
		sort(a, 0, a.length);
	}
	
	private static void sort(Object[] a, int lo, int hi) {
		if(hi - lo < 2) {
			return;
		}
		if(hi - lo <= THREADHOLD) {
			__insertionSort(a, lo, hi);
			return;
		}
		int limit = 2 * log2(hi - lo);
		__quickSort(a, lo, hi, limit);
		__insertionSort(a, lo, hi);
		
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void __insertionSort(Object[] a, int lo, int hi) {
		Comparable target;
		int j;
		for(int i = lo + 1; i < hi; i++) {
			target = (Comparable)a[i];
			j = i - 1;
			
			while(j >= lo && target.compareTo(a[j]) < 0) {
				a[j + 1] = a[j];
				j--;
			}
			a[j+1] = target;
		}
	}
	
	private static void __heapSort(Object[] a, int lo, int hi) {
		if(hi - lo < 2) {
			return;
		}
		int last = hi - 1;
		int parent = ((last - 1) >>> 1);
		
		while(parent >= lo) {
			__heapify(a, parent, last);
			parent--;
		}
		int end = hi - 1;
		while(end > lo) {
			swap(a, lo, end);
			end--;
			__heapify(a, lo, end);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void __heapify(Object[] a, int cur, int last) {
		int left, right, large;
		
		while(((cur << 1) + 1) <= last) {
			left = (cur << 1) + 1;
			right = (cur << 1) + 2;
			large = cur;
			
			if(((Comparable)a[left]).compareTo(a[large]) > 0) {
				large = left;
			}
			if((right <= last) && ((Comparable)a[right]).compareTo(a[large]) > 0) {
				large = right;
			}
			if(large != cur) {
				swap(a, cur, large);
				cur = large;
			} else {
				return;
			}
			
		}
	}
	
	private static void __quickSort(Object[] a, int lo, int hi, int depthLimit) {
		
		if(hi - lo <= THREADHOLD) {
			__insertionSort(a, lo, hi);
			return;
		}
		if(depthLimit == 0) {
			__heapSort(a, lo, hi);
			return;
		}
		int pivot = __partition(a, lo, hi - 1);
		__quickSort(a, lo, pivot, depthLimit - 1);
		__quickSort(a, pivot, hi, depthLimit - 1);
	}
	
	@SuppressWarnings("unchecked")
	private static int __partition(Object[] a, int left, int right) {
		int lo = left;
		int hi = right;
		@SuppressWarnings("rawtypes")
		Comparable pivot = (Comparable)a[left + ((right - left) >>> 1)];
		
		while(true) {
			while(pivot.compareTo(a[lo]) > 0) lo++;
			while(pivot.compareTo(a[hi]) < 0 && lo <= hi) hi--;
			
			if(lo >= hi) break;
			swap(a, lo, hi);
		}
		return hi;
	}
	
	private static void swap(Object[] a, int i, int j) {
		Object temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	
	
	
	/*==========================
	 * sorting Object type array 
	 *==========================*/
	
	public static <T> void sort(T[] a, Comparator<? super T> c) {
		if(c == null) {
			sort(a, 0, a.length);
		}
		else {
			sort(a, 0, a.length, c);
		}
	}
	
	private static <T> void sort(T[] a, int lo, int hi, Comparator<? super T> c) {
		if(hi - lo < 2) {
			return;
		}
		if(hi - lo <= THREADHOLD) {
			__insertionSort(a, lo, hi, c);
			return;
		}
		int limit = 2 * log2(hi - lo);
		__quickSort(a, lo, hi, limit, c);
		__insertionSort(a, lo, hi, c);
		
	}

	private static <T> void __insertionSort(T[] a, int lo, int hi, Comparator<? super T> c) {
		T target;
		int j;
		for(int i = lo + 1; i < hi; i++) {
			target = a[i];
			j = i - 1;
			
			while(j >= lo && c.compare(target, a[j]) < 0) {
				a[j + 1] = a[j];
				j--;
			}
			a[j+1] = target;
		}
	}
	
	private static <T> void __heapSort(T[] a, int lo, int hi, Comparator<? super T> c) {
		if(hi - lo < 2) {
			return;
		}
		int last = hi - 1;
		int parent = ((last - 1) >>> 1);
		
		while(parent >= lo) {
			__heapify(a, parent, last, c);
			parent--;
		}
		int end = hi - 1;
		while(end > lo) {
			swap(a, lo, end);
			end--;
			__heapify(a, lo, end, c);
		}
	}

	private static <T> void __heapify(T[] a, int cur, int last, Comparator<? super T> c) {
		int left, right, large;
		
		while(((cur << 1) + 1) <= last) {
			left = (cur << 1) + 1;
			right = (cur << 1) + 2;
			large = cur;
			
			if(c.compare(a[left], a[large]) > 0) {
				large = left;
			}
			if((right <= last) && c.compare(a[right], a[large]) > 0) {
				large = right;
			}
			if(large != cur) {
				swap(a, cur, large);
				cur = large;
			} else {
				return;
			}
			
		}
	}
	
	private static <T> void __quickSort(T[] a, int lo, int hi, int depthLimit, Comparator<? super T> c) {
		
		if(hi - lo <= THREADHOLD) {
			__insertionSort(a, lo, hi, c);
			return;
		}
		if(depthLimit == 0) {
			__heapSort(a, lo, hi, c);
			return;
		}
		int pivot = __partition(a, lo, hi - 1, c);
		__quickSort(a, lo, pivot, depthLimit - 1, c);
		__quickSort(a, pivot, hi, depthLimit - 1, c);
	}
	
	private static <T> int __partition(T[] a, int left, int right, Comparator<? super T> c) {
		int lo = left;
		int hi = right;
		T pivot = a[left + ((right - left) >>> 1)];
		
		while(true) {
			while(c.compare(a[lo], pivot) < 0) lo++;
			while(c.compare(a[hi], pivot) > 0 && lo <= hi) hi--;
			
			if(lo >= hi) break;
			swap(a, lo, hi);
		}
		return hi;
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


