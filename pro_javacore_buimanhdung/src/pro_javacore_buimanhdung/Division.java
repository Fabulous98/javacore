/**
 * Copyright(C) 2019 Luvina Software
 * Devision.java, Dec 27, 2019, MDung
 */
package pro_javacore_buimanhdung;

import java.util.Scanner;

/**
 * Chương trình chia hai số A, B. Trong đó 0 < A, B < 99999
 * @author MDung
 */
public class Division {
	
	// Biến dùng để nhập dữ liệu từ bàn phím cho các hàm trong chương trình, sau này không cần khai báo lại
	public static Scanner scan = new Scanner(System.in);
	
	/**
	 * Phương thức Main
	 * @param args
	 */
	public static void main(String[] args) {
		int a = 0;
		int b = 0;
		// Nhập số nguyên a đến khi hợp lệ
		a = importValue("A");
		// Nhập số nguyên b đến khi hợp lệ
		b = importValue("B");
		// In ra kết quả không làm tròn a/b
		System.out.println("Thương của A/B bằng: " + divide(a,b));
	}
	
	/**
	 * tính thương 2 số
	 * @param a số bị chia
	 * @param b số chia
	 * @return results
	 * @throws ArithmeticException
	 */
	public static float divide(int a, int b) {
		
		// Biến lưu kết quả trả về
		float results = 0;
		results = (float)a / (float)b;
		return results;
	}
	
	/**
	 * Hàm nhập một số thỏa mản > 0 và <= 99999
	 * @return value
	 * @throws NumberFormatException
	 */
	
	public static int importValue(String inputs) {
		
		// Biến lưu giá trị trả về
		int value = 0;
		System.out.println("Giá trị ["+inputs+"]:");
		// Nhập số từ bàn phím
		String str = scan.nextLine();
		
		// Xét xem dữ liệu vừa nhập có phải số không
		try {
			value = Integer.parseInt(str);
			
		// Bắt lỗi nếu không phải là số hoặc không nhập gì
		} catch (NumberFormatException e) {
			// Xử lý nếu không nhập gì (chỉ gõ Enter)
			if (str.length() < 1){
				System.out.println("Hãy nhập giá trị cho ["+inputs+"].");
				value = importValue(inputs);
			// Xử lý nếu nhập giá trị không phải số (bao gồm dấu cách và các ký tự khác số khác)
			} else {
				System.out.println("Giá trị ["+inputs+"] phải là giá trị số và > 0. Hãy nhập lại.");
				value = importValue(inputs);
			}
		}
		
		// Nếu nhập đúng số nhưng value <=0 thì thông báo và gọi lại hàm importValue(inputs)
		if (value <= 0) {
			System.out.println("Giá trị ["+inputs+"] phải là giá trị số và > 0. Hãy nhập lại.");
			value = importValue(inputs);
		// Nếu số đã nhập có 6 chữ số trở lên thì thông báo và gọi lại hàm importValue(inputs)
		} if (value > 99999) {
			System.out.println("Giá trị ["+inputs+"] không được lớn hơn 5 số. Hãy nhập lại.");
			value = importValue(inputs);
		}
		return value;
	}
}
