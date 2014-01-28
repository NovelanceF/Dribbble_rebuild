package nl.lance.dribbb.shots.fragment;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import nl.lance.dribbb.R;
import nl.lance.dribbb.adapter.CommentsAdapter;
import nl.lance.dribbb.network.DribbbleAPI;
import nl.lance.dribbb.network.ShotsData;

/**
 * Created by Novelance on 1/28/14.
 */
public class CommentsFragment extends Fragment{

  private CommentsAdapter adapter;
  private ShotsData data;

  public CommentsFragment(Activity a) {
    data = new ShotsData(a);
    adapter = new CommentsAdapter(a, data.getCommentsList());
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_comments, container, false);

    ListView listView = (ListView) rootView.findViewById(R.id.comments_listview);
    listView.setAdapter(adapter);

    data.getCommentsRefresh(DribbbleAPI.getCommentsUrl(getActivity().getIntent().getExtras().getString("id")), adapter);
    return rootView;
  }
}
