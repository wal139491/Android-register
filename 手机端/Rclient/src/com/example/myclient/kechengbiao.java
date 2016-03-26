package com.example.myclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import com.example.myclient.ZhuceActivity.SocketClientThread;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class kechengbiao extends Activity {
	private final int IS_FINISH=1;
	String value,rcontent;
	private Button cbtn;
	private ProgressDialog dialog=null;
	private Button mon12,mon34,mon56,mon78,mon90;
	private Button tue12,tue34,tue56,tue78,tue90;
	private Button wed12,wed34,wed56,wed78,wed90;
	private Button thu12,thu34,thu56,thu78,thu90;
	private Button fri12,fri34,fri56,fri78,fri90;
	private Button sat12,sat34,sat56,sat78,sat90;
	private Button sun12,sun34,sun56,sun78,sun90;
	private Handler handler=new Handler(){
		public void handleMessage(Message msg){
			 Gson gson = new Gson();  
			rcontent=(String) msg.obj;
			Map<String, String> retMap = gson.fromJson(rcontent,  
		               new TypeToken<Map<String, String>>() {  
		                }.getType());
			int sge=retMap.size();
			
			//获取周几的课表
			 String  zhouyi1= retMap.get("zhouyi1");
			 String  zhouyi2= retMap.get("zhouyi2");
			 String  zhouyi3= retMap.get("zhouyi3");
			 String  zhouyi4= retMap.get("zhouyi4");
			 String  zhouyi5= retMap.get("zhouyi5");
			 
			 String  zhouer1= retMap.get("zhouer1");
			 String  zhouer2= retMap.get("zhouer2");
			 String  zhouer3= retMap.get("zhouer3");
			 String  zhouer4= retMap.get("zhouer4");
			 String  zhouer5= retMap.get("zhouer5");
			 
			 String zhousan1=(String)retMap.get("zhousan1");
			 String zhousan2=(String)retMap.get("zhousan2");
			 String zhousan3=(String)retMap.get("zhousan3");
			 String zhousan4=(String)retMap.get("zhousan4");
			 String zhousan5=(String)retMap.get("zhousan5");
			 
			 String  zhousi1= retMap.get("zhousi1");
			 String  zhousi2= retMap.get("zhousi2");
			 String  zhousi3= retMap.get("zhousi3");
			 String  zhousi4= retMap.get("zhousi4");
			 String  zhousi5= retMap.get("zhousi5");
			 
			 String  zhouwu1= retMap.get("zhouwu1");
			 String  zhouwu2= retMap.get("zhouwu2");
			 String  zhouwu3= retMap.get("zhouwu3");
			 String  zhouwu4= retMap.get("zhouwu4");
			 String  zhouwu5= retMap.get("zhouwu5");
			 
			 String  zhouliu1= retMap.get("zhouliu1");
			 String  zhouliu2= retMap.get("zhouliu2");
			 String  zhouliu3= retMap.get("zhouliu3");
			 String  zhouliu4= retMap.get("zhouliu4");
			 String  zhouliu5= retMap.get("zhouliu5");
			 
			 String  zhouri1= retMap.get("zhouri1");
			 String  zhouri2= retMap.get("zhouri2");
			 String  zhouri3= retMap.get("zhouri3");
			 String  zhouri4= retMap.get("zhouri4");
			 String  zhouri5= retMap.get("zhouri5");
			 //设置UI
			 for(int i=1;i<=sge;i++){
			  mon12.setText(zhouyi1);
		      mon34.setText(zhouyi2);
		      mon56.setText(zhouyi3);
		      mon78.setText(zhouyi4);
		      mon90.setText(zhouyi5);
		      
		      tue12.setText(zhouer1);
		      tue34.setText(zhouer2);
		      tue56.setText(zhouer3);
		      tue78.setText(zhouer4);
		      tue90.setText(zhouer5);
		      
		      wed12.setText(zhousan1);
		      wed34.setText(zhousan2);
		      wed56.setText(zhousan3);
		      wed78.setText(zhousan4);
		      wed90.setText(zhousan5);
		      
		      thu12.setText(zhousi1);
		      thu34.setText(zhousi2);
		      thu56.setText(zhousi3);
		      thu78.setText(zhousi4);
		      thu90.setText(zhousi5);
		      
		      fri12.setText(zhouwu1);
		      fri34.setText(zhouwu2);
		      fri56.setText(zhouwu3);
		      fri78.setText(zhouwu4);
		      fri90.setText(zhouwu5);
		      
		      sat12.setText(zhouliu1);
		      sat34.setText(zhouliu2);
		      sat56.setText(zhouliu3);
		      sat78.setText(zhouliu4);
		      sat90.setText(zhouliu5);
		      
		      sun12.setTag(zhouri1);
		      sun34.setTag(zhouri2);
		      sun56.setTag(zhouri3);
		      sun78.setTag(zhouri4);
		      sun90.setTag(zhouri5);
		    }
			
			//是dialog消失
			if(msg.what==IS_FINISH){
				dialog.dismiss();
			}
		 }
		
	};
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kechengbiao);
		cbtn=(Button)findViewById(R.id.kebiao);
		dialog=new ProgressDialog(this);
		dialog.setTitle("提示");
		dialog.setMessage("正在加载课表，请稍后...");
		//在此处修改
		//dialog.setCancelable(false);
		dialog.setCancelable(true);
		mon12=(Button)findViewById(R.id.mon_first);
		mon34=(Button)findViewById(R.id.mon_second);
		mon56=(Button)findViewById(R.id.mon_third);
		mon78=(Button)findViewById(R.id.mon_fourth);
		mon90=(Button)findViewById(R.id.mon_fifth);
		
		tue12=(Button)findViewById(R.id.tues_first);
		tue34=(Button)findViewById(R.id.tues_second);
		tue56=(Button)findViewById(R.id.tues_third);
		tue78=(Button)findViewById(R.id.tues_fourth);
		tue90=(Button)findViewById(R.id.tues_fifth);
		
		wed12=(Button)findViewById(R.id.wed_first);
		wed34=(Button)findViewById(R.id.wed_second);
		wed56=(Button)findViewById(R.id.wed_third);
		wed78=(Button)findViewById(R.id.wed_fourth);
		wed90=(Button)findViewById(R.id.wed_fifth);
		
		thu12=(Button)findViewById(R.id.thurs_first);
		thu34=(Button)findViewById(R.id.thurs_second);
		thu56=(Button)findViewById(R.id.thurs_third);
		thu78=(Button)findViewById(R.id.thurs_fourth);
		thu90=(Button)findViewById(R.id.thurs_fifth);
		
		fri12=(Button)findViewById(R.id.fri_first);
		fri34=(Button)findViewById(R.id.fri_second);
		fri56=(Button)findViewById(R.id.fri_third);
		fri78=(Button)findViewById(R.id.fri_fourth);
		fri90=(Button)findViewById(R.id.fri_fifth);
		
		sat12=(Button)findViewById(R.id.sat_first);
		sat34=(Button)findViewById(R.id.sat_second);
		sat56=(Button)findViewById(R.id.sat_third);
		sat78=(Button)findViewById(R.id.sat_fourth);
		sat90=(Button)findViewById(R.id.sat_fifth);
		
		sun12=(Button)findViewById(R.id.sun_first);
		sun34=(Button)findViewById(R.id.sun_second);
		sun56=(Button)findViewById(R.id.sun_third);
		sun78=(Button)findViewById(R.id.sun_fourth);
		sun90=(Button)findViewById(R.id.sun_fifth);
		Intent intent = getIntent();
		value = intent.getStringExtra("ip");
		cbtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			
				 new KbThread().start();
			     dialog.show();
			}
		});
	}
	class KbThread extends Thread{
		public void run(){
			try {
				 Context context = getWindow().getContext();        
				    TelephonyManager telephonemanage = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);              
				    String ime = telephonemanage.getDeviceId();
				    
					Socket socket = new Socket(InetAddress.getByName(value),6789);
					  PrintWriter out = new PrintWriter(socket.getOutputStream());
					BufferedReader in= new BufferedReader(new InputStreamReader(
				            socket.getInputStream() , "GB2312"));
					
					   Gson gson = new Gson();  
						  while(true){
						  Map<String, String> map = new HashMap();
						  map.put("qingqiu","qq");
						  map.put("kebiao", "kb");
						  map.put("imei",ime);
						  
					  String json = gson.toJson(map);
					      out.println(json);
						  out.flush();
						  
						String content=in.readLine();
						if(content!=null){
						Message message=Message.obtain();
						message.obj=content;
						message.what=IS_FINISH;
						handler.sendMessage(message);
						}
						if(content==null||content.equals("")){
							Looper.prepare();
				            Toast.makeText(kechengbiao.this, "请检查网络连接，服务端是否打开",
									     Toast.LENGTH_SHORT).show();
								  Looper.loop();// 进入loop中的循环，查看消息队列
							}
						/*if(content!= null){
							Map<String, String> retMap = gson.fromJson(content,  
						               new TypeToken<Map<String, String>>() {  
						                }.getType());
							int sge=retMap.size();
							
							//获取周几的课表
							 String  zhouyi1= retMap.get("zhouyi1");
							 String  zhouyi2= retMap.get("zhouyi2");
							 String  zhouyi3= retMap.get("zhouyi3");
							 String  zhouyi4= retMap.get("zhouyi4");
							 String  zhouyi5= retMap.get("zhouyi5");
							 String zhousan1=(String)retMap.get("zhousan1");
							 String zhousan2=(String)retMap.get("zhousan2");
							 String zhousan3=(String)retMap.get("zhousan3");
							 String zhousan4=(String)retMap.get("zhousan4");
							 String zhousan5=(String)retMap.get("zhousan5");
							 //设置UI
							 for(int i=1;i<=sge;i++){
							  mon12.setText(zhouyi1);
						      mon34.setText(zhouyi2);
						      mon56.setText(zhouyi3);
						      mon78.setText(zhouyi4);
						      mon90.setText(zhouyi5);
						      wed12.setText(zhousan1);
						      wed34.setText(zhousan2);
						      wed56.setText(zhousan3);
						      wed78.setText(zhousan4);
						      wed90.setText(zhousan5);
						      
							 }
						  }*/
						 in.close();
						 out.close();
						 socket.close();
						  }
						  
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
		
	}
	
	
	
	
}
