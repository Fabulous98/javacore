/**
 * Copyright(C) 2019 LuvinaSoff
 * StandardString.java, Dec 12, 2019, MDung
 */

import java.util.*;

/**
 * Chương trình chuẩn hóa xâu
 * @author MDung
 */
public class StandardString {
	/**
	 * Hàm xóa khoảng trắng và chuyển thành chữ thường
	 * @param str
	 * @return str
	 */
	public String chuanHoa(String str) {
        str = str.trim();
        str = str.replaceAll("\\s+", " ");
        str = str.toLowerCase();
        return str;
    }
 
	/**
	 * Hàm viết hoa các chữ cái đầu tên riêng
	 * @param str
	 * @return
	 */
    public String chuanHoaDanhTuRieng(String str) {
        str = chuanHoa(str);
        
        // truyền các từ trong str vào một mảng temp
        String temp[] = str.split(" ");
        str = "";
        
        // Viết hoa từng chữ rồi cộng dồn các chữ vào str
        for (int i = 0; i < temp.length; i++) {
            str += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
            	if (i < temp.length - 1)
            		str += " ";
        }
        
        // Xóa dấu cách ở cuối
        	str =str.trim();
        return str;
    }
 
    /**
     * Main
     * @param sgr
     */
    public static void main(String[] sgr) {
        StandardString chx = new StandardString();
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Nhap mot xau: ");
        String xau = scan.nextLine();
        
        xau = chx.chuanHoaDanhTuRieng(xau);
        System.out.println("Xau sau khi chuan hoa la: " + xau);
    }
}
