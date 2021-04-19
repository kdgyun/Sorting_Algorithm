package MergeSort;

/**
 * 
 * @author kdgyun
 * {@link https://st-lab.tistory.com}
 * {@link https://github.com/kdgyun}
 *
 */

import java.util.Comparator;

public class MergeSort {

	@SuppressWarnings("rawtypes")
	private static Comparable[] sorted;

	
	
	//============= primitive type ============//
	
	
	/*==========================
	 * sorting byte type array 
	 *==========================*/
	
	public static void sort(byte[] a) {

		sorted = new Comparable[a.length];
		sort(a, 0, a.length - 1);
		sorted = null;
	}

	// Bottom-Up
	private static void sort(byte[] a, int left, int right) {

		for (int size = 1; size <= right; size += size) {

			for (int l = 0; l <= right - size; l += (2 * size)) {
				int high = Math.min(l + (2 * size) - 1, right);
				merge(a, l, l + size - 1, high);
			}
		}

	}

	private static void merge(byte[] a, int left, int mid, int right) {
		int l = left;
		int r = mid + 1;
		int idx = left;

		while (l <= mid && r <= right) {

			if (a[l] <= a[r]) {
				sorted[idx++] = a[l++];
			}

			else {
				sorted[idx++] = a[r++];
			}
		}

		if (l > mid) {
			while (r <= right) {
				sorted[idx++] = a[r++];
			}
		} else {
			while (l <= mid) {
				sorted[idx++] = a[l++];
			}
		}

		System.arraycopy(sorted, left, a, left, right - left + 1);
	}
	
	
	
	/*==========================
	 * sorting char type array 
	 *==========================*/
	
	public static void sort(char[] a) {

		sorted = new Comparable[a.length];
		sort(a, 0, a.length - 1);
		sorted = null;
	}

	// Bottom-Up
	private static void sort(char[] a, int left, int right) {

		for (int size = 1; size <= right; size += size) {

			for (int l = 0; l <= right - size; l += (2 * size)) {
				int high = Math.min(l + (2 * size) - 1, right);
				merge(a, l, l + size - 1, high);
			}
		}

	}

	private static void merge(char[] a, int left, int mid, int right) {
		int l = left;
		int r = mid + 1;
		int idx = left;

		while (l <= mid && r <= right) {

			if (a[l] <= a[r]) {
				sorted[idx++] = a[l++];
			}

			else {
				sorted[idx++] = a[r++];
			}
		}

		if (l > mid) {
			while (r <= right) {
				sorted[idx++] = a[r++];
			}
		} else {
			while (l <= mid) {
				sorted[idx++] = a[l++];
			}
		}

		System.arraycopy(sorted, left, a, left, right - left + 1);
	}

	
	
	
	/*==========================
	 * sorting short type array 
	 *==========================*/
	
	public static void sort(short[] a) {

		sorted = new Comparable[a.length];
		sort(a, 0, a.length - 1);
		sorted = null;
	}

	// Bottom-Up
	private static void sort(short[] a, int left, int right) {

		for (int size = 1; size <= right; size += size) {

			for (int l = 0; l <= right - size; l += (2 * size)) {
				int high = Math.min(l + (2 * size) - 1, right);
				merge(a, l, l + size - 1, high);
			}
		}

	}

	private static void merge(short[] a, int left, int mid, int right) {
		int l = left;
		int r = mid + 1;
		int idx = left;

		while (l <= mid && r <= right) {

			if (a[l] <= a[r]) {
				sorted[idx++] = a[l++];
			}

			else {
				sorted[idx++] = a[r++];
			}
		}

		if (l > mid) {
			while (r <= right) {
				sorted[idx++] = a[r++];
			}
		} else {
			while (l <= mid) {
				sorted[idx++] = a[l++];
			}
		}

		System.arraycopy(sorted, left, a, left, right - left + 1);
	}

	
	
	/*==========================
	 * sorting int type array 
	 *==========================*/
	
	public static void sort(int[] a) {

		sorted = new Comparable[a.length];
		sort(a, 0, a.length - 1);
		sorted = null;
	}

	// Bottom-Up
	private static void sort(int[] a, int left, int right) {

		for (int size = 1; size <= right; size += size) {

			for (int l = 0; l <= right - size; l += (2 * size)) {
				int high = Math.min(l + (2 * size) - 1, right);
				merge(a, l, l + size - 1, high);
			}
		}

	}

	private static void merge(int[] a, int left, int mid, int right) {
		int l = left;
		int r = mid + 1;
		int idx = left;

		while (l <= mid && r <= right) {

			if (a[l] <= a[r]) {
				sorted[idx++] = a[l++];
			}

			else {
				sorted[idx++] = a[r++];
			}
		}

		if (l > mid) {
			while (r <= right) {
				sorted[idx++] = a[r++];
			}
		} else {
			while (l <= mid) {
				sorted[idx++] = a[l++];
			}
		}

		System.arraycopy(sorted, left, a, left, right - left + 1);
	}
	
	
	
	/*==========================
	 * sorting long type array 
	 *==========================*/
	
	public static void sort(long[] a) {

		sorted = new Comparable[a.length];
		sort(a, 0, a.length - 1);
		sorted = null;
	}

	// Bottom-Up
	private static void sort(long[] a, int left, int right) {

		for (int size = 1; size <= right; size += size) {

			for (int l = 0; l <= right - size; l += (2 * size)) {
				int high = Math.min(l + (2 * size) - 1, right);
				merge(a, l, l + size - 1, high);
			}
		}

	}

