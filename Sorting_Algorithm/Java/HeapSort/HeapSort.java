package HeapSort;

/**
 * 
 * @author kdgyun
 * {@link https://st-lab.tistory.com}
 * {@link https://github.com/kdgyun}
 *
 */


public class HeapSort {

	public static void sort(int[] a) {
		sort(a, a.length);
	}
	

	
	// 부모 인덱스를 얻는 함수
	private static int getParent(int child) {
	    return (child - 1) / 2;
	}

	// 두 인덱스의 원소를 교환하는 함수
	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}
	
	

	
	private static void sort(int[] a, int size) {
		
		// max heap
		int lastIdx = size - 1;
		int parentIdx = getParent(lastIdx);
		
		while(parentIdx >= 0) {
			heapify(a, parentIdx, lastIdx);
			parentIdx--;
		}

	    // sort
	    int end = size - 1;
	    while(end > 0) {
	        swap(a, 0, end);
	        end--;
	        heapify(a, 0, end);
	    }
	}
	
	
	private static void heapify(int[] a, int parentIdx, int lastIdx) {
		
	    int leftChildIdx;
	    int rightChildIdx;
	    int largestIdx;

	    /*
	     * 현재 부모 인덱스의 자식 노드 인덱스가 
	     * 마지막 인덱스를 넘지 않을 때 까지 반복한다.
	     * 
	     * 이 때 왼쪽 자식 노드를 기준으로 해야 한다.
	     * 오른쪽 자식 노드를 기준으로 범위를 검사하게 되면
	     * 마지막 부모 인덱스가 왼쪽 자식만 갖고 있을 경우
	     * 왼쪽 자식노드와는 비교 및 교환을 할 수 없기 때문이다. 
	     */
	    while((parentIdx * 2) + 1 <= lastIdx) {
	        leftChildIdx = (parentIdx * 2) + 1;
	        rightChildIdx = (parentIdx * 2) + 2;
	        largestIdx = parentIdx;

	        /*
	         * left child node와 비교 
	         * (범위는 while문에서 검사했으므로 별도 검사 필요 없음)
	         */
	        if (a[leftChildIdx] > a[largestIdx]) {
	            largestIdx = leftChildIdx;
	        }

	        /*
	         * right child node와 비교 
	         * right child node는 범위를 검사해주어야한다. 
	         */
	        if (rightChildIdx <= lastIdx && a[rightChildIdx] > a[largestIdx]) {
	            largestIdx = rightChildIdx;
	        }

	        /*
	         * 교환이 발생했을 경우 두 원소를 교체 한 후
	         * 교환이 된 자식노드를 부모 노드가 되도록 교체한다. 
	         */
	        if (largestIdx != parentIdx) {
	            swap(a, parentIdx, largestIdx);
	            parentIdx = largestIdx;
	        } 
	        else {
	            return;
	        }
	    }
	}
}
