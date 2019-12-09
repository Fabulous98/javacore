/**
 * Copyright(C) 2019 LuvinaSoff
 * Fibonacci.java, Dec 9, 2019, MDung
 */

import java.util.Scanner;

/**
 * Chương trình tìm số fibonacci thứ n
 * @author MDung
 */
public class Fibonacci {
	
	// thuộc tính thể hiện số fibonacci thứ mấy
	int fibNumber;
	// Thuộc tính thể hiện giá trị của số fibonacci
	int value;
	
	// Hàm khởi tạo
	Fibonacci(int n){
		this.fibNumber = n;
		this.value = this.findFibonacci();
	}
	
	/**
	 * Phương thức tìm số Fibonacci thứ n
	 * @return Fibonacci_thứ_n
	 */
	public int findFibonacci(){
		
	// Tạo một mảng có 50 phần tử
	int[] arrFib = new int [50];
	int i, k;
	
		k=this.fibNumber;
		
		// Khởi tạo 3 số fibonacci đầu tiên
		arrFib[0]=0; 
		arrFib[1]=1; 
		arrFib[2]=1;
		// Tính toán các số fibonacci đến số thứ n
		for (i=1; i<=k-1; i++) {
			arrFib[i+1] = arrFib[i] + arrFib[i-1];
		}
	return arrFib[k];
	}

	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// Nhập n từ bàn phím
		System.out.print("Nhap n: ");
		Scanner scanln = new Scanner(System.in);
		int n = scanln.nextInt(); 
		
		// Tạo một thể hiện của lớp Fibonacci với đầu vào là n
		Fibonacci m = new Fibonacci(n);
			
			if (n<0) System.out.print("Error");

		System.out.println("So Fibonacci thu " + n + " la: " + m.value);
		
	}

}
