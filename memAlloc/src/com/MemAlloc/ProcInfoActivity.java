package com.MemAlloc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class ProcInfoActivity extends Activity
{
	private TextView mtvProcInfo;
	private String LOG_TAG = "ProcInfoActivity";
	private boolean isActivityRunning;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.proc_info_view);
		
		Log.e(LOG_TAG, "[onCreate]");		
	
		isActivityRunning = false;
		mtvProcInfo = (TextView) findViewById(R.id.procResult);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.e(LOG_TAG, "[onStart]");
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.e(LOG_TAG, "[onResume]");
		
		isActivityRunning = true;
		mHandler.sendEmptyMessage(0);
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		isActivityRunning = false;
		Log.e(LOG_TAG, "[onPause]");
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		Log.e(LOG_TAG, "[onStop]");
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		Log.e(LOG_TAG, "[onDestroy]");
	}
	
	Handler mHandler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			Log.e(LOG_TAG, "[handleMessage] msg : " + msg);			
			
			if(msg.what == 0)
			{
				try
				{
					BufferedReader in = new BufferedReader(new FileReader("/proc/meminfo"), 8192);
					String s;
					
					while((s=in.readLine())!=null)
					{
				        Log.e(LOG_TAG, "[handleMessage] readLine : " + s);				
						//String segs[] = s.trim().split("[ ]+");
				        mtvProcInfo.append(s+"\n");
					}
				}
				catch(IOException e)
				{}
				catch(NumberFormatException e)
				{}		
				
				if(isActivityRunning)
					mHandler.sendEmptyMessageDelayed(0, 1000);
			}
		}
	};
	
}
