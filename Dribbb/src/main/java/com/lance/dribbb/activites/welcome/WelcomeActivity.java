package com.lance.dribbb.activites.welcome;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.lance.dribbb.R;
import com.lance.dribbb.activites.ContentActivity;

public class WelcomeActivity extends Activity {
	
  private static final int GO_HOME = 100;
  private static final int GO_GUIDE = 200;
  boolean isFirst = false;
  private Handler mHandler = new Handler() {
  @Override
  public void handleMessage(Message msg) {
    switch (msg.what) {
	    case GO_HOME:
	      goHome();
		    break;
	    case GO_GUIDE:
	      goGuide();
		    break;
	    }
    }
  };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		init();
	}

	private void init() {
		SharedPreferences preferences = getSharedPreferences("first_pref", MODE_PRIVATE);
		isFirst = preferences.getBoolean("isFirst", true);
		if(!isFirst) {
			mHandler.sendEmptyMessageDelayed(GO_HOME, 150);
		} else {
			mHandler.sendEmptyMessageDelayed(GO_GUIDE, 150);
		}
	}
	
	private void goHome() {
		Intent intent = new Intent(WelcomeActivity.this, ContentActivity.class);
		startActivity(intent);
		overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
		this.finish();
	}
	
	 private void goGuide() {
	    Intent intent = new Intent(WelcomeActivity.this, GuideActivity.class);
	    startActivity(intent);
	    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
	    this.finish();
	  }
}
