/**
 * Copyright(C) 2019 LuvinaSoff
 * FindLongestStr.java, Dec 12, 2019, MDung
 */

import java.util.*;

/**
 * Description
 * @author MDung
 */
public class FindLongestStr {

	/**
	 * Main
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	        Scanner scan = new Scanner(System.in);
	        // Nhập xâu từ bàn phím
	        System.out.println("Nhap mot xau: ");
	        	String xau = scan.nextLine();
	        		xau = xau.trim();
	        
	        // Xóa khoảng trắng đầu, cuối
	        String[] temp = xau.split(" ");
	        
	        // Tìm vị trí từ dài nhất
	        int index = findIndexOfLongestStr(temp);
	        
	        // In ra kết quả
	        System.out.println("Tu dai nhat la xau thu " + index + ": " + temp[index]);

	}
	
	/**
	 * Hàm tìm chỉ số của từ dài nhất
	 * @param str
	 * @return dem
	 */
	public static int findIndexOfLongestStr(String[] str) {
		int max = str[0].length();
		int dem = 0;
		
		// So sánh độ dài để cuối cùng max = từ dài nhất, dem bằng chỉ số của từ đó
		for (int i=1; i<str.length; i++ ) {
			if (str[i].length() > max) {
				max = str[i].length();
				dem = i;
			}
		}
	return dem;
	}

}
