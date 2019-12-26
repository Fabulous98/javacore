/**
 * Copy(C) 2019 Luvina SoftWare Company
 * Manager.java Dec 25, 2019 2019 Minh
 * 
 */
package com.leminh.user.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Comparator;

import com.leminh.user.user.User;

/**
 * Xây dựng đối tượng quản lí user để thực hiện các phương thức thêm 1 user,
 * tính tổng điểm của các user, tìm kiếm user, sắp xếp user
 * 
 * @author Minh
 *
 */
public class Manager {
	private String path;
	private ArrayList<User> list;

	private Comparator<User> tfSoftUpName = new Comparator<User>() {

		@Override
		public int compare(User user1, User user2) {
			return user1.getUserName().compareTo(user2.getUserName());
		}
	};

	public Manager(String path) {
		super();
		this.path = path;

		list = new ArrayList<User>();
	}

//	public Manager(String path) {
//		path = "D:/user.txt";
//		list = new ArrayList<User>();
//	}

	/**
	 * Phương thức thêm 1 user vào danh sách
	 * 
	 * @param userName
	 * @param age
	 * @param address
	 * @param score
	 */
	public void addUser(String userName, int age, String address, double score) {
		User user = new User(userName, age, address, score);
		list.add(user);
		System.out.println("Đã thêm user thành công!");

	}

	/**
	 * Lưu danh sách vào file
	 * 
	 * @param path là đường dẫn để lưu file
	 */
	public void writeListToFile() {
		String text = "";
		for (int i = 0; i < list.size(); i++) {
			text += list.get(i).getUserName() + "_" + list.get(i).getAge() + "_" + list.get(i).getAddress() + "_"
					+ list.get(i).getScore() + "\r\n";
		}

		try {
			File file = new File(path);
			FileOutputStream fout = new FileOutputStream(file);
			byte[] buff = text.getBytes();
			fout.write(buff);
			fout.close();
			System.out.println("Đã ghi thành công");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Đọc file
	 */
	public String readFile() {
		String chuoi = "";
		try {
			File file = new File(path);
			FileInputStream fin = new FileInputStream(file);
			byte[] buff = new byte[1024];
			int len = fin.read(buff);
			while (len > 0) {
				chuoi += new String(buff, 0, len);
				len = fin.read(buff);
			}
			fin.close();
			System.out.println("Đã đọc file thành công!");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return chuoi;
	}

	/**
	 * Sắp xếp tăng dần theo tên của các user
	 */
	public void softUpName() {
		list.sort(tfSoftUpName);
		System.out.println("Danh sách User sắp xếp theo tên tăng dần là:");
		System.out.println(list);
	}

	/**
	 * tính tổng điểm của các User trong danh sách
	 * 
	 * @param sum là tổng
	 */
	public void tinhTongDiem() {
		double sum = 0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i).getScore();
		}

		System.out.println("Tổng điểm của các User là: " + sum);
	}

	/*
	 * tìm kiếm User theo tên và trả về 1 dannh sách các user có tên trùng nhau
	 */
	public void timKiemTheoTen(String name) {
		ArrayList<User> listUser = new ArrayList<User>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUserName().equals(name)) {
				listUser.add(list.get(i));
			}
		}
		System.out.println(listUser);
	}
}
