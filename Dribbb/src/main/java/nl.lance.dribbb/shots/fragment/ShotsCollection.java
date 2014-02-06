package nl.lance.dribbb.shots.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fortysevendeg.swipelistview.SwipeListView;

import net.tsz.afinal.FinalDb;

import java.util.List;

import nl.lance.dribbb.R;
import nl.lance.dribbb.adapter.ShotsCollectionAdapter;
import nl.lance.dribbb.models.Shots;

/**
 * Created by Novelance on 2/4/14.
 */
public class ShotsCollection extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_shots_collect, container, false);

    SwipeListView listView = (SwipeListView)rootView.findViewById(R.id.shots_collected_listview);
    ShotsCollectionAdapter adapter = new ShotsCollectionAdapter(getActivity(), initData());
    listView.setAdapter(adapter);
    return rootView;
  }

  private List<Shots> initData() {
    FinalDb db = FinalDb.create(getActivity());
    return db.findAll(Shots.class);
  }
}
