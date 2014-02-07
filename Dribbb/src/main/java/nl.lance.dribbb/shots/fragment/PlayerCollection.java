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
import nl.lance.dribbb.activites.PlayerActivity;
import nl.lance.dribbb.adapter.PlayerCollectionAdapter;
import nl.lance.dribbb.models.Player;

/**
 * Created by Novelance on 2/4/14.
 */
public class PlayerCollection extends Fragment{

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_player_collect, container, false);

    SwipeListView listView = (SwipeListView)rootView.findViewById(R.id.player_collected_listview);
    PlayerCollectionAdapter adapter = new PlayerCollectionAdapter(getActivity(), getData());
    listView.setAdapter(adapter);

    final FinalDb db = FinalDb.create(getActivity());
    final List<Player> list = db.findAll(Player.class);

    listView.setSwipeListViewListener(new BaseSwipeListViewListener(){

      @Override
      public void onStartOpen(int position, int action, boolean right) {
      }

      @Override
      public void onStartClose(int position, boolean right) {
      }

      @Override
      public void onClickFrontView(int position) {
        Intent intent = new Intent(getActivity(), PlayerActivity.class);

        intent.putExtras(initPlayerInfoBundle(list, position));
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
      }

      @Override
      public void onClickBackView(int position) {
        Player player = new Player();
        player.setId(list.get(position).getId());
        db.delete(player);

        Intent intent = new Intent(getActivity(), LoadingActivity.class);
        intent.putExtra("ct", 1);
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

  private List<Player> getData(){
    FinalDb db = FinalDb.create(getActivity());
    return db.findAll(Player.class);
  }

  private Bundle initPlayerInfoBundle(List<Player> list, int position) {
    Bundle bundle = new Bundle();

    bundle.putString("username", list.get(position).getUsername());
    bundle.putString("avatar_url", list.get(position).getAvatarUrl());
    bundle.putString("name", list.get(position).getName());
    bundle.putString("likes_count", list.get(position).getLikesCount());
    bundle.putString("likes_received_count", list.get(position).getLikesReceivedCount());
    bundle.putString("following_count", list.get(position).getFollowingCount());
    bundle.putString("followers_count", list.get(position).getFollowersCount());

    return bundle;
  }
}
