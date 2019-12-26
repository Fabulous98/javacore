/**
 * Copy(C) 2019 Luvina SoftWare Company
 * Main.java Dec 25, 2019 2019 Minh
 * 
 */
package com.leminh.user.main;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool.ManagedBlocker;

import com.leminh.user.manager.Manager;

/**
 * @author Minh
 *
 */
public class Main {
	public static void main(String[] args) {
		Manager manage = new Manager("D:/user.txt");
		manage.addUser("Nguyệt", 22, "Hải Dương", 9.5);
		manage.addUser("Le", 23, "Bắc Ninh", 9);
		manage.writeListToFile();
		String text = manage.readFile();
		System.out.println(text);
		manage.softUpName();
		System.out.println("Tìm kiếm theo tên User: ");
		manage.timKiemTheoTen("Nguyệt");

	}
}
