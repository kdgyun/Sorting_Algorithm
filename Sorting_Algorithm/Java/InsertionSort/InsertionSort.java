package InsertionSort;

/**
 * 
 * @author kdgyun
 * {@link https://st-lab.tistory.com}
 * {@link https://github.com/kdgyun}
 *
 */


import java.util.Comparator;

public class InsertionSort {
	
	
	//============= primitive type ============//
	
	
	/*==========================
	 * sorting byte type array 
	 *==========================*/
	
	public void sort(byte[] a) {
		sort(a, a.length);
	}
	
	private void sort(byte[] a, int size ){
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
	
	public void sort(char[] a) {
		sort(a, a.length);
	}
	
	private void sort(char[] a, int size) {
		
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
	
	public void sort(short[] a) {
		sort(a, a.length);
	}
	
	private void sort(short[] a, int size) {
		
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
	
	public void sort(int[] a) {
		sort(a, a.length);
	}
	
	private void sort(int[] a, int size) {
		
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
	
	public void sort(long[] a) {
		sort(a, a.length);
	}
	
	private void sort(long[] a, int size) {
		
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
	
	public void sort(float[] a) {
		sort(a, a.length);
	}
	
	private void sort(float[] a, int size) {
		
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
	
	public void sort(double[] a) {
		sort(a, a.length);
	}
	
	private void sort(double[] a, int size) {
		
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void sort(Object[] a, int size, Comparator c) {
		Object target;
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
}
