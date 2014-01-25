package com.lance.dribbb.adapter;

import java.util.List;
import java.util.Map;

import android.R.integer;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.lance.dribbb.R;
import com.lance.dribbb.network.BitmapLruCache;

public class DrawerAdapter extends BaseAdapter {
  
  private Activity mActivity;
  private LayoutInflater mInflater;
  private List<Map<String, Object>> mList;
  private Typeface typeface;
  private SharedPreferences userInfo;
  private RequestQueue mRequestQueue;
  private ImageLoader mImageLoader;
  
  public DrawerAdapter(Activity a, List<Map<String, Object>> list) {
    mActivity = a;
    mInflater = LayoutInflater.from(a);
    mList = list;
    typeface = Typeface.createFromAsset(a.getAssets(), "font/Roboto-Light.ttf");
    userInfo = a.getSharedPreferences("user_info", 0);
    mRequestQueue = Volley.newRequestQueue(mActivity);
    mImageLoader = new ImageLoader(mRequestQueue, new BitmapLruCache());
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
    if(position == 0) {
      Holder1 holder;
      if(convertView == null) {
        convertView = mInflater.inflate(R.layout.item_user, null);
        holder = new Holder1();
        holder.userAvatar = (NetworkImageView)convertView.findViewById(R.id.item_user_avatar);
        holder.userInfo1 = (TextView)convertView.findViewById(R.id.item_user_info);
        holder.userInfo2 = (TextView)convertView.findViewById(R.id.item_user_info2);
        convertView.setTag(holder);
      } else {
        holder = (Holder1)convertView.getTag();
      }
      
      if(userInfo.getString("avatar_url", "") == "") {
        //holder.userAvatar.setImageResource(R.drawable.player_unconnected);
      } else {
        holder.userAvatar.setImageUrl(userInfo.getString("avatar_url", "").toString(), mImageLoader);
      }
      holder.userInfo1.setText(userInfo.getString("name", "Tap to connect").toString());
      holder.userInfo1.setTypeface(typeface);
      String userInfo2 = userInfo.getString("Followings", "to your Dribbble account").toString() + userInfo.getString("likesReceived", "").toString();
      holder.userInfo2.setText(userInfo2);
      holder.userInfo2.setTypeface(typeface);
      
    } else {
      Holder2 holder;
      if (convertView == null) {
        convertView = mInflater.inflate(R.layout.item_others, null);
        holder = new Holder2();
        holder.itemText = (TextView)convertView.findViewById(R.id.item_text);
        convertView.setTag(holder);
      } else {
        holder = (Holder2)convertView.getTag();
      }
      
      if(userInfo.getString("avatar_url", "null").equals("null") && position != 3) {
        holder.itemText.setTextColor(0xFF454545);
      } else {
        holder.itemText.setTextColor(0xFFdfdfdf);
      }
      holder.itemText.setText(mList.get(position).get("drawer_items").toString());
      holder.itemText.setTypeface(typeface);
      
    }
    return convertView;
  }
  
  private static class Holder1 {
    NetworkImageView userAvatar;
    TextView userInfo1;
    TextView userInfo2;
  }
  
  private static class Holder2 {
    TextView itemText;
  }

}
