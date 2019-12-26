/**
 * Copy(C) 2019 Luvina SoftWare Company
 * VanBan.java Dec 25, 2019 2019 Minh
 * 
 */
package com.leminh.docfilevanban.vanban;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * @author Minh
 *
 */
public class VanBan {
	private int soLuongKiTu, soLuongDong, soLuongTu;
	private String text = "";
	private String path;

	public VanBan(String path) {
		super();
		this.path = path;
	}

	/**
	 * Đọc file
	 */
	public void readFile() {
		text = "";
		try {
			File file = new File(path);
			FileInputStream fin = new FileInputStream(file);
			byte[] buff = new byte[1024];
			int len = fin.read(buff);
			while (len > 0) {
				text += new String(buff, 0, len);
				len = fin.read(buff);
			}
			fin.close();
			System.out.println("Đã đọc file thành công!");
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
	public void demSoLuongDong() {
		soLuongDong = 0;
		;
		String[] chuoiCon = text.split("\r\n");
		soLuongDong = chuoiCon.length;
		System.out.println("Số dòng trong văn bản là: " + soLuongDong);
	}

	/**
	 * Đếm số lương kí tự trong Van bản
	 * 
	 * @param soLuongKiTu
	 */
	public void demSoLuongKiTu() {
		soLuongKiTu = text.length();
		// - (soLuongDong - 1) * 4;
		System.out.println("Số lượng kí tự là: " + soLuongKiTu);
	}

	/**
	 * Đếm số lượng từ trong văn bản
	 * 
	 * @param soLuongTu
	 */
	public void demSoLuongTu() {
		soLuongTu = 1;
		for (int i = 0; i < text.length(); i++) {
			if ((text.charAt(i) + "").contentEquals(" ")) {
				soLuongTu++;
			}
		}

		System.out.println("Số lượng từ trong văn bản là: " + soLuongTu);
	}
}
