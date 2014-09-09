package com.homer.bind;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.homer.R;

/**
 * @author 	sunboy_2050
 * @date		2012-03-16 
 * @blog		http://blog.csdn.net/sunboy_2050/
 */

public class PlayBindMusicActivity extends Activity implements OnClickListener {

	private Button playBtn;
	private Button stopBtn;
	private Button pauseBtn;
	private Button exitBtn;

	private BindMusicService musicService;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.bind_music_service);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);

		playBtn = (Button) findViewById(R.id.play);
		stopBtn = (Button) findViewById(R.id.stop);
		pauseBtn = (Button) findViewById(R.id.pause);
		exitBtn = (Button) findViewById(R.id.exit);

		playBtn.setOnClickListener(this);
		stopBtn.setOnClickListener(this);
		pauseBtn.setOnClickListener(this);
		exitBtn.setOnClickListener(this);

		connection();
	}

	private void connection() {
		Intent intent = new Intent("com.homer.bind.bindService");
		bindService(intent, sc, Context.BIND_AUTO_CREATE);			// bindService
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.play:
			musicService.play();
			break;
		case R.id.stop:
			if (musicService != null) {
				musicService.stop();
			}
			break;
		case R.id.pause:
			if (musicService != null) {
				musicService.pause();
			}
			break;
		case R.id.exit:
			this.finish();
			break;
		}
	}

	private ServiceConnection sc = new ServiceConnection() {

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {		//connect Service
			musicService = ((BindMusicService.MyBinder) (service)).getService();
			if (musicService != null) {
				musicService.play();		// play music
			}
		}
		
		@Override
		public void onServiceDisconnected(ComponentName name) {					//disconnect Service
			musicService = null;
		}
	};
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		
		if(sc != null){
			unbindService(sc);
		}
	}
}