/**
 * 
 */

import java.util.*;

/**
 * @author LA-PM
 *
 */
public class Cau1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Nhap 0<n<100: ");
		int n = scan.nextInt();
		System.out.println("Nhap 0<m<100: ");
		int m = scan.nextInt();
		
		int[][] A = new int[n][m];
		for (int i=0; i<n; i++) {
			System.out.println("Nhap dong " + i +":");
			for (int j=0; j<m; j++) {
				A[i][j] = scan.nextInt();
			}
			
		}
		
		
		
		int max1 = A[0][0];
		int indexA = 0;
		int indexB = 0;
		
		for (int i=0; i<n; i++) {
			for (int j=0; j<m; j++) {
				if (max1>A[i][j]) {
					max1=A[i][j];
					indexA = i;
					indexB = j;
					break;
				}
			}
			
		}
		
		int max2 = max1;
		int index1 = indexA;
		int index2 = indexB;
		max1 = A[0][0];
		indexA = 0;
		indexB = 0;
		
		for (int i=0; i<n; i++) {
			for (int j=1; j<m; j++) {
				if (A[i][j]>max1) {
					max2 = max1;
					max1 = A[i][j];
					index1 = indexA;
					index2 = indexB;
					indexA = i; indexB= j;
				}
			}
			
		}
		
		System.out.println("So lon thu 2 la: " + max2 + " o vi tri " + "[" + index1 + "]" +"[" + index2 +"]");
		
		System.out.println("Cac so nguyen to trong mang la: ");
				
				for (int i=0; i<n; i++) {
					for (int j=0; j<m; j++) {
						if (checkSnt(A[i][j])) {
							System.out.println(A[i][j]+" ");
						}
					}
					
				}
				
		for (int i=0; i<n; i++) {
					
			QuickSort.quickSort(A[i], 0, n);
		}
		
		System.out.println("Mang sau khi sap xep tang dan la: ");
		
		for (int i=0; i<n; i++) {
			System.out.println("");
			for (int j=0; j<m; j++) {
				System.out.printf(A[i][j] + " ");
				}
			}
			
		}
	
	public static boolean checkSnt(int a) {
		
		boolean check = false;
		int dem = 0;
		for (int i=1; i<=a; i++) {
			if (a%i==0) {
				dem++;
			}
		}
		
		if (dem == 2) check = true;
		return check;
	}

}
