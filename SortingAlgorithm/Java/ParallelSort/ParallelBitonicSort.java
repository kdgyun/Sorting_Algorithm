package ParallelSort;

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
import java.util.concurrent.RecursiveAction;

import Utils.Convert;
import Utils.Order;

public class ParallelBitonicSort {

	final static int THRESHOLD = 1 << 13;
	
	final static int greatestPowerOfTwoLessThan(int len) {
		int k = 1;
		while (k > 0 && k < len) {
			k <<= 1;
		}
		return k >>> 1;
	}
	
	//============= primitive type ============//
	
	
	/*==========================
	 * sorting byte type array 
	 *==========================*/
	
	public static void sort(byte[] a) {
		new ParallelBitonicSortForByte.BitonicSort(a, 0, a.length, true).invoke();
	}
	
	private static final class ParallelBitonicSortForByte {
		private static final class BitonicSort extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final byte[] a;
			final int lo;
			final int len;
			final boolean dir;
			
			BitonicSort(byte[] a, int lo, int len, boolean dir) {
				this.a = a;
				this.lo = lo;
				this.len = len;
				this.dir = dir;
			}
			
			public final void compute() {
				if(len < 2) {
					return;
				}
				int mid = len >>> 1;
	
				if(len - lo < THRESHOLD) {					
					sort(a, lo, mid, !dir);
					sort(a, lo + mid, len - mid, dir);
					merge(a, lo, len, dir);
				}
				else {
					BitonicSort left = new BitonicSort(a, lo, mid, !dir);
					BitonicSort right = new BitonicSort(a, lo + mid, len - mid, dir);
					left.fork();
					right.compute();
					left.join();
					new Merger(a, lo, len, dir).invoke();
				}
			}
			
		}
		
		private static final class Merger extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final byte[] a;
			final int lo;
			final int len;
			final boolean dir;
			
			Merger(byte[] a, int lo, int len, boolean dir) {
				this.a = a;
				this.lo = lo;
				this.len = len;
				this.dir = dir;
			}
			
