import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JOptionPane;



import java.awt.CardLayout;
import java.awt.Desktop;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import java.awt.FlowLayout;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import javax.swing.ImageIcon;


@SuppressWarnings("serial")
public class Manager extends JFrame {

	private JPanel contentPane;
	private ThreadTest thread = null;
	public JTextField textField;
	public JTextField textField_1;
	private JLabel lblNewLabel_2;
    public String ip;
	public String port;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager frame = new Manager();
				
					frame.setVisible(true);
					frame.setTitle("签到服务端");
					frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
			       
			       
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Manager() {
		
		addWindowListener(new WindowAdapter() {
		
			@Override
			//点击关闭按钮提示
			public void windowClosing(WindowEvent arg0) {
				int i = JOptionPane.showConfirmDialog(null,"确定退出吗","",JOptionPane.YES_NO_OPTION); 
				if(i==0){    
					System.exit(0);   
					}
				
			}
		});
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		//打开按钮
		JButton btnNewButton = new JButton("\u6253\u5F00\u670D\u52A1\u7AEF");
		btnNewButton.setBackground(UIManager.getColor("Button.background"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton.setBounds(90, 78, 259, 33);
		btnNewButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				
				 thread = new ThreadTest();
				 thread.start();
				String ipp=textField.getText();
				 String iport=textField_1.getText();
			}
		});
		
		
		//签到情况查询
		JButton btnNewButton_1 = new JButton("\u7B7E\u5230\u60C5\u51B5\u67E5\u8BE2");
		btnNewButton_1.setBackground(UIManager.getColor("Button.background"));
		btnNewButton_1.setBounds(263, 121, 161, 33);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			
		
