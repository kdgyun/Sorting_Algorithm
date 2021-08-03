package InsertionSort;

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

public class InsertionSort {
	
	
	//============= primitive type ============//
	
	
	/*==========================
	 * sorting byte type array 
	 *==========================*/
	
	public static void sort(byte[] a) {
		sort(a, a.length);
	}
	
	private static void sort(byte[] a, int size ){
		byte target; 
		int j;
		
		for(int i = 1; i < size; i++) {
			target = a[i];
			
			j = i - 1;
			while(j >= 0 && target < a[j]) {
				a[j + 1] = a[j];
				j--;
			}

			a[j + 1] = target;	
		}
		
	}
	
	
	
	/*==========================
	 * sorting char type array 
	 *==========================*/
	
	public static void sort(char[] a) {
		sort(a, a.length);
	}
	
	private static void sort(char[] a, int size) {
		
		char target;
		int j;
		
		for(int i = 1; i < size; i++) {
			target = a[i];
			
			j = i - 1;
			while(j >= 0 && target < a[j]) {
				a[j + 1] = a[j];
				j--;
			}

			a[j + 1] = target;	
		}
		
	}
	
	
	
	/*==========================
	 * sorting short type array 
	 *==========================*/
	
	public static void sort(short[] a) {
		sort(a, a.length);
	}
	
	private static void sort(short[] a, int size) {
		
		short target;
		int j;
		
		for(int i = 1; i < size; i++) {
			target = a[i];
			
			j = i - 1;
			while(j >= 0 && target < a[j]) {
				a[j + 1] = a[j];
				j--;
			}

			a[j + 1] = target;	
		}
		
	}
	
	
	
	/*==========================
	 * sorting int type array 
	 *==========================*/
	
	public static void sort(int[] a) {
		sort(a, a.length);
	}
	
	private static void sort(int[] a, int size) {
		
		int target, j;
		
		for(int i = 1; i < size; i++) {
			target = a[i];
			
			j = i - 1;
			while(j >= 0 && target < a[j]) {
				a[j + 1] = a[j];
				j--;
			}

			a[j + 1] = target;	
		}
		
	}
	
	
	
	/*==========================
	 * sorting long type array 
	 *==========================*/
	
	public static void sort(long[] a) {
		sort(a, a.length);
	}
	
	private static void sort(long[] a, int size) {
		
		long target;
		int j;
		
		for(int i = 1; i < size; i++) {
			target = a[i];
			
			j = i - 1;
			while(j >= 0 && target < a[j]) {
				a[j + 1] = a[j];
				j--;
			}

			a[j + 1] = target;	
		}
		
	}
	
	
	
	/*==========================
	 * sorting float type array 
	 *==========================*/
	
	public static void sort(float[] a) {
		sort(a, a.length);
	}
	
	private static void sort(float[] a, int size) {
		
		float target;
		int j;
		
		for(int i = 1; i < size; i++) {
			target = a[i];
			
			j = i - 1;
			while(j >= 0 && target < a[j]) {
				a[j + 1] = a[j];
				j--;
			}

			a[j + 1] = target;	
		}
		
	}
	
	
	
	/*==========================
	 * sorting double type array 
	 *==========================*/
	
	public static void sort(double[] a) {
		sort(a, a.length);
	}
	
	private static void sort(double[] a, int size) {
		
		double target;
		int j;
		
		for(int i = 1; i < size; i++) {
			target = a[i];
			
			j = i - 1;
			while(j >= 0 && target < a[j]) {
				a[j + 1] = a[j];
				j--;
			}

			a[j + 1] = target;	
		}
		
	}
	
	
	
	//============= Object type ============//
	
	
	/*==========================
	 * sorting Object type array 
	 *==========================*/
	public static void sort(Object[] a) {
		sort(a, a.length);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void sort(Object[] a, int size) {
		
		
		Object target;
		int j;
		
		for(int i = 1; i < size; i++) {
			target = a[i];
			
			j = i - 1;
			while(j >= 0 && ((Comparable)target).compareTo(a[j]) < 0) {
				a[j + 1] = a[j];
				j--;
			}

			a[j + 1] = target;	
		}
	}
	
	
	/*==========================
	 * sorting Object type array 
	 *==========================*/
	public static <T> void sort(T[] a, Comparator<? super T> c) {
		if(c == null) {
			sort(a, a.length);
		}
		else {
			sort(a, a.length, c);
		}
	}
	
	
	private static <T> void sort(T[] a, int size, Comparator<? super T> c) {
		T target;
		int j;
		
		for(int i = 1; i < size; i++) {
			target = a[i];
			
			j = i - 1;
			while(j >= 0 && c.compare(target, a[j]) < 0) {
				a[j + 1] = a[j];
				j--;
			}

			a[j + 1] = target;	
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
