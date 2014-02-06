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

import java.util.List;

import nl.lance.dribbb.R;
import nl.lance.dribbb.models.Player;
import nl.lance.dribbb.network.BitmapLruCache;

/**
 * Created by Novelance on 2/6/14.
 */
public class PlayerCollectionAdapter extends BaseAdapter {

  private List<Player> mList;
  private LayoutInflater mInflater;
  private ImageLoader mImageLoader;
  private Activity mActivity;

  public PlayerCollectionAdapter(Activity a, List<Player> list) {
    mList = list;
    mActivity = a;
    RequestQueue requestQueue = Volley.newRequestQueue(a);
    mInflater = LayoutInflater.from(a);
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
      convertView = mInflater.inflate(R.layout.item_collection_players, null);
      holder = new Holder();
      holder.playerAvatar = (NetworkImageView)convertView.findViewById(R.id.player_collect_avatar);
      holder.playerName = (TextView)convertView.findViewById(R.id.player_collect_name);
      holder.playerUsername = (TextView)convertView.findViewById(R.id.player_collect_username);
      holder.button = (ImageButton)convertView.findViewById(R.id.player_collect_delete);
      convertView.setTag(holder);
    } else {
      holder = (Holder)convertView.getTag();
    }

    holder.playerAvatar.setImageUrl(mList.get(position).getAvatarUrl(), mImageLoader);
    holder.playerName.setText(mList.get(position).getName());
    holder.playerUsername.setText(mList.get(position).getUsername());
    return convertView;
  }

  private static class Holder {
    public NetworkImageView playerAvatar;
    public TextView playerName;
    public TextView playerUsername;
    public ImageButton button;
  }
}
