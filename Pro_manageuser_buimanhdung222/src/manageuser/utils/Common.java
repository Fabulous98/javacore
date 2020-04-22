/**
 * Copyright(C) 2019 Luvina Software
 * Common.java, Dec 27, 2019, MDung
 */
package manageuser.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import manageuser.entities.MstGroup;
import manageuser.entities.MstJapan;
import manageuser.entities.TblDetailUserJapan;
import manageuser.entities.TblUser;
import manageuser.entities.TblUserInfor;
import manageuser.logics.MstGroupLogic;
import manageuser.logics.MstJapanLogic;
import manageuser.logics.impl.MstGroupLogicImpl;
import manageuser.logics.impl.MstJapanLogicImpl;
import manageuser.logics.impl.TblUserLogicImpl;
import manageuser.properties.ConfigProperties;

/**
 * @author MDung
 *
 */
public class Common {
	// Khởi tạo columSort chứa các cột của tblUserInfor
		private static List<String> columnSort = new ArrayList<>();

		/**
		 * Mã hóa password theo SHA-1
		 * 
		 * @param password
		 *            là mật khẩu cần mã hóa
		 * @param salt
		 *            giá trị thêm vào mật khấu để mã hóa
		 * @return mật khẩu sau khi đã được mã hóa
		 * @throws NoSuchAlgorithmException
		 * @throws UnsupportedEncodingException
		 */
		public static String hashEncrypt(String password, String salt)
				throws NoSuchAlgorithmException, UnsupportedEncodingException {
			// Khởi tạo chuỗi sha là password + salt
			String sha = password + salt;
			// Khởi tạo đối tượng MessageDigest thực hiện thuật toán mã hóa SHA-1
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			// đặt lại digest cho lần khác
			crypt.reset();
			// cập nhật sử dụng mảng được chỉ định
			crypt.update(sha.getBytes("UTF-8"));
			// hàm tính toán băm trả về kết quả là 1 mảng byte
			byte[] digest = crypt.digest();
			// Khởi tạo đối tượng BigInteger trả về kết quả dương
			BigInteger bigInt = new BigInteger(1, digest);
			String hashtext = bigInt.toString(16);
			// trả về kết quả của hàm băm SHA 256 kết quả trả về là chuỗi 16 kí tự
			return hashtext;
		}

		/**
		 * Hàm kiểm tra xem 2 chuỗi string có giống nhau hay không
		 * 
		 * @param str1
		 *            chuỗi cần so sánh
		 * @param str2
		 *            chuỗi so sánh
		 * @return true nếu 2 chuỗi giống nhau, false nếu hai chuỗi khác nhau
		 */
		public static boolean compareString(String str1, String str2) {
			// Khởi tạo một biến kiểm tra và gán giá trị ban đầu là false
			boolean check = false;
			// Nếu hai chuỗi giống nhau
			if (str1.equals(str2)) {
				// gán lại biến check = true
				check = true;
			}
			// trả về true nếu hai chuỗi giống nhau, false nếu hai chuỗi khác nhau
			return check;
		}

		/**
		 * Kiểm tra login
		 * 
		 * @param session
		 *            session lấy về
		 * @return true nếu login đúng false nếu sai
		 * @throws ClassNotFoundException
		 * @throws SQLException
		 */
		public static boolean checkLogin(HttpSession session) throws ClassNotFoundException, SQLException {
			boolean existUser = false;
			try {
				// Nếu sesion khác null
				if (session != null) {
					// Gán giá trị cho biến loginName là session login
					String loginName = (String) session.getAttribute(Constant.SESSION_LOGIN);
					// Nếu session login khác rỗng ( đã đăng nhập)
					if (loginName != null && !loginName.isEmpty()) {
						TblUserLogicImpl tblUserLogic = new TblUserLogicImpl();
						// Kiểm tra login có phải là admin không
						if (tblUserLogic.checkLogin(loginName)) {
							// gán lại biến existUser là true
							existUser = true;
						}
					}
				}
			} catch (SQLException | ClassNotFoundException e) {
				// Ghi log và ném ngoại lệ
				System.out.println("Class: Common" + " Method: " + e.getStackTrace()[0].getMethodName() + ", Error: "
						+ e.getMessage());
				throw e;
			}
			// Trả về kết quả true nếu đã đăng nhập và false nếu chưa
			return existUser;
		}

