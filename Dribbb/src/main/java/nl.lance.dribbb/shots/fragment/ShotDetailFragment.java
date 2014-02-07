package nl.lance.dribbb.shots.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import net.frakbot.imageviewex.ImageViewNext;
import net.tsz.afinal.FinalDb;

import java.util.List;

import nl.lance.dribbb.R;
import nl.lance.dribbb.activites.PlayerActivity;
import nl.lance.dribbb.adapter.DetailAdapter;
import nl.lance.dribbb.models.Shots;
import nl.lance.dribbb.network.DribbbleAPI;
import nl.lance.dribbb.network.ShotsData;
import nl.lance.dribbb.views.FooterState;

/**
 * Created by Novelance on 1/26/14.
 */
public class ShotDetailFragment extends Fragment implements View.OnClickListener {

  private ShotsData data;
  private Bundle bundle;

  public ShotDetailFragment(Activity a) {
    data = new ShotsData(a);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_shots_detail, container, false);
    ImageViewNext.setMaximumNumberOfThreads(200);
    bundle = getActivity().getIntent().getExtras();
    initShotDetail(rootView);
    initMoreShots(rootView);
    return rootView;
  }

  private void initMoreShots(View view) {
    GridView gridView = (GridView) view.findViewById(R.id.more_shots);
    DetailAdapter adapter = new DetailAdapter(getActivity(), data.getList(), 2);
    RelativeLayout relativeLayout = (RelativeLayout)view.findViewById(R.id.gridview_empty);
    shotsLoading(1, adapter, relativeLayout);
    gridView.setAdapter(adapter);
    gridView.setEmptyView(relativeLayout);
  }

  private void initShotDetail(View view) {

    ImageViewNext playerAvatar = (ImageViewNext) view.findViewById(R.id.detail_avatar);
    playerAvatar.setOnClickListener(this);
    ImageViewNext detailimage = (ImageViewNext) view.findViewById(R.id.detail_image);

    setImageParmas(detailimage);

    TextView title = (TextView) view.findViewById(R.id.detail_title);
    TextView player = (TextView) view.findViewById(R.id.detail_player);
    player.setOnClickListener(this);
    TextView views = (TextView) view.findViewById(R.id.detail_views);
    TextView likes = (TextView) view.findViewById(R.id.detail_likes);
    TextView comments = (TextView) view.findViewById(R.id.detail_commentss);

    ImageButton shotCollect = (ImageButton) view.findViewById(R.id.shot_collect);
    shotCollect.setOnClickListener(this);

    playerAvatar.setUrl(bundle.getString("avatar_url"));
    detailimage.setUrl(bundle.getString("image_url"));
    title.setText(bundle.getString("title"));
    player.setText(bundle.getString("name"));
    views.setText(bundle.getString("views_count"));
    likes.setText(bundle.getString("likes_count"));
    comments.setText(bundle.getString("comments_count"));

  }

  private void setImageParmas(ImageViewNext v) {
    ViewGroup.LayoutParams params = v.getLayoutParams();
    WindowManager manager = getActivity().getWindowManager();
    Display display = manager.getDefaultDisplay();
    params.width = display.getWidth();
    params.height = display.getWidth() * 3 / 4;
    v.setLayoutParams(params);
  }

  private void shotsLoading(int page, DetailAdapter adapter, RelativeLayout relativeLayout) {
    String Url = DribbbleAPI.getuserLikesUel(getActivity().getIntent().getExtras().getString("username")) + page;
    data.getShotsRefresh(Url, adapter, new FooterState(), relativeLayout);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.detail_avatar:
      case R.id.detail_player: {
        toPlayerPage();
      }
      break;
      case R.id.shot_collect: {
        collectShot();
      }
      break;
    }
  }

  private void toPlayerPage() {
    Intent intent = new Intent(getActivity(), PlayerActivity.class);
    Bundle playerBundle = new Bundle();

    String tags[] = DribbbleAPI.tagBundlePlayerInfo;

    for (int i = 0; i < tags.length; i++) {
      playerBundle.putString(tags[i], bundle.getString(tags[i]));
      if (i == 3) {
        playerBundle.putString("player_" + tags[i], bundle.getString("player_" + tags[i]));
      }
    }

    intent.putExtras(playerBundle);
    getActivity().startActivity(intent);
    getActivity().overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
  }

  private void collectShot() {
    FinalDb db = FinalDb.create(getActivity());
    List<Shots> list = db.findAll(Shots.class);

    if (!isExisted(bundle.getString("id"), list)) {
      Shots shots = new Shots();
      shots.setAvatarUrl(bundle.getString("avatar_url"));
      shots.setCommentsCount(bundle.getString("comments_count"));
      shots.setFollowersCount(bundle.getString("followers_count"));
      shots.setFollowingCount(bundle.getString("following_count"));
      shots.setImageUrl(bundle.getString("image_url"));
      shots.setLikesCount(bundle.getString("likes_count"));
      shots.setLikesReceivdCount(bundle.getString("likes_received_count"));
      shots.setName(bundle.getString("name"));
      shots.setPlayerLikesCount(bundle.getString("player_likes_count"));
      shots.setShotsId(bundle.getString("id"));
      shots.setTitle(bundle.getString("title"));
      shots.setUsername(bundle.getString("username"));
      shots.setImageUrl(bundle.getString("image_url"));
      shots.setViewsCount(bundle.getString("views_count"));

      Toast.makeText(getActivity(), "Shots Collected", 2000).show();

      db.save(shots);
    } else {
      Toast.makeText(getActivity(), "Already Collected", 2000).show();
    }
  }

  private boolean isExisted(String s, List<Shots> list) {
    int i;
    for (i = 0; i < list.size(); i++) {
      if (list.get(i).getShotsId().equals(s)) {
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
