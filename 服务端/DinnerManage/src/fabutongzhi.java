import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class fabutongzhi extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
    private JComboBox comboBox;
    private JTextPane textPane;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					fabutongzhi frame = new fabutongzhi();
					frame.setTitle("课程设计");
					frame.setVisible(false);
				
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public fabutongzhi() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(140, 10, 108, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel("\u901A\u77E5\u5B66\u751F\u5E74\u7EA7\uFF1A");
		label.setBounds(10, 13, 114, 15);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("\u8BFE\u7A0B\u901A\u77E5\u5185\u5BB9\uFF1A");
		label_1.setBounds(10, 63, 114, 15);
		contentPane.add(label_1);
		
		textPane= new JTextPane();
		textPane.setBounds(140, 63, 245, 142);
		contentPane.add(textPane);
		
		JButton button = new JButton("\u4FDD\u5B58");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String grade=textField.getText();
				 String major=(String)comboBox.getSelectedItem();//选中的值
				 String content=textPane.getText();
				 Date date=new java.util.Date();
     		    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置显示格式
     		    String nowTime= df.format(date);
				 Sqlcon scon =new Sqlcon();
	  			 Connection conn = scon.getConnection();
	  			 String sql="insert into dbo.Tongzhi values(?,?,?,?)";
	  			 try {
	  				 PreparedStatement ppst=conn.prepareStatement(sql);
	  				  ppst.setString(1,grade);
	        		  ppst.setString(2,major);
	        		  ppst.setString(3,content);
	        		  ppst.setString(4,nowTime);
	        		  ppst.executeUpdate();
	        		  JOptionPane.showMessageDialog(null, "保存通知成功");
				} catch (Exception e2) {
					// TODO: handle exception
					JOptionPane.showMessageDialog(null, "保存失败,请检查数据库连接");
				}
       		 
				
			}
		});
		button.setBounds(331, 229, 93, 23);
		contentPane.add(button);
		
		comboBox= new JComboBox();
		 comboBox.setModel(new DefaultComboBoxModel(new String[] {"软件工程","计算机科学","网络工程","信息管理"}));
		comboBox.setBounds(322, 10, 90, 21);
		contentPane.add(comboBox);
		
		JLabel label_2 = new JLabel("\u4E13\u4E1A");
		label_2.setBounds(258, 13, 54, 15);
		contentPane.add(label_2);
	}
}
