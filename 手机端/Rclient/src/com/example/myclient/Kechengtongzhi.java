package com.example.myclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.example.myclient.MainActivity.SocketClientThread;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.TextView;

public class Kechengtongzhi extends Activity implements OnDateChangedListener  {

	
	private String dtime;
	private String stime;
	private DatePicker datePicker;
	private Button dbutton;
	private Button sbutton;
	private TextView show;
	private String value,rtongzhi;
	private final int IS_OK=1;
	private ProgressDialog dialog=null;
	private Handler handler=new Handler(){
		public void handleMessage(Message msg){
			 Gson gson = new Gson();  
			rtongzhi=(String) msg.obj;
			Map<String, String> retMap = gson.fromJson(rtongzhi,  
		               new TypeToken<Map<String, String>>() {  
		                }.getType());
			 String tongzhi=(String)retMap.get("tongzhi");
			//int sge=retMap.size();
		 if(!tongzhi.equals("")&&tongzhi!=null){
			show.setText(tongzhi);
			}
		 if(tongzhi.equals("")||tongzhi==null){
			 show.setText("û�пγ�֪ͨ");
		  }
			//��dialog��ʧ
			if(msg.what==IS_OK){
				dialog.dismiss();
			}
		}
	};
	protected void onCreate(Bundle savedInstanceState) {
		// TODO �Զ����ɵķ������
		super.onCreate(savedInstanceState);
		setContentView(R.layout.kechengtongzhi);
		datePicker=(DatePicker)findViewById(R.id.datePicker1);
		dbutton=(Button)findViewById(R.id.dbtn);
		sbutton=(Button)findViewById(R.id.cbtn);
		show=(TextView)findViewById(R.id.showtv);
		//��ȡ��������
		/*Calendar calendar=Calendar.getInstance();
		calendar.set(Calendar.YEAR,(Calendar.MONTH)+1,Calendar.DAY_OF_MONTH);
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");//������ʾ��ʽ
		dtime=format.format(calendar.getTime());*/
		datePicker.init(2015, 1, 1, this);//��ʼ������
		Intent intent = getIntent();
		value = intent.getStringExtra("ip");
		dialog=new ProgressDialog(this);
		dialog.setTitle("����֪ͨ");
		dialog.setMessage("���ڼ��أ����Ժ�...");
	    dialog.setCancelable(true);
	    
		dbutton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				new  dkethread().start();
				dialog.show();
			}
		});
		sbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO �Զ����ɵķ������
				new tethread().start();
				dialog.show();
			}
		});
	}

	//���ڿؼ��Ĵ���
	public void onDateChanged(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		// TODO �Զ����ɵķ������
		Calendar calendar=Calendar.getInstance();
		calendar.set(datePicker.getYear(), datePicker.getMonth(),datePicker.getDayOfMonth());
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");//������ʾ��ʽ
		stime=format.format(calendar.getTime());
		
		//show.setText(format.format(calendar.getTime()));
		
	}
	//�����֪ͨ
	class dkethread extends Thread{
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
							  map.put("time","lw");
							  map.put("imei",ime);
							  map.put("tebie","tebie");
							  map.put("biaoshi","biaoshi");
						  String json = gson.toJson(map);
						      out.println(json);
							  out.flush();
							  
							String fuxin=in.readLine();
							if(fuxin!=null){
							Message message=Message.obtain();
							message.obj=fuxin;
							message.what=IS_OK;
							handler.sendMessage(message);
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
	//�鿴�������ڵ�֪ͨ
	class tethread extends Thread{
		public void run(){
			   try {
				   Context context = getWindow().getContext();        
				    TelephonyManager telephonemanage = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);              
				    String ime = telephonemanage.getDeviceId();
				    
					Socket soc = new Socket(InetAddress.getByName(value),6789);
					  PrintWriter outt = new PrintWriter(soc.getOutputStream());
					BufferedReader inn= new BufferedReader(new InputStreamReader(
				            soc.getInputStream() , "GB2312"));
					
					   Gson gson = new Gson(); 
					  while(true){
							  Map<String, String> map = new HashMap();
							  map.put("time",stime);
							  map.put("imei",ime);
							  map.put("nia","nia");
							  map.put("niya","niya");
							
						  String json = gson.toJson(map);
						      outt.println(json);
							  outt.flush();
							  
							String back=inn.readLine();
							if(back!=null){
							Message message=Message.obtain();
							message.obj=back;
							message.what=IS_OK;
							handler.sendMessage(message);
							}
							 inn.close();
							 outt.close();
							 soc.close();
					  }
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
	}
	 
  }
