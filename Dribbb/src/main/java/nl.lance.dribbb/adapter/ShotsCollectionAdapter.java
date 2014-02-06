package nl.lance.dribbb.adapter;

import android.app.Activity;
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

import net.tsz.afinal.FinalDb;

import java.util.List;

import nl.lance.dribbb.R;
import nl.lance.dribbb.models.Shots;
import nl.lance.dribbb.network.BitmapLruCache;

/**
 * Created by Novelance on 2/6/14.
 */
public class ShotsCollectionAdapter extends BaseAdapter {

  private LayoutInflater mInflater;
  private Activity mActivity;
  private List<Shots> mList;
  private ImageLoader mImageLoader;
  private FinalDb db;

  public ShotsCollectionAdapter(Activity a, List<Shots> list) {
    mList = list;
    mActivity = a;
    mInflater = LayoutInflater.from(a);
    RequestQueue requestQueue = Volley.newRequestQueue(a);
    mImageLoader = new ImageLoader(requestQueue, new BitmapLruCache());
    db = FinalDb.create(a);
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
      convertView.setTag(holder);
    } else {
      holder = (Holder)convertView.getTag();
    }

    holder.shotImage.setImageUrl(mList.get(position).getImageUrl(), mImageLoader);
    holder.shotTitle.setText(mList.get(position).getTitle());
    holder.player.setText(mList.get(position).getName());
    return convertView;
  }

  private static class Holder {
    public NetworkImageView shotImage;
    public TextView shotTitle;
    public TextView player;
    public ImageButton delete;
    public TextView textView;
  }
}
