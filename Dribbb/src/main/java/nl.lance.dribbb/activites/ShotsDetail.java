package nl.lance.dribbb.activites;

import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

import cn.sharesdk.framework.ShareSDK;
import me.imid.swipebacklayout.lib.SwipeBackLayout;
import me.imid.swipebacklayout.lib.app.SwipeBackActivity;
import nl.lance.dribbb.R;
import nl.lance.dribbb.shots.fragment.CommentsFragment;
import nl.lance.dribbb.shots.fragment.ShotDetailFragment;

public class ShotsDetail extends SwipeBackActivity implements ShotDetailFragment.DrawerListener{

  private SwipeBackLayout mSwipeBackLayout;
  public SlidingMenu sm;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_shots_detail);

    ShareSDK.initSDK(this);

    mSwipeBackLayout = getSwipeBackLayout();
    mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);

    getSupportFragmentManager().beginTransaction()
            .replace(R.id.container, new ShotDetailFragment(ShotsDetail.this)).commit();

    getSupportFragmentManager().beginTransaction()
            .replace(R.id.comments_list_container, new CommentsFragment(ShotsDetail.this)).commit();

    initSlidingMenu();
  }

  private void initSlidingMenu() {
    WindowManager manager = getWindowManager();
    Display display = manager.getDefaultDisplay();

    sm = new SlidingMenu(ShotsDetail.this);
    sm.setMode(SlidingMenu.RIGHT);
    sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
    sm.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
    sm.setBehindOffset(display.getWidth() * 1 / 4);
    sm.setShadowDrawable(R.drawable.sidebar_shadow_right);
    sm.setShadowWidth(30);
    sm.setMenu(R.layout.menu_layout);

    sm.setOnOpenedListener(new SlidingMenu.OnOpenedListener() {
      @Override
      public void onOpened() {
        mSwipeBackLayout.setEnableGesture(false);
      }
    });
    sm.setOnClosedListener(new SlidingMenu.OnClosedListener() {
      @Override
      public void onClosed() {
        mSwipeBackLayout.setEnableGesture(true);
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    getMenuInflater().inflate(R.menu.shots_detail, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.action_settings:
        return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onIconSelected() {
    sm.toggle();
  }
}
