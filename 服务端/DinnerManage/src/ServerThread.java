

import java.io.*;
import java.net.*;
import java.sql.Connection;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;




import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

//负责处理每个线程通信的线程类
public class ServerThread implements Runnable
{
    //定义当前线程所处理的Socket
    Socket s = null;
    //该线程所处理的Socket所对应的输入流
    BufferedReader br = null;
    BufferedWriter  out=null;
    PrintWriter sout=null;
    Manager mag;
    String ip;
   
    String port;
    String grade,major;
    String sgrade,smajor;
    String sql1;
    private String showc;
    String zhouyi1,zhouyi2,zhouyi3,zhouyi4,zhouyi5;
    String zhouer1,zhouer2,zhouer3,zhouer4,zhouer5;
    String zhousan1,zhousan2,zhousan3,zhousan4,zhousan5;
    String zhousi1,zhousi2,zhousi3,zhousi4,zhousi5;
    String zhouwu1,zhouwu2,zhouwu3,zhouwu4,zhouwu5;
    String zhouliu1,zhouliu2,zhouliu3,zhouliu4,zhouliu5;
    String zhouri1,zhouri2,zhouri3,zhouri4,zhouri5;
    public ServerThread(Socket s,String ip,String port)
        throws IOException
    {
        this.s = s;
        this.ip=ip;
        this.port=port;
        //初始化该Socket对应的输入流  解决中文乱码 使用utf-8
        br = new BufferedReader(new InputStreamReader(
            s.getInputStream() , "utf-8"));  
        out=new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));
        sout = new PrintWriter(s.getOutputStream());
    }
    public void run()
    {
        try
        {   /*Manager ma=new Manager();
        	String sip=ma.textField.getText();
		   String sport=ma.textField_1.getText();
        	 System.out.println(sip);
        	 System.out.println(sport);*/
            String content = null;
            //采用循环不断从Socket中读取客户端发送过来的数据
        	Gson gson =new Gson();
        	
        	
            if ((content = readFromClient()) != null)
            {
            	
               
            	/*String ostr="yes";
            	OutputStream os = s.getOutputStream();
                os.write((ostr + "\n").getBytes("utf-8"));*/
			    
            	
            	Map<String, String> retMap = gson.fromJson(content,  
			               new TypeToken<Map<String, String>>() {  
			                }.getType());
           if(retMap.size()==2)
	            	{
        	 //向手机端发送数据
            	String ostr="yes";
           	 OutputStream os = s.getOutputStream();
               os.write((ostr + "\n").getBytes("utf-8"));

	            		String jstr=(String)retMap.get("ceshi");
	            		String imei=(String)retMap.get("imei");
	            		
	            		try {
	            			 //判断当前时间
        					if(jstr.equals("ok")){
        						//返回信息
        		       
	            			Sqlcon scon =new Sqlcon();
	            			Connection conn = scon.getConnection();
	            			//Statement smt=conn.createStatement();
	            			String sql="INSERT into dbo.Time values(?,?);";//sql语句中末尾的分号可有可无
	            		    PreparedStatement ppst=conn.prepareStatement(sql);
	            		    ppst.setString(1,imei);//对sql语句中的每个？按顺序赋值,赋值的方法取决于数据库的字段属性，我的字段属性都是字符串类型
	            		    Date date=new java.util.Date();
	            		    DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");//设置显示格式
	            		    String nowTime= df.format(date);//用DateFormat的format()方法在dt中获取并以yyyy/MM/dd HH:mm:ss格式显示

	            		    //System.out.println(nowTime);
	            		   
	            		    ppst.setString(2, nowTime);
	            		    ppst.executeUpdate();
	            	        //System.out.println("手机端签到成功");
	            			//smt.close();
	            			ppst.close();
	            			conn.close();
	            			out.close();
        					}
	            		} catch (SQLException e) {
	            			// TODO Auto-generated catch block
	            			e.printStackTrace();
	            		}
	            
	            	}
             if(retMap.size()==1){
            	
				  Map map = new HashMap();
				  
				  map.put("ip",ip);
				  map.put("port",port);
				  
			  String reip = gson.toJson(map);
			      sout.println(reip);
				  sout.flush();  
              }
            	if(retMap.size()==5){
            	
            String  sno= (String)retMap.get("sno");
	        String sname=(String)retMap.get("sname");
            String smajor= (String)retMap.get("smajor");
            String imei= (String)retMap.get("imei");
            String sgrade= (String)retMap.get("sgrage");
            try{
        	Sqlcon scon =new Sqlcon();
			Connection conn = scon.getConnection();
			//Statement smt=conn.createStatement();
			String sql="INSERT into dbo.Stuinfo values(?,?,?,?,?);";//sql语句中末尾的分号可有可无
			String sqll="Select * from dbo.Stuinfo where imei=?";
		    PreparedStatement  pstmt=conn.prepareStatement(sqll);
		    pstmt.setString(1,imei);
		    ResultSet rs = pstmt.executeQuery();
		    if(!rs.next()){
		    PreparedStatement ppst=conn.prepareStatement(sql);	
		    ppst.setString(1, sno);//对sql语句中的每个？按顺序赋值,赋值的方法取决于数据库的字段属性，字段属性都是字符串类型
		    ppst.setString(2, sname);
		    ppst.setString(3, smajor);
		    ppst.setString(4, sgrade);
		    ppst.setString(5, imei);
		    ppst.executeUpdate();
			//向手机端发送数据
           	String pstr="keyi";
           	 OutputStream os = s.getOutputStream();
               os.write((pstr + "\n").getBytes("utf-8"));
        	  
			ppst.close();
			conn.close();
			out.close();
		    }
		    else{
		    	//向手机端发送数据
               	String ostr="no";
               	 OutputStream out = s.getOutputStream();
                   out.write((ostr + "\n").getBytes("utf-8"));
		    	}
            }catch (SQLException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
         
            }
           //返回课表信息
            	if(retMap.size()==3){
            		String  imstr= (String)retMap.get("imei");
            try{
           		 Sqlcon scon =new Sqlcon();
        			Connection conn = scon.getConnection();
        			String sql="Select Sgrade,Smajor from dbo.Stuinfo where Imei=? ";
        			
        			PreparedStatement  pstmt=conn.prepareStatement(sql);
        		    pstmt.setString(1,imstr);
        		     ResultSet rs = pstmt.executeQuery();
        		     if(rs.next()){
        		    	  grade=rs.getString(1);
        		    	  major=rs.getString(2);
        		      }
        		    // System.out.println("学生的年级为："+grade);
        		    // System.out.println("学生的专业为："+major);
        		   
        		     if(major.equals("软件工程")){
        		         sql1="Select Quarte12,Quarte34,Quarte56,Quarte78,Quarte90 from dbo.Rtable where Week=? and Grade=? ";
        		      }
        		        if(major.equals("计算机科学")){
        		         sql1="Select Quarte12,Quarte34,Quarte56,Quarte78,Quarte90 from dbo.Jtable where Week=? and Grade=? ";
        		      }
        		        if(major.equals("网络工程")){
        		         sql1="Select Quarte12,Quarte34,Quarte56,Quarte78,Quarte90 from dbo.Wtable where Week=? and Grade=? ";
        		      }
        		       if(major.equals("信息管理")){
        		         sql1="Select Quarte12,Quarte34,Quarte56,Quarte78,Quarte90 from dbo.Xtable where Week=? and Grade=? ";
        		      }
        		 	//周一
        		        
        		         PreparedStatement  psstmt=conn.prepareStatement(sql1);
            		     psstmt.setString(1,"星期一");
            		     psstmt.setString(2,grade);
            	    	ResultSet rs1 = psstmt.executeQuery();
            		    while(rs1.next()){
            		     zhouyi1=rs1.getString("Quarte12");
            		     zhouyi2=rs1.getString("Quarte34");
            		     zhouyi3=rs1.getString("Quarte56");
            		     zhouyi4=rs1.getString("Quarte78");
            		     zhouyi5=rs1.getString("Quarte90");
            	     }
            		  //周二
       		         PreparedStatement  psstm3=conn.prepareStatement(sql1);
           		     psstm3.setString(1,"星期二");
           		     psstm3.setString(2,grade);
           	    	ResultSet rs3 = psstm3.executeQuery();
           		    while(rs3.next()){
           		     zhouer1=rs3.getString("Quarte12");
           		     zhouer2=rs3.getString("Quarte34");
           		     zhouer3=rs3.getString("Quarte56");
           		     zhouer4=rs3.getString("Quarte78");
           		     zhouer5=rs3.getString("Quarte90");
             	    }
            		    
            		    //周三
            		  PreparedStatement  pstmts=conn.prepareStatement(sql1);
           		      pstmts.setString(1,"星期三");
           		      pstmts.setString(2,grade);
           		      ResultSet rs2 = pstmts.executeQuery();
           		    if(rs2.next()){
           		     zhousan1=rs2.getString("Quarte12");
        		     zhousan2=rs2.getString("Quarte34");
        		     zhousan3=rs2.getString("Quarte56");
        		     zhousan4=rs2.getString("Quarte78");
        		     zhousan5=rs2.getString("Quarte90");
           		    }
           		    
           		  //周四
      		         PreparedStatement  psstm4=conn.prepareStatement(sql1);
          		     psstm4.setString(1,"星期四");
          		     psstm4.setString(2,grade);
          	    	ResultSet rs4 = psstm4.executeQuery();
          		    while(rs4.next()){
          		     zhousi1=rs4.getString("Quarte12");
          		     zhousi2=rs4.getString("Quarte34");
          		     zhousi3=rs4.getString("Quarte56");
          		     zhousi4=rs4.getString("Quarte78");
          		     zhousi5=rs4.getString("Quarte90");
            	    }
          		    
          		  //周五
     		        PreparedStatement  psstm5=conn.prepareStatement(sql1);
         		     psstm5.setString(1,"星期五");
         		     psstm5.setString(2,grade);
         	    	ResultSet rs5 = psstm5.executeQuery();
         		    while(rs5.next()){
         		     zhouwu1=rs5.getString("Quarte12");
         		     zhouwu2=rs5.getString("Quarte34");
         		     zhouwu3=rs5.getString("Quarte56");
         		     zhouwu4=rs5.getString("Quarte78");
         		     zhouwu5=rs5.getString("Quarte90");
           	    }
         		   //周六
    		         PreparedStatement  psstm6=conn.prepareStatement(sql1);
        		     psstm6.setString(1,"星期六");
        		     psstm6.setString(2,grade);
        	    	ResultSet rs6 = psstm6.executeQuery();
        		    while(rs6.next()){
        		     zhouliu1=rs6.getString("Quarte12");
        		     zhouliu2=rs6.getString("Quarte34");
        		     zhouliu3=rs6.getString("Quarte56");
        		     zhouliu4=rs6.getString("Quarte78");
        		     zhouliu5=rs6.getString("Quarte90");
          	    }
        		  //周日
   		         PreparedStatement  psstm7=conn.prepareStatement(sql1);
       		     psstm7.setString(1,"星期日");
       		     psstm7.setString(2,grade);
       	    	ResultSet rs7 = psstm7.executeQuery();
       		    while(rs7.next()){
       		     zhouri1=rs7.getString("Quarte12");
       		     zhouri2=rs7.getString("Quarte34");
       		     zhouri3=rs7.getString("Quarte56");
       		     zhouri4=rs7.getString("Quarte78");
       		     zhouri5=rs7.getString("Quarte90");
         	    }
           		    //转换添加\n
           		      //周一转
           		     String jzhouyi1=zhouyi1.replaceAll("/n","\n");
           		     String jzhouyi2=zhouyi2.replaceAll("/n","\n");
           		     String jzhouyi3=zhouyi3.replaceAll("/n","\n");
           		     String jzhouyi4=zhouyi4.replaceAll("/n","\n");  
           		     String jzhouyi5=zhouyi5.replaceAll("/n","\n");
           		    //周二转
           		     String jzhouer1=zhouer1.replaceAll("/n","\n");
           		     String jzhouer2=zhouer2.replaceAll("/n","\n");
           		     String jzhouer3=zhouer3.replaceAll("/n","\n");
           		     String jzhouer4=zhouer4.replaceAll("/n","\n");  
           		     String jzhouer5=zhouer5.replaceAll("/n","\n");
           		     //周三转
           		     String jzhousan1=zhousan1.replaceAll("/n","\n");
        		     String jzhousan2=zhousan2.replaceAll("/n","\n");
        		     String jzhousan3=zhouyi3.replaceAll("/n","\n");
        		     String jzhousan4=zhousan4.replaceAll("/n","\n");  
        		     String jzhousan5=zhousan5.replaceAll("/n","\n");
        		     //周四转
           		     String jzhousi1=zhousi1.replaceAll("/n","\n");
        		     String jzhousi2=zhousi2.replaceAll("/n","\n");
        		     String jzhousi3=zhousi3.replaceAll("/n","\n");
        		     String jzhousi4=zhousi4.replaceAll("/n","\n");  
        		     String jzhousi5=zhousi5.replaceAll("/n","\n");
        		     //周五转
           		     String jzhouwu1=zhouwu1.replaceAll("/n","\n");
        		     String jzhouwu2=zhouwu2.replaceAll("/n","\n");
        		     String jzhouwu3=zhouwu3.replaceAll("/n","\n");
        		     String jzhouwu4=zhouwu4.replaceAll("/n","\n");  
        		     String jzhouwu5=zhouwu5.replaceAll("/n","\n");
        	
         		    //发送数据
            		   Map<String, String> map = new HashMap();
            	      map.put("zhouyi1",jzhouyi1);
            	      map.put("zhouyi2",jzhouyi2);
            	      map.put("zhouyi3",jzhouyi3);
            	      map.put("zhouyi4",jzhouyi4);
            	      map.put("zhouyi5",jzhouyi5);
            	      
            	      map.put("zhouer1",jzhouer1);
            	      map.put("zhouer2",jzhouer2);
            	      map.put("zhouer3",jzhouer3);
            	      map.put("zhouer4",jzhouer4);
            	      map.put("zhouer5",jzhouer5);
            	      
            	      map.put("zhousan1",jzhousan1);
            	      map.put("zhousan2",jzhousan2);
            	      map.put("zhousan3",jzhousan3);
            	      map.put("zhousan4",jzhousan4);
            	      map.put("zhousan5",jzhousan5);
            	      
            	      map.put("zhousi1",jzhousi1);
            	      map.put("zhousi2",jzhousi2);
            	      map.put("zhousi3",jzhousi3);
            	      map.put("zhousi4",jzhousi4);
            	      map.put("zhousi5",jzhousi5);
            	      
            	      map.put("zhouwu1",jzhouwu1);
            	      map.put("zhouwu2",jzhouwu2);
            	      map.put("zhouwu3",jzhouwu3);
            	      map.put("zhouwu4",jzhouwu4);
            	      map.put("zhouwu5",jzhouwu5);
            	      
            	    
    			    String json = gson.toJson(map);
    			      sout.println(json);
    				 sout.flush();
        		    
    				 sout.close();
        		   
        		    }catch(SQLException  e){
        		    	e.printStackTrace();
        		    	
        		    }

            	}
            	if(retMap.size()==4){
            		 String imei= (String)retMap.get("imei");
                      String time= (String)retMap.get("time");
                     // String stime= (String)retMap.get("stime");
                     Date date=new java.util.Date();
          		    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置显示格式
          		    String nowtime= df.format(date);
                    String sql="Select tongzhi from dbo.Tongzhi where grade=? and major=? and date=? ";
                     try {
                    	 Sqlcon scon =new Sqlcon();
             			Connection conn = scon.getConnection();
             			String sqle="Select Sgrade,Smajor from dbo.Stuinfo where Imei=? ";
             			
             			PreparedStatement  pstmt=conn.prepareStatement(sqle);
             		    pstmt.setString(1,imei);
             		     ResultSet rs = pstmt.executeQuery();
             		     if(rs.next()){
             		    	  sgrade=rs.getString(1);
             		    	  smajor=rs.getString(2);
             		      }
                    	 
             		  PreparedStatement  pst=conn.prepareStatement(sql);
           		     pst.setString(1,sgrade);
           		     pst.setString(2,smajor);
           		     if(time.equals("lw")){
           		       pst.setString(3,nowtime); 
           		     }else{
           		     pst.setString(3,time);
           		   
           		     }
           	    	ResultSet rss = pst.executeQuery();
           	    	if(rss.next()){
           	    		 showc=rss.getString("tongzhi");
           	    		
           	    	}else{
           	    		 showc="";
           	    	}
           	     //发送数据
           		   Map<String, String> map = new HashMap();
                   	 map.put("tongzhi",showc);
                      String json = gson.toJson(map);
    			         sout.println(json);
    				      sout.flush();
           	    	
					} catch (SQLException  e1) {
						// TODO: handle exception
					}
                     
             
                    
            	}
         }
       }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    //定义读取客户端数据的方法
    private String readFromClient() throws IOException
    {
        try
        {
            return br.readLine();   
        }
        //如果捕捉到异常，表明该Socket对应的客户端已经关闭
        catch (IOException e)
        {
          s.close();  
        }
        return null;
    }

}