			public final void compute() {
				int m = greatestPowerOfTwoLessThan(len);
				
				for(int i = lo; i < lo + len - m; i++) {
					if(dir == (a[i] > a[i + m])) {
						swap(a, i, i + m);
					}
				}
				
				if(len < THRESHOLD) {
					merge(a, lo, m, dir);
					merge(a, lo + m, len - m, dir);
				} 
				else {
					Merger fm = new Merger(a, lo, m, dir);
					Merger lm = new Merger(a, lo + m, len - m, dir);
					fm.fork();
					lm.compute();
					fm.join();
					
				}
			}
		}
	}
	
	private static void sort(byte[] a, int lo, int len, boolean dir) {
		if(len > 1) {
			int mid = (len >>> 1);
			sort(a, lo, mid, !dir);
			sort(a, lo + mid, len - mid, dir);
			merge(a, lo, len, dir);
		}
	}
	
	private static void merge(byte[] a, int lo, int len, boolean dir) {
		if(len > 1) {
			int m = greatestPowerOfTwoLessThan(len);
			
			for(int i = lo; i < lo + len - m; i++) {
				if(dir == (a[i] > a[i + m])) {
					swap(a, i, i + m);
				}
			}
			merge(a, lo, m, dir);
			merge(a, lo + m, len - m, dir);
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
		new ParallelBitonicSortForChar.BitonicSort(a, 0, a.length, true).invoke();
	}
	
	private static final class ParallelBitonicSortForChar {
		private static final class BitonicSort extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final char[] a;
			final int lo;
			final int len;
			final boolean dir;
			
			BitonicSort(char[] a, int lo, int len, boolean dir) {
				this.a = a;
				this.lo = lo;
				this.len = len;
				this.dir = dir;
			}
			
			public final void compute() {
				if(len < 2) {
					return;
				}
				int mid = len >>> 1;
	
				if(len - lo < THRESHOLD) {					
					sort(a, lo, mid, !dir);
					sort(a, lo + mid, len - mid, dir);
					merge(a, lo, len, dir);
				}
				else {
					BitonicSort left = new BitonicSort(a, lo, mid, !dir);
					BitonicSort right = new BitonicSort(a, lo + mid, len - mid, dir);
					left.fork();
					right.compute();
					left.join();
					new Merger(a, lo, len, dir).invoke();
				}
			}
			
		}
		
		private static final class Merger extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final char[] a;
			final int lo;
			final int len;
			final boolean dir;
			
			Merger(char[] a, int lo, int len, boolean dir) {
				this.a = a;
				this.lo = lo;
				this.len = len;
				this.dir = dir;
			}
			
			public final void compute() {
				int m = greatestPowerOfTwoLessThan(len);
				
				for(int i = lo; i < lo + len - m; i++) {
					if(dir == (a[i] > a[i + m])) {
						swap(a, i, i + m);
					}
				}
				
				if(len < THRESHOLD) {
					merge(a, lo, m, dir);
					merge(a, lo + m, len - m, dir);
				} 
				else {
					Merger fm = new Merger(a, lo, m, dir);
					Merger lm = new Merger(a, lo + m, len - m, dir);
					fm.fork();
					lm.compute();
					fm.join();
					
				}
			}
		}
	}
	
	private static void sort(char[] a, int lo, int len, boolean dir) {
		if(len > 1) {
			int mid = (len >>> 1);
			sort(a, lo, mid, !dir);
			sort(a, lo + mid, len - mid, dir);
			merge(a, lo, len, dir);
		}
	}
	
	private static void merge(char[] a, int lo, int len, boolean dir) {
		if(len > 1) {
			int m = greatestPowerOfTwoLessThan(len);
			
			for(int i = lo; i < lo + len - m; i++) {
				if(dir == (a[i] > a[i + m])) {
					swap(a, i, i + m);
				}
			}
			merge(a, lo, m, dir);
			merge(a, lo + m, len - m, dir);
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
		new ParallelBitonicSortForShort.BitonicSort(a, 0, a.length, true).invoke();
	}
	
	private static final class ParallelBitonicSortForShort {
		private static final class BitonicSort extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final short[] a;
			final int lo;
			final int len;
			final boolean dir;
			
			BitonicSort(short[] a, int lo, int len, boolean dir) {
				this.a = a;
				this.lo = lo;
				this.len = len;
				this.dir = dir;
			}
			
			public final void compute() {
				if(len < 2) {
					return;
				}
				int mid = len >>> 1;
	
				if(len - lo < THRESHOLD) {					
					sort(a, lo, mid, !dir);
					sort(a, lo + mid, len - mid, dir);
					merge(a, lo, len, dir);
				}
				else {
					BitonicSort left = new BitonicSort(a, lo, mid, !dir);
					BitonicSort right = new BitonicSort(a, lo + mid, len - mid, dir);
					left.fork();
					right.compute();
					left.join();
					new Merger(a, lo, len, dir).invoke();
				}
			}
			
		}
		
		private static final class Merger extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final short[] a;
			final int lo;
			final int len;
			final boolean dir;
			
			Merger(short[] a, int lo, int len, boolean dir) {
				this.a = a;
				this.lo = lo;
				this.len = len;
				this.dir = dir;
			}
			
			public final void compute() {
				int m = greatestPowerOfTwoLessThan(len);
				
				for(int i = lo; i < lo + len - m; i++) {
					if(dir == (a[i] > a[i + m])) {
						swap(a, i, i + m);
					}
				}
				
				if(len < THRESHOLD) {
					merge(a, lo, m, dir);
					merge(a, lo + m, len - m, dir);
				} 
				else {
					Merger fm = new Merger(a, lo, m, dir);
					Merger lm = new Merger(a, lo + m, len - m, dir);
					fm.fork();
					lm.compute();
					fm.join();
					
				}
			}
		}
	}
	
	private static void sort(short[] a, int lo, int len, boolean dir) {
		if(len > 1) {
			int mid = (len >>> 1);
			sort(a, lo, mid, !dir);
			sort(a, lo + mid, len - mid, dir);
			merge(a, lo, len, dir);
		}
	}
	
	private static void merge(short[] a, int lo, int len, boolean dir) {
		if(len > 1) {
			int m = greatestPowerOfTwoLessThan(len);
			
			for(int i = lo; i < lo + len - m; i++) {
				if(dir == (a[i] > a[i + m])) {
					swap(a, i, i + m);
				}
			}
			merge(a, lo, m, dir);
			merge(a, lo + m, len - m, dir);
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
		new ParallelBitonicSortForInt.BitonicSort(a, 0, a.length, true).invoke();
	}
	
	private static final class ParallelBitonicSortForInt {
		private static final class BitonicSort extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final int[] a;
			final int lo;
			final int len;
			final boolean dir;
			
			BitonicSort(int[] a, int lo, int len, boolean dir) {
				this.a = a;
				this.lo = lo;
				this.len = len;
				this.dir = dir;
			}
			
			public final void compute() {
				if(len < 2) {
					return;
				}
				int mid = len >>> 1;
	
				if(len - lo < THRESHOLD) {					
					sort(a, lo, mid, !dir);
					sort(a, lo + mid, len - mid, dir);
					merge(a, lo, len, dir);
				}
				else {
					BitonicSort left = new BitonicSort(a, lo, mid, !dir);
					BitonicSort right = new BitonicSort(a, lo + mid, len - mid, dir);
					left.fork();
					right.compute();
					left.join();
					new Merger(a, lo, len, dir).invoke();
				}
			}
			
		}
		
		private static final class Merger extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final int[] a;
			final int lo;
			final int len;
			final boolean dir;
			
			Merger(int[] a, int lo, int len, boolean dir) {
				this.a = a;
				this.lo = lo;
				this.len = len;
				this.dir = dir;
			}
			
			public final void compute() {
				int m = greatestPowerOfTwoLessThan(len);
				
				for(int i = lo; i < lo + len - m; i++) {
					if(dir == (a[i] > a[i + m])) {
						swap(a, i, i + m);
					}
				}
				
				if(len < THRESHOLD) {
					merge(a, lo, m, dir);
					merge(a, lo + m, len - m, dir);
				} 
				else {
					Merger fm = new Merger(a, lo, m, dir);
					Merger lm = new Merger(a, lo + m, len - m, dir);
					fm.fork();
					lm.compute();
					fm.join();
					
				}
			}
		}
	}
	
	private static void sort(int[] a, int lo, int len, boolean dir) {
		if(len > 1) {
			int mid = (len >>> 1);
			sort(a, lo, mid, !dir);
			sort(a, lo + mid, len - mid, dir);
			merge(a, lo, len, dir);
		}
	}
	
	private static void merge(int[] a, int lo, int len, boolean dir) {
		if(len > 1) {
			int m = greatestPowerOfTwoLessThan(len);
			
			for(int i = lo; i < lo + len - m; i++) {
				if(dir == (a[i] > a[i + m])) {
					swap(a, i, i + m);
				}
			}
			merge(a, lo, m, dir);
			merge(a, lo + m, len - m, dir);
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
		new ParallelBitonicSortForLong.BitonicSort(a, 0, a.length, true).invoke();
	}
	
	private static final class ParallelBitonicSortForLong {
		private static final class BitonicSort extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final long[] a;
			final int lo;
			final int len;
			final boolean dir;
			
			BitonicSort(long[] a, int lo, int len, boolean dir) {
				this.a = a;
				this.lo = lo;
				this.len = len;
				this.dir = dir;
			}
			
			public final void compute() {
				if(len < 2) {
					return;
				}
				int mid = len >>> 1;
	
				if(len - lo < THRESHOLD) {					
					sort(a, lo, mid, !dir);
					sort(a, lo + mid, len - mid, dir);
					merge(a, lo, len, dir);
				}
				else {
					BitonicSort left = new BitonicSort(a, lo, mid, !dir);
					BitonicSort right = new BitonicSort(a, lo + mid, len - mid, dir);
					left.fork();
					right.compute();
					left.join();
					new Merger(a, lo, len, dir).invoke();
				}
			}
			
		}
		
		private static final class Merger extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final long[] a;
			final int lo;
			final int len;
			final boolean dir;
			
			Merger(long[] a, int lo, int len, boolean dir) {
				this.a = a;
				this.lo = lo;
				this.len = len;
				this.dir = dir;
			}
			
			public final void compute() {
				int m = greatestPowerOfTwoLessThan(len);
				
				for(int i = lo; i < lo + len - m; i++) {
					if(dir == (a[i] > a[i + m])) {
						swap(a, i, i + m);
					}
				}
				
				if(len < THRESHOLD) {
					merge(a, lo, m, dir);
					merge(a, lo + m, len - m, dir);
				} 
				else {
					Merger fm = new Merger(a, lo, m, dir);
					Merger lm = new Merger(a, lo + m, len - m, dir);
					fm.fork();
					lm.compute();
					fm.join();
					
				}
			}
		}
	}
	
	private static void sort(long[] a, int lo, int len, boolean dir) {
		if(len > 1) {
			int mid = (len >>> 1);
			sort(a, lo, mid, !dir);
			sort(a, lo + mid, len - mid, dir);
			merge(a, lo, len, dir);
		}
	}
	
	private static void merge(long[] a, int lo, int len, boolean dir) {
		if(len > 1) {
			int m = greatestPowerOfTwoLessThan(len);
			
			for(int i = lo; i < lo + len - m; i++) {
				if(dir == (a[i] > a[i + m])) {
					swap(a, i, i + m);
				}
			}
			merge(a, lo, m, dir);
			merge(a, lo + m, len - m, dir);
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
		new ParallelBitonicSortForFloat.BitonicSort(a, 0, a.length, true).invoke();
	}
	
	private static final class ParallelBitonicSortForFloat {
		private static final class BitonicSort extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final float[] a;
			final int lo;
			final int len;
			final boolean dir;
			
			BitonicSort(float[] a, int lo, int len, boolean dir) {
				this.a = a;
				this.lo = lo;
				this.len = len;
				this.dir = dir;
			}
			
			public final void compute() {
				if(len < 2) {
					return;
				}
				int mid = len >>> 1;
	
				if(len - lo < THRESHOLD) {					
					sort(a, lo, mid, !dir);
					sort(a, lo + mid, len - mid, dir);
					merge(a, lo, len, dir);
				}
				else {
					BitonicSort left = new BitonicSort(a, lo, mid, !dir);
					BitonicSort right = new BitonicSort(a, lo + mid, len - mid, dir);
					left.fork();
					right.compute();
					left.join();
					new Merger(a, lo, len, dir).invoke();
				}
			}
			
		}
		
		private static final class Merger extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final float[] a;
			final int lo;
			final int len;
			final boolean dir;
			
			Merger(float[] a, int lo, int len, boolean dir) {
				this.a = a;
				this.lo = lo;
				this.len = len;
				this.dir = dir;
			}
			
			public final void compute() {
				int m = greatestPowerOfTwoLessThan(len);
				
				for(int i = lo; i < lo + len - m; i++) {
					if(dir == (a[i] > a[i + m])) {
						swap(a, i, i + m);
					}
				}
				
				if(len < THRESHOLD) {
					merge(a, lo, m, dir);
					merge(a, lo + m, len - m, dir);
				} 
				else {
					Merger fm = new Merger(a, lo, m, dir);
					Merger lm = new Merger(a, lo + m, len - m, dir);
					fm.fork();
					lm.compute();
					fm.join();
					
				}
			}
		}
	}
	
	private static void sort(float[] a, int lo, int len, boolean dir) {
		if(len > 1) {
			int mid = (len >>> 1);
			sort(a, lo, mid, !dir);
			sort(a, lo + mid, len - mid, dir);
			merge(a, lo, len, dir);
		}
	}
	
	private static void merge(float[] a, int lo, int len, boolean dir) {
		if(len > 1) {
			int m = greatestPowerOfTwoLessThan(len);
			
			for(int i = lo; i < lo + len - m; i++) {
				if(dir == (a[i] > a[i + m])) {
					swap(a, i, i + m);
				}
			}
			merge(a, lo, m, dir);
			merge(a, lo + m, len - m, dir);
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
		new ParallelBitonicSortForDouble.BitonicSort(a, 0, a.length, true).invoke();
	}
	
	private static final class ParallelBitonicSortForDouble {
		private static final class BitonicSort extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final double[] a;
			final int lo;
			final int len;
			final boolean dir;
			
			BitonicSort(double[] a, int lo, int len, boolean dir) {
				this.a = a;
				this.lo = lo;
				this.len = len;
				this.dir = dir;
			}
			
			public final void compute() {
				if(len < 2) {
					return;
				}
				int mid = len >>> 1;
	
				if(len - lo < THRESHOLD) {					
					sort(a, lo, mid, !dir);
					sort(a, lo + mid, len - mid, dir);
					merge(a, lo, len, dir);
				}
				else {
					BitonicSort left = new BitonicSort(a, lo, mid, !dir);
					BitonicSort right = new BitonicSort(a, lo + mid, len - mid, dir);
					left.fork();
					right.compute();
					left.join();
					new Merger(a, lo, len, dir).invoke();
				}
			}
			
		}
		
		private static final class Merger extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final double[] a;
			final int lo;
			final int len;
			final boolean dir;
			
			Merger(double[] a, int lo, int len, boolean dir) {
				this.a = a;
				this.lo = lo;
				this.len = len;
				this.dir = dir;
			}
			
			public final void compute() {
				int m = greatestPowerOfTwoLessThan(len);
				
				for(int i = lo; i < lo + len - m; i++) {
					if(dir == (a[i] > a[i + m])) {
						swap(a, i, i + m);
					}
				}
				
				if(len < THRESHOLD) {
					merge(a, lo, m, dir);
					merge(a, lo + m, len - m, dir);
				} 
				else {
					Merger fm = new Merger(a, lo, m, dir);
					Merger lm = new Merger(a, lo + m, len - m, dir);
					fm.fork();
					lm.compute();
					fm.join();
					
				}
			}
		}
	}
	
	private static void sort(double[] a, int lo, int len, boolean dir) {
		if(len > 1) {
			int mid = (len >>> 1);
			sort(a, lo, mid, !dir);
			sort(a, lo + mid, len - mid, dir);
			merge(a, lo, len, dir);
		}
	}
	
	private static void merge(double[] a, int lo, int len, boolean dir) {
		if(len > 1) {
			int m = greatestPowerOfTwoLessThan(len);
			
			for(int i = lo; i < lo + len - m; i++) {
				if(dir == (a[i] > a[i + m])) {
					swap(a, i, i + m);
				}
			}
			merge(a, lo, m, dir);
			merge(a, lo + m, len - m, dir);
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
		new ParallelBitonicSortForComparable.BitonicSort(a, 0, a.length, true).invoke();
	}
	
	private static final class ParallelBitonicSortForComparable {
		private static final class BitonicSort extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final Object[] a;
			final int lo;
			final int len;
			final boolean dir;
			
			BitonicSort(Object[] a, int lo, int len, boolean dir) {
				this.a = a;
				this.lo = lo;
				this.len = len;
				this.dir = dir;
			}
			
			public final void compute() {
				if(len < 2) {
					return;
				}
				int mid = len >>> 1;
	
				if(len - lo < THRESHOLD) {					
					sort(a, lo, mid, !dir);
					sort(a, lo + mid, len - mid, dir);
					merge(a, lo, len, dir);
				}
				else {
					BitonicSort left = new BitonicSort(a, lo, mid, !dir);
					BitonicSort right = new BitonicSort(a, lo + mid, len - mid, dir);
					left.fork();
					right.compute();
					left.join();
					new Merger(a, lo, len, dir).invoke();
				}
			}
			
		}
		
		private static final class Merger extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final Object[] a;
			final int lo;
			final int len;
			final boolean dir;
			
			Merger(Object[] a, int lo, int len, boolean dir) {
				this.a = a;
				this.lo = lo;
				this.len = len;
				this.dir = dir;
			}
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public final void compute() {
				int m = greatestPowerOfTwoLessThan(len);
				
				for(int i = lo; i < lo + len - m; i++) {
					if(dir == (((Comparable)a[i]).compareTo(a[i + m]) > 0)) {
						swap(a, i, i + m);
					}
				}
				
				if(len < THRESHOLD) {
					merge(a, lo, m, dir);
					merge(a, lo + m, len - m, dir);
				} 
				else {
					Merger fm = new Merger(a, lo, m, dir);
					Merger lm = new Merger(a, lo + m, len - m, dir);
					fm.fork();
					lm.compute();
					fm.join();
					
				}
			}
		}
	}
	
	private static void sort(Object[] a, int lo, int len, boolean dir) {
		if(len > 1) {
			int mid = (len >>> 1);
			sort(a, lo, mid, !dir);
			sort(a, lo + mid, len - mid, dir);
			merge(a, lo, len, dir);
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void merge(Object[] a, int lo, int len, boolean dir) {
		if(len > 1) {
			int m = greatestPowerOfTwoLessThan(len);
			
			for(int i = lo; i < lo + len - m; i++) {
				if(dir == (((Comparable)a[i]).compareTo(a[i + m]) > 0)) {
					swap(a, i, i + m);
				}
			}
			merge(a, lo, m, dir);
			merge(a, lo + m, len - m, dir);
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
			new ParallelBitonicSortForComparable.BitonicSort(a, 0, a.length, true).invoke();
		} else {
			new ParallelBitonicSortForComparator.BitonicSort<>(a, 0, a.length, true, c).invoke();
		}
	}
	
	private static final class ParallelBitonicSortForComparator {
		private static final class BitonicSort <T> extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final T[] a;
			final int lo;
			final int len;
			final boolean dir;
			final Comparator<? super T> c;
			
			BitonicSort(T[] a, int lo, int len, boolean dir, Comparator<? super T> c) {
				this.a = a;
				this.lo = lo;
				this.len = len;
				this.dir = dir;
				this.c = c;
			}
			
			public final void compute() {
				if(len < 2) {
					return;
				}
				int mid = len >>> 1;
	
				if(len - lo < THRESHOLD) {					
					sort(a, lo, mid, !dir, c);
					sort(a, lo + mid, len - mid, dir, c);
					merge(a, lo, len, dir, c);
				}
				else {
					BitonicSort<T> left = new BitonicSort<>(a, lo, mid, !dir, c);
					BitonicSort<T> right = new BitonicSort<>(a, lo + mid, len - mid, dir, c);
					left.fork();
					right.compute();
					left.join();
					new Merger<>(a, lo, len, dir, c).invoke();
				}
			}
			
		}
		
		private static final class Merger<T> extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final T[] a;
			final int lo;
			final int len;
			final boolean dir;
			final Comparator<? super T> c;
			
			Merger(T[] a, int lo, int len, boolean dir, Comparator<? super T> c) {
				this.a = a;
				this.lo = lo;
				this.len = len;
				this.dir = dir;
				this.c = c;
			}
			
			public final void compute() {
				int m = greatestPowerOfTwoLessThan(len);
				
				for(int i = lo; i < lo + len - m; i++) {
					if(dir == (c.compare(a[i], a[i + m]) > 0)) {
						swap(a, i, i + m);
					}
				}
				
				if(len < THRESHOLD) {
					merge(a, lo, m, dir, c);
					merge(a, lo + m, len - m, dir, c);
				} 
				else {
					Merger<T> fm = new Merger<>(a, lo, m, dir, c);
					Merger<T> lm = new Merger<>(a, lo + m, len - m, dir, c);
					fm.fork();
					lm.compute();
					fm.join();
					
				}
			}
		}
	}
	
	private static <T> void sort(T[] a, int lo, int len, boolean dir, Comparator<? super T> c) {
		if(len > 1) {
			int mid = (len >>> 1);
			sort(a, lo, mid, !dir, c);
			sort(a, lo + mid, len - mid, dir, c);
			merge(a, lo, len, dir, c);
		}
	}
	
	private static <T> void merge(T[] a, int lo, int len, boolean dir, Comparator<? super T> c) {
		if(len > 1) {
			int m = greatestPowerOfTwoLessThan(len);
			
			for(int i = lo; i < lo + len - m; i++) {
				if(dir == (c.compare(a[i], a[i + m]) > 0)) {
					swap(a, i, i + m);
				}
			}
			merge(a, lo, m, dir);
			merge(a, lo + m, len - m, dir);
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
