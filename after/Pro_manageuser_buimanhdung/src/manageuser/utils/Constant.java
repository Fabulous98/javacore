/**
 * Copyright(C) 2019 Luvina Software
 * Constant.java, Dec 27, 2019, MDung
 */
package manageuser.utils;

/**
 * @author MDung
 *
 */
public class Constant {
	// =================Khai báo đường dẫn đến các file properties
		public static final String PROPERTIES_DATABASE_PATH = "manageuser/properties/databases.properties";
		public static final String PROPERTIES_ERROR_PATH = "manageuser/properties/message_error.properties";
		public static final String PROPERTIES_CONFIG_PATH = "manageuser/properties/config.properties";
		public static final String PROPERTIES_MESSAGE_PATH = "manageuser/properties/message_ja.properties";

		// Khởi tạo biến UTF8
		public static final String UTF8 = "UTF8";
		// Khởi tạo biến session khi login
		public static String SESSION_LOGIN = "username";

		// ===================Khởi tạo biến cho các action
		public static final String ACTION_BACK = "back";
		public static final String ACTION_SORT = "sort";
		public static final String ACTION_SEARCH = "search";
		public static final String ACTION_LOGIN = "login";
		public static final String ACTION_PAGING = "paging";
		public static final String ACTION_VALIDATE_003 = "validate03";
		public static final String ACTION = "action";

		// Khởi tạo giá trị cho rule = 0 của admin
		public static final int RULE_ADMIN = 0;
		// Khởi tạo giá trị cho rule = 1 của user
		public static final int RULE_USER = 1;

		public static String SORT_TYPE = "sortType";
		public static String SORT_VALUE = "sortValue";
		// Khởi tạo trường sort mặc định
		public static final String SORT_DEFAULT = "fullName";
		// khởi tạo biến sort theo fullName
		public static final String TYPE_FULL_NAME = "fullName";
		// khởi tạo biến sort theo endDate
		public static final String TYPE_END_DATE = "endDate";
		// khởi tạo biến sort theo codeLevel
		public static final String TYPE_CODE_LEVEL = "codeLevel";
		// khởi tạo biến sort theo codeLevel
		public static final String SORT_BY_CODE_LEVEL = "sortByCodeLevel";
		// Khởi tạo biến SORT_BY_FULL_NAME
		public static final String SORT_BY_FULL_NAME = "sortByFullName";
		// Khởi tạo biến SORT_BY_END_DATE
		public static final String SORT_BY_END_DATE = "sortByEndDate";
		// khởi tạo biến currentPage
		public static final String CURRENT_PAGE = "currentPage";
		// Khởi tạo giá trị sắp xếp tăng dần
		public static final String SORT_ASC = "ASC";
		// Khởi tạo giá trị sắp xếp giảm dần
		public static final String SORT_DESC = "DESC";

		// =====================Khởi tạo các giá trị default
		public static final int DELAULT_OFFSET = 0;
		public static final int DELAULT_GROUP = 0;
		public static final int LIMIT_DEFAULT = 5;
		public static final int DELAULT_LIMIT_PAGE = 3;
		public static int NUMBER_DEFAULT = 0;
		public static int PAGE_CURRENT_DEFAULT = 1;
		public static final int DEFAULT_GROUP_ID = 0;
		public static final String DEFAULT_CODE_LEVEL = "0";
		public static final String DEFAULT_STRING = "";

		// ==============Khởi tạo biến ngày tháng năm sinh
		public static final String YEAR_BIRTH = "yearBirth";
		public static final String MONTH_BIRTH = "monthBirth";
		public static final String DATE_BIRTH = "dateBirth";
		public static final int DEFAULFT_VALUE_TIME = 1;
		// Start fix bug ID 20 – ThoaDT 2019/12/25
		public static final int YEARS_START = 1900;
		// End fix bug ID 20 – ThoaDT 2019/12/25
		// -------------- Khởi tạo biến cho ngày bắt đầu có hiệu lực
		public static final String YEAR_START = "yearStart";
		public static final String MONTH_START = "monthStart";
		public static final String DATE_START = "dateStart";

