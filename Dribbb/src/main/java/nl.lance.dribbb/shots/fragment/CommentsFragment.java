package nl.lance.dribbb.shots.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import nl.lance.dribbb.R;
import nl.lance.dribbb.activites.PlayerActivity;
import nl.lance.dribbb.adapter.CommentsAdapter;
import nl.lance.dribbb.network.DribbbleAPI;
import nl.lance.dribbb.network.ShotsData;

/**
 * Created by Novelance on 1/28/14.
 */
public class CommentsFragment extends Fragment{

  private CommentsAdapter adapter;
  private ShotsData data;
  private int page = 1;

  public CommentsFragment(Activity a) {
    data = new ShotsData(a);
    adapter = new CommentsAdapter(a, data.getCommentsList());
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_comments, container, false);

    ListView listView = (ListView) rootView.findViewById(R.id.comments_listview);
    listView.setAdapter(adapter);

    TextView label = (TextView)rootView.findViewById(R.id.comments_label);
    label.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "font/Roboto-Light.ttf"));

    do {
      data.getCommentsRefresh(DribbbleAPI.getCommentsUrl(getActivity().getIntent().getExtras().getString("id")) + page ++, adapter);
    } while (page <= 6);

    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        toPlayersPage();
      }
    });

    return rootView;
  }

  private void toPlayersPage() {
    Intent intent = new Intent(getActivity(), PlayerActivity.class);
    Bundle playerBundle = new Bundle();
    Bundle bundle = getActivity().getIntent().getExtras();

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
}
