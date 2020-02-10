import javax.swing.*;    
import java.awt.event.*;  
public class PasswordFieldExample {  
    public static void main(String[] args) {    
    JFrame f=new JFrame("Password Field Example");  
    f.setLocation(780,370);
     final JLabel label = new JLabel("",JLabel.CENTER);            
     label.setBounds(50,160, 200,50);  
     final JPasswordField value = new JPasswordField();   
     value.setBounds(120,85,100,30);   
     JLabel l1=new JLabel("Username:");    
        l1.setBounds(50,40, 80,30);    
        JLabel l2=new JLabel("Password:");    
        l2.setBounds(50,85, 80,30);    
        JButton b = new JButton("Login");  
        b.setBounds(120,130, 80,30);    
        final JTextField text = new JTextField();  
        text.setBounds(120,40, 100,30);    
                f.add(value); f.add(l1); f.add(label); f.add(l2); f.add(b); f.add(text);  
                f.setSize(300,260);    
                f.setLayout(null);    
                f.setVisible(true);     
                b.addActionListener(new ActionListener() {  
                public void actionPerformed(ActionEvent e) {       
                   String data = "Username " + text.getText();  
                   data += ", Password: "   
                   + new String(value.getPassword());   
                   label.setText(data);          
                }  
             });   
}  
}  