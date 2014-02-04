package nl.lance.dribbb.shots.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import nl.lance.dribbb.R;

/**
 * Created by Novelance on 2/4/14.
 */
public class ShotsCollection extends Fragment {

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_shots_collect, container, false);
    return rootView;
  }
}
