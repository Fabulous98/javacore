/**
 * Copyright(C) 2019 LuvinaSoftware
 * User.java Dec 17, 2019 Cong_PV
 */
package com.luvina.user.user;

/**
 * @author admin
 * Description Tao User với thông tin userName, address, age,
 *         score
 */
public class User {
	private String userName, address;
	private int age;
	private double score;

	public User(String userName, int age, String address, double score) {
		this.userName = userName;
		this.age = age;
		this.address = address;
		this.score = score;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}
	
	/**
	 * @return the score
	 */
	public double getScore() {
		return score;
	}

	@Override
	public String toString() {
		return userName + "-" + age + "-" + address + "-" + score;
	}
}
