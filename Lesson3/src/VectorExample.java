/**
 * Copyright(C) 2019 LuvinaSoff
 * Vector.java, Dec 12, 2019, MDung
 */

import java.util.*;

/**
 * Description
 * @author MDung
 */
public class VectorExample {

	/**
	 * Description
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

				Vector vec = new Vector(3, 2);
				
				//add thêm 10 element
				vec.addElement(new String("A1"));
				vec.addElement(new String("B2"));
				vec.addElement(new String("C3"));
				vec.addElement(new String("D4"));
				vec.addElement(new String("E5"));
				vec.addElement(new String("F6"));
				vec.addElement(new String("G7"));
				vec.addElement(new String("H8"));
				vec.addElement(new String("I9"));
				vec.addElement(new String("J10"));
				
				//in ra nội dung vector
				Enumeration vecEnum = vec.elements();
				System.out.println("\nCac phan tu trong Vector:");
					while(vecEnum.hasMoreElements())
						System.out.print(vecEnum.nextElement() + " ");
				
				//Xóa element 5
				vec.remove(5);
				
				//in ra size
				System.out.println("\n" + vec.size());
				
				vec.set(2, "Viet Nam");
				
				System.out.println(vec.elementAt(2));
				
				//kiểm tra "Canada"
				System.out.println(vec.contains("Canada")); 
				
				//Xóa toàn bộ vector
				vec.clear();
				
				// Thêm lại 6 phần tử vào Vector
				vec.addElement(new String("First"));
				vec.addElement(new String("B2"));
				vec.addElement(new String("C2"));
				vec.addElement(new String("D2"));
				vec.addElement(new String("E2"));
				vec.addElement(new String("Last"));
				
				// In ra thanh phan dau tien
				System.out.println(vec.firstElement());
				
				// In ra thanh phan cuoi cung
				System.out.println(vec.lastElement());
				
				// Truyền vector vào mảng
				Object[] str = vec.toArray();
				
				// In mảng đó
				for (int i=0; i<str.length; i++) {
					System.out.printf(str[i]+ " ");
				}
				
				// Thêm American vào gần cuối vector
				vec.add(vec.size() -1, "American");
				
				str = vec.toArray();
				
				// Chỉ số thành phần thứ 6 và in ra nó
				System.out.println("\nChi so thanh phan thu 6 (" + str[5] + ") la: " + vec.indexOf(str[5]));

	}

}
