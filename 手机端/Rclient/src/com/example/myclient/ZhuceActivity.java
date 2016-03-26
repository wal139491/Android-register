package com.example.myclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

import android.app.Activity;
import android.content.Context;

import android.os.Bundle;
import android.os.Looper;

import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



public class ZhuceActivity extends Activity {
	private EditText et1;
	private EditText et2;
	private EditText et3;
	private EditText et4;
	private Button btn;
	private EditText et5;
	/*手机串号*/
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.zhuce);
		et1 =(EditText)findViewById(R.id.edt1);
		et2=(EditText)findViewById(R.id.edt2);
		et3=(EditText)findViewById(R.id.edt3);
		et4=(EditText)findViewById(R.id.edt4);
		btn=(Button)findViewById(R.id.send);
		et5=(EditText)findViewById(R.id.edt5);
		btn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				new SocketClientThread().start();
			
			}
		});
	
}
	class SocketClientThread extends Thread{
        @Override
		public void run() {
			// TODO Auto-generated method stub
		  try {
			  String st1=et1.getText().toString();
			  String st2=et2.getText().toString();
			  String st3=et3.getText().toString();
			  String st4=et4.getText().toString();
			  String ip=et5.getText().toString();
			  Context context = getWindow().getContext();        
			    TelephonyManager telephonemanage = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);              
			    String stri = telephonemanage.getDeviceId();
			//jsonData="{\"sname\":\"st1\",\"sno\":\"st2\",\"smajor\":st3,\"sgrade\":\"st4\"}";
		  @SuppressWarnings("resource")
		  Socket socket = new Socket(InetAddress.getByName(ip),6789);
		  PrintWriter out = new PrintWriter(socket.getOutputStream());
		BufferedReader in= new BufferedReader(new InputStreamReader(
	            socket.getInputStream() , "utf-8")); 
		 Gson gson = new Gson();  
		  while(true){
		  Map map = new HashMap();
		  map.put("sname",st1);
		  map.put("sno",st2);
		  map.put("smajor",st3);
		  map.put("sgrage",st4);
		  map.put("imei",stri);
		  String json = gson.toJson(map);
	      out.println(json);
		  out.flush();
		  String back=in.readLine();
			if(back.equals("keyi")){
				Looper.prepare();
            Toast.makeText(ZhuceActivity.this, "注册成功",
					     Toast.LENGTH_SHORT).show();
				  Looper.loop();// 进入loop中的循环，查看消息队列
				
			}
			if(back.equals("no")){
				Looper.prepare();
	            Toast.makeText(ZhuceActivity.this, "该手机已注册，请勿重复注册",
						     Toast.LENGTH_SHORT).show();
					  Looper.loop();// 进入loop中的循环，查看消息队列
			}
		
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