		/**
		 * ham chuyen cac ki tu dac biet thanh cac chuoi
		 * 
		 * @param value
		 *            kí tự cần validate
		 * @return Kí tự sau khi validate
		 */
		public static String validateWildCard(String value) {
			return value.replace("/", "!/").replace("%", "!%").replace("_", "!_").replace(";", "!;");
		}

		/**
		 * Hàm chuyển chuỗi thành số
		 * 
		 * @param number
		 *            chuỗi cần chuyển
		 * @param defaultValue
		 *            giá trị default nếu chuỗi truyền vào không phải số
		 * @return số sau khi ép kiểu nếu chuỗi truyền vào là số, giá trị default
		 *         nếu chuỗi truyền vào không phải số
		 */
		public static int convertStringToInt(String number, int defaultValue) {
			int value;
			try {
				// Trả về 1 số
				value = Integer.parseInt(number);
				// lấy trị tuyệt đối nếu là số âm
				if (value <= 0) {
					value = defaultValue;
				}
			} catch (NumberFormatException e) {
				// nếu nhập vào là chuối không phải là số thì trả về 1 số mặc định
				value = defaultValue;
			}
			return value;
		}

		/**
		 * Kiểm tra giá trị sắp xếp có phải là ASC và DESC không
		 * 
		 * @param valueOder
		 *            giá trị sắp xếp truyền vào
		 * @return giá trị ASC hoặc DESC nếu giá trị truyền vào đúng nếu không trả
		 *         về null
		 */
		public static String getSort(String valueOder) {
			// tạo một list string chứa giá trị để sắp xếp
			List<String> orderByModes = new ArrayList<>();
			orderByModes.add("ASC");
			orderByModes.add("DESC");
			String result;
			// kiểm tra nếu giá trị truyền vào khác rỗng và giống với lệnh sắp xếp
			// hay không
			if (valueOder != null && orderByModes.contains(valueOder.toUpperCase())) {
				result = valueOder.toUpperCase();
			} else {
				result = null;
			}
			return result;
		}

		/**
		 * Hàm trả về một trường cần sắp xếp nếu trường ấy đúng trong cơ sở dữ liệu
		 * nếu trường nhập vào không đúng với trường nào thuộc cơ sở dữ liệu thì trả
		 * về null
		 * 
		 * @param sortField
		 *            trường muốn sắp xếp
		 * @return
		 * @throws ClassNotFoundException
		 */
		public static String getColumeSort(String sortField) throws ClassNotFoundException {
			// Thực hiện 1 lần và lấy ra toàn bộ mảng column cần order và add vào
			// whitelist
			try {
				if (columnSort.size() == 0) {
					String[] arrTableName = { "TblUserInfor" };
					// Lấy ra toàn bộ các column (field) BO cần order by
					for (String tableName : arrTableName) {
						Class<?> cls = Class.forName("manageuser.entities." + tableName);
						Field[] fieldArray = cls.getDeclaredFields();
						for (int i = 0; i < fieldArray.length; i++) {
							String fieldName = fieldArray[i].getName();
							// add các column vào 1 mảng
							columnSort.add(fieldName);
						}
					}
				}
				// Cắt ký tự "-" ở đầu field sort
				String sort = sortField;
				if (sort != null && sort.startsWith("-")) {
					sort = sort.substring(1);
				}
				// Kiểm tra field cần order by có nằm trong danh sách field cho phép
				// sort hay không
				if (sort != null && columnSort.contains(sort)) {
					return sort;
				}
				return null;
			} catch (ClassNotFoundException e) {
				System.out.println("Class: Common" + " Method: " + e.getStackTrace()[0].getMethodName() + ", Error: "
						+ e.getMessage());
				throw e;
			}
		}

		/**
		 * Lấy vị trí data cần lấy
		 * 
		 * @param currentPage
		 *            Trang hiện tại
		 * @param limit
		 *            số lượng cần hiển thị trên 1 trang
		 * @return vị trí offset cần lấy
		 */
		public static int getOffset(int currentPage, int limit) {
			// Khởi tạo và gán vị trí data là 0
			int offset = 0;
			// Nếu page hiện tại lớn hơn 0
			if (currentPage > 0) {
				// vị trí cần lấy = số record*( page hiện tại - 1)
				offset = limit * (currentPage - 1);
			}
			// Trả về vị trí cần lấy
			return offset;
		}

