/**
 * Copyright(C) 2019 LuvinaSoftware
 * ImformationFile.java Dec 15, 2019 Cong_PV
 */
package com.luvina.informationfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author admin
 * Des Class in ra số kí tự, số từ, số dòng chuỗi kí tự đọc ra từ
 *         file
 */
public class ImformationFile {
	private String path;

	public ImformationFile() {

	}

	/**
	 * Description Phương thức in ra số kí tự, số từ, số dòng chuỗi kí tự đọc ra từ
	 * file
	 * @param buff[]
	 * @param len
	 * @param str
	 * @param soDong
	 * @param soKiTu
	 * @param soTu
	 * @return
	 * @throws IOException
	 */
	public void printInformationFile() {
		Scanner sc = new Scanner(System.in);// Mở luồng nhập dữ liệu từ bàn phím
		System.out.println("Nhập vào đường dẫn: ");
		path = sc.nextLine();// Gán đường dẫn nhập từ bàn phím cho path
		sc.close();
		File file = new File(path);// Trỏ tới file cần đọc
		try {
			FileInputStream fin = new FileInputStream(file);// Mở luồng đọc file
			byte buff[] = new byte[1024];// Tạo mảng buff để đọc file
			int len = fin.read(buff);// Độ dài mảng đọc
			String str = "";
			// Trog khi chiều dài của buff còn lớn hơn 0 thì tiếp tục đọc và sau đó gán lại
			// len
			while (len > 0) {
				str += new String(buff, 0, len);
				len = fin.read(buff);
			}
			System.out.println("Nội dung file:\n" + str);
			fin.close();
			int soDong = 1;
			int soKiTu = str.length();
			int soTu = 0;
			str += " ";// Cho vào cuối chuỗi kí tự dấu cách để đếm từ cuối cùng
			// Duyệt vòng for lấy ra từng kí tự để xét
			for (int i = 0; i < str.length() - 1; i++) {
				char kiTu = str.charAt(i);
				char kiTuTiepTheo = str.charAt(i + 1);
				// Nếu kí tự là chữ số hoặc là chữ cái
				if ((kiTu >= 'a' && kiTu <= 'z') | (kiTu >= 'A' && kiTu <= 'Z') | (kiTu >= '0' && kiTu <= '9')) {
					// Nếu kí tự tiếp theo không phải là chữ số hoặc là chữ cái
					if (!((kiTuTiepTheo >= 'a' && kiTuTiepTheo <= 'z') | (kiTuTiepTheo >= 'A' && kiTuTiepTheo <= 'Z')
							| (kiTuTiepTheo >= '0' && kiTuTiepTheo <= '9'))) {
						soTu++;// Tăng số từ lên 1 đv
					}
				}
				// Nếu kiTu là kí tự xuống dòng có mã ascii 13
				if (kiTu == 13) {
					soDong++;
				}
			}
			System.out.println("Số kí tự: " + soKiTu);
			System.out.println("Số từ: " + soTu);
			System.out.println("Số dòng: " + soDong);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
