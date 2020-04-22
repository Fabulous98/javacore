/**
 * Copyright(C) 2019 Luvina Software
 * TblUser.java, Dec 27, 2019, MDung
 */
package manageuser.entities;

/**
 * @author MDung
 *
 */
public class TblUser {
	// Khởi tạo biến userId là user ID
		private int userId;
		//Khởi tạo biến groupId là mã nhóm
		private int groupId;
		// Khởi tạo biến loginName là tên đăng nhập
		private String loginName;
		// Khởi tạo biến password là mật khẩu
		private String password;
		// Khởi tạo biến fullName là họ tên user 
		private String fullName;
		//Khởi tạo biến fullNameKana là họ tên kana của user
		private String fullNameKana;
		//Khởi tạo biến email là địa chỉ mail của user
		private String email;
		//Khởi tạo biến tel là số điện thoại của user
		private String tel;
		// Khởi tạo biến birthday là ngày sinh của user
		private String birthday;
		//Khởi tạo biến rule là phân quyền cho user 0 là admin và 1 là user
		private int rule;
		//Khởi tạo biến salt là thời gian hiện tại tính đến minisecond
		private String salt;

		/**
		 * @return the userId
		 */
		public int getUserId() {
			return userId;
		}

		/**
		 * @param userId
		 *            the userId to set
		 */
		public void setUserId(int userId) {
			this.userId = userId;
		}

		/**
		 * @return the groupId
		 */
		public int getGroupId() {
			return groupId;
		}

		/**
		 * @param groupId
		 *            the groupId to set
		 */
		public void setGroupId(int groupId) {
			this.groupId = groupId;
		}

		/**
		 * @return the loginName
		 */
		public String getLoginName() {
			return loginName;
		}

		/**
		 * @param loginName
		 *            the loginName to set
		 */
		public void setLoginName(String loginName) {
			this.loginName = loginName;
		}

		/**
		 * @return the password
		 */
		public String getPassword() {
			return password;
		}

		/**
		 * @param password
		 *            the password to set
		 */
		public void setPassword(String password) {
			this.password = password;
		}

		/**
		 * @return the fullName
		 */
		public String getFullName() {
			return fullName;
		}

		/**
		 * @param fullName
		 *            the fullName to set
		 */
		public void setFullName(String fullName) {
			this.fullName = fullName;
		}

		/**
		 * @return the fullNameKana
		 */
		public String getFullNameKana() {
			return fullNameKana;
		}

		/**
		 * @param fullNameKana
		 *            the fullNameKana to set
		 */
		public void setFullNameKana(String fullNameKana) {
			this.fullNameKana = fullNameKana;
		}

		/**
		 * @return the email
		 */
		public String getEmail() {
			return email;
		}

		/**
		 * @param email
		 *            the email to set
		 */
		public void setEmail(String email) {
			this.email = email;
		}

		/**
		 * @return the tel
		 */
		public String getTel() {
			return tel;
		}

		/**
		 * @param tel
		 *            the tel to set
		 */
		public void setTel(String tel) {
			this.tel = tel;
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
		 * @return the rule
		 */
		public int getRule() {
			return rule;
		}

		/**
		 * @param rule
		 *            the rule to set
		 */
		public void setRule(int rule) {
			this.rule = rule;
		}

		/**
		 * @return the salt
		 */
		public String getSalt() {
			return salt;
		}

		/**
		 * @param salt
		 *            the salt to set
		 */
		public void setSalt(String salt) {
			this.salt = salt;
		}
}
