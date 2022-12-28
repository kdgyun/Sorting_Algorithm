package CycleSort;

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

public class CycleSort {

	
	//============= primitive type ============//
	
	
	/*==========================
	 * sorting byte type array 
	 *==========================*/
	
	public static void sort(byte[] a) {
		sort(a, 0, a.length);
	}
	
	private static void sort(byte[] a, int left, int right) {
		for(int start = left; start < right; start++) {
			byte item = a[start];
			int pos = start;

			for (int i = start + 1; i < right; i++) {
				if (a[i] < item) {
					pos++;
				}
			}

			if (pos == start) {
				continue;
			}

			while (item == a[pos]) {
				pos++;
			}
			
			byte temp = item;
			item = a[pos];
			a[pos] = temp;
			

			while (pos != start) {
				pos = start;
				for (int i = start + 1; i < right; i++) {
					if (a[i] < item) {
						pos++;
					}
				}

				while (item == a[pos]) {
					pos++;
				}
				byte temp2 = item;
				item = a[pos];
				a[pos] = temp2;

			}
		}
	}


	
	
	
	/*==========================
	 * sorting char type array 
	 *==========================*/
	
	public static void sort(char[] a) {
		sort(a, 0, a.length);
	}
	
	private static void sort(char[] a, int left, int right) {
		for(int start = left; start < right; start++) {
			char item = a[start];
			int pos = start;

			for (int i = start + 1; i < right; i++) {
				if (a[i] < item) {
					pos++;
				}
			}

			if (pos == start) {
				continue;
			}

			while (item == a[pos]) {
				pos++;
			}
			
			char temp = item;
			item = a[pos];
			a[pos] = temp;
			

			while (pos != start) {
				pos = start;
				for (int i = start + 1; i < right; i++) {
					if (a[i] < item) {
						pos++;
					}
				}

				while (item == a[pos]) {
					pos++;
				}
				char temp2 = item;
				item = a[pos];
				a[pos] = temp2;

			}
		}
	}
	

	
	
	
	/*==========================
	 * sorting short type array 
	 *==========================*/
	
	public static void sort(short[] a) {
		sort(a, 0, a.length);
	}
	
	private static void sort(short[] a, int left, int right) {
		for(int start = left; start < right; start++) {
			short item = a[start];
			int pos = start;

			for (int i = start + 1; i < right; i++) {
				if (a[i] < item) {
					pos++;
				}
			}

			if (pos == start) {
				continue;
			}

			while (item == a[pos]) {
				pos++;
			}
			
			short temp = item;
			item = a[pos];
			a[pos] = temp;
			

			while (pos != start) {
				pos = start;
				for (int i = start + 1; i < right; i++) {
					if (a[i] < item) {
						pos++;
					}
				}

				while (item == a[pos]) {
					pos++;
				}
				short temp2 = item;
				item = a[pos];
				a[pos] = temp2;

			}
		}
	}
	
	

	
	
	/*==========================
	 * sorting int type array 
	 *==========================*/
	
	public static void sort(int[] a) {
		sort(a, 0, a.length);
	}
	
	private static void sort(int[] a, int left, int right) {
		for(int start = left; start < right; start++) {
			int item = a[start];
			int pos = start;

			for (int i = start + 1; i < right; i++) {
				if (a[i] < item) {
					pos++;
				}
			}

			if (pos == start) {
				continue;
			}

			while (item == a[pos]) {
				pos++;
			}
			
			int temp = item;
			item = a[pos];
			a[pos] = temp;
			

			while (pos != start) {
				pos = start;
				for (int i = start + 1; i < right; i++) {
					if (a[i] < item) {
						pos++;
					}
				}

				while (item == a[pos]) {
					pos++;
				}
				int temp2 = item;
				item = a[pos];
				a[pos] = temp2;

			}
		}
	}
	

	
	
	/*==========================
	 * sorting long type array 
	 *==========================*/
	
	public static void sort(long[] a) {
		sort(a, 0, a.length);
	}
	
