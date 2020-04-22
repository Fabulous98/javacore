/**
 * Copyright(C) 2019 Luvina Software
 * MstGroup.java, Dec 27, 2019, MDung
 */
package manageuser.entities;

/**
 * Class javabean chứa các thông tin MstGroup
 * @author MDung
 *
 */
public class MstGroup {
	// Khởi tạo biến groupId là mã nhóm
		private int groupId;
		// Khởi tạo biến groupName là tên nhóm
		private String groupName;

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

}
