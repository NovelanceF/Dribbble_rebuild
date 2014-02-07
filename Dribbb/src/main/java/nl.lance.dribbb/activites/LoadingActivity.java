package nl.lance.dribbb.activites;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import nl.lance.dribbb.R;

public class LoadingActivity extends Activity {

  Intent intent;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_loading);
    intent = getIntent();
    new Handler().postDelayed(new Runnable() {

      @Override
      public void run() {
        if (intent.getIntExtra("ct", -1) == 1) {
          Intent intent = new Intent(LoadingActivity.this, DActivity.class);
          Bundle bundle = new Bundle();
          bundle.putString("is", "Player");
          intent.putExtras(bundle);
          startActivity(intent);
        } else {
          Intent intent = new Intent(LoadingActivity.this, DActivity.class);
          Bundle bundle = new Bundle();
          bundle.putString("is", "Shots");
          intent.putExtras(bundle);
          startActivity(intent);
        }
        LoadingActivity.this.finish();
        overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
      }
    }, 1000);

  }

}
