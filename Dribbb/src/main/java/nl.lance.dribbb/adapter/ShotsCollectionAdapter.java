package nl.lance.dribbb.adapter;

import android.app.Activity;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import java.util.List;

import nl.lance.dribbb.R;
import nl.lance.dribbb.models.Shots;
import nl.lance.dribbb.network.BitmapLruCache;

/**
 * Created by Novelance on 2/6/14.
 */
public class ShotsCollectionAdapter extends BaseAdapter {

  private LayoutInflater mInflater;
  private Typeface typeface;
  private List<Shots> mList;
  private ImageLoader mImageLoader;

  public ShotsCollectionAdapter(Activity a, List<Shots> list) {
    mList = list;
    typeface = Typeface.createFromAsset(a.getAssets(), "font/Roboto-Light.ttf");
    mInflater = LayoutInflater.from(a);
    RequestQueue requestQueue = Volley.newRequestQueue(a);
    mImageLoader = new ImageLoader(requestQueue, new BitmapLruCache());
  }

  @Override
  public int getCount() {
    return mList.size();
  }

  @Override
  public Object getItem(int position) {
    return mList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    Holder holder = null;
    if(convertView == null){
      convertView = mInflater.inflate(R.layout.item_collection_shots, null);
      holder = new Holder();
      holder.shotImage = (NetworkImageView)convertView.findViewById(R.id.collect_shot_image);
      holder.shotTitle = (TextView)convertView.findViewById(R.id.collect_shot_title);
      holder.player = (TextView)convertView.findViewById(R.id.collect_shot_player);
      holder.delete = (ImageButton)convertView.findViewById(R.id.collect_shot_delete);
      holder.shotDeleteLabel = (TextView)convertView.findViewById(R.id.shot_delete_label);
      convertView.setTag(holder);
    } else {
      holder = (Holder)convertView.getTag();
    }

    holder.shotImage.setImageUrl(mList.get(position).getImageUrl(), mImageLoader);
    holder.shotTitle.setText(mList.get(position).getTitle());
    holder.shotTitle.setTypeface(typeface);
    holder.player.setText(mList.get(position).getName());
    holder.shotDeleteLabel.setTypeface(typeface);
    return convertView;
  }

  private static class Holder {
    public NetworkImageView shotImage;
    public TextView shotTitle;
    public TextView player;
    public ImageButton delete;
    public TextView shotDeleteLabel;
  }
}
