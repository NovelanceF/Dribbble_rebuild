package nl.lance.dribbb.shots.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import net.tsz.afinal.FinalDb;

import java.util.List;

import nl.lance.dribbb.R;
import nl.lance.dribbb.activites.ShotsDetail;
import nl.lance.dribbb.adapter.PlayersAdapter;
import nl.lance.dribbb.models.Player;
import nl.lance.dribbb.network.BitmapLruCache;
import nl.lance.dribbb.network.DribbbleAPI;
import nl.lance.dribbb.network.ShotsData;
import nl.lance.dribbb.views.FooterState;
import nl.lance.dribbb.views.ObservableScrollView;
import nl.lance.dribbb.views.ScrollGridView;

/**
 * Created by Novelance on 2/3/14.
 */
public class PlayerFragment extends Fragment implements ObservableScrollView.Callbacks, ObservableScrollView.ScrollViewListener, View.OnClickListener {

  private FrameLayout mStickyView;
  private View mPlaceholderView;
  private ImageLoader mImageLoader;
  private ShotsData data;
  private FooterState state = new FooterState();
  private PlayersAdapter adapter;
  private static int page = 1;
  private RelativeLayout progressView;
  private Bundle bundle;
  private Typeface typeface;

  public PlayerFragment(Activity a) {
    data = new ShotsData(a);
    RequestQueue mRequestQueue = Volley.newRequestQueue(a);
    mImageLoader = new ImageLoader(mRequestQueue, new BitmapLruCache());
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    ViewGroup rootView = (ViewGroup) inflater
            .inflate(R.layout.fragment_player, container, false);

    bundle = getActivity().getIntent().getExtras();
    typeface = Typeface.createFromAsset(getActivity().getAssets(), "font/Roboto-Light.ttf");

     getActivity().getActionBar().setTitle(" player");

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
    TextView name = (TextView) view.findViewById(R.id.player_name);
    TextView likes = (TextView) view.findViewById(R.id.player_likes_count);
    TextView follow = (TextView) view.findViewById(R.id.player_ff);
    TextView label = (TextView) view.findViewById(R.id.shots_label);
    name.setTypeface(typeface);
    label.setTypeface(typeface);

    NetworkImageView avatar = (NetworkImageView) view.findViewById(R.id.player_avatar);
    progressView = (RelativeLayout) view.findViewById(R.id.progress_bar);
    ImageButton collector = (ImageButton) view.findViewById(R.id.player_collector);
    collector.setOnClickListener(this);

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
    ScrollGridView gridView = (ScrollGridView) view.findViewById(R.id.player_more_shots);
    adapter = new PlayersAdapter(getActivity(), data.getList(), 0);
    shotsLoading(page);
    gridView.setAdapter(adapter);

    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), ShotsDetail.class);

        intent.putExtras(initShotsBundle(position));
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
      }
    });
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
    shotsLoading(++page);
      if (adapter.getCount() == data.getSize()) {
        progressViewEnd();
      }
    }
  }

  private void progressViewEnd() {
    progressView.getChildAt(0).setVisibility(View.GONE);
    TextView t = (TextView) progressView.getChildAt(1);
    t.setText("The End");
  }

  private void shotsLoading(int page) {
    state.setState(FooterState.State.Loading);
    data.getShotsRefresh(DribbbleAPI.getPlayersShotUrl(getActivity().getIntent().getExtras().getString("username")) + page,
            adapter, state, progressView);
  }

  @Override
  public void onStop() {
    page = 1;
    super.onStop();
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.player_collector: {
        playerCollect();
      }
      break;
    }
  }

  private Bundle initShotsBundle(int position) {
    Bundle bundle = new Bundle();

    String tags[] = DribbbleAPI.tagBundleShots;

    for (int i = 0; i < tags.length; i++) {
      bundle.putString(tags[i], data.getList().get(position).get(tags[i]).toString());
      if(i == 4) {
        bundle.putString("player_"+tags[i], data.getList().get(position).get("player_"+tags[i]).toString());
      }
    }
    return bundle;
  }

  private void playerCollect() {
    FinalDb db = FinalDb.create(getActivity());
    List<Player> list = db.findAll(Player.class);
    if (!isExisted(bundle.getString("username"), list)) {
      Player player = new Player();
      player.setUsername(bundle.getString("username"));
      player.setAvatarUrl(bundle.getString("avatar_url"));
      player.setFollowersCount(bundle.getString("followers_count"));
      player.setFollowingCount(bundle.getString("following_count"));
      player.setLikesCount(bundle.getString("player_likes_count"));
      player.setLikesReceivedCount(bundle.getString("likes_received_count"));
      player.setName(bundle.getString("name"));
      db.save(player);
      Toast.makeText(getActivity(), "Player Collected", 2000).show();
    } else {
      Toast.makeText(getActivity(), "Already Collected", 2000).show();
    }
  }

  private boolean isExisted(String username, List<Player> list) {
    int i;
    for (i = 0; i < list.size(); i++) {
      if (list.get(i).getUsername().equals(username)) {
        break;
      }
    }
    if (i == list.size()) {
      return false;
    } else {
      return true;
    }
  }
}
