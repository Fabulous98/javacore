/**
 * Copyright(C) 2019 Luvina Software
 * TblDetailUserJapan.java, Dec 27, 2019, MDung
 */
package manageuser.entities;

/**
 * Class javabean chứa các thông tin TblDetailUserJapan
 * @author MDung
 *
 */
public class TblDetailUserJapan {
	// Khởi tạo biến detailUserJapanId id của chi tiết user
		private int detailUserJapanId;
		//Khởi tạo biến UserId là id của user
		private int UserId;
		//Khởi tạo biến là Mã level tiếng nhật
		private String codeLevel;
		// Khởi tạo biến startDate là Ngày cấp chứng chỉ
		private String startDate;
		// Khởi tạo biến  endDate là ngày hết hạn
		private String endDate;
		// Khởi tạo biến total là tổng điểm
		private String total;
		/**
		 * @return the detailUserJapanId
		 */
		public int getDetailUserJapanId() {
			return detailUserJapanId;
		}
		/**
		 * @param detailUserJapanId the detailUserJapanId to set
		 */
		public void setDetailUserJapanId(int detailUserJapanId) {
			this.detailUserJapanId = detailUserJapanId;
		}
		/**
		 * @return the userId
		 */
		public int getUserId() {
			return UserId;
		}
		/**
		 * @param userId the userId to set
		 */
		public void setUserId(int userId) {
			UserId = userId;
		}
		/**
		 * @return the codeLevel
		 */
		public String getCodeLevel() {
			return codeLevel;
		}
		/**
		 * @param codeLevel the codeLevel to set
		 */
		public void setCodeLevel(String codeLevel) {
			this.codeLevel = codeLevel;
		}
		/**
		 * @return the startDate
		 */
		public String getStartDate() {
			return startDate;
		}
		/**
		 * @param startDate the startDate to set
		 */
		public void setStartDate(String startDate) {
			this.startDate = startDate;
		}
		/**
		 * @return the endDate
		 */
		public String getEndDate() {
			return endDate;
		}
		/**
		 * @param endDate the endDate to set
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
		 * @param total the total to set
		 */
		public void setTotal(String total) {
			this.total = total;
		}
}
