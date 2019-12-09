/**
 * Copyright(C) 2019 LuvinaSoff
 * ListNumber.java, Dec 9, 2019, MDung
 */

import java.util.Scanner;

/**
 * Chương trình liệt kê các phần tử xuất hiện 2 lần
 * @author MDung
 */
public class ListNumber {

	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Nhap so phan tu cua mang: ");
		int n = scan.nextInt();
		showNumbers(n);

	}
	
	public static void showNumbers(int n) {
		Scanner scan = new Scanner(System.in);
		float[] a = new float[n];
		System.out.println("Nhap cac phan tu cua mang: ");
		for (int i=0; i<n; i++) {
			a[i] = scan.nextFloat();
		}

		System.out.println("Cac so xuat hien dung 2 lan la: ");
			int dem=0;
			
			for (int j=2; j<n; j++) {
				if (a[0] == a[j])
					dem++;
			}
			
			if (dem == 1) System.out.print(a[0]+" ");
			
			for (int i=1; i<n; i++) {
				dem=0;
			for (int j=i+1; j<n; j++) {
				if (a[i] == a [j] && a[i] != a[0]) {
					dem++; a[j] = a[0];
				}
				//System.out.println(dem);
			}
			
			if (dem == 1) System.out.print(a[i] + " ");
		}
		
	}

}
