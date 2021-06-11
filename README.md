Sorting Algorithm Project (Java)
==========
<br/>

## Contents

- [Feature](#feature)
- [Directory structure](#directory-structure)
- [Build and Usage](#build-and-usage)
- [Reverse ordering](#reverse-ordering)
<br/><br/><br/>

## Feature  



- 기본 정렬은 오름차순으로 정렬 됩니다. </br>
	Sorts the specified array into ascending order (natural order).</br></br>

- 대부분의 정렬 알고리즘은 객체 정렬을 지원합니다. </br>
	Most sorting algorithms supports sorting objects. </br></br>

- 내림차순 정렬을 지원합니다. </br>
	Supports sorting Descending order (reverse order). </br>
	*See : [Reverse ordering](#reverse-ordering)* </br></br>


- 일부 정렬 알고리즘들은 스레드를 이용한 병렬 정렬을 지원합니다. ([지원하는 정렬 알고리즘](https://github.com/kdgyun/Sorting_Algorithm/blob/master/SortingAlgorithm/Java/README.md#parallelsort)) </br>
	Some sorting algorithms support parallel sorting using threads. ([Supported Sorting Algoriths](https://github.com/kdgyun/Sorting_Algorithm/blob/master/SortingAlgorithm/Java/README.md#parallelsort)) </br></br>

	
- 사용자 객체(클래스)를 정렬하는 경우 Comparator 또는 Comparable에 의한 비교방식을 반드시 구현해주어야 합니다. </br>
	if you want to sort specified array of objects, All elements in the array must implement the Comparable or Comparator interface.</br></br>


- 만약 퀵 정렬을 사용하고자 하는 경우 참고해주시기 바랍니다. [Quick Sort info](https://github.com/kdgyun/Sorting_Algorithm/blob/master/SortingAlgorithm/Java/README.md#quicksort) </br>
	if you use quick sort, Please note that [Quick Sort info](https://github.com/kdgyun/Sorting_Algorithm/blob/master/SortingAlgorithm/Java/README.md#quicksort) </br></br>


</br></br>

## Directory structure
</br>
This repository is organized as follows: 

</br> 

```
/Sorting_Algorithm
	/SortingAlgorithm
		/Java
			/BubbleSort
			/HeapSort
			/ ...
```

</br></br>
#### [Preview subFolder]
> - SelectionSort : Selection sort(선택 정렬)를 구현한 소스코드가 있습니다.
> - InsertionSort : Insertion sort(삽입 정렬)를 구현한 소스코드가 있습니다.
> - BubbleSort : Bubble sort(거품 정렬)을 구현한 소스코드가 있습니다.
> - ShellSort : Shell sort(셸 정렬)을 구현한 소스코드가 있습니다.
> - HeapSort : Heap sort(힙 정렬)을 구현한 소스코드가 있습니다.
> - MergeSort : Merge sort(합병/병합 정렬)을 구현한 소스코드가 있습니다.
> - QuickSort : Quick sort(퀵 정렬)을 구현한 소스코드가 있습니다.
> - ParallelSort : 스레드를 이용한 병렬 정렬을 구현하는 소스코드가 있습니다.
> - Utils : reverse ordering에 필요한 유틸 클래스입니다.





<br/><br/>



## Build and Usage




<br/>
<br/>

#### How to Use (download ZIP) on Eclipse

------


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

**using sorting method (All sorting methods are static methods).**

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




## Reverse ordering

</br></br>
**If you want to sort an array of primitive types in reverse order, use it as in the following example.**

```java
//ex.

import BubbleSort.BubbleSort;

class YourClass {
	public static void main {
		int[] a = {1, 5, 2, 4};
		// true : reverse order,  false : natural order
		BubbleSort.sort(a, true);	
	}
}
```

</br></br></br>

## Object ordering

</br></br>

**If you want to sort an array of Wrapper or class object types in natural order, use it as in the following example.**


```java
//ex. Wrapper type

import BubbleSort.BubbleSort;

class Main {
	public static void main {
		Integer[] a = {1, 5, 2, 4};
		BubbleSort.sort(a);
	}
}
```
<br/><br/>


```java
//ex. class Object type
//class must implement the Comparable or Comparator interface.

import BubbleSort.BubbleSort;

class Custom {
	...
}

class Main {
	public static void main {

		Custom[] a = new Custom[size];
		BubbleSort.sort(a);		// using Comparable
		BubbleSort.sort(a, comp);	// using Comparator
	}
	
	static Comparator<Custom> comp = new Comparator<Custom>() { ... };
}
```
<br/>

</br></br>
**If you want to sort an array of Wrapper or class object types in reverse order, use it as in the following example.**


```java
//ex.

import BubbleSort.BubbleSort;
import Utils.Order;

class Main {
	public static void main {
		Integer[] a = {1, 5, 2, 4};
		BubbleSort.sort(a, Order.reverseOrder());
		// or Collections.reverseOrder() (in java.util package)
	}
}
```
<br/>



`Note : reverseOrder() method of Comparator Interface in Utils.Order returns a comparator that imposes the reverse ordering of this comparator.`

</br></br></br>

-----------------

<br/>



#### 구현 대한 내용은 블로그에 기재하고 있습니다.  
<br/>

- [Stranger's LAB](https://st-lab.tistory.com/category/알고리즘/Java)
<br/>
&nbsp;&nbsp;&nbsp; 부분적으로 추가 구현 또는 차이가 있을 수 있습니다.

<br/><br/>


