


import java.sql.*; 
public class Sqlcon {  
public Connection getConnection(){ 
   Connection conn=null;
  try
 {  
  Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();
  //注册驱动
  //System.out.println("JDBC驱动正常可用"); 
  }
  catch(Exception e){ 
  e.printStackTrace();  
  //System.out.println("JDBC驱动不可用");  
 } 
  try{  
  String url = "jdbc:sqlserver://localhost:1433;DatabaseName=Student"; 
  String user = "sa";
  //数据库登录名
  String password = "";
  //登录密码
  conn = DriverManager.getConnection(url, user, password); 
  //System.out.println("数据库连接正常可用"); 
  Statement stmt = conn.createStatement();
  //System.out.println( "创建Statement成功!" ); 
  }
  catch(Exception e){ 
	e.printStackTrace();  
  //System.out.println("数据库连接失败"); 
  }  
  return conn; 
  } 


  public static void main(String[] args) { 
  //TODO Auto-generated method stub
  Sqlcon s=new Sqlcon(); 
  s.getConnection(); 
  }  
}
