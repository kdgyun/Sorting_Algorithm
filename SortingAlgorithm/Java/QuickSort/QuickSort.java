package QuickSort;

public class QuickSort {
	
	public static void sort(int[] a) {
		l_pivot_sort(a, 0, a.length - 1);
	}
	
	/**
	 *  왼쪽 피벗 선택 방식
	 * @param a		정렬할 배열
	 * @param lo	현재 부분배열의 왼쪽
	 * @param hi	현재 부분배열의 오른쪽
	 */
	private static void l_pivot_sort(int[] a, int lo, int hi) {
		
		/*
		 *  lo가 hi보다 크거나 같다면 정렬 할 원소가 
		 *  1개 이하이므로 정렬하지 않고 return한다.
		 */
		if(lo >= hi) {
			return;
		}
		
		/*
		 * 피벗을 기준으로 요소들이 왼쪽과 오른쪽으로 약하게 정렬 된 상태로
		 * 만들어 준 뒤, 최종적으로 pivot의 위치를 얻는다.
		 * 
		 * 그리고나서 해당 피벗을 기준으로 왼쪽 부분리스트와 오른쪽 부분리스트로 나누어
		 * 분할 정복을 해준다.
		 * 
		 * [과정]
		 * 
		 * Before Partition:
		 *
		 *   a[left]          left part              right part
		 * +---------------------------------------------------------+
		 * |  pivot  |    element <= pivot    |    element > pivot   |
		 * +---------------------------------------------------------+
		 *    
		 *    
		 *  After Partition:
		 *  
		 *         left part          a[lo]          right part
		 * +---------------------------------------------------------+
		 * |   element <= pivot    |  pivot  |    element > pivot    |
		 * +---------------------------------------------------------+
		 *       
		 *       
		 *  result : pivot = lo     
		 *       
		 *
		 *  Recursion:
		 *  
		 * l_pivot_sort(a, lo, pivot - 1)     l_pivot_sort(a, pivot + 1, hi)
		 *  
		 *         left part                           right part
		 * +-----------------------+             +-----------------------+
		 * |   element <= pivot    |    pivot    |    element > pivot    |
		 * +-----------------------+             +-----------------------+
		 * lo                pivot - 1        pivot + 1                 hi
		 * 
		 */
		int pivot = partition(a, lo, hi);	
		
		l_pivot_sort(a, lo, pivot - 1);
		l_pivot_sort(a, pivot + 1, hi);
	}
	
	
	
	/**
	 * pivot을 기준으로 파티션을 나누기 위한 약한 정렬 메소드
	 * 
	 * @param a		정렬 할 배열 
	 * @param left	현재 배열의 가장 왼쪽 부분
	 * @param right	현재 배열의 가장 오른쪽 부분
	 * @return		최종적으로 위치한 피벗의 위치(lo)를 반환
	 */
	private static int partition(int[] a, int left, int right) {
		
		int lo = left;
		int hi = right;
		int pivot = a[left];		// 부분리스트의 왼쪽 요소를 피벗으로 설정
		
		// lo가 hi보다 작을 때 까지만 반복한다.
		while(lo < hi) {
			
			/*
			 * hi가 lo보다 크면서, hi의 요소가 pivot보다 작거나 같은 원소를
			 * 찾을 떄 까지 hi를 감소시킨다.
			 */
			while(a[hi] > pivot && lo < hi) {
				hi--;
			}
			
			/*
			 * hi가 lo보다 크면서, lo의 요소가 pivot보다 큰 원소를
			 * 찾을 떄 까지 lo를 증가시킨다.
			 */
			while(a[lo] <= pivot && lo < hi) {
				lo++;
			}
			
			// 교환 될 두 요소를 찾았으면 두 요소를 바꾼다.
			swap(a, lo, hi);
		}
		
		
		/*
		 *  마지막으로 맨 처음 pivot으로 설정했던 위치(a[left])의 원소와 
		 *  lo가 가리키는 원소를 바꾼다.
		 */
		swap(a, left, lo);
		
		// 두 요소가 교환되었다면 피벗이었던 요소는 lo에 위치하므로 lo를 반환한다.
		return lo;
	}

	
	
	
	public static void sort2(int[] a) {
		r_pivot_sort(a, 0, a.length - 1);
	}
	
