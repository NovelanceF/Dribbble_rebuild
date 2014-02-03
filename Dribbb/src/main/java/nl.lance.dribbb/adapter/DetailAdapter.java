package nl.lance.dribbb.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.toolbox.NetworkImageView;

import java.util.List;
import java.util.Map;

import nl.lance.dribbb.R;

/**
 * Created by Novelance on 2/2/14.
 */
public class DetailAdapter extends ContentShotsAdapter {

  public DetailAdapter(Activity c, List<Map<String, Object>> list, int p) {
    super(c, list, p);

  }

  @Override
  public int getCount() {
    if(mList.size() <= 9) {
      return mList.size();
    } else {
      return 9;
    }
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

    if(position == 0 || position == 1 || position == 2) {
      holder.button.setVisibility(View.VISIBLE);
      holder.button.setPadding(0, padding, 0, 0);
    } else {
      holder.button.setPadding(0, 0, 0, 0);
    }

    holder.shotsImage.setLayoutParams(getParams(holder));
    holder.shotsImage.setImageUrl((String) mList.get(position).get("image_teaser_url"), mImageLoader);

    return convertView;
  }

}
