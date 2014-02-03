package nl.lance.dribbb.shots.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import nl.lance.dribbb.R;
import nl.lance.dribbb.views.ObservableScrollView;

/**
 * Created by Novelance on 2/3/14.
 */
public class PlayerFragment extends Fragment implements ObservableScrollView.Callbacks{

  private ObservableScrollView scrollView;
  private FrameLayout mStickyView;
  private View mPlaceholderView;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    ViewGroup rootView = (ViewGroup) inflater
            .inflate(R.layout.fragment_player, container, false);

    scrollView = (ObservableScrollView) rootView.findViewById(R.id.scroll_view);
    scrollView.setCallbacks(this);

    mStickyView = (FrameLayout) rootView.findViewById(R.id.label_sticky);
    mPlaceholderView = rootView.findViewById(R.id.label);

    scrollView.getViewTreeObserver().addOnGlobalLayoutListener(
            new ViewTreeObserver.OnGlobalLayoutListener() {
              @Override
              public void onGlobalLayout() {
                onScrollChanged(scrollView.getScrollY());
              }
            }
    );

    return rootView;
  }

  @Override
  public void onScrollChanged(int scrollY) {
    mStickyView.setTranslationY(Math.max(mPlaceholderView.getTop(), scrollY));
  }

  @Override
  public void onDownMotionEvent() {

  }

  @Override
  public void onUpOrCancelMotionEvent() {

  }
}
