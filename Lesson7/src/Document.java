/**
 * Copyright(C) 2019 LuvinaSoff
 * Document.java, Dec 26, 2019, MDung
 */

import java.io.*;
import java.util.*;

/**
 * Chương trình ví dụ về đọc ghi file
 * @author MDung
 */
public class Document {
	
	public static void main(String[] args) throws IOException {
		File file = new File("../TenBaiHat.txt");
		file.createNewFile();
	    FileWriter writer = new FileWriter(file); 
	      // ghi noi dung vao file
	      writer.write("Mot cong mot lon hon hai\r\nThis is a song!\r\n1 2 3"); 
	      writer.flush();
	      writer.close();
		Document doc = new Document("../TenBaiHat.txt");
		doc.readFile();
		doc.countSoLuongDong();
		doc.countSoLuongKiTu();
		doc.countSoLuongTu();
	}
	
	private int soLuongKiTu, soLuongDong, soLuongTu;
	private String text = "";
	private String path;

	public Document(String path) {
		super();
		this.path = path;
	}

	/**
	 * Đọc file
	 */
	public void readFile() {
		text = "";
		try {
			FileReader fr = new FileReader(path);
			int i;
			while((i = fr.read())!=-1) {
				text+=(char)i;
			}
			fr.close();
			File file = new File(path);
			System.out.println("Read File Successfull!");
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Nội dung file là:");
		System.out.println(text);

	}

	/**
	 * Đếm số lượng dòng trong van bản
	 * 
	 * @param soLuongDong
	 */
	public void countSoLuongDong() {
		soLuongDong = 0;
		;
		String[] Ducuments = text.split("\r\n");
		soLuongDong = Ducuments.length;
		System.out.println("Số dòng trong văn bản là: " + soLuongDong);
	}

	/**
	 * Đếm số lương kí tự
	 * 
	 * @param soLuongKiTu
	 */
	public void countSoLuongKiTu() {
		soLuongKiTu = text.length() - (soLuongDong-1)*2;
		System.out.println("Số lượng kí tự: " + soLuongKiTu);
	}

	/**
	 * Đếm số lượng từ 
	 * 
	 * @param soLuongTu
	 */
	public void countSoLuongTu() {
		soLuongTu = 1;
		
		String[] Ducuments = text.split(" ");
		
		soLuongTu = Ducuments.length + soLuongDong-1;

		System.out.println("Số lượng từ trong văn bản là: " + soLuongTu);
	}
}
