package nl.lance.dribbb.shots.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.GridView;

import nl.lance.dribbb.R;
import nl.lance.dribbb.activites.ShotsDetail;
import nl.lance.dribbb.adapter.ContentShotsAdapter;
import nl.lance.dribbb.network.DribbbleAPI;
import nl.lance.dribbb.network.ShotsData;
import nl.lance.dribbb.views.FooterState;

public class ShotsFragment extends Fragment {
  
  private ShotsData data;
  private static int pageDebut = 1, pagePopular = 1, pageEveryone = 1, page = 1;
  private String currentUrl = null;
  private ContentShotsAdapter adapter;
  final FooterState footerState = new FooterState();
  
  public ShotsFragment(Activity a, String Url, int padding) {
    data = new ShotsData(a);
    currentUrl = Url;
    adapter = new ContentShotsAdapter(a, data.getList(), padding);
  }
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_shots, null);
    final GridView gridView = (GridView)rootView.findViewById(R.id.shots_grid);
    gridView.setAdapter(adapter);
    initGridView(page);

    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), ShotsDetail.class);
        Bundle bundle = new Bundle();

        String tags[] = DribbbleAPI.tagBundleShots;

        for (int i = 0; i < tags.length; i++) {
          bundle.putString(tags[i], data.getList().get(position).get(tags[i]).toString());
          if(i == 4) {
            bundle.putString("player_"+tags[i], data.getList().get(position).get("player_"+tags[i]).toString());
          }
        }

        intent.putExtras(bundle);
        getActivity().startActivity(intent);
        getActivity().overridePendingTransition(R.anim.abc_fade_in, R.anim.abc_fade_out);
      }
    });

    gridView.setOnScrollListener(new OnScrollListener() {
      
      @Override
      public void onScrollStateChanged(AbsListView view, int scrollState) {
        
      }
      
      @Override
      public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(footerState.getState() == FooterState.State.Loading || footerState.getState() == FooterState.State.TheEnd) {
          return;
        }
        if(firstVisibleItem + visibleItemCount == totalItemCount && totalItemCount != 0 && totalItemCount != 2 && adapter.getCount() > 0) {
          shotsLoading(getCurrentPage());
        }
      }
    });

    return rootView;
  }
  
  private void initGridView(int page) {
    shotsLoading(page);
  }
  
  private int getCurrentPage(){
    if(currentUrl.equals(DribbbleAPI.SHOTS_DEBUTS)){
      return ++ pageDebut;
    } else if (currentUrl.equals(DribbbleAPI.SHOTS_POPULAR)) {
      return ++ pagePopular;
    } else if (currentUrl.equals(DribbbleAPI.SHOTS_EVERYONE)){
      return ++ pageEveryone;
    } else {
      return ++ page;
    }
  }
  
  @Override
  public void onStop() {
    page = pageDebut = pageEveryone = pagePopular = 1;
    super.onStop();
  }

  private void shotsLoading(int page) {
    footerState.setState(FooterState.State.Loading);
    data.getShotsRefresh(currentUrl + page, adapter, footerState, null);
  }

}
