package TimSort;

import java.util.Comparator;

import Utils.Convert;
import Utils.Order;

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
	
	
	/**
	 * 최소 길이의 run 크기, 즉 minRun을 구하기 위한 메소드
	 * 
	 * @param runSize minRun을 구하고자 하는 초기 배열의 길이
	 */
	public static int minRunLength(int runSize) {

		int r = 0;	// 홀 수가 발생할 때 2^x가 초과되지 않도록 하는 여분의 수

		/*
		 *  (runSize & 1) 을 하면, runSize의 가장 오른쪽 비트가 1일 경우에는
		 *  홀수이므로 1이 반환 될 것이고, r = r | (runSize & 1) 에서 r은 1로 될 것이다.
		 *  한 번 r이 1로 되면 OR 연산자의 특성상 이 값은 바뀌지 않는다.  
		 */  
		while(runSize >= THRESHOLD) {
			r |= (runSize & 1);
			runSize >>= 1;
				
		}
		return runSize + r;
			
	}
	
	
	//============= primitive type ============//
	
	
	/*==========================
	 * sorting byte type array 
	 *==========================*/
	
	
	private static class ByteMergeStack {
		
		private byte[] array;
		private int[] runBase;
		private int[] runLength;
		private int stackSize = 0;
	    
		public ByteMergeStack(byte[] a) {
			this.array = a;
			int len = a.length;

			int stackLen = (len <    120  ?  5 :
							len <   1542  ? 10 :
							len < 119151  ? 19 : 40);
    	
			runBase = new int[stackLen];
			runLength = new int[stackLen];
		}
	    
		public void pushRun(int runBase, int runLen) {
			this.runBase[stackSize] = runBase;
			this.runLength[stackSize] = runLen;
			stackSize++;
		}
	    
		public void mergeForce() {

			while (stackSize > 1) {
				if (stackSize > 2 && runLength[stackSize - 3] < runLength[stackSize - 1]) {
					merge(stackSize - 3);
				} else {
					merge(stackSize - 2);
				}
			}
		}
	    
	    
	    public void merge() {

			while (stackSize > 1) {
				if (stackSize > 2 && runLength[stackSize - 3] <= runLength[stackSize - 2] + runLength[stackSize - 1]
						|| stackSize > 3 && runLength[stackSize - 4] <= runLength[stackSize - 3] + runLength[stackSize - 2]) {

					if (runLength[stackSize - 3] < runLength[stackSize - 1]) {
						merge(stackSize - 3);
					} 
					else {
						merge(stackSize - 2);
					}
				}
				else if (runLength[stackSize - 2] <= runLength[stackSize - 1]) {
					merge(stackSize - 2);
				}
				else {
					break;
				}
			}
		}
	    
	    /**
	     * run[idx] 와 run[idx + 1]이 병합 됨
	     * 
	     * @param idx 병합되는 두 서브리스트(run) 중 낮은 인덱스
	     */
		private void merge(int idx) {

			int start1 = runBase[idx];
			int length1 = runLength[idx];
			int start2 = runBase[idx + 1];
			int length2 = runLength[idx + 1];

			runLength[idx] = length1 + length2;

			if (idx == (stackSize - 3)) {
				runBase[idx + 1] = runBase[idx + 2];
				runLength[idx + 1] = runLength[idx + 2];
			}
			stackSize--;

			int lo = gallopRight(array[start2], array, start1, length1);

			if (length1 == lo) {
				return;
			}
	    	start1 += lo;
	    	length1 -= lo;
	    	
			int hi = gallopLeft(array[start1 + length1 - 1], array, start2, length2);

			if (hi == 0) {
				return;
			}

			length2 = hi;
			if (length1 <= length2) {
				mergeLo(start1, length1, start2, length2);
			}
			else {
				mergeHi(start1, length1, start2, length2);
			}
		}
	    
	    
		/**
		 * gallop_right() 함수를 수행하여 RUN B 의 첫번째 원소보다 
		 * 큰 원소들이 첫번째 출현하는 위치를 RUN A 에서 찾는다.
		 * 
		 * 
		 * @param key    run B의 key
		 * @param array  배열
		 * @param base   run A의 시작지점
		 * @param lenOfA run A 의 길이
		 * @return 제외되어야 할 부분의 위치 다음 인덱스를 반환
		 */
	    private int gallopRight(byte key, byte[] array, int base, int lenOfA) {


	    	int lo = 0;	
	    	int hi = 1;

	    	if(key < array[base]) {
	    		return 0;
	    	}
	    	
	    	else {

				int maxLen = lenOfA; 
				while (hi < maxLen && array[base + hi] <= key) {
					lo = hi;
					hi = (hi << 1) + 1;

					if (hi <= 0) { 
						hi = maxLen;
					}
				}

				if (hi > maxLen) {
					hi = maxLen;
				}
			}

			lo++;

			while (lo < hi) {
				int mid = lo + ((hi - lo) >>> 1);

				if (key < array[base + mid]) {
					hi = mid;
				} else {
					lo = mid + 1;
				}
			}
			return hi;
	    }
	    
	    /**
	     * gallop_left() 함수를 수행하여 RUN A 의 첫번째 원소(오른쪽 끝)보다 
	     * 큰 원소들이 첫번째로 출현하는 위치를 RUN B 에서 찾는다.
	     * 
	     * 
	     * @param key run A의 key
	     * @param array	배열
	     * @param base run B의 시작 지점
	     * @param lenOfB run B 의 길이
	     * @return 제외되어야 할 부분의 위치 다음 인덱스를 반환
	     */
	    private int gallopLeft(byte key, byte[] array, int base, int lenOfB) {

			int lo = 0;
			int hi = 1;

			if (key > array[base + lenOfB - 1]) {
				return lenOfB;
			}

			else {

				int startPointOfRun = base + lenOfB - 1;

				int maxLen = lenOfB; 

				while (hi < maxLen && key <= array[startPointOfRun - hi]) {
					lo = hi;
					hi = (hi << 1) + 1;

					if (hi <= 0) {
						hi = maxLen;
					}
				}

				if (hi > maxLen) {
					hi = maxLen;
				}

	    		int temp = lo;
	    		lo = lenOfB - 1 - hi;
	    		hi = lenOfB - 1 - temp;
	    	}
	    	
	    	lo++;
	    	
			// binary search (lower bound)
			while (lo < hi) {
				int mid = lo + ((hi - lo) >>> 1);

				if (key <= array[base + mid]) {
					hi = mid;
				} else {
					lo = mid + 1;
				}
			}
			return hi;
		}

	    
		/**
		 * 상대적으로 낮은 인덱스에 위치한 run을 기준으로 복사하여
		 * 실제 배열 원소를 병합하는 메소드 
		 * 
		 * @param start1 RUN A에서의 병합 시작 지점
		 * @param length1 RUN A에서의 병합해야 할 길이(개수)
		 * @param start2 RUN B에서의 병합 시작 지점
		 * @param length2 RUN B에서의 병합해야 할 길이(개수)
		 */
		private void mergeLo(int start1, int length1, int start2, int length2) {

			byte[] temp = new byte[length1];
			System.arraycopy(array, start1, temp, 0, length1);

			int insertIdx = start1;	
			int runBIdx = start2;
			int tempIdx = 0;

			int leftRemain = length1; 
			int rightRemain = length2;

			while (leftRemain != 0 && rightRemain != 0) {

				if (array[runBIdx] < temp[tempIdx]) {
					array[insertIdx++] = array[runBIdx++];
					rightRemain--;
				} else {
					array[insertIdx++] = temp[tempIdx++];
					leftRemain--;
				}
			}

			if (leftRemain != 0) {
				System.arraycopy(temp, tempIdx, array, insertIdx, leftRemain);
			} 
			else {
				System.arraycopy(array, runBIdx, array, insertIdx, rightRemain);
			}

		}
	    
		
		/**
		 * 상대적으로 높은 인덱스에 위치한 run을 기준으로 복사하여
		 * 실제 배열 원소를 병합하는 메소드 
		 * 
		 * @param start1 RUN A에서의 병합 시작 지점
		 * @param length1 RUN A에서의 병합해야 할 길이(개수)
		 * @param start2 RUN B에서의 병합 시작 지점
		 * @param length2 RUN B에서의 병합해야 할 길이(개수)
		 */
		private void mergeHi(int start1, int length1, int start2, int length2) {

			byte[] temp = new byte[length2];
			System.arraycopy(array, start2, temp, 0, length2);

			int insertIdx = start2 + length2 - 1;
			int runAIdx = start1 + length1 - 1;
			int tempIdx = length2 - 1;

			int leftRemain = length1;
			int rightRemain = length2;

			while (leftRemain != 0 && rightRemain != 0) {

				if (array[runAIdx] > temp[tempIdx]) {
					array[insertIdx--] = array[runAIdx--];
					leftRemain--;
				} else {
					array[insertIdx--] = temp[tempIdx--];
					rightRemain--;
				}
			}

			if (rightRemain != 0) {
				System.arraycopy(temp, 0, array, start1, rightRemain);
			} else {
				System.arraycopy(array, start1, array, start1, leftRemain);
			}

		}
	    	
	    	
	}
	
	
	
	
	
	public static void sort(byte[] a) {
		sort(a, 0, a.length);
	}
	
	public static void sort(byte[] a, int lo, int hi) {
		
		int remain = hi - lo;
		// 정렬해야 할 원소가 1개 이하일 경우 정렬 할 필요가 없다.
		if(remain < 2) {
			return;
		}
		/**
		 * 일정 크기 이하의 배열이라면 
		 * binaryInsertionSort로 정렬 뒤 바로 반환
		 * 
		 * @see BinaryInsertionSort.BinaryInsertionSort
		 */
		if(remain < THRESHOLD) {
			int increaseRange = getAscending(a, lo, hi);
			BinarySort(a, lo, hi, lo + increaseRange);
			return;
		}
		
		
		ByteMergeStack ims = new ByteMergeStack(a);
		int minRun = minRunLength(remain);	// run의 최소 길이
		do {

			int runLen = getAscending(a, lo, hi);

			if(runLen < minRun) {

				int counts = remain < minRun ? remain : minRun;

				BinarySort(a, lo, lo + counts, lo + runLen);
				
				runLen = counts;
			}
			ims.pushRun(lo, runLen);
			ims.merge();
			
			lo += runLen;
			remain -= runLen;
		} while(remain != 0);
		ims.mergeForce();

	}
	

	private static void BinarySort(byte[] a, int lo, int hi ,int start) {

		if(lo == start) {
			start++;
		}

		for (; start < hi; start++) {
			byte target = a[start];

			int loc = binarySearch(a, target, lo, start);

			int j = start - 1;

			while (j >= loc) {
				a[j + 1] = a[j];
				j--;
			}

			a[loc] = target;
		}

	}
	
	private static int binarySearch(byte[] a, int key, int lo, int hi) {

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
	
	private static int getAscending(byte[] a, int lo, int hi) {	

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
	
	private static void reversing(byte[] a, int lo, int hi) {
		hi--;
		while (lo < hi) {
			byte temp = a[lo];
			a[lo++] = a[hi];
			a[hi--] = temp;
		}
	}
	
	
	
	
	
	
	/*==========================
	 * sorting char type array 
	 *==========================*/
	
	
	private static class CharMergeStack {
		
		private char[] array;
		private int[] runBase;
		private int[] runLength;
		private int stackSize = 0;
	    
		public CharMergeStack(char[] a) {
			this.array = a;
			int len = a.length;

			int stackLen = (len <    120  ?  5 :
							len <   1542  ? 10 :
							len < 119151  ? 19 : 40);
    	
			runBase = new int[stackLen];
			runLength = new int[stackLen];
		}
	    
		public void pushRun(int runBase, int runLen) {
			this.runBase[stackSize] = runBase;
			this.runLength[stackSize] = runLen;
			stackSize++;
		}
	    
		public void mergeForce() {

			while (stackSize > 1) {
				if (stackSize > 2 && runLength[stackSize - 3] < runLength[stackSize - 1]) {
					merge(stackSize - 3);
				} else {
					merge(stackSize - 2);
				}
			}
		}
	    
	    
	    public void merge() {

			while (stackSize > 1) {
				if (stackSize > 2 && runLength[stackSize - 3] <= runLength[stackSize - 2] + runLength[stackSize - 1]
						|| stackSize > 3 && runLength[stackSize - 4] <= runLength[stackSize - 3] + runLength[stackSize - 2]) {

					if (runLength[stackSize - 3] < runLength[stackSize - 1]) {
						merge(stackSize - 3);
					} 
					else {
						merge(stackSize - 2);
					}
				}
				else if (runLength[stackSize - 2] <= runLength[stackSize - 1]) {
					merge(stackSize - 2);
				}
				else {
					break;
				}
			}
		}
	    
	    /**
	     * run[idx] 와 run[idx + 1]이 병합 됨
	     * 
	     * @param idx 병합되는 두 서브리스트(run) 중 낮은 인덱스
	     */
		private void merge(int idx) {

			int start1 = runBase[idx];
			int length1 = runLength[idx];
			int start2 = runBase[idx + 1];
			int length2 = runLength[idx + 1];

			runLength[idx] = length1 + length2;

			if (idx == (stackSize - 3)) {
				runBase[idx + 1] = runBase[idx + 2];
				runLength[idx + 1] = runLength[idx + 2];
			}
			stackSize--;

			int lo = gallopRight(array[start2], array, start1, length1);

			if (length1 == lo) {
				return;
			}
	    	start1 += lo;
	    	length1 -= lo;
	    	
			int hi = gallopLeft(array[start1 + length1 - 1], array, start2, length2);

			if (hi == 0) {
				return;
			}

			length2 = hi;
			if (length1 <= length2) {
				mergeLo(start1, length1, start2, length2);
			}
			else {
				mergeHi(start1, length1, start2, length2);
			}
		}
	    
	    
		/**
		 * gallop_right() 함수를 수행하여 RUN B 의 첫번째 원소보다 
		 * 큰 원소들이 첫번째 출현하는 위치를 RUN A 에서 찾는다.
		 * 
		 * 
		 * @param key    run B의 key
		 * @param array  배열
		 * @param base   run A의 시작지점
		 * @param lenOfA run A 의 길이
		 * @return 제외되어야 할 부분의 위치 다음 인덱스를 반환
		 */
	    private int gallopRight(char key, char[] array, int base, int lenOfA) {


	    	int lo = 0;	
	    	int hi = 1;

	    	if(key < array[base]) {
	    		return 0;
	    	}
	    	
	    	else {

				int maxLen = lenOfA; 
				while (hi < maxLen && array[base + hi] <= key) {
					lo = hi;
					hi = (hi << 1) + 1;

					if (hi <= 0) { 
						hi = maxLen;
					}
				}

				if (hi > maxLen) {
					hi = maxLen;
				}
			}

			lo++;

			while (lo < hi) {
				int mid = lo + ((hi - lo) >>> 1);

				if (key < array[base + mid]) {
					hi = mid;
				} else {
					lo = mid + 1;
				}
			}
			return hi;
	    }
	    
	    /**
	     * gallop_left() 함수를 수행하여 RUN A 의 첫번째 원소(오른쪽 끝)보다 
	     * 큰 원소들이 첫번째로 출현하는 위치를 RUN B 에서 찾는다.
	     * 
	     * 
	     * @param key run A의 key
	     * @param array	배열
	     * @param base run B의 시작 지점
	     * @param lenOfB run B 의 길이
	     * @return 제외되어야 할 부분의 위치 다음 인덱스를 반환
	     */
	    private int gallopLeft(char key, char[] array, int base, int lenOfB) {

			int lo = 0;
			int hi = 1;

			if (key > array[base + lenOfB - 1]) {
				return lenOfB;
			}

			else {

				int startPointOfRun = base + lenOfB - 1;

				int maxLen = lenOfB; 

				while (hi < maxLen && key <= array[startPointOfRun - hi]) {
					lo = hi;
					hi = (hi << 1) + 1;

					if (hi <= 0) {
						hi = maxLen;
					}
				}

				if (hi > maxLen) {
					hi = maxLen;
				}

	    		int temp = lo;
	    		lo = lenOfB - 1 - hi;
	    		hi = lenOfB - 1 - temp;
	    	}
	    	
	    	lo++;
	    	
			// binary search (lower bound)
			while (lo < hi) {
				int mid = lo + ((hi - lo) >>> 1);

				if (key <= array[base + mid]) {
					hi = mid;
				} else {
					lo = mid + 1;
				}
			}
			return hi;
		}

	    
		/**
		 * 상대적으로 낮은 인덱스에 위치한 run을 기준으로 복사하여
		 * 실제 배열 원소를 병합하는 메소드 
		 * 
		 * @param start1 RUN A에서의 병합 시작 지점
		 * @param length1 RUN A에서의 병합해야 할 길이(개수)
		 * @param start2 RUN B에서의 병합 시작 지점
		 * @param length2 RUN B에서의 병합해야 할 길이(개수)
		 */
		private void mergeLo(int start1, int length1, int start2, int length2) {

			char[] temp = new char[length1];
			System.arraycopy(array, start1, temp, 0, length1);

			int insertIdx = start1;	
			int runBIdx = start2;
			int tempIdx = 0;

			int leftRemain = length1; 
			int rightRemain = length2;

			while (leftRemain != 0 && rightRemain != 0) {

				if (array[runBIdx] < temp[tempIdx]) {
					array[insertIdx++] = array[runBIdx++];
					rightRemain--;
				} else {
					array[insertIdx++] = temp[tempIdx++];
					leftRemain--;
				}
			}

			if (leftRemain != 0) {
				System.arraycopy(temp, tempIdx, array, insertIdx, leftRemain);
			} 
			else {
				System.arraycopy(array, runBIdx, array, insertIdx, rightRemain);
			}

		}
	    
		
		/**
		 * 상대적으로 높은 인덱스에 위치한 run을 기준으로 복사하여
		 * 실제 배열 원소를 병합하는 메소드 
		 * 
		 * @param start1 RUN A에서의 병합 시작 지점
		 * @param length1 RUN A에서의 병합해야 할 길이(개수)
		 * @param start2 RUN B에서의 병합 시작 지점
		 * @param length2 RUN B에서의 병합해야 할 길이(개수)
		 */
		private void mergeHi(int start1, int length1, int start2, int length2) {

			char[] temp = new char[length2];
			System.arraycopy(array, start2, temp, 0, length2);

			int insertIdx = start2 + length2 - 1;
			int runAIdx = start1 + length1 - 1;
			int tempIdx = length2 - 1;

			int leftRemain = length1;
			int rightRemain = length2;

			while (leftRemain != 0 && rightRemain != 0) {

				if (array[runAIdx] > temp[tempIdx]) {
					array[insertIdx--] = array[runAIdx--];
					leftRemain--;
				} else {
					array[insertIdx--] = temp[tempIdx--];
					rightRemain--;
				}
			}

			if (rightRemain != 0) {
				System.arraycopy(temp, 0, array, start1, rightRemain);
			} else {
				System.arraycopy(array, start1, array, start1, leftRemain);
			}

		}
	    	
	    	
	}
	
	
	
	
	
	public static void sort(char[] a) {
		sort(a, 0, a.length);
	}
	
	public static void sort(char[] a, int lo, int hi) {
		
		int remain = hi - lo;
		// 정렬해야 할 원소가 1개 이하일 경우 정렬 할 필요가 없다.
		if(remain < 2) {
			return;
		}
		/**
		 * 일정 크기 이하의 배열이라면 
		 * binaryInsertionSort로 정렬 뒤 바로 반환
		 * 
		 * @see BinaryInsertionSort.BinaryInsertionSort
		 */
		if(remain < THRESHOLD) {
			int increaseRange = getAscending(a, lo, hi);
			BinarySort(a, lo, hi, lo + increaseRange);
			return;
		}
		
		
		CharMergeStack ims = new CharMergeStack(a);
		int minRun = minRunLength(remain);	// run의 최소 길이
		do {

			int runLen = getAscending(a, lo, hi);

			if(runLen < minRun) {

				int counts = remain < minRun ? remain : minRun;

				BinarySort(a, lo, lo + counts, lo + runLen);
				
				runLen = counts;
			}
			ims.pushRun(lo, runLen);
			ims.merge();
			
			lo += runLen;
			remain -= runLen;
		} while(remain != 0);
		ims.mergeForce();

	}
	

	private static void BinarySort(char[] a, int lo, int hi ,int start) {

		if(lo == start) {
			start++;
		}

		for (; start < hi; start++) {
			char target = a[start];

			int loc = binarySearch(a, target, lo, start);

			int j = start - 1;

			while (j >= loc) {
				a[j + 1] = a[j];
				j--;
			}

			a[loc] = target;
		}

	}
	
	private static int binarySearch(char[] a, int key, int lo, int hi) {

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
	
	private static int getAscending(char[] a, int lo, int hi) {	

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
	
	private static void reversing(char[] a, int lo, int hi) {
		hi--;
		while (lo < hi) {
			char temp = a[lo];
			a[lo++] = a[hi];
			a[hi--] = temp;
		}
	}
	
	
	
	
	
	
	
	/*==========================
	 * sorting short type array 
	 *==========================*/
	
	
	private static class ShortMergeStack {
		
		private short[] array;
		private int[] runBase;
		private int[] runLength;
		private int stackSize = 0;
	    
		public ShortMergeStack(short[] a) {
			this.array = a;
			int len = a.length;
	    	

			int stackLen = (len <    120  ?  5 :
							len <   1542  ? 10 :
							len < 119151  ? 19 : 40);
    	
			runBase = new int[stackLen];
			runLength = new int[stackLen];
		}
	    
		public void pushRun(int runBase, int runLen) {
			this.runBase[stackSize] = runBase;
			this.runLength[stackSize] = runLen;
			stackSize++;
		}
	    
		public void mergeForce() {

			while (stackSize > 1) {
				if (stackSize > 2 && runLength[stackSize - 3] < runLength[stackSize - 1]) {
					merge(stackSize - 3);
				} else {
					merge(stackSize - 2);
				}
			}
		}
	    
	    
	    public void merge() {

			while (stackSize > 1) {
				if (stackSize > 2 && runLength[stackSize - 3] <= runLength[stackSize - 2] + runLength[stackSize - 1]
						|| stackSize > 3 && runLength[stackSize - 4] <= runLength[stackSize - 3] + runLength[stackSize - 2]) {

					if (runLength[stackSize - 3] < runLength[stackSize - 1]) {
						merge(stackSize - 3);
					} 
					else {
						merge(stackSize - 2);
					}
				}
				else if (runLength[stackSize - 2] <= runLength[stackSize - 1]) {
					merge(stackSize - 2);
				}
				else {
					break;
				}
			}
		}
	    
	    /**
	     * run[idx] 와 run[idx + 1]이 병합 됨
	     * 
	     * @param idx 병합되는 두 서브리스트(run) 중 낮은 인덱스
	     */
		private void merge(int idx) {

			int start1 = runBase[idx];
			int length1 = runLength[idx];
			int start2 = runBase[idx + 1];
			int length2 = runLength[idx + 1];

			runLength[idx] = length1 + length2;

			if (idx == (stackSize - 3)) {
				runBase[idx + 1] = runBase[idx + 2];
				runLength[idx + 1] = runLength[idx + 2];
			}
			stackSize--;

			int lo = gallopRight(array[start2], array, start1, length1);

			if (length1 == lo) {
				return;
			}
	    	start1 += lo;
	    	length1 -= lo;
	    	
			int hi = gallopLeft(array[start1 + length1 - 1], array, start2, length2);

			if (hi == 0) {
				return;
			}

			length2 = hi;
			if (length1 <= length2) {
				mergeLo(start1, length1, start2, length2);
			}
			else {
				mergeHi(start1, length1, start2, length2);
			}
		}
	    
	    
		/**
		 * gallop_right() 함수를 수행하여 RUN B 의 첫번째 원소보다 
		 * 큰 원소들이 첫번째 출현하는 위치를 RUN A 에서 찾는다.
		 * 
		 * 
		 * @param key    run B의 key
		 * @param array  배열
		 * @param base   run A의 시작지점
		 * @param lenOfA run A 의 길이
		 * @return 제외되어야 할 부분의 위치 다음 인덱스를 반환
		 */
	    private int gallopRight(short key, short[] array, int base, int lenOfA) {


	    	int lo = 0;	
	    	int hi = 1;

	    	if(key < array[base]) {
	    		return 0;
	    	}
	    	
	    	else {

				int maxLen = lenOfA; 
				while (hi < maxLen && array[base + hi] <= key) {
					lo = hi;
					hi = (hi << 1) + 1;

					if (hi <= 0) { 
						hi = maxLen;
					}
				}

				if (hi > maxLen) {
					hi = maxLen;
				}
			}

			lo++;

			while (lo < hi) {
				int mid = lo + ((hi - lo) >>> 1);

				if (key < array[base + mid]) {
					hi = mid;
				} else {
					lo = mid + 1;
				}
			}
			return hi;
	    }
	    
	    /**
	     * gallop_left() 함수를 수행하여 RUN A 의 첫번째 원소(오른쪽 끝)보다 
	     * 큰 원소들이 첫번째로 출현하는 위치를 RUN B 에서 찾는다.
	     * 
	     * 
	     * @param key run A의 key
	     * @param array	배열
	     * @param base run B의 시작 지점
	     * @param lenOfB run B 의 길이
	     * @return 제외되어야 할 부분의 위치 다음 인덱스를 반환
	     */
	    private int gallopLeft(short key, short[] array, int base, int lenOfB) {

			int lo = 0;
			int hi = 1;

			if (key > array[base + lenOfB - 1]) {
				return lenOfB;
			}

			else {

				int startPointOfRun = base + lenOfB - 1;

				int maxLen = lenOfB; 

				while (hi < maxLen && key <= array[startPointOfRun - hi]) {
					lo = hi;
					hi = (hi << 1) + 1;

					if (hi <= 0) {
						hi = maxLen;
					}
				}

				if (hi > maxLen) {
					hi = maxLen;
				}

	    		int temp = lo;
	    		lo = lenOfB - 1 - hi;
	    		hi = lenOfB - 1 - temp;
	    	}
	    	
	    	lo++;
	    	
			// binary search (lower bound)
			while (lo < hi) {
				int mid = lo + ((hi - lo) >>> 1);

				if (key <= array[base + mid]) {
					hi = mid;
				} else {
					lo = mid + 1;
				}
			}
			return hi;
		}

	    
		/**
		 * 상대적으로 낮은 인덱스에 위치한 run을 기준으로 복사하여
		 * 실제 배열 원소를 병합하는 메소드 
		 * 
		 * @param start1 RUN A에서의 병합 시작 지점
		 * @param length1 RUN A에서의 병합해야 할 길이(개수)
		 * @param start2 RUN B에서의 병합 시작 지점
		 * @param length2 RUN B에서의 병합해야 할 길이(개수)
		 */
		private void mergeLo(int start1, int length1, int start2, int length2) {

			short[] temp = new short[length1];
			System.arraycopy(array, start1, temp, 0, length1);

			int insertIdx = start1;	
			int runBIdx = start2;
			int tempIdx = 0;

			int leftRemain = length1; 
			int rightRemain = length2;

			while (leftRemain != 0 && rightRemain != 0) {

				if (array[runBIdx] < temp[tempIdx]) {
					array[insertIdx++] = array[runBIdx++];
					rightRemain--;
				} else {
					array[insertIdx++] = temp[tempIdx++];
					leftRemain--;
				}
			}

			if (leftRemain != 0) {
				System.arraycopy(temp, tempIdx, array, insertIdx, leftRemain);
			} 
			else {
				System.arraycopy(array, runBIdx, array, insertIdx, rightRemain);
			}

		}
	    
		
		/**
		 * 상대적으로 높은 인덱스에 위치한 run을 기준으로 복사하여
		 * 실제 배열 원소를 병합하는 메소드 
		 * 
		 * @param start1 RUN A에서의 병합 시작 지점
		 * @param length1 RUN A에서의 병합해야 할 길이(개수)
		 * @param start2 RUN B에서의 병합 시작 지점
		 * @param length2 RUN B에서의 병합해야 할 길이(개수)
		 */
		private void mergeHi(int start1, int length1, int start2, int length2) {

			short[] temp = new short[length2];
			System.arraycopy(array, start2, temp, 0, length2);

			int insertIdx = start2 + length2 - 1;
			int runAIdx = start1 + length1 - 1;
			int tempIdx = length2 - 1;

			int leftRemain = length1;
			int rightRemain = length2;

			while (leftRemain != 0 && rightRemain != 0) {

				if (array[runAIdx] > temp[tempIdx]) {
					array[insertIdx--] = array[runAIdx--];
					leftRemain--;
				} else {
					array[insertIdx--] = temp[tempIdx--];
					rightRemain--;
				}
			}

			if (rightRemain != 0) {
				System.arraycopy(temp, 0, array, start1, rightRemain);
			} else {
				System.arraycopy(array, start1, array, start1, leftRemain);
			}

		}
	    	
	    	
	}
	
	
	
	
	
	public static void sort(short[] a) {
		sort(a, 0, a.length);
	}
	
	public static void sort(short[] a, int lo, int hi) {
		
		int remain = hi - lo;
		// 정렬해야 할 원소가 1개 이하일 경우 정렬 할 필요가 없다.
		if(remain < 2) {
			return;
		}
		/**
		 * 일정 크기 이하의 배열이라면 
		 * binaryInsertionSort로 정렬 뒤 바로 반환
		 * 
		 * @see BinaryInsertionSort.BinaryInsertionSort
		 */
		if(remain < THRESHOLD) {
			int increaseRange = getAscending(a, lo, hi);
			BinarySort(a, lo, hi, lo + increaseRange);
			return;
		}
		
		
		ShortMergeStack ims = new ShortMergeStack(a);
		int minRun = minRunLength(remain);	// run의 최소 길이
		do {

			int runLen = getAscending(a, lo, hi);

			if(runLen < minRun) {

				int counts = remain < minRun ? remain : minRun;

				BinarySort(a, lo, lo + counts, lo + runLen);
				
				runLen = counts;
			}
			ims.pushRun(lo, runLen);
			ims.merge();
			
			lo += runLen;
			remain -= runLen;
		} while(remain != 0);
		ims.mergeForce();

	}
	

	private static void BinarySort(short[] a, int lo, int hi ,int start) {

		if(lo == start) {
			start++;
		}

		for (; start < hi; start++) {
			short target = a[start];

			int loc = binarySearch(a, target, lo, start);

			int j = start - 1;

			while (j >= loc) {
				a[j + 1] = a[j];
				j--;
			}

			a[loc] = target;
		}

	}
	
	private static int binarySearch(short[] a, short key, int lo, int hi) {

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
	
	private static int getAscending(short[] a, int lo, int hi) {	

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
	
	private static void reversing(short[] a, int lo, int hi) {
		hi--;
		while (lo < hi) {
			short temp = a[lo];
			a[lo++] = a[hi];
			a[hi--] = temp;
		}
	}
	
	
	
	
	
	
	
	
	
	/*==========================
	 * sorting int type array 
	 *==========================*/
	

	
	// run을 스택에 담아 둘 내부 정적 클래스
	private static class IntMergeStack {
		
		private int[] array;
		private int[] runBase;
		private int[] runLength;
		private int stackSize = 0;	// run 스택의 원소 개수를 가리킬 변수
	    
		public IntMergeStack(int[] a) {
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
			 *  상계로는 1~39th 누적합 2917196495 이 됨. (이전 ~38th까지의 합 = 1802926565)
			 *  		-> required stack size = 39
			 */
			int stackLen = (len <    120  ?  5 :
							len <   1542  ? 10 :
							len < 119151  ? 19 : 40);
    	
			runBase = new int[stackLen];
			runLength = new int[stackLen];
		}
	    
		public void pushRun(int runBase, int runLen) {
			this.runBase[stackSize] = runBase;
			this.runLength[stackSize] = runLen;
			stackSize++;
		}
	    
		public void mergeForce() {

			// 나머지 모든 run을 병합한다.
			while (stackSize > 1) {

				// 모든 run을 병합 할 것이기 때문에 2번 조건인 runLen[i - 2] > runLen[i - 1] 만 체크해주면서 병합한다.
				if (stackSize > 2 && runLength[stackSize - 3] < runLength[stackSize - 1]) {
					merge(stackSize - 3);
				} else {
					merge(stackSize - 2);
				}
			}
		}
	    
	    
	    public void merge() {
	    	
    		/*
    		 	기본 적인 메커니즘 
    		 	
    		 	1. runLen[i - 3] > runLen[i - 2] + runLen[i - 1]
    		 	2. runLen[i - 2] > runLen[i - 1]
    		 
    		 	스택에 요소가 5개 있을 때 기본 pivot은 상위 3개 요소 중 가운데를 지정  
    		 	ex) A, B, C, D, E (A = Bottom, E = Top)
    		 	pivot = D (== stackSize - 2)
    		 	
    		 	runLen[pivot - 1] = C, runLen[pivot] = D, runLen[pivot + 1] = E를 스택의 상위 세 요소로 한다.
			 	메커니즘상 루프는 다음과 같은 경우에 기초한다.
			 	
			 	1. C <= D + E 및 C < E일 경우 C 과 D 병합. 즉, pivot - 1 과 pivot 병합
			 	2. C <= D + E 및 C >= E일 경우, D와 E 병합. 즉, pivot 과 pivot + 1 병합 
			 	3. C > D + E 및 D <= E일 경우,  D와 E 병합. 즉, pivot 과 pivot + 1 병합 
			 	4. C > D + E 및 D > E일 경우 루프 종료

				while (stackSize > 1) {

					// 1번 조건 C > B + A 에 위배 될 경우 (== C <= B + A 일 경우)
					if (stackSize > 2 && runLength[stackSize - 3] <= runLength[stackSize - 2] + runLength[stackSize - 1] {
						// C < A가 크다면 (B, C)를 병합
						if (runLength[stackSize - 3] < runLength[stackSize - 1]) {
							merge(stackSize - 3);
						} 
						else { // A, B 병합
							merge(stackSize - 2);
						}
					}
					// 2번 조건 B > A 에 위배 될 경우 (== B <= A 일 경우)
					else if (runLength[stackSize - 2] <= runLength[stackSize - 1]) {
						merge(stackSize - 2); // A, B 병합
					}
					// 그 외의 경우는 위 두 조건을 만족한다는 의미이므로 종료
					else {
						break;
					}
				}
	    	
	    	    위 방식을 그대로 조건식으로 구현할 경우 stack 규칙이 깨짐
	    	  
	    	    예로들어 120, 80, 25, 20이 스택에 있고, 30이 스택에 추가되었다고 가정.
	    	    즉, stack[] = {120, 80, 25, 20, 30} 
	    	   
	    	    첫 번째 반복문에서 첫 번째 merge() 시
	    	    첫 번째 조건문의 runLen[pivot − 1] <= runLen[pivot] + runLen[pivot + 1])
	    	    즉, 25 <= (20 + 30) 을 만족시키며, 해당 하위 조건문 runLen[pivot − 1] < runLen[pivot + 1] 인
	    	    25 < 30 도 만족시키기 때문에 25와 20이 병합된다.
	    	   
	    	    그러면 결과는, {120, 80, 45, 30} 이 된다.
	    	   
	    	   	다음 반복문으로 넘어가게 되면 다음과 같이 된다.
	    	    80 > 45 + 30 이기 때문에 첫 번째 조건문을 만족하지 못하며,
	    	    그 다음 조건문인 runLen[pivot] <= runLen[pivot + 1] 또한 45 > 30이라 
	    	    
	    	    stack의 유지의 두 조건을 모두 만족한다는 의미로 반복문이 종료된다.
	    	    
	    	    하지만, 전체 남아있는 스택을 볼 때, 120 <= 80 + 45 을 만족하는게 있음에도 merge되지 않기에
	    	    stack의 규칙이 깨지게 된다.
	    	  
	    	  	즉, 상위 3개만 아니라 그 다음 아래의 3개의 요소에 대해서도 검사를 해야한다.
	    	  	
	    	 */
  

			while (stackSize > 1) {

				/*
				 *  1번 조건 C > B + A 에 위배 될 경우 (== C <= B + A 일 경우)
				 *  혹은, D > C + B 에 위배 될 경우 (== D <= C + B 일 경우) 
				 */
				if (stackSize > 2 && runLength[stackSize - 3] <= runLength[stackSize - 2] + runLength[stackSize - 1]
						|| stackSize > 3 && runLength[stackSize - 4] <= runLength[stackSize - 3] + runLength[stackSize - 2]) {

					if (runLength[stackSize - 3] < runLength[stackSize - 1]) {
						merge(stackSize - 3);
					} 
					else {
						merge(stackSize - 2);
					}
				}
				// 2번 조건 B > A 에 위배 될 경우 (== B <= A 일 경우)
				else if (runLength[stackSize - 2] <= runLength[stackSize - 1]) {
					merge(stackSize - 2);
				}
				// 그 외의 경우는 위 두 조건을 만족한다는 의미이므로 종료
				else {
					break;
				}
			}
			
		}
	    
	    /**
	     * run[idx] 와 run[idx + 1]이 병합 됨
	     * 
	     * @param idx 병합되는 두 서브리스트(run) 중 낮은 인덱스
	     */
		private void merge(int idx) {

			int start1 = runBase[idx];
			int length1 = runLength[idx];
			int start2 = runBase[idx + 1];
			int length2 = runLength[idx + 1];

			// idx 와 idx + 1 번째 run을 병합
			runLength[idx] = length1 + length2;
	    	
			/*
			 * 상위 3개 (A, B, C)에서 A, B가 병합 할 경우 C를 당겨온다.
			 * 
			 * ex) 
			 * stack [[A], [B], [C]]
			 * 
			 * runLen[idx] = length1 + length2 
			 * stack[[A + B], [B], [C]]
			 * 
			 * C를 B위치로 당겨온다. 
			 * stack[[A + B], [C], [C]]
			 * 
			 * 이 때 마지막 [C](stack[i - 1])는 어차피 더이상 참조될 일 없음
			 */

			if (idx == (stackSize - 3)) {
				runBase[idx + 1] = runBase[idx + 2];
				runLength[idx + 1] = runLength[idx + 2];
			}
			stackSize--;

	    	/*
			 
 			  gallopRight ->								<- gallopLeft
			  				RUN A					   RUN B
			  ______________________________ ______________________________				
			  [   |   |   ||   |   |   |MAX] [MIN|   |   |   |   ||   |   ]
			  ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣ ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣
			  |___________| |______________| |___________________| |______|
			  less than MIN       RUN A'             RUN B'        greater than MAX
	  		  
                            |____________________________________|
						            merge RUN A' and RUN B'
	    	 */
	    
			// start point (RUN B의 시작점보다 작으면서 RUN A 에서 merge를 시작할 위치)
			int lo = gallopRight(array[start2], array, start1, length1);

			/*
			 * 만약 RUN A의 길이와 merge를 시작할 지점이 같을 경우 
			 * 이미 정렬되어있는 상태로 정렳 할 필요 없음
			 */
			if (length1 == lo) {
				return;
			}
	    	start1 += lo;
	    	length1 -= lo;
	    	
	    	// end point (RUN A의 끝 점보다 크면서 RUN B에서 merge가 끝나는 위치)
			int hi = gallopLeft(array[start1 + length1 - 1], array, start2, length2);

			if (hi == 0) {
				return;
			}

			length2 = hi;
			if (length1 <= length2) {
				mergeLo(start1, length1, start2, length2);
			}
			else {
				mergeHi(start1, length1, start2, length2);
			}
		}
	    


		/**
		 * gallop_right() 함수를 수행하여 RUN B 의 첫번째 원소보다 
		 * 큰 원소들이 첫번째 출현하는 위치를 RUN A 에서 찾는다.
		 * 
		 * 
		 * @param key    run B의 key
		 * @param array  배열
		 * @param base   run A의 시작지점
		 * @param lenOfA run A 의 길이
		 * @return 제외되어야 할 부분의 위치 다음 인덱스를 반환
		 */
	    private int gallopRight(int key, int[] array, int base, int lenOfA) {


	    	int lo = 0;		// 이전 탐색(gallop) 지점
	    	int hi = 1;		// 현재 탐색(gallop) 지점
	    	
	    	/*
	    	 * RUN B의 시작지점 값(key)이 RUN A의 시작지점 값보다 작을 경우
	    	 * 제외 될 원소는 없으므로 0 리턴 
	    	 */
	    	if(key < array[base]) {
	    		return 0;
	    	}
	    	
	    	else {
		    	/*
	    		
				  gallopRight ->								
				  				RUN A		      key	   RUN B
				  ______________________________ ______________________________				
				  [   |   |   ||   |   |   |MAX] [MIN|   |   |   |   ||   |   ]
				  ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣ ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣
				  |___________| |______________| |___________________| |______|
				  less than MIN       RUN A'             RUN B'        greater than MAX
		  		  
		  		                |____________________________________|
	 									merge RUN A' and RUN B'
		    	 */
				
				int maxLen = lenOfA; // galloping을 하여 가질 수 있는 최대 한계값
				while (hi < maxLen && array[base + hi] <= key) {
					lo = hi;
					hi = (hi << 1) + 1;

					if (hi <= 0) { // overflow 발생시 run A의 끝 점으로 초기화
						hi = maxLen;
					}
				}

				if (hi > maxLen) {
					hi = maxLen;
				}
			}

			lo++;

			// binary search (Upper Bound)
			while (lo < hi) {
				int mid = lo + ((hi - lo) >>> 1);

				if (key < array[base + mid]) {
					hi = mid;
				} else {
					lo = mid + 1;
				}
			}
			return hi;
	    }
	    
	    /**
	     * gallop_left() 함수를 수행하여 RUN A 의 첫번째 원소(오른쪽 끝)보다 
	     * 큰 원소들이 첫번째로 출현하는 위치를 RUN B 에서 찾는다.
	     * 
	     * 
	     * @param key run A의 key
	     * @param array	배열
	     * @param base run B의 시작 지점
	     * @param lenOfB run B 의 길이
	     * @return 제외되어야 할 부분의 위치 다음 인덱스를 반환
	     */
	    private int gallopLeft(int key, int[] array, int base, int lenOfB) {

			int lo = 0;
			int hi = 1;

			/*
			 * key가 B의 탐색의 첫 위치(오른쪽 끝)보다 크면 제외되어야 할 부분은 없으므로 
			 * run B의 마지막 인덱스를 반환
			 */
			if (key > array[base + lenOfB - 1]) {
				return lenOfB;
			}

			else {
		    	/**
				
				                                                 <- gallopLeft
				               RUN A					   RUN B
				  ______________________________ ______________________________				
				  [   |   |   ||   |   |   |MAX] [MIN|   |   |   |   ||   |   ]
				  ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣ ￣￣￣￣￣￣￣￣￣￣￣￣￣￣￣
				  |___________| |______________| |___________________| |______|
				  less than MIN       RUN A'             RUN B'        greater than MAX
		  		  
				                |____________________________________|
				                         merge RUN A' and RUN B'
		    	 */
				
				
				// run B의 오른쪽부터 시작해야하므로 뒤쪽 시작점을 가리키는 변수
				int startPointOfRun = base + lenOfB - 1;

				int maxLen = lenOfB; // galloping을 하여 가질 수 있는 최대 한계값

				while (hi < maxLen && key <= array[startPointOfRun - hi]) {
					lo = hi;
					hi = (hi << 1) + 1;

					// overflow가 발생시 runB의 끝점(왼쪽 끝)으로 초기화
					if (hi <= 0) {
						hi = maxLen;
					}
				}

				if (hi > maxLen) {
					hi = maxLen;
				}

				/*
				 * 뒤에서부터 탐색했기 때문에 실제 가리키는 인덱스는 lo > hi 이므로 
				 * 이분 탐색을 하기 위해 run B에 대해 가리키는 지점을 서로 바꿔준다.
				 */
	    		int temp = lo;
	    		lo = lenOfB - 1 - hi;
	    		hi = lenOfB - 1 - temp;
	    	}
	    	
	    	lo++;
	    	
			// binary search (lower bound)
			while (lo < hi) {
				int mid = lo + ((hi - lo) >>> 1);

				if (key <= array[base + mid]) {
					hi = mid;
				} else {
					lo = mid + 1;
				}
			}
			
			return hi;
		}

	    
		/**
		 * 상대적으로 낮은 인덱스에 위치한 run을 기준으로 복사하여
		 * 실제 배열 원소를 병합하는 메소드 
		 * 
		 * @param start1 RUN A에서의 병합 시작 지점
		 * @param length1 RUN A에서의 병합해야 할 길이(개수)
		 * @param start2 RUN B에서의 병합 시작 지점
		 * @param length2 RUN B에서의 병합해야 할 길이(개수)
		 */
		private void mergeLo(int start1, int length1, int start2, int length2) {
			// RUN A 를 담을 임시 복사 배열
			int[] temp = new int[length1];
			System.arraycopy(array, start1, temp, 0, length1); // RUN A를 temp 배열에 복사

			int insertIdx = start1;		// 재배치 되는 위치
			int runBIdx = start2;		// RUN B의 탐색 위치
			int tempIdx = 0;			// 복사한 RUN A의 탐색 위치

			int leftRemain = length1; 		// 배치해야 할 RUN A의 원소 개수
			int rightRemain = length2; 		// 배치해야 할 RUN B의 원소 개수

			// 두 원소 중 먼저 소진 될 때 까지 반복
			while (leftRemain != 0 && rightRemain != 0) {

				// RUN B < RUN A 라면 RUN B 원소 삽입
				if (array[runBIdx] < temp[tempIdx]) {
					array[insertIdx++] = array[runBIdx++];
					rightRemain--;
				} else {
					array[insertIdx++] = temp[tempIdx++];
					leftRemain--;
				}
			}

			// 왼쪽 부분 리스트가 남아있을 경우
			if (leftRemain != 0) {
				System.arraycopy(temp, tempIdx, array, insertIdx, leftRemain);
			} 
			else {	// 오른 쪽 부분 리스트가 남아있을 경우
				System.arraycopy(array, runBIdx, array, insertIdx, rightRemain);
			}

		}
	    
		
		/**
		 * 상대적으로 높은 인덱스에 위치한 run을 기준으로 복사하여
		 * 실제 배열 원소를 병합하는 메소드 
		 * 
		 * @param start1 RUN A에서의 병합 시작 지점
		 * @param length1 RUN A에서의 병합해야 할 길이(개수)
		 * @param start2 RUN B에서의 병합 시작 지점
		 * @param length2 RUN B에서의 병합해야 할 길이(개수)
		 */
		private void mergeHi(int start1, int length1, int start2, int length2) {
			// RUN B 를 담을 임시 복사 배열
			int[] temp = new int[length2];
			System.arraycopy(array, start2, temp, 0, length2);

			int insertIdx = start2 + length2 - 1;	// 재배치되는 위치 
			int runAIdx = start1 + length1 - 1;		// run A의 탐색 위치
			int tempIdx = length2 - 1;				// 복사한 run B의 탐색 위치

			int leftRemain = length1; 				// 배치해야 할 RUN A의 원소 개수
			int rightRemain = length2;				// 배치해야 할 RUN B의 원소 개수

			while (leftRemain != 0 && rightRemain != 0) {

				// RUN A' > RUN B' 라면 RUN A' 원소 삽입 (내림차순이기 때문)
				if (array[runAIdx] > temp[tempIdx]) {
					array[insertIdx--] = array[runAIdx--];
					leftRemain--;
				} else {
					array[insertIdx--] = temp[tempIdx--];
					rightRemain--;
				}
			}

			// 오른쪽 부분 리스트가 남아있을 경우
			if (rightRemain != 0) {
				System.arraycopy(temp, 0, array, start1, rightRemain);
			} else {
				System.arraycopy(array, start1, array, start1, leftRemain);
			}

		}
	    	
	    	
	}
	
	
	
	
	
	public static void sort(int[] a) {
		sort(a, 0, a.length);
	}
	
	public static void sort(int[] a, int lo, int hi) {
		
		int remain = hi - lo;
		// 정렬해야 할 원소가 1개 이하일 경우 정렬 할 필요가 없다.
		if(remain < 2) {
			return;
		}
		/**
		 * 일정 크기 이하의 배열이라면 
		 * binaryInsertionSort로 정렬 뒤 바로 반환
		 * 
		 * @see BinaryInsertionSort.BinaryInsertionSort
		 */
		if(remain < THRESHOLD) {
			int increaseRange = getAscending(a, lo, hi);
			BinarySort(a, lo, hi, lo + increaseRange);
			return;
		}
		
		
		IntMergeStack ims = new IntMergeStack(a);
		int minRun = minRunLength(remain);	// run의 최소 길이
		do {
			
			/*
			 * 정렬 된 구간의 길이를 구한다. 
			 */
			int runLen = getAscending(a, lo, hi);
			
			/*
			 * 만약 정렬 된 부분의 길이가 minRun 보다 작다면
			 * 정렬 된 부분 길이를 포함한 부분 배열에 대해
			 * 이진 삽입 정렬을 시행한다.    
			 */
			if(runLen < minRun) {
				/*
				 * [lo : lo+minRun] 구간에 대해 정렬
				 * 
				 * counts : run에 있는 요소의 총 개수
				 * 이 때 최소 run 크기가 남은 원소 개수보다 클 수 있으므로 이를 처리해준다.
				 */
				int counts = remain < minRun ? remain : minRun;
				
				/*
				 * BinarySort(array, lo, hi, start);
				 * index[lo] ~ index[lo + counts] 구간을 삽입 정렬을 하되,
				 * index[lo + runLen] 부터 삽입정렬을 시작함.
				 * (앞서 구한 오름차순 길이인 runLen에 의해 lo + runLen의 이전 인덱스는 이미 오름차순 상태임) 
				 */
				BinarySort(a, lo, lo + counts, lo + runLen);
				
				// 이진 삽입 정렬이 수행되었기에 증가하는 길이는 endPoint가 된다.
				runLen = counts;
			}
			// stack에 run의 시작점과 해당 run의 길이를 스택에 push한다. 
			ims.pushRun(lo, runLen);
			ims.merge();
			
			lo += runLen;
			remain -= runLen;
		} while(remain != 0);	// 정렬 해야 할 원소가 0개가 될 때 까지 반복
	
		ims.mergeForce();

	}
	
	
	// ============  아래는 Binary Insertion Sort에서 구현했던 내용들임  ================ //
	
	private static void BinarySort(int[] a, int lo, int hi ,int start) {

		if(lo == start) {
			start++;
		}

		for (; start < hi; start++) {
			int target = a[start];

			int loc = binarySearch(a, target, lo, start);

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
	

	
	
	
	/*==========================
	 * sorting long type array 
	 *==========================*/
	
	
	private static class LongMergeStack {
		
		private long[] array;
		private int[] runBase;
		private int[] runLength;
		private int stackSize = 0;	// run 스택의 원소 개수를 가리킬 변수
	    
		public LongMergeStack(long[] a) {
			this.array = a;
			int len = a.length;
	    	

			int stackLen = (len <    120  ?  5 :
							len <   1542  ? 10 :
							len < 119151  ? 19 : 40);
    	
			runBase = new int[stackLen];
			runLength = new int[stackLen];
		}
	    
		public void pushRun(int runBase, int runLen) {
			this.runBase[stackSize] = runBase;
			this.runLength[stackSize] = runLen;
			stackSize++;
		}
	    
		public void mergeForce() {

			while (stackSize > 1) {
				if (stackSize > 2 && runLength[stackSize - 3] < runLength[stackSize - 1]) {
					merge(stackSize - 3);
				} else {
					merge(stackSize - 2);
				}
			}
		}
	    
	    
	    public void merge() {

			while (stackSize > 1) {
				if (stackSize > 2 && runLength[stackSize - 3] <= runLength[stackSize - 2] + runLength[stackSize - 1]
						|| stackSize > 3 && runLength[stackSize - 4] <= runLength[stackSize - 3] + runLength[stackSize - 2]) {

					if (runLength[stackSize - 3] < runLength[stackSize - 1]) {
						merge(stackSize - 3);
					} 
					else {
						merge(stackSize - 2);
					}
				}
				else if (runLength[stackSize - 2] <= runLength[stackSize - 1]) {
					merge(stackSize - 2);
				}
				else {
					break;
				}
			}
		}
	    
	    /**
	     * run[idx] 와 run[idx + 1]이 병합 됨
	     * 
	     * @param idx 병합되는 두 서브리스트(run) 중 낮은 인덱스
	     */
		private void merge(int idx) {

			int start1 = runBase[idx];
			int length1 = runLength[idx];
			int start2 = runBase[idx + 1];
			int length2 = runLength[idx + 1];

			runLength[idx] = length1 + length2;

			if (idx == (stackSize - 3)) {
				runBase[idx + 1] = runBase[idx + 2];
				runLength[idx + 1] = runLength[idx + 2];
			}
			stackSize--;

			int lo = gallopRight(array[start2], array, start1, length1);

			if (length1 == lo) {
				return;
			}
	    	start1 += lo;
	    	length1 -= lo;
	    	
			int hi = gallopLeft(array[start1 + length1 - 1], array, start2, length2);

			if (hi == 0) {
				return;
			}

			length2 = hi;
			if (length1 <= length2) {
				mergeLo(start1, length1, start2, length2);
			}
			else {
				mergeHi(start1, length1, start2, length2);
			}
		}
	    
	    
		/**
		 * gallop_right() 함수를 수행하여 RUN B 의 첫번째 원소보다 
		 * 큰 원소들이 첫번째 출현하는 위치를 RUN A 에서 찾는다.
		 * 
		 * 
		 * @param key    run B의 key
		 * @param array  배열
		 * @param base   run A의 시작지점
		 * @param lenOfA run A 의 길이
		 * @return 제외되어야 할 부분의 위치 다음 인덱스를 반환
		 */
	    private int gallopRight(long key, long[] array, int base, int lenOfA) {


	    	int lo = 0;	
	    	int hi = 1;

	    	if(key < array[base]) {
	    		return 0;
	    	}
	    	
	    	else {

				int maxLen = lenOfA; 
				while (hi < maxLen && array[base + hi] <= key) {
					lo = hi;
					hi = (hi << 1) + 1;

					if (hi <= 0) { 
						hi = maxLen;
					}
				}

				if (hi > maxLen) {
					hi = maxLen;
				}
			}

			lo++;

			while (lo < hi) {
				int mid = lo + ((hi - lo) >>> 1);

				if (key < array[base + mid]) {
					hi = mid;
				} else {
					lo = mid + 1;
				}
			}
			return hi;
	    }
	    
	    /**
	     * gallop_left() 함수를 수행하여 RUN A 의 첫번째 원소(오른쪽 끝)보다 
	     * 큰 원소들이 첫번째로 출현하는 위치를 RUN B 에서 찾는다.
	     * 
	     * 
	     * @param key run A의 key
	     * @param array	배열
	     * @param base run B의 시작 지점
	     * @param lenOfB run B 의 길이
	     * @return 제외되어야 할 부분의 위치 다음 인덱스를 반환
	     */
	    private int gallopLeft(long key, long[] array, int base, int lenOfB) {

			int lo = 0;
			int hi = 1;

			if (key > array[base + lenOfB - 1]) {
				return lenOfB;
			}

			else {

				int startPointOfRun = base + lenOfB - 1;

				int maxLen = lenOfB; 

				while (hi < maxLen && key <= array[startPointOfRun - hi]) {
					lo = hi;
					hi = (hi << 1) + 1;

					if (hi <= 0) {
						hi = maxLen;
					}
				}

				if (hi > maxLen) {
					hi = maxLen;
				}

	    		int temp = lo;
	    		lo = lenOfB - 1 - hi;
	    		hi = lenOfB - 1 - temp;
	    	}
	    	
	    	lo++;
	    	
			// binary search (lower bound)
			while (lo < hi) {
				int mid = lo + ((hi - lo) >>> 1);

				if (key <= array[base + mid]) {
					hi = mid;
				} else {
					lo = mid + 1;
				}
			}
			return hi;
		}

	    
		/**
		 * 상대적으로 낮은 인덱스에 위치한 run을 기준으로 복사하여
		 * 실제 배열 원소를 병합하는 메소드 
		 * 
		 * @param start1 RUN A에서의 병합 시작 지점
		 * @param length1 RUN A에서의 병합해야 할 길이(개수)
		 * @param start2 RUN B에서의 병합 시작 지점
		 * @param length2 RUN B에서의 병합해야 할 길이(개수)
		 */
		private void mergeLo(int start1, int length1, int start2, int length2) {

			long[] temp = new long[length1];
			System.arraycopy(array, start1, temp, 0, length1);

			int insertIdx = start1;	
			int runBIdx = start2;
			int tempIdx = 0;

			int leftRemain = length1; 
			int rightRemain = length2;

			while (leftRemain != 0 && rightRemain != 0) {

				if (array[runBIdx] < temp[tempIdx]) {
					array[insertIdx++] = array[runBIdx++];
					rightRemain--;
				} else {
					array[insertIdx++] = temp[tempIdx++];
					leftRemain--;
				}
			}

			if (leftRemain != 0) {
				System.arraycopy(temp, tempIdx, array, insertIdx, leftRemain);
			} 
			else {
				System.arraycopy(array, runBIdx, array, insertIdx, rightRemain);
			}

		}
	    
		
		/**
		 * 상대적으로 높은 인덱스에 위치한 run을 기준으로 복사하여
		 * 실제 배열 원소를 병합하는 메소드 
		 * 
		 * @param start1 RUN A에서의 병합 시작 지점
		 * @param length1 RUN A에서의 병합해야 할 길이(개수)
		 * @param start2 RUN B에서의 병합 시작 지점
		 * @param length2 RUN B에서의 병합해야 할 길이(개수)
		 */
		private void mergeHi(int start1, int length1, int start2, int length2) {

			long[] temp = new long[length2];
			System.arraycopy(array, start2, temp, 0, length2);

			int insertIdx = start2 + length2 - 1;
			int runAIdx = start1 + length1 - 1;
			int tempIdx = length2 - 1;

			int leftRemain = length1;
			int rightRemain = length2;

			while (leftRemain != 0 && rightRemain != 0) {

				if (array[runAIdx] > temp[tempIdx]) {
					array[insertIdx--] = array[runAIdx--];
					leftRemain--;
				} else {
					array[insertIdx--] = temp[tempIdx--];
					rightRemain--;
				}
			}

			if (rightRemain != 0) {
				System.arraycopy(temp, 0, array, start1, rightRemain);
			} else {
				System.arraycopy(array, start1, array, start1, leftRemain);
			}

		}
	    	
	    	
	}
	
	
	
	
	
	public static void sort(long[] a) {
		sort(a, 0, a.length);
	}
	
	public static void sort(long[] a, int lo, int hi) {
		
		int remain = hi - lo;
		// 정렬해야 할 원소가 1개 이하일 경우 정렬 할 필요가 없다.
		if(remain < 2) {
			return;
		}
		/**
		 * 일정 크기 이하의 배열이라면 
		 * binaryInsertionSort로 정렬 뒤 바로 반환
		 * 
		 * @see BinaryInsertionSort.BinaryInsertionSort
		 */
		if(remain < THRESHOLD) {
			int increaseRange = getAscending(a, lo, hi);
			BinarySort(a, lo, hi, lo + increaseRange);
			return;
		}
		
		
		LongMergeStack ims = new LongMergeStack(a);
		int minRun = minRunLength(remain);	// run의 최소 길이
		do {

			int runLen = getAscending(a, lo, hi);

			if(runLen < minRun) {

				int counts = remain < minRun ? remain : minRun;

				BinarySort(a, lo, lo + counts, lo + runLen);
				
				runLen = counts;
			}
			ims.pushRun(lo, runLen);
			ims.merge();
			
			lo += runLen;
			remain -= runLen;
		} while(remain != 0);
		ims.mergeForce();

	}
	

	private static void BinarySort(long[] a, int lo, int hi ,int start) {

		if(lo == start) {
			start++;
		}

		for (; start < hi; start++) {
			long target = a[start];

			int loc = binarySearch(a, target, lo, start);

			int j = start - 1;

			while (j >= loc) {
				a[j + 1] = a[j];
				j--;
			}

			a[loc] = target;
		}

	}
	
	private static int binarySearch(long[] a, long key, int lo, int hi) {

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
	
	private static int getAscending(long[] a, int lo, int hi) {	

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
	
	private static void reversing(long[] a, int lo, int hi) {
		hi--;
		while (lo < hi) {
			long temp = a[lo];
			a[lo++] = a[hi];
			a[hi--] = temp;
		}
	}
	
	
	

	
	
	
	/*==========================
	 * sorting float type array 
	 *==========================*/
	
	
	private static class FloatMergeStack {
		
		private float[] array;
		private int[] runBase;
		private int[] runLength;
		private int stackSize = 0;	// run 스택의 원소 개수를 가리킬 변수
	    
		public FloatMergeStack(float[] a) {
			this.array = a;
			int len = a.length;

			int stackLen = (len <    120  ?  5 :
							len <   1542  ? 10 :
							len < 119151  ? 19 : 40);
    	
			runBase = new int[stackLen];
			runLength = new int[stackLen];
		}
	    
		public void pushRun(int runBase, int runLen) {
			this.runBase[stackSize] = runBase;
			this.runLength[stackSize] = runLen;
			stackSize++;
		}
	    
		public void mergeForce() {

			while (stackSize > 1) {
				if (stackSize > 2 && runLength[stackSize - 3] < runLength[stackSize - 1]) {
					merge(stackSize - 3);
				} else {
					merge(stackSize - 2);
				}
			}
		}
	    
	    
	    public void merge() {

			while (stackSize > 1) {
				if (stackSize > 2 && runLength[stackSize - 3] <= runLength[stackSize - 2] + runLength[stackSize - 1]
						|| stackSize > 3 && runLength[stackSize - 4] <= runLength[stackSize - 3] + runLength[stackSize - 2]) {

					if (runLength[stackSize - 3] < runLength[stackSize - 1]) {
						merge(stackSize - 3);
					} 
					else {
						merge(stackSize - 2);
					}
				}
				else if (runLength[stackSize - 2] <= runLength[stackSize - 1]) {
					merge(stackSize - 2);
				}
				else {
					break;
				}
			}
		}
	    
	    /**
	     * run[idx] 와 run[idx + 1]이 병합 됨
	     * 
	     * @param idx 병합되는 두 서브리스트(run) 중 낮은 인덱스
	     */
		private void merge(int idx) {

			int start1 = runBase[idx];
			int length1 = runLength[idx];
			int start2 = runBase[idx + 1];
			int length2 = runLength[idx + 1];

			runLength[idx] = length1 + length2;

			if (idx == (stackSize - 3)) {
				runBase[idx + 1] = runBase[idx + 2];
				runLength[idx + 1] = runLength[idx + 2];
			}
			stackSize--;

			int lo = gallopRight(array[start2], array, start1, length1);

			if (length1 == lo) {
				return;
			}
	    	start1 += lo;
	    	length1 -= lo;
	    	
			int hi = gallopLeft(array[start1 + length1 - 1], array, start2, length2);

			if (hi == 0) {
				return;
			}

			length2 = hi;
			if (length1 <= length2) {
				mergeLo(start1, length1, start2, length2);
			}
			else {
				mergeHi(start1, length1, start2, length2);
			}
		}
	    
	    
		/**
		 * gallop_right() 함수를 수행하여 RUN B 의 첫번째 원소보다 
		 * 큰 원소들이 첫번째 출현하는 위치를 RUN A 에서 찾는다.
		 * 
		 * 
		 * @param key    run B의 key
		 * @param array  배열
		 * @param base   run A의 시작지점
		 * @param lenOfA run A 의 길이
		 * @return 제외되어야 할 부분의 위치 다음 인덱스를 반환
		 */
	    private int gallopRight(float key, float[] array, int base, int lenOfA) {


	    	int lo = 0;	
	    	int hi = 1;

	    	if(key < array[base]) {
	    		return 0;
	    	}
	    	
	    	else {

				int maxLen = lenOfA; 
				while (hi < maxLen && array[base + hi] <= key) {
					lo = hi;
					hi = (hi << 1) + 1;

					if (hi <= 0) { 
						hi = maxLen;
					}
				}

				if (hi > maxLen) {
					hi = maxLen;
				}
			}

			lo++;

			while (lo < hi) {
				int mid = lo + ((hi - lo) >>> 1);

				if (key < array[base + mid]) {
					hi = mid;
				} else {
					lo = mid + 1;
				}
			}
			return hi;
	    }
	    
	    /**
	     * gallop_left() 함수를 수행하여 RUN A 의 첫번째 원소(오른쪽 끝)보다 
	     * 큰 원소들이 첫번째로 출현하는 위치를 RUN B 에서 찾는다.
	     * 
	     * 
	     * @param key run A의 key
	     * @param array	배열
	     * @param base run B의 시작 지점
	     * @param lenOfB run B 의 길이
	     * @return 제외되어야 할 부분의 위치 다음 인덱스를 반환
	     */
	    private int gallopLeft(float key, float[] array, int base, int lenOfB) {

			int lo = 0;
			int hi = 1;

			if (key > array[base + lenOfB - 1]) {
				return lenOfB;
			}

			else {

				int startPointOfRun = base + lenOfB - 1;

				int maxLen = lenOfB; 

				while (hi < maxLen && key <= array[startPointOfRun - hi]) {
					lo = hi;
					hi = (hi << 1) + 1;

					if (hi <= 0) {
						hi = maxLen;
					}
				}

				if (hi > maxLen) {
					hi = maxLen;
				}

	    		int temp = lo;
	    		lo = lenOfB - 1 - hi;
	    		hi = lenOfB - 1 - temp;
	    	}
	    	
	    	lo++;
	    	
			// binary search (lower bound)
			while (lo < hi) {
				int mid = lo + ((hi - lo) >>> 1);

				if (key <= array[base + mid]) {
					hi = mid;
				} else {
					lo = mid + 1;
				}
			}
			return hi;
		}

	    
		/**
		 * 상대적으로 낮은 인덱스에 위치한 run을 기준으로 복사하여
		 * 실제 배열 원소를 병합하는 메소드 
		 * 
		 * @param start1 RUN A에서의 병합 시작 지점
		 * @param length1 RUN A에서의 병합해야 할 길이(개수)
		 * @param start2 RUN B에서의 병합 시작 지점
		 * @param length2 RUN B에서의 병합해야 할 길이(개수)
		 */
		private void mergeLo(int start1, int length1, int start2, int length2) {

			float[] temp = new float[length1];
			System.arraycopy(array, start1, temp, 0, length1);

			int insertIdx = start1;	
			int runBIdx = start2;
			int tempIdx = 0;

			int leftRemain = length1; 
			int rightRemain = length2;

			while (leftRemain != 0 && rightRemain != 0) {

				if (array[runBIdx] < temp[tempIdx]) {
					array[insertIdx++] = array[runBIdx++];
					rightRemain--;
				} else {
					array[insertIdx++] = temp[tempIdx++];
					leftRemain--;
				}
			}

			if (leftRemain != 0) {
				System.arraycopy(temp, tempIdx, array, insertIdx, leftRemain);
			} 
			else {
				System.arraycopy(array, runBIdx, array, insertIdx, rightRemain);
			}

		}
	    
		
		/**
		 * 상대적으로 높은 인덱스에 위치한 run을 기준으로 복사하여
		 * 실제 배열 원소를 병합하는 메소드 
		 * 
		 * @param start1 RUN A에서의 병합 시작 지점
		 * @param length1 RUN A에서의 병합해야 할 길이(개수)
		 * @param start2 RUN B에서의 병합 시작 지점
		 * @param length2 RUN B에서의 병합해야 할 길이(개수)
		 */
		private void mergeHi(int start1, int length1, int start2, int length2) {

			float[] temp = new float[length2];
			System.arraycopy(array, start2, temp, 0, length2);

			int insertIdx = start2 + length2 - 1;
			int runAIdx = start1 + length1 - 1;
			int tempIdx = length2 - 1;

			int leftRemain = length1;
			int rightRemain = length2;

			while (leftRemain != 0 && rightRemain != 0) {

				if (array[runAIdx] > temp[tempIdx]) {
					array[insertIdx--] = array[runAIdx--];
					leftRemain--;
				} else {
					array[insertIdx--] = temp[tempIdx--];
					rightRemain--;
				}
			}

			if (rightRemain != 0) {
				System.arraycopy(temp, 0, array, start1, rightRemain);
			} else {
				System.arraycopy(array, start1, array, start1, leftRemain);
			}

		}
	    	
	    	
	}
	
	
	
	
	
	public static void sort(float[] a) {
		sort(a, 0, a.length);
	}
	
	public static void sort(float[] a, int lo, int hi) {
		
		int remain = hi - lo;
		// 정렬해야 할 원소가 1개 이하일 경우 정렬 할 필요가 없다.
		if(remain < 2) {
			return;
		}
		/**
		 * 일정 크기 이하의 배열이라면 
		 * binaryInsertionSort로 정렬 뒤 바로 반환
		 * 
		 * @see BinaryInsertionSort.BinaryInsertionSort
		 */
		if(remain < THRESHOLD) {
			int increaseRange = getAscending(a, lo, hi);
			BinarySort(a, lo, hi, lo + increaseRange);
			return;
		}
		
		
		FloatMergeStack ims = new FloatMergeStack(a);
		int minRun = minRunLength(remain);	// run의 최소 길이
		do {

			int runLen = getAscending(a, lo, hi);

			if(runLen < minRun) {

				int counts = remain < minRun ? remain : minRun;

				BinarySort(a, lo, lo + counts, lo + runLen);
				
				runLen = counts;
			}
			ims.pushRun(lo, runLen);
			ims.merge();
			
			lo += runLen;
			remain -= runLen;
		} while(remain != 0);
		ims.mergeForce();

	}
	

	private static void BinarySort(float[] a, int lo, int hi ,int start) {

		if(lo == start) {
			start++;
		}

		for (; start < hi; start++) {
			float target = a[start];

			int loc = binarySearch(a, target, lo, start);

			int j = start - 1;

			while (j >= loc) {
				a[j + 1] = a[j];
				j--;
			}

			a[loc] = target;
		}

	}
	
	private static int binarySearch(float[] a, float key, int lo, int hi) {

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
	
	private static int getAscending(float[] a, int lo, int hi) {	

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
	
	private static void reversing(float[] a, int lo, int hi) {
		hi--;
		while (lo < hi) {
			float temp = a[lo];
			a[lo++] = a[hi];
			a[hi--] = temp;
		}
	}
	
	
	
	
	
	
	
	
	
	
	/*==========================
	 * sorting double type array 
	 *==========================*/
	
	
	private static class DoubleMergeStack {
		
		private double[] array;
		private int[] runBase;
		private int[] runLength;
		private int stackSize = 0;	// run 스택의 원소 개수를 가리킬 변수
	    
		public DoubleMergeStack(double[] a) {
			this.array = a;
			int len = a.length;
	    	

			int stackLen = (len <    120  ?  5 :
							len <   1542  ? 10 :
							len < 119151  ? 19 : 40);
    	
			runBase = new int[stackLen];
			runLength = new int[stackLen];
		}
	    
		public void pushRun(int runBase, int runLen) {
			this.runBase[stackSize] = runBase;
			this.runLength[stackSize] = runLen;
			stackSize++;
		}
	    
		public void mergeForce() {

			while (stackSize > 1) {
				if (stackSize > 2 && runLength[stackSize - 3] < runLength[stackSize - 1]) {
					merge(stackSize - 3);
				} else {
					merge(stackSize - 2);
				}
			}
		}
	    
	    
	    public void merge() {

			while (stackSize > 1) {
				if (stackSize > 2 && runLength[stackSize - 3] <= runLength[stackSize - 2] + runLength[stackSize - 1]
						|| stackSize > 3 && runLength[stackSize - 4] <= runLength[stackSize - 3] + runLength[stackSize - 2]) {

					if (runLength[stackSize - 3] < runLength[stackSize - 1]) {
						merge(stackSize - 3);
					} 
					else {
						merge(stackSize - 2);
					}
				}
				else if (runLength[stackSize - 2] <= runLength[stackSize - 1]) {
					merge(stackSize - 2);
				}
				else {
					break;
				}
			}
		}
	    
	    /**
	     * run[idx] 와 run[idx + 1]이 병합 됨
	     * 
	     * @param idx 병합되는 두 서브리스트(run) 중 낮은 인덱스
	     */
		private void merge(int idx) {

			int start1 = runBase[idx];
			int length1 = runLength[idx];
			int start2 = runBase[idx + 1];
			int length2 = runLength[idx + 1];

			runLength[idx] = length1 + length2;

			if (idx == (stackSize - 3)) {
				runBase[idx + 1] = runBase[idx + 2];
				runLength[idx + 1] = runLength[idx + 2];
			}
			stackSize--;

			int lo = gallopRight(array[start2], array, start1, length1);

			if (length1 == lo) {
				return;
			}
	    	start1 += lo;
	    	length1 -= lo;
	    	
			int hi = gallopLeft(array[start1 + length1 - 1], array, start2, length2);

			if (hi == 0) {
				return;
			}

			length2 = hi;
			if (length1 <= length2) {
				mergeLo(start1, length1, start2, length2);
			}
			else {
				mergeHi(start1, length1, start2, length2);
			}
		}
	    
	    
		/**
		 * gallop_right() 함수를 수행하여 RUN B 의 첫번째 원소보다 
		 * 큰 원소들이 첫번째 출현하는 위치를 RUN A 에서 찾는다.
		 * 
		 * 
		 * @param key    run B의 key
		 * @param array  배열
		 * @param base   run A의 시작지점
		 * @param lenOfA run A 의 길이
		 * @return 제외되어야 할 부분의 위치 다음 인덱스를 반환
		 */
	    private int gallopRight(double key, double[] array, int base, int lenOfA) {


	    	int lo = 0;	
	    	int hi = 1;

	    	if(key < array[base]) {
	    		return 0;
	    	}
	    	
	    	else {

				int maxLen = lenOfA; 
				while (hi < maxLen && array[base + hi] <= key) {
					lo = hi;
					hi = (hi << 1) + 1;

					if (hi <= 0) { 
						hi = maxLen;
					}
				}

				if (hi > maxLen) {
					hi = maxLen;
				}
			}

			lo++;

			while (lo < hi) {
				int mid = lo + ((hi - lo) >>> 1);

				if (key < array[base + mid]) {
					hi = mid;
				} else {
					lo = mid + 1;
				}
			}
			return hi;
	    }
	    
	    /**
	     * gallop_left() 함수를 수행하여 RUN A 의 첫번째 원소(오른쪽 끝)보다 
	     * 큰 원소들이 첫번째로 출현하는 위치를 RUN B 에서 찾는다.
	     * 
	     * 
	     * @param key run A의 key
	     * @param array	배열
	     * @param base run B의 시작 지점
	     * @param lenOfB run B 의 길이
	     * @return 제외되어야 할 부분의 위치 다음 인덱스를 반환
	     */
	    private int gallopLeft(double key, double[] array, int base, int lenOfB) {

			int lo = 0;
			int hi = 1;

			if (key > array[base + lenOfB - 1]) {
				return lenOfB;
			}

			else {

				int startPointOfRun = base + lenOfB - 1;

				int maxLen = lenOfB; 

				while (hi < maxLen && key <= array[startPointOfRun - hi]) {
					lo = hi;
					hi = (hi << 1) + 1;

					if (hi <= 0) {
						hi = maxLen;
					}
				}

				if (hi > maxLen) {
					hi = maxLen;
				}

	    		int temp = lo;
	    		lo = lenOfB - 1 - hi;
	    		hi = lenOfB - 1 - temp;
	    	}
	    	
	    	lo++;
	    	
			// binary search (lower bound)
			while (lo < hi) {
				int mid = lo + ((hi - lo) >>> 1);

				if (key <= array[base + mid]) {
					hi = mid;
				} else {
					lo = mid + 1;
				}
			}
			return hi;
		}

	    
		/**
		 * 상대적으로 낮은 인덱스에 위치한 run을 기준으로 복사하여
		 * 실제 배열 원소를 병합하는 메소드 
		 * 
		 * @param start1 RUN A에서의 병합 시작 지점
		 * @param length1 RUN A에서의 병합해야 할 길이(개수)
		 * @param start2 RUN B에서의 병합 시작 지점
		 * @param length2 RUN B에서의 병합해야 할 길이(개수)
		 */
		private void mergeLo(int start1, int length1, int start2, int length2) {

			double[] temp = new double[length1];
			System.arraycopy(array, start1, temp, 0, length1);

			int insertIdx = start1;	
			int runBIdx = start2;
			int tempIdx = 0;

			int leftRemain = length1; 
			int rightRemain = length2;

			while (leftRemain != 0 && rightRemain != 0) {

				if (array[runBIdx] < temp[tempIdx]) {
					array[insertIdx++] = array[runBIdx++];
					rightRemain--;
				} else {
					array[insertIdx++] = temp[tempIdx++];
					leftRemain--;
				}
			}

			if (leftRemain != 0) {
				System.arraycopy(temp, tempIdx, array, insertIdx, leftRemain);
			} 
			else {
				System.arraycopy(array, runBIdx, array, insertIdx, rightRemain);
			}

		}
	    
		
		/**
		 * 상대적으로 높은 인덱스에 위치한 run을 기준으로 복사하여
		 * 실제 배열 원소를 병합하는 메소드 
		 * 
		 * @param start1 RUN A에서의 병합 시작 지점
		 * @param length1 RUN A에서의 병합해야 할 길이(개수)
		 * @param start2 RUN B에서의 병합 시작 지점
		 * @param length2 RUN B에서의 병합해야 할 길이(개수)
		 */
		private void mergeHi(int start1, int length1, int start2, int length2) {

			double[] temp = new double[length2];
			System.arraycopy(array, start2, temp, 0, length2);

			int insertIdx = start2 + length2 - 1;
			int runAIdx = start1 + length1 - 1;
			int tempIdx = length2 - 1;

			int leftRemain = length1;
			int rightRemain = length2;

			while (leftRemain != 0 && rightRemain != 0) {

				if (array[runAIdx] > temp[tempIdx]) {
					array[insertIdx--] = array[runAIdx--];
					leftRemain--;
				} else {
					array[insertIdx--] = temp[tempIdx--];
					rightRemain--;
				}
			}

			if (rightRemain != 0) {
				System.arraycopy(temp, 0, array, start1, rightRemain);
			} else {
				System.arraycopy(array, start1, array, start1, leftRemain);
			}

		}
	    	
	    	
	}
	
	
	
	
	
	public static void sort(double[] a) {
		sort(a, 0, a.length);
	}
	
	public static void sort(double[] a, int lo, int hi) {
		
		int remain = hi - lo;
		// 정렬해야 할 원소가 1개 이하일 경우 정렬 할 필요가 없다.
		if(remain < 2) {
			return;
		}
		/**
		 * 일정 크기 이하의 배열이라면 
		 * binaryInsertionSort로 정렬 뒤 바로 반환
		 * 
		 * @see BinaryInsertionSort.BinaryInsertionSort
		 */
		if(remain < THRESHOLD) {
			int increaseRange = getAscending(a, lo, hi);
			BinarySort(a, lo, hi, lo + increaseRange);
			return;
		}
		
		
		DoubleMergeStack ims = new DoubleMergeStack(a);
		int minRun = minRunLength(remain);	// run의 최소 길이
		do {

			int runLen = getAscending(a, lo, hi);

			if(runLen < minRun) {

				int counts = remain < minRun ? remain : minRun;

				BinarySort(a, lo, lo + counts, lo + runLen);
				
				runLen = counts;
			}
			ims.pushRun(lo, runLen);
			ims.merge();
			
			lo += runLen;
			remain -= runLen;
		} while(remain != 0);
		ims.mergeForce();

	}
	

	private static void BinarySort(double[] a, int lo, int hi ,int start) {

		if(lo == start) {
			start++;
		}

		for (; start < hi; start++) {
			double target = a[start];

			int loc = binarySearch(a, target, lo, start);

			int j = start - 1;

			while (j >= loc) {
				a[j + 1] = a[j];
				j--;
			}

			a[loc] = target;
		}

	}
	
	private static int binarySearch(double[] a, double key, int lo, int hi) {

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
	
	private static int getAscending(double[] a, int lo, int hi) {	

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
	
	private static void reversing(double[] a, int lo, int hi) {
		hi--;
		while (lo < hi) {
			double temp = a[lo];
			a[lo++] = a[hi];
			a[hi--] = temp;
		}
	}
	
	
	
	
	
	
	
	
	

	
	
	
	//============= Object type ============//

	private static class ComparableObjectMergeStack {
		
		private Object[] array;
		private int[] runBase;
		private int[] runLength;
		private int stackSize = 0;
	    
		public ComparableObjectMergeStack(Object[] a) {
			this.array = a;
			int len = a.length;
	    	
	    	

			int stackLen = (len <    120  ?  5 :
							len <   1542  ? 10 :
							len < 119151  ? 19 : 40);
    	
			runBase = new int[stackLen];
			runLength = new int[stackLen];
		}
	    
		public void pushRun(int runBase, int runLen) {
			this.runBase[stackSize] = runBase;
			this.runLength[stackSize] = runLen;
			stackSize++;
		}
	    
		public void mergeForce() {

			while (stackSize > 1) {

				if (stackSize > 2 && runLength[stackSize - 3] < runLength[stackSize - 1]) {
					merge(stackSize - 3);
				} else {
					merge(stackSize - 2);
				}
			}
		}
	    
	    
	    public void merge() {

			while (stackSize > 1) {

				if (stackSize > 2 && runLength[stackSize - 3] <= runLength[stackSize - 2] + runLength[stackSize - 1]
						|| stackSize > 3 && runLength[stackSize - 4] <= runLength[stackSize - 3] + runLength[stackSize - 2]) {

					if (runLength[stackSize - 3] < runLength[stackSize - 1]) {
						merge(stackSize - 3);
					} 
					else {
						merge(stackSize - 2);
					}
				}
				else if (runLength[stackSize - 2] <= runLength[stackSize - 1]) {
					merge(stackSize - 2);
				}
				else {
					break;
				}
			}
		}

		@SuppressWarnings("unchecked")
		private void merge(int idx) {

			int start1 = runBase[idx];
			int length1 = runLength[idx];
			int start2 = runBase[idx + 1];
			int length2 = runLength[idx + 1];

			runLength[idx] = length1 + length2;

			if (idx == (stackSize - 3)) {
				runBase[idx + 1] = runBase[idx + 2];
				runLength[idx + 1] = runLength[idx + 2];
			}
			stackSize--;

			int lo = gallopRight((Comparable<Object>)array[start2], array, start1, length1);

			if (length1 == lo) {
				return;
			}
	    	start1 += lo;
	    	length1 -= lo;
	    	
			int hi = gallopLeft((Comparable<Object>)array[start1 + length1 - 1], array, start2, length2);

			if (hi == 0) {
				return;
			}

			length2 = hi;
			if (length1 <= length2) {
				mergeLo(start1, length1, start2, length2);
			}
			else {
				mergeHi(start1, length1, start2, length2);
			}
		}
	    
	    
		/**
		 * gallop_right() 함수를 수행하여 RUN B 의 첫번째 원소보다 
		 * 큰 원소들이 첫번째 출현하는 위치를 RUN A 에서 찾는다.
		 * 
		 * 
		 * @param key    run B의 key
		 * @param array  배열
		 * @param base   run A의 시작지점
		 * @param lenOfA run A 의 길이
		 * @return 제외되어야 할 부분의 위치 다음 인덱스를 반환
		 */
	    private int gallopRight(Comparable<Object> key, Object[] array, int base, int lenOfA) {


	    	int lo = 0;	
	    	int hi = 1;
	    	

	    	if(key.compareTo(array[base]) < 0) {
	    		return 0;
	    	}
	    	
	    	else {
	    	
				int maxLen = lenOfA; 
				while (hi < maxLen && key.compareTo(array[base + hi]) >= 0) {
					lo = hi;
					hi = (hi << 1) + 1;

					if (hi <= 0) {
						hi = maxLen;
					}
				}

				if (hi > maxLen) {
					hi = maxLen;
				}
			}

			lo++;

			while (lo < hi) {
				int mid = lo + ((hi - lo) >>> 1);

				if (key.compareTo(array[base + mid]) < 0) {
					hi = mid;
				} else {
					lo = mid + 1;
				}
			}
			return hi;
	    }
	    
	    /**
	     * gallop_left() 함수를 수행하여 RUN A 의 첫번째 원소(오른쪽 끝)보다 
	     * 큰 원소들이 첫번째로 출현하는 위치를 RUN B 에서 찾는다.
	     * 
	     * 
	     * @param key run A의 key
	     * @param array	배열
	     * @param base run B의 시작 지점
	     * @param lenOfB run B 의 길이
	     * @return 제외되어야 할 부분의 위치 다음 인덱스를 반환
	     */
	    private int gallopLeft(Comparable<Object> key, Object[] array, int base, int lenOfB) {

			int lo = 0;
			int hi = 1;


			if (key.compareTo(array[base + lenOfB - 1]) > 0) {
				return lenOfB;
			}

			else {

				int startPointOfRun = base + lenOfB - 1;

				int maxLen = lenOfB; 

				while (hi < maxLen && key.compareTo(array[startPointOfRun - hi]) <= 0) {
					lo = hi;
					hi = (hi << 1) + 1;

					if (hi <= 0) {
						hi = maxLen;
					}
				}

				if (hi > maxLen) {
					hi = maxLen;
				}

	    		int temp = lo;
	    		lo = lenOfB - 1 - hi;
	    		hi = lenOfB - 1 - temp;
	    	}
	    	
	    	lo++;
	    	
			// binary search (lower bound)
			while (lo < hi) {
				int mid = lo + ((hi - lo) >>> 1);

				if (key.compareTo(array[base + mid]) <= 0) {
					hi = mid;
				} else {
					lo = mid + 1;
				}
			}
			return hi;
		}

	    
		/**
		 * 상대적으로 낮은 인덱스에 위치한 run을 기준으로 복사하여
		 * 실제 배열 원소를 병합하는 메소드 
		 * 
		 * @param start1 RUN A에서의 병합 시작 지점
		 * @param length1 RUN A에서의 병합해야 할 길이(개수)
		 * @param start2 RUN B에서의 병합 시작 지점
		 * @param length2 RUN B에서의 병합해야 할 길이(개수)
		 */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		private void mergeLo(int start1, int length1, int start2, int length2) {

			Object[] temp = new Object[length1];
			System.arraycopy(array, start1, temp, 0, length1);

			int insertIdx = start1;
			int runBIdx = start2;
			int tempIdx = 0;

			int leftRemain = length1;
			int rightRemain = length2; 


			while (leftRemain != 0 && rightRemain != 0) {

				if (((Comparable)array[runBIdx]).compareTo(temp[tempIdx]) < 0) {
					array[insertIdx++] = array[runBIdx++];
					rightRemain--;
				} else {
					array[insertIdx++] = temp[tempIdx++];
					leftRemain--;
				}
			}

			// 왼쪽 부분 리스트가 남아있을 경우
			if (leftRemain != 0) {
				System.arraycopy(temp, tempIdx, array, insertIdx, leftRemain);
			} 
			else {	// 오른 쪽 부분 리스트가 남아있을 경우
				System.arraycopy(array, runBIdx, array, insertIdx, rightRemain);
			}

		}
	    
		
		/**
		 * 상대적으로 높은 인덱스에 위치한 run을 기준으로 복사하여
		 * 실제 배열 원소를 병합하는 메소드 
		 * 
		 * @param start1 RUN A에서의 병합 시작 지점
		 * @param length1 RUN A에서의 병합해야 할 길이(개수)
		 * @param start2 RUN B에서의 병합 시작 지점
		 * @param length2 RUN B에서의 병합해야 할 길이(개수)
		 */
		@SuppressWarnings({ "unchecked", "rawtypes" })
		private void mergeHi(int start1, int length1, int start2, int length2) {
			Object[] temp = new Object[length2];
			System.arraycopy(array, start2, temp, 0, length2);

			int insertIdx = start2 + length2 - 1; 
			int runAIdx = start1 + length1 - 1;
			int tempIdx = length2 - 1;

			int leftRemain = length1;
			int rightRemain = length2;

			while (leftRemain != 0 && rightRemain != 0) {

				if (((Comparable)array[runAIdx]).compareTo(temp[tempIdx]) > 0) {
					array[insertIdx--] = array[runAIdx--];
					leftRemain--;
				} else {
					array[insertIdx--] = temp[tempIdx--];
					rightRemain--;
				}
			}

			if (rightRemain != 0) {
				System.arraycopy(temp, 0, array, start1, rightRemain);
			} else {
				System.arraycopy(array, start1, array, start1, leftRemain);
			}

		}
	    	
	    	
	}
	
	
	
	
	
	public static void sort(Object[] a) {
		sort(a, 0, a.length);
	}
	
	public static void sort(Object[] a, int lo, int hi) {
		
		int remain = hi - lo;
		if(remain < 2) {
			return;
		}

		if(remain < THRESHOLD) {
			int increaseRange = getAscending(a, lo, hi);
			BinarySort(a, lo, hi, lo + increaseRange);
			return;
		}
		
		
		ComparableObjectMergeStack cms = new ComparableObjectMergeStack(a);
		int minRun = minRunLength(remain);	// run의 최소 길이
		do {

			int runLen = getAscending(a, lo, hi);
			

			if(runLen < minRun) {

				int counts = remain < minRun ? remain : minRun;
				

				BinarySort(a, lo, lo + counts, lo + runLen);
				
				runLen = counts;
			}
			cms.pushRun(lo, runLen);
			cms.merge();
			
			lo += runLen;
			remain -= runLen;
		} while(remain != 0);	
		cms.mergeForce();

	}
	

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static void BinarySort(Object[] a, int lo, int hi ,int start) {

		if(lo == start) {
			start++;
		}

		for (; start < hi; start++) {
			Comparable target = (Comparable)a[start];

			int loc = binarySearch(a, target, lo, start);

			int j = start - 1;

			while (j >= loc) {
				a[j + 1] = a[j];
				j--;
			}

			a[loc] = target;
		}

	}
	
	private static int binarySearch(Object[] a, Comparable<Object> key, int lo, int hi) {

		int mid;
		while (lo < hi) {
			mid = (lo + hi) >>> 1;
			if (key.compareTo(a[mid]) <0) {
				hi = mid;
			} 
			else {
				lo = mid + 1;
			}
		}
		return lo;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static int getAscending(Object[] a, int lo, int hi) {	

		int limit = lo + 1;
		if (limit == hi) { 
			return 1;
		}

		if (((Comparable)a[lo]).compareTo(a[limit]) <= 0) {
			while (limit < hi && ((Comparable)a[limit - 1]).compareTo(a[limit]) <= 0) {
				limit++;
			}
		}
		else {
			while (limit < hi && ((Comparable)a[limit - 1]).compareTo(a[limit]) > 0) {
				limit++;
			}
			reversing(a, lo, limit);
		}

		return limit - lo;
	}
	
	private static void reversing(Object[] a, int lo, int hi) {
		hi--;
		while (lo < hi) {
			Object temp = a[lo];
			a[lo++] = a[hi];
			a[hi--] = temp;
		}
	}
	
	
	
	
	private static class ComparatorObjectMergeStack<T> {
		
		private T[] array;
		final private Comparator<? super T> c;
		private int[] runBase;
		private int[] runLength;
		private int stackSize = 0;
	    
		public ComparatorObjectMergeStack(T[] a, Comparator<? super T> c) {
			this.array = a;
			this.c = c;
			int len = a.length;
	    	
	    	

			int stackLen = (len <    120  ?  5 :
							len <   1542  ? 10 :
							len < 119151  ? 19 : 40);
    	
			runBase = new int[stackLen];
			runLength = new int[stackLen];
		}
	    
		public void pushRun(int runBase, int runLen) {
			this.runBase[stackSize] = runBase;
			this.runLength[stackSize] = runLen;
			stackSize++;
		}
	    
		public void mergeForce() {

			while (stackSize > 1) {

				if (stackSize > 2 && runLength[stackSize - 3] < runLength[stackSize - 1]) {
					merge(stackSize - 3);
				} else {
					merge(stackSize - 2);
				}
			}
		}
	    
	    
	    public void merge() {

			while (stackSize > 1) {

				if (stackSize > 2 && runLength[stackSize - 3] <= runLength[stackSize - 2] + runLength[stackSize - 1]
						|| stackSize > 3 && runLength[stackSize - 4] <= runLength[stackSize - 3] + runLength[stackSize - 2]) {

					if (runLength[stackSize - 3] < runLength[stackSize - 1]) {
						merge(stackSize - 3);
					} 
					else {
						merge(stackSize - 2);
					}
				}
				else if (runLength[stackSize - 2] <= runLength[stackSize - 1]) {
					merge(stackSize - 2);
				}
				else {
					break;
				}
			}
		}

		private void merge(int idx) {

			int start1 = runBase[idx];
			int length1 = runLength[idx];
			int start2 = runBase[idx + 1];
			int length2 = runLength[idx + 1];

			runLength[idx] = length1 + length2;

			if (idx == (stackSize - 3)) {
				runBase[idx + 1] = runBase[idx + 2];
				runLength[idx + 1] = runLength[idx + 2];
			}
			stackSize--;

			int lo = gallopRight(array[start2], array, start1, length1, c);

			if (length1 == lo) {
				return;
			}
	    	start1 += lo;
	    	length1 -= lo;
	    	
			int hi = gallopLeft(array[start1 + length1 - 1], array, start2, length2, c);

			if (hi == 0) {
				return;
			}

			length2 = hi;
			if (length1 <= length2) {
				mergeLo(start1, length1, start2, length2);
			}
			else {
				mergeHi(start1, length1, start2, length2);
			}
		}
	    
	    
		/**
		 * gallop_right() 함수를 수행하여 RUN B 의 첫번째 원소보다 
		 * 큰 원소들이 첫번째 출현하는 위치를 RUN A 에서 찾는다.
		 * 
		 * 
		 * @param key    run B의 key
		 * @param array  배열
		 * @param base   run A의 시작지점
		 * @param lenOfA run A 의 길이
		 * @return 제외되어야 할 부분의 위치 다음 인덱스를 반환
		 */
	    private static <T> int gallopRight(T key, T[] array, int base, int lenOfA, Comparator<? super T> c) {


	    	int lo = 0;	
	    	int hi = 1;
	    	

	    	if(c.compare(key, array[base]) < 0) {
	    		return 0;
	    	}
	    	
	    	else {
	    	
				int maxLen = lenOfA; 
				while (hi < maxLen && c.compare(key, array[base + hi]) >= 0) {
					lo = hi;
					hi = (hi << 1) + 1;

					if (hi <= 0) {
						hi = maxLen;
					}
				}

				if (hi > maxLen) {
					hi = maxLen;
				}
			}

			lo++;

			while (lo < hi) {
				int mid = lo + ((hi - lo) >>> 1);

				if (c.compare(key, array[base + mid]) < 0) {
					hi = mid;
				} else {
					lo = mid + 1;
				}
			}
			return hi;
	    }
	    
	    /**
	     * gallop_left() 함수를 수행하여 RUN A 의 첫번째 원소(오른쪽 끝)보다 
	     * 큰 원소들이 첫번째로 출현하는 위치를 RUN B 에서 찾는다.
	     * 
	     * 
	     * @param key run A의 key
	     * @param array	배열
	     * @param base run B의 시작 지점
	     * @param lenOfB run B 의 길이
	     * @return 제외되어야 할 부분의 위치 다음 인덱스를 반환
	     */
	    private static <T> int gallopLeft(T key, T[] array, int base, int lenOfB, Comparator<? super T> c) {

			int lo = 0;
			int hi = 1;


			if (c.compare(key, array[base + lenOfB - 1]) > 0) {
				return lenOfB;
			}

			else {

				int startPointOfRun = base + lenOfB - 1;

				int maxLen = lenOfB; 

				while (hi < maxLen && c.compare(key, array[startPointOfRun - hi]) <= 0) {
					lo = hi;
					hi = (hi << 1) + 1;

					if (hi <= 0) {
						hi = maxLen;
					}
				}

				if (hi > maxLen) {
					hi = maxLen;
				}

	    		int temp = lo;
	    		lo = lenOfB - 1 - hi;
	    		hi = lenOfB - 1 - temp;
	    	}
	    	
	    	lo++;
	    	
			// binary search (lower bound)
			while (lo < hi) {
				int mid = lo + ((hi - lo) >>> 1);

				if (c.compare(key, array[base + mid]) <= 0) {
					hi = mid;
				} else {
					lo = mid + 1;
				}
			}
			return hi;
		}

	    
		/**
		 * 상대적으로 낮은 인덱스에 위치한 run을 기준으로 복사하여
		 * 실제 배열 원소를 병합하는 메소드 
		 * 
		 * @param start1 RUN A에서의 병합 시작 지점
		 * @param length1 RUN A에서의 병합해야 할 길이(개수)
		 * @param start2 RUN B에서의 병합 시작 지점
		 * @param length2 RUN B에서의 병합해야 할 길이(개수)
		 */
		@SuppressWarnings({ "unchecked" })
		private void mergeLo(int start1, int length1, int start2, int length2) {

			T[] temp = (T[])java.lang.reflect.Array.newInstance
            (this.array.getClass().getComponentType(), length1);
			System.arraycopy(array, start1, temp, 0, length1);

			int insertIdx = start1;
			int runBIdx = start2;
			int tempIdx = 0;

			int leftRemain = length1;
			int rightRemain = length2; 


			while (leftRemain != 0 && rightRemain != 0) {

				if (c.compare(array[runBIdx], temp[tempIdx]) < 0) {
					array[insertIdx++] = array[runBIdx++];
					rightRemain--;
				} else {
					array[insertIdx++] = temp[tempIdx++];
					leftRemain--;
				}
			}

			// 왼쪽 부분 리스트가 남아있을 경우
			if (leftRemain != 0) {
				System.arraycopy(temp, tempIdx, array, insertIdx, leftRemain);
			} 
			else {	// 오른 쪽 부분 리스트가 남아있을 경우
				System.arraycopy(array, runBIdx, array, insertIdx, rightRemain);
			}

		}
	    
		
		/**
		 * 상대적으로 높은 인덱스에 위치한 run을 기준으로 복사하여
		 * 실제 배열 원소를 병합하는 메소드 
		 * 
		 * @param start1 RUN A에서의 병합 시작 지점
		 * @param length1 RUN A에서의 병합해야 할 길이(개수)
		 * @param start2 RUN B에서의 병합 시작 지점
		 * @param length2 RUN B에서의 병합해야 할 길이(개수)
		 */
		@SuppressWarnings({ "unchecked" })
		private void mergeHi(int start1, int length1, int start2, int length2) {
			T[] temp =(T[])java.lang.reflect.Array.newInstance
		            (this.array.getClass().getComponentType(), length2);
			System.arraycopy(array, start2, temp, 0, length2);

			int insertIdx = start2 + length2 - 1; 
			int runAIdx = start1 + length1 - 1;
			int tempIdx = length2 - 1;

			int leftRemain = length1;
			int rightRemain = length2;

			while (leftRemain != 0 && rightRemain != 0) {

				if (c.compare(array[runAIdx], temp[tempIdx]) > 0) {
					array[insertIdx--] = array[runAIdx--];
					leftRemain--;
				} else {
					array[insertIdx--] = temp[tempIdx--];
					rightRemain--;
				}
			}

			if (rightRemain != 0) {
				System.arraycopy(temp, 0, array, start1, rightRemain);
			} else {
				System.arraycopy(array, start1, array, start1, leftRemain);
			}

		}
	    	
	    	
	}
	
	
	
	
	
	public static <T> void sort(T[] a, Comparator<? super T> c) {
		if(c == null) {
			sort(a, 0, a.length);
		} else {
			sort(a, 0, a.length, c);
		}
	}
	
	public static <T> void sort(T[] a, int lo, int hi, Comparator<? super T> c) {
		
		int remain = hi - lo;
		if(remain < 2) {
			return;
		}

		if(remain < THRESHOLD) {
			int increaseRange = getAscending(a, lo, hi, c);
			BinarySort(a, lo, hi, lo + increaseRange, c);
			return;
		}
		
		
		ComparatorObjectMergeStack<T> cms = new ComparatorObjectMergeStack<T>(a, c);
		int minRun = minRunLength(remain);	// run의 최소 길이
		do {

			int runLen = getAscending(a, lo, hi, c);
			

			if(runLen < minRun) {

				int counts = remain < minRun ? remain : minRun;
				

				BinarySort(a, lo, lo + counts, lo + runLen, c);
				
				runLen = counts;
			}
			cms.pushRun(lo, runLen);
			cms.merge();
			
			lo += runLen;
			remain -= runLen;
		} while(remain != 0);	
		cms.mergeForce();

	}
	

	
	private static <T> void BinarySort(T[] a, int lo, int hi ,int start, Comparator<? super T> c) {

		if(lo == start) {
			start++;
		}

		for (; start < hi; start++) {
			T target = a[start];

			int loc = binarySearch(a, target, lo, start, c);

			int j = start - 1;

			while (j >= loc) {
				a[j + 1] = a[j];
				j--;
			}

			a[loc] = target;
		}

	}
	
	private static <T> int binarySearch(T[] a, T key, int lo, int hi, Comparator<? super T> c) {

		int mid;
		while (lo < hi) {
			mid = (lo + hi) >>> 1;
			if (c.compare(key, a[mid]) < 0) {
				hi = mid;
			} 
			else {
				lo = mid + 1;
			}
		}
		return lo;
	}
	
	private static <T> int getAscending(T[] a, int lo, int hi, Comparator<? super T> c) {	

		int limit = lo + 1;
		if (limit == hi) { 
			return 1;
		}

		if (c.compare(a[lo], a[limit]) <= 0) {
			while (limit < hi && c.compare(a[limit - 1], a[limit]) <= 0) {
				limit++;
			}
		}
		else {
			while (limit < hi && c.compare(a[limit - 1], a[limit]) > 0) {
				limit++;
			}
			reversing(a, lo, limit);
		}

		return limit - lo;
	}
	
	
	
	
	//============= Reverse Ordering ============//
	public static void sort(byte[] a, boolean isReverse) {
		
		// reverse order
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
		
		// reverse order
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
		
		// reverse order
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
		
		// reverse order
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
		
		// reverse order
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
		
		// reverse order
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
		
		// reverse order
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