	/**
	 *  오른쪽 피벗 선택 방식
	 * @param a		정렬할 배열
	 * @param lo	현재 부분배열의 왼쪽
	 * @param hi	현재 부분배열의 오른쪽
	 */
	private static void r_pivot_sort(int[] a, int lo, int hi) {
		
		/*
		 *  lo가 hi보다 크거나 같다면 정렬 할 원소가 
		 *  1개 이하이므로 정렬하지 않고 return한다.
		 */
		if(lo >= hi) {
			return;
		}
		
		/*
		 * 피벗을 기준으로 요소들이 왼쪽과 오른쪽으로 약하게 정렬 된 상태로
		 * 만들어 준 뒤, 최종적으로 pivot의 위치를 얻는다.
		 * 
		 * 그리고나서 해당 피벗을 기준으로 왼쪽 부분리스트와 오른쪽 부분리스트로 나누어
		 * 분할 정복을 해준다.
		 * 
		 * [과정]
		 * 
		 * Before Partitioning:
		 *
		 *         left part                right part       a[right]   
		 * +---------------------------------------------------------+
		 * |    element < pivot    |    element >= pivot   |  pivot  |
		 * +---------------------------------------------------------+
		 *    
		 *    
		 *  result After Partitioning:
		 *  
		 *         left part         a[hi]          right part
		 * +---------------------------------------------------------+
		 * |   element < pivot    |  pivot  |    element >= pivot    |
		 * +---------------------------------------------------------+
		 *       
		 *       
		 *  result : pivot = hi     
		 *       
		 *
		 *  Recursion:
		 *  
		 * r_pivot_sort(a, lo, pivot - 1)     r_pivot_sort(a, pivot + 1, hi)
		 *  
		 *         left part                           right part
		 * +-----------------------+             +-----------------------+
		 * |   element <= pivot    |    pivot    |    element > pivot    |
		 * +-----------------------+             +-----------------------+
		 * lo                pivot - 1        pivot + 1                 hi
		 * 
		 */
		int pivot = rpartition(a, lo, hi);	
		
		r_pivot_sort(a, lo, pivot - 1);
		r_pivot_sort(a, pivot + 1, hi);
	}
	
	
	
	/**
	 * pivot을 기준으로 파티션을 나누기 위한 약한 정렬 메소드
	 * 
	 * @param a		정렬 할 배열 
	 * @param left	현재 배열의 가장 왼쪽 부분
	 * @param right	현재 배열의 가장 오른쪽 부분
	 * @return		최종적으로 위치한 피벗의 위치(lo)를 반환
	 */
	private static int rpartition(int[] a, int left, int right) {
		
		int lo = left;
		int hi = right;
		int pivot = a[right];		// 부분리스트의 오른쪽 요소를 피벗으로 설정
		
		// lo가 hi보다 작을 때 까지만 반복한다.
		while(lo < hi) {
			
			/*
			 * hi가 lo보다 크면서, lo의 요소가 pivot보다 큰 원소를
			 * 찾을 떄 까지 lo를 증가시킨다.
			 */
			while(a[lo] < pivot && lo < hi) {
				lo++;
			}
			
			/*
			 * hi가 lo보다 크면서, hi의 요소가 pivot보다 작거나 같은 원소를
			 * 찾을 떄 까지 hi를 감소시킨다.
			 */
			while(a[hi] >= pivot && lo < hi) {
				hi--;
			}
			
			
			// 교환 될 두 요소를 찾았으면 두 요소를 바꾼다.
			swap(a, lo, hi);
		}
		
		
		/*
		 *  마지막으로 맨 처음 pivot으로 설정했던 위치(a[right])의 원소와 
		 *  hi가 가리키는 원소를 바꾼다.
		 */
		swap(a, right, hi);
		
		// 두 요소가 교환되었다면 피벗이었던 요소는 hi에 위치하므로 hi를 반환한다.
		return hi;
	}
	
	
	
	
	
