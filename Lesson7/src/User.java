/**
 * Copyright(C) 2019 LuvinaSoff
 * User.java, Dec 26, 2019, MDung
 */

/**
 * Lop user luu thong tin user
 * @author MDung
 */
public class User {
	private String userName, address;
	private int age;
	private double score;

	/**
	 * Constructor khởi tao user
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
	 * Lấy username
	 * 
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Lấy userscore
	 * 
	 * @return the score
	 */
	public double getScore() {
		return score;
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
		return "Tên: " + userName + "\n" + "Tuổi: " + age + "\n" + "Địa chỉ: " + address + "\n" + "Điểm: " + score + "\n";
	}

}
