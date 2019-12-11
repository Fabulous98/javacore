/**
 * Copyright(C) 2019 LuvinaSoftware
 * SumCross.java, Dec 11, 2019, MDung
 */

import java.util.Scanner;

/**
 * Chương trình tính tổng đường chéo chính ma trận nxn
 * @author MDung
 */
public class SumCross {

	/**
	 * Main
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Nhap so phan tu cua mang 2 chieu: ");
			int n = scan.nextInt();
			int[][] a = new int[n][n];
		
		// Nhập mảng 2 chiều nxn từ bàn phím
		for (int i=0; i<n; i++) {
			for (int j=0; j<n; j++) {
				a[i][j] = scan.nextInt();
			}
		}
		
		// In ra kết quả là tổng các phần tử trên đường chéo chính sử dụng hàm sumMainCross()
		System.out.println("Tong cac phan tu tren duong cheo chinh la: "+ sumMainCross(a));

	}
	
	/**
	 * Hàm tính tổng các phần tử trên đường chéo chính của một mảng 2 chiều nxn
	 *
	 * @param a
	 * @return sum
	 */
	public static int sumMainCross(int[][] a) {
		int sum = 0;
		
			// cộng dồn sum các phần tử trên đường chéo chính
			for (int i=0; i<a.length; i++) {
				sum+=a[i][i];
			}
			return sum;
	}

}
