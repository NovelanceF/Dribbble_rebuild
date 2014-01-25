package com.lance.dribbb.network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.UserDataHandler;

import android.app.Activity;
import android.content.SharedPreferences;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.lance.dribbb.R;
import com.lance.dribbb.adapter.DrawerAdapter;

public class PlayerData {
  
  private Activity mActivity;
  private RequestQueue mRequestQueue;
  private List<Map<String, Object>> drawerList = new ArrayList<Map<String,Object>>();
  SharedPreferences userInfo;
  
  public PlayerData(Activity a) {
    mRequestQueue = Volley.newRequestQueue(a);
    mActivity = a;
    userInfo = mActivity.getSharedPreferences("user_info", 0);
  }
  
  public List<Map<String, Object>> getDrawerList() {
    Map<String, Object> map = new HashMap<String, Object>();
    map.put("user_avatar", R.drawable.player_unconnected);
    map.put("user_info1", userInfo.getString("name", "Tap to connect"));
    map.put("user_info2", userInfo.getString("Followings", "to your dribbble account"));
    drawerList.add(map);
    
    map = new HashMap<String, Object>();
    map.put("drawer_items", "Followings");
    drawerList.add(map);
    
    map = new HashMap<String, Object>();
    map.put("drawer_items", "Likes");
    drawerList.add(map);
    
    map = new HashMap<String, Object>();
    map.put("drawer_items", "About");
    drawerList.add(map);
    
    return drawerList;
  }
  
  public void getPlayerInfo(String url, final DrawerAdapter adapter) {
    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, 
        new Response.Listener<JSONObject>() {

          @Override
          public void onResponse(JSONObject arg0) {
            try {
              initPlayerInfo(arg0);
              adapter.notifyDataSetChanged();
            } catch (JSONException e) {
              e.printStackTrace();
            }
          }
      
    }, new Response.ErrorListener() {

      @Override
      public void onErrorResponse(VolleyError arg0) {
        
      }
    });
    mRequestQueue.add(jsonObjectRequest);
  }
  
  public void initPlayerInfo(JSONObject jsonObject) throws JSONException {
    userInfo.edit().putString("avatar_url", jsonObject.getString("avatar_url").toString()).commit();  
    //map.put("user_info1", jsonObject.getString("name").toString());
    userInfo.edit().putString("name", jsonObject.getString("name").toString()).commit();
    //map.put("user_info2", jsonObject.getString("following_count").toString());
    userInfo.edit().putString("Followings", jsonObject.getString("following_count").toString() + " Followers, ").commit();
    userInfo.edit().putString("likesReceived", jsonObject.getString("likes_received_count").toString() + " Likes received.").commit();
  }

}
