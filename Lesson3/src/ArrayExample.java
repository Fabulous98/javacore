/**
 * Copyright(C) 2019 LuvinaSoftware
 * ArrayExample.java, Dec 11, 2019, MDung
 */

import java.util.Scanner;

/**
 * Chương trình làm việc với mảng nhập từ bàn phím
 * @author MDung
 */
public class ArrayExample {
	
	/**
	 * Main
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Nhap so phan tu cua mang: ");
			Scanner scan = new Scanner(System.in);
				int n = scan.nextInt();
		
		System.out.println("Nhap cac phan tu trong mang: ");
			int[] a = new int[n];
			 
			// b nhiều hơn a một phần tử phục vụ việc thêm x vào b
			int[] b = new int[n+1];
				
				// Nhập mảng a từ bàn phím đồng thời clone sang b
				for (int i=0; i<n; i++) {
					b[i] = a[i] = scan.nextInt();
				}
				
			// Tìm chỉ số của phần từ lớn nhất trong mảng a
			int dem = findMax(a);
		
		System.out.printf("Phan tu lon nhat trong mang la:a[%d] = ", dem );
		System.out.println(a[dem]);
		System.out.println("Mang sau khi sap xep giam dan la: ");
				
			// Sắp xếp mảng a giảm dần
			sortedDescending(a);
					
			// In kết quả
			for (int i=0; i<a.length; i++) {
				System.out.printf(a[i]+" ");
				System.out.println();
			}
				
		System.out.println("Nhap so nguyen x can chen: ");
			int x = scan.nextInt();
					
		System.out.println("Mang moi la: ");
					
			// Thêm x vào mảng b và sắp xếp b giảm dần
			b[n] = x; 
			sortedDescending(b);
			
			// Hiển thị kết quả
			for (int i=0; i<b.length; i++) {
				System.out.printf(b[i]+" ");
				System.out.println();
			}
		
	}
	
	/**
	 * Hàm tìm chỉ số của phần tử lớn nhất trong mảng
	 *
	 * @param a
	 * @return dem
	 */
	public static int findMax(int[] a) {
		int max = a[0];
		int dem = 0;
		
			for (int i=1; i<a.length; i++) {
				if (max < a[i]) {
					max = a[i]; dem = i;
				}
			}
		return dem;
	}
	
	/**
	 * Hàm sắp xếp giảm dần mảng
	 *
	 * @param a
	 */
	public static void sortedDescending(int[] a) {
		int left = 0;
		int right = a.length - 1;
			QuickSort.quickSort(a, left, right);
			int swap = a[0];
			for (int i=0; i<=(int)(a.length/2) ; i++) {
				swap = a[i];
				a[i] = a[a.length-1-i];
				a[a.length-1-i] = swap;
			}
	}
	

}