	private static void sort(long[] a, int left, int right) {
		for(int start = left; start < right; start++) {
			long item = a[start];
			int pos = start;

			for (int i = start + 1; i < right; i++) {
				if (a[i] < item) {
					pos++;
				}
			}

			if (pos == start) {
				continue;
			}

			while (item == a[pos]) {
				pos++;
			}
			
			long temp = item;
			item = a[pos];
			a[pos] = temp;
			

			while (pos != start) {
				pos = start;
				for (int i = start + 1; i < right; i++) {
					if (a[i] < item) {
						pos++;
					}
				}

				while (item == a[pos]) {
					pos++;
				}
				long temp2 = item;
				item = a[pos];
				a[pos] = temp2;

			}
		}
	}
	

	
	
	/*==========================
	 * sorting float type array 
	 *==========================*/
	
	public static void sort(float[] a) {
		sort(a, 0, a.length);
	}
	
	private static void sort(float[] a, int left, int right) {
		for(int start = left; start < right; start++) {
			float item = a[start];
			int pos = start;

			for (int i = start + 1; i < right; i++) {
				if (a[i] < item) {
					pos++;
				}
			}

			if (pos == start) {
				continue;
			}

			while (item == a[pos]) {
				pos++;
			}
			
			float temp = item;
			item = a[pos];
			a[pos] = temp;
			

			while (pos != start) {
				pos = start;
				for (int i = start + 1; i < right; i++) {
					if (a[i] < item) {
						pos++;
					}
				}

				while (item == a[pos]) {
					pos++;
				}
				float temp2 = item;
				item = a[pos];
				a[pos] = temp2;

			}
		}
	}
	
	
	
	/*==========================
	 * sorting double type array 
	 *==========================*/
	
	public static void sort(double[] a) {
		sort(a, 0, a.length);
	}
	
	private static void sort(double[] a, int left, int right) {
		for(int start = left; start < right; start++) {
			double item = a[start];
			int pos = start;

			for (int i = start + 1; i < right; i++) {
				if (a[i] < item) {
					pos++;
				}
			}

			if (pos == start) {
				continue;
			}

			while (item == a[pos]) {
				pos++;
			}
			
			double temp = item;
			item = a[pos];
			a[pos] = temp;
			

			while (pos != start) {
				pos = start;
				for (int i = start + 1; i < right; i++) {
					if (a[i] < item) {
						pos++;
					}
				}

				while (item == a[pos]) {
					pos++;
				}
				double temp2 = item;
				item = a[pos];
				a[pos] = temp2;

			}
		}
	}
	
	
	//============= Object type ============//
	
	
	/*==========================
	 * sorting Object type array 
	 *==========================*/
	
	public static void sort(Object[] a) {
		sort(a, 0, a.length);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void sort(Object[] a, int left, int right) {
		for(int start = left; start < right; start++) {
			Comparable item = (Comparable)a[start];
			int pos = start;

			for (int i = start + 1; i < right; i++) {
				if (item.compareTo(a[i]) > 0) {
					pos++;
				}
			}

			if (pos == start) {
				continue;
			}

			while (item.compareTo(a[pos]) == 0) {
				pos++;
			}
			
			Object temp = item;
			item = (Comparable)a[pos];
			a[pos] = temp;
			

			while (pos != start) {
				pos = start;
				for (int i = start + 1; i < right; i++) {
					if (item.compareTo(a[i]) > 0) {
						pos++;
					}
				}

				while (item.compareTo(a[pos]) == 0) {
					pos++;
				}
				Object temp2 = item;
				item = (Comparable)a[pos];
				a[pos] = temp2;

			}
		}
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
		for(int start = left; start < right; start++) {
			T item = a[start];
			int pos = start;

			for (int i = start + 1; i < right; i++) {
				if (c.compare(item, a[i]) > 0) {
					pos++;
				}
			}

			if (pos == start) {
				continue;
			}

			while (c.compare(item, a[pos]) == 0) {
				pos++;
			}
			
			T temp = item;
			item = a[pos];
			a[pos] = temp;
			

			while (pos != start) {
				pos = start;
				for (int i = start + 1; i < right; i++) {
					if (c.compare(item, a[i]) > 0) {
						pos++;
					}
				}

				while (c.compare(item, a[pos]) == 0) {
					pos++;
				}
				T temp2 = item;
				item = a[pos];
				a[pos] = temp2;

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

