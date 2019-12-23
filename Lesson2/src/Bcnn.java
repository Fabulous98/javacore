/**
 * Copyright(C) 2019 LuvinaSoftware
 * Bcnn.java, Nov 28, 2019, MDung
 */

import java.util.Scanner;

/**
 * Description
 * @author MDung
 */
public class Bcnn {
	
	/**
	 * Main
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Nhap a, b tu ban phim
		System.out.println("Nhap a, b: ");
		Scanner scanner = new Scanner(System.in);
	    int a = scanner.nextInt(); 
	    int b = scanner.nextInt(); 
	    scanner.close();
	    // Tim BCNN(a,b) va gan cho c
	    int c = findBcnn(a , b);
	    // In ket qua ra man hinh
	    System.out.println("BCNN cua "+a+" va "+b+" la: "+c);

	}
	/**
	 * Ham tim BCNN
	 * @param a
	 * @param b
	 * @return
	 */
	public static int findBcnn(int a, int b) {
		
	    int c = Ucln.findUcln(a , b);
	   
	    return a*b/c;
	}

}