		/**
		 * Hàm lấy số lượng hiển thị bản ghi trên 1 trang
		 * 
		 * @return số lượng record cần lấy
		 */
		public static int getLimit() {
			// lấy số record từ file properties
			int limit = convertStringToInt(ConfigProperties.getValueByKey("LIMIT_RECORD"), Constant.LIMIT_DEFAULT);
			// trả về số record
			return limit;
		}

		/**
		 * Hàm lấy số lượng hiển thị bản ghi trên 1 trang
		 * 
		 * @return số lượng record cần lấy
		 */
		public static int getLimitPage() {
			// lấy số limitPage từ file properties
			int limitPage = convertStringToInt(ConfigProperties.getValueByKey("LIMIT_PAGE"), Constant.DELAULT_LIMIT_PAGE);
			// trả về số page hiển thị
			return limitPage;
		}

		/**
		 * Tính tổng số trang
		 * 
		 * @param totalUser
		 *            tổng số user
		 * @param limit
		 *            số record/page
		 * @return tổng số trang cần hiển thị
		 */
		public static int getTotalPage(int totalUser, int limit) {
			// lấy về số trang đúng kiểu double
			double totalPage = (double) totalUser / limit;
			// ép kiểu về int đồng thời giá trị trả về lớn hơn hoặc bằng kết quả
			int getTotalPage = (int) Math.ceil(totalPage);
			// trả về số page cần hiển thị
			return getTotalPage;
		}

		/**
		 * phương thức hiển thị ở chuỗi paging theo trang hiện tại
		 * 
		 * @param totalUser
		 *            tổng sô user
		 * @param limit
		 *            số lượng cần hiển thị trên 1 trang
		 * @param currentPage
		 *            trang hiện tại
		 */
		public static List<Integer> getListPaging(int totalUser, int limit, int currentPage) {
			int limitPage = getLimitPage();
			// tính số group hiện tại
			int currentGroup = getCurrentGroup(currentPage, limitPage);
			// lấy giá trị nhor nhất trong group
			int minGroup = getMinCurrentGroup(currentGroup, limitPage);
			// Lấy giá trị lớn nhất trong group
			int maxGroup = getMaxCurrentGroup(currentGroup, limitPage);
			// Tính toán tổng số page
			int totalPage = getTotalPage(totalUser, limit);
			// Khởi tạo đối tượng liIntegers froup để chứa listpagging
			List<Integer> listPaging = new ArrayList<Integer>();
			// Thêm vào listPaging
			if (currentPage <= totalPage) {
				for (int i = minGroup; i <= maxGroup; i++) {
					if (i > totalPage || i < 0) {
						break;
					}
					listPaging.add(i);
				}
			}
			// trả về listPaging
			return listPaging;
		}

		/**
		 * Tính nhóm hiện tại
		 * 
		 * @param curentPage
		 *            page hiện tại
		 * @param limitPage
		 *            số page hiển thị
		 * @return nhóm hiện tại
		 */
		public static int getCurrentGroup(int curentPage, int limitPage) {
			int currentGroup = (int) Math.ceil((double) curentPage / limitPage);
			return currentGroup;
		}

		/**
		 * Tìm số page min của nhóm hiện tại
		 * 
		 * @param currentGroup
		 *            nhóm hiện tại
		 * @param limitPage
		 *            số page hiển thị
		 * @return số page min của nhóm
		 */
		public static int getMinCurrentGroup(int currentGroup, int limitPage) {
			int minCurrentGroup = (currentGroup - 1) * limitPage + 1;
			return minCurrentGroup;

		}

		/**
		 * Tìm số page max của nhóm hiện tại
		 * 
		 * @param currentGroup
		 *            nhóm hiện tại
		 * @param limitPage
		 *            số page hiển thị
		 * @return page lớn nhất trong nhóm đang hiển thị
		 */
		public static int getMaxCurrentGroup(int currentGroup, int limitPage) {
			int maxCurrentGroup = currentGroup * limitPage;
			return maxCurrentGroup;
		}

		/**
		 * Lấy danh sách tháng
		 * 
		 * @return danh sách các tháng
		 */
		public static List<Integer> getListMonth() {
			// Khởi tạo danh sách chứa các tháng
			List<Integer> listMonth = new ArrayList<Integer>();
			// chạy i từ 1 đến 12
			for (int i = 1; i <= 12; i++) {
				// thêm i vào danh sách
				listMonth.add(i);
			}
			// trả về danh sách các tháng
			return listMonth;
		}

