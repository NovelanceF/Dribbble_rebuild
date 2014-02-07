package nl.lance.dribbb.shots.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fortysevendeg.swipelistview.BaseSwipeListViewListener;
import com.fortysevendeg.swipelistview.SwipeListView;

import net.tsz.afinal.FinalDb;

import java.util.List;

import nl.lance.dribbb.R;
import nl.lance.dribbb.activites.LoadingActivity;
import nl.lance.dribbb.activites.ShotsDetail;
import nl.lance.dribbb.adapter.ShotsCollectionAdapter;
import nl.lance.dribbb.models.Shots;

/**
 * Created by Novelance on 2/4/14.
 */
public class ShotsCollection extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    final View rootView = inflater.inflate(R.layout.fragment_shots_collect, container, false);

    final FinalDb db = FinalDb.create(getActivity());
    final List<Shots> list = db.findAll(Shots.class);

    SwipeListView listView = (SwipeListView)rootView.findViewById(R.id.shots_collected_listview);
    final ShotsCollectionAdapter adapter = new ShotsCollectionAdapter(getActivity(), initData());
    listView.setAdapter(adapter);

    listView.setSwipeListViewListener(new BaseSwipeListViewListener(){

      @Override
      public void onStartOpen(int position, int action, boolean right) {
      }

      @Override
      public void onStartClose(int position, boolean right) {
      }

      @Override
      public void onClickFrontView(int position) {
        Intent intent = new Intent(getActivity(), ShotsDetail.class);

        intent.putExtras(initShotBundle(list, position));
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
      }

      @Override
      public void onClickBackView(int position) {
        Shots shots = new Shots();
        shots.setId(list.get(position).getId());
        db.delete(shots);

        Intent intent = new Intent(getActivity(), LoadingActivity.class);
        intent.putExtra("ct", 2);
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
        getActivity().finish();
      }

      @Override
      public void onDismiss(int[] reverseSortedPositions) {
      }
    });
    return rootView;
  }

  private List<Shots> initData() {
    FinalDb db = FinalDb.create(getActivity());
    return db.findAll(Shots.class);
  }

  private Bundle initShotBundle(List<Shots> list, int position) {

    Bundle bundle = new Bundle();

    bundle.putString("id", list.get(position).getShotsId());
    bundle.putString("title", list.get(position).getTitle());
    bundle.putString("image_url", list.get(position).getImageUrl());
    bundle.putString("views_count", list.get(position).getViewsCount());
    bundle.putString("likes_count", list.get(position).getLikesCount());
    bundle.putString("comments_count", list.get(position).getCommentsCount());
    bundle.putString("username", list.get(position).getUsername());
    bundle.putString("name", list.get(position).getName());
    bundle.putString("avatar_url", list.get(position).getAvatarUrl());
    bundle.putString("player_likes_count", list.get(position).getPlayerLikesCount());
    bundle.putString("likes_received_count", list.get(position).getLikesReceivdCount());
    bundle.putString("following_count", list.get(position).getFollowingCount());
    bundle.putString("followers_count", list.get(position).getFollowersCount());

    return bundle;
  }
}
