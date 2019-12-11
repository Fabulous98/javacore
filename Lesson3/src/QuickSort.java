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
	 * Main
	 * @param args
	 */
	
	public static void main(String[] args) {
	  
		Scanner scanner = new Scanner(System.in); 
		System.out.println("Nhap so phan tu mang: ");
		int n = scanner.nextInt();
	
			//Tạo mảng X có n phần từ nhập từ bàn phím
			int[] x = new int[n];
			System.out.println("Nhap cac phan tu mang: ");
			
			for (int i=0; i<n; i++) {
				x[i] = scanner.nextInt();
			}

		// left và right là chỉ số của phần từ đầu, cuối của mảng x
	    int left = 0;
	    int right = x.length - 1;
	    
		    // Thực hiện sắp xếp nhanh dãy x
		    quickSort(x, left, right);
	    
	    // In ra kết quả sau khi sắp xếp
	    System.out.println("Day so sau khi sap xep: ");
	    printArray(x);
	}

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
	      // Khi bên trái của pivot nhỏ hơn pivot thì xét tiếp phần từ bên phải
	      while (arr[i] < pivot) {
	        i++;
	      }
	
	      // Khi bên phải của pivot lớn hơn pivot thì xét tiếp phần từ bên trái
	      while (arr[j] > pivot) {
	        j--;
	      }
	
	      // tiến hành đổi chỗ 2 phần từ bên trái và bên phải pivot không thỏa mãn arr[i] < pivot và arr[j] > pivot
	      // đồng thời tăng i và giảm j 1 đơn vị
	      if (i <= j) {
	        int temp = arr[i];
	        arr[i] = arr[j];
	        arr[j] = temp;
	        i++;
	        j--;
	      }
	    }
	
	    // Tiếp tục QuickSort với phần mảng bên trái pivot lúc đầu
	    if (left < j)
	      quickSort(arr, left, j);
	
	    // Tiếp tục QuickSort với phần mảng bên phải pivot lúc đầu
	    if (right > i)
	      quickSort(arr, i, right);
	  }
	
	  // In ra mảng sau khi sắp xếp nhanh
	  public static void printArray(int[] arr) {
	    for(int i = 0; i < arr.length; i++) {
	      System.out.print(arr[i] + " ");
	    }
	
	    System.out.println();
	  }
	}
