package ParallelSort;

import java.util.Comparator;
import java.util.concurrent.RecursiveAction;

import Utils.Convert;
import Utils.Order;




public class ParallelRPQuickSort {

	final static int THRESHOLD = 1 << 13;
	
	// ============= primitive type ============//

	/*
	 * ========================== 
	 *  sorting byte type array
	 * ==========================
	 */
	
	public static void sort(byte[] a) {
		new ParRPQuickByte.QSort(a, 0, a.length - 1).invoke();
	}
	
	
	private static final class ParRPQuickByte {	
		private static final class QSort extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final byte[] a;
			final int lo;
			final int hi;
			
			QSort(byte[] a, int lo, int hi) {
				this.a = a;
				this.lo = lo;
				this.hi = hi;
			}
			
			public final void compute() {
				
				if(lo >= hi) {
					return;
				}
				
				int pivot = partition(a, lo, hi);
				if(hi - lo < THRESHOLD) {
					
					
					qsort(a, lo, pivot - 1);
					qsort(a, pivot + 1, hi);
				}
				else {
					QSort left = new QSort(a, lo, pivot - 1);
					QSort right = new QSort(a, pivot + 1, hi);
					left.fork();
					right.fork();
					left.join();
					right.join();
				}
				
			}
		}

		
		private static void qsort(byte[] a, int lo, int hi) {
			
			if(lo >= hi) {
				return;
			}
			int pivot = partition(a, lo, hi);	
			
			qsort(a, lo, pivot - 1);
			qsort(a, pivot + 1, hi);
			
		}
		private static int partition(byte[] a, int left, int right) {
			
			int lo = left;
			int hi = right;
			byte pivot = a[right];		
			while(lo < hi) {
				

				while(a[lo] < pivot && lo < hi) {
					lo++;
				}
				while(a[hi] >= pivot && lo < hi) {
					hi--;
				}
				
				swap(a, lo, hi);
			}
			

			swap(a, right, hi);
			
			return hi;
		}
		
		private static void swap(byte[] a, int i, int j) {
			byte temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}
	
	
	
	/*
	 * ========================== 
	 *  sorting char type array
	 * ==========================
	 */
	
	public static void sort(char[] a) {
		new ParRPQuickChar.QSort(a, 0, a.length - 1).invoke();
	}
	
	
	private static final class ParRPQuickChar {	
		private static final class QSort extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final char[] a;
			final int lo;
			final int hi;
			
			QSort(char[] a, int lo, int hi) {
				this.a = a;
				this.lo = lo;
				this.hi = hi;
			}
			
			public final void compute() {
				
				if(lo >= hi) {
					return;
				}
				
				int pivot = partition(a, lo, hi);
				if(hi - lo < THRESHOLD) {
					
					
					qsort(a, lo, pivot - 1);
					qsort(a, pivot + 1, hi);
				}
				else {
					QSort left = new QSort(a, lo, pivot - 1);
					QSort right = new QSort(a, pivot + 1, hi);
					left.fork();
					right.fork();
					left.join();
					right.join();
				}
				
			}
		}

		
		private static void qsort(char[] a, int lo, int hi) {
			
			if(lo >= hi) {
				return;
			}
			int pivot = partition(a, lo, hi);	
			
			qsort(a, lo, pivot - 1);
			qsort(a, pivot + 1, hi);
			
		}
		private static int partition(char[] a, int left, int right) {
			
			int lo = left;
			int hi = right;
			char pivot = a[right];		
			while(lo < hi) {
				

				while(a[lo] < pivot && lo < hi) {
					lo++;
				}
				while(a[hi] >= pivot && lo < hi) {
					hi--;
				}
				
				swap(a, lo, hi);
			}
			

			swap(a, right, hi);
			
			return hi;
		}
		
		private static void swap(char[] a, int i, int j) {
			char temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}
	
	
	
	
	
	
	
	/*
	 * ========================== 
	 *  sorting short type array
	 * ==========================
	 */
	
	public static void sort(short[] a) {
		new ParRPQuickShort.QSort(a, 0, a.length - 1).invoke();
	}
	
	
	private static final class ParRPQuickShort {	
		private static final class QSort extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final short[] a;
			final int lo;
			final int hi;
			
