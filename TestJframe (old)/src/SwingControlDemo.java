 
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
 
public class SwingControlDemo {
    
   private JFrame mainFrame;
   private JFrame userFrame;
   private JLabel headerLabel;
   private JLabel welcome;
   private JPanel statusPanel;
   private JPanel controlPanel;
   private JPanel controlPanel2;
   private JTextField userText;
   private JPasswordField passwordText;

   public SwingControlDemo(){
      prepareGUI();
   }

   public static void main(String[] args){
      SwingControlDemo  swingControlDemo = new SwingControlDemo();      
      swingControlDemo.showTextFieldDemo();
      swingControlDemo.showTextFieldDemo2();
   }

   private void prepareUser() {
	   userFrame = mainFrame = new JFrame("You are welcome!");
	   userFrame.setSize(340,320);
	   userFrame.setLocationRelativeTo(null);
	   userFrame.setLayout(new GridLayout(1, 1));
	   userFrame.addWindowListener(new WindowAdapter() {
	      public void windowClosing(WindowEvent windowEvent){
	          System.exit(0);
	      }        
	   });
	   welcome = new JLabel("Chào mừng bạn!", JLabel.CENTER);
	   userFrame.add(welcome);
	   userFrame.setVisible(true);
   }
   
   private void prepareGUI(){
      mainFrame = new JFrame("Login");
      mainFrame.setSize(340,320);
      mainFrame.setLocationRelativeTo(null);
      mainFrame.setLayout(new GridLayout(4, 1));
      mainFrame.addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent windowEvent){
            System.exit(0);
         }        
      });    
      headerLabel = new JLabel("", JLabel.CENTER);        
      showfooter();
      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());
      controlPanel2 = new JPanel();
      controlPanel2.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(controlPanel2);
      mainFrame.add(statusPanel);
      mainFrame.setVisible(true);  
   }
   
   private void showfooter() {
	   statusPanel = new JPanel();    
	   statusPanel.setSize(350,100);
	   
	   	  userText = new JTextField(12);
	      passwordText = new JPasswordField(12);   
	      
	      JButton loginButton = new JButton("Login");
	      loginButton.setSize(80,35);
	      loginButton.addActionListener(new ActionListener() {
	         public void actionPerformed(ActionEvent e) {     
	        	 jButton1ActionPerformed(e);        
	         }
	      }); 
	      
	   statusPanel.add(loginButton);
	   
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
           ps.setString(1, userText.getText());
           ps.setString(2, passwordText.getText());
           rs = ps.executeQuery();

           if (rs.next()) { 
           	JOptionPane.showMessageDialog(null, "           Login Successful!");
           	mainFrame.setVisible(false);
           	prepareUser();
           }
           else JOptionPane.showMessageDialog(null, "Tên đăng nhập hoặc mật khẩu không đúng!");
       }
       catch(Exception e){
           System.out.println(e);
       }
   }
   
   private void showTextFieldDemo(){
      headerLabel.setText("Thông tin đăng nhập"); 

      JLabel  namelabel= new JLabel("User ID:       ", JLabel.RIGHT);
      controlPanel.add(namelabel);
      controlPanel.add(userText);
      mainFrame.setVisible(true);  
   }
   
   private void showTextFieldDemo2(){
	      
	      JLabel  passwordLabel = new JLabel("Password: ", JLabel.RIGHT);
	
	      controlPanel2.add(passwordLabel);       
	      controlPanel2.add(passwordText);
	      
	      mainFrame.setVisible(true);  
	   }
}