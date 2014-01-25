package com.lance.dribbb.adapter;

import java.util.List;
import java.util.Map;

import javax.crypto.interfaces.PBEKey;

import android.R.integer;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;
import com.lance.dribbb.R;
import com.lance.dribbb.activites.ContentActivity;
import com.lance.dribbb.application.AppData;
import com.lance.dribbb.network.BitmapLruCache;
import com.lance.dribbb.network.ShotsData;
import com.lance.dribbb.views.FooterState;

public class ContentShotsAdapter extends BaseAdapter{
  
  private Activity mActivity;
  private List<Map<String, Object>> mList;
  private LayoutInflater mInflater;
  private RequestQueue mRequestQueue;
  private ImageLoader mImageLoader;
  private int padding;
  
  public ContentShotsAdapter(Activity c, List<Map<String, Object>> list, int p) {
    this.mActivity = c;
    mList = list;
    mInflater = LayoutInflater.from(c);
    mRequestQueue = Volley.newRequestQueue(mActivity);
    padding = p;
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
    FooterState state = new FooterState();
    if((position == mList.size() - 3 || position == mList.size() - 2 || position == mList.size() - 1) 
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
  
  private static class Holder {
    public NetworkImageView shotsImage;
    public ImageView button;
    public ProgressBar pb;
  }
  
  private boolean listEnd() {
    if(getCount() == ShotsData.getSize()) {
      Log.i("isListEnd", "YES");
    } else {
      Log.i("isListEnd", "False");
    }
    return getCount() == ShotsData.getSize();
  }
  
  private ViewGroup.LayoutParams getParams(Holder holder){
    WindowManager manager = mActivity.getWindowManager();
    Display display = manager.getDefaultDisplay();
    ViewGroup.LayoutParams params = holder.shotsImage.getLayoutParams();
    params.width = display.getWidth() / 3;
    params.height = params.width*3/4;
    return params;
  }

}
