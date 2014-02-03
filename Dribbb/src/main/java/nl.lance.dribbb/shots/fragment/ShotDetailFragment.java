package nl.lance.dribbb.shots.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.TextView;

import net.frakbot.imageviewex.ImageViewNext;

import nl.lance.dribbb.R;
import nl.lance.dribbb.adapter.DetailAdapter;
import nl.lance.dribbb.network.DribbbleAPI;
import nl.lance.dribbb.network.ShotsData;
import nl.lance.dribbb.views.FooterState;

/**
 * Created by Novelance on 1/26/14.
 */
public class ShotDetailFragment extends Fragment {

  ShotsData data;

  public ShotDetailFragment(Activity a) {
    data = new ShotsData(a);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_shots_detail, container, false);
    ImageViewNext.setMaximumNumberOfThreads(200);
    initShotDetail(rootView);
    initMoreShots(rootView);
    return rootView;
  }

  private void initMoreShots(View view) {
    GridView gridView = (GridView)view.findViewById(R.id.more_shots);
    DetailAdapter adapter = new DetailAdapter(getActivity(), data.getList(), 2);
    data.getShotsRefresh(DribbbleAPI.getuserLikesUel(getActivity().getIntent().getExtras().getString("player_username")) + "1", adapter, new FooterState());
    gridView.setAdapter(adapter);
  }

  private void initShotDetail(View view) {

    Bundle bundle = getActivity().getIntent().getExtras();

    ImageViewNext playerAvatar = (ImageViewNext)view.findViewById(R.id.detail_avatar);
    ImageViewNext detailimage = (ImageViewNext)view.findViewById(R.id.detail_image);

    setImageParmas(detailimage);

    TextView title = (TextView)view.findViewById(R.id.detail_title);
    TextView player = (TextView)view.findViewById(R.id.detail_player);
    TextView views = (TextView)view.findViewById(R.id.detail_views);
    TextView likes = (TextView)view.findViewById(R.id.detail_likes);
    TextView comments = (TextView)view.findViewById(R.id.detail_commentss);

    playerAvatar.setUrl(bundle.getString("player_avatar_url"));
    detailimage.setUrl(bundle.getString("image_url"));
    title.setText(bundle.getString("title"));
    player.setText(bundle.getString("player_name"));
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
}
