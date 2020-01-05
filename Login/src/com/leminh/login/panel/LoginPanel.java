/**
 * Copy(C) 2020 Luvina SoftWare Company
 * LoginPanel.java Jan 4, 2020 2020 Minh
 * 
 */
package com.leminh.login.panel;

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

import com.leminh.login.ui.GUI;
import com.leminh.login.ui.ICommon;


/**
 * @author Minh
 *
 */
public class LoginPanel extends BasePanel implements ActionListener {
	private static final String BT_LOGIN = "BT_LOGIN";
	private MainPanel mainPanel;
	/**
	 * Khai báo các thuộc tính
	 */

	private JLabel lbLogin, lbUser, lbPass, lblogin;
	private JButton btlogin;
	private JTextField tfUser, tfPass;

	@Override
	public void innit() {

		setBackground(Color.WHITE);
		setLayout(null);
	}

	@Override
	public void event() {

	}
	
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Connection con;
        Statement stt;
        ResultSet rs;
        PreparedStatement ps;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/csdl_dangnhap", "root", "12345678");
            stt = con.createStatement();
            //rs = stt.executeQuery("SELECT username, password FROM taikhoan where username = ? and password = ?");
            String sql = "SELECT username, password FROM taikhoan where username = ? and password = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, tfUser.getText());
            ps.setString(2, tfPass.getText());
            rs = ps.executeQuery();
            int flag = 0;
            while(rs.next()){
            	
                //if ((tfUser.getText().equals(rs.getString(1))) && (tfPass.getText().equals(rs.getString(2)))){
                    flag = 1;
                    break;
                //}
            }
            if (flag==1) { 
            	JOptionPane.showMessageDialog(null, "Dang nhap thanh cong");
            	this.setVisible(false);
            	DangNhapPanel dn = new DangNhapPanel();
                dn.setVisible(true);
            }
            else JOptionPane.showMessageDialog(null, "Ten Dang nhap hoac mat khau khong dung");
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

	@Override
	public void comp() {

		/**
		 * Tạo lable LOGIN
		 */
		Font f1 = new Font("Times New Roman", Font.BOLD, 40);
		Font f2 = new Font("Times New Roman", Font.PLAIN, 20);

		lbLogin = creatLable(f1, "LOGIN", 0, 0, Color.RED);
		FontMetrics metric = getFontMetrics(f1);
		int w = metric.stringWidth(lbLogin.getText());
		int h = metric.getHeight();
		lbLogin.setSize(w, h);
		int x = GUI.W_FRAME / 2 - w / 2;
		int y = 10;
		lbLogin.setLocation(x, y);
		add(lbLogin);

		/**
		 * Tạo lable user
		 */

		lbUser = creatLable(f2, "User", 30, lbLogin.getY() + 80, Color.BLUE);
		lbUser.setSize(50, lbUser.getHeight());
		add(lbUser);

		/**
		 * Tạo TextFeild User
		 */
		tfUser = creatTextField(f2, 240, lbUser.getHeight(), lbUser.getX() + lbUser.getWidth() + 10, lbUser.getY(),
				Color.BLACK);
		add(tfUser);
		
		/**
		 * Tạo label pass
		 */
		
		lbPass = creatLable(f2, "Pass", lbUser.getX(), lbUser.getY() + lbUser.getHeight() + 20, Color.BLUE);
		add(lbPass);

		/**
		 * Tạo tfPass
		 */

		tfPass = creatTextField(f2, 240, lbPass.getHeight(), tfUser.getX(), lbPass.getY(), Color.BLACK);
		add(tfPass);

		btlogin = creatButton(f2, "Login", tfPass.getX() + tfPass.getWidth() - 80, tfPass.getY() + 40, Color.RED,
				BT_LOGIN);
		btlogin.setSize(80, tfPass.getHeight());
		btlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
		
		
		add(btlogin);

	}



}
