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

public class ParallelOddEvenMergeSort {

	final static int THRESHOLD = 1 << 13;
	
	//============= primitive type ============//
	
	
	/*==========================
	 * sorting byte type array 
	 *==========================*/
	
	public static void sort(byte[] a) {
		new ParallelOddEvenMergeSortForByte.OddEvenMergeSort(a, 0, a.length).invoke();
	}
	
	private static final class ParallelOddEvenMergeSortForByte {
		private static final class OddEvenMergeSort extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final byte[] a;
			final int lo;
			final int hi;
			
			OddEvenMergeSort(byte[] a, int lo, int hi) {
				this.a = a;
				this.lo = lo;
				this.hi = hi;
			}
			
			public final void compute() {
				if(hi - lo < 2) {
					return;
				}
				int mid = (hi + lo) >>> 1;
	
				if(hi - lo < THRESHOLD) {					
					sort(a, lo, mid);
					sort(a, mid, hi);
					merge(a, lo, hi, 1);
				}
				else {
					OddEvenMergeSort left = new OddEvenMergeSort(a, lo, mid);
					OddEvenMergeSort right = new OddEvenMergeSort(a, mid, hi);
					left.fork();
					right.compute();
					left.join();
					new Merger(a, lo, hi, 1).invoke();
				}
			}
			
		}
		
		private static final class Merger extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final byte[] a;
			final int lo;
			final int hi;
			final int dist;
			
			Merger(byte[] a, int lo, int hi, int dist) {
				this.a = a;
				this.lo = lo;
				this.hi = hi;
				this.dist = dist;
			}
			
