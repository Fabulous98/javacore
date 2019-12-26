/**
 * Copyright(C) 2019 LuvinaSoff
 * Manager.java, Dec 26, 2019, MDung
 */

import java.util.*;
import java.io.*;

/**
 * Description
 * @author MDung
 */
public class Manager {
	
	public static void main(String[] args) {
		Manager manager = new Manager("../users.txt");
		manager.addUser("Dũng", 21, "Hà Nội", 9.5);
		manager.addUser("Huy", 20, "Hà Tĩnh", 9.2);
		
		manager.writeListToFile();
		String text = manager.readFile();
		
		System.out.println(text);
		manager.softUpByName();
		System.out.println("Tìm kiếm theo tên User: ");
		manager.searchByName("Huy");
	}
	
	private String path;
	private ArrayList<User> list;

	private Comparator<User> conditions = new Comparator<User>() {

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


	/**
	 * Thêm 1 user vào danh sách
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
			text += list.get(i).getUserName() + " " + list.get(i).getAge() + " " + list.get(i).getAddress() + " "
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
	 * Read file
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
	public void softUpByName() {
		list.sort(conditions);
		System.out.println("Danh sách User sắp xếp tăng dần theo tên:");
		System.out.println(list);
		writeListToFile();
	}

	/**
	 * tính tổng điểm của các User
	 * 
	 * @param sum
	 */
	public void countTongDiem() {
		double sum = 0;
		for (int i = 0; i < list.size(); i++) {
			sum += list.get(i).getScore();
		}

		System.out.println("Tổng điểm của các User: " + sum);
	}

	/*
	 * tìm kiếm User theo tên hiển thị danh sách các user có tên trùng nhau
	 */
	public void searchByName(String name) {
		ArrayList<User> listUsers = new ArrayList<User>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getUserName().equals(name)) {
				listUsers.add(list.get(i));
			}
		}
		System.out.println(listUsers);
	}
}
