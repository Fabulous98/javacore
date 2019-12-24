/**
 * Copyright(C) 2019 LuvinaSoff
 * QuickSort.java, Dec 9, 2019, MDung
 */

import java.util.Scanner;

/**
 * Chương trình thực hiện QuickSort tăng dần
 * @author MDung
 */

public class QuickSort {


	  /**
	   * Hàm static QuickSort
	   * @param arr
	   * @param left
	   * @param right
	   */
	  public static void quickSort(int[] arr, int left, int right) {
		  
		// Trả về void khi mảng rỗng hoặc sắp xếp xong (left > right)
	    if (arr == null || arr.length == 0)
	      return;
	
	    if (left >= right)
	      return;
	
	    // middle lưu chỉ số của pivot ở giữa mảng
	    int middle = left + (right - left) / 2;
	    int pivot = arr[middle];
	    int i = left, j = right;
	
	    while (i <= j) {
	      
	      while (arr[i] < pivot) {
	        i++;
	      }
	
	      
	      while (arr[j] > pivot) {
	        j--;
	      }
	
	     
	      if (i <= j) {
	        int temp = arr[i];
	        arr[i] = arr[j];
	        arr[j] = temp;
	        i++;
	        j--;
	      }
	    }
	
	    
	    if (left < j)
	      quickSort(arr, left, j);
	
	    if (right > i)
	      quickSort(arr, i, right);
	  }
	
	
	}
