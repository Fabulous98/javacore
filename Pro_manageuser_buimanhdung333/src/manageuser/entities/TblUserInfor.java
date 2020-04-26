/**
 * Copyright(C) 2019 Luvina Software
 * TblUserInfor.java, Dec 27, 2019, MDung
 */
package manageuser.entities;

/**
 * Class javabean chứa các thông tin TblUserInfor
 * @author MDung
 *
 */
public class TblUserInfor {
	// Khai báo biến groupId
		private int groupId;
		// khai báo biến codeLevel : trình độ tiếng nhật
		private String codeLevel;
		// Khai báo id của user
		private int userId;
		// Khai báo tên đăng nhập
		private String loginName;
		// khai báo fullName của user
		private String fullName;
		// Khai báo tên kana
		private String fullNameKana;
		// khai báo ngày sinh
		private String birthday;
		// Khai báo nhóm
		private String groupName;
		// Khai báo email
		private String email;
		// Khai báo sdt
		private String tel;
		// Khai báo tên trình độ tiếng Nhật
		private String nameLevel;
		// Khai báo ngày hết hạn
		private String endDate;
		// Khai báo điểm
		private String total;
		// Khai báo ngày bắt đầu
		private String startDay;
		// Khai báo pass
		private String pass;
		// Khai báo confirmpass
		private String confirmPass;
		//Khởi tạo biến gender là giới tính
		private String gender="";

		/**
		 * @return the gender
		 */
		public String getGender() {
			return gender;
		}

		/**
		 * @param gender the gender to set
		 */
		public void setGender(String gender) {
			this.gender = gender;
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
		 * @return the pass
		 */
		public String getPass() {
			return pass;
		}

		/**
		 * @param pass
		 *            the pass to set
		 */
		public void setPass(String pass) {
			this.pass = pass;
		}

		/**
		 * @return the confirmPass
		 */
		public String getConfirmPass() {
			return confirmPass;
		}

		/**
		 * @param confirmPass
		 *            the confirmPass to set
		 */
		public void setConfirmPass(String confirmPass) {
			this.confirmPass = confirmPass;
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
		 * @return the codeLevel
		 */
		public String getCodeLevel() {
			return codeLevel;
		}

		/**
		 * @param codeLevel
		 *            the codeLevel to set
		 */
		public void setCodeLevel(String codeLevel) {
			this.codeLevel = codeLevel;
		}

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
		 * @return the birthday
		 */
		public String getBirthday() {
			return birthday;
		}

		/**
		 * @param birthday
		 *            the birthday to set
		 */
		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}

		/**
		 * @return the groupName
		 */
		public String getGroupName() {
			return groupName;
		}

		/**
		 * @param groupName
		 *            the groupName to set
		 */
		public void setGroupName(String groupName) {
			this.groupName = groupName;
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
		 * @return the nameLevel
		 */
		public String getNameLevel() {
			return nameLevel;
		}

		/**
		 * @param nameLevel
		 *            the nameLevel to set
		 */
		public void setNameLevel(String nameLevel) {
			this.nameLevel = nameLevel;
		}

		/**
		 * @return the endDate
		 */
		public String getEndDate() {
			return endDate;
		}

		/**
		 * @param endDate
		 *            the endDate to set
		 */
		public void setEndDate(String endDate) {
			this.endDate = endDate;
		}

		/**
		 * @return the total
		 */
		public String getTotal() {
			return total;
		}

		/**
		 * @param total
		 *            the total to set
		 */
		public void setTotal(String total) {
			this.total = total;
		}

		/**
		 * @return the startDay
		 */
		public String getStartDay() {
			return startDay;
		}

		/**
		 * @param startDay
		 *            the startDay to set
		 */
		public void setStartDay(String startDay) {
			this.startDay = startDay;
		}

}
