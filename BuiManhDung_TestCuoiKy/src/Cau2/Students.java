/**
 * Copyright(C) 2020 LuvinaSoff
 * Students.java, Jan 6, 2020, MDung
 */
package Cau2;

/**
 * Description
 * @author MDung
 */
public class Students {
	private String userName, address;
	private int age;
	private float gpa;
	private int id;

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Constructor khởi tao user
	 * 
	 * @param userName
	 * @param age
	 * @param address
	 * @param gpa
	 */
	public Students(int id, String userName, int age, String address, float gpa) {
		super();
		this.id = id;
		this.userName = userName;
		this.age = age;
		this.address = address;
		this.gpa = gpa;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}

	/**
	 * @param gpa the gpa to set
	 */
	public void setGpa(float gpa) {
		this.gpa = gpa;
	}

	/**
	 * Lấy username
	 * 
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Lấy usergpa
	 * 
	 * @return the gpa
	 */
	public float getGpa() {
		return gpa;
	}

	/**
	 * @return age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @return address
	 */
	public String getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return "Tên: " + userName + "\n" + "Tuổi: " + age + "\n" + "Địa chỉ: " + address + "\n" + "GPA: " + gpa + "\n";
	}
}