		// -------------- Khởi tạo biến cho ngày bắt đầu có hiệu lực
		public static final String YEAR_END = "yearEnd";
		public static final String MONTH_END = "monthEnd";
		public static final String DATE_END = "dateEnd";

		// ------------------Khởi tạo biến các thuộc tính của userInfo

		public static String USER_INFOR = "userInfor";
		public static String LOGGIN_NAME = "loginName";
		public static String GROUP_ID = "groupId";
		public static String FULL_NAME = "fullName";
		public static String FULL_NAME_KANA = "fullNameKana";
		public static String TEL = "tel";
		public static String EMAIL = "email";
		public static String PASS = "pass";
		public static String CONFIRM_PASS = "confirmPass";
		public static String CODE_LEVEL = "codeLevel";
		public static String TOTAL = "total";

		// ------------------Khởi tạo biến lưu đường dẫn đến các màn hình
		public static String URL_ADM002 = "/jsp/ADM002.jsp";
		public static String URL_ADM003 = "/jsp/ADM003.jsp";
		public static String URL_ADM004 = "/jsp/ADM004.jsp";
		public static String URL_ADM001 = "/jsp/ADM001.jsp";
		public static String URL_LIST_USER = "/listUser.do";
		public static String URL_SYS_ERR = "/jsp/System_Error.jsp";
		public static final String URL_ADM006 = "/jsp/ADM006.jsp";
		public static final String URL_ADM005 = "/jsp/ADM005.jsp";
		// ---------------------------Khởi tạo biến lưu các đường dẫn
		public static final String URL_ERROR = "/error.do?type=";
		public static final String SUCCESS = "/successOK.do?type=";
		public static final String URL_LOGIN = "/login.do";
		public static final String URL_ADD_USER_CONFIRM = "/addUserConfirm.do";
		public static final String URL_EDIT_USER_CONFIRM = "/editUserConfirm.do";
		// ------------------------ Khởi tạo các biến checkFomat

		public static final int MAX_LENGTH = 255;
		public static final int MAX_PASS = 15;
		public static final int MIN_PASS = 5;
		public static final int MAX_TOTAL = 9;
		public static final int MIN_LOGIN = 4;
		public static final int MAX_LOGIN = 15;
		
		public static final String FORMAT_KANA = "[\\u30A0-\\u30FF\\uFF65-\\uFF9F　]*";
		public static final String FORMAT_NUMBER_DATE = "\\d{4}/\\d{2}/\\d{2}";
		public static final String FORMAT_LOGIN_NAME = "[a-zA-Z_][a-zA-Z0-9_]*";
		public static final String FORMAT_TEL = "[0-9]{1,4}-[0-9]{1,4}-[0-9]{1,4}$";
		public static final String FORMAT_EMAIL = "([.]*[\\w])+@(([\\w])+\\.)+[\\w]+$";
		public static final String FORMAT_HALF_SIZE = "[0-9]+";

		public static final String KEY = "key";
		public static final String CHECK_03_04 = "check";
		public static final String OK = "true";
		public static final String USER_ID = "userId";
		// ------------------------ Khởi tạo các biến thông báo
		public static final String ER020 = "ERROR_020";
		public static final String MSG005 = "MSG_005";
		public static final String INSERT_SUCCESS = "MSG_001";
		public static final String EDIT_SUCCESS = "MSG_002";
		public static final String DELETE_SUCCESS = "MSG_003";
		public static final String ER014 = "ERROR_014";
		public static final String ER013 = "ERROR_013";
		public static final String ERROR_001_LOGINNAME = "ERROR_001_LOGINNAME";
		public static final String ERROR_001_PASS = "ER001_PASSWORD";
		public static final String ERROR_003_LOGIN = "ERROR_003_LOGINNAME";
		public static final String ERROR_003_EMAIL = "ERROR_003_EMAIL";
		public static final String SYSTEM_ERROR = "ERROR_SYSTEM";
		public static final String ERROR_SYSTEM = "ERROR_SYSTEM";
		public static final String TYPE = "type";
		public static final String LIST_MSTGROUP = "listMstGroup";
		public static final String LIST_MSTJAPAN = "listMstJapan";
		public static final String LIST_DATE = "listDate";
		public static final String LIST_YEAR = "listYear";
		public static final String LIST_MONTH = "listMonth";
		
}
