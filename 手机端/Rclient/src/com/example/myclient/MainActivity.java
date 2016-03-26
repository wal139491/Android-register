

package com.example.myclient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;



import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private TextView tv1;
    private Button bt2;
    private Button bt1;
    private Button btn; 
    private Button kbtn;
    private Button tongbtn;
    Handler handler;
    private SharedPreferences sp;
    private EditText edt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv1= (TextView)findViewById(R.id.myview);
		bt2=(Button)findViewById(R.id.bt2);
       bt1=(Button)findViewById(R.id.bt1);
	   btn=(Button)findViewById(R.id.cha);
	   edt=(EditText)findViewById(R.id.edit);
	   kbtn=(Button)findViewById(R.id.kbutton);
	   tongbtn=(Button)findViewById(R.id.tongbtn);
	   sp = this.getSharedPreferences("userInfo", Context.MODE_PRIVATE);  
	   
       edt.setText(sp.getString("IP", ""));  
      // final String cip=edt.getText().toString();
		 //��תע��ҳ��
		   bt1.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO �Զ����ɵķ������
					// TODO Auto-generated method stub
					Intent intent= new Intent();
					intent.setClass(MainActivity.this,ZhuceActivity.class);
					MainActivity.this.startActivity(intent);
					}
			});
		 //���ǩ��
			bt2.setOnClickListener(new OnClickListener() {
				
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					new SocketClientThread().start();
					
					   
				}
			});
			btn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO �Զ����ɵķ������
					/*Intent intent = new Intent();   
					//intent.setClassName("com.UCMobile", "com.UCMobile.main.UCMobile");   
					 intent.setAction(Intent.ACTION_VIEW); intent.addCategory(Intent.CATEGORY_DEFAULT);   
					 intent.setData(Uri.parse("http://58.154.51.126:8002/"));   
					 startActivity(intent);*/
                  new ChaThread().start();
					
				}
			});
			//��ת�α�ҳ��
			   kbtn.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO �Զ����ɵķ������
						// TODO Auto-generated method stub
						Intent intent= new Intent();
						intent.setClass(MainActivity.this,kechengbiao.class);
						intent.putExtra("ip",edt.getText().toString() );
						MainActivity.this.startActivity(intent);
						}
				});
			 //��ת֪ͨҳ��
			   tongbtn.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO �Զ����ɵķ������
						// TODO Auto-generated method stub
						Intent intent= new Intent();
						intent.setClass(MainActivity.this,Kechengtongzhi.class);
						intent.putExtra("ip",edt.getText().toString() );
						MainActivity.this.startActivity(intent);
						}
				});
	
	
	}
	//��ѯ�߳�
	class ChaThread extends Thread{
		public void run(){
			try {
				 String st1=edt.getText().toString();
				 Socket socket = new Socket(InetAddress.getByName(st1),6789);
				  PrintWriter outt = new PrintWriter(socket.getOutputStream());
				BufferedReader inn= new BufferedReader(new InputStreamReader(
			            socket.getInputStream() , "utf-8"));
				 Gson gson = new Gson();  
				 while(true){
					  Map map = new HashMap();
					  map.put("cha","reip");
					  String jip = gson.toJson(map);
				      outt.println(jip);
					  outt.flush();
					  String rip=inn.readLine();
					  Map<String, String> retMap = gson.fromJson(rip,  
				               new TypeToken<Map<String, String>>() {  
				                }.getType());
						String sip=(String)retMap.get("ip");
	            		String sport=(String)retMap.get("port");
	            		Intent intent = new Intent();   
						//intent.setClassName("com.UCMobile", "com.UCMobile.main.UCMobile");   
						 intent.setAction(Intent.ACTION_VIEW); intent.addCategory(Intent.CATEGORY_DEFAULT);   
						 intent.setData(Uri.parse("http://"+sip+":"+sport+"/"));   
						 startActivity(intent); 
				 }
			
				 
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
	}
	
	//ǩ���߳�
	class SocketClientThread extends Thread{
		public void run(){
			 try {
				 String st1=edt.getText().toString();
				 Context context = getWindow().getContext();        
				    TelephonyManager telephonemanage = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);              
				    String str = telephonemanage.getDeviceId();//�ֻ�����  
				 @SuppressWarnings("resource")
				Socket socket = new Socket(InetAddress.getByName(st1),6789);
				  PrintWriter out = new PrintWriter(socket.getOutputStream());
				BufferedReader in= new BufferedReader(new InputStreamReader(
			            socket.getInputStream() , "utf-8"));

			
		       Gson gson = new Gson();  
				  while(true){
				  Map<String, String> map = new HashMap();
				  map.put("ceshi","ok");
				  map.put("imei",str);
				  
			  String json = gson.toJson(map);
			      out.println(json);
				  out.flush();
				  
			//����IP
				  Editor editor = sp.edit();  
                  editor.putString("IP", st1);  
                  editor.commit();  
				  String back=in.readLine();
					if(!back.equals("")&&back!=null){
						Looper.prepare();
		            Toast.makeText(MainActivity.this, "ǩ���ɹ�",
							     Toast.LENGTH_SHORT).show();
						  Looper.loop();// ����loop�е�ѭ�����鿴��Ϣ����
						
					}
					else{
						Looper.prepare();
						Toast.makeText(MainActivity.this, "ǩ��ʧ��",
							     Toast.LENGTH_SHORT).show();
						  Looper.loop();// ����loop�е�ѭ�����鿴��Ϣ����
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
	