			QSort(short[] a, int lo, int hi) {
				this.a = a;
				this.lo = lo;
				this.hi = hi;
			}
			
			public final void compute() {
				
				if(lo >= hi) {
					return;
				}
				
				int pivot = partition(a, lo, hi);
				if(hi - lo < THRESHOLD) {
					
					
					qsort(a, lo, pivot - 1);
					qsort(a, pivot + 1, hi);
				}
				else {
					QSort left = new QSort(a, lo, pivot - 1);
					QSort right = new QSort(a, pivot + 1, hi);
					left.fork();
					right.fork();
					left.join();
					right.join();
				}
				
			}
		}

		
		private static void qsort(short[] a, int lo, int hi) {
			
			if(lo >= hi) {
				return;
			}
			int pivot = partition(a, lo, hi);	
			
			qsort(a, lo, pivot - 1);
			qsort(a, pivot + 1, hi);
			
		}
		private static int partition(short[] a, int left, int right) {
			
			int lo = left;
			int hi = right;
			short pivot = a[right];		
			while(lo < hi) {
				

				while(a[lo] < pivot && lo < hi) {
					lo++;
				}
				while(a[hi] >= pivot && lo < hi) {
					hi--;
				}
				
				swap(a, lo, hi);
			}
			

			swap(a, right, hi);
			
			return hi;
		}
		
		private static void swap(short[] a, int i, int j) {
			short temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}
	
	
	
	
	/*
	 * ========================== 
	 *  sorting int type array
	 * ==========================
	 */
	
	public static void sort(int[] a) {
		new ParRPQuickInt.QSort(a, 0, a.length - 1).invoke();
	}
	
	
	private static final class ParRPQuickInt {	
		private static final class QSort extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final int[] a;
			final int lo;
			final int hi;
			
			QSort(int[] a, int lo, int hi) {
				this.a = a;
				this.lo = lo;
				this.hi = hi;
			}
			
			public final void compute() {
				
				if(lo >= hi) {
					return;
				}
				
				int pivot = partition(a, lo, hi);
				if(hi - lo < THRESHOLD) {
					
					
					qsort(a, lo, pivot - 1);
					qsort(a, pivot + 1, hi);
				}
				else {
					QSort left = new QSort(a, lo, pivot - 1);
					QSort right = new QSort(a, pivot + 1, hi);
					left.fork();
					right.fork();
					left.join();
					right.join();
				}
				
			}
		}

		
		private static void qsort(int[] a, int lo, int hi) {
			
			if(lo >= hi) {
				return;
			}
			int pivot = partition(a, lo, hi);	
			
			qsort(a, lo, pivot - 1);
			qsort(a, pivot + 1, hi);
			
		}
		private static int partition(int[] a, int left, int right) {
			
			int lo = left;
			int hi = right;
			int pivot = a[right];		
			while(lo < hi) {
				

				while(a[lo] < pivot && lo < hi) {
					lo++;
				}
				while(a[hi] >= pivot && lo < hi) {
					hi--;
				}
				
				swap(a, lo, hi);
			}
			

			swap(a, right, hi);
			
			return hi;
		}
		
		private static void swap(int[] a, int i, int j) {
			int temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}
	
	
	
	
	
	/*
	 * ========================== 
	 *  sorting long type array
	 * ==========================
	 */
	
	public static void sort(long[] a) {
		new ParRPQuickLong.QSort(a, 0, a.length - 1).invoke();
	}
	
	
	private static final class ParRPQuickLong {	
		private static final class QSort extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final long[] a;
			final int lo;
			final int hi;
			
			QSort(long[] a, int lo, int hi) {
				this.a = a;
				this.lo = lo;
				this.hi = hi;
			}
			
