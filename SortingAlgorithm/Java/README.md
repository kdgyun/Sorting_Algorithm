Project
-----------
<br/>

#### Java  




> - Java로 구현 한 정렬 알고리즘 폴더들이 있습니다.
> - Java11 API : [Java API](https://docs.oracle.com/en/java/javase/11/docs/api/index.html)
> - 특별한 경우를 제외하고는 사용자 객체의 정렬까지 지원합니다.
> - 사용자 객체(클래스)를 정렬하는 경우 Comparator 또는 Comparable에 의해 비교방식을 구현해주어야 하며, 형식은 표준 Java 작성법과 동일합니다.

</br></br>
#### [Preview subFolder]
> - SelectionSort : Selection sort(선택 정렬)를 구현한 소스코드가 있습니다.
> - InsertionSort : Insertion sort(삽입 정렬)를 구현한 소스코드가 있습니다.
> - BubbleSort : Bubble sort(거품 정렬)을 구현한 소스코드가 있습니다.
> - ShellSort : Shell sort(셸 정렬)을 구현한 소스코드가 있습니다.
> - HeapSort : Heap sort(힙 정렬)을 구현한 소스코드가 있습니다.
> - MergeSort : Merge sort(합병/병합 정렬)을 구현한 소스코드가 있습니다.
> - QuickSort : Quick sort(퀵 정렬)을 구현한 소스코드가 있습니다.



<br/><br/>
&nbsp;&nbsp;&nbsp; 블로그에서 포스팅과 동시에 지속적으로 업데이트 예정 


<br/><br/>
<br/><br/>

-----------------


#### How to Use (download ZIP) on Eclipse
<br/>

- **1. Project import** <br /> <br /> Window -> File -> New -> Java Project -> uncheck the **"Use default location"** and Browse the **SortingAlgorithm** folder
-> Finish

<br /><br />

- **2. Build path** <br /> <br /> Your Project -> Build Path -> Configure Build Path -> Project -> select the class path -> add -> Select **SortingAlgorithm** -> Apply and Close

<br /><br />

- **3. import class**

```
import [sorting algorithm package name].[sorting algorithm name];
```

</br></br></br>

**All sorting methods are static methods.**

```java
//ex.

import BubbleSort.BubbleSort;

class YourClass {
	public static void main {
		int[] a = {1, 5, 2, 4};
		BubbleSort.sort(a);
	}
}
```

</br></br></br>

**If you want to sort an array of primitive types in reverse order, use it as in the following example.**

```java
//ex.

import BubbleSort.BubbleSort;

class YourClass {
	public static void main {
		int[] a = {1, 5, 2, 4};
		// true : reverse order,  false : nature order
		BubbleSort.sort(a, true);	
	}
}
```

</br></br></br>


**If you want to sort an array of Wrapper types in reverse order, use it as in the following example.**


```java
//ex.

import BubbleSort.BubbleSort;
import Utils.Order;

class YourClass {
	public static void main {
		Integer[] a = {1, 5, 2, 4};
		BubbleSort.sort(a, Order.reverseOrder());
		// or Collections.reverseOrder() (in java.util package)
	}
}
```
<br/>

```
Note : reverseOrder() method is in Utils.Order.
```

</br></br></br>
<br/>

```
Note

class QuickSort -> standard QuickSort (with middle element selected as pivot)
class LPQuickSort -> left-pivot QuickSort
class RPQuickSort -> right-pivot QuickSort
```

</br></br></br>


<br/><br/>

-----------------

#### 구현 대한 내용은 블로그에 기재하고 있습니다.  
<br/>

- [Stranger's LAB](https://st-lab.tistory.com/category/알고리즘/Java)
<br/>
&nbsp;&nbsp;&nbsp; 부분적으로 추가 구현 또는 차이가 있을 수 있습니다.

<br/><br/>


