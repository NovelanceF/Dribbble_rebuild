package nl.lance.dribbb.adapter;

import android.app.Activity;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;
import java.util.Map;

import nl.lance.dribbb.R;
import nl.lance.dribbb.views.FooterState;

/**
 * Created by Novelance on 2/3/14.
 */
public class PlayersAdapter extends ContentShotsAdapter {
  public PlayersAdapter(Activity c, List<Map<String, Object>> list, int p) {
    super(c, list, p);
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    Holder holder;
    if (convertView == null) {
      convertView = mInflater.inflate(R.layout.item_shots_layout, null);
      holder = new Holder();
      holder.shotsImage = (NetworkImageView)convertView.findViewById(R.id.shots_item_image);
      holder.pb = (ProgressBar)convertView.findViewById(R.id.gird_loading);
      holder.button = (ImageView)convertView.findViewById(R.id.hhhhh);

      convertView.setTag(holder);
    } else {
      holder = (Holder)convertView.getTag();
    }
    FooterState state = new FooterState();
    if((position == mList.size() - 1)
            //&& state.getState() == FooterState.State.Loading
            && !listEnd()) {
      holder.pb.setVisibility(View.VISIBLE);
    } else {
      holder.pb.setVisibility(View.GONE);
    }

    holder.shotsImage.setLayoutParams(getParams(holder));
    holder.shotsImage.setImageUrl((String) mList.get(position).get("image_teaser_url"), mImageLoader);

    return convertView;
  }

  @Override
  public ViewGroup.LayoutParams getParams(Holder holder) {
    WindowManager manager = mActivity.getWindowManager();
    Display display = manager.getDefaultDisplay();
    ViewGroup.LayoutParams params = holder.shotsImage.getLayoutParams();
    params.width = display.getWidth() / 2;
    params.height = params.width * 3/4;
    return params;
  }
}
