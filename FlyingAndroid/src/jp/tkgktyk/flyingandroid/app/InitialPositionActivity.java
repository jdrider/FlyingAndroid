package jp.tkgktyk.flyingandroid.app;

import jp.tkgktyk.flyingandroid.InitialPosition;
import jp.tkgktyk.flyingandroid.R;
import jp.tkgktyk.flyinglayout.FlyingLayoutF;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;

public class InitialPositionActivity extends Activity {

	private FlyingLayoutF mFlyingLayout;

	private InitialPosition mInitialPosition;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_initial_position);

		mInitialPosition = new InitialPosition(this);

		mFlyingLayout = (FlyingLayoutF) findViewById(R.id.flying);
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(this);
		mFlyingLayout.setSpeed(Float.parseFloat(pref.getString(
				getString(R.string.pref_key_speed), "1.5f")));
		mFlyingLayout.getViewTreeObserver().addOnGlobalLayoutListener(
				new OnGlobalLayoutListener() {
					@SuppressWarnings("deprecation")
					@SuppressLint("NewApi")
					@Override
					public void onGlobalLayout() {
						mFlyingLayout.setOffsetX(mInitialPosition
								.getX(mFlyingLayout));
						mFlyingLayout.setOffsetY(mInitialPosition
								.getY(mFlyingLayout));
						if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
							mFlyingLayout.getViewTreeObserver()
									.removeOnGlobalLayoutListener(this);
						} else {
							mFlyingLayout.getViewTreeObserver()
									.removeGlobalOnLayoutListener(this);
						}
					}
				});
		mFlyingLayout
				.setOnFlyingEventListener(new FlyingLayoutF.OnFlyingEventListener() {
					@Override
					public void onOutsideClick(FlyingLayoutF v, int x, int y) {
						// doing nothing
					}

					@Override
					public void onMoveFinished(FlyingLayoutF v) {
						mInitialPosition.setXp(v, v.getOffsetX());
						mInitialPosition.setYp(v, v.getOffsetY());
						mInitialPosition.save(v.getContext());
						v.setOffsetX(mInitialPosition.getX(v));
						v.setOffsetY(mInitialPosition.getY(v));
						v.requestLayout();
					}

					@Override
					public void onMove(FlyingLayoutF v, int deltaX, int deltaY) {
						// doing nothing
					}
				});
	}
}
