/**
* Copyright(C) 2019 Luvina Acadamy
* MessageErrorProperties.java, Nov 22, 2019 dtthoa
*/
package manageuser.properties;

import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import manageuser.utils.Constant;

/**
 * Class Đọc các thông tin lỗi
 * 
 * @author Đoàn Thị Thoa
 *
 */
public class MessageProperties {
	// lưu các cặp <key, value> trong file properties
	private static Map<String, String> mapMessageProperties = new HashMap<String, String>();
	static {
		try {
			// tạo đối tượng kiểu Properties
			Properties properties = new Properties();
			// đọc file properties
			properties.load(new InputStreamReader(
					DatabaseProperties.class.getClassLoader().getResourceAsStream(Constant.PROPERTIES_MESSAGE_PATH),
					"UTF-8"));
			// lưu các giá trị key trong file properties
			Enumeration<?> enumeration = properties.propertyNames();
			// true nếu vẫn còn phần tử, false nếu tất cả phần tử đã được lấy ra
			while (enumeration.hasMoreElements()) {
				// key = key tiếp theo
				String key = (String) enumeration.nextElement();
				// lấy value tương ứng với key
				String value = properties.getProperty(key);
				// thêm vào list
				mapMessageProperties.put(key, value);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new RuntimeException(e);

		}
	}

	/**
	 * lấy value tương ứng với key trong file properties
	 * 
	 * @param key
	 *            tên key trong file properties
	 * @return trả về value tương ứng với key
	 */
	public static String getValueByKey(String key) {
		String value = mapMessageProperties.get(key);
		return value;
	}

}
