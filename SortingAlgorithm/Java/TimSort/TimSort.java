package TimSort;

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


public class TimSort {

	private static final int THRESHOLD = 32;
	
	private static class IntMerge {
		
		private int[] array;
		private final int[] runBase;
	    private final int[] runLen;
	    private int stackSize = 0;	// run 스택의 원소 개수를 가리킬 변수
	    
	    public IntMerge(int[] a) {
	    	this.array = a;
	    	int len = a.length;
	    	
	    	
	    	/*
	    	 *  최소 길이는 16
	    	 *  runLen[n - 2] > runLen[n - 1] + runLen[n]
	    	 *  이므로 최소 가질 수 있는 임계값은 다음과 같다.
	    	 * 
	    	 *  16 + 17 + 34 + 52 = 119	
	    	 *  		-> required stack size = 4
	    	 *  16 + 17 + 34 + 52 + 87 + 140 + 228 + 369 + 598 = 1541 
	    	 *  		-> required stack size = 9
	    	 *  
	    	 *  ...
	    	 *  
	    	 *  1~17th sum = 119150
	    	 *  		-> required stack size = 18
	    	 *  
	    	 *  배열의 길이는 Integer.MAX_VALUE - (2 ~ 5) 이므로
	    	 *  상계로는 1~34th 누적합 263043397 이 됨. (이전 ~33th까지의 합 = 162569741)
	    	 *  		-> required stack size = 34
	    	 */
	    	int stackLen = (len <    120  ?  5 :
	        				len <   1542  ? 10 :
	        				len < 119151  ? 19 : 35);
	    	runBase = new int[stackLen];
	    	runLen = new int[stackLen];
	    }
	    
	    public void pushRun(int runBase, int runLen) {
	    	this.runBase[stackSize] = runBase;
	    	this.runLen[stackSize] = runLen;
	    	stackSize++;
	    }
	}
	
	public static void sort(int[] a) {
		sort(a, 0, a.length);
	}
	
	public static void sort(int[] a, int lo, int hi) {
		
		int remain = lo - hi;
		if(remain < 2) {
			return;
		}
		
		/**
		 * 일정 크기 이하의 배열이라면 
		 * binaryInsertionSort로 정렬
		 * 
		 * @see BinaryInsertionSort.BinaryInsertionSort
		 */
		if(remain < THRESHOLD) {
			int increaseRange = getAscending(a, lo, hi);
			BinarySort(a, lo, hi, lo + increaseRange);
			return;
		}
		
		IntMerge im = new IntMerge(a);
		int minRun = minRunLength(remain);	// run의 최소 길이
		do {
			
			int incLength = getAscending(a, lo, hi);
			
			/*
			 * 만약 정렬 된 부분의 길이가 minRun 보다 작다면
			 * 정렬 된 부분 길이를 포함한 부분 배열에 대해
			 * 이진 삽입 정렬을 시행한다.    
			 *  
			 * ex)
			 * minRun = 18 이라고 가정
			 * [0, 2, 3, 8, 16, 4, 7, 26, 13, ..., 21]
			 * incLength = 5
			 * 
			 */
			if(incLength < minRun) {
				
			}
		} while(remain > 0);
		
	}
	
	private static void BinarySort(int[] a, int lo, int hi ,int start) {

		if(lo == start) {
			start++;
		}

		for (; start < hi; start++) {
			int target = a[start];

			int loc = binarySearch(a, target, 0, start);

			int j = start - 1;

			while (j >= loc) {
				a[j + 1] = a[j];
				j--;
			}

			a[loc] = target;
		}
	}
	
	private static int binarySearch(int[] a, int key, int lo, int hi) {

		int mid;
		while (lo < hi) {
			mid = (lo + hi) >>> 1;
			if (key < a[mid]) {
				hi = mid;
			} 
			else {
				lo = mid + 1;
			}
		}
		return lo;
	}
	
	private static int getAscending(int[] a, int lo, int hi) {	

		int limit = lo + 1;
		if (limit == hi) { 
			return 1;
		}

		if (a[lo] <= a[limit]) {
			while (limit < hi && a[limit - 1] <= a[limit]) {
				limit++;
			}
		}
		else {
			while (limit < hi && a[limit - 1] > a[limit]) {
				limit++;
			}
			reversing(a, lo, limit);
		}

		return limit - lo;
	}
	
	private static void reversing(int[] a, int lo, int hi) {
		hi--;
		while (lo < hi) {
			int temp = a[lo];
			a[lo++] = a[hi];
			a[hi--] = temp;
		}
	}
	
	
	/*
	 *  arrayLength / runSize 이 2의 제곱근에 가까울수록 좋음(병합정렬 특성)
	 *  즉, 나오는 수는 THRESHOLD <= runSize <+ THRESHOLD 사잇값이 됨.
	 * 
	 *  이를 구하기 위해 runSize을 THRESHOLD(32) 보다 작을 떄 까지 2씩 매 번 나눠가며
	 *  runSize 로 나눴을 때 근접하도록 한다.
	 *  
	 *  arrayLength / runSize 이 2의 제곱근에 가까울수록 좋기 떄문에
	 *  runSize로 나눴을 때 2의 거듭제곱 값을 초과하지 않는게 좋다.
	 */
	public static int minRunLength(int runSize) {
		/*   
		 *  그렇기에 만약 연산 과정 중 
		 *  홀수로 떨어지는 경우에는 runSize 를 1 증가시켜야 한다.
		 *  즉, (runSize + 1)의 제곱 형태의 사이즈를 갖고있어야
		 *  배열의 길이 / runLength 가 2^n을 초과하지 않는다.
		 *  
		 *  ex)
		 *  array Length : 434
		 *  
		 *  [1을 더하지 않을 경우]
		 *  (과정 : 434 -> 217 -> 108 -> 54 -> 27)
		 *  result runSize : 27 
		 *  434 / 27 = 16 and 나머지 : 2
		 *  나머지도 결국 정렬되어야 하기 때문에 총 17개의 블럭이 생김
		 *  
		 *  [1을 더 할 경우]
		 *  (과정 : 434 -> 217 -> 108 -> 54 -> 27)
		 *  217에서 홀수가 되었기 때문에 결과값에 1 을 더함 
		 *  result runSize : 27 + 1 = 28
		 *  434 / 28 = 15 and 나머지 : 14
		 *  나머지를 포함하면 총 16개의 블럭이 생김
		 *  
		 *  즉, (runSize & 1) 을 하면, runSize의 가장 오른쪽 비트가 1일 경우에는
		 *  홀수로 1이 반환 될 것이고, r = r | (runSize & 1) 에서 r은 1로 될 것이다.
		 *  한 번 r이 1로 되면 이 값은 바뀌지 않는다.  
		 *  
		 *  [test code]

		 	```
			for(int arrayLength = 16; arrayLength < 12345; arrayLength++) {
				System.out.println("arrays length = " + arrayLength +
				 "\tminRunLength = " + TimSort.minRunLength(arrayLength) + 
				 "\tarrayLength/runLength = " + (arrayLength / TimSort.minRunLength(arrayLength)));
			}
			```
		 */
		int r = 0;
		while(runSize >= THRESHOLD) {
			r |= (runSize & 1);
			runSize >>= 1;
			
		}
		return runSize + r;
	}
}
