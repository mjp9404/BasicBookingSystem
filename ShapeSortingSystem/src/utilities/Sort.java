package utilities;

import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.Exchanger;

public class Sort {

	/**
	 * Bubble Sort Method
	 * @param arr Unsorted Array of Comparable
	 * @param comp Height/Volume/BaseArea Comparator
	 * @return Sorted Array of Comparable<T>[]
	 */
	public static<T>  Comparable<T>[] bubbleSort(Comparable<T>[]  arr, Comparator comp) {
		int l = arr.length;
		for(int i = 0; i < l-1; i++) {
			for(int j = 0; j< l-1; j++) {
				if(comp.compare(arr[j], arr[j+1]) < 0) {
					Comparable<T> temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
				}
			}
		}
		return arr;
	}

	/**
	 * Insert Sort Method
	 * @param arr Unsorted Array of Comparable<T>[]
	 * @param comp Height/Volume/BaseArea Comparator
	 * @return Sorted Array of Comparable<T>[]
	 */
	public static <T> Comparable<T>[] insertSort(Comparable<T>[] list, Comparator<? super T> comp) {
        for (int i = 1; i < list.length; i++) {
            /** insert list[i] into a sorted sublist list[0..i-1] so that
             list[0..i] is sorted. */
            Comparable<T> currentElement = list[i];
            int k;
            for (k = i - 1; k >= 0 && comp.compare((T) list[k], (T) currentElement) < 0; k--) {
                list[k + 1] = list[k];
            }

            // Insert the current element into list[k+1]
            list[k + 1] = currentElement;
        }
        return list;
    }
	/**
	 * Selection Sort Method
	 * @param arr Unsorted Array of Comparable<T>[]
	 * @param comp Height/Volume/BaseArea Comparator
	 * @return Sorted Array of Comparable<T>[]
	 */
	public static <T> Comparable<T>[] selectionSort(Comparable<T>[] arr, Comparator comp) {
		
		for (int i = 0; i < arr.length - 1; ++i) {
			int minIndex = i;
			
			for (int j = i + 1; j < arr.length; ++j) {
				if (comp.compare(arr[j], arr[minIndex]) > 0) {
					minIndex = j;
				}
			}
			swap(arr, i, minIndex);
		}
		return arr;
	}

	/**Driver of Merge Sort
	 * Recursive use divides elements. Then runs merge to sort them back together
	 * @param arr Unsorted Array of Comparable<T>[]
	 * @param l Beginning Index of array to be sorted
	 * @param r Length of Array minus one
	 * @param comp Height/Volume/BaseArea Comparator
	 */
	public  static <T> void mergeSort(Comparable<T>[] arr, int l, int r, Comparator comp) {
		if(l<r) {
			int m = (l + r) /2;
			mergeSort(arr, l, m, comp);
			mergeSort(arr, m+1, r, comp);
			merge(arr, l, m, r, comp);
		}
	}

	/**
	 * Utility merge method Used by merge sort
	 * Merge's sorted elements of array back together
	 * @param arr Unsorted Array of Comparable<T>[]
	 * @param a Low index of left array
	 * @param b High index of left array
	 * @param c Right array is b+1 to c
	 * @param comp Height/Volume/BaseArea Comparator
	 */
	  static <T> void merge(Comparable<T>[] arr, int a, int b, int c, Comparator comp) {
		    int n1 = b - a + 1;
		    int n2 = c - b;

		    Comparable<T>[] left = new Comparable[n1];
		    Comparable<T>[] right= new Comparable[n2];

		    for (int i = 0; i < n1; i++)
		    	left[i] = arr[a + i];
		    for (int j = 0; j < n2; j++)
		    	right[j] = arr[b + 1 + j];

		    int i = 0;
		    int j = 0;
		    int k = a;
		    
		    while (i < n1 && j < n2) {
		      if (comp.compare(left[i], right[j])>0) {
		        arr[k] = left[i];
		        i++;
		      } else {
		        arr[k] = right[j];
		        j++;
		      }
		      k++;
		    }

		    while (i < n1) {
		      arr[k] = left[i];
		      i++;
		      k++;
		    }

		    while (j < n2) {
		      arr[k] = right[j];
		      j++;
		      k++;
		    }
		  }

	  /**
	   * Driver of Quick Sort
	   * 
	   * @param arr Unsorted Array of Comparable<T>[]
	   * @param from Beginning Index of array to be sorted
	   * @param to Length of Array
	   * @param comp Height/Volume/BaseArea Comparator
	   */
	public static <T> void quickSort(Comparable<T>[] arr, int from, int to, Comparator comp) {

		if(from<to) {
			int part = partition(arr, from, to, comp);
			quickSort(arr, from, part-1, comp);
			quickSort(arr, part+1, to, comp);
		}
	}
	
	/**
	 * Utility method used by Quick sort method.
	 * Sets pivot and assigns it to it's correct postion in sorted list.
	 * Assigns all smaller elements to the lower side, and higher elements to higher side
	 * @param arr Unsorted Array of Comparable<T>[]
	 * @param from Beginning Index of array to be sorted
	 * @param to Length of Array
	 * @param comp Height/Volume/BaseArea Comparator
	 * @return
	 */
	private static <T> int partition(Comparable<T>[] arr, int from, int to, Comparator comp) {
		Comparable<T> pivot = arr[to];
		int i = (from-1);
		
		for (int j = from; j< to; j++) {
			if(comp.compare(arr[j], pivot)>0) {
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i+1, to);
		return (i+1);
	}
	
	/**
	 * Gnome Sort
	 * Compares current element to previous. 
	 * If swap occurs, index decrements with element.
	 * @param arr Unsorted Array of Comparable<T>[]
	 * @param l
	 * @param comp Height/Volume/BaseArea Comparator
	 */
	public static <T> void gnomeSort(Comparable<T>[] arr, int l, Comparator comp) {
		int index = 0;
		
		while(index < l) {
			if(index == 0)
				index++;
			if(comp.compare(arr[index], arr[index-1]) <= 0)
				index++;
			else {
				swap(arr, index, index-1);
				index--;
			}
		}
		return;
		}
	
	/**
	 * Utility swap method used by multiple Sorting methods
	 * @param arr Array with elements to be swapped
	 * @param i Index of Array element moving out of
	 * @param j Index of element to be swapped with
	 */
	private static <T> void swap(Comparable<T>[] arr, int i, int j) {
		Comparable<T> temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
