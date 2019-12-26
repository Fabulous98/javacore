/**
 * Copyright(C) 2019 LuvinaSoftware
 * UserManager.java Dec 17, 2019 Cong_PV
 */
package com.luvina.user.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.luvina.user.user.User;

/**
 * @author admin Description
 */
public class UserManager {
	private String path;

	public UserManager(String path) {
		this.path = path;
	}

	/**
	 * Description Phương thức Nhập vào các User lưu vào file
	 * 
	 * @param xacNhan  xác nhận nhập nữa không
	 * @param userName
	 * @param age
	 * @param address
	 * @param score
	 * @param str
	 * @return
	 */
	public void nhapUser() {
		int xacNhan = 1;// Khởi tạo xacNhan = 1
		Scanner sc = new Scanner(System.in);// Tạo sc nhập dữ liệu vào từ bàn phím
		while (xacNhan == 1) {
			System.out.println("Nhập tên User:");
			String userName = sc.nextLine();// Giá trị tên User
			System.out.println("Nhập Địa chỉ:");
			String address = sc.nextLine();// Giá trị địa chỉ
			System.out.println("Nhập tuổi:");
			int age = sc.nextInt();// Giá trị tuổi
			System.out.println("Nhập Điểm:");
			double score = sc.nextDouble();// Giá trị điểm
			String str = userName + "-" + age + "-" + address + "-" + score + "\n";// Chuỗi lưu giá trị
			writeFile(str);// Ghi giá trị vào file
			System.out.println("Bạn có muốn nhập User tiếp theo không?(1: Có / 0: Không)");
			xacNhan = sc.nextInt();
			sc.nextLine();
		}
	}

	/**
	 * Description Phương thức phụ ghi dữ liệu vào file
	 * 
	 * @param buff[] mảng byte đọc dữ liệu
	 * @param file
	 * @param fout
	 * @return
	 * @throws IOException
	 */
	private void writeFile(String str) {
		File file = new File(path);// Trỏ đến file có đường dẫn path
		try {
			FileOutputStream fout = new FileOutputStream(file, true);// Mở luồng
			byte buff[] = str.getBytes();// ghi file theo mảng buff
			fout.write(buff);// Ghi file
			fout.close();// đóng luồng
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Description Xây dựng tiêu chí so sánh theo tên User
	 */
	private Comparator<User> tcSortNameUp = new Comparator<User>() {

		@Override
		public int compare(User u1, User u2) {
			return u1.getUserName().compareTo(u2.getUserName());
		}
	};

	/**
	 * Description Phương thức đọc nội dung từ file, sắp xếp danh sách theo tên
	 * user, hiển thị ra màn hình
	 * 
	 * @param
	 * @return
	 */
	public void printUser() {
		List<User> listUser = TaoListData();// Tạo listUser từ dữ liệu đọc từ file

		Collections.sort(listUser, tcSortNameUp);// Sắp xếp theo tc Tên User tăng dần
		System.out.println(listUser.toString());// In listUser
	}

	/**
	 * Description Phương thức phụ Tạo listUser từ dữ liệu đọc từ file
	 * 
	 * @param str, arrUser[], arr[], userName, age, address, score
	 * @return listUser
	 */
	private List<User> TaoListData() {
		List<User> listUser = new ArrayList<User>();
		String str = readFile();// Đọc dữ liệu từ file
		String[] arrUser = str.split("\n");// Cắt từng dòng lưu vào mảng arrUser

		// Duyệt vòng for lấy ra các giá trị của User, add vào listUser
		for (int i = 0; i < arrUser.length; i++) {
			String[] arr = arrUser[i].split("-");
			String userName = arr[0];// Giá trị tên User
			int age = Integer.parseInt(arr[1]);// Giá trị tuổi
			String address = arr[2];// Giá trị địa chỉ
			double score = Double.parseDouble(arr[3]);// Giá trị điểm
			User user = new User(userName, age, address, score);
			listUser.add(user);
		}
		return listUser;
	}

	/**
	 * Description Phương thức phụ đọc dữ liệu từ file
	 * 
	 * @param len
	 * @return str Chuỗi đọc ra từ file
	 */
	private String readFile() {
		File file = new File(path);// Trỏ đến file cần đọc dữ liệu
		String str = "";
		try {
			FileInputStream fin = new FileInputStream(file);// Mở luồng
			byte buff[] = new byte[1024];
			int len = fin.read(buff);
			// Đọc dữ liệu từ file
			while (len > 0) {
				str += new String(buff, 0, len);
				len = fin.read(buff);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	/**
	 * Description Phương thức tính tổng điểm của tất cả các User
	 * @param listUser dữ liệu các user đọc từ file
	 * @param diem Tổng điểm
	 * @return
	 */
	public void tinhTongDiem() {
		List<User> listUser = TaoListData();
		double diem = 0;
		//Duyệt vòng for tính tổng tất cả điểm của các User
		for (int i = 0; i < listUser.size(); i++) {
			diem += listUser.get(i).getScore();
		}
		System.out.println("Tổng Điểm của tất cả các User là: " + diem);
	}

	/**
	 * Description Phương thức tìm các User theo tên
	 * @param listUser dữ liệu các user đọc từ file
	 * @return listUserKQ
	 */
	public List<User> timUserTheoTen(String ten) {
		List<User> listUser = TaoListData();
		List<User> listUserKQ = new ArrayList<>();
		//Duyệt vòng for lấy ra từng User
		for (int i = 0; i < listUser.size(); i++) {
			//Nếu tên trùng với tên của User thì add User vào listUserKQ
			if (ten.equalsIgnoreCase(listUser.get(i).getUserName())) {
				listUserKQ.add(listUser.get(i));
			}
		}
		return listUserKQ;
	}
}