			public final void compute() {
				
				if(lo >= hi) {
					return;
				}
				
				int pivot = partition(a, lo, hi);
				if(hi - lo < THRESHOLD) {
					
					
					qsort(a, lo, pivot - 1);
					qsort(a, pivot + 1, hi);
				}
				else {
					QSort left = new QSort(a, lo, pivot - 1);
					QSort right = new QSort(a, pivot + 1, hi);
					left.fork();
					right.fork();
					left.join();
					right.join();
				}
				
			}
		}

		
		private static void qsort(long[] a, int lo, int hi) {
			
			if(lo >= hi) {
				return;
			}
			int pivot = partition(a, lo, hi);	
			
			qsort(a, lo, pivot - 1);
			qsort(a, pivot + 1, hi);
			
		}
		private static int partition(long[] a, int left, int right) {
			
			int lo = left;
			int hi = right;
			long pivot = a[right];		
			while(lo < hi) {
				

				while(a[lo] < pivot && lo < hi) {
					lo++;
				}
				while(a[hi] >= pivot && lo < hi) {
					hi--;
				}
				
				swap(a, lo, hi);
			}
			

			swap(a, right, hi);
			
			return hi;
		}
		
		private static void swap(long[] a, int i, int j) {
			long temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}
	
	
	
	
	/*
	 * ========================== 
	 *  sorting float type array
	 * ==========================
	 */
	
	public static void sort(float[] a) {
		new ParRPQuickFloat.QSort(a, 0, a.length - 1).invoke();
	}
	
	
	private static final class ParRPQuickFloat {	
		private static final class QSort extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final float[] a;
			final int lo;
			final int hi;
			
			QSort(float[] a, int lo, int hi) {
				this.a = a;
				this.lo = lo;
				this.hi = hi;
			}
			
			public final void compute() {
				
				if(lo >= hi) {
					return;
				}
				
				int pivot = partition(a, lo, hi);
				if(hi - lo < THRESHOLD) {
					
					
					qsort(a, lo, pivot - 1);
					qsort(a, pivot + 1, hi);
				}
				else {
					QSort left = new QSort(a, lo, pivot - 1);
					QSort right = new QSort(a, pivot + 1, hi);
					left.fork();
					right.fork();
					left.join();
					right.join();
				}
				
			}
		}

		
		private static void qsort(float[] a, int lo, int hi) {
			
			if(lo >= hi) {
				return;
			}
			int pivot = partition(a, lo, hi);	
			
			qsort(a, lo, pivot - 1);
			qsort(a, pivot + 1, hi);
		}
		private static int partition(float[] a, int left, int right) {
			
			int lo = left;
			int hi = right;
			float pivot = a[right];		
			while(lo < hi) {
				

				while(a[lo] < pivot && lo < hi) {
					lo++;
				}
				while(a[hi] >= pivot && lo < hi) {
					hi--;
				}
				
				swap(a, lo, hi);
			}
			

			swap(a, right, hi);
			
			return hi;
		}
		
		private static void swap(float[] a, int i, int j) {
			float temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}
	
	
	
	
	
	
	/*
	 * ========================== 
	 *  sorting double type array
	 * ==========================
	 */
	
	public static void sort(double[] a) {
		new ParRPQuickDouble.QSort(a, 0, a.length - 1).invoke();
	}
	
	
	private static final class ParRPQuickDouble {	
		private static final class QSort extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final double[] a;
			final int lo;
			final int hi;
			
			QSort(double[] a, int lo, int hi) {
				this.a = a;
				this.lo = lo;
				this.hi = hi;
			}
			
			public final void compute() {
				
				if(lo >= hi) {
					return;
				}
				
				int pivot = partition(a, lo, hi);
				if(hi - lo < THRESHOLD) {
					
					
					qsort(a, lo, pivot - 1);
					qsort(a, pivot + 1, hi);
				}
				else {
					QSort left = new QSort(a, lo, pivot - 1);
					QSort right = new QSort(a, pivot + 1, hi);
					left.fork();
					right.fork();
					left.join();
					right.join();
				}
				
			}
		}

		
		private static void qsort(double[] a, int lo, int hi) {
			
			if(lo >= hi) {
				return;
			}
			int pivot = partition(a, lo, hi);	
			
			qsort(a, lo, pivot - 1);
			qsort(a, pivot + 1, hi);
		}
		private static int partition(double[] a, int left, int right) {
			
			int lo = left;
			int hi = right;
			double pivot = a[right];		
			while(lo < hi) {
				

				while(a[lo] < pivot && lo < hi) {
					lo++;
				}
				while(a[hi] >= pivot && lo < hi) {
					hi--;
				}
				
				swap(a, lo, hi);
			}
			

			swap(a, right, hi);
			
			return hi;
		}
		
