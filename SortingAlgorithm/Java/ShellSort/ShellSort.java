package ShellSort;


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

public class ShellSort {

	/**
	 * This sequence is based on the Ciura sequence.
	 * {@link https://en.wikipedia.org/wiki/Shellsort#Gap_sequences} 
	 * The number after 1750 is obtained by multiplying by 2.25.
	 */
	private final static int[] gap = 
		{1, 4, 10, 23, 57, 132, 301, 701, 1750, 3937, 	
		8858, 19930, 44842, 100894, 227011, 510774,
		1149241, 2585792, 5818032, 13090572, 29453787, 
		66271020, 149109795, 335497038, 754868335, 1698453753};	

	
	
	
	private static int getGap(int length) {
		int index = 0;
		int len = (int)(length / 2.25);	
		while (gap[index] < len) index++;
		return index;
	}
	
	
	//============= primitive type ============//

	
	
	/*==========================
	 * sorting byte type array 
	 *==========================*/
	
	public static void sort(byte[] a) {
		sort(a, a.length);
		
	}
	
 	private static void sort(byte[] a, int size) {
		int gapIndex = getGap(size);

		while(gapIndex >= 0) {
			int step = gap[gapIndex--];
			

			for(int i = step; i < size; i++) {
	
				byte target = a[i];
				int j = i;
				
				for (; j >= step && target < a[j - step]; j -= step) {
					a[j] = a[j - step];
				}
				a[j] = target;
			}
		}
	}

	
 	
	/*==========================
	 * sorting char type array 
	 *==========================*/
	
	public static void sort(char[] a) {
		sort(a, a.length);
		
	}
	
 	private static void sort(char[] a, int size) {
		int gapIndex = getGap(size);

		while(gapIndex >= 0) {
			int step = gap[gapIndex--];
			

			for(int i = step; i < size; i++) {
	
				char target = a[i];
				int j = i;
				
				for (; j >= step && target < a[j - step]; j -= step) {
					a[j] = a[j - step];
				}
				a[j] = target;
			}
		}
	}

	
 	
	/*==========================
	 * sorting short type array 
	 *==========================*/
	
	public static void sort(short[] a) {
		sort(a, a.length);
		
	}
	
 	private static void sort(short[] a, int size) {
		int gapIndex = getGap(size);

		while(gapIndex >= 0) {
			int step = gap[gapIndex--];
			

			for(int i = step; i < size; i++) {
	
				short target = a[i];
				int j = i;
				
				for (; j >= step && target < a[j - step]; j -= step) {
					a[j] = a[j - step];
				}
				a[j] = target;
			}
		}
	}

	
 	
	
	/*==========================
	 * sorting int type array 
	 *==========================*/
	
	public static void sort(int[] a) {
		sort(a, a.length);
		
	}

	
 	private static void sort(int[] a, int size) {
		int gapIndex = getGap(size);

		while(gapIndex >= 0) {
			int step = gap[gapIndex--];
			

			for(int i = step; i < size; i++) {
	
				int target = a[i];
				int j = i;
				
				for (; j >= step && target < a[j - step]; j -= step) {
					a[j] = a[j - step];
				}
				a[j] = target;
			}
		}
	}

 	
 	
	
	/*==========================
	 * sorting long type array 
	 *==========================*/
	
	public static void sort(long[] a) {
		sort(a, a.length);
		
	}

	
 	private static void sort(long[] a, int size) {
		int gapIndex = getGap(size);

		while(gapIndex >= 0) {
			int step = gap[gapIndex--];
			

			for(int i = step; i < size; i++) {
	
				long target = a[i];
				int j = i;
				
				for (; j >= step && target < a[j - step]; j -= step) {
					a[j] = a[j - step];
				}
				a[j] = target;
			}
		}
	}

 	
 	
	
	/*==========================
	 * sorting float type array 
	 *==========================*/
	
	public static void sort(float[] a) {
		sort(a, a.length);
		
	}

	
 	private static void sort(float[] a, int size) {
		int gapIndex = getGap(size);

		while(gapIndex >= 0) {
			int step = gap[gapIndex--];
			

			for(int i = step; i < size; i++) {
	
				float target = a[i];
				int j = i;
				
				for (; j >= step && target < a[j - step]; j -= step) {
					a[j] = a[j - step];
				}
				a[j] = target;
			}
		}
	}

 	
 	
	
	/*==========================
	 * sorting double type array 
	 *==========================*/
	
	public static void sort(double[] a) {
		sort(a, a.length);
		
	}

	
 	private static void sort(double[] a, int size) {
		int gapIndex = getGap(size);

		while(gapIndex >= 0) {
			int step = gap[gapIndex--];
			

			for(int i = step; i < size; i++) {
	
				double target = a[i];
				int j = i;
				
				for (; j >= step && target < a[j - step]; j -= step) {
					a[j] = a[j - step];
				}
				a[j] = target;
			}
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
		int gapIndex = getGap(size);

		while(gapIndex >= 0) {
			int step = gap[gapIndex--];
			

			for(int i = step; i < size; i++) {
	
				Object target = a[i];
				int j = i;
				
				for (; j >= step && ((Comparable)target).compareTo(a[j - step]) < 0; j -= step) {
					a[j] = a[j - step];
				}
				a[j] = target;
			}
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
		int gapIndex = getGap(size);

		while(gapIndex >= 0) {
			int step = gap[gapIndex--];
			

			for(int i = step; i < size; i++) {
	
				T target = a[i];
				int j = i;
				
				for (; j >= step && c.compare(target, a[j - step]) < 0; j -= step) {
					a[j] = a[j - step];
				}
				a[j] = target;
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
