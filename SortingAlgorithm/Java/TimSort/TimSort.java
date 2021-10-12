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
		private final int[] startPointOfRun;
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
	    	startPointOfRun = new int[stackLen];
	    	runLen = new int[stackLen];
	    }
	    
	    public void pushRun(int startPoint, int runLen) {
	    	this.startPointOfRun[stackSize] = startPoint;
	    	this.runLen[stackSize] = runLen;
	    	stackSize++;
	    }
	    
	    public void merge() {
	    	while(stackSize > 1) {
	    		
	    		/*
	    		 * stack의 상위 3개 원소를 비교하며
	    		 * 병합하기 위한 상위 3개 원소의 중간 피벗
	    		 *  
	    		 */
	    		int pivot = stackSize - 2;
	    		
	    		/*
	    		 *	1. runLen[i - 3] > runLen[i - 2] + runLen[i - 1]
	    		 *	2. runLen[i - 2] > runLen[i - 1]
	    		 *
	    		 *	runLen[n-1] = A, runLen[n] = B, runLen[n+1] = C를 상위 세 요소로 합니다.
				 *	운영상 루프는 다음과 같은 경우에 기초한다.
				 *	1. A <= B + C 및 A < C일 경우 A 과 B 병합. 즉, n-1 과 n 병합
				 *	2. A <= B + C 및 A >= C일 경우, B와 C 병합. 즉, n 과 n+1 병합 
				 *	3. A > B + C 및 B <= C일 경우,  B와 C 병합. 즉, n 과 n+1 병합 
				 *	4. A > B + C 및 B > C일 경우 루프 종료
	    		 */
	    		
	    		// stack 요소가 3개 이상일 경우에만 3개를 비교할 수 있음
	    		if(stackSize > 2) {
	    			// A <= B + C
	    			if(runLen[pivot - 1] <= runLen[pivot] + runLen[pivot + 1]) {
	    				// A < C
	    				if(runLen[pivot - 1] < runLen[pivot + 1]) {
	    					// A 와 B 병합 (pivot-1 and pivot merged)
	    					//merge(n - 1)
	    				}
	    				else {	// A >= C
	    					//merge(n)
	    				}
	    			}
	    			// A > B + C && B <= C
	    			else if(runLen[pivot] <= runLen[pivot + 1]) {
	    				//merge(n)
	    			}
	    			else {
	    				break;
	    			}
	    		}
	    		// 유일하게 두 개만 남은 경우
	    		else if(stackSize > 1) {
	    			// merge(n)
	    		}
	    	}
	    }
	    
	    /**
	     * run[idx] 와 run[idx + 1]이 병합 됨
	     * @param idx 병합되는 두 서브리스트(run) 중 낮은 인덱스
	     */
	    private void merge(int idx) {
	    	
	    	int start1 = startPointOfRun[idx];
	    	int length1 = runLen[idx];
	    	int start2 = startPointOfRun[idx + 1];
	    	int length2 = runLen[idx + 1];
	    }
	    
	    
	    /**
	     * gallop_right() 함수를 수행하여 RUN B 의 첫번째 원소보다 
	     * 큰 원소들이 첫번째 출현하는 위치를 RUN A 에서 찾는다.
	     * 
	     * 
	     * @param key run B의 startPoint key
	     * @param array	배열
	     * @param startPoint run A의 startPoint
	     * @param len run A 의 길이
	     * @param hint 
	     * @return
	     */
	    private int gallopRight(int key, int[] array, int startPoint, int len, int hint) {
	    	
	    	/*
	    	 * lastOffset과 다음 오프셋인 offset 사이를 구하고 이 구간에 대해
	    	 * 이분탐색을 통해 최종 오프셋을 반환한다. 
	    	 */
	    	int lo = 0;
	    	int hi = 1;
	    	
	    	/*
	    	 * RUN A의 시작지점 값이 RUN B의 시작지점보다 클 경우
	    	 */
	    	if(key < array[startPoint + hint]) {
	    		
	    		int maxOffset = hint + 1;	// 최대 오프셋은 RUN A의 길이다.
	    		
	    		// Gallop left until a[b + hint - hi] <= key < array[b + hint - lo]
	    		while(hi < maxOffset && key < array[startPoint + hint - hi]) {
	    			lo = hi;
	    			hi = (hi << 1) + 1;	// 2배씩 건너뜀 탐색
	    			
	    			// overflow가 발생시 maxOffset으로 만들어 while문의 break를 건다.
	    			if(hi <= 0) {
	    				hi = maxOffset;
	    				break;
	    			}
	    		}
	    		
	    		// 최대로 가질 수 있는 오프셋을 벗어났을 경우 최대 값으로 초기화
	    		if(hi > maxOffset) {
	    			hi = maxOffset;
	    		}
	    		
	    		int temp = lo;
	    		lo = hint - hi;
	    		hi = hint - temp;
	    	}
	    	
	    	else {
	    		// Gallop right until a[b + hint + lo] <= key < a[b + hint + hi]
	    		int maxOffset = len - hint;	// 최대로 가질 수 있는 offset
	    		
	    		while(hi < maxOffset && array[startPoint + hint + hi] <= key) {
	    			lo = hi;
	    			hi = (hi << 1) + 1;
	    			
	    			if(hi <= 0) {	// overflow
	    				hi = maxOffset;
	    				break;
	    			}
	    		}
	    		
	    		if(hi > maxOffset) {
	    			hi = maxOffset;
	    		}
	    		
	    		lo += hint;
	    		hi += hint;
	    	}
	    	
	    	lo++;
	    	
	    	// binary search
	    	while(lo < hi) {
	    		
	    		/*
	    		 * 중간 값을 구할 때 (lo + hi) / 2 를 하면
	    		 * (lo + hi) 여기서 int overflow가 발생할 수 있다.
	    		 * 
	    		 * 그러므로 hi - lo의 차이값에 2를 나눈 뒤,
	    		 * lo을 더하는 방식으로 해준다.
	    		 * 
	    		 * ex) lo = 3, hi = 7
	    		 * 3 + ((7 - 3) / 2)
	    		 * = 3 + (4 / 2)
	    		 * = 3 + 2
	    		 * = 5 
	    		 * ( == ((3 + 7) / 2 = 5) )
	    		 */
	    		int mid = lo + ((hi - lo) >>> 1);
	    		
	    		if(key < array[startPoint + mid]) {
	    			hi = mid;
	    		}
	    		else {
	    			lo = mid + 1;
	    		}
	    	}
	    	return hi;
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
			 * -> (lo + incLength) 이 이진삽입정렬 수행 시작점이 됨
			 */
			if(incLength < minRun) {
				
				// 최소 run 크기가 남은 원소 개수보다 작을 수 있으므로 이를 처리해준다.
				int endPoint = remain < minRun ? remain : minRun;
				
				/*
				 * BinarySort(array, lo, hi, start);
				 * index[lo] <= index < index[endPoint] 구간을 삽입 정렬을 하되,
				 * index[lo + incLength] 부터 삽입정렬을 시작함.
				 * (이전 인덱스는 이미 오름차순 상태임) 
				 */
				BinarySort(a, lo, lo + endPoint, lo + incLength);
				
				// 이진 삽입 정렬이 수행되었기에 증가하는 길이는 endPoint가 된다.
				incLength = endPoint;
			}
			
			// stack에 run의 시작점과 해당 run의 길이를 스택에 push한다. 
			im.pushRun(lo, incLength);
			
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
	 *  즉, 나오는 수는 THRESHOLD <= runSize <= THRESHOLD 사잇값이 됨.
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
