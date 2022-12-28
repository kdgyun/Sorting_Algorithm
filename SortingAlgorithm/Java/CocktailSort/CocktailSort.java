package CocktailSort;

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

public class CocktailSort {


	
	//============= primitive type ============//
	
	
	/*==========================
	 * sorting byte type array 
	 *==========================*/
	
	public static void sort(byte[] a) {
		sort(a, 0, a.length);
	}
	
	private static void sort(byte[] a, int left, int right) {
		int lo = left;
		int hi = right;
		boolean swapped = true;

		while(swapped) {
			swapped = false;

			for(int i = lo; i < hi-1; i++) {
				if(a[i] > a[i+1]) {
					swap(a, i, i + 1);
					swapped = true;
				}
			}

			if (!swapped) {
				break;
			}

			swapped = false;

			hi--;
			for(int i = hi - 1; i >= lo; i--) {
				if (a[i] > a[i+1]) {
					swap(a, i, i + 1);
					swapped = true;
				}
			}
			lo++;
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
	
	private static void sort(char[] a, int left, int right) {
		int lo = left;
		int hi = right;
		boolean swapped = true;

		while(swapped) {
			swapped = false;

			for(int i = lo; i < hi-1; i++) {
				if(a[i] > a[i+1]) {
					swap(a, i, i + 1);
					swapped = true;
				}
			}

			if (!swapped) {
				break;
			}

			swapped = false;

			hi--;
			for(int i = hi - 1; i >= lo; i--) {
				if (a[i] > a[i+1]) {
					swap(a, i, i + 1);
					swapped = true;
				}
			}
			lo++;
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
	
	private static void sort(short[] a, int left, int right) {
		int lo = left;
		int hi = right;
		boolean swapped = true;

		while(swapped) {
			swapped = false;

			for(int i = lo; i < hi-1; i++) {
				if(a[i] > a[i+1]) {
					swap(a, i, i + 1);
					swapped = true;
				}
			}

			if (!swapped) {
				break;
			}

			swapped = false;

			hi--;
			for(int i = hi - 1; i >= lo; i--) {
				if (a[i] > a[i+1]) {
					swap(a, i, i + 1);
					swapped = true;
				}
			}
			lo++;
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
	
	private static void sort(int[] a, int left, int right) {
		int lo = left;
		int hi = right;
		boolean swapped = true;

		while(swapped) {
			swapped = false;

			for(int i = lo; i < hi-1; i++) {
				if(a[i] > a[i+1]) {
					swap(a, i, i + 1);
					swapped = true;
				}
			}

			if (!swapped) {
				break;
			}

			swapped = false;

			hi--;
			for(int i = hi - 1; i >= lo; i--) {
				if (a[i] > a[i+1]) {
					swap(a, i, i + 1);
					swapped = true;
				}
			}
			lo++;
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
	
	private static void sort(long[] a, int left, int right) {
		int lo = left;
		int hi = right;
		boolean swapped = true;

		while(swapped) {
			swapped = false;

			for(int i = lo; i < hi-1; i++) {
				if(a[i] > a[i+1]) {
					swap(a, i, i + 1);
					swapped = true;
				}
			}

			if (!swapped) {
				break;
			}

			swapped = false;

			hi--;
			for(int i = hi - 1; i >= lo; i--) {
				if (a[i] > a[i+1]) {
					swap(a, i, i + 1);
					swapped = true;
				}
			}
			lo++;
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
	
	private static void sort(float[] a, int left, int right) {
		int lo = left;
		int hi = right;
		boolean swapped = true;

		while(swapped) {
			swapped = false;

			for(int i = lo; i < hi-1; i++) {
				if(a[i] > a[i+1]) {
					swap(a, i, i + 1);
					swapped = true;
				}
			}

			if (!swapped) {
				break;
			}

			swapped = false;

			hi--;
			for(int i = hi - 1; i >= lo; i--) {
				if (a[i] > a[i+1]) {
					swap(a, i, i + 1);
					swapped = true;
				}
			}
			lo++;
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
	
	private static void sort(double[] a, int left, int right) {
		int lo = left;
		int hi = right;
		boolean swapped = true;

		while(swapped) {
			swapped = false;

			for(int i = lo; i < hi-1; i++) {
				if(a[i] > a[i+1]) {
					swap(a, i, i + 1);
					swapped = true;
				}
			}

			if (!swapped) {
				break;
			}

			swapped = false;

			hi--;
			for(int i = hi - 1; i >= lo; i--) {
				if (a[i] > a[i+1]) {
					swap(a, i, i + 1);
					swapped = true;
				}
			}
			lo++;
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void sort(Object[] a, int left, int right) {
		int lo = left;
		int hi = right;
		boolean swapped = true;

		while(swapped) {
			swapped = false;

			for(int i = lo; i < hi-1; i++) {
				if(((Comparable)a[i]).compareTo(a[i+1]) > 0) {
					swap(a, i, i + 1);
					swapped = true;
				}
			}

			if (!swapped) {
				break;
			}

			swapped = false;

			hi--;
			for(int i = hi - 1; i >= lo; i--) {
				if (((Comparable)a[i]).compareTo(a[i+1]) > 0) {
					swap(a, i, i + 1);
					swapped = true;
				}
			}
			lo++;
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
	
	
	private static <T> void sort(T[] a, int left, int right, Comparator<? super T> c) {
		int lo = left;
		int hi = right;
		boolean swapped = true;

		while(swapped) {
			swapped = false;

			for(int i = lo; i < hi-1; i++) {
				if(c.compare(a[i], a[i+1]) > 0) {
					swap(a, i, i + 1);
					swapped = true;
				}
			}

			if (!swapped) {
				break;
			}

			swapped = false;

			hi--;
			for(int i = hi - 1; i >= lo; i--) {
				if (c.compare(a[i], a[i+1]) > 0) {
					swap(a, i, i + 1);
					swapped = true;
				}
			}
			lo++;
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