	private static void merge(long[] a, int left, int mid, int right) {
		int l = left;
		int r = mid + 1;
		int idx = left;

		while (l <= mid && r <= right) {

			if (a[l] <= a[r]) {
				sorted[idx++] = a[l++];
			}

			else {
				sorted[idx++] = a[r++];
			}
		}

		if (l > mid) {
			while (r <= right) {
				sorted[idx++] = a[r++];
			}
		} else {
			while (l <= mid) {
				sorted[idx++] = a[l++];
			}
		}

		System.arraycopy(sorted, left, a, left, right - left + 1);
	}

	
	
	
	/*==========================
	 * sorting float type array 
	 *==========================*/
	
	public static void sort(float[] a) {

		sorted = new Comparable[a.length];
		sort(a, 0, a.length - 1);
		sorted = null;
	}

	// Bottom-Up
	private static void sort(float[] a, int left, int right) {

		for (int size = 1; size <= right; size += size) {

			for (int l = 0; l <= right - size; l += (2 * size)) {
				int high = Math.min(l + (2 * size) - 1, right);
				merge(a, l, l + size - 1, high);
			}
		}

	}

	private static void merge(float[] a, int left, int mid, int right) {
		int l = left;
		int r = mid + 1;
		int idx = left;

		while (l <= mid && r <= right) {

			if (a[l] <= a[r]) {
				sorted[idx++] = a[l++];
			}

			else {
				sorted[idx++] = a[r++];
			}
		}

		if (l > mid) {
			while (r <= right) {
				sorted[idx++] = a[r++];
			}
		} else {
			while (l <= mid) {
				sorted[idx++] = a[l++];
			}
		}

		System.arraycopy(sorted, left, a, left, right - left + 1);
	}
	
	
	
	/*==========================
	 * sorting double type array 
	 *==========================*/
	
	public static void sort(double[] a) {

		sorted = new Comparable[a.length];
		sort(a, 0, a.length - 1);
		sorted = null;
	}

	// Bottom-Up
	private static void sort(double[] a, int left, int right) {

		for (int size = 1; size <= right; size += size) {

			for (int l = 0; l <= right - size; l += (2 * size)) {
				int high = Math.min(l + (2 * size) - 1, right);
				merge(a, l, l + size - 1, high);
			}
		}

	}

	private static void merge(double[] a, int left, int mid, int right) {
		int l = left;
		int r = mid + 1;
		int idx = left;

		while (l <= mid && r <= right) {

			if (a[l] <= a[r]) {
				sorted[idx++] = a[l++];
			}

			else {
				sorted[idx++] = a[r++];
			}
		}

		if (l > mid) {
			while (r <= right) {
				sorted[idx++] = a[r++];
			}
		} else {
			while (l <= mid) {
				sorted[idx++] = a[l++];
			}
		}

		System.arraycopy(sorted, left, a, left, right - left + 1);
	}
	
	
	
	
	//============= primitive type ============//
	
	
	
	/*==========================
	 * sorting Object type array 
	 *==========================*/
	
	public static void sort(Object[] a) {

		sorted = new Comparable[a.length];
		sort(a, 0, a.length - 1);
		sorted = null;
	}

	// Bottom-Up
	private static void sort(Object[] a, int left, int right) {

		for (int size = 1; size <= right; size += size) {

			for (int l = 0; l <= right - size; l += (2 * size)) {
				int high = Math.min(l + (2 * size) - 1, right);
				merge((Comparable[])a, l, l + size - 1, high);
			}
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void merge(Comparable[] a, int left, int mid, int right) {
		int l = left;
		int r = mid + 1;
		int idx = left;

		while (l <= mid && r <= right) {
			
			if (a[l].compareTo(a[r]) <= 0) {
				sorted[idx++] = a[l++];
			}
			else {
				sorted[idx++] =  a[r++];
			}
		}

		if (l > mid) {
			while (r <= right) {
				sorted[idx++] = a[r++];
			}
		} else {
			while (l <= mid) {
				sorted[idx++] = a[l++];
			}
		}

		System.arraycopy(sorted, left, a, left, right - left + 1);
	}
	
	
	
	
	/*==========================
	 * sorting Object type array 
	 *==========================*/
	
	public static <T> void sort(T[] a, Comparator<? super T> c) {

		sorted = new Comparable[a.length];
		if(c == null) {
			sort(a, 0, a.length - 1);
		}
		else {
			sort(a, 0, a.length - 1, c);
		}
		sorted = null;
	}

	// Bottom-Up
	@SuppressWarnings("rawtypes")
	private static void sort(Object[] a, int left, int right, Comparator c) {

		for (int size = 1; size <= right; size += size) {

			for (int l = 0; l <= right - size; l += (2 * size)) {
				int high = Math.min(l + (2 * size) - 1, right);
				merge(a, l, l + size - 1, high, c);
			}
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void merge(Object[] a, int left, int mid, int right , Comparator c) {
		int l = left;
		int r = mid + 1;
		int idx = left;

		while (l <= mid && r <= right) {
			
			if (c.compare(a[l], a[r]) <= 0) {
				sorted[idx++] = (Comparable) a[l++];
			}

			else {
				sorted[idx++] =  (Comparable) a[r++];
			}
		}

		if (l > mid) {
			while (r <= right) {
				sorted[idx++] = (Comparable) a[r++];
			}
		} else {
			while (l <= mid) {
				sorted[idx++] = (Comparable) a[l++];
			}
		}

		System.arraycopy(sorted, left, a, left, right - left + 1);
	}
	

}
