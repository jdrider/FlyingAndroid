package jp.tkgktyk.flyingandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ToggleActivity extends Activity {
	private static final String TAG = ToggleActivity.class.getSimpleName();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// ignore exit animation
		overridePendingTransition(0, 0);
		finish();
		// Timer timer = new Timer(false);
		// timer.schedule(new TimerTask() {
		// @Override
		// public void run() {
		// Log.d(TAG, "toggle");
		// sendBroadcast(new Intent(
		// "jp.tkgktyk.flyingandroid.ACTION_TOGGLE"));
		// }
		// }, 100);
		// don't work if windows style is transparent or floating.
		// Log.d(TAG, "toggle");
		// sendBroadcast(new Intent("jp.tkgktyk.flyingandroid.ACTION_TOGGLE"));
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		sendBroadcast(new Intent("jp.tkgktyk.flyingandroid.ACTION_TOGGLE"));
	}
}