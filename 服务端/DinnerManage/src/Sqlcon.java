


import java.sql.*; 
public class Sqlcon {  
public Connection getConnection(){ 
   Connection conn=null;
  try
 {  
  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
  //ע������
  //System.out.println("JDBC������������"); 
  }
  catch(Exception e){ 
  e.printStackTrace();  
  //System.out.println("JDBC����������");  
 } 
  try{  
  String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Student"; 
  String user = "sa";
  //���ݿ��¼��
  String password = "";
  //��¼����
  conn = DriverManager.getConnection(url, user, password); 
  //System.out.println("���ݿ�������������"); 
  Statement stmt = conn.createStatement();
  //System.out.println( "����Statement�ɹ�!" ); 
  }
  catch(Exception e){ 
	e.printStackTrace();  
  //System.out.println("���ݿ�����ʧ��"); 
  }  
  return conn; 
  } 


  public static void main(String[] args) { 
  //TODO Auto-generated method stub
  Sqlcon s=new Sqlcon(); 
  s.getConnection(); 
  }  
}
