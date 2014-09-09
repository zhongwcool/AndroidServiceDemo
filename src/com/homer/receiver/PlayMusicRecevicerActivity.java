package com.homer.receiver;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.homer.R;

/**
 * @author 	sunboy_2050
 * @date		2012-03-16 
 * @blog		http://blog.csdn.net/sunboy_2050/
 */

public class PlayMusicRecevicerActivity extends Activity implements OnClickListener {
	
	private Button playBtn;
	private Button stopBtn;
	private Button pauseBtn;
	private Button exitBtn;
	private Button closeBtn;
	
	private Intent intent;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.music_receiver);
		
		this.getActionBar().setDisplayHomeAsUpEnabled(true);

		playBtn = (Button) findViewById(R.id.play);
		stopBtn = (Button) findViewById(R.id.stop);
		pauseBtn = (Button) findViewById(R.id.pause);
		exitBtn = (Button) findViewById(R.id.exit);
		closeBtn = (Button) findViewById(R.id.close);
		
		playBtn.setOnClickListener(this);
		stopBtn.setOnClickListener(this);
		pauseBtn.setOnClickListener(this);
		exitBtn.setOnClickListener(this);
		closeBtn.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		int op = -1;
		 intent = new Intent("com.homer.receiver.musicReceiver");

		switch (v.getId()) {
		case R.id.play:								// play music
			op = 1;
			break;
		case R.id.stop:								// stop music
			op = 2;
			break;
		case R.id.pause:							// pause music
			op = 3;
			break;
		case R.id.close:							// close activity
			this.finish();
			break;
		case R.id.exit:								// process by MusicReceiver
			op = 4;
			this.finish();
			break;
		}

		Bundle bundle = new Bundle();
		bundle.putInt("op", op);
		intent.putExtras(bundle);

		 sendBroadcast(intent);						// sendBroadcast
	}
	
	@Override
	public void onDestroy(){
		super.onDestroy();
		
		if(intent != null){
			stopService(intent);
		}
	}
}