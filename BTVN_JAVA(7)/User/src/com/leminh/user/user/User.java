/**
 * Copy(C) 2019 Luvina SoftWare Company
 * User.java Dec 25, 2019 2019 Minh
 * 
 */
package com.leminh.user.user;

/**
 * Xây dựng đối tượng user gồm các thuộc tính userName, age, address, score
 * 
 * @author Minh
 *
 */
public class User {
	private String userName, address;
	private int age;
	private double score;

	/**
	 * Phương thức khởi tao user
	 * 
	 * @param userName
	 * @param age
	 * @param address
	 * @param score
	 */
	public User(String userName, int age, String address, double score) {
		super();
		this.userName = userName;
		this.age = age;
		this.address = address;
		this.score = score;
	}

	/**
	 * Lấy tên của user
	 * 
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Lấy điểm của user
	 * 
	 * @return the score
	 */
	public double getScore() {
		return score;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return "Tên: " + userName + "\n" + "Tuổi: " + age + "\n" + "Địa chỉ: " + address + "\n" + "Điểm: " + score
				+ "\n";
	}

}
