/**
 * Copyright(C) 2019 LuvinaSoftware
 * CalculatorEx.java, Dec 15, 2019, MDung
 */

import java.util.*;

/**
 * Chương trình xử lý chuỗi số, tính toán và xử lý ngoại lệ
 * @author MDung
 */
public class CalculatorEx {

	/**
	 * Main
	 *
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		int s1 = 0;
		int s2 = 0;
		System.out.println("Nhap chuoi 1: ");
		String chuoi1 = scan.nextLine();
		
		try {
			s1 = Integer.parseInt(chuoi1);
		}catch (NumberFormatException e){
			System.out.println(e);
		}
		
		System.out.println("Nhap chuoi 1: ");
		String chuoi2 = scan.nextLine();
		
		try {
			s2 = Integer.parseInt(chuoi2);
		}catch (NumberFormatException e){
			System.out.println(e);
		}
		
		sum(s1,s2);
		subtract(s1,s2);
		multiply(s1,s2);
		divide(s1,s2);
		square(s1);
		exponential(s1,s2);
	}
	
	/**
	 * tính tổng 2 số
	 * @param a
	 * @param b
	 */
	public static void sum(int a, int b) {
		int tong = a + b;
		System.out.println("a + b = : " + tong);
	}
	
	/**
	 * tính hiệu 2 số
	 * @param a số bị trừ
	 * @param b số trừ
	 */
	public static void subtract(int a, int b) {
		int sub = a - b;
		System.out.println("a - b = : " + sub);
	}
	
	/**
	 * tính tích 2 số
	 * @param a
	 * @param b
	 */
	public static void multiply(int a, int b) {
		int mul = a * b;
		System.out.println("a * b = : " + mul);
	}
	
	/**
	 * tính thương 2 số
	 * @param a số bị chia
	 * @param b số chia
	 */
	public static void divide(int a, int b) {
		try {
			float div = (float)a / (float)b;
			System.out.println("a / b = " + div);
		}
		catch(ArithmeticException e) {
			System.out.println(e);
		}
	}
	
	/**
	 * tính căn bậc 2 
	 * @param a
	 */
	public static void square(int a) throws ArithmeticException {
		if (a < 0) {
			throw new ArithmeticException("Error Số âm!");
		}
		try {
			float squa = (float) Math.sqrt(a);
			System.out.println("Can bac hai cua a la: " + squa);
		}
		catch(ArithmeticException e) {
			System.out.println(e);
		}
	}
	
	/**
	 * tính lũy thừa
	 * @param a cơ số
	 * @param b số mũ
	 */
	public static void exponential(int a, int b) {
			long kq = (long)Math.pow(a, b);
			System.out.println("Luy thua " + a + "^" + b + " = " + kq);
	}

}
