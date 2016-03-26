import java.awt.BorderLayout;
import java.awt.Event;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import javax.swing.JFormattedTextField;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JSpinner;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JTextArea;


public class Kechengsheji extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JComboBox comboBox1 = new JComboBox();
	private JComboBox comboBox_2 = new JComboBox();
	private JTextField textField_4;
	private JTextField textField_5;
	private JButton button = new JButton("\u4FDD\u5B58");
	private JTextField textField12;
	private JTextField textField13;
	private JTextField textField22;
	private JTextField textField23;
	private JTextField textField32;
	private JTextField textField33;
	private JTextField textField42;
	private JTextField textField43;
	private JTextField textField52;
	private JTextField textField53;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kechengsheji frame = new Kechengsheji();
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
	public Kechengsheji() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 514, 389);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u4E0A\u5348 12\u8282");
		lblNewLabel.setBounds(65, 110, 69, 21);
		contentPane.add(lblNewLabel);
		//12节
		textField = new JTextField();
		textField.setBounds(167, 110, 104, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.addActionListener(this);
		
		JLabel lblNewLabel_1 = new JLabel("\u4E0A\u5348 34\u8282");
		lblNewLabel_1.setBounds(65, 138, 79, 27);
		contentPane.add(lblNewLabel_1);
		//34节输入文本框
		textField_1 = new JTextField();
		textField_1.setBounds(167, 141, 104, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		textField_1.addActionListener(this);
		
		JLabel lblNewLabel_2 = new JLabel("\u4E0B\u5348 56\u8282");
		lblNewLabel_2.setBounds(67, 189, 67, 21);
		contentPane.add(lblNewLabel_2);
		//56节文本框
		textField_2 = new JTextField();
		textField_2.setBounds(167, 189, 104, 21);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		textField_2.addActionListener(this);
	
		
		JLabel label = new JLabel("\u4E0B\u5348 78\u8282");
		label.setBounds(65, 221, 71, 24);
		contentPane.add(label);
		//78节文本框
		textField_3 = new JTextField();
		textField_3.setBounds(167, 220, 104, 21);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		textField_3.addActionListener(this);
		
		JLabel label_1 = new JLabel("\u4E13\u4E1A\uFF1A");
		label_1.setBounds(10, 40, 54, 15);
		contentPane.add(label_1);
		
		
		comboBox1.setModel(new DefaultComboBoxModel(new String[] {"软件工程","计算机科学","网络工程","信息管理"}));
		comboBox1.setBounds(55, 37, 91, 21);
		contentPane.add(comboBox1);
		
		JLabel label_2 = new JLabel("\u5E74\u7EA7\uFF1A");
		label_2.setBounds(156, 40, 67, 15);
		contentPane.add(label_2);
		
		
		JLabel label_3 = new JLabel("\u4E0A\u8BFE\u65F6\u95F4\uFF1A");
		label_3.setBounds(308, 40, 71, 15);
		contentPane.add(label_3);
		
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"星期一","星期二","星期三","星期四","星期五"}));
		comboBox_2.setBounds(374, 37, 75, 21);
		contentPane.add(comboBox_2);
		
		
		button.addActionListener(this);
		button.setBounds(356, 305, 93, 23);
		contentPane.add(button);
		
		//年级
		textField_4 = new JTextField();
		textField_4.setBounds(190, 37, 93, 21);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
	
		
		JLabel label_4 = new JLabel("\u665A\u4E0A 9 10\u8282");
		label_4.setBounds(65, 264, 69, 15);
		contentPane.add(label_4);
		//910jie文本框
		textField_5 = new JTextField();
		textField_5.setBounds(167, 261, 104, 21);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		textField_5.addActionListener(this);
		JTextPane textPane = new JTextPane();
		textPane.setBounds(69, 305, 6, 21);
		contentPane.add(textPane);
		
		JLabel lblNewLabel_3 = new JLabel("\u8BFE\u7A0B\u540D");
		lblNewLabel_3.setBounds(169, 85, 54, 15);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u6559\u5E08\u540D");
		lblNewLabel_4.setBounds(278, 85, 54, 15);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("\u6559\u5BA4");
		lblNewLabel_5.setBounds(357, 85, 54, 15);
		contentPane.add(lblNewLabel_5);
		
		textField12 = new JTextField();
		textField12.setBounds(266, 110, 91, 21);
		contentPane.add(textField12);
		textField12.setColumns(10);
		//添加键盘监听
		textField12.addActionListener(this);
		
		textField13 = new JTextField();
		textField13.setBounds(352, 110, 97, 21);
		contentPane.add(textField13);
		textField13.setColumns(10);
		textField13.addActionListener(this);
		
		textField22 = new JTextField();
		textField22.setBounds(266, 141, 91, 21);
		contentPane.add(textField22);
		textField22.setColumns(10);
		textField22.addActionListener(this);
		
		textField23 = new JTextField();
		textField23.setBounds(356, 141, 93, 21);
		contentPane.add(textField23);
		textField23.setColumns(10);
		textField23.addActionListener(this);
		
		textField32 = new JTextField();
		textField32.setBounds(266, 189, 91, 21);
		contentPane.add(textField32);
		textField32.setColumns(10);
		textField32.addActionListener(this);
		
		textField33 = new JTextField();
		textField33.setBounds(356, 189, 93, 21);
		contentPane.add(textField33);
		textField33.setColumns(10);
		textField33.addActionListener(this);
		
		textField42 = new JTextField();
		textField42.setBounds(266, 220, 91, 21);
		contentPane.add(textField42);
		textField42.setColumns(10);
		textField42.addActionListener(this);
		
		textField43 = new JTextField();
		textField43.setBounds(356, 221, 93, 21);
		contentPane.add(textField43);
		textField43.setColumns(10);
		textField43.addActionListener(this);
		
		textField52 = new JTextField();
		textField52.setBounds(266, 261, 91, 21);
		contentPane.add(textField52);
		textField52.setColumns(10);
		textField52.addActionListener(this);
		
		textField53 = new JTextField();
		textField53.setBounds(352, 261, 97, 21);
		contentPane.add(textField53);
		textField53.setColumns(10);
		textField53.addActionListener(this);
		
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==button){
			  int  index = comboBox1.getSelectedIndex();
			  int  weekday= comboBox_2.getSelectedIndex();
			  String inde=comboBox1.getSelectedItem().toString();
			  String  week= comboBox_2.getSelectedItem().toString();
			  String ojie1=textField.getText(); String ojie2=textField12.getText(); String ojie3=textField13.getText();
			  String ojie=ojie1+"/n"+ojie2+"/n"+ojie3;
              String tjie1=textField_1.getText();String tjie2=textField22.getText();String tjie3=textField23.getText();
              String tjie=tjie1+"/n"+tjie2+"/n"+tjie3;
              String fjie1=textField_2.getText();String fjie2=textField32.getText();String fjie3=textField33.getText();
              String fjie=fjie1+"/n"+fjie2+"/n"+fjie3;
			  String sjie1=textField_3.getText();String sjie2=textField42.getText();String sjie3=textField43.getText();
			  String sjie=sjie1+"/n"+sjie2+"/n"+sjie3;
			  String njie1=textField_5.getText();String njie2=textField52.getText(); String njie3=textField53.getText();
			  String njie=njie1+"/n"+njie2+"/n"+njie3;
			  String grade=textField_4.getText();
			  if(ojie.equals("/n/n")){
				  ojie="";
			  }
			  if(tjie.equals("/n/n")){
				  tjie="";
			  }
			  if(fjie.equals("/n/n")){
				  fjie="";
			  }
			  if(sjie.equals("/n/n")){
				  sjie="";
			  }
			  if(njie.equals("/n/n")){
				  njie="";
			  }
			  try{
			  Sqlcon scon =new Sqlcon();
  			 Connection conn = scon.getConnection();
  		   
			    //选择软工
		          if(index==0){
		        	   String sql="insert into dbo.Rtable values(?,?,?,?,?,?,?)";
		        		  PreparedStatement ppst=conn.prepareStatement(sql);
		        		  ppst.setString(1, week);
		        		  ppst.setString(2,ojie);
		        		  ppst.setString(3,tjie);
		        		  ppst.setString(4,fjie);
		        		  ppst.setString(5,sjie);
		        		  ppst.setString(6,njie);
		        		  ppst.setString(7,grade);
		        		  ppst.executeUpdate();
		        		  
		        	 
		          }
		         //选择计算机科学
		          if(index==1){
		        	  String sql="insert into dbo.Jtable values(?,?,?,?,?,?,?)";
	        		  PreparedStatement ppst=conn.prepareStatement(sql);
	        		  ppst.setString(1, week);
	        		  ppst.setString(2,ojie);
	        		  ppst.setString(3,tjie);
	        		  ppst.setString(4,fjie);
	        		  ppst.setString(5,sjie);
	        		  ppst.setString(6,njie);
	        		  ppst.setString(7,grade);
	        		  ppst.executeUpdate();
		          }
		          //选择网络工程
		          if(index==2){
		        	  String sql="insert into dbo.Wtable values(?,?,?,?,?,?,?)";
	        		  PreparedStatement ppst=conn.prepareStatement(sql);
	        		  ppst.setString(1, week);
	        		  ppst.setString(2,ojie);
	        		  ppst.setString(3,tjie);
	        		  ppst.setString(4,fjie);
	        		  ppst.setString(5,sjie);
	        		  ppst.setString(6,njie);
	        		  ppst.setString(7,grade);
	        		  ppst.executeUpdate();
		          }
		          //选择信息管理
		          if(index==3){
		        	  String sql="insert into dbo.Xtable values(?,?,?,?,?,?,?)";
	        		  PreparedStatement ppst=conn.prepareStatement(sql);
	        		  ppst.setString(1, week);
	        		  ppst.setString(2,ojie);
	        		  ppst.setString(3,tjie);
	        		  ppst.setString(4,fjie);
	        		  ppst.setString(5,sjie);
	        		  ppst.setString(6,njie);
	        		  ppst.setString(7,grade);
	        		  ppst.executeUpdate();
		          }
		          JOptionPane.showMessageDialog(this, "保存"+week+"课表成功");  
			    }catch(SQLException ex){
			    	JOptionPane.showMessageDialog(this, "请检查数据库连接");
			    }
		     }
		//键盘监听
		if (e.getSource() ==textField){
			  textField12.requestFocus();
		   }
		  if (e.getSource() ==textField12){
			  textField13.requestFocus();
		   }
		  if (e.getSource() ==textField13){
			  textField_1.requestFocus();
		   }
		  if (e.getSource() ==textField_1){
			  textField22.requestFocus();
		   }
		  if (e.getSource() ==textField22){
			  textField23.requestFocus();
		   }
		  if (e.getSource() ==textField23){
			  textField_2.requestFocus();
		   }
		  if (e.getSource() ==textField_2){
			  textField32.requestFocus();
		   }
		  if (e.getSource() ==textField32){
			  textField33.requestFocus();
		   }
		  if (e.getSource() ==textField33){
			  textField_3.requestFocus();
		   }

		  if (e.getSource() ==textField_3){
			  textField42.requestFocus();
		   }
		  if (e.getSource() ==textField42){
			  textField43.requestFocus();
		   }
		  if (e.getSource() ==textField43){
			  textField_5.requestFocus();
		   }
		  
		  if (e.getSource() ==textField_5){
			  textField52.requestFocus();
		   }
		  if (e.getSource() ==textField52){
			  textField53.requestFocus();
		   }
	
		
			  }
	}

