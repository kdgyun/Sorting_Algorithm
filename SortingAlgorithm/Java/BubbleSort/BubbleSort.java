package BubbleSort;


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
import Utils.Order;
import Utils.Convert;

public class BubbleSort {
	
	
	
	//============= primitive type ============//
	
	
	/*==========================
	 * sorting byte type array 
	 *==========================*/
	
	public static void sort(byte[] a) {
		sort(a, a.length);
	}
	
	private static void sort(byte[] a, int size) {
		
		for(int i = 1; i < size; i++) {
			boolean swapped = false;	
			
			for(int j = 0; j < size - i; j++) {
				if(a[j] > a [j + 1]) {
					swap(a, j, j + 1);
					swapped = true;
				}
			}
			if(swapped == false) {
				break;
			}
		}
	}
	
	private static void swap(byte[] a, int i, int j) {
		byte temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
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

	
	
	
	/*==========================
	 * sorting char type array 
	 *==========================*/
	
	public static void sort(char[] a) {
		sort(a, a.length);
	}
	
	private static void sort(char[] a, int size) {
		
		for(int i = 1; i < size; i++) {
			boolean swapped = false;	
			
			for(int j = 0; j < size - i; j++) {
				if(a[j] > a [j + 1]) {
					swap(a, j, j + 1);
					swapped = true;
				}
			}
			if(swapped == false) {
				break;
			}
		}
	}
	
	private static void swap(char[] a, int i, int j) {
		char temp = a[i];
		a[i] = a[j];
		a[j] = temp;
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
	
	
	
	/*==========================
	 * sorting short type array 
	 *==========================*/
	
	public static void sort(short[] a) {
		sort(a, a.length);
	}
	
	private static void sort(short[] a, int size) {
		
		for(int i = 1; i < size; i++) {
			boolean swapped = false;	
			
			for(int j = 0; j < size - i; j++) {
				if(a[j] > a [j + 1]) {
					swap(a, j, j + 1);
					swapped = true;
				}
			}
			if(swapped == false) {
				break;
			}
		}
	}
	
	private static void swap(short[] a, int i, int j) {
		short temp = a[i];
		a[i] = a[j];
		a[j] = temp;
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
	
	
	/*==========================
	 * sorting int type array 
	 *==========================*/
	
	public static void sort(int[] a) {
		sort(a, a.length);
	}
	
	private static void sort(int[] a, int size) {
		
		for(int i = 1; i < size; i++) {
			boolean swapped = false;	
			
			for(int j = 0; j < size - i; j++) {
				if(a[j] > a [j + 1]) {
					swap(a, j, j + 1);
					swapped = true;
				}
			}
			if(swapped == false) {
				break;
			}
		}
	}
	
	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
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
	
	
	/*==========================
	 * sorting long type array 
	 *==========================*/
	
	public static void sort(long[] a) {
		sort(a, a.length);
	}
	
	private static void sort(long[] a, int size) {
		
		for(int i = 1; i < size; i++) {
			boolean swapped = false;	
			
			for(int j = 0; j < size - i; j++) {
				if(a[j] > a [j + 1]) {
					swap(a, j, j + 1);
					swapped = true;
				}
			}
			if(swapped == false) {
				break;
			}
		}
	}
	
	private static void swap(long[] a, int i, int j) {
		long temp = a[i];
		a[i] = a[j];
		a[j] = temp;
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
	
	
	/*==========================
	 * sorting float type array 
	 *==========================*/
	
	public static void sort(float[] a) {
		sort(a, a.length);
	}
	
	private static void sort(float[] a, int size) {
		
		for(int i = 1; i < size; i++) {
			boolean swapped = false;	
			
			for(int j = 0; j < size - i; j++) {
				if(a[j] > a [j + 1]) {
					swap(a, j, j + 1);
					swapped = true;
				}
			}
			if(swapped == false) {
				break;
			}
		}
	}
	
	private static void swap(float[] a, int i, int j) {
		float temp = a[i];
		a[i] = a[j];
		a[j] = temp;
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
	
	/*==========================
	 * sorting double type array 
	 *==========================*/
	
	public static void sort(double[] a) {
		sort(a, a.length);
	}
	
	private static void sort(double[] a, int size) {
		
		for(int i = 1; i < size; i++) {
			boolean swapped = false;	
			
			for(int j = 0; j < size - i; j++) {
				if(a[j] > a [j + 1]) {
					swap(a, j, j + 1);
					swapped = true;
				}
			}
			if(swapped == false) {
				break;
			}
		}
	}
	
	private static void swap(double[] a, int i, int j) {
		double temp = a[i];
		a[i] = a[j];
		a[j] = temp;
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
	
	
	//============= Object type ============//
	
	
	/*==========================
	 * sorting Object type array 
	 *==========================*/
	
	public static void sort(Object[] a) {
		sort(a, a.length);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void sort(Object[] a, int size) {
		
		for(int i = 1; i < size; i++) {
			boolean swapped = false;	
			
			for(int j = 0; j < size - i; j++) {
				if(((Comparable)a[j + 1]).compareTo(a[j]) < 0) {
					swap(a, j, j + 1);
					swapped = true;
				}
			}
			if(swapped == false) {
				break;
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
			sort(a, a.length);
		}
		else {
			sort(a, a.length, c);
		}
	}
	
	private static <T> void sort(T[] a, int size, Comparator<? super T> c) {
		
		for(int i = 1; i < size; i++) {
			boolean swapped = false;	
			
			for(int j = 0; j < size - i; j++) {
				if(c.compare(a[j + 1], a[j]) < 0) {
					swap(a, j, j + 1);
					swapped = true;
				}
			}
			if(swapped == false) {
				break;
			}
		}
	}
	
}