			public final void compute() {
				int subDist = dist << 1;
				if(hi - lo < THRESHOLD) {
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
				else {
					if(subDist < (hi - lo)) {
						Merger fm = new Merger(a, lo, hi, subDist);
						Merger lm = new Merger(a, lo + dist, hi, subDist);
						fm.fork();
						lm.compute();
						fm.join();
						
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
			}
		}
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
		new ParallelOddEvenMergeSortForChar.OddEvenMergeSort(a, 0, a.length).invoke();
	}
	
	private static final class ParallelOddEvenMergeSortForChar {
		private static final class OddEvenMergeSort extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final char[] a;
			final int lo;
			final int hi;
			
			OddEvenMergeSort(char[] a, int lo, int hi) {
				this.a = a;
				this.lo = lo;
				this.hi = hi;
			}
			
			public final void compute() {
				if(hi - lo < 2) {
					return;
				}
				int mid = (hi + lo) >>> 1;
	
				if(hi - lo < THRESHOLD) {					
					sort(a, lo, mid);
					sort(a, mid, hi);
					merge(a, lo, hi, 1);
				}
				else {
					OddEvenMergeSort left = new OddEvenMergeSort(a, lo, mid);
					OddEvenMergeSort right = new OddEvenMergeSort(a, mid, hi);
					left.fork();
					right.compute();
					left.join();
					new Merger(a, lo, hi, 1).invoke();
				}
			}
			
		}
		
		private static final class Merger extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final char[] a;
			final int lo;
			final int hi;
			final int dist;
			
			Merger(char[] a, int lo, int hi, int dist) {
				this.a = a;
				this.lo = lo;
				this.hi = hi;
				this.dist = dist;
			}
			
			public final void compute() {
				int subDist = dist << 1;
				if(hi - lo < THRESHOLD) {
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
				else {
					if(subDist < (hi - lo)) {
						Merger fm = new Merger(a, lo, hi, subDist);
						Merger lm = new Merger(a, lo + dist, hi, subDist);
						fm.fork();
						lm.compute();
						fm.join();
						
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
			}
		}
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
		new ParallelOddEvenMergeSortForShort.OddEvenMergeSort(a, 0, a.length).invoke();
	}
	
	private static final class ParallelOddEvenMergeSortForShort {
		private static final class OddEvenMergeSort extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final short[] a;
			final int lo;
			final int hi;
			
			OddEvenMergeSort(short[] a, int lo, int hi) {
				this.a = a;
				this.lo = lo;
				this.hi = hi;
			}
			
			public final void compute() {
				if(hi - lo < 2) {
					return;
				}
				int mid = (hi + lo) >>> 1;
	
				if(hi - lo < THRESHOLD) {					
					sort(a, lo, mid);
					sort(a, mid, hi);
					merge(a, lo, hi, 1);
				}
				else {
					OddEvenMergeSort left = new OddEvenMergeSort(a, lo, mid);
					OddEvenMergeSort right = new OddEvenMergeSort(a, mid, hi);
					left.fork();
					right.compute();
					left.join();
					new Merger(a, lo, hi, 1).invoke();
				}
			}
			
		}
		
		private static final class Merger extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final short[] a;
			final int lo;
			final int hi;
			final int dist;
			
			Merger(short[] a, int lo, int hi, int dist) {
				this.a = a;
				this.lo = lo;
				this.hi = hi;
				this.dist = dist;
			}
			
			public final void compute() {
				int subDist = dist << 1;
				if(hi - lo < THRESHOLD) {
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
				else {
					if(subDist < (hi - lo)) {
						Merger fm = new Merger(a, lo, hi, subDist);
						Merger lm = new Merger(a, lo + dist, hi, subDist);
						fm.fork();
						lm.compute();
						fm.join();
						
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
			}
		}
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
		new ParallelOddEvenMergeSortForInteger.OddEvenMergeSort(a, 0, a.length).invoke();
	}
	
	private static final class ParallelOddEvenMergeSortForInteger {
		private static final class OddEvenMergeSort extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final int[] a;
			final int lo;
			final int hi;
			
			OddEvenMergeSort(int[] a, int lo, int hi) {
				this.a = a;
				this.lo = lo;
				this.hi = hi;
			}
			
			public final void compute() {
				if(hi - lo < 2) {
					return;
				}
				int mid = (hi + lo) >>> 1;
	
				if(hi - lo < THRESHOLD) {					
					sort(a, lo, mid);
					sort(a, mid, hi);
					merge(a, lo, hi, 1);
				}
				else {
					OddEvenMergeSort left = new OddEvenMergeSort(a, lo, mid);
					OddEvenMergeSort right = new OddEvenMergeSort(a, mid, hi);
					left.fork();
					right.compute();
					left.join();
					new Merger(a, lo, hi, 1).invoke();
				}
			}
			
		}
		
		private static final class Merger extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final int[] a;
			final int lo;
			final int hi;
			final int dist;
			
			Merger(int[] a, int lo, int hi, int dist) {
				this.a = a;
				this.lo = lo;
				this.hi = hi;
				this.dist = dist;
			}
			
			public final void compute() {
				int subDist = dist << 1;
				if(hi - lo < THRESHOLD) {
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
				else {
					if(subDist < (hi - lo)) {
						Merger fm = new Merger(a, lo, hi, subDist);
						Merger lm = new Merger(a, lo + dist, hi, subDist);
						fm.fork();
						lm.compute();
						fm.join();
						
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
			}
		}
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
		new ParallelOddEvenMergeSortForLong.OddEvenMergeSort(a, 0, a.length).invoke();
	}
	
	private static final class ParallelOddEvenMergeSortForLong {
		private static final class OddEvenMergeSort extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final long[] a;
			final int lo;
			final int hi;
			
			OddEvenMergeSort(long[] a, int lo, int hi) {
				this.a = a;
				this.lo = lo;
				this.hi = hi;
			}
			
			public final void compute() {
				if(hi - lo < 2) {
					return;
				}
				int mid = (hi + lo) >>> 1;
	
				if(hi - lo < THRESHOLD) {					
					sort(a, lo, mid);
					sort(a, mid, hi);
					merge(a, lo, hi, 1);
				}
				else {
					OddEvenMergeSort left = new OddEvenMergeSort(a, lo, mid);
					OddEvenMergeSort right = new OddEvenMergeSort(a, mid, hi);
					left.fork();
					right.compute();
					left.join();
					new Merger(a, lo, hi, 1).invoke();
				}
			}
			
		}
		
		private static final class Merger extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final long[] a;
			final int lo;
			final int hi;
			final int dist;
			
			Merger(long[] a, int lo, int hi, int dist) {
				this.a = a;
				this.lo = lo;
				this.hi = hi;
				this.dist = dist;
			}
			
			public final void compute() {
				int subDist = dist << 1;
				if(hi - lo < THRESHOLD) {
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
				else {
					if(subDist < (hi - lo)) {
						Merger fm = new Merger(a, lo, hi, subDist);
						Merger lm = new Merger(a, lo + dist, hi, subDist);
						fm.fork();
						lm.compute();
						fm.join();
						
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
			}
		}
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
		new ParallelOddEvenMergeSortForFloat.OddEvenMergeSort(a, 0, a.length).invoke();
	}
	
	private static final class ParallelOddEvenMergeSortForFloat {
		private static final class OddEvenMergeSort extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final float[] a;
			final int lo;
			final int hi;
			
			OddEvenMergeSort(float[] a, int lo, int hi) {
				this.a = a;
				this.lo = lo;
				this.hi = hi;
			}
			
			public final void compute() {
				if(hi - lo < 2) {
					return;
				}
				int mid = (hi + lo) >>> 1;
	
				if(hi - lo < THRESHOLD) {					
					sort(a, lo, mid);
					sort(a, mid, hi);
					merge(a, lo, hi, 1);
				}
				else {
					OddEvenMergeSort left = new OddEvenMergeSort(a, lo, mid);
					OddEvenMergeSort right = new OddEvenMergeSort(a, mid, hi);
					left.fork();
					right.compute();
					left.join();
					new Merger(a, lo, hi, 1).invoke();
				}
			}
			
		}
		
		private static final class Merger extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final float[] a;
			final int lo;
			final int hi;
			final int dist;
			
			Merger(float[] a, int lo, int hi, int dist) {
				this.a = a;
				this.lo = lo;
				this.hi = hi;
				this.dist = dist;
			}
			
			public final void compute() {
				int subDist = dist << 1;
				if(hi - lo < THRESHOLD) {
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
				else {
					if(subDist < (hi - lo)) {
						Merger fm = new Merger(a, lo, hi, subDist);
						Merger lm = new Merger(a, lo + dist, hi, subDist);
						fm.fork();
						lm.compute();
						fm.join();
						
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
			}
		}
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
		new ParallelOddEvenMergeSortForDouble.OddEvenMergeSort(a, 0, a.length).invoke();
	}
	
	private static final class ParallelOddEvenMergeSortForDouble {
		private static final class OddEvenMergeSort extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final double[] a;
			final int lo;
			final int hi;
			
			OddEvenMergeSort(double[] a, int lo, int hi) {
				this.a = a;
				this.lo = lo;
				this.hi = hi;
			}
			
			public final void compute() {
				if(hi - lo < 2) {
					return;
				}
				int mid = (hi + lo) >>> 1;
	
				if(hi - lo < THRESHOLD) {					
					sort(a, lo, mid);
					sort(a, mid, hi);
					merge(a, lo, hi, 1);
				}
				else {
					OddEvenMergeSort left = new OddEvenMergeSort(a, lo, mid);
					OddEvenMergeSort right = new OddEvenMergeSort(a, mid, hi);
					left.fork();
					right.compute();
					left.join();
					new Merger(a, lo, hi, 1).invoke();
				}
			}
			
		}
		
		private static final class Merger extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final double[] a;
			final int lo;
			final int hi;
			final int dist;
			
			Merger(double[] a, int lo, int hi, int dist) {
				this.a = a;
				this.lo = lo;
				this.hi = hi;
				this.dist = dist;
			}
			
			public final void compute() {
				int subDist = dist << 1;
				if(hi - lo < THRESHOLD) {
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
				else {
					if(subDist < (hi - lo)) {
						Merger fm = new Merger(a, lo, hi, subDist);
						Merger lm = new Merger(a, lo + dist, hi, subDist);
						fm.fork();
						lm.compute();
						fm.join();
						
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
			}
		}
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
		new ParallelOddEvenMergeSortForComparable.OddEvenMergeSort(a, 0, a.length).invoke();
	}
	
	private static final class ParallelOddEvenMergeSortForComparable {
		private static final class OddEvenMergeSort extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final Object[] a;
			final int lo;
			final int hi;
			
			OddEvenMergeSort(Object[] a, int lo, int hi) {
				this.a = a;
				this.lo = lo;
				this.hi = hi;
			}
			
			public final void compute() {
				if(hi - lo < 2) {
					return;
				}
				int mid = (hi + lo) >>> 1;
	
				if(hi - lo < THRESHOLD) {					
					sort(a, lo, mid);
					sort(a, mid, hi);
					merge(a, lo, hi, 1);
				}
				else {
					OddEvenMergeSort left = new OddEvenMergeSort(a, lo, mid);
					OddEvenMergeSort right = new OddEvenMergeSort(a, mid, hi);
					left.fork();
					right.compute();
					left.join();
					new Merger(a, lo, hi, 1).invoke();
				}
			}
			
		}
		
		private static final class Merger extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final Object[] a;
			final int lo;
			final int hi;
			final int dist;
			
			Merger(Object[] a, int lo, int hi, int dist) {
				this.a = a;
				this.lo = lo;
				this.hi = hi;
				this.dist = dist;
			}
			
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public final void compute() {
				int subDist = dist << 1;
				if(hi - lo < THRESHOLD) {
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
				else {
					if(subDist < (hi - lo)) {
						Merger fm = new Merger(a, lo, hi, subDist);
						Merger lm = new Merger(a, lo + dist, hi, subDist);
						fm.fork();
						lm.compute();
						fm.join();
						
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
			}
		}
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
			new ParallelOddEvenMergeSortForComparable.OddEvenMergeSort(a, 0, a.length).invoke();
		} else {
			new ParallelOddEvenMergeSortForComparator.OddEvenMergeSort<>(a, 0, a.length, c).invoke();
		}
	}
	
	private static final class ParallelOddEvenMergeSortForComparator {
		private static final class OddEvenMergeSort <T> extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final T[] a;
			final int lo;
			final int hi;
			final Comparator<? super T> c;
			
			OddEvenMergeSort(T[] a, int lo, int hi, Comparator<? super T> c) {
				this.a = a;
				this.lo = lo;
				this.hi = hi;
				this.c = c;
			}
			
			public final void compute() {
				if(hi - lo < 2) {
					return;
				}
				int mid = (hi + lo) >>> 1;
	
				if(hi - lo < THRESHOLD) {					
					sort(a, lo, mid, c);
					sort(a, mid, hi, c);
					merge(a, lo, hi, 1, c);
				}
				else {
					OddEvenMergeSort<T> left = new OddEvenMergeSort<>(a, lo, mid, c);
					OddEvenMergeSort<T> right = new OddEvenMergeSort<>(a, mid, hi, c);
					left.fork();
					right.compute();
					left.join();
					new Merger<>(a, lo, hi, 1, c).invoke();
				}
			}
			
		}
		
		private static final class Merger <T> extends RecursiveAction {
			private static final long serialVersionUID = 1L;
			final T[] a;
			final int lo;
			final int hi;
			final int dist;
			final Comparator<? super T> c;
			
			Merger(T[] a, int lo, int hi, int dist, Comparator<? super T> c) {
				this.a = a;
				this.lo = lo;
				this.hi = hi;
				this.dist = dist;
				this.c = c;
			}
			
			public final void compute() {
				int subDist = dist << 1;
				if(hi - lo < THRESHOLD) {
					if(subDist < (hi - lo)) {
						merge(a, lo, hi, subDist, c);
						merge(a, lo + dist, hi, subDist, c);
						
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
				else {
					if(subDist < (hi - lo)) {
						Merger<T> fm = new Merger<>(a, lo, hi, subDist, c);
						Merger<T> lm = new Merger<>(a, lo + dist, hi, subDist, c);
						fm.fork();
						lm.compute();
						fm.join();
						
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
			}
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