	public static void sort3(int[] a) {
		m_pivot_sort(a, 0, a.length - 1);
	}
	
	/**
	 *  중간 피벗 선택 방식
	 * @param a		정렬할 배열
	 * @param lo	현재 부분배열의 왼쪽
	 * @param hi	현재 부분배열의 오른쪽
	 */
	private static void m_pivot_sort(int[] a, int lo, int hi) {
		
		/*
		 *  lo가 hi보다 크거나 같다면 정렬 할 원소가 
		 *  1개 이하이므로 정렬하지 않고 return한다.
		 */
		if(lo >= hi) {
			return;
		}
		
		/*
		 * 피벗을 기준으로 요소들이 왼쪽과 오른쪽으로 약하게 정렬 된 상태로
		 * 만들어 준 뒤, 최종적으로 pivot의 위치를 얻는다.
		 * 
		 * 그리고나서 해당 피벗을 기준으로 왼쪽 부분리스트와 오른쪽 부분리스트로 나누어
		 * 분할 정복을 해준다.
		 * 
		 * [과정]
		 * 
		 * Partitioning:
		 *
		 *      left part      a[(right + left)/2]      right part      
		 * +---------------------------------------------------------+
		 * |    element < pivot    |  pivot  |    element >= pivot   |
		 * +---------------------------------------------------------+
		 *    
		 *    
		 *  result After Partitioning:
		 *  
		 *         left part         a[hi]          right part
		 * +---------------------------------------------------------+
		 * |   element < pivot    |  pivot  |    element >= pivot    |
		 * +---------------------------------------------------------+
		 *       
		 *       
		 *  result : pivot = hi     
		 *       
		 *
		 *  Recursion:
		 *  
		 * m_pivot_sort(a, lo, pivot)         m_pivot_sort(a, pivot + 1, hi)
		 *  
		 *         left part                           right part
		 * +-----------------------+             +-----------------------+
		 * |   element <= pivot    |             |    element > pivot    |
		 * +-----------------------+             +-----------------------+
		 * lo                pivot          pivot + 1                   hi
		 * 
		 */
		int pivot = mpartition(a, lo, hi);	
		
		m_pivot_sort(a, lo, pivot);
		m_pivot_sort(a, pivot + 1, hi);
	}
	
	
	
	/**
	 * pivot을 기준으로 파티션을 나누기 위한 약한 정렬 메소드
	 * 
	 * @param a		정렬 할 배열 
	 * @param left	현재 배열의 가장 왼쪽 부분
	 * @param right	현재 배열의 가장 오른쪽 부분
	 * @return		최종적으로 위치한 피벗의 위치(lo)를 반환
	 */
	private static int mpartition(int[] a, int left, int right) {
		
		// lo와 hi는 각각 배열의 끝에서 1 벗어난 위치부터 시작한다.
		int lo = left - 1;
		int hi = right + 1;
		int pivot = a[(left + right) / 2];		// 부분리스트의 중간 요소를 피벗으로 설정
		

		while(true) {
			
			/*
			 * 1 증가시키고 난 뒤의 lo 위치의 요소가 pivot보다 큰 요소를
			 * 찾을 떄 까지 반복한다.
			 */
			do { 
				lo++; 
			} while(a[lo] < pivot);

			
			/*
			 * 1 감소시키고 난 뒤의 hi 위치가 lo보다 크거나 같은 위치이면서
			 * hi위치의 요소가 pivot보다 작은 요소를 찾을 떄 까지 반복한다.
			 */
			do {
				hi--;
			} while(a[hi] > pivot && lo <= hi);
			
			
			/*
			 * 만약 hi가 lo보다 크지 않다면(엇갈린다면) swap하지 않고 hi를 리턴한다.
			 */
			if(lo >= hi) {
				return hi;
			}
			
			
			// 교환 될 두 요소를 찾았으면 두 요소를 바꾼다.
			swap(a, lo, hi);
		}
		
	}
	
	
	
	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
}
