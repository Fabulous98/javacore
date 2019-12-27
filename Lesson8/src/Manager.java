import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Copyright(C) 2019 LuvinaSoftware
 * Manager.java, Dec 27, 2019, MDung
 */

import java.util.ArrayList;

/**
 * Quan ly Employee
 * @author MDung
 */
public class Manager {
	private ArrayList<Employee> list;

public Manager() {
	list = new ArrayList<Employee>();
}

public void enterDetails() {
	Employee em = new Employee();
	Scanner scan = new Scanner(System.in);
	System.out.println("Nhap id: ");
	int Id = Integer.parseInt(scan.nextLine());
	em.setId(Id);
	System.out.println("Nhap name: ");
	String Name = scan.nextLine();
	em.setName(Name);
	System.out.println("Nhap Age: ");
	int Age = Integer.valueOf(scan.nextLine());
	em.setAge(Age);
	System.out.println("Nhap Salary: ");
	int Salary = Integer.valueOf(scan.nextLine());
	em.setSalary(Salary);
	System.out.println("Nhap Address: ");
	String Add = scan.nextLine();
	em.setAddress(Add);
	list.add(em);
	System.out.println("Add Employee Successful!\n");
}

public void printDetails() {
	System.out.println("Thong tin nhan vien: ");
	for (int i=0; i < list.size(); i++) {
	System.out.println("Id: " + list.get(i).getId());
	System.out.println("Name: " + list.get(i).getName());
	System.out.println("Age: " + list.get(i).getAge());
	System.out.println("Salary: " + list.get(i).getSalary());
	System.out.println("Address: " + list.get(i).getAddress() + "\n");
	}
}

public void writeEmployeeFile(String path) {
	String text = "";
	for (int i = 0; i < list.size(); i++) {
		text += list.get(i).getId() + " " + list.get(i).getName() + " " + list.get(i).getAge() + " " + list.get(i).getSalary() + " " +  list.get(i).getAddress() + "\r\n";
	}

	try {
		
		File file = new File(path);
		file.createNewFile();
	    FileWriter writer = new FileWriter(file); 
	      // ghi noi dung vao file
	      writer.write(text); 
	      writer.flush();
	      writer.close();
	      System.out.println("Đã ghi thành công!!!!");

	} catch (IOException e) {
		e.printStackTrace();
	}
}
}
