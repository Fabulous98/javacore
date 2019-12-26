/**
 * Copyright(C) 2019 LuvinaSoftware
 * Main.java Dec 17, 2019 Cong_PV
 */
package com.luvina.user.main;

import java.util.List;

import com.luvina.user.manager.UserManager;
import com.luvina.user.user.User;

/**
 * @author admin Description
 */
public class Main {

	/**
	 * Description
	 * 
	 * @param
	 * @return
	 */
	public static void main(String[] args) {
		UserManager mgr = new UserManager("D:\\Account.txt");
//		mgr.nhapUser();
//		mgr.printUser();
		mgr.tinhTongDiem();
		List<User> listKQ = mgr.timUserTheoTen("trong");
		if (listKQ.isEmpty()) {
			System.out.println("Không có User trùng với tên đã nhập");
		} else {
			System.out.println("Các User cần tìm là:\n" + listKQ);
		}
	}

}
