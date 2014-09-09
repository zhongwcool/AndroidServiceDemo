package com.homer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.homer.bind.PlayBindMusicActivity;
import com.homer.receiver.PlayMusicRecevicerActivity;
import com.homer.remote.PlayRemoteMusicActivity;
import com.homer.service.PlayMusicServiceActivity;

public class MainActivity extends Activity implements OnClickListener {

	private Button musicServiceBtn;
	private Button musicReceiverBtn;
	private Button bindMusicServiceBtn;
	private Button remoteMusicServiceBtn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		musicServiceBtn = (Button) findViewById(R.id.musicService);
		musicReceiverBtn = (Button) findViewById(R.id.musicReceiver);
		bindMusicServiceBtn = (Button) findViewById(R.id.bindMusicService);
		remoteMusicServiceBtn = (Button) findViewById(R.id.remoteMusicService);
		
		musicServiceBtn.setOnClickListener(this);
		musicReceiverBtn.setOnClickListener(this);
		bindMusicServiceBtn.setOnClickListener(this);
		remoteMusicServiceBtn.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.musicService:
			startActivity(new Intent(this, PlayMusicServiceActivity.class));
			break;

		case R.id.musicReceiver:
			startActivity(new Intent(this, PlayMusicRecevicerActivity.class));
			break;

		case R.id.bindMusicService:
			startActivity(new Intent(this, PlayBindMusicActivity.class));
			break;

		case R.id.remoteMusicService:
			startActivity(new Intent(this, PlayRemoteMusicActivity.class));
			break;
		}
	}

}