		/**
		 * Lấy danh sách các ngày
		 * 
		 * @return danh sách ngày
		 */
		public static List<Integer> getListDay() {
			// Khởi tạo listDay chứa danh sách các ngày
			List<Integer> listDay = new ArrayList<Integer>();
			// Chạy i từ 1 đến 31
			for (int i = 1; i <= 31; i++) {
				// Thêm i vào list
				listDay.add(i);
			}
			// trả về danh sách các ngày
			return listDay;
		}

		/**
		 * Lấy danh sách năm hiển thị
		 * 
		 * @param fromYear
		 *            năm bắt đầu
		 * @param toYear
		 *            năm kết thúc
		 * @return danh sách năm
		 */
		public static List<Integer> getListYear(int fromYear, int toYear) {
			// Khởi tạo listDay chứa danh sách các ngày
			List<Integer> listYear = new ArrayList<Integer>();
			// Chạy i từ 1 đến 31
			for (int i = fromYear; i <= toYear; i++) {
				// Thêm i vào list
				listYear.add(i);
			}
			// trả về danh sách các ngày
			return listYear;
		}

		/**
		 * Hàm lấy năm hiện tại
		 * 
		 * @return số năm hiện tại
		 */
		public static int getYearNow() {
			Calendar calendar = Calendar.getInstance();
			int nowYear = calendar.get(Calendar.YEAR);
			return nowYear;
		}

		/**
		 * Hàm lấy về thời gian hiện tại tính đến milisecond
		 * 
		 * @return Chuỗi thời gian milisecond
		 */
		public static String getKey() {
			// Khởi tạo biến salt
			String salt = "";
			// Lấy về thời gian hiện tại theo milisecond kiểu long
			long currentTime = System.currentTimeMillis();
			// Ép kiểu nó về kiểu String
			salt = String.valueOf(currentTime);
			// Trả về biến salt
			return salt;
		}

		/**
		 * Phương thức chuyển sang chuối string
		 * 
		 * @param year
		 *            năm nhập vào
		 * @param month
		 *            tháng nhập vào
		 * @param day
		 *            ngày nhập vào
		 * @return string trả về một chuỗi String
		 */
		public static String convertIntToTime(int year, int month, int day) {
			// Khởi tạo chuỗi String
			// Chuyển thành kiểu có định dạng yyyy-MM-đ
			String string = Integer.toString(year) + "/" + Integer.toString(month) + "/" + Integer.toString(day);
			return string;

		}

		/**
		 * Phương thức kiểm tra chuổi nhập vào không rỗng
		 * 
		 * @param value
		 *            giá trị nhập vào
		 * @return true là chuỗi rỗng, false là chuỗi không rỗng
		 */
		public static boolean checkIsEmpty(String value) {
			boolean check = false;
			// Nếu chuỗi rỗng
			if (value == null || value.trim().isEmpty()) {
				// Trả về true
				check = true;
			}
			// ngược lại trả về false
			return check;
		}

		/**
		 * Phương thức kiểm tra giá trị max của chuỗi nhập vào
		 * 
		 * @param value
		 *            chuỗi nhập vào, giá trị tối đa của chuỗi
		 * @return true là chuỗi vượt quá maxlenth, false là chuỗi không vượt quá
		 *         maxlenth
		 */
		public static boolean checkMaxLength(String value, int max) {
			// Gán biến check bằng flase
			boolean check = false;
			if (value.length() > max) {
				// Nếu giá trị nhập vào vượt quá số kí tự
				// Gán biến check bằng true
				check = true;
			}
			// trả về false
			return check;
		}

		/**
		 * Kiểm trả xem chuỗi input có phải 1 byte không
		 * 
		 * @param input
		 *            chuỗi đầu vào
		 * @return true nếu là ký tự 1 byte false nếu không là ký tự 1 byte
		 * @throws UnsupportedEncodingException
		 */
		public static boolean checkOneByte(String input) throws UnsupportedEncodingException {
			boolean check = false;
			int stringLength = input.length();
			int byteLength = input.getBytes("ASCII").length;
			if (stringLength == byteLength) {
				check = true;
			}
			return check;
		}

