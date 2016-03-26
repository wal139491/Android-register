import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class Mainframe extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;


	DefaultTableModel model;
	ResultSetMetaData rsmd;
   
    JScrollPane scrollPane;
    private JButton button_1=new JButton("\u67E5\u8BE2");
    private JButton button_2 = new JButton("\u67E5\u8BE2");
    private JButton button_3=new JButton("\u4FDD\u5B58");
    private JButton button_4 = new JButton("\u5220\u9664");
    private JButton button_5 = new JButton("\u4E13\u4E1A\u67E5\u8BE2");
    JTable table;
	JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Mainframe frame = new Mainframe();
					frame.setTitle("学生信息管理");
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
	public Mainframe() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 394);
		contentPane = new JPanel();
		contentPane.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("\u59D3\u540D");
		label.setBounds(10, 10, 29, 15);
		contentPane.add(label);
		
		textField = new JTextField();
		textField.setBounds(47, 10, 66, 21);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u5B66\u53F7");
		label_1.setBounds(243, 10, 29, 15);
		contentPane.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(282, 7, 66, 21);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
	    //按学号查询
		button_2.addActionListener(this);
        button_2.setBounds(358, 6, 66, 23);
		contentPane.add(button_2);
		
		//保存按钮
		button_3.addActionListener(this);
		button_3.setBounds(32, 54, 66, 23);
		contentPane.add(button_3);
		
		//删除按钮
		button_4.addActionListener(this);
	    button_4.setBounds(119, 54, 75, 23);
		contentPane.add(button_4);
		
		//监听下拉列表框按钮
		button_5.addActionListener(this);
		button_5.setBounds(330, 54, 94, 23);
		contentPane.add(button_5);

	    //按姓名查询
		button_1.addActionListener(this);
		button_1.setBounds(142, 6, 75, 23);
		contentPane.add(button_1);
		
		//下拉列表框
	   comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"软件工程","计算机科学","网络工程","信息管理"}));
		comboBox.setBounds(226, 55, 94, 21);
		contentPane.add(comboBox);
		
		
		
		
		
       
		
	}

	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e) {
		// TODO 自动生成的方法存根
	   //按姓名查询
	    Vector colum = new Vector();  
		 Vector rows = new Vector(); 
        if(e.getSource()==button_1){
        	try{  
        		Sqlcon scon =new Sqlcon();
				Connection conn = scon.getConnection();
				String sname=textField.getText();
			 String sqlt="Select Stuinfo.Sno,Stuinfo.Sname,Stuinfo.Smajor,Stuinfo.Sgrade from Stuinfo where Stuinfo.Sname=?  ";
			 PreparedStatement  pstmt=conn.prepareStatement(sqlt);
		     pstmt.setString(1,sname);
		     ResultSet rs = pstmt.executeQuery();
		     rsmd = rs.getMetaData();
		   // System.out.println(rsmd.getColumnCount());
		        
	              colum.addElement("学号");
	              colum.addElement("姓名");
	              colum.addElement("专业");
	              colum.addElement("年级");
	              if(!rs.next()){
	            	  JOptionPane.showMessageDialog(this, "没有这条数据"); 
	              }
	              else{
	                Vector currow = new Vector();
	                 for (int i=1;i<= rsmd.getColumnCount();++i) {  
	                       currow.addElement(rs.getString(i));  
	                  } 
	               rows.addElement(currow);  
	            }
	                table = new JTable(rows, colum);
	        		table.setPreferredScrollableViewportSize(new Dimension(300, 200));
	        		JScrollPane scrollPane = new JScrollPane(table);
	        		scrollPane.setBounds(80, 100, 300, 200);
	        		getContentPane().add(scrollPane);
	        		setVisible(true);
	          
        	}
				catch (SQLException ex) {
	    			// TODO Auto-generated catch block
	    		}
        	
        }
        //按学号查询
        if(e.getSource()==button_2){
        	try{  
        		Sqlcon scon =new Sqlcon();
				Connection conn = scon.getConnection();
				String sno=textField_1.getText();
			 String sqlt="Select Stuinfo.Sno,Stuinfo.Sname,Stuinfo.Smajor,Stuinfo.Sgrade from Stuinfo where Stuinfo.Sno=?  ";
			 PreparedStatement  pstmt=conn.prepareStatement(sqlt);
		     pstmt.setString(1,sno);
		     ResultSet rs = pstmt.executeQuery();
		     rsmd = rs.getMetaData();
		     //System.out.println(rsmd.getColumnCount());
	       
	              colum.addElement("学号");//获得列名就是字段 
	              colum.addElement("姓名");
	              colum.addElement("专业");
	              colum.addElement("年级");
	              if(!rs.next()){
	            	  JOptionPane.showMessageDialog(this, "没有这条数据"); 
	              }
	              else{
	              	 Vector currow = new Vector();
	                  for (int i=1;i<= rsmd.getColumnCount();++i) {  
	                       currow.addElement(rs.getString(i));  
	                  } 
	               rows.addElement(currow);  
	            }
	            
	                table= new JTable(rows, colum);
	        		table.setPreferredScrollableViewportSize(new Dimension(300, 200));
	        	    scrollPane = new JScrollPane(table);
	        		scrollPane.setBounds(80, 100, 300, 200);
	        		getContentPane().add(scrollPane);
	        		setVisible(true);
	          
        	}
				catch (SQLException ex) {
	    			// TODO Auto-generated catch block
	    		}
        	}
        
     
    //删除这条数据
      if(e.getSource()==button_4){
    	  int n = JOptionPane.showConfirmDialog(null, "确认删除吗?", "确认删除框", JOptionPane.YES_NO_OPTION);	
    	  if (n == JOptionPane.YES_OPTION) {		
    		  String deletesql = "delete  from Stuinfo where Sno=" + "'"  
                      + table.getValueAt(table.getSelectedRow(), 0) + "'"; 
        	  //System.out.println(table.getValueAt(table.getSelectedRow(), 0));
             try{
          		Sqlcon scon =new Sqlcon();
      	     Connection conn = scon.getConnection();
      	     PreparedStatement ppst=conn.prepareStatement(deletesql);
      	     ppst.executeUpdate();
      	    }catch(SQLException e3){
          		  
          	  }
             /*删除该行的功能
             int row = table.getSelectedRow();
             model=new DefaultTableModel(rows, colum); 
             model.removeRow(row);*/
             int[] rowIndexes = table.getSelectedRows();  
             for (int i = 0; i < rowIndexes.length; i++)  
             {  
               //转换为Model的索引，这句很重要，否则索引不对应  
                rowIndexes[i] = table.convertRowIndexToModel(rowIndexes[i]);  
            }  
            //排序，这句很重要，否则顺序是乱的  
            Arrays.sort(rowIndexes);  
              
            model = (DefaultTableModel) table.getModel();  
             //降序删除  
           for(int i=rowIndexes.length-1; i>=0; i--){  
               model.removeRow(rowIndexes[i]);  
                //与Model对应的列表数据，含有更多元数据  
                //MainFrame.resultList.remove(rowIndexes[i]);  
           }  
       JOptionPane.showMessageDialog(this, "删除成功！");  
          } 
    		 
    	  
         }
    		
    
    	  
      
      /**
  	 * 根据下拉列表框的值查询学生信息
  	 */
      
      if(e.getSource()==button_5){
    	  Sqlcon scon =new Sqlcon();
			Connection conn = scon.getConnection();
    	  int r=comboBox.getSelectedIndex()+1;//选中的是第几项
    	  String major=(String)comboBox.getSelectedItem();//选中的值
    	  try{
    	  String ssql="Select Stuinfo.Sno,Stuinfo.Sname,Stuinfo.Smajor,Stuinfo.Sgrade from Stuinfo where Stuinfo.Smajor=?  ";
    	  PreparedStatement  pstmt=conn.prepareStatement(ssql);
		     pstmt.setString(1,major);
		     ResultSet rs = pstmt.executeQuery();
		     rsmd = rs.getMetaData();
		              colum.addElement("学号");
		              colum.addElement("姓名");
		              colum.addElement("专业");
		              colum.addElement("年级");
		              while(rs.next()){
			              	
		                   Vector currow = new Vector();
		               
		                   
		            for (int i=1;i<= rsmd.getColumnCount();++i) {  
		                       currow.addElement(rs.getString(i));  
		                  } 
		               rows.addElement(currow);  
		            }
		               table= new JTable(rows, colum);
		             	table.setPreferredScrollableViewportSize(new Dimension(300, 200));
		             	table.putClientProperty("terminateEditOnFocusLost", Boolean.TRUE);
		        		//JScrollPane scrollPane = new JScrollPane(table);
		             	scrollPane = new JScrollPane(table);
		        		scrollPane.setBounds(80, 100, 300, 200);
		        		getContentPane().add(scrollPane);
		        		setVisible(true);
		        		/*table3.getTableHeader().addMouseListener(new MouseAdapter() {  
		        		    @Override  
		        		   public void mousePressed(MouseEvent e) {  
		        			      if (table.getCellEditor() != null) {  
		        			           table.getCellEditor().stopCellEditing();  
		        			       }  
		        			        super.mousePressed(e);  
		        		    }  
		        			});  */

    	  }
    	  catch (SQLException ex) {
	    			// TODO Auto-generated catch block
	    		}
      }
      
      // 修改单元格内容并保存  
      if (e.getSource() == button_3) {  
            String stfri=(String) table.getValueAt(table.getSelectedRow(), 0);//编辑前该cell的值
    	 /* if (table.isEditing()){ 
           table.getCellEditor().stopCellEditing();  }*/
            String val0 = (String) table.getValueAt(table.getSelectedRow(),  
            table.getSelectedColumn());  
          // System.out.println(val0);// val0为取得编辑的内容  
          int val1 = table.getSelectedColumn();// val1为编辑的列  
          //System.out.println(val1);  
          String val2 = (String) table.getValueAt(table.getSelectedRow(), 0);// 主键内容  
          // System.out.println(val2); 
           try {
			System.out.println(rsmd.getColumnName(val1 + 1));
		} catch (SQLException e2) {
			// TODO 自动生成的 catch 块
			e2.printStackTrace();
		}
           String sql = null;  
           try { 
        	   if(val1==0){
        		   System.out.println("编辑前的值为"+stfri);
        		   sql = "update Stuinfo set " + "["  
                           + rsmd.getColumnName(val1 + 1) + "]" + "=" + "'" + val0  
                           + "'" + " where Sno=" + "'" + stfri + "'";
        	   }
        	   else{
        		   sql = "update Stuinfo set " + "["  
                       + rsmd.getColumnName(val1 + 1) + "]" + "=" + "'" + val0  
                       + "'" + " where Sno=" + "'" + val2 + "'";}  
            } catch (SQLException e1) {  
                // TODO Auto-generated catch block  
                e1.printStackTrace();  
            }  
           boolean bool = false;
           //更新数据库
          try{
       		Sqlcon scon =new Sqlcon();
   	     Connection conn = scon.getConnection();
   	     PreparedStatement ppst=conn.prepareStatement(sql);	
   	     ppst.executeUpdate();
   	     //System.out.println("执行完更新");
   	  
      
    }catch(SQLException e3){
       		  
       	  }
       	  
            bool = true;  
           if (bool == true) {  
                JOptionPane.showMessageDialog(this, "修改成功！");  
            } else {  
               JOptionPane.showMessageDialog(this, "修改失败！");  
            }  
           scrollPane.setViewportView(table);
        }
   
    }
	
	
	}
