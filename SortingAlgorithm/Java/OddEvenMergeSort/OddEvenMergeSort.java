package OddEvenMergeSort;

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

public class OddEvenMergeSort {


	
	//============= primitive type ============//
	
	
	/*==========================
	 * sorting byte type array 
	 *==========================*/
	
	public static void sort(byte[] a) {
		sort(a, 0, a.length);
	}
	
	private static void sort(byte[] a, int lo, int hi) {
		if(hi - lo > 1) {
			int mid = (hi + lo) >>> 1;
			sort(a, lo, mid);
			sort(a, mid, hi);
			merge(a, lo, hi, 1);
		}
	}
	
	private static void merge(byte[] a, int lo, int hi, int dist) {
		int subDist = dist << 1;
		if(subDist < (hi - lo)) {
			merge(a, lo, hi, subDist);
			merge(a, lo + dist, hi, subDist);
			
			for(int i = lo + dist; i + dist < hi; i += subDist) {
				if(a[i] > a[i + dist]) {
					swap(a, i, i + dist);
				}
			}
		} else {
			if(a[lo] > a[lo + dist]) {
				swap(a, lo, lo + dist);
			}
		}
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
		if(hi - lo > 1) {
			int mid = (hi + lo) >>> 1;
			sort(a, lo, mid);
			sort(a, mid, hi);
			merge(a, lo, hi, 1);
		}
	}
	
	private static void merge(char[] a, int lo, int hi, int dist) {
		int subDist = dist << 1;
		if(subDist < (hi - lo)) {
			merge(a, lo, hi, subDist);
			merge(a, lo + dist, hi, subDist);
			
			for(int i = lo + dist; i + dist < hi; i += subDist) {
				if(a[i] > a[i + dist]) {
					swap(a, i, i + dist);
				}
			}
		} else {
			if(a[lo] > a[lo + dist]) {
				swap(a, lo, lo + dist);
			}
		}
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
		if(hi - lo > 1) {
			int mid = (hi + lo) >>> 1;
			sort(a, lo, mid);
			sort(a, mid, hi);
			merge(a, lo, hi, 1);
		}
	}
	
	private static void merge(short[] a, int lo, int hi, int dist) {
		int subDist = dist << 1;
		if(subDist < (hi - lo)) {
			merge(a, lo, hi, subDist);
			merge(a, lo + dist, hi, subDist);
			
			for(int i = lo + dist; i + dist < hi; i += subDist) {
				if(a[i] > a[i + dist]) {
					swap(a, i, i + dist);
				}
			}
		} else {
			if(a[lo] > a[lo + dist]) {
				swap(a, lo, lo + dist);
			}
		}
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
		if(hi - lo > 1) {
			int mid = (hi + lo) >>> 1;
			sort(a, lo, mid);
			sort(a, mid, hi);
			merge(a, lo, hi, 1);
		}
	}
	
	private static void merge(int[] a, int lo, int hi, int dist) {
		int subDist = dist << 1;
		if(subDist < (hi - lo)) {
			merge(a, lo, hi, subDist);
			merge(a, lo + dist, hi, subDist);
			
			for(int i = lo + dist; i + dist < hi; i += subDist) {
				if(a[i] > a[i + dist]) {
					swap(a, i, i + dist);
				}
			}
		} else {
			if(a[lo] > a[lo + dist]) {
				swap(a, lo, lo + dist);
			}
		}
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
		if(hi - lo > 1) {
			int mid = (hi + lo) >>> 1;
			sort(a, lo, mid);
			sort(a, mid, hi);
			merge(a, lo, hi, 1);
		}
	}
	
	private static void merge(long[] a, int lo, int hi, int dist) {
		int subDist = dist << 1;
		if(subDist < (hi - lo)) {
			merge(a, lo, hi, subDist);
			merge(a, lo + dist, hi, subDist);
			
			for(int i = lo + dist; i + dist < hi; i += subDist) {
				if(a[i] > a[i + dist]) {
					swap(a, i, i + dist);
				}
			}
		} else {
			if(a[lo] > a[lo + dist]) {
				swap(a, lo, lo + dist);
			}
		}
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
		if(hi - lo > 1) {
			int mid = (hi + lo) >>> 1;
			sort(a, lo, mid);
			sort(a, mid, hi);
			merge(a, lo, hi, 1);
		}
	}
	
	private static void merge(float[] a, int lo, int hi, int dist) {
		int subDist = dist << 1;
		if(subDist < (hi - lo)) {
			merge(a, lo, hi, subDist);
			merge(a, lo + dist, hi, subDist);
			
			for(int i = lo + dist; i + dist < hi; i += subDist) {
				if(a[i] > a[i + dist]) {
					swap(a, i, i + dist);
				}
			}
		} else {
			if(a[lo] > a[lo + dist]) {
				swap(a, lo, lo + dist);
			}
		}
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
		if(hi - lo > 1) {
			int mid = (hi + lo) >>> 1;
			sort(a, lo, mid);
			sort(a, mid, hi);
			merge(a, lo, hi, 1);
		}
	}
	
	private static void merge(double[] a, int lo, int hi, int dist) {
		int subDist = dist << 1;
		if(subDist < (hi - lo)) {
			merge(a, lo, hi, subDist);
			merge(a, lo + dist, hi, subDist);
			
			for(int i = lo + dist; i + dist < hi; i += subDist) {
				if(a[i] > a[i + dist]) {
					swap(a, i, i + dist);
				}
			}
		} else {
			if(a[lo] > a[lo + dist]) {
				swap(a, lo, lo + dist);
			}
		}
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
		if(hi - lo > 1) {
			int mid = (hi + lo) >>> 1;
			sort(a, lo, mid);
			sort(a, mid, hi);
			merge(a, lo, hi, 1);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void merge(Object[] a, int lo, int hi, int dist) {
		int subDist = dist << 1;
		if(subDist < (hi - lo)) {
			merge(a, lo, hi, subDist);
			merge(a, lo + dist, hi, subDist);
			
			for(int i = lo + dist; i + dist < hi; i += subDist) {
				if(((Comparable)a[i]).compareTo(a[i + dist]) > 0) {
					swap(a, i, i + dist);
				}
			}
		} else {
			if(((Comparable)a[lo]).compareTo(a[lo + dist]) > 0) {
				swap(a, lo, lo + dist);
			}
		}
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
		if(hi - lo > 1) {
			int mid = (hi + lo) >>> 1;
			sort(a, lo, mid, c);
			sort(a, mid, hi, c);
			merge(a, lo, hi, 1, c);
		}
	}
	
	private static <T> void merge(T[] a, int lo, int hi, int dist, Comparator<? super T> c) {
		int subDist = dist << 1;
		if(subDist < (hi - lo)) {
			merge(a, lo, hi, subDist);
			merge(a, lo + dist, hi, subDist);
			
			for(int i = lo + dist; i + dist < hi; i += subDist) {
				if(c.compare(a[i], a[i + dist]) > 0) {
					swap(a, i, i + dist);
				}
			}
		} else {
			if(c.compare(a[lo], a[lo + dist]) > 0) {
				swap(a, lo, lo + dist);
			}
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
