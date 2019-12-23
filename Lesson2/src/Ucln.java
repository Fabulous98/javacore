/**
 * Copyright(C) 2019 LuvinaSoftware
 * Ucln.java, Nov 28, 2019, MDung
 */

import java.util.Scanner;

/**
 * Chuong trinh tim Ucln
 * @author MDung
 */

public class Ucln {
/**
 * Ham tim UCLN
 * @param a
 * @param b
 * @return Ucln(a,b)
 */
	public static int findUcln(int a, int b) {
		 
		while (a != b) {
	        if (a > b) {
	            a -= b;
	        } else {
	            b -= a;
	        }
	    }
		return a;
	}
	
	/**
	 * Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Nhap a,b tu ban phim
		System.out.println("Nhap a, b: ");
		Scanner scanner = new Scanner(System.in);
	    
	    int a = scanner.nextInt(); int a1=a;
	    int b = scanner.nextInt(); int b1=b;
	    scanner.close();
	    // In ra Ucln(a,b)
	    System.out.println("UCLN cua "+a1+" va "+b1+" la: "+findUcln(a1, b));

	}

}
