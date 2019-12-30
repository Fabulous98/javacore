/**
 * Copyright(C) 2019 LuvinaSoff
 * TestJDBC.java, Dec 30, 2019, MDung
 */

import java.sql.*;

/**
 * Description
 * @author MDung
 */
public class TestJDBC {
	

	/**
	 * Des
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CSDLLA24PM01","root","root");
			
			// tao doi tuong Statement
			Statement stt = null;
			stt = con.createStatement();
			// Thuc thi truy van ResultSet
			
			/* Comment */ System.out.println("--------------------------------------\nIn thong tin nhan vien co luong > 25000\n--------------------------------------");
			
						ResultSet rs= stt.executeQuery("select concat(Honhanvien,' ', Tenlot,' ', Tennhanvien) as 'Hovaten', Luong "
													 + "from nhanvien where nhanvien.luong > 25000");
						while (rs.next()) {
							System.out.println(rs.getString(1)+ " " + rs.getInt(2));
						}
			
			/* Comment */ System.out.println("--------------------------------------\nIn thong tin nhan vien sinh vao thang 6\n--------------------------------------");
						
						rs= stt.executeQuery("select concat(Honhanvien,' ', Tenlot,' ', Tennhanvien) as 'Hovaten', ngaysinh from nhanvien where month(nhanvien.ngaysinh) = 6");
						while (rs.next()) {
							System.out.println(rs.getString(1)+ " " + rs.getString(2));
						}
			
			/* Comment */ System.out.println("--------------------------------------\nTim phong ban co > 3 nhan vien\n--------------------------------------");
			
						rs= stt.executeQuery("select phongban.tenphong, maphong, count(manhanvien) as 'So nhan vien'\r\n" + 
									  		 "from nhanvien inner join phongban using (maphong)\r\n" + 
											 "group by maphong having count(manhanvien) >3");
						while (rs.next()) {
							System.out.println(rs.getString(1)+ " " + rs.getInt(2)+ " " + rs.getInt(3));
						}
						
			/* Comment */ System.out.println("--------------------------------------\nThong ke so nhan vien cac phong ban theo gioi tinh\n--------------------------------------");
			
						rs= stt.executeQuery("select p.tenphong,\r\n" + 
								"			   count(case when n.gioitinh='M' then n.manhanvien else null end) as 'So NV Nam',\r\n" + 
								"			   count(case when n.gioitinh='F' then n.manhanvien else null end) as 'So NV Nu',\r\n" + 
								"			   count(n.manhanvien) as 'tong so NV'\r\n" + 
								"		from nhanvien n inner join phongban p\r\n" + 
								"							  using(Maphong)\r\n" + 
								"		group by p.maphong");
						
						while (rs.next()) {
							System.out.println(rs.getString(1)+ " " + rs.getInt(2) + " " + rs.getInt(3)+" " + rs.getInt(4));
						}
			
		// Buoc 5. Dong doi tuong con
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}

		
	}
}
