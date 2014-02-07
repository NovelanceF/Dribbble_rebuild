package nl.lance.dribbb.activites;

import android.app.ActionBar;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import nl.lance.dribbb.R;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import nl.lance.dribbb.shots.fragment.ShotsFragment;
import nl.lance.dribbb.views.FooterState;

public class UActivity extends SwipeBackActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_u);
    
    setActionBarStyle();
    
    if(getIntent() != null) {
      getSupportFragmentManager().beginTransaction()
          .add(R.id.container, new ShotsFragment(UActivity.this, getIntent().getExtras().getString("url"), 120))
          .commit();
    }
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.u, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if(id == android.R.id.home) {
      this.finish();
      overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
    }
    return super.onOptionsItemSelected(item);
  }

  private void setActionBarStyle() {
    this.getActionBar().setTitle("dribbble");
    getActionBar().setIcon(R.drawable.ic_action);
    getActionBar().setBackgroundDrawable(this.getBaseContext().getResources().getDrawable(R.drawable.actionbar_back));
    getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
    int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
    TextView textView = (TextView) findViewById(titleId);
    textView.setTypeface(Typeface.createFromAsset(getAssets(), "font/Wendy.ttf"));
    textView.setTextColor(0xFFdfdfdf);
    textView.setTextSize(32); 
    getActionBar().setDisplayHomeAsUpEnabled(true);
    getActionBar().setHomeButtonEnabled(true);
    
  }
  
  @Override
  protected void onStop() {
    super.onStop();
    FooterState state = new FooterState();
    state.setState(nl.lance.dribbb.views.FooterState.State.TheEnd);
  }

}
