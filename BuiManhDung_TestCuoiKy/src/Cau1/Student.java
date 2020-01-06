/**
 * Copyright(C) 2020 LuvinaSoff
 * Student.java, Jan 6, 2020, MDung
 */
package Cau1;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Cau1.gui.*;

/**
 * Description
 * @author MDung
 */
public class Student extends BasePanel implements ActionListener {
	private static final String BT_LOGIN = "BT_LOGIN";
	/**
	 * Khai báo các thuộc tính
	 */

	private JLabel hoten, ngaysinh, sdt;
	private JButton addInfo;
	private JTextField tfUser, tfPass, sdtText;

	@Override
	public void innit() {

		setBackground(Color.WHITE);
		setLayout(null);
	}

	@Override
	public void event() {

	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		// TODO add your handling code here:
		Connection con;
		Statement stt;
		ResultSet rs;
		PreparedStatement ps;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/TestCuoiKy24_BuiManhDung", "root", "12345678");
			stt = con.createStatement();
			
			String sql = "insert user(hoten, ngaysinh, sodienthoai) values ('" + tfUser.getText() + "','" 
			+ tfPass.getText() + "','" + sdtText.getText() + "')";
			try {
			int rows = stt.executeUpdate(sql);
			if (rows>0) {
				JOptionPane.showMessageDialog(this, "Thêm thành công!");
			}else {
				JOptionPane.showMessageDialog(this, "Dữ liệu bạn nhập không đúng!");
			}
			}catch(Exception e){
				JOptionPane.showMessageDialog(this, "Dữ liệu bạn nhập không đúng!");
			}
			
		} catch (Exception e) {
			System.out.println(e);
			
		}
	}

	@Override
	public void comp() {

		/**
		 * Tạo lable LOGIN
		 */
		Font f1 = new Font("Times New Roman", Font.BOLD, 40);
		Font f2 = new Font("Times New Roman", Font.PLAIN, 16);
		

		/**
		 * Tạo lable user
		 */

		hoten = creatLable(f2, "Họ Tên:", 30, 10, Color.BLACK);
		FontMetrics metric = getFontMetrics(f1);
		int w = 70;
		int h = 30;
		hoten.setSize(w, h);
		int x = 30;
		int y = 10;
		hoten.setLocation(x, y);
		add(hoten);

		/**
		 * Tạo TextFeild User
		 */
		tfUser = creatTextField(f2, 240, hoten.getHeight(), hoten.getX() + hoten.getWidth()+40, hoten.getY(), Color.BLACK);
		add(tfUser);

		/**
		 * Tạo label pass
		 */

		ngaysinh = creatLable(f2, "Ngày sinh:  ", hoten.getX(), hoten.getY() + hoten.getHeight() + 20, Color.BLACK);
		add(ngaysinh);

		/**
		 * Tạo tfPass
		 */

		tfPass = creatTextField(f2, 240, ngaysinh.getHeight(), tfUser.getX(), ngaysinh.getY(), Color.BLACK);
		tfPass.setSize(240, h);
		add(tfPass);
		
		sdt = creatLable(f2, "Số điện thoại:  ", ngaysinh.getX(), ngaysinh.getY() + ngaysinh.getHeight() + 30, Color.BLACK);
		add(sdt);

		/**
		 * Tạo tfPass
		 */

		sdtText = creatTextField(f2, 240, sdt.getHeight(), tfPass.getX(), sdt.getY(), Color.BLACK);
		sdtText.setSize(240, h);
		add(sdtText);

		addInfo = creatButton(f2, "Thêm", sdtText.getX() + sdtText.getWidth() - 180, sdtText.getY()+40, Color.BLACK,
				BT_LOGIN);
		addInfo.setSize(80, tfPass.getHeight());
		addInfo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		add(addInfo);

	}
	

}