		/**
		 * Phương thức check độ dài trong khoảng
		 * 
		 * @param value
		 *            giá trị chuỗi nhập vào
		 * @param min
		 *            giá trị bắt đầu trong khoảng
		 * @param max
		 *            giá trị kết thúc trong khoảng
		 * @return true chuỗi nằm ngoài khoảng, false giá trị nằm trong khoảng
		 */
		public static boolean checkLength(String value, int min, int max) {
			boolean check = false;
			// Nếu vượt quá trong khoảng
			if (value.length() < min || value.length() > max) {
				// Gán biến check bằng true
				check = true;
			}
			// Ngược lại biến check bằng false
			return check;
		}

		/**
		 * Phương thức kiểm tra formmat
		 *
		 * @param input
		 *            chuỗi nhập vào
		 * @param value
		 *            kiểu cần format
		 * @return false là sai kiểu nhập vào , true là đúng kiểu nhập vào
		 */
		public static boolean checkFormat(String input, String format) {
			boolean check = false;
			check = input.matches(format);
			return check;
		}

		/**
		 * Phương thức kiểm tra ngày nhập vào có đúng hay không
		 * 
		 * @param input
		 *            Ngày nhập vào
		 * @return trả về true, false
		 */
		public static boolean isValidateDate(String input) {
			boolean checkValidate = false;
			try {
				DateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");
				// Set false để kiểm tra tính hợp lệ của date
				simpleDateFormat.setLenient(false);
				// Parse String thành kiểu date
				simpleDateFormat.parse(input);
				// gán lại biến check = true
				checkValidate = true;
				// nếu có ngoại lệ xảy ra
			} catch (ParseException e) {
				// ghi log và xử lí
				System.out.println(
						"Class: Common" + "Method: " + e.getStackTrace()[0].getMethodName() + ", Error: " + e.getMessage());
				checkValidate = false;
			}
			return checkValidate;
		}

		/**
		 * Phương thức chuyển từ chuỗi thành ngày
		 * 
		 * @param strDate
		 *            Chuỗi nhập vào
		 * @return trả về ngày tháng năm
		 * @throws ParseException
		 *             ngoại lệ
		 */
		public static Date toDate(String dateInput) throws ParseException {
			// Khởi tạo kiểu date
			Date date = new Date();
			try {
				if (dateInput != null) {
					// parse chuỗi ngày String sang kiểu Date
					date = new SimpleDateFormat("yyyy/MM/dd").parse(dateInput);
				}
			} catch (ParseException e) {
				System.out.println("Class: Common" + " Method: " + e.getStackTrace()[0].getMethodName() + ", Error: "
						+ e.getMessage());
				// Ném ngoại lệ
				throw e;
			}
			// Trả về 1 ngày theo định dạng yyyy/MM/dd
			return date;
		}

		/**
		 * Thêm lỗi vào danh sách
		 * 
		 * @param list
		 *            danh sách muốn thêm lỗi
		 * @param err
		 *            lỗi muốn thêm
		 * @return danh sách lỗi
		 */
		public static List<String> addListError(List<String> list, String err) {
			// nếu chuỗi lỗi là khác rỗng thì thêm lỗi vào list
			if (!Constant.DEFAULT_STRING.equals(err) && err != null) {
				list.add(err);
			}
			return list;
		}

