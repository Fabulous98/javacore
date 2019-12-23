/**
 * Copyright(C) 2019 LuvinaSoftware
 * SumInteger.java, Nov 28, 2019, MDung
 */

import java.util.Scanner;

/**
 * Chuong trinh tinh tong cac chu so
 * @author MDung
 */
public class SumInteger {
	/**
	 * Main
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Nhap so nguyen duong a
		System.out.println("Nhap mot so nguyen duong: ");
		Scanner scan = new Scanner(System.in);
		int a = scan.nextInt();
		scan.close();
		// Tinh tong cac chu so va in 
		int c=SumInteger.sumInt(a);
		System.out.println("Tong cac chu so la: "+c);
	}
	
	/**
	 * Phuong thuc tinh tong cac chu so
	 * @param a
	 * @return tong_cac_chu_so
	 */
	public static int sumInt(int a) {
		
		int soDu, n = a, tong=0;
		while (n > 0) {
	        soDu = n % 10;
	        n = n / 10;
	        tong += soDu;
	    }
		return tong;
	}

}
