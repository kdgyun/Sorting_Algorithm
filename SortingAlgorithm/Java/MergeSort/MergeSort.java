package MergeSort;

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

public class MergeSort {

	@SuppressWarnings("rawtypes")
	private static Comparable[] sorted;
	private static byte[] BYTESorted;
	private static char[] CHARSorted;
	private static short[] SHORTSorted;
	private static int[] INTSorted;
	private static long[] LONGSorted;
	private static float[] FLOATSorted;
	private static double[] DOUBLESorted;
	

	
	
	//============= primitive type ============//
	
	
	/*==========================
	 * sorting byte type array 
	 *==========================*/
	
	public static void sort(byte[] a) {

		BYTESorted = new byte[a.length];
		sort(a, 0, a.length - 1);
		BYTESorted = null;
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
				BYTESorted[idx++] = a[l++];
			}

			else {
				BYTESorted[idx++] = a[r++];
			}
		}

		if (l > mid) {
			while (r <= right) {
				BYTESorted[idx++] = a[r++];
			}
		} else {
			while (l <= mid) {
				BYTESorted[idx++] = a[l++];
			}
		}

		System.arraycopy(BYTESorted, left, a, left, right - left + 1);
	}
	
	
	
	/*==========================
	 * sorting char type array 
	 *==========================*/
	
	public static void sort(char[] a) {

		CHARSorted = new char[a.length];
		sort(a, 0, a.length - 1);
		CHARSorted = null;
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
				CHARSorted[idx++] = a[l++];
			}

			else {
				CHARSorted[idx++] = a[r++];
			}
		}

		if (l > mid) {
			while (r <= right) {
				CHARSorted[idx++] = a[r++];
			}
		} else {
			while (l <= mid) {
				CHARSorted[idx++] = a[l++];
			}
		}

		System.arraycopy(CHARSorted, left, a, left, right - left + 1);
	}

	
	
	
	/*==========================
	 * sorting short type array 
	 *==========================*/
	
	public static void sort(short[] a) {

		SHORTSorted = new short[a.length];
		sort(a, 0, a.length - 1);
		SHORTSorted = null;
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
				SHORTSorted[idx++] = a[l++];
			}

			else {
				SHORTSorted[idx++] = a[r++];
			}
		}

		if (l > mid) {
			while (r <= right) {
				SHORTSorted[idx++] = a[r++];
			}
		} else {
			while (l <= mid) {
				SHORTSorted[idx++] = a[l++];
			}
		}

		System.arraycopy(SHORTSorted, left, a, left, right - left + 1);
	}

	
	
	/*==========================
	 * sorting int type array 
	 *==========================*/
	
	public static void sort(int[] a) {

		INTSorted = new int[a.length];
		sort(a, 0, a.length - 1);
		INTSorted = null;
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
				INTSorted[idx++] = a[l++];
			}

			else {
				INTSorted[idx++] = a[r++];
			}
		}

		if (l > mid) {
			while (r <= right) {
				INTSorted[idx++] = a[r++];
			}
		} else {
			while (l <= mid) {
				INTSorted[idx++] = a[l++];
			}
		}

		System.arraycopy(INTSorted, left, a, left, right - left + 1);
	}
	
	
	
	/*==========================
	 * sorting long type array 
	 *==========================*/
	
	public static void sort(long[] a) {

		LONGSorted = new long[a.length];
		sort(a, 0, a.length - 1);
		LONGSorted = null;
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
				LONGSorted[idx++] = a[l++];
			}

			else {
				LONGSorted[idx++] = a[r++];
			}
		}

		if (l > mid) {
			while (r <= right) {
				LONGSorted[idx++] = a[r++];
			}
		} else {
			while (l <= mid) {
				LONGSorted[idx++] = a[l++];
			}
		}

		System.arraycopy(LONGSorted, left, a, left, right - left + 1);
	}

	
	
	
	/*==========================
	 * sorting float type array 
	 *==========================*/
	
	public static void sort(float[] a) {

		FLOATSorted = new float[a.length];
		sort(a, 0, a.length - 1);
		FLOATSorted = null;
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
				FLOATSorted[idx++] = a[l++];
			}

			else {
				FLOATSorted[idx++] = a[r++];
			}
		}

		if (l > mid) {
			while (r <= right) {
				FLOATSorted[idx++] = a[r++];
			}
		} else {
			while (l <= mid) {
				FLOATSorted[idx++] = a[l++];
			}
		}

		System.arraycopy(FLOATSorted, left, a, left, right - left + 1);
	}
	
	
	
	/*==========================
	 * sorting double type array 
	 *==========================*/
	
	public static void sort(double[] a) {

		DOUBLESorted = new double[a.length];
		sort(a, 0, a.length - 1);
		DOUBLESorted = null;
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
				DOUBLESorted[idx++] = a[l++];
			}

			else {
				DOUBLESorted[idx++] = a[r++];
			}
		}

		if (l > mid) {
			while (r <= right) {
				DOUBLESorted[idx++] = a[r++];
			}
		} else {
			while (l <= mid) {
				DOUBLESorted[idx++] = a[l++];
			}
		}

		System.arraycopy(DOUBLESorted, left, a, left, right - left + 1);
	}
	
	
	
	
	//============= Oblect type ============//
	
	
	
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
	
	

	// reverse ordering
	
	public static void sort(byte[] a, boolean isReverse) {
		
		// reverse order
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
		
		// reverse order
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
		
		// reverse order
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
		
		// reverse order
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
		
		// reverse order
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
		
		// reverse order
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
		
		// reverse order
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



