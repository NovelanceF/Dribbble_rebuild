package nl.lance.dribbb.shots.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import nl.lance.dribbb.R;
import nl.lance.dribbb.adapter.PlayersAdapter;
import nl.lance.dribbb.network.BitmapLruCache;
import nl.lance.dribbb.network.DribbbleAPI;
import nl.lance.dribbb.network.ShotsData;
import nl.lance.dribbb.views.FooterState;
import nl.lance.dribbb.views.ObservableScrollView;
import nl.lance.dribbb.views.ScrollGridView;

/**
 * Created by Novelance on 2/3/14.
 */
public class PlayerFragment extends Fragment implements ObservableScrollView.Callbacks, ObservableScrollView.ScrollViewListener {

  private FrameLayout mStickyView;
  private View mPlaceholderView;
  private ImageLoader mImageLoader;
  private ShotsData data;
  private FooterState state = new FooterState();
  private PlayersAdapter adapter;
  private static int page = 1;

  public PlayerFragment(Activity a) {
    data = new ShotsData(a);
    RequestQueue mRequestQueue = Volley.newRequestQueue(a);
    mImageLoader = new ImageLoader(mRequestQueue, new BitmapLruCache());
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    ViewGroup rootView = (ViewGroup) inflater
            .inflate(R.layout.fragment_player, container, false);

    initPlayerInfo(rootView);
    initGridView(rootView);

    final ObservableScrollView scrollView = (ObservableScrollView) rootView.findViewById(R.id.scroll_view);
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

    scrollView.setScrollViewListener(this);

    return rootView;
  }

  private void initPlayerInfo(ViewGroup view) {
    TextView name = (TextView)view.findViewById(R.id.player_name);
    TextView likes = (TextView)view.findViewById(R.id.player_likes_count);
    TextView follow = (TextView)view.findViewById(R.id.player_ff);
    NetworkImageView avatar = (NetworkImageView)view.findViewById(R.id.player_avatar);

    Bundle bundle = getActivity().getIntent().getExtras();
    avatar.setImageUrl(bundle.getString("avatar_url"), mImageLoader);
    name.setText(bundle.getString("name"));
    String likesAndLiked = bundle.getString("player_likes_count") + " Likes, and "
            + bundle.getString("likes_received_count") + " Likes received.";
    likes.setText(likesAndLiked);
    String followingAndFollowers = bundle.getString("following_count") + " Following, and "
            + bundle.getString("followers_count") + " followers.";
    follow.setText(followingAndFollowers);
  }

  private void initGridView(ViewGroup view) {
    ScrollGridView gridView = (ScrollGridView)view.findViewById(R.id.player_more_shots);
    adapter = new PlayersAdapter(getActivity(), data.getList(), 0);
    Log.i("URL", DribbbleAPI.getPlayersShotUrl(getActivity().getIntent().getExtras().getString("username")));
    data.getShotsRefresh(DribbbleAPI.getPlayersShotUrl(getActivity().getIntent().getExtras().getString("username")) + page,
            adapter, new FooterState());
    gridView.setAdapter(adapter);
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

  @Override
  public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
    View view = (View) scrollView.getChildAt(scrollView.getChildCount() - 1);
    int diff = (view.getBottom() - (scrollView.getHeight() + scrollView.getScrollY()));

    if (diff == 0 && state.getState() != FooterState.State.Loading && adapter.getCount() != 0) {
      state.setState(FooterState.State.Loading);
      data.getShotsRefresh(DribbbleAPI.getPlayersShotUrl(getActivity().getIntent().getExtras().getString("username")) + ++page,
              adapter, state);
      Log.i("ONSCROLL", "BBB");
    }
  }

  @Override
  public void onStop() {
    page = 1;
    super.onStop();
  }
}
