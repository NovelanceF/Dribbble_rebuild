package com.lance.dribbb.activites;

import com.lance.dribbb.R;
import com.lance.dribbb.R.layout;
import com.lance.dribbb.R.menu;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class PlayerActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_player);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.player, menu);
    return true;
  }

}