		private static void swap(double[] a, int i, int j) {
			double temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
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
			new ParRPQuickDComparator.QSort(a, 0, a.length - 1, c).invoke();
		}
	}
	
	public static void sort(Object[] a) {
		new ParLRQuickDComparable.QSort(a, 0, a.length - 1).invoke();
	}
	
	private static final class ParLRQuickDComparable {	
		private static final class QSort extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final Object[] a;
			final int lo;
			final int hi;
			
			QSort(Object[] a, int lo, int hi) {
				this.a = a;
				this.lo = lo;
				this.hi = hi;
			}
			
			public final void compute() {
				
				if(lo >= hi) {
					return;
				}
				
				int pivot = partition(a, lo, hi);
				if(hi - lo < THRESHOLD) {
					
					
					qsort(a, lo, pivot - 1);
					qsort(a, pivot + 1, hi);
				}
				else {
					QSort left = new QSort(a, lo, pivot - 1);
					QSort right = new QSort(a, pivot + 1, hi);
					left.fork();
					right.fork();
					left.join();
					right.join();
				}
				
			}
		}
		

		private static void qsort(Object[] a, int lo, int hi) {
			

			if(lo >= hi) {
				return;
			}

			int pivot = partition(a, lo, hi);	
			
			qsort(a, lo, pivot - 1);
			qsort(a, pivot + 1, hi);
		}
		

		@SuppressWarnings({ "rawtypes", "unchecked" })
		private static int partition(Object[] a, int left, int right) {
			
			int lo = left;
			int hi = right;
			Comparable pivot = ((Comparable)a[right]);		
			while(lo < hi) {
				
				while(pivot.compareTo(a[lo]) > 0 && lo < hi) {
					lo++;
				}
				while(pivot.compareTo(a[hi]) <= 0 && lo < hi) {
					hi--;
				}
				swap(a, lo, hi);
			}
			

			swap(a, right, hi);
			
			return hi;
		}
		
		private static void swap(Object[] a, int i, int j) {
			Object temp = a[i];
			a[i] = a[j];
			a[j] = temp;
		}
	}
	

	
	private static final class ParRPQuickDComparator {
		private static final class QSort extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final Object[] a;
			final int lo;
			final int hi;
			final Comparator<?> c;
			
			QSort(Object[] a, int lo, int hi, Comparator<?> c) {
				this.a = a;
				this.lo = lo;
				this.hi = hi;
				this.c = c;
			}
			
			public final void compute() {

				if(lo >= hi) {
					return;
				}
				int pivot = partition(a, lo, hi, c);	
				if(hi - lo < THRESHOLD) {
					
					
					qsort(a, lo, pivot - 1, c);
					qsort(a, pivot + 1, hi, c);
				}

				else {
					QSort left = new QSort(a, lo, pivot - 1, c);
					QSort right = new QSort(a, pivot + 1, hi, c);
					
					left.fork();
					right.fork();
					left.join();
					right.join();
				}

			}
		}
		
		


		@SuppressWarnings("rawtypes")
		private static void qsort(Object[] a, int lo, int hi, Comparator c) {

			if(lo >= hi) {
				return;
			}

			int pivot = partition(a, lo, hi, c);	
			
			qsort(a, lo, pivot - 1, c);
			qsort(a, pivot + 1, hi, c);
		}
		

		@SuppressWarnings({ "rawtypes", "unchecked" })
		private static int partition(Object[] a, int left, int right, Comparator c) {
			
			int lo = left;
			int hi = right;
			Object pivot = a[right];

			while(lo < hi) {
				while(c.compare(a[lo], pivot) < 0 && lo < hi) { 
					lo++;
				}
				while(c.compare(pivot, a[hi]) <= 0 && lo < hi) {
					hi--;
				}

				swap(a, lo, hi);
			}
			

			swap(a, right, hi);
			
			return hi;
		}
		
		

		private static void swap(Object[] a, int i, int j) {
			Object temp = a[i];
			a[i] = a[j];
			a[j] = temp;
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

