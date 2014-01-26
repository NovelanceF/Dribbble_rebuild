package nl.lance.dribbb.shots.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nl.lance.dribbb.R;

/**
 * Created by Novelance on 1/26/14.
 */
public class ShotDetailFragment extends Fragment {

  public ShotDetailFragment(Bundle shotsDetail) {

  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_shots_detail, container, false);
    return rootView;
  }
}
