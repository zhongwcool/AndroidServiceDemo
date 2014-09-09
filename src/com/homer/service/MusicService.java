package com.homer.service;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import com.homer.R;

/**
 * @author sunboy_2050
 * @date 2012-03-16
 * @blog http://blog.csdn.net/sunboy_2050/
 */

public class MusicService extends Service {
	private static final String TAG = "MyService";

	private MediaPlayer mediaPlayer;

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

	@Override
	public void onCreate() {
		Log.v(TAG, "onCreate");
		Toast.makeText(this, "show media player", Toast.LENGTH_SHORT).show();

		if (mediaPlayer == null) {
			mediaPlayer = MediaPlayer.create(this, R.raw.tmp);
			mediaPlayer.setLooping(false);
		}
	}

	@Override
	public void onDestroy() {
		Log.v(TAG, "onDestroy");
		Toast.makeText(this, "stop media player", Toast.LENGTH_SHORT).show();
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			mediaPlayer.release();
		}
	}

	@Override
	public void onStart(Intent intent, int startId) {
		Log.v(TAG, "onStart");
		if (intent != null) {
			Bundle bundle = intent.getExtras();
			if (bundle != null) {
				int op = bundle.getInt("op");
				switch (op) {
				case 1:
					play();
					break;
				case 2:
					stop();
					break;
				case 3:
					pause();
					break;
				}
			}
		}
	}

	public void play() {
		if (!mediaPlayer.isPlaying()) {
			mediaPlayer.start();
		}
	}

	public void pause() {
		if (mediaPlayer != null && mediaPlayer.isPlaying()) {
			mediaPlayer.pause();
		}
	}

	public void stop() {
		if (mediaPlayer != null) {
			mediaPlayer.stop();
			try {
				mediaPlayer.prepare(); // 在调用stop后如果需要再次通过start进行播放,需要之前调用prepare函数
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
