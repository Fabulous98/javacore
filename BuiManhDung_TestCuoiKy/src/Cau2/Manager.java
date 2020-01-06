/**
 * Copyright(C) 2020 LuvinaSoff
 * Manager.java, Jan 6, 2020, MDung
 */
package Cau2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.lang.*;

/**
 * Description
 * @author MDung
 */
public class Manager {
	public static void main(String[] args) {
		System.out.println("/************************************/");
		System.out.println("1. Add student");
		System.out.println("2. Edit student by id");
		System.out.println("3. Delete student by id");
		System.out.println("4. Sort student by gpa");
		System.out.println("5. Sort student by name");
		System.out.println("6. Show student");
		System.out.println("0. Exit");
		System.out.println("/************************************/");
		
		Manager ma = new Manager();
		
		Scanner scan = new Scanner(System.in);
		int chon;
		do {
		chon = scan.nextInt();
		
		switch (chon)
		{
		case 1:
			System.out.println("Nhap id: ");
			int _id = scan.nextInt();
			scan.nextLine();
			System.out.println("Nhap name: ");
			String _name = scan.nextLine();
			System.out.println("Nhap age: ");
			int _age = scan.nextInt();
			scan.nextLine();
			System.out.println("Nhap address: ");
			String _add = scan.nextLine();
			System.out.println("Nhap gpa: ");
			float _gpa = scan.nextFloat();
			scan.nextLine();
		   ma.addStudent(_id, _name, _age, _add, _gpa);
		   break;
		case 2:
			float _gpa2;
			int _id2;
			String _name2;
			int _age2;
			String _add2;
			float gpa2;
			System.out.println("Nhap id: ");
			
			_id2 = scan.nextInt();
			scan.nextLine();
			System.out.println("Nhap name: ");
			_name2 = scan.nextLine();
			System.out.println("Nhap age: ");
			_age2 = scan.nextInt();
			scan.nextLine();
			System.out.println("Nhap address: ");
			_add2 = scan.nextLine();
			System.out.println("Nhap gpa: ");
			gpa2 = scan.nextFloat();
			scan.nextLine();
			ma.editStudent(_id2, _name2, _age2, _add2,gpa2);
		   break;
		case 3:
			int _id3;
			System.out.println("Nhap id: ");
			_id3 = scan.nextInt();
			ma.deleteStudent(_id3);
		   break;
		case 4:
			ma.softUpByGpa();
		   break;
		case 5:
			ma.softUpByName();
		   break; 
		case 6:
			ma.show();
		   break;
		default:
			
		}
		}while (chon!=0);
		
	}
	
	private ArrayList<Students> list;
	
	public void menuStudent() {
		
	}
	

	private Comparator<Students> conditions = new Comparator<Students>() {

		@Override
		public int compare(Students user1, Students user2) {
			return user1.getUserName().compareTo(user2.getUserName());
		}
	};
	
	private Comparator<Students> conditions2 = new Comparator<Students>() {
		public int compare(Students user1, Students user2) {
			
			return (user1.getGpa() > user2.getGpa()) ? 1 : -1;
		
		}
	};

	public Manager() {
		super();
		list = new ArrayList<Students>();
	}



	public void addStudent(int id, String userName, int age, String address, float gpa) {
		Students user = new Students(id, userName, age, address, gpa);
		list.add(user);
		System.out.println("Đã thêm user thành công!");

	}

	
	public void editStudent(int id, String name, int age, String address, float gpa) {
		for (int i = 0; i < list.size(); i++) {
			if (id == list.get(i).getId()) {
				list.get(i).setUserName(name);
				list.get(i).setAge(age);
				list.get(i).setAddress(address);
				list.get(i).setGpa(gpa);
				break;
			}
		}

		System.out.println("Success!");
	}
	
	public void deleteStudent(int id) {
		for (int i = 0; i < list.size(); i++) {
			if (id == list.get(i).getId()) {
				list.remove(i);
				break;
			}
		}

		System.out.println("Success!");
	}

	
	public void softUpByName() {
		list.sort(conditions);
		System.out.println("Sap xep theo ten Success!");
	}

	public void softUpByGpa() {
		list.sort(conditions2);
		System.out.println("Sap xep theo Gpa Success!");
	}
	
	public void show() {
		System.out.println(list);
	}
}
