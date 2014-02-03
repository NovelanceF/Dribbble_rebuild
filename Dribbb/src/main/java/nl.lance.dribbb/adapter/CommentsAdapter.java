package nl.lance.dribbb.adapter;

import android.app.Activity;
import android.graphics.Typeface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nl.lance.dribbb.R;
import nl.lance.dribbb.network.BitmapLruCache;

/**
 * Created by Novelance on 1/28/14.
 */
public class CommentsAdapter extends BaseAdapter {

  private Activity mActivity;
  List<Map<String, Object>> mList = new ArrayList<Map<String, Object>>();
  LayoutInflater mInflater;
  ImageLoader mImageLoader;

  public CommentsAdapter(Activity a, List<Map<String, Object>> list) {
    mList = list;
    mActivity = a;
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
    Holder holder;
    if(convertView == null){
      convertView = mInflater.inflate(R.layout.item_comments, null);
      holder = new Holder();
      holder.avatar = (NetworkImageView)convertView.findViewById(R.id.item_comment_avatar);
      holder.player = (TextView)convertView.findViewById(R.id.item_comment_player);
      holder.body = (TextView)convertView.findViewById(R.id.item_comment_body);
      holder.likes = (TextView)convertView.findViewById(R.id.item_comment_like);
      holder.createTime = (TextView)convertView.findViewById(R.id.item_comment_create_time);
      convertView.setTag(holder);
    } else {
      holder = (Holder)convertView.getTag();
    }
    String imageUrl = (String) mList.get(position).get("avatar_url");
    Typeface typeface = Typeface.createFromAsset(mActivity.getAssets(), "font/Roboto-Light.ttf");

    holder.avatar.setImageUrl(imageUrl, mImageLoader);
    holder.player.setText(mList.get(position).get("name").toString());
    holder.player.setTypeface(typeface);
    holder.body.setText(Html.fromHtml(mList.get(position).get("body").toString()));
    holder.body.setTypeface(typeface);
    holder.createTime.setText(mList.get(position).get("created_at").toString());
    holder.likes.setText(mList.get(position).get("likes_count").toString());

    return convertView;
  }

  public static class Holder {

    NetworkImageView avatar;
    TextView player;
    TextView body;
    TextView likes;
    TextView createTime;
  }
}
