package com.homer.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * @author 	sunboy_2050
 * @date		2012-03-16 
 * @blog		http://blog.csdn.net/sunboy_2050/
 */

public class MusicReceiver extends BroadcastReceiver {		// receive Broadcast
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		if(intent != null){
			Bundle bundle = intent.getExtras();
			Intent it = new Intent(context, MusicReceiverService.class);	// call service for MusicReceiverService.class
			it.putExtras(bundle);
			if(bundle != null){
				int op = bundle.getInt("op");
				if(op == 4){
					context.stopService(it);		// stopService
				}else{
					context.startService(it);		// startService
				}
			}
		}
	}
	
}