			public void mouseClicked(MouseEvent arg0) {
				Desktop desktop = Desktop.getDesktop();
				try {
					 ip=textField.getText();
					 port=textField_1.getText();
					System.out.println(ip);
					System.out.println(port);
					desktop.browse(new URI("http://"+ip+":"+port+"/"));
					//desktop.browse(new URI("http://58.154.51.126:8002/"));
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				} catch (URISyntaxException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton_1);
		contentPane.add(btnNewButton);
		
		
		
		//创建数据库
		JButton button = new JButton("\u521B\u5EFA\u6570\u636E\u5E93");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Connection  con=null;
				Statement stat=null;
				  String sql="CREATE DATABASE \"Student\"";
				 ResultSet rs = null;
				  String sqls="select * from sys.databases where name = 'Student' ";
				  PreparedStatement ps = null;
				  try {
						Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
						con= DriverManager.getConnection("jdbc:sqlserver://localhost:1433", "sa", ""); 
						ps = con.prepareStatement(sqls); 
						rs = ps.executeQuery();
						if(!rs.next()){
						stat=con.createStatement();
						 stat.executeUpdate(sql);
						  stat.executeUpdate("Use Student create table dbo.Stuinfo(SID int IDENTITY(1,1) PRIMARY KEY, Sno char(10), Sname nvarchar(10), Smajor nvarchar(10), Sgrade nvarchar(20),Imei nvarchar(20))");
						  stat.executeUpdate("use Student create table dbo.Time(ID int IDENTITY(1,1) PRIMARY KEY, Imei nvarchar(20), Time datetime )");
						  stat.executeUpdate("Use Student create table dbo.Jtable(JID int IDENTITY(1,1) PRIMARY KEY, Week nvarchar(5), Quarte12 nvarchar(40), Quarte34 nvarchar(40), Quarte56 nvarchar(40),Quarte78 nvarchar(20), Quarte90 nvarchar(40), Grade nvarchar(10))");
						  stat.executeUpdate("Use Student create table dbo.Rtable(RID int IDENTITY(1,1) PRIMARY KEY, Week nvarchar(5), Quarte12 nvarchar(40), Quarte34 nvarchar(40), Quarte56 nvarchar(40),Quarte78 nvarchar(20), Quarte90 nvarchar(40), Grade nvarchar(10))");
						  stat.executeUpdate("Use Student create table dbo.Xtable(XID int IDENTITY(1,1) PRIMARY KEY, Week nvarchar(5), Quarte12 nvarchar(40), Quarte34 nvarchar(40), Quarte56 nvarchar(40),Quarte78 nvarchar(20), Quarte90 nvarchar(40), Grade nvarchar(10))");
						  stat.executeUpdate("Use Student create table dbo.Wtable(WID int IDENTITY(1,1) PRIMARY KEY, Week nvarchar(5), Quarte12 nvarchar(40), Quarte34 nvarchar(40), Quarte56 nvarchar(40),Quarte78 nvarchar(20), Quarte90 nvarchar(40), Grade nvarchar(10))");
						  stat.executeUpdate("use Student create table dbo.Tongzhi(ID int IDENTITY(1,1) PRIMARY KEY, grade nvarchar(15), major nvarchar(10),  tongzhi nvarchar(150), date datetime )");
						  stat.close();
						  con.close();
						  JOptionPane.showMessageDialog(null, "数据库创建成功");
						}
						else{
						JOptionPane.showMessageDialog(null, "数据库已存在");
						}
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				
				
				
			}
		});
		button.setBounds(263, 171, 161, 33);
		contentPane.add(button);
		
		
		//测试连接按钮
		JButton button_1 = new JButton("\u6D4B\u8BD5\u8FDE\u63A5");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Connection conn=null;
				Statement sta=null;
				try {
					Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
					conn= DriverManager.getConnection("jdbc:sqlserver://localhost:1433", "sa", ""); 
					 JOptionPane.showMessageDialog(null, "数据库连接正常可用");
					
					
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					 JOptionPane.showMessageDialog(null, "失败，请检查数据库连接");
				}
				finally{
					 try {  
                  if (conn != null)  
						conn.close();  
				 } catch (SQLException e) {  
			   // TODO Auto-generated catch block  
			     e.printStackTrace();  
					 }  

				}
				
			}
		});
		button_1.setBounds(26, 171, 122, 33);
		contentPane.add(button_1);
		//IP文本框
		textField = new JTextField();
		textField.addInputMethodListener(new InputMethodListener() {
			public void caretPositionChanged(InputMethodEvent arg0) {
			}
			public void inputMethodTextChanged(InputMethodEvent arg0) {
			
			}
		});
		textField.setBounds(64, 44, 128, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(271, 44, 138, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		
		JLabel lblNewLabel = new JLabel("\u7F51\u7AD9IP");
		lblNewLabel.setBounds(26, 47, 54, 15);
		contentPane.add(lblNewLabel);
		
		JLabel label = new JLabel("\u7F51\u7AD9\u7AEF\u53E3");
		label.setBounds(207, 47, 54, 15);
		contentPane.add(label);
		
		JLabel lblNewLabel_1 = new JLabel("\u8BF7\u5728\u6253\u5F00\u670D\u52A1\u7AEF\u4E4B\u524D\u8F93\u5165\u7F51\u7AD9\u5730\u5740");
		lblNewLabel_1.setBounds(26, 10, 235, 15);
		contentPane.add(lblNewLabel_1);
		
		 lblNewLabel_2 = new JLabel("\u672A\u8FD0\u884C");
		 lblNewLabel_2.setForeground(Color.RED);
		lblNewLabel_2.setBounds(312, 247, 112, 15);
		contentPane.add(lblNewLabel_2);
		
		JButton button_2 = new JButton("\u7BA1\u7406\u5B66\u751F\u4FE1\u606F");
		//弹出学生信息管理界面
		button_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Mainframe fframe=new Mainframe();
				fframe.setVisible(true);
			
			}
		});
		button_2.setBounds(26, 121, 128, 33);
		contentPane.add(button_2);
		
		JButton button_3 = new JButton("\u8BFE\u7A0B\u8BBE\u8BA1");
		//弹出课程添加界面
		button_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Kechengsheji kecheng=new Kechengsheji();
				kecheng.setVisible(true);
			}
		});
		button_3.setBounds(150, 229, 93, 23);
		contentPane.add(button_3);
		
		JButton btnNewButton_2 = new JButton("\u8BFE\u7A0B\u901A\u77E5");
		//弹出通知界面
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fabutongzhi tongzhi=new fabutongzhi();
				tongzhi.setVisible(true);
			}
		});
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(32, 229, 93, 23);
		contentPane.add(btnNewButton_2);
	
	/*获取值
		public String getIP(){
		 
		return ip;
			
		}
		public String getPort(){
			String port=textField_1.getText();
		}
	*/

	}
	class  ThreadTest extends Thread{
		 private boolean runFlag = false; 
		
		 public void run(){ 
			 String ipp=textField.getText();
			 String iport=textField_1.getText();
			// System.out.println(ipp);
			 //System.out.println(iport);
			 JOptionPane.showMessageDialog(null, "服务端程序已打开");
			 lblNewLabel_2.setText("服务端正在运行");
			 lblNewLabel_2.setForeground(Color.blue);
		  runFlag = true;  

		   if(runFlag){     
		// TODO 这里加需要线程执行
			ServerSocket ss = null;
			try {
				ss = new ServerSocket(6789);
			} catch (IOException e3) {
				// TODO 自动生成的 catch 块
				e3.printStackTrace();
			}
		        while(true)
		        {
		           //此行代码会阻塞，将一直等待别人的连接           
		            Socket s = null;
		           try {
						s = ss.accept();
						
					} catch (IOException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
		          
					
		         
		   try {
						new Thread(new ServerThread(s, ipp, iport)).start();
						
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
		          
		        }
		
		    }
	           
		   }
		
    /* public void stopThread(){ 
  
		  runFlag = false;
		  
		  }*/
	}
}
