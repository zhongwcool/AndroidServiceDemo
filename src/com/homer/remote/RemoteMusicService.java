package com.homer.remote;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.RemoteException;

import com.homer.R;

/**
 * @author 	sunboy_2050
 * @date		2012-03-16 
 * @blog		http://blog.csdn.net/sunboy_2050/
 */

public class RemoteMusicService extends Service {

	private MediaPlayer mediaPlayer;

	@Override
	public IBinder onBind(Intent intent) {
		return binder;
	}

	private final IMusicControlService.Stub binder = new IMusicControlService.Stub() {

		@Override
		public void play() throws RemoteException {
			if (mediaPlayer == null) {
				mediaPlayer = MediaPlayer.create(RemoteMusicService.this, R.raw.tmp);
				mediaPlayer.setLooping(false);
			}
			if (!mediaPlayer.isPlaying()) {
				mediaPlayer.start();
			}
		}

		@Override
		public void pause() throws RemoteException {
			if (mediaPlayer != null && mediaPlayer.isPlaying()) {
				mediaPlayer.pause();
			}			
		}

		@Override
		public void stop() throws RemoteException {
			if (mediaPlayer != null) {
				mediaPlayer.stop();
				try {
					mediaPlayer.prepare();		// 在调用stop后如果需要再次通过start进行播放,需要之前调用prepare函数
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
		}
	};
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		
		if(mediaPlayer != null){
			mediaPlayer.stop();
			mediaPlayer.release();
		}
	}
}
