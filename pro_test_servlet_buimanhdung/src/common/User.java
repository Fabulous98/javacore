/**
 * Copyright(C) 2020 LuvinaSoftware
 * User.java, Feb 18, 2020, MDung
 */
package common;

/**
 * Class User
 * 
 * @author MDung
 */
public class User {
	protected int id;
	protected String name;
	protected String birthday;
	protected String birthplace;

	public User() {
		super();
	}

	public User(int id, String name, String birthday, String birthplace) {
		super();
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.birthplace = birthplace;
	}

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the birthplace
	 */
	public String getBirthplace() {
		return birthplace;
	}

	/**
	 * @param birthplace the birthplace to set
	 */
	public void setBirthplace(String birthplace) {
		this.birthplace = birthplace;
	}

}
