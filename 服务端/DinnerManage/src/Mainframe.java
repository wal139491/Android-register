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
					frame.setTitle("ѧ����Ϣ����");
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
		
	    //��ѧ�Ų�ѯ
		button_2.addActionListener(this);
        button_2.setBounds(358, 6, 66, 23);
		contentPane.add(button_2);
		
		//���水ť
		button_3.addActionListener(this);
		button_3.setBounds(32, 54, 66, 23);
		contentPane.add(button_3);
		
		//ɾ����ť
		button_4.addActionListener(this);
	    button_4.setBounds(119, 54, 75, 23);
		contentPane.add(button_4);
		
		//���������б��ť
		button_5.addActionListener(this);
		button_5.setBounds(330, 54, 94, 23);
		contentPane.add(button_5);

	    //��������ѯ
		button_1.addActionListener(this);
		button_1.setBounds(142, 6, 75, 23);
		contentPane.add(button_1);
		
		//�����б��
	   comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"�������","�������ѧ","���繤��","��Ϣ����"}));
		comboBox.setBounds(226, 55, 94, 21);
		contentPane.add(comboBox);
		
		
		
		
		
       
		
	}

	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent e) {
		// TODO �Զ����ɵķ������
	   //��������ѯ
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
		        
	              colum.addElement("ѧ��");
	              colum.addElement("����");
	              colum.addElement("רҵ");
	              colum.addElement("�꼶");
	              if(!rs.next()){
	            	  JOptionPane.showMessageDialog(this, "û����������"); 
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
        //��ѧ�Ų�ѯ
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
	       
	              colum.addElement("ѧ��");//������������ֶ� 
	              colum.addElement("����");
	              colum.addElement("רҵ");
	              colum.addElement("�꼶");
	              if(!rs.next()){
	            	  JOptionPane.showMessageDialog(this, "û����������"); 
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
        
     
    //ɾ����������
      if(e.getSource()==button_4){
    	  int n = JOptionPane.showConfirmDialog(null, "ȷ��ɾ����?", "ȷ��ɾ����", JOptionPane.YES_NO_OPTION);	
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
             /*ɾ�����еĹ���
             int row = table.getSelectedRow();
             model=new DefaultTableModel(rows, colum); 
             model.removeRow(row);*/
             int[] rowIndexes = table.getSelectedRows();  
             for (int i = 0; i < rowIndexes.length; i++)  
             {  
               //ת��ΪModel��������������Ҫ��������������Ӧ  
                rowIndexes[i] = table.convertRowIndexToModel(rowIndexes[i]);  
            }  
            //����������Ҫ������˳�����ҵ�  
            Arrays.sort(rowIndexes);  
              
            model = (DefaultTableModel) table.getModel();  
             //����ɾ��  
           for(int i=rowIndexes.length-1; i>=0; i--){  
               model.removeRow(rowIndexes[i]);  
                //��Model��Ӧ���б����ݣ����и���Ԫ����  
                //MainFrame.resultList.remove(rowIndexes[i]);  
           }  
       JOptionPane.showMessageDialog(this, "ɾ���ɹ���");  
          } 
    		 
    	  
         }
    		
    
    	  
      
      /**
  	 * ���������б���ֵ��ѯѧ����Ϣ
  	 */
      
      if(e.getSource()==button_5){
    	  Sqlcon scon =new Sqlcon();
			Connection conn = scon.getConnection();
    	  int r=comboBox.getSelectedIndex()+1;//ѡ�е��ǵڼ���
    	  String major=(String)comboBox.getSelectedItem();//ѡ�е�ֵ
    	  try{
    	  String ssql="Select Stuinfo.Sno,Stuinfo.Sname,Stuinfo.Smajor,Stuinfo.Sgrade from Stuinfo where Stuinfo.Smajor=?  ";
    	  PreparedStatement  pstmt=conn.prepareStatement(ssql);
		     pstmt.setString(1,major);
		     ResultSet rs = pstmt.executeQuery();
		     rsmd = rs.getMetaData();
		              colum.addElement("ѧ��");
		              colum.addElement("����");
		              colum.addElement("רҵ");
		              colum.addElement("�꼶");
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
      
      // �޸ĵ�Ԫ�����ݲ�����  
      if (e.getSource() == button_3) {  
            String stfri=(String) table.getValueAt(table.getSelectedRow(), 0);//�༭ǰ��cell��ֵ
    	 /* if (table.isEditing()){ 
           table.getCellEditor().stopCellEditing();  }*/
            String val0 = (String) table.getValueAt(table.getSelectedRow(),  
            table.getSelectedColumn());  
          // System.out.println(val0);// val0Ϊȡ�ñ༭������  
          int val1 = table.getSelectedColumn();// val1Ϊ�༭����  
          //System.out.println(val1);  
          String val2 = (String) table.getValueAt(table.getSelectedRow(), 0);// ��������  
          // System.out.println(val2); 
           try {
			System.out.println(rsmd.getColumnName(val1 + 1));
		} catch (SQLException e2) {
			// TODO �Զ����ɵ� catch ��
			e2.printStackTrace();
		}
           String sql = null;  
           try { 
        	   if(val1==0){
        		   System.out.println("�༭ǰ��ֵΪ"+stfri);
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
           //�������ݿ�
          try{
       		Sqlcon scon =new Sqlcon();
   	     Connection conn = scon.getConnection();
   	     PreparedStatement ppst=conn.prepareStatement(sql);	
   	     ppst.executeUpdate();
   	     //System.out.println("ִ�������");
   	  
      
    }catch(SQLException e3){
       		  
       	  }
       	  
            bool = true;  
           if (bool == true) {  
                JOptionPane.showMessageDialog(this, "�޸ĳɹ���");  
            } else {  
               JOptionPane.showMessageDialog(this, "�޸�ʧ�ܣ�");  
            }  
           scrollPane.setViewportView(table);
        }
   
    }
	
	
	}
