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
		private int[] startPointOfRun;
	    private int[] runLen;
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
    	
//	    	 int stackLen = (len <    120  ?  5 :
//                 len <   1542  ? 10 :
//                 len < 119151  ? 24 : 49);
	    	startPointOfRun = new int[stackLen];
	    	runLen = new int[stackLen];
	    }
	    
	    public void pushRun(int runBase, int runLen) {
	        this.startPointOfRun[stackSize] = runBase;
	        this.runLen[stackSize] = runLen;
	        stackSize++;
	    }
	    
	    public void mergeForce() {
	    	while(stackSize > 1) {
	    		int pivot = stackSize - 2;
	    		if(pivot > 0 && runLen[pivot - 1] < runLen[pivot + 1]) {
	    			pivot--;
	    		}
	    		//System.out.println("pivot : " + pivot);
	    		merge(pivot);
	    	}
	    }
	    
	    
	    public void merge() {
	    	
//	    	System.out.println("merge run");
	    	
//	    	while(stackSize > 1) {
//	    		
//	    		/*
//	    		 * stack의 상위 3개 원소를 비교하며
//	    		 * 병합하기 위한 상위 3개 원소의 중간 피벗
//	    		 *  
//	    		 */
//	    		int pivot = stackSize - 2;
//	    		
//	    		/*
//	    		 *	1. runLen[i - 3] > runLen[i - 2] + runLen[i - 1]
//	    		 *	2. runLen[i - 2] > runLen[i - 1]
//	    		 *
//	    		 *	runLen[n-1] = A, runLen[n] = B, runLen[n+1] = C를 상위 세 요소로 합니다.
//				 *	운영상 루프는 다음과 같은 경우에 기초한다.
//				 *	1. A <= B + C 및 A < C일 경우 A 과 B 병합. 즉, n-1 과 n 병합
//				 *	2. A <= B + C 및 A >= C일 경우, B와 C 병합. 즉, n 과 n+1 병합 
//				 *	3. A > B + C 및 B <= C일 경우,  B와 C 병합. 즉, n 과 n+1 병합 
//				 *	4. A > B + C 및 B > C일 경우 루프 종료
//	    		 */
//	    		
//	    		// stack 요소가 3개 이상일 경우에만 3개를 비교할 수 있음
//	    		
//	    		
//	    		if(stackSize > 3) {
//	    			if(runLen[pivot - 1] <= runLen[pivot] + runLen[pivot + 1]) {
//	    				if(runLen[pivot - 1] < runLen[pivot + 1]) {
//	    					// A 와 B 병합 (pivot-1 and pivot merged)
//	    					merge(pivot - 1);
//	    				}
//	    				else {	// A >= C
//	    					merge(pivot);
//	    				}
//	    			}
//	    			else {
//	    				merge(pivot);
//	    			}
//	    		}
//	    		else if(stackSize > 2) {
//	    			// A <= B + C
//	    			if(runLen[pivot - 1] <= runLen[pivot] + runLen[pivot + 1]) {
//	    				// A < C
//	    				if(runLen[pivot - 1] < runLen[pivot + 1]) {
//	    					// A 와 B 병합 (pivot-1 and pivot merged)
//	    					merge(pivot - 1);
//	    				}
//	    				else {	// A >= C
//	    					merge(pivot);
//	    				}
//	    			}
//	    			// A > B + C && B <= C
//	    			else if(runLen[pivot] <= runLen[pivot + 1]) {
//	    				merge(pivot);
//	    			}
//	    			merge(pivot);
//	    		}
//	    		// 유일하게 두 개만 남은 경우
//	    		else if(stackSize > 1) {
//	    			merge(pivot);
//	    		}
//	    		else if(pivot < 0 || runLen[pivot] > runLen[pivot + 1]){
//	    			break;
//	    		}
//	    		else {
//	    			merge(pivot);
//	    		}
//	    	}
	    	
	    	// ref. OpenJDK’s java.utils.Collection.sort() is broken: The good, the bad and the worst case⋆
	    	while (stackSize > 1) {
	        	int pivot = stackSize - 2;

	        	if (pivot > 0 && runLen[pivot - 1] <= runLen[pivot] + runLen[pivot + 1] ||
	        			pivot > 1 && runLen[pivot - 2] <= runLen[pivot] + runLen[pivot - 1]) {
	        		if (runLen[pivot - 1] < runLen[pivot + 1])
	        			pivot--;
	        	} else if (pivot < 0 || runLen[pivot] > runLen[pivot + 1]) {
	        		break; // Invariant is established
	        	}

	        	merge(pivot);
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

	    	// idx 와 idx + 1 번째 run을 병합
	    	runLen[idx] = length1 + length2;
	    	
	    	/*
	    	 *  상위 3개 (A, B, C)에서 A를 기준으로 병합 할 경우
	    	 *  앞서 A, B가 병합되므로, C를 당겨온다.
	    	 *  
	    	 *  ex)
	    	 *  stack [[A], [B], [C]]
	    	 *  
	    	 *  runLen[idx] = length1 + length2
	    	 *  stack[[A + B], [B], [C]]
	    	 *  
	    	 *  C를 B위치로 당겨온다.
	    	 *  stack[[A + B], [C], [C]]
	    	 *  
	    	 *  이 때 마지막 [C]는 더이상 참조될 일 없음 
	    	 */
	    	
	    	if(idx == (stackSize - 3)) {
	    		startPointOfRun[idx + 1] = startPointOfRun[idx + 2];
	    		runLen[idx + 1] = runLen[idx + 2];
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
	    
	    	// start2(RUN B의 시작점보다 작으면서 RUN A 에서 merge를 시작할 위치)
	    	int lo = gallopRight(array[start2], array, start1, length1, 0);
	    	
	    	/*
	    	 *  만약 RUN A의 길이와 merge를 시작할 지점이 같을 경우 
	    	 *  이미 정렬되어있는 상태로 정렳 할 필요 없음
	    	 */
	    	if(length1 == lo) {
	    		return;
	    	}
	    	start1 += lo;
	    	length1 -= lo;
	    	
	    	int hi = gallopLeft(array[start1 + length1 - 1], array, start2, length2, length2 - 1);

	    	if(hi == 0) {
	    		return;
	    	}
	    	

	    	length2 = hi;
	    	if(length1 <= length2) {
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
	     * @param key run B의 startPoint key
	     * @param array	배열
	     * @param base run A의 startPoint
	     * @param len run A 의 길이
	     * @param hint 
	     * @return
	     */
	    private int gallopRight(int key, int[] array, int base, int len, int hint) {

	    	/*
	    	 * lastOffset과 다음 오프셋인 offset 사이를 구하고 이 구간에 대해
	    	 * 이분탐색을 통해 최종 오프셋을 반환한다. 
	    	 */
	    	int lo = 0;
	    	int hi = 1;
	    	
	    	/*
	    	 * RUN A의 시작지점 값이 RUN B의 시작지점보다 클 경우
	    	 */
	    	if(key < array[base + hint]) {
	    		int maxOffset = hint + 1;	// 최대 오프셋은 RUN A의 길이다.
	    		
	    		// Gallop left until a[b + hint - hi] <= key < array[b + hint - lo]
	    		while(hi < maxOffset && key < array[base + hint - hi]) {
	    			lo = hi;
	    			hi = (hi << 1) + 1;	// 2배씩 건너뜀 탐색
	    			
	    			// overflow가 발생시 maxOffset으로 만들어 while문의 break를 건다.
	    			if(hi <= 0) {
	    				hi = maxOffset;
	    			
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
	    		
	    		while(hi < maxOffset && array[base + hint + hi] <= key) {
	    			lo = hi;
	    			hi = (hi << 1) + 1;
	    			
	    			if(hi <= 0) {	// overflow
	    				hi = maxOffset;
	    				
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
	    		
	    		if(key < array[base + mid]) {
	    			hi = mid;
	    		}
	    		else {
	    			lo = mid + 1;
	    		}
	    	}
	    	return hi;
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
	    private int gallopLeft(int key, int[] array, int startPoint, int len, int hint) {
	    	
	    	/*
	    	 * lastOffset과 다음 오프셋인 offset 사이를 구하고 이 구간에 대해
	    	 * 이분탐색을 통해 최종 오프셋을 반환한다. 
	    	 */
	    	int lastOfs = 0;
	    	int ofs = 1;
	    	
	    	/*
	    	 * RUN A의 시작지점 값이 RUN B의 시작지점보다 클 경우
	    	 */
	    	if(key <= array[startPoint + hint]) {
	    		
	    		int maxOffset = hint + 1;	// 최대 오프셋은 RUN A의 길이다.
	    		
	    		// Gallop left until a[b + hint - hi] <= key < array[b + hint - lo]
	    		while(ofs < maxOffset && key <= array[startPoint + hint - ofs]) {
	    			lastOfs = ofs;
	    			ofs = (ofs << 1) + 1;	// 2배씩 건너뜀 탐색
	    			
	    			// overflow가 발생시 maxOffset으로 만들어 while문의 break를 건다.
	    			if(ofs <= 0) {
	    				ofs = maxOffset;
	    				break;
	    			}
	    		}
	    		
	    		// 최대로 가질 수 있는 오프셋을 벗어났을 경우 최대 값으로 초기화
	    		if(ofs > maxOffset) {
	    			ofs = maxOffset;
	    		}
	    		
	    		int temp = lastOfs;
	    		lastOfs = hint - ofs;
	    		ofs = hint - temp;
	    	}
	    	
	    	else {
	    		// Gallop right until a[b + hint + lo] <= key < a[b + hint + hi]
	    		int maxOffset = len - hint;	// 최대로 가질 수 있는 offset
	    		
	    		while(ofs < maxOffset && array[startPoint + hint + ofs] < key) {
	    			lastOfs = ofs;
	    			ofs = (ofs << 1) + 1;
	    			
	    			if(ofs <= 0) {	// overflow
	    				ofs = maxOffset;
	    				break;
	    			}
	    		}
	    		
	    		if(ofs > maxOffset) {
	    			ofs = maxOffset;
	    		}
	    		
	    		lastOfs += hint;
	    		ofs += hint;
	    	}
	    	lastOfs++;
	    	
	    	// binary search
	    	while(lastOfs < ofs) {
	    		
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
	    		int mid = lastOfs + ((ofs - lastOfs) >>> 1);
	    		
	    		if(key <= array[startPoint + mid]) {
	    			ofs = mid;
	    		}
	    		else {
	    			lastOfs = mid + 1;
	    		}
	    	}
	    	return ofs;
	    }
	    
//	    private static int MIN_GALLOP = 7;
//	    private int minGallop = MIN_GALLOP;
	    
	    // start1, length1, start2, length2
	    private void mergeLo(int start1, int length1, int start2, int length2) {
	    	// RUN A' 를 담을 임시 복사 배열
	    	int[] temp = new int[length1];
	    	System.arraycopy(array, start1, temp, 0, length1);
	    	
	    	int left = start1;
	    	int right = start2;
	    	int tempIdx = 0;
	    	
	    	int leftRemain = length1;
	    	int rightRemain = length2;
	    	
	    	while(leftRemain != 0 && rightRemain != 0) {
	    		
	    		// RUN B' < RUN A' 라면 RUN B' 원소 삽입
	    		if(array[right] < temp[tempIdx]) {
	    			array[left++] = array[right++];
	    			rightRemain--;
	    		}
	    		else {
	    			array[left++] = temp[tempIdx++];
	    			leftRemain--;
	    		}
	    	}
	    	
	    	// 왼쪽 부분 리스트가 남아있을 경우
	    	if(leftRemain != 0) {
	    		System.arraycopy(temp, tempIdx, array, left, leftRemain);
	    	}
	    	else {
	    		System.arraycopy(array, right, array, left, rightRemain);
	    	}
	    	
	    	
	    	
//	    	 // Copy first run into temp array
//	        int[] a = this.array; // For performance
//	        int[] tmp = new int[len1];
//
//	        int cursor1 = 0; // Indexes into tmp array
//	        int cursor2 = base2;   // Indexes int a
//	        int dest = base1;      // Indexes int a
//	        System.arraycopy(a, base1, tmp, cursor1, len1);
//
//	        // Move first element of second run and deal with degenerate cases
//	        a[dest++] = a[cursor2++];
//	        if (--len2 == 0) {
//	            System.arraycopy(tmp, cursor1, a, dest, len1);
//	            return;
//	        }
//	        if (len1 == 1) {
//	            System.arraycopy(a, cursor2, a, dest, len2);
//	            a[dest + len2] = tmp[cursor1]; // Last elt of run 1 to end of merge
//	            return;
//	        }
//
//	        int minGallop = this.minGallop;  // Use local variable for performance
//	    outer:
//	        while (true) {
//	            int count1 = 0; // Number of times in a row that first run won
//	            int count2 = 0; // Number of times in a row that second run won
//
//	            /*
//	             * Do the straightforward thing until (if ever) one run starts
//	             * winning consistently.
//	             */
//	            do {
//	                assert len1 > 1 && len2 > 0;
//	                if ((a[cursor2]) < (tmp[cursor1])) {
//	                    a[dest++] = a[cursor2++];
//	                    count2++;
//	                    count1 = 0;
//	                    if (--len2 == 0)
//	                        break outer;
//	                } else {
//	                    a[dest++] = tmp[cursor1++];
//	                    count1++;
//	                    count2 = 0;
//	                    if (--len1 == 1)
//	                        break outer;
//	                }
//	            } while ((count1 | count2) < minGallop);
//
//	            /*
//	             * One run is winning so consistently that galloping may be a
//	             * huge win. So try that, and continue galloping until (if ever)
//	             * neither run appears to be winning consistently anymore.
//	             */
//	            do {
//	                assert len1 > 1 && len2 > 0;
//	                count1 = gallopRight(a[cursor2], tmp, cursor1, len1, 0);
//	                if (count1 != 0) {
//	                    System.arraycopy(tmp, cursor1, a, dest, count1);
//	                    dest += count1;
//	                    cursor1 += count1;
//	                    len1 -= count1;
//	                    if (len1 <= 1)  // len1 == 1 || len1 == 0
//	                        break outer;
//	                }
//	                a[dest++] = a[cursor2++];
//	                if (--len2 == 0)
//	                    break outer;
//
//	                count2 = gallopLeft(tmp[cursor1], a, cursor2, len2, 0);
//	                if (count2 != 0) {
//	                    System.arraycopy(a, cursor2, a, dest, count2);
//	                    dest += count2;
//	                    cursor2 += count2;
//	                    len2 -= count2;
//	                    if (len2 == 0)
//	                        break outer;
//	                }
//	                a[dest++] = tmp[cursor1++];
//	                if (--len1 == 1)
//	                    break outer;
//	                minGallop--;
//	            } while (count1 >= 7 | count2 >= 7);
//	            if (minGallop < 0)
//	                minGallop = 0;
//	            minGallop += 2;  // Penalize for leaving gallop mode
//	        }  // End of "outer" loop
//	        this.minGallop = minGallop < 1 ? 1 : minGallop;  // Write back to field
//
//	        if (len1 == 1) {
//	            assert len2 > 0;
//	            System.arraycopy(a, cursor2, a, dest, len2);
//	            a[dest + len2] = tmp[cursor1]; //  Last elt of run 1 to end of merge
//	        } else if (len1 == 0) {
//	            throw new IllegalArgumentException(
//	                "Comparison method violates its general contract!");
//	        } else {
//	            assert len2 == 0;
//	            assert len1 > 1;
//	            System.arraycopy(tmp, cursor1, a, dest, len1);
//	        }
	    	
	    }
	    
	    // start1, length1, start2, length2
	    private void mergeHi(int start1, int length1, int start2, int length2) {
	    	// RUN B' 를 담을 임시 복사 배열
	    	int[] temp = new int[length2];
	    	System.arraycopy(array, start2, temp, 0, length2);
	    	
	    	int left = start1 + length1 - 1;
	    	int right = start2 + length2 - 1;
	    	
	    	int tempIdx = length2 - 1;
	    	
	    	int leftRemain = length1;
	    	int rightRemain = length2;
	    	
	    	while(leftRemain != 0 && rightRemain != 0) {
	    		
	    		// RUN A' > RUN B' 라면 RUN A' 원소 삽입 (내림차순이기 때문)
	    		if(array[left] > temp[tempIdx]) {
	    			array[right--] = array[left--];
	    			leftRemain--;
	    		}
	    		else {
	    			array[right--] = temp[tempIdx--];
	    			rightRemain--;
	    		}
	    	}
	    	
	    	// 오른쪽 부분 리스트가 남아있을 경우
	    	if(rightRemain != 0) {
	    		System.arraycopy(temp, 0, array, start1, rightRemain);
	    	}
	    	else {
	    		System.arraycopy(array, start1, array, start1, leftRemain);
	    	}
	    	
	    	
	    	
//	        // Copy second run into temp array
//	        int[] a = this.array; // For performance
//	        int[] tmp = new int[len2];
//	        int tmpBase = 0;
//	        System.arraycopy(a, base2, tmp, tmpBase, len2);
//
//	        int cursor1 = base1 + len1 - 1;  // Indexes into a
//	        int cursor2 = tmpBase + len2 - 1; // Indexes into tmp array
//	        int dest = base2 + len2 - 1;     // Indexes into a
//
//	        // Move last element of first run and deal with degenerate cases
//	        a[dest--] = a[cursor1--];
//	        if (--len1 == 0) {
//	            System.arraycopy(tmp, tmpBase, a, dest - (len2 - 1), len2);
//	            return;
//	        }
//	        if (len2 == 1) {
//	            dest -= len1;
//	            cursor1 -= len1;
//	            System.arraycopy(a, cursor1 + 1, a, dest + 1, len1);
//	            a[dest] = tmp[cursor2];
//	            return;
//	        }
//
//	        int minGallop = this.minGallop;  // Use local variable for performance
//	    outer:
//	        while (true) {
//	            int count1 = 0; // Number of times in a row that first run won
//	            int count2 = 0; // Number of times in a row that second run won
//
//	            /*
//	             * Do the straightforward thing until (if ever) one run
//	             * appears to win consistently.
//	             */
//	            do {
//	                assert len1 > 0 && len2 > 1;
//	                if (tmp[cursor2] <a[cursor1]) {
//	                    a[dest--] = a[cursor1--];
//	                    count1++;
//	                    count2 = 0;
//	                    if (--len1 == 0)
//	                        break outer;
//	                } else {
//	                    a[dest--] = tmp[cursor2--];
//	                    count2++;
//	                    count1 = 0;
//	                    if (--len2 == 1)
//	                        break outer;
//	                }
//	            } while ((count1 | count2) < minGallop);
//
//	            /*
//	             * One run is winning so consistently that galloping may be a
//	             * huge win. So try that, and continue galloping until (if ever)
//	             * neither run appears to be winning consistently anymore.
//	             */
//	            do {
//	                assert len1 > 0 && len2 > 1;
//	                count1 = len1 - gallopRight(tmp[cursor2], a, base1, len1, len1 - 1);
//	                if (count1 != 0) {
//	                    dest -= count1;
//	                    cursor1 -= count1;
//	                    len1 -= count1;
//	                    System.arraycopy(a, cursor1 + 1, a, dest + 1, count1);
//	                    if (len1 == 0)
//	                        break outer;
//	                }
//	                a[dest--] = tmp[cursor2--];
//	                if (--len2 == 1)
//	                    break outer;
//
//	                count2 = len2 - gallopLeft(a[cursor1], tmp, tmpBase, len2, len2 - 1);
//	                if (count2 != 0) {
//	                    dest -= count2;
//	                    cursor2 -= count2;
//	                    len2 -= count2;
//	                    System.arraycopy(tmp, cursor2 + 1, a, dest + 1, count2);
//	                    if (len2 <= 1)
//	                        break outer; // len2 == 1 || len2 == 0
//	                }
//	                a[dest--] = a[cursor1--];
//	                if (--len1 == 0)
//	                    break outer;
//	                minGallop--;
//	            } while (count1 >= MIN_GALLOP | count2 >= MIN_GALLOP);
//	            if (minGallop < 0)
//	                minGallop = 0;
//	            minGallop += 2;  // Penalize for leaving gallop mode
//	        }  // End of "outer" loop
//	        this.minGallop = minGallop < 1 ? 1 : minGallop;  // Write back to field
//
//	        if (len2 == 1) {
//	            assert len1 > 0;
//	            dest -= len1;
//	            cursor1 -= len1;
//	            System.arraycopy(a, cursor1 + 1, a, dest + 1, len1);
//	            a[dest] = tmp[cursor2];  // Move first elt of run2 to front of merge
//	        } else if (len2 == 0) {
//	            throw new IllegalArgumentException(
//	                "Comparison method violates its general contract!");
//	        } else {
//	            assert len1 == 0;
//	            assert len2 > 0;
//	            System.arraycopy(tmp, tmpBase, a, dest - (len2 - 1), len2);
//	        }

	    	
	    }
	    	
	    	
	}
	
	
	
	
	
	public static void sort(int[] a) {
		sort(a, 0, a.length);
	}
	
	public static void sort(int[] a, int lo, int hi) {
		
		int remain = hi - lo;
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
			
			int runLen = getAscending(a, lo, hi);
			
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
			if(runLen < minRun) {
				// 최소 run 크기가 남은 원소 개수보다 작을 수 있으므로 이를 처리해준다.
				int force = remain < minRun ? remain : minRun;
				
				/*
				 * BinarySort(array, lo, hi, start);
				 * index[lo] <= index < index[endPoint] 구간을 삽입 정렬을 하되,
				 * index[lo + incLength] 부터 삽입정렬을 시작함.
				 * (이전 인덱스는 이미 오름차순 상태임) 
				 */
				BinarySort(a, lo, lo + force, lo + runLen);
				
				// 이진 삽입 정렬이 수행되었기에 증가하는 길이는 endPoint가 된다.
				runLen = force;
			}
			// stack에 run의 시작점과 해당 run의 길이를 스택에 push한다. 
			im.pushRun(lo, runLen);
			im.merge();
			
			lo += runLen;
			remain -= runLen;
		} while(remain != 0);
		im.mergeForce();

	}
	
	
	
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
		
//	       if (start == lo)
//	           start++;
//	       for ( ; start < hi; start++) {
//	           int pivot = a[start];
//
//	           int left = lo;
//	           int right = start;
//	           assert left <= right;
//	           /*
//	            * Invariants:
//	            *   pivot >= all in [lo, left).
//	            *   pivot <  all in [right, start).
//	            */
//	           while (left < right) {
//	               int mid = (left + right) >>> 1;
//	               if (pivot < a[mid])
//	                   right = mid;
//	               else
//	                   left = mid + 1;
//	           }
//	           assert left == right;
//
//	           /*
//	            * The invariants still hold: pivot >= all in [lo, left) and
//	            * pivot < all in [left, start), so pivot belongs at left.  Note
//	            * that if there are elements equal to pivot, left points to the
//	            * first slot after them -- that's why this sort is stable.
//	            * Slide elements over to make room for pivot.
//	            */
//	           int n = start - left;  // The number of elements to move
//	           // Switch is just an optimization for arraycopy in default case
//	           switch (n) {
//	               case 2:  a[left + 2] = a[left + 1];
//	               case 1:  a[left + 1] = a[left];
//	                        break;
//	               default: System.arraycopy(a, left, a, left + 1, n);
//	           }
//	           a[left] = pivot;
//	       }
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
