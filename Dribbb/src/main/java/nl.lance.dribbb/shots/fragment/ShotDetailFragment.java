package nl.lance.dribbb.shots.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import net.frakbot.imageviewex.ImageViewNext;

import nl.lance.dribbb.R;

/**
 * Created by Novelance on 1/26/14.
 */
public class ShotDetailFragment extends Fragment {

  private Bundle shotDetail = new Bundle();

  public ShotDetailFragment() {
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_shots_detail, container, false);
    ImageViewNext.setMaximumNumberOfThreads(200);
    initShotDetail(rootView);
    return rootView;
  }

  private void initShotDetail(View view) {

    ImageViewNext playerAvatar = (ImageViewNext)view.findViewById(R.id.detail_avatar);
    ImageViewNext detailimage = (ImageViewNext)view.findViewById(R.id.detail_image);

    setImageParmas(detailimage);

    TextView title = (TextView)view.findViewById(R.id.detail_title);
    TextView player = (TextView)view.findViewById(R.id.detail_player);
    TextView views = (TextView)view.findViewById(R.id.detail_views);
    TextView likes = (TextView)view.findViewById(R.id.detail_likes);
    TextView comments = (TextView)view.findViewById(R.id.detail_commentss);

    playerAvatar.setUrl(getActivity().getIntent().getExtras().getString("player_avatar_url"));
    detailimage.setUrl(getActivity().getIntent().getExtras().getString("image_url"));

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
