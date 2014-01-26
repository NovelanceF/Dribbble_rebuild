package nl.lance.dribbb.activites;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

import nl.lance.dribbb.R;

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
