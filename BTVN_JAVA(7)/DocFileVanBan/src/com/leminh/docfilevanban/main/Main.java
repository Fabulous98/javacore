/**
 * Copy(C) 2019 Luvina SoftWare Company
 * Main.java Dec 25, 2019 2019 Minh
 * 
 */
package com.leminh.docfilevanban.main;

import com.leminh.docfilevanban.vanban.VanBan;

/**
 * @author Minh
 *
 */
public class Main {
	public static void main(String[] args) {
		VanBan vanBan = new VanBan("D:/user.txt");
		vanBan.readFile();
		vanBan.demSoLuongDong();
		vanBan.demSoLuongKiTu();
		vanBan.demSoLuongTu();
	}
}
