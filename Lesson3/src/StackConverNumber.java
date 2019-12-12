/**
 * Copyright(C) 2019 LuvinaSoff
 * StackConverNumber.java, Dec 12, 2019, MDung
 */

import java.util.*;

/**
 * Chương trình chuyển Decimal thành Binary dùng Stack
 * @author MDung
 */
public class StackConverNumber {

	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Nhap so thap phan: ");
			Scanner scan = new Scanner(System.in);
			int decimalNumber = scan.nextInt();
		
		System.out.print("Hệ nhị phân của " + decimalNumber +" là :");
		  
			// Convert và in ra kết quả
			convertBinary(decimalNumber);

	}
	/** 
	 * Hàm convert Decimal to Binary
	 * @param num
	 */
	public static void convertBinary(int num){
		Stack<Integer> stack = new Stack<Integer>();
	    	while(num != 0){
	    	 
	    		//thực hiện phép chia lấy phần dư cho 2.
	    		int d = num%2;
	      
	    		// thêm vào stack.
	    		stack.push(d);
	    		num/=2;
	    	}
	     
		     // In ra kết quả
		     while(!(stack.isEmpty())){
		      System.out.print(stack.pop());
		     }     
	}
}