		/**
		 * Hàm tạo một đối tượng tblUser từ đối tượng tblUserInfor
		 * 
		 * @param tblUserInfor
		 *            đối tượng tblUserInfor
		 * @return một đối tượng tblUser
		 * @throws NoSuchAlgorithmException
		 * @throws UnsupportedEncodingException
		 */
		public static TblUser createUser(TblUserInfor tblUserInfor)
				throws NoSuchAlgorithmException, UnsupportedEncodingException {
			// Khởi tạo đối tượng tblUser
			TblUser tblUser = new TblUser();
			try {
				// Set các thuộc tính cho đối tượng
				tblUser.setLoginName(tblUserInfor.getLoginName());
				tblUser.setGroupId(tblUserInfor.getGroupId());
				tblUser.setFullName(tblUserInfor.getFullName());
				tblUser.setFullNameKana(tblUserInfor.getFullNameKana());
				tblUser.setBirthday(tblUserInfor.getBirthday());
				tblUser.setEmail(tblUserInfor.getEmail());
				tblUser.setTel(tblUserInfor.getTel());
				// Gắn giá trị cho pass
				tblUser.setPassword(Common.hashEncrypt(tblUserInfor.getPass(), Common.getKey()));
				tblUser.setSalt(Common.getKey());
				tblUser.setUserId(tblUserInfor.getUserId());

			} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
				// ghi log và ném ngoại lệ
				System.out.println("Class: Common" + " Method: " + e.getStackTrace()[0].getMethodName() + ", Error: "
						+ e.getMessage());
				throw e;
			}
			return tblUser;
		}

		/**
		 * Hàm tạo một đối tượng TblDetailUserJapan từ đối tượng tblUserInfor
		 * 
		 * @param tblUserInfor
		 *            đối tượng tblUserInfor
		 * @return trả về một đối tượng TblDetailUserJapan
		 */
		public static TblDetailUserJapan createDetailUser(TblUserInfor tblUserInfor) {
			// Khởi tạo đối tượng tblUser
			TblDetailUserJapan tblDetail = new TblDetailUserJapan();
			// Sét các thuộc tính cho đối tượng
			tblDetail.setCodeLevel(tblUserInfor.getCodeLevel());
			// Set giá trị cho ngày bắt đầu
			tblDetail.setStartDate(tblUserInfor.getStartDay());
			// set giá trị cho ngày kết thúc
			tblDetail.setEndDate(tblUserInfor.getEndDate());
			// Set giá trị cho điểm
			tblDetail.setTotal(tblUserInfor.getTotal());
			// Trả về đối tượng TblDetailUserJapan
			return tblDetail;
		}

		/**
		 * Thực hiện set giá trị cho các hạng mục selectbox ở màn hình ADM003
		 * 
		 * @param request
		 *            set giá trị lên req
		 * @throws ClassNotFoundException
		 * @throws SQLException
		 */
		public static void setDataLogic(HttpServletRequest request) throws ClassNotFoundException, SQLException {
			try { // Khởi tạo đối tượng MstJapanLogic
				MstJapanLogic mstJapanLogic = new MstJapanLogicImpl();
				// Khởi tạo đối tượng
				MstGroupLogic mstGroupLogic = new MstGroupLogicImpl();
				// Khởi tạo 1 listMstJapan để chứa các nhóm
				List<MstJapan> listMstJapan = mstJapanLogic.getAllMstJapan();
				// Khởi tạo 1 listMstGroup để chứa các nhóm
				List<MstGroup> listMstGroup = mstGroupLogic.getAllMstGroup();
				// Khởi tạo listDate để lấy giá trị trong list để gắn lên request
				List<Integer> listDate = Common.getListDay();
				//// Khởi tạo listMonth để lấy giá trị trong list để gắn lên request
				List<Integer> listMonth = Common.getListMonth();
				//// Khởi tạo listYear để lấy giá trị trong list để gắn lên request
				List<Integer> listYear = Common.getListYear(Constant.YEARS_START, Common.getYearNow() + 1);

				// Set các list lên request để hiển thị trên selectbox của ADM003
				request.setAttribute(Constant.LIST_MSTJAPAN, listMstJapan);
				request.setAttribute(Constant.LIST_MSTGROUP, listMstGroup);
				request.setAttribute(Constant.LIST_DATE, listDate);
				request.setAttribute(Constant.LIST_MONTH, listMonth);
				request.setAttribute(Constant.LIST_YEAR, listYear);
			} catch (ClassNotFoundException | SQLException e) {
				System.out.println("Class: Common" + " Method: " + e.getStackTrace()[0].getMethodName() + ", Error: "
						+ e.getMessage());
				// Ném ngoại
				throw e;
			}
		}

		/**
		 * Kiểm tra userinfor có codeLevel hay không
		 * 
		 * @param tblUserInfor
		 *            đối tượng muốn kiểm tra codeLevel
		 * @return true nếu có codeLvel false nếu không
		 */
		public static boolean checkCodelevel(String codeLevel) {
			// Khởi tạo biến check gán = false
			boolean check = false;
			// nếu codeLevel khác 0
			if (codeLevel != null && !Constant.DEFAULT_CODE_LEVEL.equals(codeLevel)) {
				// gán check = true
				check = true;
			}
			// Trả về biến check
			return check;
		}
}
